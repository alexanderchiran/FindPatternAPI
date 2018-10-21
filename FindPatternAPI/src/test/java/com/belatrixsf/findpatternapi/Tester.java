package com.belatrixsf.findpatternapi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 * @author Alexander Chiran
 * paulo.alexander12@gmail.com
 * 
 *
 */
public class Tester {

	public static void main(String[] args) throws Exception{
		//URL hh= new URL("http://www.cnn.com");
		URL hh= new URL("http://www.yahoo.com");
		
		URLConnection connection = hh.openConnection();
		String redirect = connection.getHeaderField("Location");
		if (redirect != null){
		    connection = new URL(redirect).openConnection();
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		
		String inputLine;
		System.out.println();
		while ((inputLine = in.readLine()) != null) {
		    System.out.println(inputLine);
		}  
	}
}
