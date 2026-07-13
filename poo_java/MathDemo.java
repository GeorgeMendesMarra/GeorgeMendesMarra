/**
 * MathDemo.java
 *
 * Demonstração dos principais métodos da classe java.lang.Math.
 *
 * Versão do Java: 21 (LTS) — utiliza Math.clamp(), disponível a partir do Java 21.
 * Caso use uma versão anterior (ex.: Java 17), basta remover o método
 * demonstrarMinMaxClamp() (ou só a linha do clamp) e sua chamada no main().
 *
 * Paradigma utilizado: Imperativo / Procedural, com organização em métodos
 * estáticos (procedimentos). Embora Java seja uma linguagem orientada a
 * objetos, a própria classe Math é uma "utility class" — não instanciável,
 * composta somente por métodos estáticos que operam sobre parâmetros e
 * retornam valores, sem manter estado. Este programa segue o mesmo estilo:
 * cada bloco de funcionalidade é um método estático independente, chamado
 * sequencialmente a partir do main(), sem uso de herança, polimorfismo ou
 * encapsulamento de estado (características do paradigma OO "puro").
 */
public class MathDemo {

    /**
     * Ponto de entrada do programa.
     * Chama, em sequência, um método de demonstração para cada
     * categoria de métodos da classe Math.
     */
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAÇÃO DA CLASSE java.lang.Math ===\n");

        demonstrarConstantes();              // Math.PI, Math.E
        demonstrarArredondamento();          // ceil, floor, round, rint
        demonstrarValorAbsolutoESinal();     // abs, signum, copySign
        demonstrarMinMaxClamp();             // min, max, clamp
        demonstrarPotenciaERaiz();           // pow, sqrt, cbrt, fma
        demonstrarLogaritmosEExponencial();  // exp, log, log10, log1p, expm1
        demonstrarTrigonometria();           // sin, cos, tan, asin, acos, atan, atan2
        demonstrarHiperbolicas();            // sinh, cosh, tanh
        demonstrarConversaoAngulos();        // toRadians, toDegrees
        demonstrarDivisaoModuloInteiro();    // floorDiv, floorMod
        demonstrarAleatorioEDistancia();     // random, hypot
        demonstrarPontoFlutuanteAvancado();  // ulp, nextUp, nextDown, nextAfter, getExponent, IEEEremainder, multiplyHigh

        System.out.println("\n=== FIM DA DEMONSTRAÇÃO ===");
    }

    /**
     * Demonstra as duas constantes matemáticas fundamentais oferecidas pela classe Math.
     */
    static void demonstrarConstantes() {
        System.out.println("--- Constantes ---");

        // Math.PI: valor aproximado de π (pi), usado em cálculos de círculos, trigonometria etc.
        System.out.println("Math.PI = " + Math.PI);

        // Math.E: valor aproximado do número de Euler (base do logaritmo natural).
        System.out.println("Math.E  = " + Math.E);

        System.out.println();
    }

    /**
     * Demonstra os métodos de arredondamento de valores em ponto flutuante.
     */
    static void demonstrarArredondamento() {
        System.out.println("--- Arredondamento ---");
        double x = 4.7;
        double y = -4.7;

        // Math.ceil(x): arredonda para CIMA, retornando o menor double
        // que seja um inteiro e maior ou igual a x. Resultado: 5.0
        System.out.println("Math.ceil(" + x + ") = " + Math.ceil(x));

        // Math.floor(x): arredonda para BAIXO, retornando o maior double
        // que seja um inteiro e menor ou igual a x. Resultado: 4.0
        System.out.println("Math.floor(" + x + ") = " + Math.floor(x));

        // Math.round(x): arredonda para o inteiro mais próximo (long/int),
        // com regra de "meio para cima" (0.5 arredonda para cima). Resultado: 5
        System.out.println("Math.round(" + x + ") = " + Math.round(x));

        // Math.rint(x): arredonda para o inteiro (double) mais próximo,
        // mas em caso de empate (ex.: 2.5) escolhe o inteiro PAR mais próximo.
        System.out.println("Math.rint(" + x + ") = " + Math.rint(x));

        // Testando arredondamento com número negativo:
        // Math.floor(-4.7) vai para -5.0 (o menor valor, mais "para baixo" na reta numérica).
        System.out.println("Math.floor(" + y + ") = " + Math.floor(y));

        // Math.ceil(-4.7) vai para -4.0 (o menor valor inteiro que ainda é >= -4.7).
        System.out.println("Math.ceil(" + y + ") = " + Math.ceil(y));

        System.out.println();
    }

    /**
     * Demonstra os métodos relacionados a valor absoluto e sinal de um número.
     */
    static void demonstrarValorAbsolutoESinal() {
        System.out.println("--- Valor Absoluto e Sinal ---");

        // Math.abs(int): retorna o valor absoluto (sem sinal) de um inteiro.
        System.out.println("Math.abs(-7) = " + Math.abs(-7));

        // Math.abs(double): mesma ideia, mas para ponto flutuante.
        System.out.println("Math.abs(-7.5) = " + Math.abs(-7.5));

        // Math.signum(double): retorna -1.0 se o número é negativo,
        // 1.0 se é positivo, e 0.0 se é zero. Indica apenas o SINAL.
        System.out.println("Math.signum(-9.0) = " + Math.signum(-9.0));
        System.out.println("Math.signum(9.0)  = " + Math.signum(9.0));

        // Math.copySign(magnitude, sinal): retorna o primeiro valor (magnitude),
        // mas com o sinal do segundo valor. Aqui: magnitude 3.0 com sinal de -1.0 => -3.0
        System.out.println("Math.copySign(3.0, -1.0) = " + Math.copySign(3.0, -1.0));

        System.out.println();
    }

    /**
     * Demonstra os métodos de comparação (mínimo/máximo) e a limitação de
     * um valor dentro de um intervalo (clamp).
     */
    static void demonstrarMinMaxClamp() {
        System.out.println("--- Min, Max e Clamp ---");

        // Math.min(a, b): retorna o menor entre os dois valores.
        System.out.println("Math.min(3, 8) = " + Math.min(3, 8));

        // Math.max(a, b): retorna o maior entre os dois valores.
        System.out.println("Math.max(3, 8) = " + Math.max(3, 8));

        // Math.clamp(valor, mínimo, máximo): "prende" o valor dentro do intervalo
        // [mínimo, máximo]. Se o valor ultrapassar o limite, retorna o próprio limite.
        // Disponível a partir do Java 21.
        System.out.println("Math.clamp(15, 0, 10) = " + Math.clamp(15, 0, 10)); // ultrapassou o máximo -> 10
        System.out.println("Math.clamp(-5, 0, 10) = " + Math.clamp(-5, 0, 10)); // abaixo do mínimo -> 0

        System.out.println();
    }

    /**
     * Demonstra os métodos de potenciação e extração de raízes.
     */
    static void demonstrarPotenciaERaiz() {
        System.out.println("--- Potência e Raiz ---");

        // Math.pow(base, expoente): calcula base elevado a expoente (2^10 = 1024).
        System.out.println("Math.pow(2, 10) = " + Math.pow(2, 10));

        // Math.sqrt(x): calcula a raiz quadrada de x.
        System.out.println("Math.sqrt(64) = " + Math.sqrt(64));

        // Math.cbrt(x): calcula a raiz cúbica de x (funciona também com negativos).
        System.out.println("Math.cbrt(27) = " + Math.cbrt(27));

        // Math.fma(a, b, c): calcula (a * b) + c com um único arredondamento,
        // o que é mais preciso do que fazer a multiplicação e a soma separadamente.
        System.out.println("Math.fma(2.0, 3.0, 1.0) = " + Math.fma(2.0, 3.0, 1.0));

        System.out.println();
    }

    /**
     * Demonstra os métodos de logaritmo e função exponencial.
     */
    static void demonstrarLogaritmosEExponencial() {
        System.out.println("--- Logaritmos e Exponencial ---");

        // Math.exp(x): calcula e^x (o número de Euler elevado a x).
        System.out.println("Math.exp(1) = " + Math.exp(1));

        // Math.log(x): calcula o logaritmo natural (base e) de x.
        System.out.println("Math.log(Math.E) = " + Math.log(Math.E));

        // Math.log10(x): calcula o logaritmo na base 10 de x.
        System.out.println("Math.log10(1000) = " + Math.log10(1000));

        // Math.log1p(x): calcula log(1 + x) com mais precisão do que
        // fazer Math.log(1 + x) manualmente, especialmente para x próximo de 0.
        System.out.println("Math.log1p(0) = " + Math.log1p(0));

        // Math.expm1(x): calcula e^x - 1 com mais precisão do que
        // Math.exp(x) - 1 manualmente, especialmente para x próximo de 0.
        System.out.println("Math.expm1(0) = " + Math.expm1(0));

        System.out.println();
    }

    /**
     * Demonstra as funções trigonométricas básicas e suas inversas.
     */
    static void demonstrarTrigonometria() {
        System.out.println("--- Trigonometria ---");
        double angulo = Math.PI / 4; // 45 graus, em radianos

        // Math.sin(x): seno de x (x em radianos).
        System.out.println("Math.sin(PI/4) = " + Math.sin(angulo));

        // Math.cos(x): cosseno de x (x em radianos).
        System.out.println("Math.cos(PI/4) = " + Math.cos(angulo));

        // Math.tan(x): tangente de x (x em radianos).
        System.out.println("Math.tan(PI/4) = " + Math.tan(angulo));

        // Math.asin(x): arco-seno de x, retorna o ângulo (em radianos) cujo seno é x.
        System.out.println("Math.asin(1.0) = " + Math.asin(1.0));

        // Math.acos(x): arco-cosseno de x, retorna o ângulo (em radianos) cujo cosseno é x.
        System.out.println("Math.acos(1.0) = " + Math.acos(1.0));

        // Math.atan(x): arco-tangente de x, retorna o ângulo (em radianos) cuja tangente é x.
        System.out.println("Math.atan(1.0) = " + Math.atan(1.0));

        // Math.atan2(y, x): calcula o ângulo (em radianos) entre o eixo X positivo
        // e o ponto (x, y), considerando corretamente o quadrante do ponto.
        System.out.println("Math.atan2(1.0, 1.0) = " + Math.atan2(1.0, 1.0));

        System.out.println();
    }

    /**
     * Demonstra as funções hiperbólicas (usadas em cálculos de ondas,
     * relatividade, engenharia, entre outros).
     */
    static void demonstrarHiperbolicas() {
        System.out.println("--- Funções Hiperbólicas ---");

        // Math.sinh(x): seno hiperbólico de x.
        System.out.println("Math.sinh(1.0) = " + Math.sinh(1.0));

        // Math.cosh(x): cosseno hiperbólico de x.
        System.out.println("Math.cosh(1.0) = " + Math.cosh(1.0));

        // Math.tanh(x): tangente hiperbólica de x.
        System.out.println("Math.tanh(1.0) = " + Math.tanh(1.0));

        System.out.println();
    }

    /**
     * Demonstra a conversão entre graus e radianos.
     */
    static void demonstrarConversaoAngulos() {
        System.out.println("--- Conversão de Ângulos ---");

        // Math.toRadians(graus): converte um ângulo de graus para radianos.
        System.out.println("Math.toRadians(180) = " + Math.toRadians(180)); // resultado ~ PI

        // Math.toDegrees(radianos): converte um ângulo de radianos para graus.
        System.out.println("Math.toDegrees(Math.PI) = " + Math.toDegrees(Math.PI)); // resultado = 180

        System.out.println();
    }

    /**
     * Demonstra a divisão e o módulo inteiro "arredondados para baixo" (floor),
     * comparando com o comportamento padrão dos operadores / e % do Java.
     */
    static void demonstrarDivisaoModuloInteiro() {
        System.out.println("--- Divisão e Módulo Inteiro (arredondados para baixo) ---");

        // Math.floorDiv(a, b): divisão inteira que arredonda o resultado
        // para baixo (em direção ao infinito negativo), diferente do operador "/" padrão.
        System.out.println("Math.floorDiv(-7, 2) = " + Math.floorDiv(-7, 2)); // -4

        // Math.floorMod(a, b): módulo (resto) compatível com floorDiv;
        // o resultado sempre tem o mesmo sinal do divisor.
        System.out.println("Math.floorMod(-7, 2) = " + Math.floorMod(-7, 2)); // 1

        // Comparação com o operador "/" padrão do Java, que TRUNCA (corta) em vez
        // de arredondar para baixo: -7 / 2 = -3 (e não -4 como no floorDiv).
        System.out.println("(-7 / 2) padrão Java = " + (-7 / 2));

        // Comparação com o operador "%" padrão do Java, que mantém o sinal do dividendo.
        System.out.println("(-7 % 2) padrão Java = " + (-7 % 2));

        System.out.println();
    }

    /**
     * Demonstra a geração de números aleatórios e o cálculo de hipotenusa/distância.
     */
    static void demonstrarAleatorioEDistancia() {
        System.out.println("--- Aleatoriedade e Distância ---");

        // Math.random(): gera um número pseudoaleatório do tipo double,
        // no intervalo [0.0, 1.0), útil para simulações, jogos, sorteios etc.
        System.out.println("Math.random() (0.0 a 1.0) = " + Math.random());

        // Math.hypot(x, y): calcula a hipotenusa de um triângulo retângulo,
        // ou seja, sqrt(x² + y²), evitando problemas de overflow/underflow
        // que poderiam ocorrer ao calcular manualmente.
        System.out.println("Math.hypot(3, 4) = " + Math.hypot(3, 4)); // sqrt(3² + 4²) = 5

        System.out.println();
    }

    /**
     * Demonstra métodos mais avançados, ligados à representação interna
     * de números em ponto flutuante (padrão IEEE 754).
     */
    static void demonstrarPontoFlutuanteAvancado() {
        System.out.println("--- Ponto Flutuante Avançado ---");

        // Math.ulp(x): retorna o "unit in the last place" — o menor incremento
        // possível entre x e o próximo valor double representável.
        System.out.println("Math.ulp(1.0) = " + Math.ulp(1.0));

        // Math.nextUp(x): retorna o menor valor double maior que x
        // (o "próximo" valor representável, em direção ao +infinito).
        System.out.println("Math.nextUp(1.0) = " + Math.nextUp(1.0));

        // Math.nextDown(x): retorna o maior valor double menor que x
        // (o "próximo" valor representável, em direção ao -infinito).
        System.out.println("Math.nextDown(1.0) = " + Math.nextDown(1.0));

        // Math.nextAfter(inicio, direcao): retorna o valor double adjacente a "inicio",
        // na direção do valor "direcao".
        System.out.println("Math.nextAfter(1.0, 2.0) = " + Math.nextAfter(1.0, 2.0));

        // Math.getExponent(x): retorna o expoente binário não enviesado de x,
        // conforme a representação IEEE 754. Para 8.0 = 1.0 * 2^3, o expoente é 3.
        System.out.println("Math.getExponent(8.0) = " + Math.getExponent(8.0));

        // Math.IEEEremainder(a, b): calcula o resto da divisão de a por b,
        // seguindo a definição do padrão IEEE 754 (diferente do operador %).
        System.out.println("Math.IEEEremainder(10, 3) = " + Math.IEEEremainder(10, 3));

        // Math.multiplyHigh(a, b): multiplica dois valores long e retorna
        // os 64 bits MAIS SIGNIFICATIVOS do resultado de 128 bits,
        // útil para multiplicações de precisão estendida.
        System.out.println("Math.multiplyHigh(1000000000000L, 2L) = " + Math.multiplyHigh(1000000000000L, 2L));

        System.out.println();
    }
}
