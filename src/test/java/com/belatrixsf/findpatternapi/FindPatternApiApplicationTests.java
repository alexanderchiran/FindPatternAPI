package com.belatrixsf.findpatternapi;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.belatrixsf.findpatternapi.controller.FindPatternRestController;
import com.belatrixsf.findpatternapi.model.ClientPattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindPatternApiApplicationTests {

	@Test
	public void contextLoads() {
	}

	public static void main(String[] args) {
		/*
		 * FindPatternRestController findPatternRestController = new
		 * FindPatternRestController(); ClientPattern clientPattern = new
		 * ClientPattern(); clientPattern.setRegexr("222222");
		 * findPatternRestController.processurl(clientPattern);
		 */
		String patron = "var1[value1], var2[value2], var3[value3] #mividavale ewrewrqewwrecr  wef w wefwerer werwer \n\n asdasdsada";
		//Pattern pattern = Pattern.compile("(\\[)(.*?)(\\])");
		Pattern pattern = Pattern.compile("(?<=[\\s>])#(\\d*[A-Za-z_]+\\d*)\\b(?!;)", Pattern.MULTILINE);
		Matcher matcher = pattern.matcher(patron);

		List<String> listMatches = new ArrayList<String>();

		while (matcher.find()) {
			listMatches.add(matcher.group());
		}
		System.out.println("Inicia");
		for (String s : listMatches) {
			System.out.println(s);
		}

	}

}
