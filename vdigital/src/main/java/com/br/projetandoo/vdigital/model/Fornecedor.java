package com.br.projetandoo.vdigital.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "fornecedor")
public class Fornecedor /*extends AbstractEntity*/ implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Produto> produtos;

	/*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Endereco> enderecos;*/


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	/*public Set<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(Set<Endereco> enderecos) {
		this.enderecos = enderecos;
	}*/

}
