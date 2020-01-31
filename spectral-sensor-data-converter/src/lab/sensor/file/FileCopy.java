package lab.sensor.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	
	public static void copy(String srcFileFullPath, String destFileFullPath) throws Exception {

		try {
			FileInputStream fis = new FileInputStream(new File(srcFileFullPath));
			FileOutputStream fos = new FileOutputStream(new File(destFileFullPath));
			
			int fileByte = 0;
			while((fileByte = fis.read()) != -1) {
				fos.write(fileByte);
			}
			fis.close();
			fos.close();

		} catch(FileNotFoundException e) {
			throw e;
		} catch(IOException e) {
			throw e;
		}

	}
}