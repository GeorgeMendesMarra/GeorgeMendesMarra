class Logica {
    // Método principal onde a execução do programa começa
    public static void main(String[] args) {
        
        // --- Operador AND Lógico Regular (&) ---
        // Ele SEMPRE avalia AMBOS os lados (teste("1", false) e teste("2", true)).
        // Ambas as mensagens ("1" e "2") serão impressas.
        if (teste("1", false) & teste("2", true)) System.out.println("false & true entrou"); // false & true -> false (Não entra)

        // --- Operador AND Curto-Circuito (&&) ---
        // Se o primeiro lado for 'false' (teste("3", false)), ele NÃO avalia o segundo.
        // Apenas a mensagem "3" será impressa. A mensagem "4" NÃO.
        if (teste("3", false) && teste("4", true)) System.out.println("false && true entrou"); // false && true -> false (Não entra)
        
        // --- Operador AND Lógico Regular (&) ---
        // Ele SEMPRE avalia AMBOS os lados (teste("5", true) e teste("6", false)).
        // Ambas as mensagens ("5" e "6") serão impressas.
        if (teste("5", true) & teste("6", false)) System.out.println("true & false entrou"); // true & false -> false (Não entra)
        
        // --- Operador AND Curto-Circuito (&&) ---
        // Ambos os lados são avaliados apenas se o primeiro for 'true'.
        // Ambas as mensagens ("7" e "8") serão impressas.
        if (teste("7", true) && teste("8", false)) System.out.println("true && false entrou"); // true && false -> false (Não entra)
        
        // ----------------------------------------------------------------------
        
        // --- Operador OR Lógico Regular (|) ---
        // Ele SEMPRE avalia AMBOS os lados (teste("9", false) e teste("10", true)).
        // Ambas as mensagens ("9" e "10") serão impressas.
        if (teste("9", false) | teste("10", true)) System.out.println("false | true entrou"); // false | true -> true (Entra)
        
        // --- Operador OR Curto-Circuito (||) ---
        // Ambos os lados são avaliados apenas se o primeiro for 'false'.
        // Ambas as mensagens ("11" e "12") serão impressas.
        if (teste("11", false) || teste("12", true)) System.out.println("false || true entrou"); // false || true -> true (Entra)
        
        // --- Operador OR Lógico Regular (|) ---
        // Ele SEMPRE avalia AMBOS os lados (teste("13", true) e teste("14", false)).
        // Ambas as mensagens ("13" e "14") serão impressas.
        if (teste("13", true) | teste("14", false)) System.out.println("true | false entrou"); // true | false -> true (Entra)
        
        // --- Operador OR Curto-Circuito (||) ---
        // Se o primeiro lado for 'true' (teste("15", true)), ele NÃO avalia o segundo.
        // Apenas a mensagem "15" será impressa. A mensagem "16" NÃO.
        if (teste("15", true) || teste("16", false)) System.out.println("true || false entrou"); // true || false -> true (Entra)
    }

    // Método auxiliar que imprime uma mensagem e retorna um valor booleano.
    // Usado para rastrear quais partes das expressões lógicas são executadas.
    public static boolean teste(String mensagem, boolean valor) {
        System.out.println(mensagem);
        return valor;
    }
}
