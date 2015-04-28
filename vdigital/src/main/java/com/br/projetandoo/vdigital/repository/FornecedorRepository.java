package com.br.projetandoo.vdigital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.projetandoo.vdigital.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

	Fornecedor findByNome(String nomeFornecedor);

}
