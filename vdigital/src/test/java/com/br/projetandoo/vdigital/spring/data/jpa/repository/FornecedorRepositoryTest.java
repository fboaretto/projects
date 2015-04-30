package com.br.projetandoo.vdigital.spring.data.jpa.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.br.projetandoo.vdigital.config.MainConfig;
import com.br.projetandoo.vdigital.model.Fornecedor;
import com.br.projetandoo.vdigital.model.Produto;
import com.br.projetandoo.vdigital.repository.FornecedorRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={MainConfig.class})
@Transactional
public class FornecedorRepositoryTest {
	
	private static final String COCA_COLA_LTDA = "COCA-COLA LTDA";
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Test
	public void test() {
		
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome(COCA_COLA_LTDA);
		
		Produto p1 = new Produto();
		p1.setNome("Coca Cola Litrao");
		p1.setCodigo("000000");
		p1.setFornecedor(fornecedor);
		
		Produto p2 = new Produto();
		p2.setNome("Coca Cola Litrim");
		p2.setCodigo("111111");
		p2.setFornecedor(fornecedor);
		
		List<Produto> produtos = new ArrayList<Produto>();
		produtos.add(p1);
		produtos.add(p2);
		
		fornecedor.setProdutos(produtos);
		fornecedor = this.fornecedorRepository.save(fornecedor);
		assertNotNull(fornecedor.getId());
		
		fornecedor = this.fornecedorRepository.findOne(fornecedor.getId());
		assertNotNull(fornecedor);
		assertEquals(COCA_COLA_LTDA, fornecedor.getNome());
		assertEquals(2, fornecedor.getProdutos().size());
		
	}

}
