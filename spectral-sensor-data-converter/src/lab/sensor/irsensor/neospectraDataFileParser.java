package lab.sensor.irsensor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class neospectraDataFileParser implements ISensorDataFileParser {

	private static final String STR_WAVE_LENGTH = "Wavelength";
	private static final String TAB = "\t";
	private static final int COLUMN_IDX_WAVELENGTH = 0;
	private static final int COLUMN_IDX_DATA_REFLECTANCE = 1;
	

	@Override
	public List<String> getWaveLengthList(String sensorDataFilePath) {
		return readRawDataByColumn(sensorDataFilePath, COLUMN_IDX_WAVELENGTH);
	}

	@Override
	public List<String> getRawDataList(String sensorDataFilePath) {
		return readRawDataByColumn(sensorDataFilePath, COLUMN_IDX_DATA_REFLECTANCE);
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

