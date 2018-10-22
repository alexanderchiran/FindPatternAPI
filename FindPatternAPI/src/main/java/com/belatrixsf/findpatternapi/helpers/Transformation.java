package com.belatrixsf.findpatternapi.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Alexander Chiran
 * paulo.alexander12@gmail.com
 * 
 * 
 *
 */

public class Transformation {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * change protocol
	 * @param listURL
	 * @return
	 */

	public String changeURLHttps(String url) {
		try {
			if (url != null) {
				url = url.replace("http://", "https://");
			}

		} catch (Exception e) {
			logger.error(e.toString());
		}
		return url;

	}
	
	/**
	 * change protocol
	 * @param url
	 * @return
	 */
	public String changeURLHttp(String url) {
		try {
			if (url != null) {
				url = url.replace("https://", "http://");
			}

		} catch (Exception e) {
			logger.error(e.toString());
		}
		return url;

	}
	
	/**
	 * 
	 * @param textHtml
	 * @return
	 */
	public String cleantext(String textHtml) {
		try {
			Document doc = Jsoup.parse(textHtml);
			String cleantext = doc.body().text();
			return cleantext;
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return null;

	}
	
	/**
	 * 
	 * @param text
	 * @return
	 */
	public String findPattern(String text, String regexr) {
		try {
			String cadena = "";
			Pattern pattern = Pattern.compile(regexr);
			Matcher matcher = pattern.matcher(text);

			List<String> listMatches = new ArrayList<String>();

			while (matcher.find()) {
				listMatches.add(matcher.group());
			}

			for (String s : listMatches) {
				cadena = cadena.concat(" \n ").concat(s);
			}
			return cadena;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return null;
		}

	}

}
