package com.br.projetandoo.vdigital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.projetandoo.vdigital.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
