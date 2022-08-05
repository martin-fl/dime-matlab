package info.scce.dime.matlab.client;

import info.scce.dime.matlab.common.FunctionCall;
import java.net.InetSocketAddress;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
  public static Logger log = LoggerFactory.getLogger(App.class);

  public static void main(String[] args) {
    try {
      Connection conn = new Connection(new InetSocketAddress(/*"172.17.0.2"*/"localhost", 4242));

      List<List<Double>> A = List.of(List.of(3.0, 0.0), List.of(0.0, 3.0));

      Object res;
      for (int i = 0; i < 10; ++i) {
        res = conn.request(new FunctionCall("test", i, "test str!", A));
        log.info("res = {}", res.toString());
        res = conn.request(new FunctionCall("process", i, A));
        log.info("res = {}", res.toString());
        Thread.sleep(500);
      }

      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
