set foreign_key_checks = 0;
set sql_safe_updates=0;
delete from atendimento;
delete from atendimento_itens;
delete from classepermissao;
delete from config;
delete from empresa;
delete from exame_convenio;
delete from exame;
delete from convenio;
delete from log_sistema;
delete from patrimonio;
delete from paciente;
delete from permissao;
delete from tenant;
delete from usuario; 
delete from usuario_permissao;
delete from usuario_empresa;


set foreign_key_checks = 1;
alter table atendimento auto_increment = 1000;
alter table classepermissao auto_increment = 1;
alter table classepermissao auto_increment = 1;
alter table convenio auto_increment = 1;
alter table exame auto_increment = 1;
alter table log_sistema auto_increment = 1;
alter table permissao auto_increment = 1;
alter table usuario auto_increment = 1;
alter table patrimonio auto_increment = 1;
alter table paciente auto_increment = 1;

INSERT INTO classepermissao VALUES (1,'Atendimento',NULL,NULL),(2,'Convênio',NULL,NULL),(3,'Exame',NULL,NULL),(4,'Paciente',NULL,NULL),(5,'Usuario',NULL,NULL),(6,'Relatório',NULL,NULL),(7,'Empresa',NULL,NULL),(8,'Patrimônio',NULL,NULL);
INSERT INTO permissao VALUES (1,'C_ATEND',NULL),(2,'U_ATEND',NULL),(3,'D_ATEND',NULL),(4,'R_ATEND',NULL),(5,'S_ATEND',NULL),(6,'C_CONV',NULL),(7,'U_CONV',NULL),(8,'D_CONV',NULL),(9,'R_CONV',NULL),(10,'S_CONV',NULL),(11,'C_EXME',NULL),(12,'U_EXME',NULL),(13,'D_EXME',NULL),(14,'R_EXME',NULL),(15,'S_EXME',NULL),(16,'C_PCTE',NULL),(17,'U_PCTE',NULL),(18,'D_PCTE',NULL),(19,'R_PCTE',NULL),(20,'S_PCTE',NULL),(21,'C_USU',NULL),(22,'U_USU',NULL),(23,'D_USU',NULL),(24,'R_USU',NULL),(25,'S_USU',NULL),(26,'R_REL_ATEND',NULL),(27,'R_REL_CONV',NULL),(28,'R_REL_EXME',NULL),(29,'R_REL_PCTE',NULL),(30,'R_REL_USU',NULL),(31,'R_REL',NULL),(32,'C_EMP',NULL),(33,'U_EMP',NULL),(34,'D_EMPD',NULL),(35,'R_EMP',NULL),(36,'S_EMP',NULL),(37,'C_PAT',NULL),(38,'U_PAT',NULL),(39,'D_PAT',NULL),(40,'R_PAT',NULL),(41,'S_PAT',NULL);

INSERT INTO usuario VALUES (1, 'turma1@gmail.com', 'CURSO', NULL, '$2a$10$ym4FHvnOCErbHb49jq8FzekqTSYTAsqhDX4ntJ00XC0h8Iganq0Nm', 1, 1, 0, 1, NULL),(3,'controle@gmail.com','Controle','Controle','1',1,0,1,0,NULL);

INSERT INTO usuario_permissao VALUES 
(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),
(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),
(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30),
(1,31),(1,32),(1,33),(1,34),(1,35),(1,36),(1,37),(1,38),(1,39),(1,40),(1,41);

INSERT INTO tenant (id, descricao) VALUES ('1', 'GASOT E MARQUES SERVICO DE RADIOLOGIA LTDA');

INSERT INTO empresa (id, cidade, razaosocial, cpfoucnpj, naturezapessoa, uf, cep, logradouro, numero, bairro, whats, tenant_id,status, editar) 
VALUES ('1', 'ARAPONGAS', 'GASOT E MARQUES SERVICO DE RADIOLOGIA LTDA', '35502138000173', 'JURIDICA', 'PR', '86703530', 'PEPIRA DE CRISTA AMARELA', '81', 'VILA COELHO', '(43)9841-18809', '1', '1', '1');
INSERT INTO usuario_empresa (id_usuario, id_empresa, empresapadrao, tenant_id) VALUES ('1', '1', '1', '1');

set foreign_key_checks = 1;


