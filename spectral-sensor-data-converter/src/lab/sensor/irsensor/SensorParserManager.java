package lab.sensor.irsensor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lab.sensor.irsensor.as7420.AS7420DataFileParser;
import lab.sensor.irsensor.as7420.AS7420DataType;
import lab.sensor.irsensor.as7520.AS7520DataFileParser;
import lab.sensor.irsensor.neospectra.NeospectraDataFileParser;

public class SensorParserManager {
	private static final SensorParserManager instance = new SensorParserManager();
	
	private Map<String, SensorParserAttribute> sensorParserMap;
	
	private SensorParserManager() {
		List<String> defaultDataTypeList = Arrays.asList(DataType.SPECTRUM);
		List<String> AS7420DataTypeList = Arrays.asList(AS7420DataType.RAW_DATA, AS7420DataType.SPECTRUM, AS7420DataType.FIRST_DERIVATIVE, AS7420DataType.SECOND_DERIVATIVE);
		
		sensorParserMap = new HashMap<>();
		sensorParserMap.put(SensorList.AS7520, new SensorParserAttribute(new AS7520DataFileParser(), defaultDataTypeList));
		sensorParserMap.put(SensorList.AS7420, new SensorParserAttribute(new AS7420DataFileParser(), AS7420DataTypeList));
		sensorParserMap.put(SensorList.NEO_SPECTRA_MICRO, new SensorParserAttribute(new NeospectraDataFileParser(), defaultDataTypeList));
	}

	public static SensorParserManager getInstance() {
		return instance;
	}

	public List<String> getSupportedSensorList() {
		List<String> supportedSensorList = new ArrayList<>();
		Set<String> set = sensorParserMap.keySet();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			supportedSensorList.add(it.next());
		}
		return supportedSensorList;
	}

	public ISensorDataFileParser getSensorDataFileParser(String sensorName) throws SensorNotSupportedException {
		SensorParserAttribute sensorParserAttribute = sensorParserMap.get(sensorName);
		if(sensorParserAttribute == null) {
			throw new SensorNotSupportedException(sensorName + " is not supported !");
		}
		return sensorParserAttribute.getSensorDataFileParser();
	}
	
	public List<String> getAvailableDataTypeList(String sensorName) throws SensorNotSupportedException {
		SensorParserAttribute sensorParserAttribute = sensorParserMap.get(sensorName);
		if(sensorParserAttribute == null) {
			throw new SensorNotSupportedException(sensorName + " is not supported !");
		}
		return sensorParserAttribute.getAvailableDataTypeList(); 
	}
}
