package lab.sensor.app;

import lab.sensor.file.SendsorDataFileMover;
import lab.sensor.log.Log;

public class App {
	/*
	private static final String TAG = App.class.getSimpleName();
	private static final String SRC_PATH = "D:\\java-test-data\\spectral-sensor-data\\src";
	private static final String DEST_PATH = "D:\\java-test-data\\spectral-sensor-data\\dest";
	*/
	public static void main(String... args) {
		try {
			new InputArgumentChecker().checkInputArgument(args);
			new SendsorDataFileMover(args[0], args[1]).renameAndMove();
		} catch (Exception e) {
			Log.i(e.getMessage());
		}

	}
}