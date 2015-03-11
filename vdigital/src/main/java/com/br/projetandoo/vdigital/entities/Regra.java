package com.br.projetandoo.vdigital.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Regra {

	@Id
	private Long id;
	
	private String descricao;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
