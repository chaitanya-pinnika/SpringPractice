package com.chaitanya.prod;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProdCallBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProdCallBackApplication.class, args);
		
		
		//create a LOgger
		Logger logger = LoggerFactory.getLogger(KafkaProdCallBackApplication.class);
		
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
					producer.send(new ProducerRecord<String,String>("test","Hello-"+i),new Callback() {@Override
					public void onCompletion(RecordMetadata metadata, Exception exception) {
						// TODO Auto-generated method stub
						if(exception==null) // if no exception
						{
							logger.info("received metadata:\n"+"topic:"+metadata.topic()+"\n"+
									"Partition:"+metadata.partition()+"\n"+
									"offset:"+metadata.offset()+"\n"+
									"Timestamp"+metadata.timestamp()+"\n"
									);
							
							
						}
						else //if there is an exception
						{
							logger.error("can not produce: ",exception);
						}
						
					}	
					});
				
				//5.since the send call is asynchronous we have to close it 
				producer.close();
		
		
	}

}
