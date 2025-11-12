import java.util.Scanner;

/**
 * Programa que solicita ao usuário que defina uma senha e depois a confirme.
 * O usuário tem até 3 tentativas para digitar a senha correta.
 * 
 * Exibe:
 * - "Acesso concedido." se acertar a senha;
 * - "Senha incorreta. Tente novamente." para cada erro;
 * - "Acesso bloqueado." após 3 tentativas incorretas.
 * 
 * @author
 * @version 1.0
 */
public class VerificacaoSenha {

    /**
     * Método principal. Controla o processo de definição e verificação da senha.
     * 
     * @param args argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Cria um objeto Scanner para ler entradas do teclado
        Scanner entrada = new Scanner(System.in);

        // Solicita que o usuário defina uma senha inicial
        System.out.print("Defina uma senha: ");
        String senhaDefinida = entrada.nextLine(); // Armazena a senha criada pelo usuário

        // Variável que será usada para armazenar a senha digitada nas tentativas de confirmação
        String tentativa;

        // Define o número máximo de tentativas permitidas
        int tentativasRestantes = 3;

        // Início do laço que controla as tentativas de confirmação da senha
        while (tentativasRestantes > 0) {

            // Pede que o usuário digite novamente a senha para confirmar
            System.out.print("Digite a senha para confirmar: ");
            tentativa = entrada.nextLine(); // Lê a senha digitada pelo usuário

            // Verifica se a senha digitada é igual à senha definida anteriormente
            if (tentativa.equals(senhaDefinida)) {
                // Se forem iguais, exibe mensagem de sucesso e encerra o loop
                System.out.println("Acesso concedido.");
                break; // Sai do laço imediatamente
            } else {
                // Caso a senha esteja incorreta, decrementa o contador de tentativas
                tentativasRestantes--;

                // Verifica se ainda há tentativas restantes
                if (tentativasRestantes > 0) {
                    // Exibe mensagem de erro e informa quantas tentativas restam
                    System.out.println("Senha incorreta. Tente novamente.");
                    System.out.println("Tentativas restantes: " + tentativasRestantes);
                } else {
                    // Se o usuário usou todas as tentativas, bloqueia o acesso
                    System.out.println("Acesso bloqueado. Número máximo de tentativas excedido.");
                }
            }
        }

        // Fecha o objeto Scanner para liberar recursos do sistema
        entrada.close();
    }
}
