package com.br.projetandoo.vdigital.config;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/META-INF/data/hibernate.properties")
public class JpaConfig {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		
        HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
        hibernateAdapter.setDatabasePlatform(env.getProperty("hibernate.dialect"));
        hibernateAdapter.setShowSql(env.getProperty("hibernate.show_sql", Boolean.class));
		
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setPackagesToScan("com.br.projetandoo.vdigital.model");
		emf.setJpaVendorAdapter(hibernateAdapter);
        emf.setJpaPropertyMap(Collections.singletonMap("javax.persistence.validation.mode", "none"));

		return emf;
	}

	@Bean
    public JpaTransactionManager transactionManager() {
        
		JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }

}
