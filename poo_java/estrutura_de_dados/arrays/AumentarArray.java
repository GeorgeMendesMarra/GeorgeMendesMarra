public class AumentarArray {

    public static void main(String[] args) {
        // --- Exemplo de como "aumentar" um array de Strings ---

        // 1. Array original com tamanho fixo (6 elementos)
        String[] nomes = {"Lucas", "Daniel", "Julia", "Tatiana", "Izabela", "Matheus"};
        System.out.println("Array original (tamanho " + nomes.length + "): " + java.util.Arrays.toString(nomes));

        // 2. Definir o novo tamanho. Queremos adicionar 3 nomes.
        int novoTamanho = nomes.length + 3;

        // 3. Criar um NOVO array com o tamanho desejado.
        // Ele terá 9 posições, com as 6 primeiras vazias (valor default 'null').
        String[] novoNomes = new String[novoTamanho];

        // 4. Copiar os elementos do array original para o novo array.
        // Sintaxe: System.arraycopy(arrayOrigem, posInicialOrigem, arrayDestino, posInicialDestino, numElementos)
        System.arraycopy(nomes, 0, novoNomes, 0, nomes.length);

        // O array 'novoNomes' agora contém: {"Lucas", "Daniel", "Julia", "Tatiana", "Izabela", "Matheus", null, null, null}
        
        // 5. Adicionar os novos elementos nas posições restantes do novo array.
        // A primeira posição livre é o índice 6.
        novoNomes[6] = "Carlos";
        novoNomes[7] = "Mariana";
        novoNomes[8] = "Fernanda";

        // 6. (Opcional) Fazer a referência 'nomes' apontar para o novo array.
        // Isso é útil se você quiser continuar trabalhando com a variável 'nomes'.
        nomes = novoNomes;

        // 7. Exibir o resultado final.
        System.out.println("Novo array (tamanho " + nomes.length + "): " + java.util.Arrays.toString(nomes));
    }
}
