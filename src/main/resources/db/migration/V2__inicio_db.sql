INSERT INTO usuario(login, senha) VALUES ('01234567890', '$2a$10$OZIv7QI9sLYWpyD1lvFv6uxZ2Lo33UHsCPcmShP6dSU5nxszVDlEW'); -- senha '123456'

INSERT INTO perfil(descricao) VALUES ('admin');

INSERT INTO usuario_perfil(id_usuario, id_perfil) VALUES (1, 1);


INSERT INTO PACIENTE (CPF,DATA_NASCIMENTO,BAIRRO,CEP,CIDADE,COMPLEMENTO,ESTADO,LOGRADOURO,NUMERO,NOME, DELETADO)
VALUES('01234567890',now(),'Vila isabel','20541000','Rio de Janeiro','casa','RJ','RUa tal','10','Maria',false) ;

INSERT INTO PACIENTE (CPF,DATA_NASCIMENTO,BAIRRO,CEP,CIDADE,COMPLEMENTO,ESTADO,LOGRADOURO,NUMERO,NOME, DELETADO)
VALUES('07249484060',now(),'Vila isabel','20541000','Rio de Janeiro','casa','RJ','RUa tal','10','Joao', false) ;


insert into usuario(login, senha) values ('39410349010', '$2a$10$OZIv7QI9sLYWpyD1lvFv6uxZ2Lo33UHsCPcmShP6dSU5nxszVDlEW');
insert into profissional(atuacao, cpf, deletado, nome, usuario_id) values (1, '39410349010', false, 'Lucas', 2);
insert into usuario_perfil(id_usuario, id_perfil) values (2, 1);