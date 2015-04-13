package com.br.projetandoo.vdigital.spring.data.jpa.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.br.projetandoo.vdigital.config.MainConfig;
import com.br.projetandoo.vdigital.model.Produto;
import com.br.projetandoo.vdigital.repository.ProdutoRepository;

@ContextConfiguration(classes={MainConfig.class/*, TestConfig.class*/})
//@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class ProdutoRepositoryTest {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	//@Autowired
	//private JdbcOperations jdbcOps;
	
	@Test
	public void testFindByNome() {
		
		Produto produto = produtoRepository.findByNome("Coca-Cola(1L)");
		assertEquals(Long.valueOf(1001), produto.getOid());
		assertEquals("Coca-Cola(1L)",produto.getNome());
	}

}
