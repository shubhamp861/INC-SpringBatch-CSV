package com.example.batch.Config;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.batch.DBWriter.DBWriter;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBatchConfig.class)
public class testy {

	// @ContextConfiguration("SpringBatchConfig.class")

	@Autowired
	JobLauncherTestUtils jobLauncherTestUtils;

	

	@Test
	public void contextLoads() throws Exception {
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

	}
}
