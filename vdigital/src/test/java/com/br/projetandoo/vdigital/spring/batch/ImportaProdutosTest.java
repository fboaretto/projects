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
	private JobLauncher jobLauncher;

	@Autowired
	private Job job;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("file:src/test/resources/spring/batch/fileset/produtos.txt")
	private Resource produtosResource;

	@Value("file:src/test/resources/spring/batch/fileset/produtosErroLinha2.txt")
	private Resource produtosErro2Resource;
	
	@Value("file:src/test/resources/spring/batch/fileset/produtosErroLinha3.txt")
	private Resource produtosErro3Resource;

	//private static final Logger LOG = LoggerFactory.getLogger(ImportaProdutosTest.class);
			
	public static int contagemInicial;
	
	public static int contagemFinal;

	
	@Before
	public void setup() throws Exception {
		jdbcTemplate.update("delete from Produto");
		contagemInicial = recupaContagemTotalProdutos();
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

		assertEquals(1001, produto1.get("produto_oid"));
		assertEquals("Coca-Cola(1L)", produto1.get("nome"));
		assertEquals(500, produto1.get("quantidadeDepo"));
		
		assertEquals(new BigDecimal("1.00"), produto1.get("valorCompra"));
		assertEquals(new BigDecimal("3.50"), produto1.get("valorVenda"));

		assertEquals(200, produto1.get("pontoReposicao"));
		assertEquals(10, produto1.get("quantMaxGondola"));
		assertEquals(10, produto1.get("quantMinGondola"));
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
		
		assertEquals(produto1.get("produto_oid"), 1001);
		assertEquals(produto2.get("produto_oid"), 1002);
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
