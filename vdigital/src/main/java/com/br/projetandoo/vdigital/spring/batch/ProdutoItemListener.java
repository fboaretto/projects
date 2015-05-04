package com.br.projetandoo.vdigital.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.listener.ItemListenerSupport;
import org.springframework.batch.item.file.FlatFileParseException;

import com.br.projetandoo.vdigital.model.Produto;

public class ProdutoItemListener extends ItemListenerSupport<Produto, Produto> {
	
	private Logger logger = LoggerFactory.getLogger(ProdutoItemListener.class);
	
	@Override
	public void onReadError(Exception e) {
		
		if(e instanceof FlatFileParseException) {
			FlatFileParseException ffpe = (FlatFileParseException) e;
			
			StringBuilder mensagemErro = new StringBuilder();
			mensagemErro.append("Um erro ocorreu processando a linha " + ffpe.getLineNumber() + "\n");
			logger.error(mensagemErro.toString(), ffpe);
		}
		else {
			logger.error("Um erro ocorreu: ", e);
		}
	}
	
	@Override
	public void onProcessError(Produto item, Exception e) {
		
		if(e instanceof NullPointerException) {
			NullPointerException npe = (NullPointerException) e;
			
			StringBuilder mensagemErro = new StringBuilder();
			mensagemErro.append(">>>>>>>>>>>>>>>>>Um erro ocorreu processando o item: [" + item.getCodigo() + "] : " + item.getNome());
			logger.error(mensagemErro.toString(), npe);
		}
		else {
			logger.error("Um erro ocorreu: ", e);
		}
	}

}
