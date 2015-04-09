package com.br.projetandoo.vdigital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages="com.br.projetandoo.vdigital.repository")
public class RepositoryConfig {}