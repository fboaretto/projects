CREATE TABLE fornecedor (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(60) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE produto (
  id BIGINT NOT NULL AUTO_INCREMENT,	
  nome VARCHAR(60) NOT NULL,
  codigo VARCHAR(6) NOT NULL,
  estoque INT,
  codigoBarra VARCHAR(13),
  valorCusto DECIMAL(10,2),
  valorVenda DECIMAL(10,2),
  quantMaxDepo INT,
  quantMaxLoja INT,
  quantAtualDepo INT,
  quantAtualLoja INT,
  pontoRessuprimento INT,
  pontoReposicao INT,
  fornecedor_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_FORNECEDOR_PRODUTO FOREIGN KEY (fornecedor_id) REFERENCES fornecedor (id)
);

CREATE TABLE endereco (
  id BIGINT NOT NULL AUTO_INCREMENT,
  endereco VARCHAR(100),
  bairro VARCHAR(45),
  cep VARCHAR(45),
  cidade VARCHAR(45),
  fornecedor_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_FORNECEDOR_ENDERECO FOREIGN KEY (fornecedor_id) REFERENCES fornecedor (id)
);

CREATE TABLE telefone (
  id BIGINT NOT NULL AUTO_INCREMENT,
  ddd VARCHAR(10),
  telefone VARCHAR(45),
  endereco_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_ENDERECO_TELEFONE FOREIGN KEY (endereco_id) REFERENCES endereco (id)
 );

CREATE TABLE pessoa (
  id BIGINT NOT NULL AUTO_INCREMENT,
  cpf VARCHAR(45),
  primeiroNome VARCHAR(45),
  ultimoNome VARCHAR(45),
  email VARCHAR(45),
  dataNascimento DATETIME,
  tipoCadastro_id BIGINT NOT NULL,
  celular_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_CELULAR_PESSOA FOREIGN KEY (celular_id) REFERENCES telefone (id)
);

CREATE TABLE cliente (
  id BIGINT NOT NULL AUTO_INCREMENT,
  pessoa_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_PESSOA_CLIENTE FOREIGN KEY (pessoa_id) REFERENCES pessoa (id)
);

CREATE TABLE empregado (
  id BIGINT NOT NULL AUTO_INCREMENT,
  dataContrato DATETIME,
  pessoa_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_PESSOA_EMPREGADO FOREIGN KEY (pessoa_id) REFERENCES pessoa (id)
);

CREATE TABLE compraCliente (
  id BIGINT NOT NULL AUTO_INCREMENT,
  data DATETIME,
  cliente_id BIGINT NOT NULL,
  empregado_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_CLIENTE_COMPRACLIENTE FOREIGN KEY (cliente_id) REFERENCES cliente (id),
  CONSTRAINT FK_EMPREGADO_COMPRACLIENTE FOREIGN KEY (empregado_id) REFERENCES empregado (id)
);

CREATE TABLE itemCompra (
  quantidade INT NULL,
  preco DECIMAL(10,2),
  produto_id BIGINT NOT NULL,
  compraCliente_id BIGINT NOT NULL,
  PRIMARY KEY (produto_id, compraCliente_id),
  CONSTRAINT FK_PRODUTO_ITEMCOMPRA FOREIGN KEY (produto_id) REFERENCES produto (id),
  CONSTRAINT FK_COMPRACLIENTE_ITEMCOMPRA FOREIGN KEY (compraCliente_id) REFERENCES compraCliente (id)
);

CREATE TABLE controlePonto (
  id BIGINT NOT NULL AUTO_INCREMENT,
  dataEntrada DATETIME,
  empregado_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_EMPREGADO_CONTROLEPONTO FOREIGN KEY (empregado_id) REFERENCES empregado (id)
);
