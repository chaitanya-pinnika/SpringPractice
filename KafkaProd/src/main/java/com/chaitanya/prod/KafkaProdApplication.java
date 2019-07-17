package com.chaitanya.prod;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProdApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProdApplication.class, args);
		
		//1.create producer properties
		Properties prop = new Properties();
		
		//2.Add to producer properties
		prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		//3.Create producer and supply properties
		KafkaProducer<String,String> producer = new KafkaProducer<>(prop);
		
		//4.send values as ProducerRecords
		
		for(int i=0;i<5;i++)
			producer.send(new ProducerRecord<String,String>("test","Hello-"+i));
		
		//5.since the send call is asynchronous we have to close it 
		producer.close();
		
		
	}

}
