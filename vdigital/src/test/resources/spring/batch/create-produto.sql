DROP TABLE IF EXISTS Produto;

CREATE TABLE Produto(
	produto_oid INT NOT NULL,
	nome VARCHAR(60) NOT NULL,
	quantidadeDepo INT,
	valorCompra DECIMAL(10,3),
	valorVenda DECIMAL(10,3),
	pontoReposicao INT,
	quantMaxGondola INT,
	quantMinGondola INT,
	CONSTRAINT produtoPK PRIMARY KEY (produto_oid)
	);