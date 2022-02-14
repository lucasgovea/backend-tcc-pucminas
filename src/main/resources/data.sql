INSERT INTO usuario(id, login, senha) VALUES (null, 'admin', '$2a$10$OZIv7QI9sLYWpyD1lvFv6uxZ2Lo33UHsCPcmShP6dSU5nxszVDlEW'); -- senha '123456'

INSERT INTO perfil(id, descricao) VALUES (null, 'admin');

INSERT INTO usuario_perfil(id_usuario, id_perfil) VALUES (1, 1);


INSERT INTO PACIENTE (ID,CPF,DATA_NASCIMENTO,BAIRRO,CEP,CIDADE,COMPLEMENTO,ESTADO,LOGRADOURO,NUMERO,NOME)
VALUES(null,'01234567890',now(),'Vila isabel','20541000','Rio de Janeiro','casa','RJ','RUa tal','10','Maria') ;

INSERT INTO PACIENTE (ID,CPF,DATA_NASCIMENTO,BAIRRO,CEP,CIDADE,COMPLEMENTO,ESTADO,LOGRADOURO,NUMERO,NOME)
VALUES(null,'11111111111',now(),'Vila isabel','20541000','Rio de Janeiro','casa','RJ','RUa tal','10','Joao') ;