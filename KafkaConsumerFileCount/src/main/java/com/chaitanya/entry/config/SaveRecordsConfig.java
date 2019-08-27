package com.chaitanya.entry.config;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.chaitanya.entry.save.SaveRecords;

@Configuration
public class SaveRecordsConfig {
	@Bean
	@Scope("prototype")
	public AtomicInteger atomicInteger()
	{
		return new AtomicInteger();
	}
	

}
