---
Título: "Modelo de Banco de Dados"
Autor: "Professor George Mendes Marra"
Disciplina: "Banco de Dados – ADS 2025/2"
Instituição: "UniAlfa"
Data: "12-10-2025"
---

# 🗃️ Modelo de Banco de Dados

Baseado na imagem:  
https://www.devmedia.com.br/arquivos/REVISTAS/sql/imagens/85/1/image001.webp

---

## 📋 1. Levantamento de Requisitos

### 🎯 Objetivo
Modelar um sistema de controle de estoque e movimentação de produtos, envolvendo fornecedores, transportadoras, lojas e registros de entrada e saída de mercadorias.

### 🧱 Entidades Identificadas
- **Categoria**
- **Produto**
- **Fornecedor**
- **Cidade**
- **Transportadora**
- **Loja**
- **Entrada**
- **Saída**
- **ItemEntrada**
- **ItemSaída**

### 🧩 Atributos Principais

| Entidade | Atributos |
|-----------|------------|
| **Categoria** | codcategoria, categoria |
| **Produto** | codproduto, codfornecedor, codcategoria, descricao, peso, controlado, qtdmin |
| **Fornecedor** | codfornecedor, codcidade, nome, endereco, num, bairro, cep, contato, cnpj, inscr, tel |
| **Cidade** | codcidade, nome, uf |
| **Transportadora** | codtransportadora, codcidade, nome, endereco, num, bairro, cep, cnpj, inscr, tel |
| **Loja** | codloja, codcidade, nome, endereco, num, bairro, cep, contato |
| **Entrada** | codentrada, codtransportadora, dataent, datacrt, total, frete, imposto, numnf |
| **ItemEntrada** | coditementrada, codentrada, codproduto, lote, quantidade, valor |
| **Saída** | codsaida, codtransportadora, codloja, total, frete, imposto |
| **ItemSaída** | coditemsaida, codsaida, codproduto, lote, quantidade, valor |

### 🔗 Regras e Relacionamentos
- Um **produto** pertence a **uma categoria** e é fornecido por **um fornecedor**.  
- **Fornecedores**, **lojas** e **transportadoras** estão associados a **uma cidade**.  
- Uma **entrada** está vinculada a uma **transportadora** e possui **vários itens de entrada**.  
- Uma **saída** envolve **uma loja**, **uma transportadora** e **vários itens de saída**.  
- Cada **item** referencia um **produto**.  
- Quantidades e valores devem ser positivos.  
- O campo **qtdmin** do produto indica o estoque mínimo aceitável.

---

## 🧭 2. Modelagem Conceitual (DER)

O Diagrama Entidade-Relacionamento (DER) apresenta as entidades e seus vínculos principais:

```
[Cidade]──<Fornece>──[Fornecedor]──<Fornece>──[Produto]──<Pertence>──[Categoria]
      │
      ├──<Abriga>──[Transportadora]
      └──<Localiza>──[Loja]

[Transportadora]──<Realiza>──[Entrada]──<Contém>──[ItemEntrada]──<Refere>──[Produto]
[Transportadora]──<Entrega>──[Saída]──<Contém>──[ItemSaída]──<Refere>──[Produto]
```

### Cardinalidades
- 1:N entre Cidade → Fornecedor, Cidade → Transportadora, Cidade → Loja  
- 1:N entre Categoria → Produto  
- 1:N entre Fornecedor → Produto  
- 1:N entre Transportadora → Entrada e Saída  
- 1:N entre Entrada → ItemEntrada  
- 1:N entre Saída → ItemSaída  

---

## 🧮 3. Modelagem Lógica (Modelo Relacional)

| Tabela | Chave Primária | Atributos | Chaves Estrangeiras |
|--------|----------------|------------|----------------------|
| **Categoria** | codcategoria | categoria | — |
| **Cidade** | codcidade | nome, uf | — |
| **Fornecedor** | codfornecedor | nome, endereco, num, bairro, cep, contato, cnpj, inscr, tel | codcidade → Cidade(codcidade) |
| **Transportadora** | codtransportadora | nome, endereco, num, bairro, cep, cnpj, inscr, tel | codcidade → Cidade(codcidade) |
| **Loja** | codloja | nome, endereco, num, bairro, cep, contato | codcidade → Cidade(codcidade) |
| **Produto** | codproduto | descricao, peso, controlado, qtdmin | codfornecedor → Fornecedor(codfornecedor), codcategoria → Categoria(codcategoria) |
| **Entrada** | codentrada | dataent, datacrt, total, frete, imposto, numnf | codtransportadora → Transportadora(codtransportadora) |
| **ItemEntrada** | coditementrada | lote, quantidade, valor | codentrada → Entrada(codentrada), codproduto → Produto(codproduto) |
| **Saída** | codsaida | total, frete, imposto | codtransportadora → Transportadora(codtransportadora), codloja → Loja(codloja) |
| **ItemSaída** | coditemsaida | lote, quantidade, valor | codsaida → Saída(codsaida), codproduto → Produto(codproduto) |

---

## 💾 4. Modelagem Física (SQL - MySQL)

```sql
CREATE TABLE Categoria (
  codcategoria INT PRIMARY KEY,
  categoria VARCHAR(100) NOT NULL
);

CREATE TABLE Cidade (
  codcidade INT PRIMARY KEY,
  nome VARCHAR(100) NOT NULL,
  uf CHAR(2) NOT NULL
);

CREATE TABLE Fornecedor (
  codfornecedor INT PRIMARY KEY,
  codcidade INT NOT NULL,
  nome VARCHAR(150),
  endereco VARCHAR(200),
  num INT,
  bairro VARCHAR(100),
  cep CHAR(8),
  contato VARCHAR(100),
  cnpj CHAR(18),
  inscr VARCHAR(50),
  tel CHAR(14),
  FOREIGN KEY (codcidade) REFERENCES Cidade(codcidade)
);

CREATE TABLE Transportadora (
  codtransportadora INT PRIMARY KEY,
  codcidade INT NOT NULL,
  nome VARCHAR(150),
  endereco VARCHAR(200),
  num INT,
  bairro VARCHAR(100),
  cep CHAR(8),
  contato VARCHAR(100),
  cnpj CHAR(18),
  inscr VARCHAR(50),
  tel CHAR(14),
  FOREIGN KEY (codcidade) REFERENCES Cidade(codcidade)
);

CREATE TABLE Loja (
  codloja INT PRIMARY KEY,
  codcidade INT NOT NULL,
  nome VARCHAR(150),
  endereco VARCHAR(200),
  num INT,
  bairro VARCHAR(100),
  cep CHAR(8),
  contato VARCHAR(100),
  FOREIGN KEY (codcidade) REFERENCES Cidade(codcidade)
);

CREATE TABLE Produto (
  codproduto INT PRIMARY KEY,
  codfornecedor INT NOT NULL,
  codcategoria INT NOT NULL,
  descricao VARCHAR(200),
  peso DOUBLE,
  controlado BOOLEAN,
  qtdmin INT,
  FOREIGN KEY (codfornecedor) REFERENCES Fornecedor(codfornecedor),
  FOREIGN KEY (codcategoria) REFERENCES Categoria(codcategoria)
);

CREATE TABLE Entrada (
  codentrada INT PRIMARY KEY,
  codtransportadora INT NOT NULL,
  dataent DATE,
  datacrt DATE,
  total DOUBLE,
  frete DOUBLE,
  imposto DOUBLE,
  numnf INT,
  FOREIGN KEY (codtransportadora) REFERENCES Transportadora(codtransportadora)
);

CREATE TABLE ItemEntrada (
  coditementrada INT PRIMARY KEY,
  codentrada INT NOT NULL,
  codproduto INT NOT NULL,
  lote VARCHAR(50),
  quantidade INT,
  valor DOUBLE,
  FOREIGN KEY (codentrada) REFERENCES Entrada(codentrada),
  FOREIGN KEY (codproduto) REFERENCES Produto(codproduto)
);

CREATE TABLE Saida (
  codsaida INT PRIMARY KEY,
  codtransportadora INT NOT NULL,
  codloja INT NOT NULL,
  total DOUBLE,
  frete DOUBLE,
  imposto DOUBLE,
  FOREIGN KEY (codtransportadora) REFERENCES Transportadora(codtransportadora),
  FOREIGN KEY (codloja) REFERENCES Loja(codloja)
);

CREATE TABLE ItemSaida (
  coditemsaida INT PRIMARY KEY,
  codsaida INT NOT NULL,
  codproduto INT NOT NULL,
  lote VARCHAR(50),
  quantidade INT,
  valor DOUBLE,
  FOREIGN KEY (codsaida) REFERENCES Saida(codsaida),
  FOREIGN KEY (codproduto) REFERENCES Produto(codproduto)
);
```

---

## ⚙️ 5. Considerações Técnicas

- Índices recomendados: `codproduto`, `codfornecedor`, `codcategoria`, `codcidade`  
- Restrições de integridade garantem consistência e referencialidade.  
- Aplicar `CHECK (quantidade > 0 AND valor > 0)` onde suportado.  
- Triggers podem automatizar atualização de estoque após operações em `ItemEntrada` e `ItemSaida`.

---

## ✍️ Autor
Professor **George Mendes Marra**  
Disciplina: **Banco de Dados – ADS 2025/2**
