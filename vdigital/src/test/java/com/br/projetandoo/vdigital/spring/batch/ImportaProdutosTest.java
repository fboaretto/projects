package com.br.projetandoo.vdigital.spring.batch;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/spring/batch/jobs/importaProdutosJob.xml",
		"/spring/batch/job-database-test.xml",
		"/spring/batch/job-context-test.xml" })
public class ImportaProdutosTest {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("file:src/test/resources/spring/batch/produtos.txt")
	private Resource produtosResource;

	@Value("file:src/test/resources/spring/batch/produtosErro.txt")
	private Resource produtosErroResource;

	//private static final Logger LOG = LoggerFactory.getLogger(ImportaProdutosTest.class);
			
	public static int contagemInicial;
	public static int contagemFinal;


	@Before
	public void setup() throws Exception {
		jdbcTemplate.update("delete from Produto");
		contagemInicial = recupaTodosProdutos();
	}


	//@Test
	public void testImportaProdutos_todosSalvosComSucesso() throws Exception {

		jobLauncher.run(job,
				new JobParametersBuilder().addString("inputResource", produtosResource.getFile().getAbsolutePath())
										  .addLong("timestamp", System.currentTimeMillis())
										  .toJobParameters());
		int produtosAdicionados = 5;
		contagemFinal = recupaTodosProdutos();

		assertEquals(contagemInicial + produtosAdicionados, contagemFinal);
	}


	@Test
	public void testImportaProdutos_primeiroProdutoErrado_nenhumItemSalvo() throws Exception {

		jobLauncher.run(job, new JobParametersBuilder().addString("inputResource",produtosErroResource.getFile().getAbsolutePath())
															 .addLong("timestamp", System.currentTimeMillis())
															 .toJobParameters());
		int produtosAdicionados = 0;
		contagemFinal = recupaTodosProdutos();

		assertEquals(contagemInicial + produtosAdicionados, contagemFinal);
	}
	

	public Integer recupaTodosProdutos() {
		return jdbcTemplate.queryForObject("select count(*) from Produto", Integer.class);
	}
}
