package com.br.projetandoo.vdigital.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.br.projetandoo.vdigital.model.Fornecedor;
import com.br.projetandoo.vdigital.model.Produto;
import com.br.projetandoo.vdigital.repository.FornecedorRepository;

public class ProdutoLookupItemProcessor implements ItemProcessor<Produto, Produto> {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProdutoLookupItemProcessor.class);
	
	private FornecedorRepository fornecedorRepository;
	
	public ProdutoLookupItemProcessor(FornecedorRepository fornecedorRepository) {
		this.fornecedorRepository = fornecedorRepository;
	}
	
	public Produto process(Produto produto) throws Exception {
		
		Fornecedor fornecedor = fornecedorRepository.findByNome(produto.getNomeFornecedor());
		
		if(fornecedor == null) {
			Fornecedor novoFornecedor = new Fornecedor();
			novoFornecedor.setNome(produto.getNomeFornecedor());
			
			fornecedorRepository.save(novoFornecedor);
			fornecedor = fornecedorRepository.findByNome(produto.getNomeFornecedor());
			
			LOG.debug("########################################### ITEM PORCESSOR: " + fornecedor.getId().toString());
		}
		
		produto.setFornecedor(fornecedor);
		
		LOG.debug("########################################### ITEM PORCESSOR: " + produto.getId().toString());
		
		return produto;
	}

}
