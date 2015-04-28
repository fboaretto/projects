package com.br.projetandoo.vdigital.spring.batch;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.br.projetandoo.vdigital.model.Produto;

public class ProdutoItemWriter implements ItemWriter<Produto> {

	private JdbcTemplate jdbcTemplate;

	private static final String INSERT_PRODUTO = "INSERT INTO produto(id,nome,fornecedor_id)"
			+ "VALUES(?,?,?)";

	/*
	 * "INSERT INTO produto(id,nome,valorCompra,valorVenda,quantMaxDepo," +
	 * "quantMaxLoja,quantAtualDepo,quantAtualLoja,pontoRessuprimento," +
	 * "pontoReposicao)" + "VALUES(?,?,?,?,?,?,?,?,?,?)";
	 */

	private static final String UPDATE_PRODUTO = "UPDATE produto SET nome=?, fornecedor_id=?"
			+ "where id=?"; 
			
			/*"UPDATE produto SET nome=?, valorCompra=?, valorVenda=?, quantMaxDepo=?, "
			+ "quantMaxLoja=?, quantAtualDepo=?, quantAtualLoja=?, pontoRessuprimento=?"
			+ ", pontoReposicao=? where id=?";*/

	public ProdutoItemWriter(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void write(List<? extends Produto> produtos) throws Exception {

		for (Produto produto : produtos) {

			int updated = jdbcTemplate.update(UPDATE_PRODUTO,
					produto.getNome(), produto.getFornecedor(), produto.getId());
					
					/*produto.getNome(), produto.getValorCompra(),
					produto.getValorVenda(), produto.getQuantMaxDepo(),
					produto.getQuantMaxLoja(), produto.getQuantMaxDepo(),
					produto.getQuantAtualLoja(),
					produto.getPontoRessuprimento(),
					produto.getPontoReposicao(), produto.getId());*/

			if (updated == 0) {
				jdbcTemplate.update(INSERT_PRODUTO, produto.getId(), produto.getNome(), produto.getFornecedor());
						/*produto.getNome(), produto.getValorCompra(),
						produto.getValorVenda(), produto.getQuantMaxDepo(),
						produto.getQuantMaxLoja(), produto.getQuantMaxDepo(),
						produto.getQuantAtualLoja(),
						produto.getPontoRessuprimento(),
						produto.getPontoReposicao());*/
			}
		}
	}

}
