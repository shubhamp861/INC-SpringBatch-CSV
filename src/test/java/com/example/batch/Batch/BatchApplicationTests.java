package com.example.batch.Batch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ContextConfiguration("SpringBatchConfig.class")
public class BatchApplicationTests {

	 @Autowired
	    JobLauncherTestUtils jobLauncherTestUtils;
	@Test
	public void contextLoads() throws Exception {
		
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        
		//Testing a individual step
	        //JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");
	        
	        assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
		
		
	}

}
