package com.springTestBatch.jobsTest1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.builder.StaxEventItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.transaction.PlatformTransactionManager;

import com.springTestBatch.JobListener1;
import com.springTestBatch.StepListener1;
import com.springTestBatch.ExampleObject1.ExampleObject1;
import com.springTestBatch.repository.ExampleObject1Repository;

@Configuration
@EnableBatchProcessing
public class Job1 {

	@Autowired
	ExampleObject1Repository exampleObject1Repository;
	
	@Autowired
	JobListener1 jobListener1;
	
	@Autowired
	StepListener1 stepListener1;

	@Bean
	Job job1Start(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder("job1Start", jobRepository)
				.preventRestart()
				.start(Step1Job1(jobRepository, transactionManager))
				.listener(jobListener1)
				.build();
	}

	@Bean
	Step Step1Job1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("Stepjob1Start", jobRepository)
				.<ExampleObject1, ExampleObject1>chunk(10, transactionManager)
				.reader(repositoryItemReader(exampleObject1Repository))
				.processor(new Proc())
				//.writer(jsonFileItemWriter(null)) 
				.writer(itemWriterXML(null))
				.startLimit(1)
				.listener(stepListener1)
				.build();
	}

	@Bean
	RepositoryItemReader<ExampleObject1> repositoryItemReader(ExampleObject1Repository exampleObject1Repository) {

		Map<String, Direction> sortMap = new HashMap<>();
		sortMap.put("id", Direction.DESC); // name

		System.out.println("!!!!!!Start JOB");
		return new RepositoryItemReaderBuilder<ExampleObject1>()
				// .pageSize(50)
				.repository(exampleObject1Repository)
				.methodName("selectALLV2")
				// .arguments(Arrays.asList(1L, 2L)) pageable
				.arguments()
				.sorts(sortMap)
				.saveState(false)
				.build();
	}

	@Bean
	@StepScope
	JsonFileItemWriter<ExampleObject1> jsonFileItemWriter(@Value("#{jobParameters['outputFile']}") FileSystemResource fileSystemResource) {	
		return new JsonFileItemWriterBuilder<ExampleObject1>()
				.jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
				.resource(fileSystemResource)   //new FileSystemResource("D:\\jsonFileItemWriterOUTPUT.Json")
				.name("tradeJsonFileItemWriter")
				.build();
	}
	

	
//	@Bean
//	@StepScope
	 ItemWriter<ExampleObject1> itemWriterXML(
			@Value("#{jobParameters['outputFile']}") FileSystemResource fileSystemResource) { // Environment environment

		XStreamMarshaller studentMarshaller = new XStreamMarshaller();
		studentMarshaller.setAliases(Collections.singletonMap("ExampleObject1", ExampleObject1.class));
		
		return new StaxEventItemWriterBuilder<ExampleObject1>()
				.name("studentWriter")
				.resource(new FileSystemResource("D:\\xmlFileItemWriterOUTPUT.xml") ) //fileSystemResource  
				.marshaller(studentMarshaller)
				.rootTagName("Object1")				
				.build();
	}

}
