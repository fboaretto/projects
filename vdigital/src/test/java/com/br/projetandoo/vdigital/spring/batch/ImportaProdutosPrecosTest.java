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
		"/spring/batch/jobs/importaRelatorioFinanceiro.xml",
		"/spring/batch/launch-context.xml" })
public class ImportaProdutosPrecosTest {

	private static final String TABELA_PRODUTO = "produto";

	@Autowired
	private Job job;

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Value("file:src/test/resources/spring/batch/input/produtosPrecosCustoVenda_umBloco.txt")
	private Resource arquivoUnicoBloco;

	public static int contagemInicial = 0;

	public static int contagemFinal;


	//@Before
	public void setup() throws Exception {
		jdbcTemplate.update("DELETE FROM produto");
		jdbcTemplate.update("DELETE FROM fornecedor");
		assertEquals(contagemInicial, recupaContagemTotalTabela(TABELA_PRODUTO).intValue());
	}


	@Test
	public void testImportaProdutos_arquivoComApenasUmBloco_todosSalvosComSucesso() throws Exception {

		JobParametersBuilder jPBuilber = carregaArquivoTeste(arquivoUnicoBloco);

		jobLauncher.run(job, jPBuilber.toJobParameters());

		int produtosAdicionados = 9;
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
