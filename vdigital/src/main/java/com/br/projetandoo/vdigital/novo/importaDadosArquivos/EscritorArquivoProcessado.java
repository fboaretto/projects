package com.br.projetandoo.vdigital.novo.importaDadosArquivos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.commons.lang3.StringUtils;

public class EscritorArquivoProcessado {

	private static final String PREFIX_CABECALHO_ITEMCOMPRA = "Descricao";
	private static final String PREFIX_CABECALHO_COMPRA		= " Numero";
	private static final String PREFIX_FIM_ARQUIVO 			= "-------";
	private static final String PREFIX_MARKAO 				= "MARKAO";

	public void processaArquivo(String arquivo) throws IOException {

		FileWriter fWriter = new FileWriter("/home/fboaretto/git/vdigital/vdigital/src/main/resources/novo/vendas_processadas.txt");
		PrintWriter pWriter = new PrintWriter(fWriter);

		BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));

		String linha = buffReader.readLine();
		linha = buffReader.readLine();

		//split na segunda linha para capturar dia e hora das compras:
		String[] items = StringUtils.split(linha);
		pWriter.println(items[2] + " " + items[3]);

		//Ignorando header inicial
		for (int i = 1; i <= 11; i++) {
			linha = buffReader.readLine();
		}

		while (linha != null) {

			if (linha.contains(PREFIX_MARKAO)) {
				for (int i = 1; i <= 6; i++)
					linha = buffReader.readLine();
			}

			if (linha.startsWith(PREFIX_CABECALHO_COMPRA)) {

				linha = buffReader.readLine();

				if (linha.contains(PREFIX_MARKAO)) {
					for (int i = 1; i <= 6; i++)
						linha = buffReader.readLine();
				}
			}

			if (linha.startsWith(PREFIX_CABECALHO_ITEMCOMPRA)) {

				linha = buffReader.readLine();

				if (linha.contains(PREFIX_MARKAO)) {
					for (int i = 1; i <= 6; i++)
						linha = buffReader.readLine();
				}
			}

			if (linha.startsWith(PREFIX_FIM_ARQUIVO)) {
				System.out.println(linha);
				break;
			}
			
			System.out.println(linha);

			pWriter.println(linha);
			linha = buffReader.readLine();
		}

		buffReader.close();
		pWriter.close();
	}

}
