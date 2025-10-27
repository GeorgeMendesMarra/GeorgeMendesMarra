# Exercícios – Primeira Forma Normal (1FN)

## Exercício 1 – Conceito

Explique com suas próprias palavras o que significa uma tabela estar na **Primeira Forma Normal**.

---

## Exercício 2 – Identificação

Considere a tabela abaixo:

| Aluno | Disciplinas                    |
| ----- | ------------------------------ |
| João  | Matemática, Física             |
| Maria | Química                        |
| Pedro | História, Geografia, Filosofia |

A tabela está em 1FN? Justifique sua resposta.

---

## Exercício 3 – Conversão

Transforme a tabela do exercício 2 em uma tabela que **respeite a 1FN**.

---

## Exercício 4 – Valores Atômicos

Explique o que significa que os atributos de uma tabela devem ter **valores atômicos** na 1FN. Dê um exemplo.

---

## Exercício 5 – Tabela com Repetição

Analise a tabela:

| Pedido | Produto       | Quantidade |
| ------ | ------------- | ---------- |
| 001    | Caneta, Lápis | 10, 5      |
| 002    | Caderno       | 2          |

Ela está na 1FN? Caso negativo, refaça-a para que esteja.

---

## Exercício 6 – Identificador

Por que cada tabela deve possuir uma **chave primária** para estar em 1FN? Explique com exemplo.

---

## Exercício 7 – Multivalorados

A tabela a seguir contém informações de um paciente:

| Paciente | Alergias          |
| -------- | ----------------- |
| Ana      | Pólen, Penicilina |
| Bruno    | Frutos do mar     |

Identifique os problemas de 1FN e proponha uma solução.

---

## Exercício 8 – Exercício Prático

Crie uma tabela para armazenar **informações de livros e autores** que **esteja em 1FN**. Considere que um livro pode ter vários autores.

---

## Exercício 9 – Teoria e Prática

Explique por que **não é permitido armazenar listas ou conjuntos dentro de um campo** em uma tabela 1FN. Dê um exemplo de violação.

---

## Exercício 10 – Revisão

Você é um analista de dados e recebeu a tabela abaixo:

| Funcionário | Projetos   |
| ----------- | ---------- |
| Carlos      | P1, P2     |
| Marina      | P2         |
| Roberto     | P3, P4, P5 |

1. Identifique se a tabela está na 1FN.
2. Caso não esteja, reescreva a tabela em **1FN**, garantindo que cada valor seja atômico.
