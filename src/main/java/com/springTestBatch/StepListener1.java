package com.springTestBatch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class StepListener1 implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		System.out.println("Before Step" + stepExecution.getStepName());
		System.out.println("Before Step" + stepExecution.getJobExecutionId());
		System.out.println("Before Step" + stepExecution.getExecutionContext());
		
		stepExecution.getExecutionContext().put("sec", "sec wartość");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		// TODO Auto-generated method stub
		
		System.out.println("After Step" + stepExecution.getStepName());
		System.out.println("After Step" + stepExecution.getJobExecutionId());
		System.out.println("After Step" + stepExecution.getExecutionContext());
		return null;
	}

}
