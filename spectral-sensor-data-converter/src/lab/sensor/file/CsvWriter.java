package lab.sensor.file;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
	private static final String COMA = ",";
	private File file;
		
	public CsvWriter(String filePath) {
		this.file = new File(filePath);
	}

	public void createAndWriteHeader(List<String> headers) throws Exception {
		try {
			StringBuilder line = new StringBuilder();
			for(String header : headers) {
				line.append(header);
				line.append(COMA);
			}

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write(line.toString());
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			throw e;
		}
	}
	
	public void addRow(List<String> rowList) throws Exception {
		try {
			StringBuilder line = new StringBuilder();
			for(String row : rowList) {
				line.append(row);
				line.append(COMA);
			}

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
			bufferedWriter.append(line.toString());
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			throw e;
		}
	}

}