package com.chaitanya.cons;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaConsumerExApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerExApplication.class, args);
		
		Logger logger = LoggerFactory.getLogger(KafkaConsumerExApplication.class);
		
		String bootstrap_servers= "localhost:9092";
		String groupId = "myFirstGroup";
		
		//1. create consumer properties
		
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
		props.put(ConsumerConfig.GROUP_ID_CONFIG,groupId );
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // from very beginning
		
		//2.create consumer
		
		KafkaConsumer<String,String> consumer = new KafkaConsumer<>(props);
		
		//3.subscribe consumer to our topic
		consumer.subscribe(Arrays.asList("test")); // we are only subscribing to one topic
		
		//4.poll for new data
		
		while(true)
		{
			ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(100)); 
			
			for(ConsumerRecord<String,String> cns: records)
			{
				logger.info("key:"+cns.key()+"\n"+
							"value:"+cns.value()+"\n"+
							"partition:"+cns.partition()+"\n"+
							"offset"+cns.offset()
						);
			}
		}
		
		
	}

}
