-- ==========================================
-- 🗃️ MODELO FÍSICO - POSTGRESQL
-- Sistema de Controle de Estoque (DevMedia)
-- Autor: Prof. George Mendes Marra
-- Instituto Federal Goiano – Campus Hidrolândia
-- ==========================================

CREATE SCHEMA IF NOT EXISTS estoque;
SET search_path TO estoque;

CREATE TABLE categoria (
    codcategoria SERIAL PRIMARY KEY,
    categoria VARCHAR(100) NOT NULL
);

CREATE TABLE cidade (
    codcidade SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    uf CHAR(2) NOT NULL CHECK (uf ~ '^[A-Z]{2}$')
);

CREATE TABLE fornecedor (
    codfornecedor SERIAL PRIMARY KEY,
    codcidade INT NOT NULL REFERENCES cidade(codcidade) ON DELETE RESTRICT,
    nome VARCHAR(150) NOT NULL,
    endereco VARCHAR(200),
    num INT,
    bairro VARCHAR(100),
    cep CHAR(8) CHECK (cep ~ '^[0-9]{8}$'),
    contato VARCHAR(100),
    cnpj CHAR(14) UNIQUE CHECK (cnpj ~ '^[0-9]{14}$'),
    inscr VARCHAR(20),
    tel VARCHAR(15)
);

CREATE TABLE transportadora (
    codtransportadora SERIAL PRIMARY KEY,
    codcidade INT NOT NULL REFERENCES cidade(codcidade) ON DELETE RESTRICT,
    nome VARCHAR(150) NOT NULL,
    endereco VARCHAR(200),
    num INT,
    bairro VARCHAR(100),
    cep CHAR(8),
    cnpj CHAR(14) UNIQUE CHECK (cnpj ~ '^[0-9]{14}$'),
    inscr VARCHAR(20),
    tel VARCHAR(15)
);

CREATE TABLE loja (
    codloja SERIAL PRIMARY KEY,
    codcidade INT NOT NULL REFERENCES cidade(codcidade) ON DELETE RESTRICT,
    nome VARCHAR(150) NOT NULL,
    endereco VARCHAR(200),
    num INT,
    bairro VARCHAR(100),
    cep CHAR(8),
    contato VARCHAR(100)
);

CREATE TABLE produto (
    codproduto SERIAL PRIMARY KEY,
    codfornecedor INT NOT NULL REFERENCES fornecedor(codfornecedor) ON DELETE RESTRICT,
    codcategoria INT NOT NULL REFERENCES categoria(codcategoria) ON DELETE RESTRICT,
    descricao VARCHAR(200) NOT NULL,
    peso NUMERIC(10,2),
    controlado BOOLEAN DEFAULT FALSE,
    qtdmin INT DEFAULT 0 CHECK (qtdmin >= 0)
);

CREATE TABLE entrada (
    codentrada SERIAL PRIMARY KEY,
    codtransportadora INT NOT NULL REFERENCES transportadora(codtransportadora) ON DELETE RESTRICT,
    dataent DATE NOT NULL DEFAULT CURRENT_DATE,
    datacrt DATE DEFAULT CURRENT_DATE,
    total NUMERIC(12,2) DEFAULT 0 CHECK (total >= 0),
    frete NUMERIC(10,2) DEFAULT 0 CHECK (frete >= 0),
    imposto NUMERIC(10,2) DEFAULT 0 CHECK (imposto >= 0),
    numnf VARCHAR(20) UNIQUE
);

CREATE TABLE itementrada (
    coditementrada SERIAL PRIMARY KEY,
    codentrada INT NOT NULL REFERENCES entrada(codentrada) ON DELETE CASCADE,
    codproduto INT NOT NULL REFERENCES produto(codproduto) ON DELETE RESTRICT,
    lote VARCHAR(50),
    quantidade INT NOT NULL CHECK (quantidade > 0),
    valor NUMERIC(10,2) NOT NULL CHECK (valor >= 0)
);

CREATE TABLE saida (
    codsaida SERIAL PRIMARY KEY,
    codtransportadora INT NOT NULL REFERENCES transportadora(codtransportadora) ON DELETE RESTRICT,
    codloja INT NOT NULL REFERENCES loja(codloja) ON DELETE RESTRICT,
    datasaida DATE NOT NULL DEFAULT CURRENT_DATE,
    total NUMERIC(12,2) DEFAULT 0 CHECK (total >= 0),
    frete NUMERIC(10,2) DEFAULT 0 CHECK (frete >= 0),
    imposto NUMERIC(10,2) DEFAULT 0 CHECK (imposto >= 0)
);

CREATE TABLE itemsaida (
    coditemsaida SERIAL PRIMARY KEY,
    codsaida INT NOT NULL REFERENCES saida(codsaida) ON DELETE CASCADE,
    codproduto INT NOT NULL REFERENCES produto(codproduto) ON DELETE RESTRICT,
    lote VARCHAR(50),
    quantidade INT NOT NULL CHECK (quantidade > 0),
    valor NUMERIC(10,2) NOT NULL CHECK (valor >= 0)
);

CREATE INDEX idx_produto_fornecedor ON produto(codfornecedor);
CREATE INDEX idx_produto_categoria ON produto(codcategoria);
CREATE INDEX idx_entrada_transportadora ON entrada(codtransportadora);
CREATE INDEX idx_saida_transportadora ON saida(codtransportadora);
CREATE INDEX idx_saida_loja ON saida(codloja);

CREATE TABLE estoque_produto (
    codproduto INT PRIMARY KEY REFERENCES produto(codproduto) ON DELETE CASCADE,
    quantidade INT DEFAULT 0 CHECK (quantidade >= 0)
);

CREATE OR REPLACE FUNCTION atualizar_estoque_entrada()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO estoque_produto (codproduto, quantidade)
    VALUES (NEW.codproduto, NEW.quantidade)
    ON CONFLICT (codproduto)
    DO UPDATE SET quantidade = estoque_produto.quantidade + EXCLUDED.quantidade;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_atualiza_estoque_entrada
AFTER INSERT ON itementrada
FOR EACH ROW
EXECUTE FUNCTION atualizar_estoque_entrada();

CREATE OR REPLACE FUNCTION atualizar_estoque_saida()
RETURNS TRIGGER AS $$
BEGIN
    UPDATE estoque_produto
    SET quantidade = GREATEST(0, quantidade - NEW.quantidade)
    WHERE codproduto = NEW.codproduto;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_atualiza_estoque_saida
AFTER INSERT ON itemsaida
FOR EACH ROW
EXECUTE FUNCTION atualizar_estoque_saida();

-- ==========================================
-- FIM DO SCRIPT
-- ==========================================
