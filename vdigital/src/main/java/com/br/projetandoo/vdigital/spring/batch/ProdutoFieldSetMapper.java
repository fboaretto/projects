package com.br.projetandoo.vdigital.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.br.projetandoo.vdigital.model.Produto;


public class ProdutoFieldSetMapper implements FieldSetMapper<Produto> {
	
	private static final Logger LOG = LoggerFactory.getLogger(ImportaProdutosTest.class);
	
	public Produto mapFieldSet(FieldSet fieldSetParam) throws BindException {
		
		Produto produto = new Produto();
		
		try {
			produto.setOid(fieldSetParam.readInt("oid"));
			produto.setNome(fieldSetParam.readString("nome"));
			produto.setQuantidadeDepo(fieldSetParam.readInt("quantidadeDepo"));
			produto.setValorCompra(fieldSetParam.readBigDecimal("valorCompra"));
			produto.setValorCompra(fieldSetParam.readBigDecimal("valorVenda"));
			produto.setPontoReposicao(fieldSetParam.readInt("pontoReposicao"));
			produto.setQuantMaxGondola(fieldSetParam.readInt("quantMaxGondola"));
			produto.setQuantMinGondola(fieldSetParam.readInt("quantMinGondola"));
		
		} catch (FlatFileParseException ffpe) {
			LOG.debug("****************************************** ERRO> " + ffpe);
			//ffpe.printStackTrace();
		}
		
		return produto;
	}

}
