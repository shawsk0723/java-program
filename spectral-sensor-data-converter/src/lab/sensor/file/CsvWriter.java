package lab.sensor.file;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
	private File file;
	
	public CsvWriter(String filePath) {
		this.file = new File(filePath);
	}

	public void createAndWriteHeader(List<String> headers) {
		try {
			String line = "";
			for(String header : headers) {
				line = line + header + ",";
			}

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addRow(List<String> rowList) {
		try {
			String line = "";
			for(String row : rowList) {
				line = line + row + ",";
			}

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
			bufferedWriter.append(line);
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}