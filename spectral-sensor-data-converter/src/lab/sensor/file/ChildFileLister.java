package lab.sensor.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ChildFileLister {
	public static List<String> getChildFileList(String parentDirPath) {
		List<String> strFileList = new ArrayList<String>();
		File parentDir = new File(parentDirPath);
		File[] fileList = parentDir.listFiles();
		for(int i=0; i<fileList.length; i++) {
			strFileList.add(fileList[i].toString());
		}
		return strFileList;
	}
}
