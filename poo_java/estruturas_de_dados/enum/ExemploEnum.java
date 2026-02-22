import java.util.Scanner;

// Definição de um enum chamado DiaDaSemana
// Ele contém constantes que representam os dias da semana
enum DiaDaSemana {
    SEGUNDA,
    TERCA,
    QUARTA,
    QUINTA,
    SEXTA,
    SABADO,
    DOMINGO
}

public class ExemploEnum {
    public static void main(String[] args) {
        // Criamos um Scanner para ler a entrada do usuário
        Scanner scanner = new Scanner(System.in);

        // Pedimos para o usuário digitar o dia da semana
        System.out.print("Digite o dia da semana (ex: SEGUNDA, TERCA...): ");
        String entrada = scanner.next().toUpperCase(); 
        // .toUpperCase() transforma em maiúsculo para evitar erro 
        // caso o usuário digite em minúsculo

        try {
            // Convertemos a String digitada para o tipo enum DiaDaSemana
            DiaDaSemana dia = DiaDaSemana.valueOf(entrada);

            // Mostra o dia escolhido
            System.out.println("Você escolheu: " + dia);

            // Usamos switch para mostrar uma mensagem dependendo do dia
            switch (dia) {
                case SEGUNDA:
                    System.out.println("Começo da semana!");
                    break;
                case SEXTA:
                    System.out.println("Sextou!");
                    break;
                case SABADO:
                case DOMINGO:
                    System.out.println("Final de semana!");
                    break;
                default:
                    System.out.println("Dia comum.");
            }
        } catch (IllegalArgumentException e) {
            // Caso o usuário digite algo que não exista no enum
            System.out.println("Dia inválido! Digite um nome correto em maiúsculo (ex: SEGUNDA).");
        }

        // Fecha o Scanner para evitar vazamento de recursos
        scanner.close();
    }
}
