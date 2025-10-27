# Exercícios – Terceira Forma Normal (3FN)

## Exercício 1 – Conceito

Explique com suas próprias palavras o que significa uma tabela estar na **Terceira Forma Normal**.

---

## Exercício 2 – Identificação

Considere a tabela abaixo:

| FuncionarioID | Nome  | Departamento | GerenteDepartamento |
| ------------- | ----- | ------------ | ------------------- |
| 001           | Ana   | Vendas       | Carlos              |
| 002           | Bruno | Compras      | Carla               |
| 003           | Carla | Vendas       | Carlos              |

A tabela está em 3FN? Justifique sua resposta.

---

## Exercício 3 – Dependência Transitiva

Explique o que é **dependência transitiva** em uma tabela e por que ela viola a 3FN. Dê um exemplo.

---

## Exercício 4 – Conversão para 3FN

Transforme a tabela do exercício 2 em tabelas que **respeitem a 3FN**, mantendo as informações corretamente.

---

## Exercício 5 – Tabela com Dependência Transitiva

A tabela a seguir contém informações de alunos e cursos:

| AlunoID | NomeAluno | CursoID | NomeCurso  | CoordenadorCurso |
| ------- | --------- | ------- | ---------- | ---------------- |
| A01     | João      | C01     | Matemática | Prof. Ana        |
| A02     | Maria     | C02     | Física     | Prof. Bruno      |

Identifique as dependências transitivas e refatore a tabela para 3FN.

---

## Exercício 6 – Benefícios da 3FN

Liste três benefícios de se normalizar uma tabela para a 3FN.

---

## Exercício 7 – Redundância e 3FN

Explique como a existência de informações redundantes pode ser eliminada ao aplicar a 3FN. Dê um exemplo.

---

## Exercício 8 – Exercício Prático

Crie uma tabela de **funcionários e departamentos** que esteja em 3FN, considerando que cada departamento possui um gerente.

---

## Exercício 9 – Teoria e Prática

Dê um exemplo de tabela que esteja na 2FN mas **não esteja na 3FN**, explicando a razão.

---

## Exercício 10 – Revisão

Você recebeu a tabela abaixo:

| PedidoID | ProdutoID | NomeProduto | Categoria  | EstoqueCategoria |
| -------- | --------- | ----------- | ---------- | ---------------- |
| P001     | 101       | Caneta      | Escritório | 500              |
| P002     | 102       | Lápis       | Escritório | 500              |

1. Verifique se a tabela está em 3FN.
2. Caso não esteja, reescreva-a em **3FN**, separando as informações corretamente.
