INSERT INTO ROLE(perfil) VALUES ('ROLE_PACIENTE');
INSERT INTO ROLE(perfil) VALUES ('ROLE_FUNCIONARIO');
INSERT INTO ROLE(perfil) VALUES ('ROLE_ADMIN');

INSERT INTO USUARIO(nome,email,validate,paciente_id,senha) VALUES ('Martin Petersen', 'petersen.martin.imd@gmail.com',true, null ,'$2a$10$3XtMirDe94AcNfXkv7Ivh.2LV7HAVZArrw03Yw9PEpj9nZZmQNYvm');

INSERT INTO USUARIO_ROLE(usuario_id,role_id) VALUES (1,3);

