package lab.sensor.app;

import java.util.List;

import lab.sensor.file.FileCopy;
import lab.sensor.file.FilePathSeparator;
import lab.sensor.file.SendsorDataFileLister;
import lab.sensor.log.Log;

public class RenameAndCopy {
	private static String TAG = SendsorDataFileLister.class.getSimpleName();

	private static final String FILE_PATH_SEPARATOR = "\\";
	
	private static final String FN_DELIM_FIRST = "~";
	private static final String FN_DELIM_SECOND = "_Abs_";
	
	private String srcRootPath;
	private String destRootPath;
	
	public RenameAndCopy(String srcRootPath, String destRootPath) {
		this.srcRootPath = srcRootPath;
		this.destRootPath= destRootPath;
	}

	public void execute() {
		/*
			source folder structure
			: [fabric]/[sub-fabric]/[iteration-number].file-ext

			dest file name
			: [fabric]~[sub-fabric]_Abs_[iteration-number].file-ext

			ex)
			wool/A-1/1.ard 
			-> wool~A-1_Abs_1.ard
		*/

		try {
			Log.i(TAG, "sensor data rename and copy start ~");

			List<String> sensorDataFileList = SendsorDataFileLister.getSensorDataFileList(srcRootPath);
	
			for(String sensorDataFile : sensorDataFileList) {
				String destFileFullPath = makeDestFileFullPath(sensorDataFile);
				Log.d(TAG, "dest file " + destFileFullPath);
				FileCopy.copy(sensorDataFile, destFileFullPath);
			}

			Log.i(TAG, "sensor data rename and copy success ~");
		} catch(Exception e) {
			Log.e(TAG, e);
			Log.i(TAG, "sensor data rename and copy fail ~");
		}
	}

	
	private String makeDestFileFullPath(String srcFileFullPath) {
		String destFileFullPath = "";
		String validPath = srcFileFullPath.replace(srcRootPath + FILE_PATH_SEPARATOR, "");
		List<String> pathComponents = FilePathSeparator.splitFilePath(validPath);

		destFileFullPath = destRootPath + FILE_PATH_SEPARATOR + 
							pathComponents.get(0) + FN_DELIM_FIRST + 
							pathComponents.get(1) + FN_DELIM_SECOND +
							pathComponents.get(2);
		return destFileFullPath;
	}

}