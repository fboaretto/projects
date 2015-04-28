package com.br.projetandoo.vdigital.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.br.projetandoo.vdigital.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Long>, ProdutoRepositoryCustom {
	
	List<Produto> findByFornecedor(Long fornecedor_id);
	
}
