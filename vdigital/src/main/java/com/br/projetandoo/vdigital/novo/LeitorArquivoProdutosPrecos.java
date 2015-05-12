package com.br.projetandoo.vdigital.novo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.br.projetandoo.vdigital.model.Produto;

public class LeitorArquivoProdutosPrecos implements LeitorArquivo {

	private List<Produto> produtos = new ArrayList<Produto>();
	
	@Override
	public List<Produto> leArquivo(String arquivo) throws IOException {
		
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));

		String linha = buffReader.readLine();

		while (linha != null) {
			
			if (linha.startsWith("MARKAO")) {	//COMO RESOLVER O PROBLEMA DE QUEBRA DE PÁGS?(ARQUIVOS 2 E 3)
				for (int i = 1; i <= 10; i++) {
					linha = buffReader.readLine();
				}
			}
			
			//String nome = linha.substring(1, 41).trim();
			//String codigo = linha.substring(43, 50).trim();
			//String estoque = linha.substring(51, 57).trim();
			//String codBarra = linha.substring(101, 114).trim();
			//String nomeFornecedor = linha.substring(117, 135).trim();

			//System.out.println(nome + "\t" + codigo);
			System.out.println(linha);
			//Produto produto = atualizaValoresProduto(nome, codigo, estoque, codBarra, nomeFornecedor);
			
			//produtos.add(produto);
			linha = buffReader.readLine();
		}

		buffReader.close();
		return produtos;
	}
	
	
	public Produto atualizaValoresProduto(String nome, String codigo, String estoque, 
			String codBarra, String fornecedor) {

		Produto produto = new Produto();

		produto.setNome(nome);
		produto.setCodigo(codigo);

		if (estoque.isEmpty())
			produto.setEstoque(Integer.valueOf(0));
		else
			produto.setEstoque(Integer.parseInt(estoque));

		if (codBarra.isEmpty())
			produto.setCodigoBarra(null);
		else
			produto.setCodigoBarra(codBarra);

		produto.setNomeFornecedor(fornecedor);

		return produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	
}
