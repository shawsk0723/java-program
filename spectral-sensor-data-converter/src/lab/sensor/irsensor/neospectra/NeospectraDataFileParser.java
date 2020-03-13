package lab.sensor.irsensor.neospectra;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lab.sensor.irsensor.ISensorDataFileParser;
import lab.sensor.irsensor.SensorDataRecords;


public class NeospectraDataFileParser implements ISensorDataFileParser {

	private static final String STR_WAVE_LENGTH = "Wavelength";
	private static final String TAB = "\t";
	private static final int COLUMN_IDX_WAVELENGTH = 0;
	private static final int COLUMN_IDX_DATA_REFLECTANCE = 1;
	

	@Override
	public void setDataType(String dataType) {

	}
	
	@Override
	public List<String> getWaveLengthList(String sensorDataFilePath) {
		return readRawDataByColumn(sensorDataFilePath, COLUMN_IDX_WAVELENGTH);
	}

	@Override
	public SensorDataRecords getSensorDataRecords(String sensorDataFilePath) {
		SensorDataRecords sensorDataRecords = new SensorDataRecords();
		List<String> record = readRawDataByColumn(sensorDataFilePath, COLUMN_IDX_DATA_REFLECTANCE);
		sensorDataRecords.write(record);
		return sensorDataRecords;
	}

	private List<String> readRawDataByColumn(String sensorDataFilePath, int columnIndex) {
		List<String> rawDataList = new ArrayList<String>();
		double data=0;
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
				
				if(columnIndex==COLUMN_IDX_WAVELENGTH)
				{
					data = Double.parseDouble(lineSplit[columnIndex]);
					data = Math.floor(data);
					//System.out.println(data);
					lineSplit[columnIndex]=Integer.toString((int)data);
					//System.out.println(lineSplit[columnIndex]);					
				}
				rawDataList.add(lineSplit[columnIndex]);
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return rawDataList;		
	}
	
}
