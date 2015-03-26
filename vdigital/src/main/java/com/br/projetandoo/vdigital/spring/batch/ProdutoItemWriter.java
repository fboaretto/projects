package com.br.projetandoo.vdigital.spring.batch;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.br.projetandoo.vdigital.model.Produto;

public class ProdutoItemWriter implements ItemWriter<Produto> {
	
	private JdbcTemplate jdbcTemplate;
	
	private static final String INSERT_PRODUTO = 
			"INSERT INTO Produto(produto_oid,nome,quantidadeDepo,valorCompra,"
			+ "valorVenda,pontoReposicao,quantMaxGondola,quantMinGondola)"
					+ "values(?,?,?,?,?,?,?,?)";

	private static final String UPDATE_PRODUTO = 
			"update Produto SET nome=?, quantidadeDepo=?, valorCompra=?, valorVenda=?,"
					+ " pontoReposicao=?, quantMaxGondola=?, quantMinGondola=?"
					+ " where produto_oid=?";
	
	
	public ProdutoItemWriter(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void write(List<? extends Produto> produtos) throws Exception {

		for (Produto produto : produtos) {

			int updated = jdbcTemplate.update(UPDATE_PRODUTO,
					produto.getNome(), produto.getQuantidadeDepo(),
					produto.getValorCompra(), produto.getValorVenda(),
					produto.getPontoReposicao(), produto.getQuantMaxGondola(),
					produto.getQuantMinGondola(), produto.getOid());

			if (updated == 0) {

				jdbcTemplate.update(INSERT_PRODUTO, produto.getOid(),
						produto.getNome(), produto.getQuantidadeDepo(),
						produto.getValorCompra(), produto.getValorVenda(),
						produto.getPontoReposicao(),
						produto.getQuantMaxGondola(),
						produto.getQuantMinGondola());
			}
		}
	}

}
