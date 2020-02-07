package lab.sensor.app;

public class SensorDataInfo {
	private String sensorName;
	private String srcRootPath;
	private String destRootPath;

	public SensorDataInfo sensorName(String sensorName) {
		this.sensorName = sensorName;
		return this;
	}
	
	public SensorDataInfo srcRootPath(String srcRootPath) {
		this.srcRootPath = srcRootPath;
		return this;
	}

	public SensorDataInfo destRootPath(String destRootPath) {
		this.destRootPath = destRootPath;
		return this;
	}

	public String getSensorName() {
		return sensorName;
	}
	
	public String getSrcRootPath() {
		return srcRootPath;
	}

	public String getDestRootPath() {
		return destRootPath;
	}
}