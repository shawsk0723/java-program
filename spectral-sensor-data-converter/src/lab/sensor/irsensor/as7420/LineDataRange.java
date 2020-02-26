package lab.sensor.irsensor.as7420;

public class LineDataRange {
	private int offset = 0;
	private int length = 0;

	public LineDataRange() {

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
