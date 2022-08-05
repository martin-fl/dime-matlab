package info.scce.dime.matlab.client;

import info.scce.dime.matlab.common.FunctionCall;
import java.net.InetSocketAddress;
import java.util.List;

public class Bridge {
	static Connection connection = null;
	
	public static Connection get() {
		if (connection == null) {
			try { 
				connection = new Connection(new InetSocketAddress("172.17.0.1", 4242));
			} catch (Exception e) { 
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	public static double call_process(double a, double b) {
		try {
			return Bridge.get().request(new FunctionCall("process", a, b));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0;
	}
	
	public static String call_function_no_args(String name) {
		try {
			return Bridge.get().request(new FunctionCall(name));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "";
	}
	
	public static List<Double> call_double(List<Double> xs) {
		try {
			return Bridge.get().request(new FunctionCall("mydouble", xs));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return xs;
	}
}