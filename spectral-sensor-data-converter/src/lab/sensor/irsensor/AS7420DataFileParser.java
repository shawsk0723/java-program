package lab.sensor.irsensor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AS7420DataFileParser implements ISensorDataFileParser {

	private static final String STR_WAVE_LENGTH = "Wavelength [nm];;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;";
	private static final String STR_MEASURE_DATA = "Measure data;";
	private static final String SEMICOLUMN = ";";
	private static final String WAVELENGTH_PARSING = ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;";
	private static final String RAWDATA_PARSING = ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;";
	private static final String DATA_END_POINT = ";;";

	private static final int COLUMN_IDX_WAVELENGTH = 0;
	private static final int COLUMN_IDX_DATA_REFLECTANCE = 1;

	@Override
	public List<String> getWaveLengthList(String sensorDataFilePath) {
		return readRawDataByraw(sensorDataFilePath, COLUMN_IDX_WAVELENGTH, STR_WAVE_LENGTH, WAVELENGTH_PARSING);
	}

	@Override
	public List<String> getRawDataList(String sensorDataFilePath) {
		return readRawDataByraw(sensorDataFilePath, COLUMN_IDX_DATA_REFLECTANCE, STR_MEASURE_DATA, RAWDATA_PARSING);
	}

	private List<String> readRawDataByraw(String sensorDataFilePath, int columnIndex, String Parse_data1,
			String Parse_data2) {
		List<String> rawDataList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(sensorDataFilePath));

			String line = null;

			while ((line = br.readLine()) != null) {
				if (line.contains(Parse_data1)) {
					String[] lineSplit = line.split(Parse_data2);
					lineSplit = lineSplit[1].split(DATA_END_POINT);
					lineSplit = lineSplit[0].split(SEMICOLUMN);
					rawDataList = Arrays.asList(lineSplit);
					System.out.println(rawDataList);
					break;
				}
			}

			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rawDataList;
	}

}
