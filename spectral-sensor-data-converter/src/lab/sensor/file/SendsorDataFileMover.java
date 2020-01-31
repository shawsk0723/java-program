package lab.sensor.file;

import java.util.List;

import lab.sensor.log.Log;

public class SendsorDataFileMover {
	private static String TAG = SendsorDataFileMover.class.getSimpleName();

	private static final String FILE_PATH_SEPARATOR = "\\";
	
	private static final String FN_DELIM_FIRST = "~";
	private static final String FN_DELIM_SECOND = "_Abs_";
	
	private String srcRootPath;
	private String destRootPath;
	
	public SendsorDataFileMover(String srcRootPath, String destRootPath) {
		this.srcRootPath = srcRootPath;
		this.destRootPath= destRootPath;
	}

	public void renameAndMove() {
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
			List<String> fabricPathList = ChildFileLister.getChildFileList(srcRootPath);
	
			for(String fabricPath : fabricPathList) {
				List<String> subFabricPathList = ChildFileLister.getChildFileList(fabricPath);
				for(String subFabricPath : subFabricPathList) {
					//Log.d(TAG, "sub fabric path = " + subFabricPath);
					List<String> sensorDataFileList = ChildFileLister.getChildFileList(subFabricPath);
					for(String sensorDataFile : sensorDataFileList) {
						Log.d(TAG, "src file = " + sensorDataFile);
						String destFileFullPath = makeDestFileFullPath(sensorDataFile);
						Log.d(TAG, "dest file " + destFileFullPath);
						FileCopy.copy(sensorDataFile, destFileFullPath);
					}
				}
			}

			Log.i(TAG, "File Conversion Success ~");
		} catch(Exception e) {
			Log.e(TAG, e);
			Log.i(TAG, "File Conversion Fail ~");
		}
	}

	
	private String makeDestFileFullPath(String srcFileFullPath) {
		String destFileFullPath = "";
		String validPath = srcFileFullPath.replace(srcRootPath + FILE_PATH_SEPARATOR, "");
		//Log.d(TAG, validPath);
		List<String> pathComponents = FilePathSeparator.splitFilePath(validPath);
		//Log.d(TAG, pathComponents.toString());

		destFileFullPath = destRootPath + FILE_PATH_SEPARATOR + 
							pathComponents.get(0) + FN_DELIM_FIRST + 
							pathComponents.get(1) + FN_DELIM_SECOND +
							pathComponents.get(2);
		return destFileFullPath;
	}

}