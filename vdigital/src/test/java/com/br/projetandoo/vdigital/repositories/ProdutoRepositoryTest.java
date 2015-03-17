package com.br.projetandoo.vdigital.repositories;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.br.projetandoo.vdigital.entities.Produto;
import com.br.projetandoo.vdigital.repositories.ProdutoRepository;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations="classpath:META-INF/application-context.xml")
@ContextConfiguration(locations="classpath:META-INF/test-context.xml")
public class ProdutoRepositoryTest {

	@Autowired
	ProdutoRepository repository;
	
	@Test
	public void test() {
		Produto produto = new Produto();
		produto.setNome("COCA COLA");
		produto.setValorCompra(BigDecimal.valueOf(2.0));
		//produto.setValorVenda(BigDecimal.valueOf(5.0));
		
		repository.save(produto);
		
		Produto dbproduto = repository.findOne(produto.getOid());
		assertNotNull(dbproduto);
		System.out.println(dbproduto.toString());
	}

}
