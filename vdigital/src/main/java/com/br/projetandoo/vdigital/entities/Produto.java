package com.br.projetandoo.vdigital.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String nome;

	private String sku;

	private BigDecimal valorUnitario;

	private Integer estoque;

	private Integer gondola;

	public String toString() {
		return "Produto " + getId() + " : " + getNome() + " [" + getSku()
				+ "] quant.estoque:" + getEstoque() + " quant.gondola:"
				+ getGondola();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public Integer getGondola() {
		return gondola;
	}

	public void setGondola(Integer gondola) {
		this.gondola = gondola;
	}

	public Integer getId() {
		return id;
	}

}
