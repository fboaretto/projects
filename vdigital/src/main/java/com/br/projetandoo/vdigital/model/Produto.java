package com.br.projetandoo.vdigital.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto /*extends AbstractEntity*/ implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;

	@Column(nullable = false)
	private String nome;

	private BigDecimal valorCompra;

	private BigDecimal valorVenda;

	private Integer quantMaxDepo;

	private Integer quantMaxLoja;

	private Integer quantAtualDepo;

	private Integer quantAtualLoja;

	private Integer pontoRessuprimento;

	private Integer pontoReposicao;
	
	private String nomeFornecedor;


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

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String toString() {
		return "Produto [" + getId() + "]: " + getNome();
	}

}
