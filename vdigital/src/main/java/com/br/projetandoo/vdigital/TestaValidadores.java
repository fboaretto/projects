package com.br.projetandoo.vdigital;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.br.projetandoo.vdigital.config.Config;
import com.br.projetandoo.vdigital.service.ValidadorService;

@Component
public class TestaValidadores {

	@Autowired
	private static ValidadorService validador;
	
	final static Long produto_oid = (long) 1001;

	@SuppressWarnings("resource")
	public static void main(final String[] args) {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

		//necessario para encerrar o container em aplicacoes desktop
		context.registerShutdownHook();

		validador = context.getBean(ValidadorService.class);
		validador.validaPontoRessuprimento(produto_oid);
		validador.validaPontoReposicao(produto_oid);
		
	}

}
