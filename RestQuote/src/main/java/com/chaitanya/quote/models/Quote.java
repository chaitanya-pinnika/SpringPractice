package com.chaitanya.quote.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Quote {
	
	private String type;
	@Override
	public String toString() {
		return value.toString();
	}
	private Value value;
	public String getType() {
		return type;
	}
	public Value getValue() {
		return value;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	
	
	

}
