import java.util.Scanner;

/**
 * ⏰ Classe ConversaoTempo
 * <p>
 * Este programa lê um valor inteiro em minutos fornecido pelo usuário e converte
 * para horas e minutos. A conversão é feita utilizando divisão e resto da divisão.
 * </p>
 *
 * <pre>
 * horas = minutosTotais / 60
 * minutosRestantes = minutosTotais % 60
 * </pre>
 *
 * <p><b>Autor:</b> Professor George Mendes Marra</p>
 * <p><b>Disciplina:</b> Programação Orientada a Objetos</p>
 */
public class ConversaoTempo {

    /**
     * Método principal — ponto de entrada do programa.
     *
     * @param args Argumentos da linha de comando (não utilizados neste programa)
     */
    public static void main(String[] args) {

        // Cria um objeto Scanner para leitura dos dados digitados
        Scanner input = new Scanner(System.in);

        // --- Entrada de dados ---
        System.out.print("Digite o tempo total em minutos: ");
        int minutosTotais = input.nextInt(); // Lê o valor em minutos

        // --- Processamento ---
        // Divide o total de minutos por 60 para obter as horas inteiras
        int horas = minutosTotais / 60;

        // Usa o operador % (módulo) para obter os minutos restantes
        int minutosRestantes = minutosTotais % 60;

        // --- Saída de dados ---
        System.out.println("\n⏱ Conversão de Minutos para Horas e Minutos ⏱");
        System.out.println("Tempo total: " + minutosTotais + " minutos");
        System.out.println("Equivale a: " + horas + " hora(s) e " + minutosRestantes + " minuto(s)");

        // Fecha o Scanner
        input.close();
    }
}
