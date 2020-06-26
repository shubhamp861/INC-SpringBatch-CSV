package com.example.batch.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class cont {

	@Autowired
	JobLauncher joblauncher;

	@Autowired
	Job job;

	@RequestMapping("/load")

	public String map() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		System.out.println("here we are");
		// Map<String,JobParameter> maps = new HashMap<>();
		// maps.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters parameters = new JobParameters();
		JobExecution JobExecution = joblauncher.run(job, parameters);

		System.out.println("JobExecution done = " + JobExecution.getStatus());

		return "completed";

	}

}
