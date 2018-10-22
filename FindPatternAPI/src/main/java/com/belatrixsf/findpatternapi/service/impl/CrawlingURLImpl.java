package com.belatrixsf.findpatternapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belatrixsf.findpatternapi.helpers.FileUtility;
import com.belatrixsf.findpatternapi.model.RegexModel;
import com.belatrixsf.findpatternapi.repositories.RegexRepository;
import com.belatrixsf.findpatternapi.service.ICrawlingURL;
import com.belatrixsf.findpatternapi.service.IProcessURL;

/**
 * 
 * @author Alexander Chiran paulo.alexander12@gmail.com
 * 
 */
@Service
public class CrawlingURLImpl implements ICrawlingURL {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RegexRepository regexRepository;

	@Autowired
	private IProcessURL iProcessURL;
	

	private FileUtility fileUtility = new FileUtility();

	private String regexr;
	private List<String> notsslUrls;

	/**
	 * Start processing of urls
	 */
	@Override
	public void exploreFile(Integer regexrIN) {
		try {

			notsslUrls = new ArrayList<>();
			obtainRegex(regexrIN);
			List<String> listURL = fileUtility.readFile();
			initCrawling(listURL);
			if (!notsslUrls.isEmpty()) {
				initCrawling(notsslUrls);
			}

		} catch (Exception e) {
			logger.error(e.toString());
		}
	}

	/**
	 * 
	 * @param id
	 */
	private void obtainRegex(Integer id) {
		try {
			Optional<RegexModel> regexmodel = regexRepository.findById(id);
			regexr = regexmodel.get().getValue();

		} catch (Exception e) {
			logger.error(e.toString());
		}

	}

	/**
	 * go through each URL
	 * 
	 * @param listURL
	 */
	private void initCrawling(List<String> listURL) {

		try {
			if (listURL != null) {
				for (String url : listURL) {
					iProcessURL.processURL(url, notsslUrls, regexr);
				}
			}
		} catch (Exception e) {
			logger.error(e.toString());
		}
	}

	

}
