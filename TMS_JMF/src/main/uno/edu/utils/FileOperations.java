package main.uno.edu.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileOperations {

	public static BufferedReader getUTFReader(File file) {

		FileInputStream fstream;
		try {
			FileInputStream fis = new FileInputStream(file);

			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			return br;

		} catch (Exception e) {
			return null;
		}
	}

	public static String getFileContent(File file) {
		String strLine;
		StringBuilder sb = null;
		try {
			sb = new StringBuilder();
			BufferedReader br = getUTFReader(file);
			while ((strLine = br.readLine()) != null) {
				sb.append(strLine + "\n\r");
			}
		} catch (Exception e) {
			sb = null;
		}
		return sb.toString();
	}

}
