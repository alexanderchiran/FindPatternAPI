package com.belatrixsf.findpatternapi.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.belatrixsf.findpatternapi.model.ClientPattern;
import com.belatrixsf.findpatternapi.model.Message;
import com.belatrixsf.findpatternapi.service.ICrawlingURL;

@RestController
@RequestMapping("/process")
public class FindPatternRestController {

	@Autowired
	private ICrawlingURL crawlingURL;
	
	

	/**
	 * this method let process the file with URLs
	 * @param clientPattern
	 * @return
	 */

	@RequestMapping(value = "/url", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Message> processurl(@RequestBody ClientPattern clientPattern) {
		Message message =null;
		try {
			System.out.println("regexr enviado " + clientPattern.getRegexr());
			
			System.out.println("regexr enviado " + clientPattern.getRegexr());
			
			/**
			 * validation that lets you know if a non-zero string is coming
			 */
			if(clientPattern.getRegexr() != null) {
				crawlingURL.exploreFile(clientPattern.getRegexr());
				message = new Message("1", "proceso correcto");
				return new ResponseEntity<Message>(message, HttpStatus.OK);
			}
			else {
				message = new Message("2", "cadena vacía");
				return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);				
			}
		} catch (Exception e) {
			message = new Message("0", "proceso Incorrecto");
			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		}		

	}
}
