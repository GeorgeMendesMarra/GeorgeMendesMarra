public class ExemploTryCatch {

    public static void main(String[] args) {
        
        try {
            // 1. Bloco 'try': Contém o código que pode falhar
            int numerador = 10;
            int denominador = 0; // Isso causará uma exceção!
            
            int resultado = numerador / denominador; // Linha que lança a exceção
            
            // Esta linha NUNCA será executada se a exceção ocorrer
            System.out.println("O resultado da divisão é: " + resultado);
            
        } catch (ArithmeticException e) {
            // 2. Bloco 'catch': Captura e trata a exceção
            
            // Imprime uma mensagem amigável para o usuário
            System.err.println("Erro: Não é possível dividir por zero.");
            
            // (Opcional) Imprime detalhes técnicos da exceção
            System.err.println("Detalhe técnico da exceção: " + e.getMessage());
            
        } finally {
            // 3. Bloco 'finally' (Opcional): Executado sempre, ocorrendo ou não erro
            System.out.println("O bloco try-catch foi finalizado.");
        }
        
        // O programa continua a execução normalmente após o bloco try-catch
        System.out.println("Programa encerrado com sucesso.");
    }
}
