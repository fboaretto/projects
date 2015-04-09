package com.br.projetandoo.vdigital.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="produto_oid", unique=true, nullable=false)
	private Long oid;
	
	private String nome;
	
	private BigDecimal valorCompra;

	private BigDecimal valorVenda;
	
	private Integer quantMaxDepo;
	
	private Integer quantMaxLoja;
	
	private Integer quantAtualDepo;
	
	private Integer quantAtualLoja;
	
	private Integer pontoRessuprimento;
	
	private Integer pontoReposicao;


	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Integer getQuantMaxDepo() {
		return quantMaxDepo;
	}

	public void setQuantMaxDepo(Integer quantMaxDepo) {
		this.quantMaxDepo = quantMaxDepo;
	}

	public Integer getQuantMaxLoja() {
		return quantMaxLoja;
	}

	public void setQuantMaxLoja(Integer quantMaxLoja) {
		this.quantMaxLoja = quantMaxLoja;
	}

	public Integer getQuantAtualDepo() {
		return quantAtualDepo;
	}

	public void setQuantAtualDepo(Integer quantAtualDepo) {
		this.quantAtualDepo = quantAtualDepo;
	}

	public Integer getQuantAtualLoja() {
		return quantAtualLoja;
	}

	public void setQuantAtualLoja(Integer quantAtualLoja) {
		this.quantAtualLoja = quantAtualLoja;
	}

	public Integer getPontoReposicao() {
		return pontoReposicao;
	}

	public void setPontoReposicao(Integer pontoReposicao) {
		this.pontoReposicao = pontoReposicao;
	}
	
	public Integer getPontoRessuprimento() {
		return pontoRessuprimento;
	}

	public void setPontoRessuprimento(Integer pontoRessuprimento) {
		this.pontoRessuprimento = pontoRessuprimento;
	}

	public String toString() {
		return "Produto [" + getOid() + "]: " + getNome();
	}

}
