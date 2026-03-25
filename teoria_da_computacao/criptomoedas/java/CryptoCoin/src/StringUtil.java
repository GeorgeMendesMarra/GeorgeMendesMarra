import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.ArrayList;
import java.util.Base64;

public class StringUtil {
    
    // Aplica hash SHA-256 a uma string
    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // Aplica assinatura digital ECDSA a uma string usando chave privada
    public static byte[] applyECDSASig(PrivateKey privateKey, String input) {
        Signature dsa;
        try {
            dsa = Signature.getInstance("ECDSA", "BC");
            dsa.initSign(privateKey);
            byte[] strByte = input.getBytes("UTF-8");
            dsa.update(strByte);
            return dsa.sign();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // Verifica assinatura digital
    public static boolean verifyECDSASig(PublicKey publicKey, String data, byte[] signature) {
        try {
            Signature ecdsaVerify = Signature.getInstance("ECDSA", "BC");
            ecdsaVerify.initVerify(publicKey);
            ecdsaVerify.update(data.getBytes("UTF-8"));
            return ecdsaVerify.verify(signature);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    // Converte chave pública para string
    public static String getStringFromKey(Key key) {
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }
    
    // Dificuldade do Proof-of-Work (número de zeros iniciais)
    public static int difficulty = 4;
    
    // Mineração: encontra hash com a dificuldade especificada
    public static String mineBlock(String data, int difficulty) {
        int nonce = 0;
        String hash;
        String target = new String(new char[difficulty]).replace('\0', '0');
        
        do {
            nonce++;
            hash = applySha256(data + nonce);
        } while (!hash.substring(0, difficulty).equals(target));
        
        System.out.println("Bloco minerado! Nonce: " + nonce + " - Hash: " + hash);
        return hash;
    }
}
