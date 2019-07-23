package com.chaitanya.cons;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

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
public class KafkaConsumerThreadApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerThreadApplication.class, args);
		//new ConsumerThreadRunner().first();
		new KafkaConsumerThreadApplication().run();
		
		
	} // end main method
	

  private KafkaConsumerThreadApplication()
  {
	  
  }
  private void run()
  {
	  Logger logger = LoggerFactory.getLogger(KafkaConsumerThreadApplication.class);
	   CountDownLatch latch = new CountDownLatch(1);
	   logger.info("creating the consumer thread");
	   Runnable myrunnable = new ConsumerThread(latch); 
	   Thread myThread = new Thread(myrunnable);
	   myThread.start();
	   // add a shutdown hook
	   Runtime.getRuntime().addShutdownHook(new Thread(()-> {
		   logger.info("caught shutdown hook");
		   ((ConsumerThread) myrunnable).shutdown();
		   
		   try {
			   latch.await();
		   }
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }
		   logger.info("Application has exited");
		   
		   
		   
	   }));
	   
	   
	   try {
		   latch.await();
	   }
	   catch(Exception e)
	   {
		   logger.error("Application got interrupted",e);
	   }
	   finally
	   {
		   logger.info("Application is closing");
	   }
  }



class ConsumerThread implements Runnable
{
	private CountDownLatch latch;
	private KafkaConsumer<String,String> consumer;
	private Logger logger = LoggerFactory.getLogger(ConsumerThread.class);
	
	ConsumerThread(CountDownLatch latch)
	{

		String bootstrap_servers= "localhost:9092";
		String groupId = "myFirstGroup";
		
		
		//1. create consumer properties
		
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_servers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
		props.put(ConsumerConfig.GROUP_ID_CONFIG,groupId );
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // from very beginning
		
		consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList("test"));
		this.latch = latch;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
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
		} // end while
		}
		catch(Exception e)
		{
			logger.info("Received shutdown signal");
		}
		
		finally
		{
			consumer.close();
			latch.countDown();// tell our main code we are done with consumer.
		}
	}
	
	public void shutdown() {
		
		consumer.wakeup(); // it will throw exception to interrupt consumer.poll()
		
	}
} // end Thread class
}// end Application Level class
	
	



