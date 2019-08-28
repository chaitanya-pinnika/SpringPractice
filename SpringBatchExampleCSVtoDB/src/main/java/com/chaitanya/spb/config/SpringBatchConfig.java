package com.chaitanya.spb.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.chaitanya.spb.model.User;

@EnableBatchProcessing
@Configuration
public class SpringBatchConfig {
	
	//cretae a job
	//Inorder to create a job we need JobBuilderFactory and it is automatically created by Spring boot 
	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,ItemReader<User> itemReader,ItemProcessor<User,User> itemProcessor,ItemWriter<User> itemWriter)
	{
		Step step = stepBuilderFactory.get("ETL-file-load")
				.<User,User>chunk(100)
				.reader(itemReader).processor(itemProcessor).writer(itemWriter).build();
		
		//creating a job
		return jobBuilderFactory.get("ETL-Load")     // our factory creates a job with ET-Load name
			.incrementer(new RunIdIncrementer()) // sequece of ids assigned to every run. default provided by spring boot
			.start(step) // if we have multiple steps we can use either flow or start. If we have only one step we can use start and assign step
			.build();
	}
	
	@Bean
	public FlatFileItemReader<User> itemReader(@Value("${inputFile}") Resource resource)
	{
		FlatFileItemReader<User> flatFileItemReader = new FlatFileItemReader<>();
		flatFileItemReader.setResource(resource);
		flatFileItemReader.setName("CSV-Reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		return flatFileItemReader;
	}
	
	@Bean
	public LineMapper<User> lineMapper()
	{
		DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[] {"id","name","dept","salary"});
		BeanWrapperFieldSetMapper<User> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(User.class);
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		return defaultLineMapper;
	}

}
