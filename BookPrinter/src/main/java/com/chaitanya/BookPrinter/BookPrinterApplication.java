package com.chaitanya.BookPrinter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.chaitanya.models.Book;

@SpringBootApplication
public class BookPrinterApplication {

	@Autowired
	static Book book;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context=SpringApplication.run(BookPrinterApplication.class, args);
		
		 book = (Book) context.getBean(Book.class);
		System.out.println(book);
		
	}

}
