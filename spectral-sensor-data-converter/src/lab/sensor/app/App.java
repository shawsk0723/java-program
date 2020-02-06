package lab.sensor.app;

import lab.sensor.log.Log;

public class App {

	public static void main(String... args) {
		try {
			new InputArgumentChecker().checkInputArgument(args);
			new RenameAndCopy(args[0], args[1]).execute();
		} catch (Exception e) {
			Log.i(e.getMessage());
		}

	}
}