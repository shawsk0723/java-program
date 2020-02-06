package lab.sensor.irsensor;

import java.util.List;

public interface ISensorDataFileParser {
	List<String> getWaveLengthList(String sensorDataFilePath);
	List<String> getRawDataList(String sensorDataFilePath);
}
