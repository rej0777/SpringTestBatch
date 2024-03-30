package com.springTestBatch.multiResourceJob;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.support.SynchronizedItemStreamReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.springTestBatch.ExampleObject1.ExampleObject1;



/*


@Configuration
@EnableBatchProcessing
public class MultiResourceJob {

	@Value("classpath*:/input2/*.txt")
	 Resource[] inputFiles;
	
	
	@Bean
	Job jobSaveAllStart(JobRepository jobRepository, PlatformTransactionManager transactionManager) throws Exception {
		return new JobBuilder("jobSaveAllStart", jobRepository)				
				.preventRestart()
				.incrementer(new RunIdIncrementer())				
				.start(StepSaveAllStart(jobRepository, transactionManager, asyncBatchTaskExecutor)) //
				//.listener(jobListener3)
				.build();
	}
	
	@Bean
	Step StepSaveAllStart(JobRepository jobRepository, PlatformTransactionManager transactionManager,TaskExecutor taskExecutor) throws Exception { //
		return new   StepBuilder("StepSaveAllStart", jobRepository)
				.<ExampleObject1, ExampleObject1>chunk(10000, transactionManager)			
				.reader(multiResourceItemReader())
//				.processor(new ProcSaveAll())		
				.writer(new DBWriter(e5IntervalDataRepo))
				
				.startLimit(2)
				.faultTolerant()
				.skip(FlatFileParseException.class)
				.skipLimit(100)
				.retryLimit(1)
				.retry(Throwable.class)
				.listener(stepListener3)
				.taskExecutor(taskExecutor)
				// .throttleLimit(20)
				.build();
	}
	
	@Bean
	public SynchronizedItemStreamReader<ExampleObject1> multiResourceItemReader() {
	    System.out.println("In multiResourceItemReader");
	    MultiResourceItemReader<ExampleObject1> reader = new MultiResourceItemReader<>();
	    reader.setDelegate(reader());
	    reader.setResources(inputFiles);
	    reader.setSaveState(false);//??
	    SynchronizedItemStreamReader<ExampleObject1> synchronizedItemStreamReader = new SynchronizedItemStreamReader<>();
	    synchronizedItemStreamReader.setDelegate(reader);
	    return synchronizedItemStreamReader;
	}
	
}
*/