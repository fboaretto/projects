package com.br.projetandoo.vdigital.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	@Column(name="produto_oid")
	private Integer oid;
	
	private String nome;
	
	private Integer quantidadeDepo;

	private BigDecimal valorCompra;
	
	private BigDecimal valorVenda;
	
	private Integer pontoReposicao;
	
	private Integer quantMaxGondola;
	
	private Integer quantMinGondola;


	public String toString() {
		return "Produto [" + getOid() + "]: " + getNome();
	}

	
	public Integer getOid() {
		return oid;
	}


	public void setOid(Integer oid) {
		this.oid = oid;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Integer getQuantidadeDepo() {
		return quantidadeDepo;
	}


	public void setQuantidadeDepo(Integer quantidadeDepo) {
		this.quantidadeDepo = quantidadeDepo;
	}


	public BigDecimal getValorCompra() {
		return valorCompra;
	}


	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}


	public BigDecimal getValorVenda() {
		return valorVenda;
	}


	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}


	public Integer getPontoReposicao() {
		return pontoReposicao;
	}


	public void setPontoReposicao(Integer pontoReposicao) {
		this.pontoReposicao = pontoReposicao;
	}


	public Integer getQuantMaxGondola() {
		return quantMaxGondola;
	}


	public void setQuantMaxGondola(Integer quantMaxGondola) {
		this.quantMaxGondola = quantMaxGondola;
	}


	public Integer getQuantMinGondola() {
		return quantMinGondola;
	}


	public void setQuantMinGondola(Integer quantMinGondola) {
		this.quantMinGondola = quantMinGondola;
	}

}
