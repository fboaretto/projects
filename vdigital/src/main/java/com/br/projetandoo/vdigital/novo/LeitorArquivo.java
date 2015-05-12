package com.br.projetandoo.vdigital.novo;

import java.io.IOException;
import java.util.List;

import com.br.projetandoo.vdigital.model.Produto;

public interface LeitorArquivo {
	
	public List<Produto> leArquivo(String arquivo) throws IOException;

}
