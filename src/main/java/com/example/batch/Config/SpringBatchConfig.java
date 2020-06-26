package com.example.batch.Config;



import javax.persistence.Entity;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.example.batch.DBWriter.DBWriter;
import com.example.batch.Model.User;
@Component
@Configuration
@EnableBatchProcessing 
public class SpringBatchConfig {
	
	@Autowired
	DBWriter itemWriter;

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, ItemReader<User>  itemReader,
			ItemWriter<User> itemWriter, ItemProcessor<User,User> itemProcessor) {
		
		System.out.println("job builder factory");
		
		Step step =stepBuilderFactory.get("step")
				.<User,User>chunk(100)
				 .reader(itemReader)
				  .processor(itemProcessor)
				   .writer(itemWriter)
				    .build();
				
				
				
		return jobBuilderFactory
				.get("ETL-JOB")
				 .incrementer(new RunIdIncrementer())
				  .start(step)
				   .build();
	}
	
	@Bean
	public FlatFileItemReader<User> itemReader()
	{   System.out.println("item reader running");
		FlatFileItemReader<User> flatFileItemReader = new  FlatFileItemReader<User>();
		flatFileItemReader.setResource(new ClassPathResource("users.csv"));
		flatFileItemReader.setName("reader");
		flatFileItemReader.setLinesToSkip(1);
		flatFileItemReader.setLineMapper(lineMapper());
		
		return flatFileItemReader;
	}
    @Bean
	public LineMapper<User> lineMapper() {
    	System.out.println("Line mapper running");
    	DefaultLineMapper<User> defaultLinemapper = new DefaultLineMapper<>();
    	DelimitedLineTokenizer DeLimitedLineTokenizer = new DelimitedLineTokenizer();
    	DeLimitedLineTokenizer.setDelimiter(",");
    	DeLimitedLineTokenizer.setStrict(false);
    	DeLimitedLineTokenizer.setNames(new String[] {"id","name","dept","salary"});
    	
    	BeanWrapperFieldSetMapper<User> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
    	beanWrapperFieldSetMapper.setTargetType(User.class);
    	System.out.println("bean wrapper  running");
    	
    	defaultLinemapper.setLineTokenizer(DeLimitedLineTokenizer);
    	defaultLinemapper.setFieldSetMapper(beanWrapperFieldSetMapper);
    	return defaultLinemapper;
		
		
	}

}
