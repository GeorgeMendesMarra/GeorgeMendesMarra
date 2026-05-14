// Passo 1: Importar a classe ArrayList do pacote java.util
import java.util.ArrayList;

public class Exercicio2 {
    public static void main(String[] args) {
        
        // Passo 2: Criar um ArrayList<Integer> para armazenar números inteiros
        ArrayList<Integer> numeros = new ArrayList<>();
        
        // Passo 3: Adicionar os números de 1 a 10 usando um laço for
        // O laço for inicia em 1, vai até 10, incrementando de 1 em 1
        for (int i = 1; i <= 10; i++) {
            numeros.add(i); // Adiciona cada número à lista
        }
        
        // Passo 4: Calcular a soma percorrendo a lista
        int soma = 0; // Variável para armazenar o resultado da soma
        
        // Usando for-each para percorrer cada elemento da lista
        for (int numero : numeros) {
            soma += numero; // soma = soma + numero (acumula os valores)
        }
        
        // Passo 5: Exibir o resultado da soma
        System.out.println("A soma dos números é: " + soma);
        
        // Exemplo adicional: Verificando o tamanho da lista
        System.out.println("Foram somados " + numeros.size() + " números.");
        
        // Exemplo adicional: Exibindo a lista completa
        System.out.print("Lista de números: ");
        for (int numero : numeros) {
            System.out.print(numero + " ");
        }
    }
}
