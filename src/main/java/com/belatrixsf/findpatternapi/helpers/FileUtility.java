package com.belatrixsf.findpatternapi.helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Alexander Chiran
 * paulo.alexander12@gmail.com
 * 
 *
 */
public class FileUtility {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 
	 * @param data
	 * @param name
	 */
	public void createOutPutFile(String data, String name) {
		OutputStream os = null;
		try {
			if (data != null && name != null) {
				String path = "C:/files/out/" + name + ".txt";
				File file = new File(path);
				if (!file.exists()) {
					file.createNewFile();
				}
				FileOutputStream writer = new FileOutputStream(path);
				writer.write((data).getBytes());
				writer.close();

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				logger.error(e.toString());
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String nameFile() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss");
			Date fecha = new Date();
			return sdf.format(fecha);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return "";

	}
	
	/**
	 * reading the file and you get a list of urls
	 * 
	 * @return
	 */
	public List<String> readFile() {

		List<String> listURL = new ArrayList<String>();
		File file = new File("C:/files/url.txt");
		BufferedReader reader = null;

		try {
			Transformation transformation = new Transformation();
			reader = new BufferedReader(new FileReader(file));
			String text = null;

			while ((text = reader.readLine()) != null) {
				text = transformation.changeURLHttps(text);
				listURL.add(text);
			}
			return listURL;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			logger.error(e.toString());

		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}

		}
		return null;
	}

}
