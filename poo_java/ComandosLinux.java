import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ComandosLinux {

    public static void main(String[] args) {
        String comando = "ls -l"; // Comando Linux de exemplo: listar arquivos e diretórios detalhadamente
       

        try {
            // Cria um processo para executar o comando
            Process processo = Runtime.getRuntime().exec(comando);
           

            // Lê a saída do comando
            BufferedReader saida = new BufferedReader(new InputStreamReader(processo.getInputStream()));
            String linha;
            System.out.println("Saída do comando:");
            while ((linha = saida.readLine()) != null) {
                System.out.println(linha);
            }

            // Lê a saída de erro do comando (se houver)
            BufferedReader erro = new BufferedReader(new InputStreamReader(processo.getErrorStream()));
            System.out.println("\nErros do comando (se houver):");
            while ((linha = erro.readLine()) != null) {
                System.err.println(linha);
            }

            // Espera o processo terminar e obtém o código de saída
            int codigoSaida = processo.waitFor();
            System.out.println("\nCódigo de saída do comando: " + codigoSaida);

            if (codigoSaida == 0) {
                System.out.println("Comando executado com sucesso.");
            } else {
                System.out.println("O comando retornou um código de erro.");
            }

        } catch (IOException e) {
            System.err.println("Erro de I/O ao executar o comando: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("O processo foi interrompido: " + e.getMessage());
        }
    }
}
