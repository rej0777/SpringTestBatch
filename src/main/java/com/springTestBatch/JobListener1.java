package com.springTestBatch;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobListener1 implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Before Job" + jobExecution.getJobInstance().getJobName());
		System.out.println("Beforejob Params" + jobExecution.getJobParameters());
		System.out.println("Beforejob getExecutionContext" + jobExecution.getExecutionContext());
		jobExecution.getExecutionContext().put("xx", "xxx");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("after Job" + jobExecution.getJobInstance().getJobName());
		System.out.println("after Job Params" + jobExecution.getJobParameters());
		System.out.println("after Job getExecutionContext" + jobExecution.getExecutionContext());
	}

	
	
}
