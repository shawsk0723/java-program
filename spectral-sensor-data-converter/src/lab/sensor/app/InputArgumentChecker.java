package lab.sensor.app;

import lab.sensor.file.DirChecker;

public class InputArgumentChecker {

	private static String MESSAGE_INPUT_ARG_INVALID = "input argument is invalid."; 
	private static String MESSAGE_PLEASE_INPUT_ARG = "=> please input both source and destination path.";
	private static String MESSAGE_CHECK_INPUT_PATH_VALIDITY = "=> please check whether both source path and destination path are valid.";
	private static String NEW_LINE = "\n";
	
	public void checkInputArgument(String... args) throws Exception {
		if(args.length < 2) {
			String message = MESSAGE_INPUT_ARG_INVALID 
							+ NEW_LINE
							+ MESSAGE_PLEASE_INPUT_ARG;
			throw new Exception(message);
		} 

		for(int i = 0; i < args.length; i++) {
			if(!DirChecker.isPathExist(args[i])) {
				String message = MESSAGE_INPUT_ARG_INVALID
								+ NEW_LINE
								+ MESSAGE_CHECK_INPUT_PATH_VALIDITY;
				throw new Exception(message);
			}
		}
	}
}