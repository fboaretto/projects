package com.br.projetandoo.vdigital.spring.batch;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.br.projetandoo.vdigital.model.Produto;

public class ProdutoPrecosFieldSetMapper implements FieldSetMapper<Produto> {

	private static final Logger LOG = LoggerFactory.getLogger(ProdutoPrecosFieldSetMapper.class);

	public Produto mapFieldSet(FieldSet fsParam) throws BindException {

		Produto produto = new Produto();

		try {
			produto.setNome(fsParam.readString("nome"));
			produto.setCodigo(fsParam.readString("codigo"));
			
			String valorVenda = fsParam.readString("valorVenda").trim().replace(",", ".");
			String valorCusto = fsParam.readString("valorCusto").trim().replace(",", ".");
			
			produto.setVolume(Integer.parseInt(fsParam.readString("volume").trim()));
			produto.setValorVenda(new BigDecimal(valorVenda));
			produto.setValorCusto(new BigDecimal(valorCusto));
			
		} catch (FlatFileParseException ffpe) {
			LOG.debug("*************************** ERRO> " + ffpe);
		}

		LOG.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ SETMAPPER: "
				+ produto.getNome() + "   " + produto.getCodigo() + "   "
				+ produto.getValorVenda() + "   " + produto.getValorCusto());
				
		return produto;
	}

}
