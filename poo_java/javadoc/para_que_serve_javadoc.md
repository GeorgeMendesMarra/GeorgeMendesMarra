Javadoc é uma ferramenta para gerar documentação da API de código-fonte Java em formato HTML. A documentação gerada pelo Javadoc pode ser usada para documentar a API de classes, interfaces, construtores, métodos e campos.

**Como usar o Javadoc**

1.  **Comentários de documentação:**
    * Os comentários de documentação do Javadoc são escritos em um formato específico que o Javadoc pode analisar e usar para gerar documentação HTML.
    * Os comentários de documentação são delimitados por `/**` no início e `*/` no final.
    * Os comentários de documentação podem incluir tags Javadoc, que são palavras-chave que o Javadoc usa para identificar diferentes tipos de informações.

2.  **Tags Javadoc:**
    * As tags Javadoc são usadas para fornecer informações adicionais sobre o código que está sendo documentado.
    * Algumas tags Javadoc comuns incluem:
        * `@author`: O autor do código.
        * `@version`: A versão do código.
        * `@param`: Um parâmetro de um método.
        * `@return`: O valor de retorno de um método.
        * `@throws`: Uma exceção que um método pode lançar.
        * `@see`: Uma referência a outra classe, método ou campo.
        * `@deprecated`: Indica que um método ou campo foi descontinuado.

3.  **Executando o Javadoc:**
    * O Javadoc pode ser executado a partir da linha de comando ou de uma IDE.
    * Para executar o Javadoc a partir da linha de comando, use o seguinte comando:

    ```
    javadoc <opções> <arquivos-java>
    ```

    * Para executar o Javadoc a partir de uma IDE, consulte a documentação da IDE.

4.  **Exemplo:**

    ```java
    /**
     * Esta classe representa um ponto em um plano cartesiano.
     *
     * @author [seu nome]
     * @version 1.0
     */
    public class Ponto {

        /**
         * A coordenada x do ponto.
         */
        private int x;

        /**
         * A coordenada y do ponto.
         */
        private int y;

        /**
         * Cria um novo ponto com as coordenadas especificadas.
         *
         * @param x A coordenada x do ponto.
         * @param y A coordenada y do ponto.
         */
        public Ponto(int x, int y) {
            this.x = x;
            this.y = y;
        }

        /**
         * Retorna a coordenada x do ponto.
         *
         * @return A coordenada x do ponto.
         */
        public int getX() {
            return x;
        }

        /**
         * Retorna a coordenada y do ponto.
         *
         * @return A coordenada y do ponto.
         */
        public int getY() {
            return y;
        }
    }
    ```

**Recursos adicionais**

* Documentação do Javadoc: [https://www.oracle.com/technetwork/pt/java/javase/documentation/index-jsp-135444-ptb.html](https://www.google.com/search?q=https://www.oracle.com/technetwork/pt/java/javase/documentation/index-jsp-135444-ptb.html)
* Tutorial do Javadoc: [https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html)
