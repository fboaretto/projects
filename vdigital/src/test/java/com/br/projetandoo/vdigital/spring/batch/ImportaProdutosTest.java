package com.br.projetandoo.vdigital.spring.batch;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
	private Job job;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("file:src/test/resources/spring/batch/input/produtos.txt")
	private Resource produtosResource;

	@Value("file:src/test/resources/spring/batch/input/produtosErroLinha2.txt")
	private Resource produtosErro2Resource;
	
	@Value("file:src/test/resources/spring/batch/input/produtosErroLinha3.txt")
	private Resource produtosErro3Resource;

	public static int contagemInicial = 0;
	
	public static int contagemFinal;

	
	@Before
	public void setup() throws Exception {
		jdbcTemplate.update("delete from Produto");
	}


	@Test
	public void testImportaProdutos_todosSalvosComSucesso() throws Exception {

		JobParametersBuilder jPBuilber = carregaArquivoTeste(produtosResource);
		
		jobLauncher.run(job, jPBuilber.toJobParameters());
		
		int produtosAdicionados = 5;
		contagemFinal = recupaContagemTotalProdutos();

		assertEquals(contagemInicial + produtosAdicionados, contagemFinal);
		
		List<Map<String, Object>> produtosMap = jdbcTemplate.queryForList("SELECT * FROM Produto");
		Map<String, Object> produto1 = produtosMap.get(0);
		Map<String, Object> produto5 = produtosMap.get(4);

		//Verificando o  primeiro e o útimo produto
		assertEquals(1001L, produto1.get("produto_oid"));
		assertEquals("Coca-Cola(1L)", produto1.get("nome"));
		assertEquals(new BigDecimal("1.00"), produto1.get("valorCompra"));
		assertEquals(new BigDecimal("3.50"), produto1.get("valorVenda"));
		assertEquals(500, produto1.get("quantMaxDepo"));
		assertEquals(200, produto1.get("quantMaxLoja"));
		assertEquals(500, produto1.get("quantAtualDepo"));
		assertEquals(200, produto1.get("quantAtualLoja"));
		assertEquals(350, produto1.get("pontoRessuprimento"));
		assertEquals(100, produto1.get("pontoReposicao"));
		
		assertEquals(1005L, produto5.get("produto_oid"));
		assertEquals("presunto #Sadia pacote -500g", produto5.get("nome"));
		assertEquals(new BigDecimal("1.19"), produto5.get("valorCompra"));
		assertEquals(new BigDecimal("3.29"), produto5.get("valorVenda"));
		assertEquals(300, produto5.get("quantMaxDepo"));
		assertEquals(80, produto5.get("quantMaxLoja"));
		assertEquals(300, produto5.get("quantAtualDepo"));
		assertEquals(80, produto5.get("quantAtualLoja"));
		assertEquals(160, produto5.get("pontoRessuprimento"));
		assertEquals(40, produto5.get("pontoReposicao"));
	}


	/*Arquivo fonte contendo 5 produtos.
	 *Segundo da lista contem erro.
	 *Primeiro não é salvo(commit-interval=2) 
	 * */
	@Test
	public void testImportaProdutos_segundoProdutoErrado_nenhumItemSalvo() throws Exception {

		JobParametersBuilder jPBuilber = carregaArquivoTeste(produtosErro2Resource);
		
		jobLauncher.run(job, jPBuilber.toJobParameters());
		
		int produtosAdicionados = 0;
		contagemFinal = recupaContagemTotalProdutos();

		assertEquals(contagemInicial + produtosAdicionados, contagemFinal);
	}


	/*Arquivo fonte contendo 5 produtos.
	 *Terceiro da lista contem erro.
	 *Primeiro e segundo produtos salvos(commit-interval=2) 
	 * */
	@Test
	public void testImportaProdutos_terceiroProdutoErrado_doisProdutosSalvos() throws Exception {

		JobParametersBuilder jPBuilber = carregaArquivoTeste(produtosErro3Resource);
		jobLauncher.run(job, jPBuilber.toJobParameters());
		
		int produtosAdicionados = 2;
		contagemFinal = recupaContagemTotalProdutos();

		assertEquals(contagemInicial + produtosAdicionados, contagemFinal);
		
		List<Map<String, Object>> produtosMap = jdbcTemplate.queryForList("SELECT * FROM Produto");
		Map<String, Object> produto1 = produtosMap.get(0);
		Map<String, Object> produto2 = produtosMap.get(1);
		
		assertEquals(produto1.get("produto_oid"), 1001L);
		assertEquals(produto2.get("produto_oid"), 1002L);
	}
	

	public Integer recupaContagemTotalProdutos() {
		return jdbcTemplate.queryForObject("select count(*) from Produto", Integer.class);
	}
	
	public JobParametersBuilder carregaArquivoTeste(Resource resource) throws IOException {
		JobParametersBuilder jPBuilber = new JobParametersBuilder();
		jPBuilber.addString("inputFile", resource.getFile().getAbsolutePath());
		return jPBuilber;
	}
	
}
