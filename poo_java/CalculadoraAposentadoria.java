import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Classe que calcula o tempo que falta para a aposentadoria
 * com base na idade, sexo e anos de contribuição.
 * Utiliza BufferedReader para receber a entrada de dados do usuário.
 */
public class CalculadoraAposentadoria {

    public static void main(String[] args) {
        // Cria um BufferedReader para ler dados de entrada a partir do console (System.in).
        // InputStreamReader converte bytes de entrada para caracteres.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // Variáveis para armazenar os dados de entrada
        int idade = 0;
        String sexo = "";
        int anosContribuicao = 0;
        
        // Flag booleana que indica se a pessoa já tem os requisitos mínimos
        boolean temDireito = false; 

        // Variáveis para armazenar as regras (Mínimos)
        int idadeMinima;
        int contribuicaoMinima;
        
        // --- 1. Entrada de Dados ---
        System.out.println("--- Verificador de Aposentadoria ---");
        
        // --- Leitura do Sexo ---
        try {
            System.out.print("Digite o seu sexo (M para Masculino, F para Feminino): ");
            // Lê a linha, remove espaços em branco (trim) e converte para maiúsculas (toUpperCase)
            sexo = reader.readLine().trim().toUpperCase();

            // Validação simples para garantir que a entrada é 'M' ou 'F'
            if (!sexo.equals("M") && !sexo.equals("F")) {
                System.err.println("Erro: Sexo inválido. Use 'M' ou 'F'.");
                return; // Encerra o programa
            }

        } catch (IOException e) {
            // Trata erro caso haja falha na leitura da entrada (I/O)
            System.err.println("Erro de leitura: " + e.getMessage());
            return;
        }

        // --- Leitura da Idade e Anos de Contribuição ---
        try {
            System.out.print("Digite a sua idade (apenas números inteiros): ");
            // Lê a linha e converte a string lida para um número inteiro (int)
            idade = Integer.parseInt(reader.readLine().trim());

            System.out.print("Digite a quantidade de anos de contribuição: ");
            anosContribuicao = Integer.parseInt(reader.readLine().trim());
            
        } catch (IOException e) {
            System.err.println("Erro de leitura: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            // Trata erro caso a string lida não possa ser convertida para um número inteiro
            System.err.println("Erro: Entrada inválida. Idade e Anos de Contribuição devem ser números inteiros.");
            return;
        }
        
        // --- 2. Lógica da Aposentadoria (Definição das Regras) ---
        
        if (sexo.equals("F")) {
            // Regras para Mulheres: 60 anos de idade E 30 anos de contribuição
            idadeMinima = 60;
            contribuicaoMinima = 30;
            
            // Verifica se a mulher JÁ atingiu AMBOS os requisitos
            if (idade >= idadeMinima && anosContribuicao >= contribuicaoMinima) {
                temDireito = true;
            }
            
        } else { // Assume que o sexo é "M"
            // Regras para Homens: 65 anos de idade E 35 anos de contribuição
            idadeMinima = 65;
            contribuicaoMinima = 35;
            
            // Verifica se o homem JÁ atingiu AMBOS os requisitos
            if (idade >= idadeMinima && anosContribuicao >= contribuicaoMinima) {
                temDireito = true;
            }
        }
        
        // --- 3. Exibição do Resultado ---
        System.out.println("\n------------------------------------");

        if (temDireito) {
            // Se a flag for verdadeira, exibe a mensagem de sucesso
            System.out.println("Parabéns! Você já tem o direito a aposentadoria.");
            
        } else {
            // Se a flag for falsa, calcula e mostra o tempo que falta
            
            System.out.println("Ainda faltam anos para a sua aposentadoria.");
            System.out.println("Requisitos mínimos (Idade: " + idadeMinima + " anos | Contribuição: " + contribuicaoMinima + " anos)");
            System.out.println("------------------------------------");
            
            // Calcula quantos anos faltam para cada requisito
            int faltaIdade = idadeMinima - idade;
            int faltaContribuicao = contribuicaoMinima - anosContribuicao;

            // Mostra o que falta para a IDADE (se o resultado for positivo)
            if (faltaIdade > 0) {
                System.out.println("Faltam " + faltaIdade + " anos de idade para atingir o mínimo exigido.");
            } else {
                System.out.println("Idade mínima já atingida.");
            }

            // Mostra o que falta para o TEMPO DE CONTRIBUIÇÃO (se o resultado for positivo)
            if (faltaContribuicao > 0) {
                System.out.println("Faltam " + faltaContribuicao + " anos de contribuição.");
            } else {
                System.out.println("Tempo de contribuição mínimo já atingido.");
            }
            
            System.out.println("\nLembrete: Você se aposentará quando atingir AMBOS os requisitos.");
        }
        
        System.out.println("------------------------------------");
    }
}
