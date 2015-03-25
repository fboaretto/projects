package com.br.projetandoo.vdigital.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ImportaProdutos {
	
	static Logger LOG = LoggerFactory.getLogger(ImportaProdutos.class);

	public static void main(String[] args) {
		
		String[] springConfig = { 
				"spring/batch/config/database.xml",
				"spring/batch/config/context.xml",
				"spring/batch/jobs/job-produto.xml" };

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		LOG.debug("############################# " + context.getDisplayName());
		
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("produtoJob");
		
		LOG.debug("############################# " + job.toString());

		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");
	}
}
