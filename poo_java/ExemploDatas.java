import java.time.LocalDate;                // Importa a classe LocalDate para trabalhar com datas (sem hora)
import java.time.Period;                   // Importa a classe Period para calcular o período entre duas datas
import java.time.format.DateTimeFormatter; // Importa a classe DateTimeFormatter para formatar e converter datas
import java.util.Scanner;                  // Importa a classe Scanner para leitura de dados do teclado

public class ExemploDatas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Cria um objeto Scanner para ler a entrada do usuário

        // Obtém a data atual do sistema
        LocalDate dataAtual = LocalDate.now();
        System.out.println("Data atual: " + dataAtual);
        
        // Solicita ao usuário a data de nascimento no formato dd/MM/yyyy
        System.out.print("Digite sua data de nascimento (formato: dd/MM/yyyy): ");
        String entrada = scanner.nextLine(); // Lê a data digitada como texto

        // Define o formato esperado para converter a String em LocalDate
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataNascimento = LocalDate.parse(entrada, formato); // Converte a String para uma data

        // Calcula a idade como o período entre a data de nascimento e a data atual
        Period idade = Period.between(dataNascimento, dataAtual);

        // Exibe a idade detalhada em anos, meses e dias
        System.out.println("Você tem " + idade.getYears() + " anos, " +
                           idade.getMonths() + " meses e " +
                           idade.getDays() + " dias.");

        scanner.close(); // Encerra o uso do Scanner
    }
}

