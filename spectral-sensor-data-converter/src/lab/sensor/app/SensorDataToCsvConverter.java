package lab.sensor.app;

import java.util.ArrayList;
import java.util.List;

import lab.sensor.file.CsvWriter;
import lab.sensor.file.FileIOConst;
import lab.sensor.file.FilePathSeparator;
import lab.sensor.file.SendsorDataFileLister;
import lab.sensor.irsensor.AS7520DataFileParser;
import lab.sensor.irsensor.SensorDataFileParser;
import lab.sensor.log.Log;

public class SensorDataToCsvConverter {
	private static final String TAG = SensorDataToCsvConverter.class.getSimpleName();

	private static final String FIRST_HEADER = "Num_Data";
	private static final String SECOND_HEADER = "sample name";
	private static final String THIRD_HEADER = "iteration";
	
	private static final String CSV_FILE_NAME = "Absorbance.csv";
	
	private String srcRootPath;
	private String destRootPath;
	
	public SensorDataToCsvConverter(String srcRootPath, String destRootPath) {
		this.srcRootPath = srcRootPath;
		this.destRootPath= destRootPath;
	}

	public void execute() {

		try {
			Log.i(TAG, "sensor data convert start ~");

			List<String> sensorDataFileList = SendsorDataFileLister.getSensorDataFileList(srcRootPath);
	
			SensorDataFileParser sensorDataParser = new AS7520DataFileParser();
			List<String>waveLengthList = sensorDataParser.getWaveLengthList(sensorDataFileList.get(0));
			
			CsvWriter csvWriter = new CsvWriter(destRootPath + FileIOConst.FILE_PATH_SEPARATOR + CSV_FILE_NAME); 
			csvWriter.createAndWriteHeader(makeCsvHeaderRow(waveLengthList));

			for(String sensorDataFile : sensorDataFileList) {
				csvWriter.addRow(makeCsvDataRow(sensorDataFile, sensorDataParser));
			}

			Log.i(TAG, "sensor data convert success ~");
		} catch(Exception e) {
			Log.e(TAG, e);
			Log.i(TAG, "sensor data convert fail ~");
		}
	}

	private List<String> makeCsvHeaderRow(List<String> waveLengthList) {
		List<String> headerRow = new ArrayList<>();
		headerRow.add(FIRST_HEADER);
		headerRow.add(SECOND_HEADER);
		headerRow.add(THIRD_HEADER);
		headerRow.addAll(waveLengthList);
		return headerRow;
	}

	private List<String> makeCsvDataRow(String sensorDataFile, SensorDataFileParser sensorDataParser) {
		List<String> dataRow = new ArrayList<>();
		String validPath = sensorDataFile.replace(srcRootPath + FileIOConst.FILE_PATH_SEPARATOR, "");
		List<String> pathComponents = FilePathSeparator.splitFilePath(validPath);
		for(int i = 0; i < pathComponents.size(); i++) {
			if(i == 2) {
				dataRow.add(pathComponents.get(i).split("\\.(?=[^\\.]+$)")[0]);
			} else {
				dataRow.add(pathComponents.get(i));
			}

		}
		
		List<String> rawDataList = sensorDataParser.getRawDataList(sensorDataFile);
		dataRow.addAll(rawDataList);
		return dataRow;
	}
}