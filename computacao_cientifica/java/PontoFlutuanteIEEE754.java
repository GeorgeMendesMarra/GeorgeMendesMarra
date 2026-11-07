import java.math.BigDecimal;

/**
 * Sistemas de numeração e ponto flutuante IEEE 754 em Java.
 *
 * Compilar: javac PontoFlutuanteIEEE754.java
 * Executar: java PontoFlutuanteIEEE754
 */
public class PontoFlutuanteIEEE754 {

    public static void main(String[] args) {
        parte1_sistemasDeNumeracao();
        parte2_complementoDeDois();
        parte3_estruturaFloatDouble();
        parte4_decodificacaoManual();
        parte5_valoresEspeciais();
        parte6_problemasDePrecisao();
        parte7_comparacaoSegura();
    }

    // =====================================================================
    // PARTE 1 - SISTEMAS DE NUMERAÇÃO (binário, octal, decimal, hexadecimal)
    // =====================================================================
    static void parte1_sistemasDeNumeracao() {
        titulo("1. Sistemas de numeração");

        // Literais em Java: o prefixo define a base
        int decimal = 42;          // base 10 (sem prefixo)
        int binario = 0b101010;    // base 2  (prefixo 0b)
        int octal   = 052;         // base 8  (prefixo 0 -> cuidado!)
        int hexa    = 0x2A;        // base 16 (prefixo 0x)
        int grande  = 1_000_000;   // '_' é só separador visual

        System.out.println("Todos valem 42? " + (decimal == binario && binario == octal && octal == hexa));
        System.out.println("Literal com separador: " + grande);

        // Conversão de int -> String em outras bases
        System.out.println("42 em binário : " + Integer.toBinaryString(decimal)); // 101010
        System.out.println("42 em octal   : " + Integer.toOctalString(decimal));  // 52
        System.out.println("42 em hexa    : " + Integer.toHexString(decimal));    // 2a
        System.out.println("42 em base 5  : " + Integer.toString(decimal, 5));    // 132

        // Conversão de String (em uma base) -> int
        System.out.println("\"101010\" (base 2)  = " + Integer.parseInt("101010", 2)); // 42
        System.out.println("\"ff\" (base 16)      = " + Integer.parseInt("ff", 16));   // 255
        System.out.println("\"zz\" (base 36)      = " + Integer.parseInt("zz", 36));   // 1295

        // Conversão manual: divisões sucessivas pela base (restos lidos de trás pra frente)
        System.out.println("Manual 42 -> base 2: " + converterParaBase(42, 2)); // 101010
        System.out.println("Manual 255 -> base 16: " + converterParaBase(255, 16)); // FF

        // Frações em binário: 0.625 = 0.101b  (0.5 + 0.125)
        System.out.println("0.625 em binário: " + fracaoParaBinario(0.625, 10)); // 0.101
        // 0.1 é uma dízima periódica em binário (0.000110011001100...)
        System.out.println("0.1   em binário: " + fracaoParaBinario(0.1, 20));
    }

    /** Divisões sucessivas: n / base, guardando os restos. */
    static String converterParaBase(int n, int base) {
        if (n == 0) return "0";
        String digitos = "0123456789ABCDEF";
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(digitos.charAt(n % base)); // resto = próximo dígito (menos significativo primeiro)
            n /= base;
        }
        return sb.reverse().toString();         // inverte: o último resto é o dígito mais significativo
    }

    /** Parte fracionária: multiplica por 2 e pega a parte inteira a cada passo. */
    static String fracaoParaBinario(double frac, int maxBits) {
        StringBuilder sb = new StringBuilder("0.");
        for (int i = 0; i < maxBits && frac != 0; i++) {
            frac *= 2;
            if (frac >= 1) { sb.append('1'); frac -= 1; }
            else           { sb.append('0'); }
        }
        return sb.toString();
    }

    // =====================================================================
    // PARTE 2 - INTEIROS COM SINAL: COMPLEMENTO DE DOIS
    // =====================================================================
    static void parte2_complementoDeDois() {
        titulo("2. Complemento de dois (int de 32 bits)");

        int n = 5;
        // -n = ~n + 1  (inverte todos os bits e soma 1)
        System.out.println(" 5 = " + bin32(n));
        System.out.println("~5 = " + bin32(~n));
        System.out.println("-5 = " + bin32(-n) + "  (== ~5 + 1: " + (-n == ~n + 1) + ")");

        // Faixa de valores: -2^31 até 2^31 - 1
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);

        // Overflow: Java NÃO lança exceção, o valor "dá a volta"
        System.out.println("MAX + 1 = " + (Integer.MAX_VALUE + 1) + " (overflow!)");

        // >> mantém o sinal; >>> preenche com zeros
        System.out.println("-8 >>  1 = " + (-8 >> 1));   // -4
        System.out.println("-8 >>> 28 = " + (-8 >>> 28)); // 15
    }

    // =====================================================================
    // PARTE 3 - ESTRUTURA DO IEEE 754: float (32 bits) e double (64 bits)
    // =====================================================================
    //
    //  float : [ 1 sinal | 8 expoente (bias 127)  | 23 mantissa ]
    //  double: [ 1 sinal | 11 expoente (bias 1023)| 52 mantissa ]
    //
    //  Valor (números normalizados):
    //      (-1)^sinal x 1.mantissa x 2^(expoente - bias)
    //
    //  O "1." inicial é implícito (não é armazenado) - "bit escondido".
    static void parte3_estruturaFloatDouble() {
        titulo("3. Estrutura de float e double");

        float f = 6.5f; // 6.5 = 110.1b = 1.101b x 2^2
        int bitsF = Float.floatToIntBits(f); // devolve o padrão de bits como int

        String s = bin32(bitsF);
        // Separa os campos para visualizar: sinal | expoente | mantissa
        System.out.println("float 6.5 = " + s.charAt(0) + " | " + s.substring(1, 9) + " | " + s.substring(9));
        // Esperado: 0 | 10000001 | 10100000000000000000000
        // expoente = 129 -> 129 - 127 = 2  /  mantissa = .101 -> 1.101b

        double d = -6.5;
        long bitsD = Double.doubleToLongBits(d);
        String sd = bin64(bitsD);
        System.out.println("double -6.5 = " + sd.charAt(0) + " | " + sd.substring(1, 12) + " | " + sd.substring(12));

        // Hexadecimal costuma ser mais legível para ver os bits
        System.out.println("float 6.5 em hexa : 0x" + Integer.toHexString(bitsF));      // 0x40d00000
        System.out.println("double -6.5 em hexa: 0x" + Long.toHexString(bitsD));        // 0xc01a000000000000

        // Java aceita literais de ponto flutuante em hexa: 0x1.ap2 = 1.101b x 2^2 = 6.5
        System.out.println("0x1.ap2 = " + 0x1.ap2);
        System.out.println("Double.toHexString(6.5) = " + Double.toHexString(6.5)); // 0x1.ap2

        // Constantes úteis
        System.out.println("Float.MAX_VALUE  = " + Float.MAX_VALUE);   // ~3.4028235E38
        System.out.println("Float.MIN_VALUE  = " + Float.MIN_VALUE);   // 1.4E-45 (menor subnormal!)
        System.out.println("Float.MIN_NORMAL = " + Float.MIN_NORMAL);  // 1.17549435E-38
        System.out.println("Double.MAX_VALUE = " + Double.MAX_VALUE);  // ~1.797...E308
        System.out.println("Double.MIN_VALUE = " + Double.MIN_VALUE);  // 4.9E-324
    }

    // =====================================================================
    // PARTE 4 - DECODIFICAÇÃO MANUAL DE UM float
    // =====================================================================
    static void parte4_decodificacaoManual() {
        titulo("4. Decodificando um float manualmente");
        decodificar(6.5f);
        decodificar(-0.15625f); // -1.01b x 2^-3
        decodificar(0.1f);      // não é exato!
        decodificar(Float.MIN_VALUE); // subnormal
    }

    static void decodificar(float f) {
        // floatToRawIntBits não "limpa" NaNs, é a visão mais crua dos bits
        int bits = Float.floatToRawIntBits(f);

        int sinal    = bits >>> 31;            // 1 bit mais significativo
        int expoente = (bits >>> 23) & 0xFF;   // próximos 8 bits (máscara 0xFF)
        int mantissa = bits & 0x7FFFFF;        // últimos 23 bits (máscara 0x7FFFFF)

        double fracao = mantissa / (double) (1 << 23); // mantissa como fração em [0, 1)
        double valor;
        String tipo;

        if (expoente == 0xFF) {                 // expoente todo 1
            tipo = (mantissa == 0) ? "Infinito" : "NaN";
            valor = (mantissa == 0) ? (sinal == 0 ? Double.POSITIVE_INFINITY : Double.NEGATIVE_INFINITY)
                                    : Double.NaN;
        } else if (expoente == 0) {             // expoente todo 0 -> zero ou subnormal
            tipo = (mantissa == 0) ? "Zero" : "Subnormal";
            // Subnormais: SEM bit escondido e expoente fixo em 1 - 127 = -126
            valor = Math.pow(2, -126) * fracao;
            if (sinal == 1) valor = -valor;
        } else {                                // caso normal: bit escondido = 1
            tipo = "Normal";
            valor = Math.pow(2, expoente - 127) * (1.0 + fracao);
            if (sinal == 1) valor = -valor;
        }

        System.out.printf("%-12s -> sinal=%d expoente=%3d (real=%4d) mantissa=%s [%s]%n",
                f, sinal, expoente, expoente - 127,
                String.format("%23s", Integer.toBinaryString(mantissa)).replace(' ', '0'), tipo);
        // %s com BigDecimal mostra o valor EXATO armazenado
        System.out.println("             valor reconstruído = " + new BigDecimal(valor));
    }

    // =====================================================================
    // PARTE 5 - VALORES ESPECIAIS
    // =====================================================================
    static void parte5_valoresEspeciais() {
        titulo("5. Valores especiais");

        // Divisão de ponto flutuante por zero NÃO lança exceção
        double posInf = 1.0 / 0.0;   // +Infinity (expoente todo 1, mantissa 0)
        double negInf = -1.0 / 0.0;  // -Infinity
        double nan    = 0.0 / 0.0;   // NaN (expoente todo 1, mantissa != 0)
        System.out.println("1.0/0.0  = " + posInf);
        System.out.println("-1.0/0.0 = " + negInf);
        System.out.println("0.0/0.0  = " + nan);

        // NaN nunca é igual a nada, nem a ele mesmo!
        System.out.println("NaN == NaN       : " + (nan == nan));             // false
        System.out.println("Double.isNaN(nan): " + Double.isNaN(nan));        // true
        System.out.println("Double.valueOf(nan).equals(nan): " + Double.valueOf(nan).equals(nan)); // true

        // Zero com sinal: +0.0 e -0.0 têm bits diferentes, mas == diz que são iguais
        double zeroPos = 0.0, zeroNeg = -0.0;
        System.out.println("0.0 == -0.0        : " + (zeroPos == zeroNeg));                 // true
        System.out.println("equals (boxed)     : " + Double.valueOf(zeroPos).equals(zeroNeg)); // false
        System.out.println("1/+0.0 = " + 1 / zeroPos + " | 1/-0.0 = " + 1 / zeroNeg);       // +Inf | -Inf

        // Overflow e underflow
        System.out.println("MAX_VALUE * 2 = " + Double.MAX_VALUE * 2);   // Infinity
        System.out.println("MIN_VALUE / 2 = " + Double.MIN_VALUE / 2);   // 0.0 (underflow)

        // Raiz de negativo gera NaN
        System.out.println("sqrt(-1) = " + Math.sqrt(-1));
    }

    // =====================================================================
    // PARTE 6 - PROBLEMAS DE PRECISÃO
    // =====================================================================
    static void parte6_problemasDePrecisao() {
        titulo("6. Problemas de precisão");

        // 0.1 e 0.2 são dízimas em binário -> são arredondados
        System.out.println("0.1 + 0.2 = " + (0.1 + 0.2));            // 0.30000000000000004
        System.out.println("0.1 + 0.2 == 0.3 ? " + (0.1 + 0.2 == 0.3)); // false

        // Valor EXATO que o double 0.1 realmente guarda
        System.out.println("0.1 exato = " + new BigDecimal(0.1));
        // 0.1000000000000000055511151231257827021181583404541015625

        // float tem ~7 dígitos decimais; double tem ~15-16
        System.out.println("float  1/3 = " + (1.0f / 3));
        System.out.println("double 1/3 = " + (1.0 / 3));

        // float só representa inteiros exatamente até 2^24 = 16.777.216
        float f = 16_777_216f;
        System.out.println("16777216f + 1 = " + (f + 1) + " (o +1 se perdeu!)");

        // Absorção: somar número pequeno a um grande
        System.out.println("1e16 + 1 = " + (1e16 + 1)); // 1.0E16

        // ULP = "unit in the last place": distância até o próximo double
        System.out.println("ulp(1.0)  = " + Math.ulp(1.0));   // 2.22E-16 (= epsilon da máquina)
        System.out.println("ulp(1e16) = " + Math.ulp(1e16));  // 2.0

        // Soma não é associativa
        double a = 1e16, b = -1e16, c = 1.0;
        System.out.println("(a+b)+c = " + ((a + b) + c)); // 1.0
        System.out.println("a+(b+c) = " + (a + (b + c))); // 0.0

        // Acumulando erro em um laço
        double soma = 0;
        for (int i = 0; i < 10; i++) soma += 0.1;
        System.out.println("10 x 0.1 = " + soma + " (esperado 1.0)"); // 0.9999999999999999

        // Dinheiro: use BigDecimal criado a partir de String (nunca de double!)
        BigDecimal x = new BigDecimal("0.1").add(new BigDecimal("0.2"));
        System.out.println("BigDecimal 0.1 + 0.2 = " + x); // 0.3 exato
    }

    // =====================================================================
    // PARTE 7 - COMPARAÇÃO SEGURA
    // =====================================================================
    static void parte7_comparacaoSegura() {
        titulo("7. Comparando números de ponto flutuante");

        double a = 0.1 + 0.2, b = 0.3;

        // Comparação com tolerância absoluta (boa perto de 0)
        double eps = 1e-9;
        System.out.println("Diferença absoluta < eps : " + (Math.abs(a - b) < eps)); // true

        // Comparação com tolerância relativa (boa para grandezas variadas)
        System.out.println("Comparação relativa      : " + quaseIguais(a, b, 1e-12)); // true
        System.out.println("1e20 vs 1e20+1e5         : " + quaseIguais(1e20, 1e20 + 1e5, 1e-12));

        // Double.compare impõe uma ordem total: -0.0 < 0.0 e NaN é o maior
        System.out.println("compare(-0.0, 0.0) = " + Double.compare(-0.0, 0.0)); // -1
        System.out.println("compare(NaN, +Inf) = " + Double.compare(Double.NaN, Double.POSITIVE_INFINITY)); // 1
    }

    static boolean quaseIguais(double x, double y, double epsRelativo) {
        double diff = Math.abs(x - y);
        double maior = Math.max(Math.abs(x), Math.abs(y));
        return diff <= maior * epsRelativo;
    }

    // ============================ utilitários ============================
    static String bin32(int v) {
        return String.format("%32s", Integer.toBinaryString(v)).replace(' ', '0');
    }

    static String bin64(long v) {
        return String.format("%64s", Long.toBinaryString(v)).replace(' ', '0');
    }

    static void titulo(String t) {
        System.out.println("\n==================== " + t + " ====================");
    }
}
