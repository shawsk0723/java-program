package lab.sensor.app;

public class HelpMessage {
	private static final String HELP_MESSAGE = "Inupt arguments format. \n"
			+ "[source root path of IR sensor raw data file] [destination root path to save csv file]";
	
	
	public static void print() {
		System.out.println(HELP_MESSAGE);
	}
}
