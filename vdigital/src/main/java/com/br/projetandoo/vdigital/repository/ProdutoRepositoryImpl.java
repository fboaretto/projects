package com.br.projetandoo.vdigital.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.projetandoo.vdigital.model.Produto;

public class ProdutoRepositoryImpl implements ProdutoRepositoryCustom{

	@PersistenceContext
	protected EntityManager entityManager;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Produto save(Produto produto) {

		Produto produtoPesquisado = produtoRepository.findByCodigo(produto.getCodigo());

		if(produtoPesquisado == null) {
			this.entityManager.persist(produto);
			return produto;
		}
		else {
			produto.setId(produtoPesquisado.getId());
			return this.entityManager.merge(produto);
		}
	}
}
