package lab.sensor.irsensor.neospectra;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lab.sensor.irsensor.ISensorDataFileParser;
import lab.sensor.irsensor.SensorDataRecords;


public class NeospectraDataFileParser implements ISensorDataFileParser {

	private static final String STR_WAVE_LENGTH = "Wavelength";
	private static final String TAB = "\t";
	private static final int COLUMN_IDX_WAVELENGTH = 0;
	private static final int COLUMN_IDX_DATA_REFLECTANCE = 1;
	private static final String DOT = "\\."; 

	@Override
	public void setDataType(String dataType) {

	}
	
	@Override
	public List<String> getWaveLengthList(String sensorDataFilePath) {
		List<String> record = readRawDataByColumn(sensorDataFilePath, COLUMN_IDX_WAVELENGTH);
		Collections.reverse(record);
		return record;
	}

	@Override
	public SensorDataRecords getSensorDataRecords(String sensorDataFilePath) {
		SensorDataRecords sensorDataRecords = new SensorDataRecords();
		List<String> record = readRawDataByColumn(sensorDataFilePath, COLUMN_IDX_DATA_REFLECTANCE);
		Collections.reverse(record);
		sensorDataRecords.write(record);
		return sensorDataRecords;
	}

	private List<String> readRawDataByColumn(String sensorDataFilePath, int columnIndex) {
		List<String> rawDataList = new ArrayList<String>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(sensorDataFilePath));
			String line = null;
			while((line = br.readLine()) != null) {
				if(line.contains(STR_WAVE_LENGTH)) {
					break;
				}
			}
			while((line = br.readLine()) != null) {
				String[] lineSplit = line.split(TAB);
				String data = columnIndex==COLUMN_IDX_WAVELENGTH ? 
						lineSplit[columnIndex].split(DOT)[0] : lineSplit[columnIndex]; 
				rawDataList.add(data);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return rawDataList;		
	}
	
}
