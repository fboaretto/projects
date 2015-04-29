package com.br.projetandoo.vdigital.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.projetandoo.vdigital.model.Fornecedor;

@Repository
public interface FornecedorRepository extends CrudRepository<Fornecedor, Long> {

	Fornecedor findByNome(String nomeFornecedor);

}
