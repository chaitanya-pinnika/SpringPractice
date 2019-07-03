package com.chaitanya.quote;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.chaitanya.quote.models.Quote;

@SpringBootApplication
public class RestQuoteApplication {
	private static final Logger log = LoggerFactory.getLogger(RestQuoteApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RestQuoteApplication.class, args);
		RestTemplate rest = new RestTemplate();
		Quote quote = rest.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		log.info(quote.toString());
	}

}
