package lab.sensor.irsensor.as7420;

import java.io.BufferedReader;
import java.io.FileReader;
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
	private LineDataRange lineDataRange;
	
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
			lineDataRange = makeLineDataRange(sensorDataFilePath);
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

	private List<String> getDataListFromFile(String sensorDataFilePath, String keyToFindLine) {
		List<String> rawDataList = new ArrayList<String>();
		try {
			String line = getDataLine(sensorDataFilePath, keyToFindLine);
			String[] lineSplit = line.split(SEMICOLON);
			for(int i = lineDataRange.getOffset(); i < lineDataRange.getOffset() + lineDataRange.getLength(); i++) {
				rawDataList.add(lineSplit[i]);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return rawDataList;
	}

	private List<List<String>> getRecordsFromFile(String sensorDataFilePath, String keyToFindLine) {
		List<List<String>> dataList = new ArrayList<>();
		try {
			List<String> dataLines = getDataLines(sensorDataFilePath, keyToFindLine);
			for(String dataLine : dataLines) {
				String[] lineSplit = dataLine.split(SEMICOLON);
				List<String> rawDataList = new ArrayList<String>();
				for(int i = lineDataRange.getOffset(); i < lineDataRange.getOffset() + lineDataRange.getLength(); i++) {
					rawDataList.add(lineSplit[i]);
				}
				dataList.add(rawDataList);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dataList;
	}

	private LineDataRange makeLineDataRange(String sensorDataFilePath) throws Exception {
		String dataLine = getDataLine(sensorDataFilePath, KEY_DATA_TYPE);
		String[] dataLineSplit = dataLine.split(SEMICOLON);

		//Log.i("dataLine = " + dataLine);
		//Log.i("dataLineSplit = " + dataLineSplit);
		
		lineDataRange = new LineDataRange();
		lineDataRange.setOffset(DataLineAnalyzer.findFirstIndexOfKey(dataLineSplit, dataType));
		lineDataRange.setLength(DataLineAnalyzer.findCountOfKey(dataLineSplit, dataType));
		return lineDataRange;
	}
	
	private String getDataLine(String sensorDataFilePath, String keyToFindLine) throws IOException {
		String dataLine = "";
		BufferedReader br = new BufferedReader(new FileReader(sensorDataFilePath));
		while ((dataLine = br.readLine()) != null) {
			if (dataLine.contains(keyToFindLine)) {
				break;
			}
		}

		br.close();
		return dataLine;
	}

	private List<String> getDataLines(String sensorDataFilePath, String keyToFindLine) throws IOException {
		List<String> dataLines = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(sensorDataFilePath));
		String dataLine = null;
		while ((dataLine = br.readLine()) != null) {
			if (dataLine.contains(keyToFindLine)) {
				dataLines.add(dataLine);
			}
		}

		br.close();
		return dataLines;
	}
	
}