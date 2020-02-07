package lab.sensor.irsensor;

public class SensorNotSupportedException extends Exception {
	private static final long serialVersionUID = 1L;

	public SensorNotSupportedException(String message) {
		super(message);
	}
}
