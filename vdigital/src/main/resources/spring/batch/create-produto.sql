DROP TABLE if EXISTS Produto;

CREATE TABLE Produto(
	produto_oid BIGINT NOT NULL,
	nome VARCHAR(60) NOT NULL,
	valorCompra DECIMAL(10,2),
	valorVenda DECIMAL(10,2),
	quantMaxDepo INT,
	quantMaxLoja INT,
	quantAtualDepo INT,
	quantAtualLoja INT,
	pontoRessuprimento INT,
	pontoReposicao INT,
	PRIMARY KEY (produto_oid)
	);