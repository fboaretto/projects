package com.br.projetandoo.vdigital.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.br.projetandoo.vdigital.model.Produto;

public class ProdutoFornecedorFieldSetMapper implements FieldSetMapper<Produto> {

	private static final Logger LOG = LoggerFactory.getLogger(ProdutoFornecedorFieldSetMapper.class);

	public Produto mapFieldSet(FieldSet fsParam) throws BindException {

		Produto produto = new Produto();

		try {
			produto.setNome(fsParam.readString("nome"));
			produto.setCodigo(fsParam.readString("codigo"));

			if(fsParam.readString("estoque").isEmpty()) {
				produto.setEstoque(Integer.valueOf(0));
			}
			else
				produto.setEstoque(Integer.parseInt(fsParam.readString("estoque").trim()));

			if(fsParam.readString("codigoBarra").isEmpty()) {
				produto.setCodigoBarra(null);
			}
			else
				produto.setCodigoBarra(fsParam.readString("codigoBarra").trim());

			produto.setNomeFornecedor(fsParam.readString("nomeFornecedor"));

		} catch (FlatFileParseException ffpe) {
			LOG.debug("*************************** ERRO> " + ffpe);
		}

		LOG.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ SETMAPPER: "
				+ produto.getNome() + "   " + produto.getCodigo() + "   "
				+ produto.getNomeFornecedor());

		return produto;
	}

}
