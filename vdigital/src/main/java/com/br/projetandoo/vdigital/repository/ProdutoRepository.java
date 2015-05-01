package com.br.projetandoo.vdigital.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.projetandoo.vdigital.model.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long>, ProdutoRepositoryCustom {

	public List<Produto> findByFornecedor(Long fornecedor_id);

	public Produto findByNome(String nomeProduto);
	
	public Produto findByCodigo(String codigo);

}
