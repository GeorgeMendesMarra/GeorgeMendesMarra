/*
 * Versão Java: 8+
 * Paradigma: Metaprogramação (anotações)
 */
import java.lang.annotation.*; // Importa anotações

@Retention(RetentionPolicy.RUNTIME) // Anotação com retenção em runtime
@interface SaudacaoAnnotation { // Definição da anotação
    String valor() default "Hello World"; // Elemento com valor padrão
} // Fim da anotação

public class HelloWorld25 { // Classe principal
    @SaudacaoAnnotation // Aplica a anotação
    public static void main(String[] args) throws Exception { // Método principal
        SaudacaoAnnotation ann = HelloWorld25.class // Obtém a anotação
            .getMethod("main", String[].class)
            .getAnnotation(SaudacaoAnnotation.class);
        System.out.println(ann.valor()); // Imprime o valor da anotação
    } // Fim do main
} // Fim da classe
