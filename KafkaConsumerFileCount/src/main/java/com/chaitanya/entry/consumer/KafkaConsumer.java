package com.chaitanya.entry.consumer;
import com.chaitanya.entry.model.User;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	private final static String file="output.DAT";
	
	  @KafkaListener(topics = "test_json",groupId="group_json")
	    public void consume(User user) {
	        System.out.println("Consumed message: " + user);
	        writeToFile(user);
	    }
	  
	  public void writeToFile(User user)
	    {
	    	ObjectMapper mapper = new ObjectMapper();
	    	ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
	    	PrintWriter out;
			try {
				out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
				 writer.writeValue(out, user);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }

}
