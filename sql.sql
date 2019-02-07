create database bwstock;

use bwstock;

/** Criando Tabelas **/

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
ean13 INT(13),
qtdEstoque INT(13),
precoUnitario decimal(10,2),
id_estoqueMovimento int,
dataCadastro Date,
atualizado timestamp NOT NULL,
foreign key(id_estoqueMovimento) references estoqueMovimento(id),
PRIMARY KEY (id));
