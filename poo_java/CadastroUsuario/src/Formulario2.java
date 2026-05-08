import java.util.Scanner;

/**
 * Formulário de cadastro de usuários via terminal
 * (substitui a versão Swing para ambientes sem interface gráfica)
 */
public class Formulario2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================");
        System.out.println("   Cadastro de Usuários");
        System.out.println("==============================");

        while (true) {
            // 1. Leitura dos campos
            System.out.print("Nome  : ");
            String nome = scanner.nextLine().trim();

            System.out.print("Email : ");
            String email = scanner.nextLine().trim();

            System.out.print("Idade : ");
            String idadeStr = scanner.nextLine().trim();

            // 2. Validar campos vazios
            if (nome.isEmpty() || email.isEmpty() || idadeStr.isEmpty()) {
                System.out.println("❌ Todos os campos são obrigatórios!\n");
                continue;
            }

            // 3. Validar idade
            int idade;
            try {
                idade = Integer.parseInt(idadeStr);
            } catch (NumberFormatException e) {
                System.out.println("❌ Idade deve ser um número válido!\n");
                continue;
            }

            // 4. Criar e salvar o usuário
            Usuario usuario = new Usuario(nome, email, idade);
            UsuarioDAO dao = new UsuarioDAO();

            try {
                boolean salvou = dao.salvar(usuario);
                if (salvou) {
                    System.out.println("✅ Usuário cadastrado com sucesso!");
                } else {
                    System.out.println("❌ Erro ao salvar. Verifique se o email já está cadastrado.");
                }
            } catch (Exception e) {
                System.out.println("❌ Erro inesperado: " + e.getMessage());
            }

            // 5. Perguntar se quer cadastrar outro
            System.out.print("\nCadastrar outro usuário? (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();
            if (!resposta.equals("s")) {
                break;
            }
            System.out.println();
        }

        System.out.println("Programa encerrado.");
        scanner.close();
    }
}
