package com.br.projetandoo.vdigital.novo.importaDadosArquivos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.br.projetandoo.vdigital.model.Produto;

public class LeitorArquivoProdutosPrecos {

	private static final String PREFIX_PRODUTO_INVALIDO = "SALDO DE BALANCO";
	private static final String PREFIX_MARKAO = "MARKAO";
	private List<Produto> produtos = new ArrayList<Produto>();
	
	public List<Produto> leArquivo(String arquivo) throws IOException {
		
		BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));

		String linha = buffReader.readLine();

		//Ignorando header inicial
		for (int i = 1; i <= 10; i++) {
			linha = buffReader.readLine();
		}
		
		while (linha != null) {
			
			if (linha.contains(PREFIX_MARKAO)) {
				for (int i = 1; i <= 4; i++) {
					linha = buffReader.readLine();
				}
			}
			if (linha.startsWith(PREFIX_PRODUTO_INVALIDO)) {
				System.out.println("SALDO DE BALANCO ...próx linha");
				linha = buffReader.readLine();
				continue;
			}
			
			String nome = linha.substring(0, 41).trim();
			String codigo = linha.substring(43, 50).trim();
			String volume = linha.substring(64, 70).trim();
			
			String valorVendaVolume = linha.substring(89, 98).trim().replace(",", ".");
			String valorCustoUnidade = linha.substring(116, 124).trim().replace(",", ".");

			System.out.println(nome + "\t" + codigo + "\t[" + volume + "]\t" + valorVendaVolume + "\t" + valorCustoUnidade);
			
			Produto produto = atualizaValoresProduto(nome, codigo, volume, valorVendaVolume, valorCustoUnidade);
			
			produtos.add(produto);
			linha = buffReader.readLine();
		}

		buffReader.close();
		return produtos;
	}
	
	
	public Produto atualizaValoresProduto(String nome, String codigo, String volume, 
			String valorVendaVolume, String valorCustoUnidade) {

		Produto produto = new Produto();

		produto.setNome(nome);
		produto.setCodigo(codigo);
		
		int volumeInt = Integer.parseInt(volume);

		if (valorVendaVolume.startsWith("."))
			valorVendaVolume = "0".concat(valorVendaVolume);
		
		BigDecimal valorVendaVolumeBD = new BigDecimal(valorVendaVolume);
		BigDecimal valorVendaUnidade = valorVendaVolumeBD.divide(new BigDecimal(volumeInt));
		produto.setValorVenda(valorVendaUnidade);
		
		if (valorCustoUnidade.startsWith("."))
			valorCustoUnidade = "0".concat(valorCustoUnidade);
		
		produto.setValorCusto(new BigDecimal(valorCustoUnidade));

		return produto;
	}
	

	public List<Produto> getProdutos() {
		return produtos;
	}
	
}
