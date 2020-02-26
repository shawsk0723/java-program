package lab.sensor.irsensor;

import java.util.List;

public class SensorParserAttribute {
	private ISensorDataFileParser sensorDataFileParser;
	private List<String> availableDataTypeList;
	
	public SensorParserAttribute(ISensorDataFileParser sensorDataFileParser, List<String> availableDataTypeList) {
		this.sensorDataFileParser = sensorDataFileParser;
		this.availableDataTypeList = availableDataTypeList;
	}

	public ISensorDataFileParser getSensorDataFileParser() {
		return sensorDataFileParser;
	}

	public List<String> getAvailableDataTypeList() {
		return availableDataTypeList;
	}
}
