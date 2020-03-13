package lab.sensor.irsensor;

import java.util.List;

public interface ISensorDataFileParser {
	void setDataType(String dataType);
	List<String> getWaveLengthList(String sensorDataFilePath);
	SensorDataRecords getSensorDataRecords(String sensorDataFilePath);
}
