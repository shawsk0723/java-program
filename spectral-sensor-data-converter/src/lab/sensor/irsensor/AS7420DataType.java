package lab.sensor.irsensor;

public enum AS7420DataType {
	RAW_DATA("RawData"),
	SPECTRUM("Spectrum"),
	FIRST_DERIVATIVE("1.Derivative"),
	SECOND_DERIVATIVE("2.Derivative");

	private String name;

	AS7420DataType(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}