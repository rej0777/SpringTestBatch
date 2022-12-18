package com.springTestBatch.taskletJob;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class ClassTasklet1 implements Tasklet, InitializingBean {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

System.out.println("Klasa RepeatStatus  execute #######################");
		
		//return null;
return RepeatStatus.FINISHED;  //CONTINUABLE;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Klasa RepeatStatus  afterPropertiesSet #######################");
		
	}

}
