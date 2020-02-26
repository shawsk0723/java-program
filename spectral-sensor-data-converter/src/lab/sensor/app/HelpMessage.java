package lab.sensor.app;

import lab.sensor.irsensor.SensorParserManager;

public class HelpMessage {
	private static final String HELP_MESSAGE = "Inupt arguments format. \n"
			+ "[ir sensor name] [source root path of IR sensor raw data file] [destination root path to save csv file]";

	private static final String SUPPORTED_SENSOR_LIST = "Supported sensor list : ";
	
	public static void print() {
		System.out.println(HELP_MESSAGE);
		System.out.println(SUPPORTED_SENSOR_LIST + 
							SensorParserManager.getInstance().getSupportedSensorList());
	}
}
