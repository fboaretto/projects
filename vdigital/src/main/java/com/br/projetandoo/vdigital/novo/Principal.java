package com.br.projetandoo.vdigital.novo;

import java.io.IOException;
import java.util.List;

import com.br.projetandoo.vdigital.model.Produto;

public class Principal {

	public static void main(String[] args) throws IOException {
		
		String arquivoCargaProdutoFornecedores = args[0];
		String arquivoCargaPrecos = args[1];
		
		//Primeiro Arquivo
		LeitorArquivo leitorProdutos = new LeitorArquivoProdutosFornecedores();
		List<Produto> produtosFornecedores = leitorProdutos.leArquivo(arquivoCargaProdutoFornecedores);
		CadastroProdutos cadastro = new CadastroProdutos();
		cadastro.salvaListaProdutos(produtosFornecedores);
		
		//Segundo Arquivo
		LeitorArquivo leitorPrecos = new LeitorArquivoProdutosPrecos();
		List<Produto> produtosPrecos = leitorPrecos.leArquivo(arquivoCargaPrecos);
		
		//Terceiro Arquivo ..
		
		//Serviço de regras ..
		
	}

	
}
