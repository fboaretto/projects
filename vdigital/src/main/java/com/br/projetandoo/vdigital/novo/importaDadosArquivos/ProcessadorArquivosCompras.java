package com.br.projetandoo.vdigital.novo.importaDadosArquivos;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.br.projetandoo.vdigital.model.Compra;
import com.br.projetandoo.vdigital.model.ItemCompra;

public class ProcessadorArquivosCompras {

	public static void main(String[] args) throws IOException {
		
		//String arquivoCargaVendas = 
				//"/home/fboaretto/git/vdigital/vdigital/src/main/resources/novo/vendas_teste.txt";
				//"/home/fboaretto/Documentos/Projetandoo/arquivosImportacao/relatoriodeprodutoscadastradoseestoque/sp001149.txt";
		
		String arquivoProcessado = "/home/fboaretto/git/vdigital/vdigital/src/main/resources/novo/vendas_processadas.txt";
		
		//EscritorArquivoProcessado escritor = new EscritorArquivoProcessado();
		//escritor.processaArquivo(arquivoCargaVendas);
		
		LeitorArquivoCompras leitor = new LeitorArquivoCompras();
		Map<Compra, List<ItemCompra>> comprasMap = leitor.leArquivo(arquivoProcessado);
		
		System.out.println(">>>>>>>>>>Numero de compras: " + comprasMap.size());
	}

}
