package com.br.projetandoo.vdigital.novo;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LeitorArquivoTest {
	
	@Test
	public void testLeitorArquivo_deveRecuperarTodosItems() throws IOException {
		
		String arquivo1 = "/home/fboaretto/git/vdigital/vdigital/src/main/resources/novo/produtosFornecedores_teste.txt";
		
		LeitorArquivoProdutosFornecedores leitor = new LeitorArquivoProdutosFornecedores();
		leitor.leArquivo(arquivo1);
		
		assertEquals(leitor.getProdutos().size(), 153);
	}
	
	

}
