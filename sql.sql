create database ecall;

use ecall;

/** Criando TipoTabela **/

CREATE TABLE tipoarquivo(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
atualizado timestamp NOT NULL,
PRIMARY KEY (id));

CREATE TABLE tipoatendimento(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
atualizado timestamp NOT NULL,
PRIMARY KEY (id));

CREATE TABLE tipoemail(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
atualizado timestamp NOT NULL,
PRIMARY KEY (id));

CREATE TABLE tipoEndereco(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
atualizado timestamp NOT NULL,
PRIMARY KEY (id));

CREATE TABLE tipotelefone(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
atualizado timestamp NOT NULL,
PRIMARY KEY (id));

CREATE TABLE meiocontato(
id INT(11) not null auto_increment,
nome varchar(50) NOT NULL,
atualizado timestamp NOT NULL,
PRIMARY KEY (id));

/** Criando Objetos **/

CREATE TABLE arquivo(
id INT(11) NOT NULL auto_increment,
nome varchar(50) NOT NULL,
id_tipoArquivo int,
PRIMARY KEY (id),
foreign key(id_tipoArquivo) references tipoarquivo(id));

CREATE TABLE pessoa(
id INT(11) NOT NULL auto_increment,
codigoPessoa int(14) NOT NULL,
login varchar(30) NOT NULL,
senha varchar(30) NOT NULL,
ativo boolean NOT NULL,
atualizado timestamp NOT NULL,
PRIMARY KEY (id));

CREATE TABLE email(
id INT(11) NOT NULL auto_increment,
nome varchar(50) NOT NULL,
id_tipoEmail int,
id_pessoa int,
PRIMARY KEY (id),
foreign key(id_tipoEmail) references tipoemail(id),
foreign key(id_pessoa) references pessoa(id));

CREATE TABLE endereco(
id INT(11) NOT NULL auto_increment,
cep int(11) NOT NULL,
numero int(7) NOT NULL,
complemento varchar(35),
bairro varchar(35) NOT NULL,
cidade varchar(35) NOT NULL,
estado varchar(35) NOT NULL,
id_tipoEndereco int,
id_pessoa int,
atualizado timestamp NOT NULL,
PRIMARY KEY (id),
foreign key(id_tipoEndereco) references tipoendereco(id),
foreign key(id_pessoa) references pessoa(id));

CREATE TABLE telefone(
id INT(11) NOT NULL auto_increment,
ddd varchar(3) NOT NULL,
numero varchar(9) NOT NULL,
id_tipoTelefone int NOT NULL,
id_pessoa int,
atualizado timestamp NOT NULL,
PRIMARY KEY (id),
foreign key(id_tipoTelefone) references tipoTelefone(id),
foreign key(id_pessoa) references pessoa(id));


CREATE TABLE usuario(
id INT(11) NOT NULL auto_increment,
id_pessoa int,
login varchar(30) NOT NULL,
senha varchar(30) NOT NULL,
ativo boolean NOT NULL,
primeiroLogin boolean NOT NULL,
atualizado timestamp NOT NULL,
PRIMARY KEY (id),
foreign key(id_pessoa) references pessoa(id));


CREATE TABLE ocorrencia(
id INT(11) NOT NULL auto_increment,
id_usuario int NOT NULL,
descricao varchar(150) NOT NULL,
resposta varchar(150),
id_Arquivo int,
atualizado timestamp NOT NULL,
PRIMARY KEY (id),
foreign key(id_usuario) references usuario(id),
foreign key(id_Arquivo) references arquivo(id));

select * from tipoArquivo;

select * from tipoAtendimento;

select * from tipoEmail


