package com.br.projetandoo.vdigital.controllers;

public class DefineRegrasController {


	private String regra;


	/*
	 * Guarda as regras definidas pelo usuario num banco dedicado (schema p/
	 * tabela de regras)
	 */

	/*PASSOS
	 * - capturar os parametros fornecidos pelo usuario;
	 * - associar os parametros fornecidos aos atributo da entidade equivalente;  
	 * - montar a regra atraves dos passos acima(query);
	 * - persistir a regra
	 * 
	 * */
	
	public String getRegra() {
		return regra;
	}
	
	public void setRegra(String regra) {
		this.regra = regra;
	}

}
