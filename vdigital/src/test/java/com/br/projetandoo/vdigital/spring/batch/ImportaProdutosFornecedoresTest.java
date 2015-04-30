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
		"/spring/batch/jobs/importaProdutosFornecedoresJob.xml",
"/spring/batch/launch-context.xml" })
public class ImportaProdutosFornecedoresTest {

	private static final String TABELA_PRODUTO = "produto";
	private static final String TABELA_FORNECEDOR = "fornecedor";

	@Autowired
	private Job job;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("file:src/test/resources/spring/batch/input/produtosFornecedores_umBloco.txt")
	private Resource arquivoUnicoBloco;

	@Value("file:src/test/resources/spring/batch/input/produtosFornecedores_cincoBlocos.txt")
	private Resource arquivoCincoBlocos;

	@Value("file:src/test/resources/spring/batch/input/produtosFornecedores_umBloco_estoquesAtualizados.txt")
	private Resource arquivoUnicoBlocoEstoquesAtualizados;

	public static int contagemInicial = 0;

	public static int contagemFinal;


	@Before
	public void setup() throws Exception {
		jdbcTemplate.update("DELETE FROM produto");
		jdbcTemplate.update("DELETE FROM fornecedor");
		assertEquals(contagemInicial, recupaContagemTotalTabela(TABELA_PRODUTO).intValue());
		assertEquals(contagemInicial, recupaContagemTotalTabela(TABELA_FORNECEDOR).intValue());
	}


	@Test
	public void testImportaProdutos_arquivoComApenasUmBloco_todosSalvosComSucesso() throws Exception {

		JobParametersBuilder jPBuilber = carregaArquivoTeste(arquivoUnicoBloco);

		jobLauncher.run(job, jPBuilber.toJobParameters());

		int produtosAdicionados = 51;
		contagemFinal = recupaContagemTotalTabela(TABELA_PRODUTO);

		assertEquals(contagemInicial + produtosAdicionados, contagemFinal);
	}

	//@Test
	public void testImportaProdutos_arquivoMaisDeUmBloco_todosSalvosComSucesso() throws Exception {

		JobParametersBuilder jPBuilber = carregaArquivoTeste(arquivoCincoBlocos);

		jobLauncher.run(job, jPBuilber.toJobParameters());

		int produtosAdicionados = 255;
		contagemFinal = recupaContagemTotalTabela(TABELA_PRODUTO);

		assertEquals(contagemInicial + produtosAdicionados, contagemFinal);
	}
	
	//@Test
	public void testImportaeAtualizaProdutos_sucesso() throws Exception {

		JobParametersBuilder jPBuilber = carregaArquivoTeste(arquivoUnicoBlocoEstoquesAtualizados);

		jobLauncher.run(job, jPBuilber.toJobParameters());

		int produtosAdicionados = 51;
		contagemFinal = recupaContagemTotalTabela(TABELA_PRODUTO);
		assertEquals(contagemInicial + produtosAdicionados, contagemFinal);
	}


	public Integer recupaContagemTotalTabela(String tabela) {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + tabela, Integer.class);
	}

	public JobParametersBuilder carregaArquivoTeste(Resource resource) throws IOException {
		JobParametersBuilder jPBuilber = new JobParametersBuilder();
		jPBuilber.addString("inputFile", resource.getFile().getAbsolutePath());
		return jPBuilber;
	}

}
