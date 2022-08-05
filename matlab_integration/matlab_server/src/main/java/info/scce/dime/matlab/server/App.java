package info.scce.dime.matlab.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.mathworks.engine.MatlabEngine;
import info.scce.dime.matlab.common.FunctionCall;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  public static Gson json =
      new GsonBuilder()
          .setObjectToNumberStrategy(ToNumberPolicy.DOUBLE)
          .setNumberToNumberStrategy(ToNumberPolicy.DOUBLE)
          .create();

  public static Logger log = LoggerFactory.getLogger(App.class);

  public static void handle(AsynchronousSocketChannel client, MatlabEngine engine)
      throws InterruptedException, ExecutionException {
    ByteBuffer buffer = ByteBuffer.allocate(2048);

    if (client.read(buffer).get() < 0) {
      return;
    }

    String input = new String(buffer.array()).trim();
    log.info("received request");

    FunctionCall request = json.fromJson(input, FunctionCall.class);
    Object result = FunctionCaller.call(request, engine).get();

    String output = json.toJson(result);

    client.write(ByteBuffer.wrap(output.getBytes())).get();
    log.info("sent response");

    App.handle(client, engine);
  }

  public static void main(String[] args) {
    try {
      // Using a group with a threadpool to resume execution even when the
      // executing thread terminates, also allows to keep the thread alive until
      // all futures have resolved
      AsynchronousChannelGroup group =
          AsynchronousChannelGroup.withThreadPool(Executors.newWorkStealingPool());

      AsynchronousServerSocketChannel server =
          AsynchronousServerSocketChannel.open(group).bind(new InetSocketAddress(4242));

      log.info("setting up matlab engine...");
      // does not throw, unlike the sync version => we always get a session
      MatlabEngine engine = MatlabEngine.connectMatlabAsync().get();
      log.info("matlab engine ready");

      // Add the matlab_code directory to matlab's search path
      String gen = (String) engine.fevalAsync("genpath", "matlab_code").get();
      engine.fevalAsync("addpath", gen).get();

      // Set the server to accept connections and how to handle them
      server.accept(
          engine,
          new CompletionHandler<AsynchronousSocketChannel, MatlabEngine>() {
            public void completed(AsynchronousSocketChannel client, MatlabEngine engine) {
              server.accept(engine, this);
              try {
                log.info("new connection");
                App.handle(client, engine);
              } catch (Exception e) {
                try {
                  client.close();
                } catch (Exception h) {
                  h.printStackTrace();
                }
                e.printStackTrace();
              }
            }

            public void failed(Throwable exec, MatlabEngine engine) {
              log.error("failed to accept connection!");
            }
          });

      // Terminate the program after long timeout and when nothing is running
      group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
