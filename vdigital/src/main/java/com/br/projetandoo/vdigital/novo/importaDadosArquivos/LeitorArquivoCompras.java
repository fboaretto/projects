package com.br.projetandoo.vdigital.novo.importaDadosArquivos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.br.projetandoo.vdigital.model.Compra;
import com.br.projetandoo.vdigital.model.ItemCompra;

public class LeitorArquivoCompras {

	private Map<Compra, List<ItemCompra>> compras = new HashMap<Compra, List<ItemCompra>>();

	public Map<Compra, List<ItemCompra>> leArquivo(String arquivo) throws IOException {

		BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(arquivo)));

		String linha = buffReader.readLine();

		//capturando data e hora
		String[] itemsData = StringUtils.split(linha);

		DateTimeFormatter dataFormat = DateTimeFormat.forPattern("dd/MM/yyyy H:mm");
		DateTime dataCompras = dataFormat.parseDateTime(itemsData[0] + " " + itemsData[1]);
		Date dataComprasFormatada = dataCompras.toDate();

		System.out.println(">>>>data das compras: " + dataComprasFormatada);

		while (linha != null) {

			linha = buffReader.readLine();

			String[] dadosCompra = StringUtils.split(linha);
			String numeroCompra = dadosCompra[0];
			String tipoOperacao = dadosCompra[1] + " " + dadosCompra[2];
			String caixa = dadosCompra[3];
			String valorTotal = dadosCompra[4].replace(",", ".");

			Compra compra = new Compra();
			compra.setDataCompra(dataComprasFormatada);
			compra.setNumero(Integer.parseInt(numeroCompra));
			compra.setTipoOperacao(tipoOperacao);
			compra.setCaixa(Integer.parseInt(caixa));
			compra.setTotal(new BigDecimal(valorTotal));

			linha = buffReader.readLine();

			List<ItemCompra> items = new ArrayList<ItemCompra>();
			while(linha != "\n") {
				// CAPTURAR DADOS DE ITEMS E SETAR NA LISTA
				//: nome(Produto), quantidade, preço
			}

			compras.put(compra, items);

			System.out.println(linha);
		}

		buffReader.close();
		return compras;
	}

}
