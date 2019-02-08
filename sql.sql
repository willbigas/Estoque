create database bwstock;

use bwstock;

/** Criando Tabelas **/

CREATE TABLE usuario(
id INT(11) not null auto_increment,
login varchar(25),
senha varchar(25),
ativo boolean,
primeirologin boolean,
atualizado timestamp NOT NULL,
PRIMARY KEY (id));


CREATE TABLE categoriaProduto(
id INT(11) not null auto_increment,
nome varchar(25),
descricao varchar(150),
atualizado timestamp NOT NULL,
PRIMARY KEY (id));


CREATE TABLE estoqueMovimento(
id INT(11) not null auto_increment,
dataEntrada date,
dataSaida date,
atualizado timestamp NOT NULL,
PRIMARY KEY (id));


CREATE TABLE produto(
id INT(11) not null auto_increment,
sku varchar(11) NOT NULL,
nome varchar(50) NOT NULL,
id_categoriaProduto int,
ean13 INT(13),
qtdEstoque INT(13),
precoUnitario decimal(10,2),
id_estoqueMovimento int,
dataCadastro Date,
ativo boolean,
atualizado timestamp NOT NULL,
foreign key(id_estoqueMovimento) references estoqueMovimento(id),
foreign key(id_categoriaProduto) references categoriaProduto(id),
PRIMARY KEY (id));

select * from estoqueMovimento;

select * from produto;

select * from categoriaproduto;

select * from usuario