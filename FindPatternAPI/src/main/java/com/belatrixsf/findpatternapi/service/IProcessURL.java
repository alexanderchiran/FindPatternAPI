package com.belatrixsf.findpatternapi.service;

import java.util.List;

/**
 * 
 * @author Alexander Chiran
 * paulo.alexander12@gmail.com
 * 
 */
public interface IProcessURL {

	public void processURL(String url, List<String> notsslUrls, String regexr);
}
