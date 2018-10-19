package com.belatrixsf.findpatternapi.service.impl;

import org.springframework.stereotype.Repository;

import com.belatrixsf.findpatternapi.helpers.RegexModel;
import com.belatrixsf.findpatternapi.service.Regex;


@Repository
public class RegexImpl implements Regex {
	
	
	
	

	@Override
	public RegexModel getById(String id) {
		//return regexRepository.findById(id).orElse(null);
		//Query query = new Query();
		//query.addCriteria(Criteria.where("id").is(id));
		//return mongoTemplate.findOne(query, RegexModel.class);
		return null;
	}

}
