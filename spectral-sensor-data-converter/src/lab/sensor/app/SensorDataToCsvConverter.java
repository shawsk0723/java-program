package lab.sensor.app;

import java.util.ArrayList;
import java.util.List;

import lab.sensor.file.CsvWriter;
import lab.sensor.file.FileIOConst;
import lab.sensor.file.FilePathSeparator;
import lab.sensor.file.SendsorDataFileLister;
import lab.sensor.irsensor.ISensorDataFileParser;
import lab.sensor.irsensor.SensorDataRecords;
import lab.sensor.irsensor.SensorParserManager;
import lab.sensor.log.Log;

public class SensorDataToCsvConverter {
	private static final String TAG = SensorDataToCsvConverter.class.getSimpleName();

	private static final String FIRST_HEADER = "Fabric";
	private static final String SECOND_HEADER = "Sample";
	private static final String THIRD_HEADER = "Iteration";
	
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
	
			String sensorName = sensorDataInfo.getSensorName();
			SensorParserManager sensorParserManager = SensorParserManager.getInstance();
			ISensorDataFileParser sensorDataParser = sensorParserManager.getSensorDataFileParser(sensorName);
			List<String> availableDataTypeList = sensorParserManager.getAvailableDataTypeList(sensorName);

			for(String dataType : availableDataTypeList) {
				sensorDataParser.setDataType(dataType);

				List<String>waveLengthList = sensorDataParser.getWaveLengthList(sensorDataFileList.get(0));
				
				String csvFileName = sensorName + UNDER_BAR + dataType + CSV_EXT;
				CsvWriter csvWriter = new CsvWriter(sensorDataInfo.getDestRootPath() + FileIOConst.FILE_PATH_SEPARATOR + csvFileName); 
				writeCsvHeaderRow(csvWriter, waveLengthList);
	
				for(String sensorDataFile : sensorDataFileList) {
					writeCsvDataRow(csvWriter, sensorDataParser, sensorDataFile);
				}
			}

			Log.i(TAG, "sensor data convert success ~");
		} catch(Exception e) {
			e.printStackTrace();
			Log.i(TAG, "sensor data convert fail ~");
		}
	}

	private void writeCsvHeaderRow(CsvWriter csvWriter, List<String> waveLengthList) throws Exception {
		List<String> headerRow = new ArrayList<>();
		headerRow.add(FIRST_HEADER);
		headerRow.add(SECOND_HEADER);
		headerRow.add(THIRD_HEADER);
		headerRow.addAll(waveLengthList);
		csvWriter.createAndWriteHeader(headerRow);
	}

	private void writeCsvDataRow(CsvWriter csvWriter, ISensorDataFileParser sensorDataParser, String sensorDataFile) throws Exception {
		List<String> dataRow = new ArrayList<>();
		String validPath = sensorDataFile.replace(sensorDataInfo.getSrcRootPath() + FileIOConst.FILE_PATH_SEPARATOR, "");
		List<String> pathComponents = FilePathSeparator.splitFilePath(validPath);

		String fabric = pathComponents.get(0);
		String sample = pathComponents.get(1);
		String iteration = pathComponents.get(2).split("\\.(?=[^\\.]+$)")[0];

		dataRow.add(fabric);
		dataRow.add(sample);
		dataRow.add(iteration);
		
		SensorDataRecords sensorDataRecords = sensorDataParser.getSensorDataRecords(sensorDataFile);
		for(int i = 0; i < sensorDataRecords.size(); i++) {

			dataRow.addAll(sensorDataRecords.read(i));
			csvWriter.addRow(dataRow);
			
			dataRow.subList(3, dataRow.size()).clear();
		}
	}
}