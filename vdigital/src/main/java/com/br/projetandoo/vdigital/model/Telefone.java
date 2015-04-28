package com.br.projetandoo.vdigital.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "telefone")
public class Telefone /*extends AbstractEntity*/ implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	//@Column(nullable = false)
	private String ddd;

	//@Column(nullable = false)
	private String telefone;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
