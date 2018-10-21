package com.belatrixsf.findpatternapi.helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.SSLHandshakeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Alexander Chiran
 * paulo.alexander12@gmail.com
 * 
 *
 */
public class HttpUtility {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 
	 * @param url
	 * @return
	 */
	public String getHtmlFromPage(String url, List<String> notsslUrls) {
		HttpURLConnection conn = null;

		try {
			
			URL obj = new URL(url);
			conn = (HttpURLConnection) obj.openConnection();
			conn.setReadTimeout(15000);
			conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
			conn.addRequestProperty("User-Agent", "Mozilla");
			conn.addRequestProperty("Referer", "google.com");

			boolean redirect = false;

			// normally, 3xx is redirect
			int status = conn.getResponseCode();
			if (status != HttpURLConnection.HTTP_OK) {
				if (status == HttpURLConnection.HTTP_MOVED_TEMP || status == HttpURLConnection.HTTP_MOVED_PERM
						|| status == HttpURLConnection.HTTP_SEE_OTHER)
					redirect = true;
			}

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

			}

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String inputLine;
			StringBuffer html = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				html.append(inputLine);
			}
			in.close();
			return html.toString();

		} catch (SSLHandshakeException e) {
			logger.error(e.toString());
			Transformation transformation = new Transformation();
			notsslUrls.add(transformation.changeURLHttp(url));

		} catch (Exception e) {
			logger.error(e.toString());
		} finally {
			if (conn != null) {
				conn.disconnect();
			}

		}
		return null;

	}

}
