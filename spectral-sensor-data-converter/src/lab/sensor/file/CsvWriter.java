package lab.sensor.file;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.StringJoiner;

public class CsvWriter {
	private static final String COMA = ",";
	private File file;
		
	public CsvWriter(String filePath) {
		this.file = new File(filePath);
	}

	public void createAndWriteHeader(List<String> headers) throws Exception {
		try {
			StringJoiner line = new StringJoiner(COMA);
			for(String header : headers) {
				line.add(header);
			}

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write(line.toString());
			bufferedWriter.close();
		} catch (IOException e) {
			throw e;
		}
	}
	
	public void addRow(List<String> rowList) throws Exception {
		try {
			StringJoiner line = new StringJoiner(COMA);
			for(String row : rowList) {
				line.add(row);
			}

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
			bufferedWriter.newLine();
			bufferedWriter.append(line.toString());
			bufferedWriter.close();
		} catch (IOException e) {
			throw e;
		}
	}

}