package com.belatrixsf.findpatternapi.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.belatrixsf.findpatternapi.helpers.RegexModel;

@Repository
public interface RegexRepository extends MongoRepository<RegexModel,Integer> {

	
}
