# 🧩 Histórico e Normalização até a Terceira Forma Normal (3FN)

## 📜 Histórico da Primeira Forma Normal (1FN)

A **Primeira Forma Normal (1FN)** surgiu no contexto do **modelo relacional de bancos de dados**, proposto por **Edgar F. Codd** em **1970** no artigo *“A Relational Model of Data for Large Shared Data Banks”*.  
Antes do modelo relacional, os bancos de dados usavam estruturas **hierárquicas** e **em rede**, que eram complexas e pouco flexíveis. Codd introduziu a ideia de organizar dados em **tabelas (relações)** e definiu princípios de **normalização** para eliminar redundâncias e anomalias.  
A **1FN** é o primeiro passo desse processo: **cada célula de uma tabela deve conter apenas um valor atômico**, sem listas, conjuntos ou campos repetidos.

---

## 🧾 Exemplo inicial: tabela não normalizada

**Tabela: ClienteRaw**

| ID_Cliente | Nome         | Telefones                         | Endereço           |
|-------------|---------------|------------------------------------|--------------------|
| 1           | Ana Silva     | (62)99999-1111, (62)98888-2222    | Rua A, Goiânia     |
| 2           | Carlos Souza  | (62)97777-3333                    | Rua B, Hidrolândia |

🔴 **Problema:** o campo **Telefones** contém múltiplos valores, violando o princípio da atomicidade.

---

## ✅ Aplicando a Primeira Forma Normal (1FN)

Separar valores múltiplos em linhas distintas.

**Tabela: Cliente_1FN**

| ID_Cliente | Nome         | Telefone       | Endereço             |
|-------------|---------------|----------------|----------------------|
| 1           | Ana Silva     | (62)99999-1111 | Rua A, Goiânia      |
| 1           | Ana Silva     | (62)98888-2222 | Rua A, Goiânia      |
| 2           | Carlos Souza  | (62)97777-3333 | Rua B, Hidrolândia  |

Agora cada célula contém apenas **um valor atômico**. A tabela está em **1FN**.

---

## 🧮 Aplicando a Segunda Forma Normal (2FN)

Para alcançar a **2FN**, é necessário:
1. Estar em **1FN** ✅
2. Garantir que todos os atributos não chave dependam **da chave primária inteira** (sem dependências parciais).

Se a chave for composta *(ID_Cliente, Telefone)*, então **Nome** e **Endereço** dependem apenas de **ID_Cliente**, e não da chave completa. Isso caracteriza **dependência parcial**.

**Solução:** separar os dados em duas tabelas.

### 🗂️ Tabela Cliente

| ID_Cliente | Nome         | Endereço             |
|-------------|---------------|----------------------|
| 1           | Ana Silva     | Rua A, Goiânia      |
| 2           | Carlos Souza  | Rua B, Hidrolândia  |

### ☎️ Tabela Telefone

| ID_Cliente | Telefone       |
|-------------|----------------|
| 1           | (62)99999-1111 |
| 1           | (62)98888-2222 |
| 2           | (62)97777-3333 |

Agora todos os atributos dependem da **chave primária** da respectiva tabela → **2FN atingida**.

---

## 🧠 Aplicando a Terceira Forma Normal (3FN)

Para alcançar a **3FN**, é necessário:
1. Estar em **2FN** ✅
2. Eliminar **dependências transitivas**, ou seja, quando um atributo não chave depende de outro atributo não chave.

Se o campo **Endereço** for dividido em **Rua**, **Cidade** e **Estado**, e **Estado** depender de **Cidade**, temos uma **dependência transitiva**.

**Solução:** mover essas informações para uma nova tabela.

### 🗂️ Tabela Cliente

| ID_Cliente | Nome         | ID_Endereco |
|-------------|---------------|-------------|
| 1           | Ana Silva     | 1           |
| 2           | Carlos Souza  | 2           |

### 🏠 Tabela Endereco

| ID_Endereco | Rua   | ID_Cidade |
|--------------|--------|-----------|
| 1            | Rua A | 1         |
| 2            | Rua B | 2         |

### 🌆 Tabela Cidade

| ID_Cidade | Nome_Cidade | Estado |
|------------|--------------|--------|
| 1          | Goiânia      | GO     |
| 2          | Hidrolândia  | GO     |

### ☎️ Tabela Telefone

| ID_Cliente | Telefone       |
|-------------|----------------|
| 1           | (62)99999-1111 |
| 1           | (62)98888-2222 |
| 2           | (62)97777-3333 |

Agora não existem dependências transitivas → **3FN atingida**.

---

## 🧭 Diagrama ER (em ASCII)

```text
[Cliente] 1 --- N [Telefone]
   |PK ID_Cliente         |PK(ID_Cliente, Telefone)
   |Nome                  |Telefone
   |FK ID_Endereco        
        |
        N
[Endereco] 1 --- N [Cidade]
   |PK ID_Endereco        |PK ID_Cidade
   |Rua                   |Nome_Cidade
   |FK ID_Cidade          |Estado
```

### 🔍 Relações
- Um **cliente** pode ter **vários telefones** (1:N).
- Um **cliente** possui um **endereço** (1:1 lógico).
- Vários **endereços** podem pertencer à mesma **cidade** (N:1).

---

## 🧱 Esquema relacional final

```sql
Cliente(ID_Cliente PK, Nome, ID_Endereco FK)
Telefone(ID_Cliente FK, Telefone, PK composto opcional)
Endereco(ID_Endereco PK, Rua, ID_Cidade FK)
Cidade(ID_Cidade PK, Nome_Cidade, Estado)
```

---

## 💬 Conclusão

- A **1FN** garante valores atômicos.  
- A **2FN** elimina dependências parciais.  
- A **3FN** elimina dependências transitivas.  

Essas formas foram desenvolvidas a partir das ideias de **Edgar F. Codd (1970)** para tornar o modelo relacional mais consistente e livre de redundâncias.  
A normalização é essencial para manter **integridade, coerência e eficiência** nos bancos de dados relacionais.
