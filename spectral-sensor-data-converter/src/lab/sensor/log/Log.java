package lab.sensor.log;

public class Log {
	public static void i(String message) {
		System.out.println(message);
	}
	
	public static void i(String TAG, String message) {
		System.out.println("[" + TAG + "]" + " " + message);
	}
	
	public static void d(String TAG, String message) {
		System.out.println("[" + TAG + "]" + " " + message);
	}

	public static void e(String TAG, Exception e) {
		System.out.println("[" + TAG + "]" + " " + e.getMessage());
	}
}
