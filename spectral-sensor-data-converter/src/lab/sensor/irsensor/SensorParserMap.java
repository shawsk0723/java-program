package lab.sensor.irsensor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SensorParserMap {
	private static final SensorParserMap instance = new SensorParserMap();
	
	private Map<String, ISensorDataFileParser> supportedSensorParserMap;
	
	private SensorParserMap() {
		supportedSensorParserMap = new HashMap<>();
		supportedSensorParserMap.put(SensorList.AS7520, new AS7520DataFileParser());
		supportedSensorParserMap.put(SensorList.AS7420, new AS7420DataFileParser());
		supportedSensorParserMap.put(SensorList.NEO_SPECTRA_MICRO, new neospectraDataFileParser());
	}

	public static SensorParserMap getInstance() {
		return instance;
	}

	public List<String> getSupportedSensorList() {
		List<String> supportedSensorList = new ArrayList<>();
		Set<String> set = supportedSensorParserMap.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			supportedSensorList.add(it.next());
		}
		return supportedSensorList;
	}

	public ISensorDataFileParser getSensorDataFileParser(String sensorName) throws SensorNotSupportedException {
		ISensorDataFileParser sensorDataFileParser = supportedSensorParserMap.get(sensorName);
		if(sensorDataFileParser == null) {
			throw new SensorNotSupportedException("");
		}
		return sensorDataFileParser;
	}
}
