package lab.sensor.irsensor;

import java.util.ArrayList;
import java.util.List;

public class SensorDataRecords {
	private List<List<String>> records;
	public SensorDataRecords() {
		records = new ArrayList<>();
	}

	public int size() {
		return records.size();
	}

	public void write(List<String> record) {
		records.add(record);
	}

	public List<String> read(int index) {
		return records.get(index);
	}
}