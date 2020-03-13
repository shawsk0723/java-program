package lab.sensor.irsensor.nironesensorx;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lab.sensor.irsensor.ISensorDataFileParser;
import lab.sensor.irsensor.SensorDataRecords;

public class NironeSensorXDataFileParser implements ISensorDataFileParser {

	private static final int WAVE_LENGTH_ROW_IDX = 1;
	private static final int RAW_DATA_ROW_IDX = 4;
	private static final int DATA_COLUMN_IDX = 2;
	private static final String DELIM = "\t";
	
	@Override
	public void setDataType(String dataType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getWaveLengthList(String sensorDataFilePath) {
		try {
			String waveLenthDataLine = getDataLine(sensorDataFilePath, WAVE_LENGTH_ROW_IDX);
			return extractData(waveLenthDataLine);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	
	@Override
	public SensorDataRecords getSensorDataRecords(String sensorDataFilePath) {
		SensorDataRecords sensorDataRecords = new SensorDataRecords();
		try {
			String rawDataLine = getDataLine(sensorDataFilePath, RAW_DATA_ROW_IDX);
			List<String> record = extractData(rawDataLine);
			sensorDataRecords.write(record);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sensorDataRecords;
	}	
	
	public List<String> getRawDataList(String sensorDataFilePath) {
		return new ArrayList<>();
	}

	private String getDataLine(String sensorDataFilePath, int rowIdx) throws IOException {
		int count = 0;
		String dataLine = "";
		BufferedReader br = new BufferedReader(new FileReader(sensorDataFilePath));
		while ((dataLine = br.readLine()) != null) {
			if(rowIdx == count++) {
				break;
			}
		}
		br.close();
		return dataLine;		
	}

	private List<String> extractData(String dataLine) {
		List<String> dataList = new ArrayList<>();
		String[] dataSplitted = dataLine.split(DELIM); 
		for(int i = DATA_COLUMN_IDX; i < dataSplitted.length; i++) {
			dataList.add(dataSplitted[i]);
		}
		return dataList;
	}

}