package com.br.projetandoo.vdigital.spring.batch;

import java.math.BigDecimal;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.br.projetandoo.vdigital.model.Produto;
import com.br.projetandoo.vdigital.repository.ProdutoRepository;

public class ProdutoItemProcessor implements ItemProcessor<Produto, Produto> {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public Produto process(Produto produto) throws Exception {

		Produto produtoPesquisado = produtoRepository.findByCodigo(produto.getCodigo());
		produto.setFornecedor(produtoPesquisado.getFornecedor());
		
		BigDecimal valorVolume = produto.getValorVenda();
		int volume = produto.getVolume();

		BigDecimal valorUnidade = valorVolume.divide(new BigDecimal(volume));

		produto.setValorVenda(valorUnidade);

		return produto;
	}

}
