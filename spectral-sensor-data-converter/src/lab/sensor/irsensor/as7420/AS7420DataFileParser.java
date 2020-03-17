package lab.sensor.irsensor.as7420;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lab.sensor.irsensor.ISensorDataFileParser;
import lab.sensor.irsensor.SensorDataRecords;

public class AS7420DataFileParser implements ISensorDataFileParser {
 
	private static final String KEY_DATA_TYPE = "DataType";
	private static final String KEY_WAVE_LENGTH = "Wavelength";
	private static final String KEY_MEASURE_DATA = "Measure data;";
	private static final String SEMICOLON = ";";

	private String dataType;
	private DataLineFormat dataLineFormat;
	
	public AS7420DataFileParser() {
		this.dataType = AS7420DataType.RAW_DATA;
	}
	
	@Override
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	@Override
	public List<String> getWaveLengthList(String sensorDataFilePath) {
		List<String> waveLengthList = new ArrayList<String>();
		try {
			dataLineFormat = makeLineDataRange(sensorDataFilePath);
			//Log.i("lineDataRange = " + lineDataRange);
			waveLengthList =  getDataListFromFile(sensorDataFilePath, KEY_WAVE_LENGTH);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return waveLengthList;
	}

	@Override
	public SensorDataRecords getSensorDataRecords(String sensorDataFilePath) {
		List<List<String>> records = getRecordsFromFile(sensorDataFilePath, KEY_MEASURE_DATA);
		SensorDataRecords sensorDataRecord = new SensorDataRecords();
		for(List<String> record : records) {
			sensorDataRecord.write(record);
		}
		return sensorDataRecord;
	}

	private DataLineFormat makeLineDataRange(String sensorDataFilePath) throws Exception {
		String dataLine = DataLineExtractor.getDataLine(sensorDataFilePath, KEY_DATA_TYPE);
		String[] dataLineSplit = dataLine.split(SEMICOLON);

		dataLineFormat = new DataLineFormat();
		dataLineFormat.setOffset(DataLineAnalyzer.findFirstIndexOfKey(dataLineSplit, dataType));
		dataLineFormat.setLength(DataLineAnalyzer.findCountOfKey(dataLineSplit, dataType));
		return dataLineFormat;
	}
	
	private List<String> getDataListFromFile(String sensorDataFilePath, String keyToFindLine) {
		try {
			String line = DataLineExtractor.getDataLine(sensorDataFilePath, keyToFindLine);
			return extractData(dataLineFormat, line);
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
	}

	private List<List<String>> getRecordsFromFile(String sensorDataFilePath, String keyToFindLine) {

		try {
			List<List<String>> dataList = new ArrayList<>();
			List<String> dataLines = DataLineExtractor.getDataLines(sensorDataFilePath, keyToFindLine);
			for(String dataLine : dataLines) {
				dataList.add(extractData(dataLineFormat, dataLine));
			}
			return dataList;
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<List<String>>();
		}
	}

	private List<String> extractData(DataLineFormat dataLineFormat, String dataLine) {
		String[] lineSplit = dataLine.split(SEMICOLON);
		List<String> rawDataList = new ArrayList<String>();
		for(int i = dataLineFormat.getOffset(); i < dataLineFormat.getOffset() + dataLineFormat.getLength(); i++) {
			rawDataList.add(lineSplit[i]);
		}
		return rawDataList;
	}

}