package com.br.projetandoo.vdigital.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//Importa configs de banco de dados, hibernate e repositórios.
@Configuration
@Import( {DataSourceConfig.class, JpaConfig.class, RepositoryConfig.class} )
public class Config {}