import java.util.Date;
import java.security.MessageDigest;

public class Bloco {
    public String hash;
    public String hashAnterior;
    private String dados; // Aqui ficariam as transações
    private long tempo;
    private int nonce; // Número usado para mineração

    public Bloco(String dados, String hashAnterior) {
        this.dados = dados;
        this.hashAnterior = hashAnterior;
        this.tempo = new Date().getTime();
        this.hash = calcularHash();
    }

    // Algoritmo SHA-256 para gerar a assinatura digital
    public String calcularHash() {
        String input = hashAnterior + Long.toString(tempo) + Integer.toString(nonce) + dados;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Simula a "Mineração" (Proof of Work)
    public void minerarBloco(int dificuldade) {
        String alvo = new String(new char[dificuldade]).replace('\0', '0'); 
        while(!hash.substring(0, dificuldade).equals(alvo)) {
            nonce++;
            hash = calcularHash();
        }
        System.out.println("Bloco Minerado!!! Hash: " + hash);
    }
}
