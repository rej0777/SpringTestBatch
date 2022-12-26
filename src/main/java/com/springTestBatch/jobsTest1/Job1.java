package com.springTestBatch.jobsTest1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.PlatformTransactionManager;
import com.springTestBatch.ExampleObject1.ExampleObject1;
import com.springTestBatch.repository.ExampleObject1Repository;

@Configuration
@EnableBatchProcessing
public class Job1 {

	@Autowired
	ExampleObject1Repository exampleObject1Repository;

	@Bean
	Job job1Start(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder("job1Start", jobRepository)
				.preventRestart()
				.start(Step1Job1(jobRepository, transactionManager))
				.build();
	}

	@Bean
	Step Step1Job1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("Stepjob1Start", jobRepository)
				.<ExampleObject1, ExampleObject1>chunk(10, transactionManager)
				.reader(repositoryItemReader(exampleObject1Repository))
				.processor(new Proc())
				.writer(jsonFileItemWriter())
				.startLimit(1)
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
	JsonFileItemWriter<ExampleObject1> jsonFileItemWriter() {
		return new JsonFileItemWriterBuilder<ExampleObject1>()
				.jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
				.resource(new FileSystemResource("D:\\tt.txt"))
				.name("tradeJsonFileItemWriter")
				.build();
	}

}
