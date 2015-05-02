package com.br.projetandoo.vdigital.spring.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.br.projetandoo.vdigital.model.Fornecedor;
import com.br.projetandoo.vdigital.model.Produto;
import com.br.projetandoo.vdigital.repository.FornecedorRepository;

public class AtualizaFornecedoresProcessor implements ItemProcessor<Produto, Produto> {

	@Autowired
	private FornecedorRepository fornecedorRepository;


	public Produto process(Produto produto) throws Exception {

		Fornecedor fornecedor = fornecedorRepository.findByNome(produto.getNomeFornecedor());

		if(fornecedor == null) {

			Fornecedor novoFornecedor = new Fornecedor();
			novoFornecedor.setNome(produto.getNomeFornecedor());

			fornecedorRepository.save(novoFornecedor);
			fornecedor = fornecedorRepository.findByNome(produto.getNomeFornecedor());
		}

		produto.setFornecedor(fornecedor);

		return produto;
	}

}
