package com.belatrixsf.findpatternapi.helpers;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class  RegexModel {
	
	@Id
	private Integer id;
	private String description;
	private String value;
	
	
	
	
	public RegexModel() {
		super();
	}


	public RegexModel(Integer id, String description, String value) {
		super();
		this.id = id;
		this.description = description;
		this.value = value;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	} 
	
	
	
}