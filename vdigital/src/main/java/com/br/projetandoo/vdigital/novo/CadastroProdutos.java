package com.br.projetandoo.vdigital.novo;

import java.util.List;

import com.br.projetandoo.vdigital.model.Produto;
import com.br.projetandoo.vdigital.repository.ProdutoRepositoryImpl;

public class CadastroProdutos {
	
	private ProdutoRepositoryImpl produtoRepositoryImpl;
	
	public void salvaListaProdutos(List<Produto> produtosFornecedores) {
		
		for (Produto produto : produtosFornecedores) {
			
			//TODO verificar se o fornecedor setado no produto existe;
			// se não existe, criar
			// seta o fornecedor no produto
			
			produtoRepositoryImpl.save(produto);
		}
		
	}
	
}
