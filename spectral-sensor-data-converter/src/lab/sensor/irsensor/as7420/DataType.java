package lab.sensor.irsensor.as7420;

public enum DataType {
	RAW_DATA("RawData"),
	SPECTRUM("Spectrum"),
	FIRST_DERIVATIVE("1.Derivative"),
	SECOND_DERIVATIVE("2.Derivative");

	private String name;

	DataType(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}