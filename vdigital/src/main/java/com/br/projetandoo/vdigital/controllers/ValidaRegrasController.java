package com.br.projetandoo.vdigital.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import com.br.projetandoo.vdigital.repositories.ProdutoRepository;

public class ValidaRegrasController {

	@Autowired
	ProdutoRepository repository;
	
	public String validaQuantidadeDepositoAbaixoEsperado(){
		
		return ""; 
	}

}
