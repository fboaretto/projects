package com.br.projetandoo.vdigital.novo.importaDadosArquivos;

import java.io.IOException;
import java.util.List;

import com.br.projetandoo.vdigital.model.Produto;

public class ProcessadorArquivosProdutos {

	public static void main(String[] args) throws IOException {

		//	/home/fboaretto/git/vdigital/vdigital/src/main/resources/novo/produtosFornecedores_teste.txt
		//	/home/fboaretto/git/vdigital/vdigital/src/main/resources/novo/produtosPrecos_teste.txt
		//	/home/fboaretto/Documentos/Projetandoo/arquivosImportacao/relatoriodeprodutoscadastradoseestoque/CW000200.txt
		//	/home/fboaretto/Documentos/Projetandoo/arquivosImportacao/relatoriodeprodutoscadastradoseestoque/sp001148.txt
		
		//String arquivoCargaProdutoFornecedores = 
		//		"/home/fboaretto/Documentos/Projetandoo/arquivosImportacao/relatoriodeprodutoscadastradoseestoque/CW000200.txt";
		
		String arquivoCargaPrecos = 
				"/home/fboaretto/Documentos/Projetandoo/arquivosImportacao/relatoriodeprodutoscadastradoseestoque/sp001148.txt";

		//Primeiro Arquivo
		/*LeitorArquivoProdutosFornecedores leitorProdutos = new LeitorArquivoProdutosFornecedores();
		List<Produto> produtosFornecedores = leitorProdutos.leArquivo(arquivoCargaProdutoFornecedores);
		System.out.println("TOTAL :::::: " + produtosFornecedores.size());
		System.out.println("Imprimindo mix:\n");
		for (Produto produto : produtosFornecedores) {
			System.out.println(produto);
		}*/
		
		//Segundo Arquivo
		LeitorArquivoProdutosPrecos leitorPrecos = new LeitorArquivoProdutosPrecos();
		List<Produto> produtosPrecos = leitorPrecos.leArquivo(arquivoCargaPrecos);
		System.out.println("TOTAL :::::: " + produtosPrecos.size());
		System.out.println("Imprimindo mix:\n");
		for (Produto produto : produtosPrecos) {
			System.out.println(produto);
		}

	}

}
