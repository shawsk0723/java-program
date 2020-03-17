package lab.sensor.irsensor.as7420;

public class DataLineAnalyzer {
	public static int findFirstIndexOfKey(String[] list, String key) {
		int index = 0;
		for(int i = 0; i < list.length; i++) {
			if(list[i].contains(key)) {
				index = i;
				break;
			}
		}
		return index;
	}
	
	public static int findCountOfKey(String[] list, String key) {
		int count = 0;
		for(int i = 0; i < list. length; i++) {
			if(list[i].contains(key))  {
				count++;
			}
		}
		return count;
	}
}
