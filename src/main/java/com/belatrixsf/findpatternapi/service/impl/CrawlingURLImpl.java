package com.belatrixsf.findpatternapi.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLHandshakeException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belatrixsf.findpatternapi.helpers.RegexModel;
import com.belatrixsf.findpatternapi.repositories.RegexRepository;
import com.belatrixsf.findpatternapi.service.ICrawlingURL;

@Service("crawlingURLImpl")
public class CrawlingURLImpl implements ICrawlingURL {

	@Autowired
	private RegexRepository regexRepository;

	private static final Logger logger = LoggerFactory.getLogger(CrawlingURLImpl.class);
	// private PageFetcher pageFetcher;
	// private Parser parser;
	// private CrawlConfig config = new CrawlConfig();

	// private int cont = 0;
	// private boolean notFound = false;
	private String regexr;
	private List<String> notsslUrls;
	/**
	 * 
	 */
	/*
	 * private void init() { try { config.setFollowRedirects(true); parser = new
	 * Parser(config); pageFetcher = new PageFetcher(config); } catch (Exception e)
	 * { logger.error(e.getMessage()); }
	 * 
	 * }
	 */

	/**
	 * 
	 * @param id
	 */
	private void obtainRegex(Integer id) {
		try {
			Optional<RegexModel> regexmodel = regexRepository.findById(id);
			regexr = regexmodel.get().getValue();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Start processing of urls
	 */
	@Override
	public void exploreFile(Integer regexrIN) {
		try {
			// init();
			notsslUrls = new ArrayList<>();
			obtainRegex(regexrIN);
			// TODO Auto-generated method stub
			System.out.println("entra al proceso");

			List<String> listURL = readFile();
			System.out.println("Proceso 11: " + listURL.toString());

			initCrawling(listURL);
			if(!notsslUrls.isEmpty()) {
				initCrawling(notsslUrls);				
			}

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
	
	private String changeURLHttp(String url) {
		try {
			if (url != null) {
				url = url.replace("https://", "http://");
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
					
					URL netUrl = new URL(url);
					String host = netUrl.getHost();

					// cont++;
					System.out.println("URL FOR: " + url);
					// url= checkURL(url);
					String textHtml = getHtmlFromPage(url);
					if (textHtml != null) {
						String cleanText = cleantext(textHtml);
						createOutPutFile(cleanText, "Original_text_" + host.concat(nameFile()));
						String textfound = findPattern(cleanText);
						createOutPutFile(textfound, "Pattern_founded_" + host);

					}

					/*
					 * if (notFound) { notFound = false; if (url.contains("https://")) {
					 * processUrl(changeURLHttps(url)); } }
					 */
				}
				System.out.println("Finaliza Proceso ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String cleantext(String textHtml) {
		try {
			Document doc = Jsoup.parse(textHtml);
			String cleantext = doc.body().text(); // "An example link"
			System.out.println("Texto limpio: " + cleantext);
			return cleantext;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 
	 * @param url
	 * @return
	 */

	/*
	 * private String checkURL(String url) { try { URL obj = new URL(url);
	 * 
	 * HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
	 * conn.setReadTimeout(5000); conn.addRequestProperty("Accept-Language",
	 * "en-US,en;q=0.8"); conn.addRequestProperty("User-Agent", "Mozilla");
	 * conn.addRequestProperty("Referer", "google.com");
	 * 
	 * System.out.println("Request URL ... " + url);
	 * 
	 * boolean redirect = false;
	 * 
	 * // normally, 3xx is redirect int status = conn.getResponseCode(); if (status
	 * != HttpURLConnection.HTTP_OK) { if (status ==
	 * HttpURLConnection.HTTP_MOVED_TEMP || status ==
	 * HttpURLConnection.HTTP_MOVED_PERM || status ==
	 * HttpURLConnection.HTTP_SEE_OTHER) redirect = true; }
	 * 
	 * System.out.println("Response Code ... " + status);
	 * 
	 * if (redirect) {
	 * 
	 * // get redirect url from "location" header field url =
	 * conn.getHeaderField("Location"); }
	 * 
	 * } catch (Exception e) { // TODO: handle exception } return url; }
	 */

	public String getHtmlFromPage(String url) {
		HttpURLConnection conn = null;

		try {

			// String url = "https://metricool.com/es/hashtags-mas-usados-instagram/";

			URL obj = new URL(url);
			conn = (HttpURLConnection) obj.openConnection();
			conn.setReadTimeout(15000);
			conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
			conn.addRequestProperty("User-Agent", "Mozilla");
			conn.addRequestProperty("Referer", "google.com");

			System.out.println("Request URL ... " + url);

			boolean redirect = false;

			// normally, 3xx is redirect
			int status = conn.getResponseCode();
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM
						|| status == HttpURLConnection.HTTP_SEE_OTHER)
					redirect = true;
			}

			System.out.println("Response Code ... " + status);

			if (redirect) {

				// get redirect url from "location" header field
				String newUrl = conn.getHeaderField("Location");

				// get the cookie if need, for login
				String cookies = conn.getHeaderField("Set-Cookie");

				// open the new connnection again
				conn = (HttpURLConnection) new URL(newUrl).openConnection();
				conn.setRequestProperty("Cookie", cookies);
				conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				conn.addRequestProperty("User-Agent", "Mozilla");
				conn.addRequestProperty("Referer", "google.com");

				System.out.println("Redirect to URL : " + newUrl);

			}

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine;
			StringBuffer html = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				html.append(inputLine);
			}
			in.close();

			System.out.println("URL Content... \n" + html.toString());
			System.out.println("Done");

			return html.toString();

		} catch (SSLHandshakeException e) {
			e.printStackTrace();
			notsslUrls.add(changeURLHttp(url));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}

		}
		return null;

	}

	/**
	 * 
	 * @param url
	 */
	/*
	 * private void processUrl1(String url) { logger.debug("Processing: {}", url);
	 * Page page = download(url); if (page != null) { ParseData parseData =
	 * page.getParseData(); if (parseData != null) { if (parseData instanceof
	 * HtmlParseData) { HtmlParseData htmlParseData = (HtmlParseData) parseData; //
	 * String title = htmlParseData.getTitle();// logger.debug("Title: {}", //
	 * htmlParseData.getTitle()); // logger.debug("Text length: {}",
	 * htmlParseData.getText().length()); // logger.debug("Html length: {}",
	 * htmlParseData.getHtml().length()); logger.debug("entra 1", url); String
	 * textURL = crearText(htmlParseData.getText()); createOutPutFile(textURL,
	 * "text_original_process" + cont + "_"); String textfound =
	 * findPattern(textURL); createOutPutFile(textfound, "Pattern_founded" + cont +
	 * "_"); } } else { logger.warn("Couldn't parse the content of the page."); } }
	 * else {
	 * 
	 * logger.warn("Couldn't fetch the content of the page.");
	 * 
	 * } logger.debug("=============="); }
	 */

	/*
	 * private String crearText(String text) {
	 * 
	 * text = text.replace("\n", " "); text = text.replace("\t", " "); return text;
	 * 
	 * }
	 */

	/**
	 * 
	 * @param text
	 * @return
	 */
	private String findPattern(String text) {
		try {
			String cadena = "";
			// String regexText = regexr;
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
			e.printStackTrace();
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
				/*
				 * os = new FileOutputStream(new File("C:/files/out/" + name + ".txt"));
				 * os.write(data.getBytes(), 0, data.length());
				 */
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
				e.printStackTrace();
			}
		}
	}

	private String nameFile() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss");
			Date fecha = new Date();
			return sdf.format(fecha);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	/*
	 * private Page download(String url) { WebURL curURL = new WebURL();
	 * curURL.setURL(url); PageFetchResult fetchResult = null; try { fetchResult =
	 * pageFetcher.fetchPage(curURL); if (fetchResult.getStatusCode() ==
	 * HttpStatus.SC_OK) { Page page = new Page(curURL);
	 * fetchResult.fetchContent(page, config.getMaxDownloadSize());
	 * parser.parse(page, curURL.getURL()); return page; } else {
	 * createOutPutFile("404 page not found ".concat(url), curURL.getDomain());
	 * //notFound = true; } } catch (Exception e) {
	 * logger.error("Error occurred while fetching url: " + curURL.getURL(), e); }
	 * finally { if (fetchResult != null) {
	 * fetchResult.discardContentIfNotConsumed(); } } return null; }
	 */

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
				text = changeURLHttps(text);
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
