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
		return readRawDataByraw(sensorDataFilePath, COLUMN_IDX_WAVELENGTH);
	}

	@Override
	public List<String> getRawDataList(String sensorDataFilePath) {
		return readRawDataByraw(sensorDataFilePath, COLUMN_IDX_DATA_REFLECTANCE);
	}

	private List<String> readRawDataByraw(String sensorDataFilePath, int columnIndex) {
		List<String> rawDataList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(sensorDataFilePath));

			String line = null;

			if (columnIndex == COLUMN_IDX_WAVELENGTH) {

				while ((line = br.readLine()) != null) {
					if (line.contains(STR_WAVE_LENGTH)) {
						String[] lineSplit1 = line.split(WAVELENGTH_PARSING);
						lineSplit1 = lineSplit1[1].split(DATA_END_POINT);
						lineSplit1 = lineSplit1[0].split(SEMICOLUMN);
						rawDataList = Arrays.asList(lineSplit1);
						//System.out.println(rawDataList);
						break;
					}
				}

			} else {

				while ((line = br.readLine()) != null) {

					if (line.contains(STR_MEASURE_DATA)) {

						String[] lineSplit2 = line.split(RAWDATA_PARSING);
						lineSplit2 = lineSplit2[1].split(DATA_END_POINT);
						lineSplit2 = lineSplit2[0].split(SEMICOLUMN);
						rawDataList = Arrays.asList(lineSplit2);
						//System.out.println(rawDataList);

						break;
					}
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
