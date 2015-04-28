package com.br.projetandoo.vdigital.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.br.projetandoo.vdigital.model.Produto;

public class ProdutoFieldSetMapper implements FieldSetMapper<Produto> {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProdutoFieldSetMapper.class);

	public Produto mapFieldSet(FieldSet fieldSetParam) throws BindException {

		Produto produto = new Produto();

		try {
			produto.setNome(fieldSetParam.readString("nome"));
			produto.setId(fieldSetParam.readLong("id"));
			produto.setNomeFornecedor(fieldSetParam.readString("nomeFornecedor"));
			/*
			 * produto.setValorCompra(fieldSetParam.readBigDecimal("valorCompra")
			 * );
			 * produto.setValorVenda(fieldSetParam.readBigDecimal("valorVenda"
			 * ));
			 * produto.setQuantMaxDepo(fieldSetParam.readInt("quantMaxDepo"));
			 * produto.setQuantMaxLoja(fieldSetParam.readInt("quantMaxLoja"));
			 * produto
			 * .setQuantAtualDepo(fieldSetParam.readInt("quantAtualDepo"));
			 * produto
			 * .setQuantAtualLoja(fieldSetParam.readInt("quantAtualLoja"));
			 * produto
			 * .setPontoRessuprimento(fieldSetParam.readInt("pontoRessuprimento"
			 * ));
			 * produto.setPontoReposicao(fieldSetParam.readInt("pontoReposicao"
			 * ));
			 */

		} catch (FlatFileParseException ffpe) {
			LOG.debug("****************************************** ERRO> "
					+ ffpe);
		}

		LOG.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$   SETMAPPER: "
				+ produto.getNome() + " " + produto.getId() + " "
				+ produto.getNomeFornecedor());
		
		return produto;
	}

}
