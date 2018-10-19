package com.belatrixsf.findpatternapi.service.impl;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.belatrixsf.findpatternapi.service.CrawlingURL;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.fetcher.PageFetchResult;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.parser.ParseData;
import edu.uci.ics.crawler4j.parser.Parser;
import edu.uci.ics.crawler4j.url.WebURL;

@Service("crawlingURLImpl")
public class CrawlingURLImpl implements CrawlingURL {

	private static final Logger logger = LoggerFactory.getLogger(CrawlingURLImpl.class);
	private  PageFetcher pageFetcher;
	private  Parser parser;
	private  CrawlConfig config = new CrawlConfig();

	private int cont = 0;
	private boolean notFound = false;
	private String regexr;
	
	/**
	 * 
	 */
	private  void init() {
		try {
			config.setFollowRedirects(true);
			parser = new Parser(config);
			pageFetcher = new PageFetcher(config);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	/**
	 * Start processing of urls
	 */
	@Override
	public void exploreFile(String regexrIN) {
		try {
			regexr=regexrIN;
			init();
			// TODO Auto-generated method stub
			System.out.println("entra al proceso");
			List<String> listURL = readFile();
			System.out.println("Proceso 11: " + listURL.toString());
			initCrawling(listURL);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param listURL
	 * @return
	 */
	private String changeURLHttps(String url) {
		try {
			if (url != null) {
				url = url.replace("http://", "https://");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;

	}

	

	/**
	 * 
	 * @param listURL
	 */
	private void initCrawling(List<String> listURL) {

		try {
			if (listURL != null) {
				for (String url : listURL) {
					cont++;
					System.out.println("URL FOR: " + url);
					processUrl(url);
					if (notFound) {
						notFound = false;
						processUrl(changeURLHttps(url));
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 
	 * @param url
	 */
	public void processUrl(String url) {
		logger.debug("Processing: {}", url);
		Page page = download(url);
		if (page != null) {
			ParseData parseData = page.getParseData();
			if (parseData != null) {
				if (parseData instanceof HtmlParseData) {
					HtmlParseData htmlParseData = (HtmlParseData) parseData;
					//String title = htmlParseData.getTitle();// logger.debug("Title: {}", htmlParseData.getTitle());
					// logger.debug("Text length: {}", htmlParseData.getText().length());
					// logger.debug("Html length: {}", htmlParseData.getHtml().length());
					logger.debug("entra 1", url);
					String textURL = crearText(htmlParseData.getText());
					createOutPutFile(textURL, "text_original_process" + cont + "_");
					String textfound = findPattern(textURL);
					createOutPutFile(textfound, "Pattern_founded" + cont + "_");
				}
			} else {
				logger.warn("Couldn't parse the content of the page.");
			}
		} else {

			logger.warn("Couldn't fetch the content of the page.");

		}
		logger.debug("==============");
	}

	private String crearText(String text) {

		text = text.replace("\n", " ");
		text = text.replace("\t", " ");
		return text;

	}

	/**
	 * 
	 * @param text
	 * @return
	 */
	private String findPattern(String text) {
		try {
			String cadena = "";
			//String regexText = regexr;
			Pattern pattern = Pattern.compile(regexr);
			Matcher matcher = pattern.matcher(text);

			List<String> listMatches = new ArrayList<String>();

			while (matcher.find()) {
				listMatches.add(matcher.group());
			}

			for (String s : listMatches) {
				cadena = cadena.concat(" \n ").concat(s);
				System.out.println(s);
			}
			return cadena;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return null;
		}

	}

	/**
	 * 
	 */
	private void createOutPutFile(String data, String name) {
		OutputStream os = null;
		try {
			if (data != null && name != null) {
				name = name.concat(nameFile());
				os = new FileOutputStream(new File("C:/files/out/" + name + ".txt"));
				os.write(data.getBytes(), 0, data.length());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private String nameFile() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss");
		Date fecha = new Date();
		return sdf.format(fecha);
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	private Page download(String url) {
		WebURL curURL = new WebURL();
		curURL.setURL(url);
		PageFetchResult fetchResult = null;
		try {
			fetchResult = pageFetcher.fetchPage(curURL);
			if (fetchResult.getStatusCode() == HttpStatus.SC_OK) {
				Page page = new Page(curURL);
				fetchResult.fetchContent(page, config.getMaxDownloadSize());
				parser.parse(page, curURL.getURL());
				return page;
			} else {
				createOutPutFile("404 page not found ".concat(url), curURL.getDomain());
				notFound = true;
			}
		} catch (Exception e) {
			logger.error("Error occurred while fetching url: " + curURL.getURL(), e);
		} finally {
			if (fetchResult != null) {
				fetchResult.discardContentIfNotConsumed();
			}
		}
		return null;
	}

	/**
	 * reading the file and you get a list of urls
	 * 
	 * @return
	 */
	private List<String> readFile() {

		List<String> listURL = new ArrayList<String>();
		File file = new File("C:/files/url.txt");
		BufferedReader reader = null;

		try {
			reader = new BufferedReader(new FileReader(file));
			String text = null;

			while ((text = reader.readLine()) != null) {
				listURL.add(text);
			}
			return listURL;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
			}

		}

	}

}
