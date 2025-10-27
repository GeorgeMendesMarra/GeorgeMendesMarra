A Segunda Forma Normal (2FN) é um conceito fundamental na normalização de bancos de dados relacionais.

Aqui está um breve histórico:

* **Pioneirismo de E.F. Codd:** A 2FN foi introduzida por **Edgar F. Codd**, o pioneiro dos bancos de dados relacionais, como parte de seu trabalho sobre normalização.
* **Definição Inicial:** A 1FN, 2FN e 3FN foram inicialmente definidas no artigo de Codd de **1971**, intitulado "Further Normalization of the Data Base Relational Model", que complementava seu trabalho seminal de 1970 ("A Relational Model of Data for Large Shared Data Banks"), onde a 1FN foi definida.
* **Propósito:** O principal objetivo da 2FN é **eliminar a redundância de dados** e melhorar a integridade dos dados, focando na remoção de **dependências funcionais parciais**.
* **Requisito:** Uma tabela está na 2FN se, e somente se, estiver na **Primeira Forma Normal (1FN)** e todos os seus atributos não-chave forem **totalmente dependentes funcionalmente de qualquer chave candidata** (ou seja, não houver dependência parcial).

Em essência, a 2FN estabeleceu a base para um design de banco de dados mais limpo e eficiente, sendo um passo crucial na progressão para formas normais mais elevadas, como a Terceira Forma Normal (3FN).
