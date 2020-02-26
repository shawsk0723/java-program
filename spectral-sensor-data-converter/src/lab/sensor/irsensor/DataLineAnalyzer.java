package lab.sensor.irsensor;

import lab.sensor.log.Log;

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
			//Log.i("list[" + i + "] =" + list[i]);
			if(list[i].contains(key))  {
				Log.i("list[" + i + "] =" + list[i]);
				count++;
			}
		}
		//Log.i("count = " + count);
		return count;
	}
}
