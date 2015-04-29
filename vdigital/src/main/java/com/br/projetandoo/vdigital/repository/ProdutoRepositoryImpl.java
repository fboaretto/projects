package com.br.projetandoo.vdigital.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.br.projetandoo.vdigital.model.Fornecedor;
import com.br.projetandoo.vdigital.model.Produto;

public class ProdutoRepositoryImpl implements ProdutoRepositoryCustom{

	private static final Logger LOG = LoggerFactory.getLogger(ProdutoRepositoryImpl.class);
	
	@Autowired
	private FornecedorRepository fornecedorRepository; 
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	public ProdutoRepositoryImpl() {}
	
	public ProdutoRepositoryImpl(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	@Override
	public Produto save(Produto produto) {
		
		Fornecedor fornecedor = fornecedorRepository.findByNome(produto.getNomeFornecedor());
		
		if(fornecedor == null) {
			Fornecedor novoFornecedor = new Fornecedor();
			novoFornecedor.setNome(produto.getNomeFornecedor());
			
			fornecedorRepository.save(novoFornecedor);
			fornecedor = fornecedorRepository.findByNome(produto.getNomeFornecedor());
			
			LOG.debug("########################################### FORNECEDOR: " + fornecedor.getId().toString());
		}
		
		produto.setFornecedor(fornecedor);
		
		LOG.debug("########################################### PRODUTO: " + produto.getId().toString());
		
		this.produtoRepository.save(produto);
		
		return produto;
	}

	

}
