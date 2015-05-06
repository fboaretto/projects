package com.br.projetandoo.vdigital.spring.batch;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.listener.SkipListenerSupport;
import org.springframework.batch.item.file.FlatFileItemWriter;

import com.br.projetandoo.vdigital.model.Produto;

public class ProdutoSkipListener extends SkipListenerSupport<Produto, Object> {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ProdutoSkipListener.class);
	
	private FlatFileItemWriter<Produto> writer;
	
		
	
	@Override
	public void onSkipInProcess(Produto item, Throwable t) {
		
		try {
			List<Produto> produtosNaoCadastrados = new ArrayList<Produto>();
			writer.write(produtosNaoCadastrados);
		} catch (Exception e) {
			
		}
		
	}

}
