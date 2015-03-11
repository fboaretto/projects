create table Produto(
	id int not null, 
	nome varchar(100) not null, 
	sku varchar(10) not null, 
	valorUnitario decimal,
	estoque int,
	gondola int);