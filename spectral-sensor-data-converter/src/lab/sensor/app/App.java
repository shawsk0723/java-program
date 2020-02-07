package lab.sensor.app;

import lab.sensor.log.Log;

public class App {

	public static void main(String... args) {
		try {
			SensorDataInfo sensorDataInfo = new InputArgumentParser().parseInputArgument(args);
			//new RenameAndCopy(args[0], args[1]).execute();
			new SensorDataToCsvConverter(sensorDataInfo).execute();
		} catch (Exception e) {
			Log.i(e.getMessage());
		}

	}
}