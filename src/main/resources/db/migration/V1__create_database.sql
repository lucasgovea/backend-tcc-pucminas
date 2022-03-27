create table agendamento (
   id  bigserial not null,
    confirmado boolean,
    marcacao timestamp not null,
    paciente_id int8,
    profissional_id int8,
    primary key (id)
)

create table funcionario (
   id  bigserial not null,
    cargo varchar(255),
    cpf varchar(255),
    deletado boolean not null,
    nome varchar(255),
    usuario_id int8,
    primary key (id)
)

create table paciente (
   id  bigserial not null,
    cpf varchar(255),
    data_nascimento date,
    deletado boolean not null,
    email varchar(255),
    bairro varchar(255),
    cep varchar(255),
    cidade varchar(255),
    complemento varchar(255),
    estado varchar(255),
    logradouro varchar(255),
    numero varchar(255),
    nome varchar(255),
    ddd varchar(255),
    telefone varchar(255),
    primary key (id)
)

create table perfil (
   id  bigserial not null,
    descricao varchar(255),
    primary key (id)
)

create table profissional (
   id  bigserial not null,
    atuacao int4 not null,
    cpf varchar(255),
    deletado boolean not null,
    nome varchar(255),
    usuario_id int8,
    primary key (id)
)

create table usuario (
   id  bigserial not null,
    login varchar(255),
    senha varchar(255),
    primary key (id)
)

create table usuario_perfil (
   id_usuario int8 not null,
    id_perfil int8 not null,
    primary key (id_usuario, id_perfil)
)

alter table funcionario 
   add constraint UK_rxosr8731eb3gbnlbt2mqfan8 unique (cpf)

alter table paciente 
   add constraint UK_fvlo8m5kqpr7knbyw4rjyer2s unique (cpf)

alter table profissional 
   add constraint UK_bxxjila9qnem55afsisc4865x unique (cpf)

alter table agendamento 
   add constraint FK72wv1pjcpc2c0g6dy3yq39td7 
   foreign key (paciente_id) 
   references paciente

alter table agendamento 
   add constraint FK4a21d2pi1rrtdt79gqf3ks9ho 
   foreign key (profissional_id) 
   references profissional

alter table funcionario 
   add constraint FKwnmqfjh318guaklwmtu9nnma 
   foreign key (usuario_id) 
   references usuario

alter table profissional 
   add constraint FK509yihu28yuuinro8jxectk7q 
   foreign key (usuario_id) 
   references usuario

alter table usuario_perfil 
   add constraint FK3cxlmf5q4y8mllkos025n9px8 
   foreign key (id_perfil) 
   references perfil

alter table usuario_perfil 
   add constraint FK2qe6tjawyl6ojl32uhbwllldh 
   foreign key (id_usuario) 
   references usuario