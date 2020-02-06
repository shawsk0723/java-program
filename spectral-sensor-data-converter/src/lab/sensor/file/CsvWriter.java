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

	public void createAndWriteHeader(List<String> headers) throws Exception {
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
			throw e;
		}
	}
	
	public void addRow(List<String> rowList) throws Exception {
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
			throw e;
		}
	}

}