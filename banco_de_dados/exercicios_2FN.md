# Exercícios – Segunda Forma Normal (2FN)

## Exercício 1 – Conceito

Explique com suas próprias palavras o que significa uma tabela estar na **Segunda Forma Normal**.

---

## Exercício 2 – Identificação

Considere a tabela abaixo:

| PedidoID | ProdutoID | NomeProduto | Quantidade |
| -------- | --------- | ----------- | ---------- |
| 001      | 101       | Caneta      | 10         |
| 001      | 102       | Lápis       | 5          |
| 002      | 103       | Caderno     | 2          |

A tabela está em 2FN? Justifique sua resposta.

---

## Exercício 3 – Dependência Parcial

Explique o que é **dependência parcial** de uma coluna em relação à chave primária composta e por que isso viola a 2FN. Dê um exemplo.

---

## Exercício 4 – Conversão para 2FN

Transforme a tabela do exercício 2 em tabelas que **respeitem a 2FN**, mantendo as informações corretamente.

---

## Exercício 5 – Tabela com Chave Composta

A tabela a seguir registra cursos e professores:

| CursoID | ProfessorID | NomeProfessor | Sala |
| ------- | ----------- | ------------- | ---- |
| C01     | P01         | Ana           | 101  |
| C01     | P02         | Bruno         | 101  |
| C02     | P03         | Carla         | 102  |

Identifique as dependências parciais e refatore a tabela para 2FN.

---

## Exercício 6 – Benefícios da 2FN

Liste três benefícios de se normalizar uma tabela para a 2FN.

---

## Exercício 7 – Multivalorados e 2FN

Explique como a existência de campos multivalorados pode afetar a segunda forma normal e o que deve ser feito para corrigir.

---

## Exercício 8 – Exercício Prático

Crie uma tabela de **vendas de produtos** que esteja em 2FN, considerando que a tabela terá chave primária composta (PedidoID + ProdutoID).

---

## Exercício 9 – Teoria e Prática

Dê um exemplo de tabela que esteja na 1FN mas **não esteja na 2FN**, explicando a razão.

---

## Exercício 10 – Revisão

Você recebeu a tabela abaixo:

| VendaID | ProdutoID | NomeProduto | PrecoUnitario | Quantidade |
| ------- | --------- | ----------- | ------------- | ---------- |
| V001    | 201       | Caneta      | 2.50          | 10         |
| V001    | 202       | Lápis       | 1.50          | 5          |

1. Verifique se a tabela está em 2FN.
2. Caso não esteja, reescreva-a em **2FN**, separando as informações corretamente.
