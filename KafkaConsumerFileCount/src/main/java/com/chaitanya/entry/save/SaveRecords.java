package com.chaitanya.entry.save;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.chaitanya.entry.model.User;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
//@PropertySource("classpath:global.properties")
public class SaveRecords {
	@Autowired
	private AtomicInteger CONSUMED_COUNT;
	
	@Autowired
	private AtomicInteger WRITTEN_COUNT;
	
	private final static String file="output.DAT";
	
	public void incrementWriteCount() {
		WRITTEN_COUNT.incrementAndGet();
	}
	public void incrementConsumedCount() {
		CONSUMED_COUNT.incrementAndGet();
	}
	
	 public int getCONSUMED_COUNT() {
		return CONSUMED_COUNT.get();
	}

	public int getWRITTEN_COUNT() {
		return WRITTEN_COUNT.get();
	}

	public void writeToFile(User user)
	    {
	    	ObjectMapper mapper = new ObjectMapper();
	    	ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
	    	PrintWriter out;
			try {
				out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
				 writer.writeValue(out, user);
				 incrementWriteCount();
				 System.out.println(CONSUMED_COUNT+" messages have been consumed..."+WRITTEN_COUNT+" messages have been written");
			} catch (IOException e) {
				System.out.println("Error writing to a file");
				e.printStackTrace();
			}
	    	
	    }
	
	
	

}
