package com.belatrixsf.findpatternapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.belatrixsf.findpatternapi.helpers.RegexModel;
import com.belatrixsf.findpatternapi.repositories.RegexRepository;



@EnableMongoRepositories(basePackageClasses = RegexRepository.class)
@Configuration
public class MongoDBConfig {	

    @Bean
    CommandLineRunner commandLineRunner(RegexRepository regexRepository) {
        return strings -> {
        	regexRepository.save(new RegexModel(3, "des 3", "Development"));
        	regexRepository.save(new RegexModel(4, "des 4", "Operations"));
        };
    }

}
