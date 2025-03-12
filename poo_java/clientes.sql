CREATE DATABASE IF NOT EXISTS nomedobanco; -- Crie o banco de dados se ele ainda não existir
USE nomedobanco; -- Use o banco de dados recém-criado

-- Crie a tabela de clientes
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telefone VARCHAR(20),
    endereco VARCHAR(255),
    cidade VARCHAR(100)
);
