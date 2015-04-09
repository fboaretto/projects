package com.br.projetandoo.vdigital.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.br.projetandoo.vdigital.config.Config;
import com.br.projetandoo.vdigital.model.Produto;
import com.br.projetandoo.vdigital.repository.ProdutoRepository;

@Component
//@ContextConfiguration(classes={Config.class})
public class Validador {

	@Autowired
	private static ProdutoRepository produtoRepository;
	
	public Validador() {
		super();
	}

	static final Logger LOGGER = LoggerFactory.getLogger(Validador.class);
	
	
    
	public static void main(String[] args){
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
		produtoRepository = ctx.getBean(ProdutoRepository.class);
		
		String mensagem = "";
		
		Produto produto = produtoRepository.findByNome("Coca-Cola(1L)");
		int estoqueAtual = produto.getQuantAtualDepo() + produto.getQuantAtualLoja();
		
		if(estoqueAtual < produto.getPontoRessuprimento()) {
			mensagem = "ESTOQUE ABAIXO DO PONTO DE RESSUPRIMENTO.";
			LOGGER.debug(mensagem);
		}
		else {
			mensagem = "TUDO EM ORDEM!";
			LOGGER.debug(mensagem);
		}
	}

}
