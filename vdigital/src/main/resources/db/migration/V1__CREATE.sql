create table Produto(
	produto_oid int NOT NULL,
	nome varchar(60) NOT NULL,
	quantidadeDepo int,
	valorCompra decimal,
	valorVenda decimal,
	pontoReposicao int,
	quantMaxGondola int,
	quantMinGondola int,
	PRIMARY KEY (produto_oid)
	);
