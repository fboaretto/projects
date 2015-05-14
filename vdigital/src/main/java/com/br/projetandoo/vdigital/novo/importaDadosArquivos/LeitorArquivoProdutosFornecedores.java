package com.br.projetandoo.vdigital.novo.importaDadosArquivos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.br.projetandoo.vdigital.model.Produto;

public class LeitorArquivoProdutosFornecedores {

	private static final String PREFIX_CABECALHO        = "\\00\\00";
	private static final String PREFIX_FINAL_ARQUIVO    = "-----";
	private static final String PREFIX_PRODUTO_INVALIDO = " SALDO DE BALANCO";
	
	private List<Produto> produtos = new ArrayList<Produto>();

	public List<Produto> leArquivo(String arquivo) throws IOException {

		BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));

		String linha = buffReader.readLine();

		while (linha != null) {
			//headers
			if (linha.startsWith(PREFIX_CABECALHO)) {
				for (int i = 1; i <= 8; i++) {
					linha = buffReader.readLine();
				}
			}
			if (linha.startsWith(PREFIX_PRODUTO_INVALIDO)) {
				System.out.println("SALDO DE BALANCO ...próx linha");
				linha = buffReader.readLine();
				continue;
			}			
			//footer
			if (linha.startsWith(PREFIX_FINAL_ARQUIVO)) {
				buffReader.close();
				return produtos;
			}
			
			String[] items = StringUtils.split(linha, "|");
			
			String nome = items[0].trim();
			String codigo = items[1].trim();
			String estoque = items[2].substring(0, 6).trim();
			String codBarra = items[7].trim();
			String nomeFornecedor = items[8].trim();
			
			System.out.println(nome + "\t" + codigo + "\t[" + estoque + "]\t[" + codBarra + "]\t" + nomeFornecedor);

			Produto produto = atualizaValoresProduto(nome, codigo, estoque, codBarra, nomeFornecedor);

			produtos.add(produto);
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
