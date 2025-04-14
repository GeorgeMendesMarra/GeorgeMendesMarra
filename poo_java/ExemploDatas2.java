// Importa classes da API de datas e entrada de dados
import java.time.LocalDate;                      // Para obter e manipular datas (sem hora)
import java.time.Period;                         // Para calcular a diferença entre duas datas
import java.time.format.DateTimeFormatter;       // Para formatar datas em texto
import java.util.Scanner;                        // Para ler dados do teclado

public class ExemploDatas2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Cria o scanner para ler a entrada do usuário

        // Obtém a data atual do sistema
        LocalDate dataAtual = LocalDate.now();

        // Define o formato desejado: dia/mês/ano
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Converte a data atual para o formato definido
        String dataFormatada = dataAtual.format(formato);
        System.out.println("Data atual: " + dataFormatada); // Exibe a data atual formatada

        // Solicita ao usuário sua data de nascimento
        System.out.print("Digite sua data de nascimento (formato: dd/MM/yyyy): ");
        String entrada = scanner.nextLine(); // Lê a data de nascimento como texto

        // Converte a string para o tipo LocalDate usando o formato especificado
        LocalDate dataNascimento = LocalDate.parse(entrada, formato);

        // Calcula o período entre a data de nascimento e a data atual
        Period idade = Period.between(dataNascimento, dataAtual);

        // Exibe a idade em anos, meses e dias
        System.out.println("Você tem " + idade.getYears() + " anos, " +
                           idade.getMonths() + " meses e " +
                           idade.getDays() + " dias.");

        scanner.close(); // Fecha o Scanner para liberar recursos
    }
}

