INSERT INTO ROLE(perfil) VALUES ('ROLE_PACIENTE');
INSERT INTO ROLE(perfil) VALUES ('ROLE_FUNCIONARIO');
INSERT INTO ROLE(perfil) VALUES ('ROLE_ADMIN');

INSERT INTO CLIENTE(nome_cliente,cpf,cidade,bairro,complemento,data_nascimento,telefone,numero,fidelidade) VALUES ('MARTIN PETERSEN', '111.111.111-11','NATAL','LAGOA NOVA','PROXIMO AO ARENA DAS DUNAS','1995-01-12','11 11111-1111','155',false);
INSERT INTO USUARIO(nome,email,validate,cliente_id,senha) VALUES ('MARTIN PETERSEN', 'martinrpetersen171@gmail.com',true, 1,'$2a$10$3XtMirDe94AcNfXkv7Ivh.2LV7HAVZArrw03Yw9PEpj9nZZmQNYvm');

INSERT INTO USUARIO_ROLE(usuario_id,role_id) VALUES (1,3);

INSERT INTO CABELO(nome) VALUES ('CABELO');
INSERT INTO BARBA(nome) VALUES ('BARBA');