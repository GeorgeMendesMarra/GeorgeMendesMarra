import java.util.ArrayList;

public class CriptoMoeda {
    public static ArrayList<Bloco> blockchain = new ArrayList<>();
    public static int dificuldade = 4; // Quantidade de zeros iniciais no hash

    public static void main(String[] args) {
        // Criando o Bloco Gênesis (o primeiro)
        System.out.println("Minerando bloco 1...");
        adicionarBloco(new Bloco("Transação: Alice enviou 10 moedas para Bob", "0"));

        System.out.println("Minerando bloco 2...");
        adicionarBloco(new Bloco("Transação: Bob enviou 5 moedas para Charlie", blockchain.get(blockchain.size()-1).hash));

        System.out.println("\nA Blockchain é válida? " + isChainValid());
    }

    public static void adicionarBloco(Bloco novoBloco) {
        novoBloco.minerarBloco(dificuldade);
        blockchain.add(novoBloco);
    }

    public static Boolean isChainValid() {
        Bloco blocoAtual;
        Bloco blocoAnterior;

        for(int i=1; i < blockchain.size(); i++) {
            blocoAtual = blockchain.get(i);
            blocoAnterior = blockchain.get(i-1);

            // Verifica se o hash atual foi alterado
            if(!blocoAtual.hash.equals(blocoAtual.calcularHash())) return false;
            // Verifica se o hash anterior confere
            if(!blocoAtual.hashAnterior.equals(blocoAnterior.hash)) return false;
        }
        return true;
    }
}
