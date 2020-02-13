package lab.sensor.irsensor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class AS7420DataFileParser implements ISensorDataFileParser {
	
	private static final String STR_WAVE_LENGTH = "Wavelength";
	private static final String STR_MEASURE_DATA = "Measure data";
	private static final String SEMICOLUMN = ";";
	private static final int COLUMN_IDX_WAVELENGTH = 0;
	private static final int COLUMN_IDX_DATA_REFLECTANCE = 1;
	private static final int DATA_END_COUNT=125;
	private static final int DATA_START_COUNT=56;
	int count = 0;
	

	@Override
	public List<String> getWaveLengthList(String sensorDataFilePath) {
		return readRawDataByraw(sensorDataFilePath, COLUMN_IDX_WAVELENGTH);
	}

	@Override
	public List<String> getRawDataList(String sensorDataFilePath) {
		return readRawDataByraw(sensorDataFilePath, COLUMN_IDX_DATA_REFLECTANCE);
	}

	private List<String> readRawDataByraw(String sensorDataFilePath, int columnIndex) {
		List<String> rawDataList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(sensorDataFilePath));
			
		
			String line = null;
			if(columnIndex==COLUMN_IDX_WAVELENGTH)
			{
			while((line = br.readLine()) != null) {
				if(line.contains(STR_WAVE_LENGTH)) {
					
					String[] lineSplit1 = line.split(SEMICOLUMN);
					while(lineSplit1 != null) {
						lineSplit1 = line.split(SEMICOLUMN);
						if(count > DATA_START_COUNT && count < DATA_END_COUNT)
						{
						    System.out.printf("%s\n",lineSplit1[count]);
						    rawDataList.add(lineSplit1[count]);
						}
						count++;
						
						if (count>DATA_END_COUNT)
						{
						    count=0;	
							break;
						}
						
					}
					
					
					break;
				}
			}
			}
			else
			{		
				
				while((line = br.readLine()) != null) {
					
					if(line.contains(STR_MEASURE_DATA)) {
						System.out.println("hello world");	
						if( count == 0)
						{
							line = br.readLine();
							count++;
							continue;
						}
						String[] lineSplit2 = line.split(SEMICOLUMN);
						//count=0;
						while(lineSplit2 != null) {
							lineSplit2 = line.split(SEMICOLUMN);
							if(count > DATA_START_COUNT && count < DATA_END_COUNT)
							{
							    System.out.printf("%s\n",lineSplit2[count]);
							    rawDataList.add(lineSplit2[count]);
							}
							count++;
							
							if (count>DATA_END_COUNT)
							{
							    count=0;	
								break;
							}
							
						}
						
						break;
					}
				}
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return rawDataList;		
	}

	

}




