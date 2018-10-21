package com.belatrixsf.findpatternapi.repositories;

/**
 * 
 * 
 * @author Alexander Chiran
 * paulo.alexander12@gmail.com
 * 
 *
 */
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.belatrixsf.findpatternapi.model.RegexModel;

@Repository
public interface RegexRepository extends MongoRepository<RegexModel,Integer> {

	
}
