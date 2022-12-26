package com.springTestBatch.jobsTest1;

import org.springframework.batch.item.ItemProcessor;

import com.springTestBatch.ExampleObject1.ExampleObject1;


public class Proc implements ItemProcessor<ExampleObject1, ExampleObject1> {

	@Override
	public ExampleObject1 process(ExampleObject1 item) throws Exception {

System.out.println(item.toString());
		
		return item;
	}

}
