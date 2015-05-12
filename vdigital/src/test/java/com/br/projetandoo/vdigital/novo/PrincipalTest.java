package com.br.projetandoo.vdigital.novo;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.br.projetandoo.vdigital.model.Produto;

public class PrincipalTest extends TestCase{
	
	@Before
	public void setup() throws Exception{
		
	}
	
	@Test
	public void testDeveLerArquivoDeCargaProdutosFornecedores() throws IOException {
		
		String arquivo1 = "/home/fboaretto/git/vdigital/vdigital/src/main/resources/novo/produtosFornecedores_teste.txt";
		
		LeitorArquivoProdutosFornecedores leitor = new LeitorArquivoProdutosFornecedores();
		leitor.leArquivo(arquivo1);
		
		assertEquals(leitor.getProdutos().size(), 153);
	}
	
	@Test
	public void testDeveLerArquivoDeCargaProdutosFornecedores_salvaItemsLidos() throws IOException {
		
		String arquivo1 = "/home/fboaretto/git/vdigital/vdigital/src/main/resources/novo/produtosFornecedores_teste.txt";
		
		LeitorArquivoProdutosFornecedores leitor = new LeitorArquivoProdutosFornecedores();
		List<Produto> produtosFornecedores = leitor.leArquivo(arquivo1);
		
		assertEquals(produtosFornecedores.size(), 153);

		
		CadastroProdutos cadastroProdutoFornecedores = new CadastroProdutos();
		
		//mockar metodo void :(
		//melhor testar a classe Cadastro futuramente :)
		cadastroProdutoFornecedores.salvaListaProdutos(produtosFornecedores);
		
	}
	
}

