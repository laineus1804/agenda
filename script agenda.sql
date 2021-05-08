/**
 * Projeto 1: Agenda de contatos
 * @author Professor Jose de Assis
 */
 
-- Listar bancos disponíveis no servidor
show databases;
-- Criar um novo banco de dados
create database dbagenda;
-- Excluir um banco de dados
drop database teste;
-- Selecionar o banco de dados
use dbagenda;

/* Tabelas */
-- Criar uma nova tabela
/*
  int (tipo de dados: números inteiros)
  primary key (chave primária)
  auto_increment (numeração automática)
  varchar (tipo de dados: String de caracteres)
  not null (campo com preenchimento obrigatório)
*/
create table contatos (
	idcon int primary key auto_increment,
    nome varchar(50) not null,
    fone varchar(15) not null,
    email varchar(50)
);

-- Listar tabelas do banco de dados
show tables;

-- Descrever a estrutura da tabela
describe contatos;

-- Excluir uma tabela
drop table contatos;

/* CRUD (Create Read Update Delete) */

/* CRUD Create */
-- Inserir um novo contato
insert into contatos(nome,fone,email)
values('José de Assis','99999-1234','jose@email.com');
insert into contatos(nome,fone)
values('Bill gates','98765-4321');
insert into contatos(nome,fone,email)
values('Linus Torvalds','88888-7777','linus@email.com');
insert into contatos(nome,fone,email)
values('Angelina Jolie','99999-4321','aj@email.com');
insert into contatos(nome,fone,email)
values('Sirlene Sanches','88888-1234','sisa@email.com');


/* CRUD Read */
-- listar todos contatos da tabela
select * from contatos;
-- listar os contatos por ordem alfabética
select * from contatos order by nome; 
-- listar campos específicos da tabela
select nome,fone from contatos order by nome;
-- criar um apelido para os campos da tabela
select nome as Contato, fone as Telefone,
email as Email from contatos order by nome;
-- selecionar um contato específico
select * from contatos where nome='Bill Gates';
select * from contatos where idcon=1;

/* CRUD Update */
update contatos set nome='Willian Gates', fone='2989-9090',
email='bill@outlook.com' where idcon=2;
update contatos set fone='99999-7667' where idcon=1;

/* CRUD delete */
delete from contatos where idcon=5;
