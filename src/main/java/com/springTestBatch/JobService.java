package com.springTestBatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Service
public class JobService {

	
	   @Autowired
	    JobLauncher jobLauncher;

	
	
	@Qualifier("taskletJob")
	@Autowired
	Job taskletJob;

	
	
	@Async
	public void startJob(String jobName, List <JobParamRequest> JobParamsRequestList) throws 
	JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, 
	JobParametersInvalidException {
		
		
Map<String, JobParameter<?>> param =new HashMap<>();
		
		
		JobParamsRequestList.stream().forEach(J ->{
			param.put(J.getParamKey(), new JobParameter(J.getParamValue(), J.getClass()));
		});
		param.put("currentTime", new JobParameter(System.currentTimeMillis(), Long.class));
		JobParameters paramJob = new JobParameters(param);
	
	
		  
		
		if (jobName.equals("taskletJob")) {
				jobLauncher.run(taskletJob, paramJob);
		//	jobLauncher.run(taskletJob, paramJob);
			
		}else if (jobName.equals("secondJobName")) {
//			jobExecution=	jobLauncher.run(secondJob, paramJob);
		}else if (jobName.equals("treeJobName")) {
//			jobExecution=	jobLauncher.run(treeJob, paramJob);
		}
	//	System.out.println("job xecution ID  " +  jobExecution.getId());
	}
		
	}
	
	

