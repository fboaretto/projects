package com.br.projetandoo.vdigital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//Importa configs de banco de dados, hibernate, repositórios e serviços.
@Configuration
@Import( {DataSourceConfig.class, JpaConfig.class, RepositoryConfig.class, ServiceConfig.class} )
public class MainConfig {}