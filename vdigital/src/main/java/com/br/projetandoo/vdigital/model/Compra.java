package com.br.projetandoo.vdigital.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class Compra {
	
	private Long id;
	
	private Date dataCompra;
	
	private int numero;
	
	private String tipoOperacao;
	
	private int caixa;
	
	private BigDecimal total;
	
	private Collection<ItemCompra> items = new HashSet<ItemCompra>();
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTipoOperacao() {
		return tipoOperacao;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Collection<ItemCompra> getItems() {
		return items;
	}

	public void setItems(Collection<ItemCompra> items) {
		this.items = items;
	}

	public int getCaixa() {
		return caixa;
	}

	public void setCaixa(int caixa) {
		this.caixa = caixa;
	}
	
}
