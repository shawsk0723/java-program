package lab.sensor.app;

import java.util.ArrayList;
import java.util.List;

import lab.sensor.file.CsvWriter;
import lab.sensor.file.FileIOConst;
import lab.sensor.file.FilePathSeparator;
import lab.sensor.file.SendsorDataFileLister;
import lab.sensor.irsensor.ISensorDataFileParser;
import lab.sensor.irsensor.SensorParserMap;
import lab.sensor.log.Log;

public class SensorDataToCsvConverter {
	private static final String TAG = SensorDataToCsvConverter.class.getSimpleName();

	private static final String FIRST_HEADER = "Fabric";
	private static final String SECOND_HEADER = "Sample";
	private static final String THIRD_HEADER = "Iteration";
	
	private static final String STR_ABSORBANCE = "Absorbance";
	private static final String CSV_EXT = ".csv";
	private static final String UNDER_BAR = "_";
	
	private SensorDataInfo sensorDataInfo;
	
	public SensorDataToCsvConverter(SensorDataInfo sensorDataInfo) {
		this.sensorDataInfo = sensorDataInfo;
	}

	public void execute() {

		try {
			Log.i(TAG, "sensor data convert start ~");

			List<String> sensorDataFileList = SendsorDataFileLister.getSensorDataFileList(sensorDataInfo.getSrcRootPath());
	
			ISensorDataFileParser sensorDataParser = SensorParserMap.getInstance().getSensorDataFileParser(sensorDataInfo.getSensorName());
			List<String>waveLengthList = sensorDataParser.getWaveLengthList(sensorDataFileList.get(0));
			
			String csvFileName = STR_ABSORBANCE + UNDER_BAR + sensorDataInfo.getSensorName() + CSV_EXT;
			CsvWriter csvWriter = new CsvWriter(sensorDataInfo.getDestRootPath() + FileIOConst.FILE_PATH_SEPARATOR + csvFileName); 
			csvWriter.createAndWriteHeader(makeCsvHeaderRow(waveLengthList));

			for(String sensorDataFile : sensorDataFileList) {
				csvWriter.addRow(makeCsvDataRow(sensorDataFile, sensorDataParser));
			}

			Log.i(TAG, "sensor data convert success ~");
		} catch(Exception e) {
			e.printStackTrace();
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

	private List<String> makeCsvDataRow(String sensorDataFile, ISensorDataFileParser sensorDataParser) {
		List<String> dataRow = new ArrayList<>();
		String validPath = sensorDataFile.replace(sensorDataInfo.getSrcRootPath() + FileIOConst.FILE_PATH_SEPARATOR, "");
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