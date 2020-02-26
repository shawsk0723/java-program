package lab.sensor.app;

import lab.sensor.file.DirChecker;
import lab.sensor.irsensor.SensorNotSupportedException;
import lab.sensor.irsensor.SensorParserManager;


public class InputArgumentParser {

	private static final int ARG_NUM = 3;
	
	private static String MESSAGE_INPUT_ARG_INVALID = "input argument is invalid."; 
	private static String MESSAGE_PLEASE_INPUT_ARG = "=> please input valid arguments";
	private static String MESSAGE_CHECK_INPUT_PATH_VALIDITY = "=> please check whether both source path and destination path are valid.";
	private static String NEW_LINE = "\n";
	
	public SensorDataInfo parseInputArgument(String... args) throws IllegalArgumentException {
		if(args.length < ARG_NUM) {
			String message = MESSAGE_INPUT_ARG_INVALID 
							+ NEW_LINE
							+ MESSAGE_PLEASE_INPUT_ARG;
			throw new IllegalArgumentException(message);
		} 

		try {
			SensorParserManager.getInstance().getSensorDataFileParser(args[0]);
		} catch (SensorNotSupportedException e) {
			throw new IllegalArgumentException(args[0] + " sensor is not supported ~");
		}
		
		for(int i = 1; i < args.length; i++) {
			if(!DirChecker.isPathExist(args[i])) {
				String message = MESSAGE_INPUT_ARG_INVALID
								+ NEW_LINE
								+ MESSAGE_CHECK_INPUT_PATH_VALIDITY;
				throw new IllegalArgumentException(message);
			}
		}

		return new SensorDataInfo().sensorName(args[0])
									.srcRootPath(args[1])
									.destRootPath(args[2]);
	}
}