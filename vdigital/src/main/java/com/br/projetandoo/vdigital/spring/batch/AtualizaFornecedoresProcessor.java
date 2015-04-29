package com.br.projetandoo.vdigital.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.br.projetandoo.vdigital.model.Fornecedor;
import com.br.projetandoo.vdigital.model.Produto;
import com.br.projetandoo.vdigital.repository.FornecedorRepository;

public class AtualizaFornecedoresProcessor implements ItemProcessor<Produto, Produto> {
	
	private static final Logger LOG = LoggerFactory.getLogger(AtualizaFornecedoresProcessor.class);
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	
	public Produto process(Produto produto) throws Exception {
		
		Fornecedor fornecedor = fornecedorRepository.findByNome(produto.getNomeFornecedor());
		
		if(fornecedor == null) {
			
			Fornecedor novoFornecedor = new Fornecedor();
			novoFornecedor.setNome(produto.getNomeFornecedor());
			
			fornecedorRepository.save(novoFornecedor);
			fornecedor = fornecedorRepository.findByNome(produto.getNomeFornecedor());
		}
		
		produto.setFornecedor(fornecedor);
		
		return produto;
	}

}
