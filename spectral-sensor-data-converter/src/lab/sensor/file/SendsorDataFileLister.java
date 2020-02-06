package lab.sensor.file;

import java.util.ArrayList;
import java.util.List;

import lab.sensor.log.Log;

public class SendsorDataFileLister {
	private static String TAG = SendsorDataFileLister.class.getSimpleName();

	public static List<String> getSensorDataFileList(String srcRootPath) {
		List<String> totalSensorDataFileList = new ArrayList<String>();

		try {
			List<String> fabricPathList = ChildFileLister.getChildFileList(srcRootPath);
	
			for(String fabricPath : fabricPathList) {
				List<String> subFabricPathList = ChildFileLister.getChildFileList(fabricPath);
				for(String subFabricPath : subFabricPathList) {
					//Log.d(TAG, "sub fabric path = " + subFabricPath);
					List<String> sensorDataFileList = ChildFileLister.getChildFileList(subFabricPath);
					for(String sensorDataFile : sensorDataFileList) {
						Log.d(TAG, "src file = " + sensorDataFile);
						totalSensorDataFileList.add(sensorDataFile);
					}
				}
			}
			
		} catch(Exception e) {
			Log.e(TAG, e);
		}
		return totalSensorDataFileList;
	}

	


}