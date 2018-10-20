package com.belatrixsf.findpatternapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.belatrixsf.findpatternapi.helpers.RegexModel;
import com.belatrixsf.findpatternapi.repositories.RegexRepository;
import com.belatrixsf.findpatternapi.service.IRegex;


@Repository
public class RegexImpl implements IRegex {
	
	@Autowired
	private RegexRepository regexRepository;

	@Override
	public List<RegexModel> findAll() {
		// TODO Auto-generated method stub
		return regexRepository.findAll();
	}

}
