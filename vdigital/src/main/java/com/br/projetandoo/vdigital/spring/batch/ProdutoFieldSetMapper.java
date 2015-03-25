package com.br.projetandoo.vdigital.spring.batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.br.projetandoo.vdigital.model.Produto;


public class ProdutoFieldSetMapper implements FieldSetMapper<Produto> {
	
	public Produto mapFieldSet(FieldSet fieldSetParam) throws BindException {
		
		Produto produto = new Produto();
		
		produto.setOid(fieldSetParam.readInt("oid"));
		produto.setNome(fieldSetParam.readString("nome"));
		produto.setQuantidadeDepo(fieldSetParam.readInt("quantidadeDepo"));
		produto.setValorCompra(fieldSetParam.readBigDecimal("valorCompra"));
		produto.setValorCompra(fieldSetParam.readBigDecimal("valorVenda"));
		produto.setPontoReposicao(fieldSetParam.readInt("pontoReposicao"));
		produto.setQuantMaxGondola(fieldSetParam.readInt("quantMaxGondola"));
		produto.setQuantMinGondola(fieldSetParam.readInt("quantMinGondola"));
		
		return produto;
	}

}
