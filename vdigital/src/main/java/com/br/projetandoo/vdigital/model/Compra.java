package com.br.projetandoo.vdigital.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

public class Compra {

	private Long id;

	private Date dataCompra;

	private String numero;

	private String tipoOperacao;

	private int caixa;

	private BigDecimal total;

	private Collection<ItemCompra> items = new HashSet<ItemCompra>();


	@Override
	public String toString() {
		return "Compra [id=" + id + ", dataCompra=" + dataCompra + ", numero="
				+ numero + ", tipoOperacao=" + tipoOperacao + ", caixa="
				+ caixa + ", total=" + total + ", items=" + items + "]";
	}


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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
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
