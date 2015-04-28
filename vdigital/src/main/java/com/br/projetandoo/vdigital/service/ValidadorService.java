package com.br.projetandoo.vdigital.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.projetandoo.vdigital.model.Produto;
import com.br.projetandoo.vdigital.repository.ProdutoRepository;

@Service
public class ValidadorService {

	@Autowired
	private ProdutoRepository produtoRepository;

	static final String MSG_ESTOQUE_DEPO_BAIXO = "Estoque abaixo do ponto de ressuprimento.";
	static final String MSG_ESTOQUE_LOJA_BAIXO = "Estoque abaixo do ponto de reposição";
	
	static final Logger LOGGER = LoggerFactory.getLogger(ValidadorService.class);


	public void validaPontoRessuprimento(Long id) {

		String mensagem = "";

		Produto produto = produtoRepository.findOne(id);

		int estoqueAtual = produto.getQuantAtualDepo() + produto.getQuantAtualLoja();

		if(estoqueAtual < produto.getPontoRessuprimento()) {
			mensagem = MSG_ESTOQUE_DEPO_BAIXO;
			LOGGER.debug(mensagem);
		}
		else {
			mensagem = ">>>>>>>>>>>>  TUDO EM ORDEM!";
			LOGGER.debug(mensagem);
		}
	}
	
	
	public void validaPontoReposicao(Long id) {

		String mensagem = "";

		Produto produto = produtoRepository.findOne(id);

		int estoqueAtual = produto.getQuantMaxLoja() - produto.getQuantAtualLoja();

		if(estoqueAtual < produto.getPontoReposicao()) {
			mensagem = MSG_ESTOQUE_LOJA_BAIXO;
			LOGGER.debug(mensagem);
		}
		else {
			mensagem = "TUDO EM ORDEM!";
			LOGGER.debug(mensagem);
		}
	}
	
	public void validaPontoCritico(Long id, int valorMax, int valorAtual) {
		
	}
	
}
