package lab.sensor.file;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FilePathSeparator {
	private static final String PATH_SEPARATOR = "\\";
	private static final String PATH_SEPARATOR_DOUBLE = "\\\\";

	public static List<String> splitFilePath(String path) {
		List<String> pathSplitted = new ArrayList<>();
		String[] dirNames = path.replaceAll(Pattern.quote(PATH_SEPARATOR), PATH_SEPARATOR_DOUBLE).split(PATH_SEPARATOR_DOUBLE);
		for(int i = 0; i < dirNames.length; i++) {
			pathSplitted.add(dirNames[i]);
		}
		return pathSplitted;
	}
	
	public static String getLastDirNameFromPath(String path) {
		String lastDirName = "";
		String[] dirNames = path.replaceAll(Pattern.quote(PATH_SEPARATOR), PATH_SEPARATOR_DOUBLE).split(PATH_SEPARATOR_DOUBLE);
		lastDirName = dirNames[dirNames.length-1];
		return lastDirName;
	}


}