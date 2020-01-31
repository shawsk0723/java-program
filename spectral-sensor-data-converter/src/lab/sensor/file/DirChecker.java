package lab.sensor.file;

import java.io.File;
import java.util.List;

public class DirChecker {
	public static boolean isPathExist(String path) {
		File file  = new File(path); 
		if(!file.exists()) {
				return false;
		}
		return true;
	}
	
	public static boolean isDirListExist(List<String> dirList) {
		for(String dir : dirList) {
			File file  = new File(dir); 
			if(!file.exists()) {
				return false;
			}
		}
		return true;
	}
}
