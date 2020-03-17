package lab.sensor.irsensor.as7420;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataLineExtractor {

	public static String getDataLine(String sensorDataFilePath, String keyToFindLine) throws IOException {
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

	public static List<String> getDataLines(String sensorDataFilePath, String keyToFindLine) throws IOException {
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
