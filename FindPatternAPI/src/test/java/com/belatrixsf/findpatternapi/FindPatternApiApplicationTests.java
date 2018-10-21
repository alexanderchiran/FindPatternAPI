package com.belatrixsf.findpatternapi;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @author Alexander Chiran
 * paulo.alexander12@gmail.com
 * 
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FindPatternApiApplicationTests {

	@Test
	public void contextLoads() {
	}

	public static void main(String[] args) throws MalformedURLException {
		try {
			String URL1 = "https://www.yahoo.com";

			URL u = new URL(URL1);
			HttpURLConnection huc = (HttpURLConnection) u.openConnection();
			huc.setRequestMethod("GET");
			huc.connect();
			// huc.getResponseCode();
			HttpURLConnection.setFollowRedirects(true);

			System.out.println("Resultado");
			System.out.println(huc.getResponseCode());
			huc.setInstanceFollowRedirects(true);
			System.out.println(huc.getResponseCode());
			System.out.println("final");
			// System.out.println(getResponseCode(URL + "Heroes_novel_190.pdf"));
			// System.out.println(getResponseCode("http://www.example.com"));
			// System.out.println(getResponseCode("http://www.example.com/junk"));

			/*
			 * URL relativeURL, baseURL; baseURL = new URL("http://www.yahoo.com");
			 * relativeURL = new URL(baseURL, "./");
			 * System.out.println(relativeURL.toExternalForm());
			 */
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		/*
		 * FindPatternRestController findPatternRestController = new
		 * FindPatternRestController(); ClientPattern clientPattern = new
		 * ClientPattern(); clientPattern.setRegexr("222222");
		 * findPatternRestController.processurl(clientPattern);
		 */
		/*
		 * String patron =
		 * "var1[value1], var2[value2], var3[value3] #mividavale ewrewrqewwrecr  wef w wefwerer werwer \n\n asdasdsada"
		 * ; //Pattern pattern = Pattern.compile("(\\[)(.*?)(\\])"); Pattern pattern =
		 * Pattern.compile("(?<=[\\s>])#(\\d*[A-Za-z_]+\\d*)\\b(?!;)",
		 * Pattern.MULTILINE); Matcher matcher = pattern.matcher(patron);
		 * 
		 * List<String> listMatches = new ArrayList<String>();
		 * 
		 * while (matcher.find()) { listMatches.add(matcher.group()); }
		 * System.out.println("Inicia"); for (String s : listMatches) {
		 * System.out.println(s); }
		 */

	}

}
