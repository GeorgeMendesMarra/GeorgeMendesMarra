// Passo 1: Importar a classe ArrayList do pacote java.util
import java.util.ArrayList;

public class Exercicio1 {
    public static void main(String[] args) {
        
        // Passo 2: Criar um ArrayList<String> chamado cores
        // ArrayList é uma lista dinâmica que pode crescer ou diminuir
        ArrayList<String> cores = new ArrayList<>();
        
        // Passo 3: Adicionar 5 cores diferentes usando o método add()
        cores.add("Vermelho");
        cores.add("Azul");
        cores.add("Verde");
        cores.add("Amarelo");
        cores.add("Roxo");
        
        // Passo 4: Exibir todas as cores usando o laço for-each
        // O for-each percorre automaticamente cada elemento da lista
        System.out.println("Cores na lista:");
        for (String cor : cores) {
            System.out.println("- " + cor);
        }
        
        // Exemplo adicional: Exibindo também o tamanho da lista
        System.out.println("\nTotal de cores na lista: " + cores.size());
    }
}
