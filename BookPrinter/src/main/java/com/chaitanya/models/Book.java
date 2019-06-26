package com.chaitanya.models;

public class Book {
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	int bid;
	@Override
	public String toString() {
		return "Book [name=" + name + ", bid=" + bid + "]";
	}
	
	

}
