A Terceira Forma Normal (3FN) é um conceito fundamental na normalização de bancos de dados relacionais, que visa a organização eficiente dos dados, minimizando a redundância e melhorando a integridade.

Aqui está um breve histórico:

* **Pioneirismo de Codd (1971):** A 3FN foi introduzida pela primeira vez por **Edgar F. Codd**, o criador do modelo relacional, em 1971. Ele formalizou as primeiras três formas normais (1FN, 2FN e 3FN) como condições para obter uma estrutura de banco de dados totalmente normalizada.
* **Requisitos:** Para que uma tabela esteja na 3FN, ela deve atender a duas condições principais (além de já estar em 1FN e 2FN):
    1.  Estar na Segunda Forma Normal (2FN).
    2.  Não haver **dependências transitivas** entre atributos não-chave. Ou seja, todos os atributos que não fazem parte da chave primária devem depender **direta e exclusivamente** da chave primária.
* **O que ela resolve:** A principal questão que a 3FN procura eliminar são as dependências transitivas. Uma dependência transitiva ocorre quando um atributo não-chave depende de outro atributo não-chave, e este, por sua vez, depende da chave primária (exemplo: `Chave Primária` $\rightarrow$ `Atributo A` $\rightarrow$ `Atributo B`).
* **Importância:** A 3FN é frequentemente o objetivo prático na maioria dos projetos de bancos de dados, pois oferece um bom equilíbrio entre a redução de redundância e a usabilidade do banco de dados, minimizando anomalias (de inserção, atualização e exclusão).
* **Evolução (BCNF):** Anos depois, a forma normal de **Boyce-Codd (BCNF)**, mais restritiva que a 3FN, foi introduzida por Raymond Boyce e Codd, para lidar com anomalias que a 3FN não conseguia resolver em casos mais complexos. Contudo, a 3FN continua sendo um marco essencial e suficiente para a grande maioria das aplicações.
