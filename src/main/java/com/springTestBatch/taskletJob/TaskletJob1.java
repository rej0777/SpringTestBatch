package com.springTestBatch.taskletJob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;

import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration 
@EnableBatchProcessing
public class TaskletJob1 extends DefaultBatchConfiguration { //

	@Autowired
	ClassTasklet1 classTasklet1;
	
	@Bean
	public Job taskletJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder("taskletJob", jobRepository)
				.incrementer(new RunIdIncrementer())
					.start(taskletStep(jobRepository, transactionManager))
					
					.build();
	}

	@Bean
	public Step taskletStep(JobRepository jobRepository, PlatformTransactionManager transactionManager ) {
		return new StepBuilder("step1", jobRepository)
				.tasklet((contribution, chunkContext) -> {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!hello");
			// simulate processing time
		//	Thread.sleep(random.nextInt(3000));
			return RepeatStatus.FINISHED;
		}, transactionManager)
				.tasklet(classTasklet1, transactionManager)   
				.build();
		
	}
	
	
	  

}
