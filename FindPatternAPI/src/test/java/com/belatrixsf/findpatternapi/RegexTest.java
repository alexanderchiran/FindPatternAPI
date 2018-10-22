package com.belatrixsf.findpatternapi;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * 
 * @author Alexander Chiran
 * paulo.alexander12@gmail.com
 * 
 *
 */
public class RegexTest {
	
	public static void main(String[] args) {
		String cadena = "";
		String text1="Nombre: Juliana Paris Uribe	Correo: julianaparis@hotmail.com";
		

		Document doc = Jsoup.parse(text1);
		String text = doc.body().text(); // "An example link"
		System.out.println("Texto limpio: "+text);
		String regexr="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$";
		
		//String regexr2="(?<=^|(?<=[^a-zA-Z0-9-_\\.]))@([A-Za-z]+[A-Za-z0-9-_]+)";
		
		Pattern pattern = Pattern.compile(regexr);
		Matcher matcher = pattern.matcher(text);

		List<String> listMatches = new ArrayList<String>();

		while (matcher.find()) {
			listMatches.add(matcher.group());
		}

		for (String s : listMatches) {
			cadena = cadena.concat(" \n ").concat(s);
			System.out.println(s);
		}
		System.out.println(cadena);
		System.out.println("Texto limpio: "+text);
		
	}

}
