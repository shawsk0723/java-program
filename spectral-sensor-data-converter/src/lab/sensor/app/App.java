package lab.sensor.app;

import lab.sensor.log.Log;

public class App {

	public static void main(String... args) {
		try {
			SensorDataInfo sensorDataInfo = new InputArgumentParser().parseInputArgument(args);
			new SensorDataToCsvConverter(sensorDataInfo).execute();
		} catch (IllegalArgumentException e) {
			Log.i(e.getMessage());
			HelpMessage.print();
		}

	}
}