package com.belatrixsf.findpatternapi;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.belatrixsf.findpatternapi.repositories.RegexRepository;


/**
 * 
 * @author Alexander Chiran
 * paulo.alexander12@gmail.com
 * 
 *
 */
@EnableMongoRepositories(basePackageClasses = RegexRepository.class)
@Configuration
public class MongoDBConfig {	

}
