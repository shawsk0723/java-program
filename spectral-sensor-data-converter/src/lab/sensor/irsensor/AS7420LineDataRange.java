package lab.sensor.irsensor;

public class AS7420LineDataRange {
	private int offset = 0;
	private int length = 0;

	public AS7420LineDataRange() {

	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public void setLength(int length) {
		this.length = length;
	}
	
	public int getOffset() {
		return offset;
	}

	public int getLength() {
		return length;
	}

	public String toString() {
		return "offset = " + offset + ", length = " + length;
	}
}
