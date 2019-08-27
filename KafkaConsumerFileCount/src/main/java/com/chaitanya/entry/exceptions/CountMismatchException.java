package com.chaitanya.entry.exceptions;

public class CountMismatchException extends Exception{

	public CountMismatchException()
	{
		System.out.println("There is a mismatch between Number of messages consumed by Consumer and messages written to File");
	}
	
}
