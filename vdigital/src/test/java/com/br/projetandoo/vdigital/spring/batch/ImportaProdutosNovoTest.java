package com.br.projetandoo.vdigital.spring.batch;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

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
		"/spring/batch/jobs/formataArquivoJob.xml",
		"/spring/batch/launch-context.xml" })
public class ImportaProdutosNovoTest {

	@Autowired
	private Job job;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("file:src/test/resources/spring/batch/input/produtosFornecedores_teste_sem_quebra_linha.txt")
	private Resource produtosFornecedoresResource;

	public static int contagemInicial = 0;
	
	public static int contagemFinal;

	
	@Before
	public void setup() throws Exception {
		jdbcTemplate.update("DELETE FROM produto");
	}


	@Test
	public void testImportaProdutos_todosSalvosComSucesso() throws Exception {

		JobParametersBuilder jPBuilber = carregaArquivoTeste(produtosFornecedoresResource);
		
		jobLauncher.run(job, jPBuilber.toJobParameters());
		
		int produtosAdicionados = 51;
		contagemFinal = recupaContagemTotalProdutos();

		assertEquals(contagemInicial + produtosAdicionados, contagemFinal);
		
		//List<Map<String, Object>> produtosMap = jdbcTemplate.queryForList("SELECT * FROM produto");
		//Map<String, Object> produto1 = produtosMap.get(0);
		//Map<String, Object> produto5 = produtosMap.get(4);

		//Verificando o  primeiro e o útimo produto
		//assertEquals(1001L, produto1.get("id"));
		//assertEquals("Coca-Cola(1L)", produto1.get("nome"));
		//assertEquals(100, produto1.get("pontoReposicao"));
		
	}


	public Integer recupaContagemTotalProdutos() {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM produto", Integer.class);
	}
	
	public JobParametersBuilder carregaArquivoTeste(Resource resource) throws IOException {
		JobParametersBuilder jPBuilber = new JobParametersBuilder();
		jPBuilber.addString("inputFile", resource.getFile().getAbsolutePath());
		return jPBuilber;
	}
	
}
