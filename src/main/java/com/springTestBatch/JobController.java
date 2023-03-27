package com.springTestBatch;

import java.util.Arrays;
import java.util.List;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/job")
public class JobController {

	@Autowired
	JobService jobService;
	
	@Autowired
	JobOperator jobOperator;
	
	
	
	@GetMapping("/start/{jobName}")
	public String startJob(@PathVariable String jobName, @RequestBody List <JobParamRequest> JobParamsRequestList) throws
	JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, 
	JobParametersInvalidException {
		
		jobService.startJob(jobName, JobParamsRequestList);
				
		return "Job Start JobController";
	}
	
	@GetMapping("/stop/{executionId}")
	public  String stopJob(@PathVariable long executionId) throws
	NoSuchJobExecutionException, JobExecutionNotRunningException {
		
		jobOperator.stop(executionId);
		
		return "Job stop";
	}
	
	/*
	
	
	///mozna zrobić drugą aplikacje  do wymiany danych REST
	
	
	@GetMapping("/students")
	public List<StudentCsv> students() { //StudentResponse
		return Arrays.asList(
				new StudentCsv(1L, "John", "Smith", "john@gmail.com"),
				new StudentCsv(2L, "John2", "Smith2", "john2@gmail.com"),
				new StudentCsv(3L, "John3", "Smith3", "john3@gmail.com"),				
	}
	
	
	@PostMapping("/createStudent")
	public StudentCsv createStudent(@RequestBody StudentCsv studentRequest) {  //StudentResponse
		System.out.println("Student Created "+ studentRequest.getId());
		return new StudentCsv(studentRequest.getId(), 
				studentRequest.getFirstName(), 
				studentRequest.getLastName(), 
				studentRequest.getEmail());
	}
	*/
	
}
