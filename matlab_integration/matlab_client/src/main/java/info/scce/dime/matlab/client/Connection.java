package info.scce.dime.matlab.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.reflect.TypeToken;
import info.scce.dime.matlab.common.FunctionCall;
import java.lang.reflect.Type;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Connection {
  public static Gson json =
      new GsonBuilder()
          .setObjectToNumberStrategy(ToNumberPolicy.DOUBLE)
          .setNumberToNumberStrategy(ToNumberPolicy.DOUBLE)
          .create();

  public static Logger log = LoggerFactory.getLogger(App.class);

  public AsynchronousSocketChannel client;

  public Connection(InetSocketAddress addr) throws Exception /* TODO: use better error handling*/ {
    client = AsynchronousSocketChannel.open();
    client.connect(addr).get();
    log.info("sucessfully connected to server");
  }

  public <T> T request(FunctionCall fc) throws Exception {
    String jsoned = json.toJson(fc);
    log.info("sending request {}", jsoned);
    client.write(ByteBuffer.wrap(jsoned.getBytes())).get();
    // could probably do something better than allocating
    // an entire buffer everytime but hey
    ByteBuffer buffer = ByteBuffer.allocate(2048);
    client.read(buffer).get();
    String response = new String(buffer.array()).trim();
    Type ty = new TypeToken<T>() {}.getType();
    log.info("received response");
    return json.fromJson(response, ty);
  }

  public void close() throws Exception {
    client.close();
  }
}
