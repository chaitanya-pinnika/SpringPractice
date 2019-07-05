package com.chaitanya.quote.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Value {
	private int id;
	private String quote;
	@Override
	public String toString() {
		return quote;
	}
	public int getId() {
		return id;
	}
	public String getQuote() {
		return quote;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}
	
	
	

}
