DROP DATABASE IF EXISTS consultas;
CREATE DATABASE consultas;
USE consultas;
SET foreign_key_checks=0;

CREATE TABLE usuario (
  id BIGINT UNSIGNED AUTO_INCREMENT,
  email VARCHAR(254) NOT NULL UNIQUE,
  senha VARCHAR(1024) NOT NULL,
  cpf VARCHAR(14) NOT NULL,
  primeiro_nome VARCHAR(255) NOT NULL,
  sobrenome VARCHAR(512) NOT NULL,
  papel VARCHAR(128) NOT NULL,
  data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);

CREATE TABLE cliente (
  id BIGINT UNSIGNED AUTO_INCREMENT,
  telefone VARCHAR(20) NOT NULL UNIQUE,
  sexo VARCHAR(50) NOT NULL,
  data_nasc VARCHAR(14) NOT NULL,
  id_usuario BIGINT UNSIGNED,
  PRIMARY KEY (id),
  FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE profissional (
  id BIGINT UNSIGNED AUTO_INCREMENT,
  especialidade VARCHAR(255) NOT NULL,
  qualificacoes VARCHAR(512) NOT NULL,
  id_usuario BIGINT UNSIGNED,
  PRIMARY KEY (id),
  FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE TABLE consulta (
  id BIGINT UNSIGNED AUTO_INCREMENT,
  data_horario DATETIME NOT NULL,
  id_profissional BIGINT UNSIGNED,
  id_cliente BIGINT UNSIGNED,
  videoconferencia VARCHAR(255) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_profissional) REFERENCES profissional(id),
  FOREIGN KEY (id_cliente) REFERENCES cliente(id)
);

INSERT INTO usuario (email, senha, cpf, primeiro_nome, sobrenome, papel) VALUES ("admin@admin.com", "12345678", "999.999.999.99", "Admin", "Admin", "ADMIN");
SELECT LAST_INSERT_ID();
INSERT into usuario (email, senha, cpf, primeiro_nome, sobrenome, papel) VALUES ("nathan@gmail.com", "123456787", "448.448.449.49", "Nathan", "Oliveira", "PROFISSIONAL");
INSERT into profissional (id_usuario, especialidade, qualificacoes) VALUES (2, "Computeiro", "UFSCar");
