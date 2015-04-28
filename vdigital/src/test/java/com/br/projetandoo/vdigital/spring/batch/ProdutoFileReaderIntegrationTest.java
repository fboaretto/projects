package com.br.projetandoo.vdigital.spring.batch;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.concurrent.Callable;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.batch.test.StepScopeTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;


@ContextConfiguration(locations = { "/spring/batch/jobs/formataArquivoJob.xml",
		"/spring/batch/job-database-test.xml",
		"/spring/batch/job-context-test.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class ProdutoFileReaderIntegrationTest {

	@Autowired
	private ItemReader<String> produtoFileReader;
	
	@Value("file:src/test/resources/spring/batch/input/produtosFornecedores_teste_sem_quebra_linha.txt")
	private Resource produtosFornecedoresResource;

	public StepExecution getStepExecution() throws IOException {
		JobParameters jobParams = new JobParametersBuilder()
				.addString("inputFile", produtosFornecedoresResource.getFile().getAbsolutePath())
				.toJobParameters();

		return MetaDataInstanceFactory.createStepExecution(jobParams);
	}

	@Test
	public void testProdutoFileReader() throws Exception {

		StepExecution execution = getStepExecution();

		Integer readCount = StepScopeTestUtils.doInStepScope(execution,
				new Callable<Integer>() {

					@Override
					public Integer call() throws Exception {
						((ItemStream) produtoFileReader)
								.open(new ExecutionContext());

						int i = 0;
						while (produtoFileReader.read() != null) {
							i++;
						}
						return i;
					}
				});

		assertEquals(readCount.intValue(), 51);
	}

}
