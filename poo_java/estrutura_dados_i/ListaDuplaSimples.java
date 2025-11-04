// Salve este arquivo como ListaDuplaSimples.java

public class ListaDuplaSimples {

    // Classe interna estática que representa um nó da lista
    static class No {
        int valor;    // dado
        No anterior;  // referencia para o nó anterior
        No proximo;   // referencia para o próximo nó

        No(int valor) {
            this.valor = valor;
            // anterior e proximo ficam null por padrão
        }
    }

    // Referências para início e fim da lista
    private No inicio;
    private No fim;

    // Adiciona um valor ao final da lista
    public void adicionar(int valor) {
        No novo = new No(valor);
        if (inicio == null) {       // lista vazia
            inicio = fim = novo;
        } else {
            fim.proximo = novo;     // último aponta para novo
            novo.anterior = fim;    // novo aponta para antigo último
            fim = novo;             // atualiza fim
        }
    }

    // Mostra do início ao fim
    public void mostrarFrente() {
        No atual = inicio;
        System.out.print("Início → Fim: ");
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.proximo;
        }
        System.out.println();
    }

    // Mostra do fim ao início
    public void mostrarTras() {
        No atual = fim;
        System.out.print("Fim → Início: ");
        while (atual != null) {
            System.out.print(atual.valor + " ");
            atual = atual.anterior;
        }
        System.out.println();
    }

    // Remover a primeira ocorrência de um valor (opcional, seguro)
    public boolean remover(int valor) {
        No atual = inicio;
        while (atual != null) {
            if (atual.valor == valor) {
                if (atual.anterior != null) {
                    atual.anterior.proximo = atual.proximo;
                } else {
                    // removendo o início
                    inicio = atual.proximo;
                }

                if (atual.proximo != null) {
                    atual.proximo.anterior = atual.anterior;
                } else {
                    // removendo o fim
                    fim = atual.anterior;
                }
                return true; // removido
            }
            atual = atual.proximo;
        }
        return false; // não encontrado
    }

    // Programa principal para testar
    public static void main(String[] args) {
        ListaDuplaSimples lista = new ListaDuplaSimples();

        // Adiciona itens
        lista.adicionar(10);
        lista.adicionar(20);
        lista.adicionar(30);

        // Mostra ambos os sentidos
        lista.mostrarFrente(); // Início → Fim: 10 20 30
        lista.mostrarTras();   // Fim → Início: 30 20 10

        // Testa remoção
        System.out.println("Removendo 20: " + lista.remover(20)); // true
        lista.mostrarFrente(); // Início → Fim: 10 30
        lista.mostrarTras();   // Fim → Início: 30 10

        // Remove início e fim
        System.out.println("Removendo 10: " + lista.remover(10)); // true
        System.out.println("Removendo 30: " + lista.remover(30)); // true
        lista.mostrarFrente(); // Início → Fim:
        lista.mostrarTras();   // Fim → Início:
    }
}
