package com.belatrixsf.findpatternapi.service.impl;

/**
 * 
 * @author Alexander Chiran
 * paulo.alexander12@gmail.com
 *
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.belatrixsf.findpatternapi.model.RegexModel;
import com.belatrixsf.findpatternapi.repositories.RegexRepository;
import com.belatrixsf.findpatternapi.service.IRegex;


@Repository
public class RegexImpl implements IRegex {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RegexRepository regexRepository;

	@Override
	public List<RegexModel> findAll() {
		try {
			return regexRepository.findAll();
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return null;
	}

}
