package com.nt.listener;

import java.util.Date;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component("jmListener")
public class JobMonitoringListener implements JobExecutionListener {

	
	private long startTime,endline;
	
	public  JobMonitoringListener() {
		System.out.println("JobMoonitoringListener::0-param constructor");

	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("job is about to begin at:: "+new Date());
		startTime = System.currentTimeMillis();
		System.out.println("job status::"+jobExecution.getStatus());
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("job completed at:: "+new Date());
		endline =  System.currentTimeMillis();
		System.out.println("job Status::"+jobExecution.getStatus());
		System.out.println("Job Excution time::"+(endline - startTime));
	}
}
