package com.br.projetandoo.vdigital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.projetandoo.vdigital.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Override
	public Produto findOne(Long id);

	public Produto findByNome(String nomeProduto);

	public Produto findByNomeIgnoreCase(String nomeProduto);

	@Override
	public List<Produto> findAll();

}
