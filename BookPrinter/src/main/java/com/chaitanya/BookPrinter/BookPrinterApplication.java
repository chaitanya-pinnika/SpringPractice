package com.chaitanya.BookPrinter;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BookPrinterApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context=SpringApplication.run(BookPrinterApplication.class, args);
		
		Book book = (Book) context.getBean(Book.class);
		book.setBid(123);
		book.setName("Head First Java");
		System.out.println(book);
		
	}

}
