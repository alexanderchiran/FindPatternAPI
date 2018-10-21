package com.belatrixsf.findpatternapi.service.impl;

import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.belatrixsf.findpatternapi.helpers.FileUtility;
import com.belatrixsf.findpatternapi.helpers.HttpUtility;
import com.belatrixsf.findpatternapi.helpers.Transformation;
import com.belatrixsf.findpatternapi.service.IProcessURL;

@Service
public class ProcessURLImpl implements IProcessURL {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Transformation transformation = new Transformation();
	private FileUtility fileUtility = new FileUtility();
	private HttpUtility httpUtility = new HttpUtility();

	/**
	 * 
	 */
	@Async
	public void processURL(String url, List<String> notsslUrls, String regexr) {
		try {

			URL netUrl = new URL(url);
			String host = netUrl.getHost();

			String textHtml = httpUtility.getHtmlFromPage(url, notsslUrls);
			if (textHtml != null) {
				String cleanText = transformation.cleantext(textHtml);
				fileUtility.createOutPutFile(cleanText, "Original_text_" + host.concat(fileUtility.nameFile()));
				String textfound = transformation.findPattern(cleanText, regexr);
				fileUtility.createOutPutFile(textfound, "Pattern_founded_" + host.concat(fileUtility.nameFile()));

			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}
}
