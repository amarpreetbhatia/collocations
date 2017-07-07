package com.amarpreet.model;

public class WordList {
	
	private String id;
	private String[] synonyms;
	
	
	
	public WordList() {
		super();
	}
	
	
	public WordList(String id, String[] synonyms) {
		super();
		this.id = id;
		this.synonyms = synonyms;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String[] getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(String[] synonyms) {
		this.synonyms = synonyms;
	}
	
	

}
