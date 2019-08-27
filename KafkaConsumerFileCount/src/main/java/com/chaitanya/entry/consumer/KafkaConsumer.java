package com.chaitanya.entry.consumer;
import com.chaitanya.entry.model.User;
import com.chaitanya.entry.save.SaveRecords;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	@Autowired
	private SaveRecords saveRecords; 
	
	  @KafkaListener(topics = "test_json",groupId="group_json")
	    public void consume(User user) {
		  	saveRecords.incrementConsumedCount();
	        System.out.println("Consumed message: " + user);
	        saveRecords.writeToFile(user);
	    }
	  

}
