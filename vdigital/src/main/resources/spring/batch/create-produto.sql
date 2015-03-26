DROP TABLE if EXISTS Produto;

CREATE TABLE Produto(
	produto_oid INT NOT NULL,
	nome VARCHAR(60) NOT NULL,
	quantidadeDepo INT,
	valorCompra DECIMAL(10,2),
	valorVenda DECIMAL(10,2),
	pontoReposicao INT,
	quantMaxGondola INT,
	quantMinGondola INT,
	PRIMARY KEY (produto_oid)
	);