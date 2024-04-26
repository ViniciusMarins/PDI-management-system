USE db_pdi;

SET FOREIGN_KEY_CHECKS = 0;

DELETE FROM area;
DELETE FROM arquivo;
DELETE FROM comentario;
DELETE FROM curso;
DELETE FROM dados_simulacao;
DELETE FROM disciplina;
DELETE FROM frequencia;
DELETE FROM lotacao;
DELETE FROM reuniao;
DELETE FROM servidor;
DELETE FROM servidor_simulacao;
DELETE FROM simulacao;
DELETE FROM token;

SET FOREIGN_KEY_CHECKS = 1;
