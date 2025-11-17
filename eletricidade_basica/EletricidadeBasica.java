import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Esta classe {@code EletricidadeBasica} é uma coleção de métodos utilitários
 * estáticos para realizar cálculos fundamentais de eletricidade.
 * * <p>Ela cobre a Lei de Ohm, Leis de Potência, associação de componentes
 * (resistores e capacitores), custo de energia e conceitos físicos
 * fundamentais como a Lei de Coulomb e resistividade.</p>
 *
 * @author
 * @version 1.0
 */
public class EletricidadeBasica {

    // --- Constantes Físicas ---

    /** Carga elementar de um elétron em Coulombs (C). */
    public static final double CARGA_ELEMENTAR_e = 1.602176634e-19;
    
    /** Constante de Coulomb (k) em N·m²/C². */
    public static final double CONSTANTE_COULOMB_k = 8.9875517923e9;

    // --- 1-3: Lei de Ohm (V = R * I) ---

    /**
     * 1. Calcula a Tensão (V) usando a Lei de Ohm.
     * Fórmula: V = R * I
     *
     * @param resistencia A resistência em Ohms (Ω).
     * @param corrente    A corrente em Amperes (A).
     * @return A tensão em Volts (V).
     */
    public static double calcularTensao(double resistencia, double corrente) {
        // Aplicação direta da Lei de Ohm
        return resistencia * corrente;
    }

    /**
     * 2. Calcula a Corrente (I) usando a Lei de Ohm.
     * Fórmula: I = V / R
     *
     * @param tensao      A tensão em Volts (V).
     * @param resistencia A resistência em Ohms (Ω).
     * @return A corrente em Amperes (A).
     */
    public static double calcularCorrente(double tensao, double resistencia) {
        // Evita divisão por zero se a resistência for nula
        if (resistencia == 0) {
            System.err.println("A resistência não pode ser zero para calcular a corrente.");
            return Double.NaN; // Not a Number
        }
        return tensao / resistencia;
    }

    /**
     * 3. Calcula a Resistência (R) usando a Lei de Ohm.
     * Fórmula: R = V / I
     *
     * @param tensao   A tensão em Volts (V).
     * @param corrente A corrente em Amperes (A).
     * @return A resistência em Ohms (Ω).
     */
    public static double calcularResistencia(double tensao, double corrente) {
        // Evita divisão por zero se a corrente for nula
        if (corrente == 0) {
            System.err.println("A corrente não pode ser zero para calcular a resistência.");
            return Double.NaN;
        }
        return tensao / corrente;
    }

    // --- 4-6: Leis de Potência (P) ---

    /**
     * 4. Calcula a Potência (P) usando Tensão e Corrente.
     * Fórmula: P = V * I
     *
     * @param tensao   A tensão em Volts (V).
     * @param corrente A corrente em Amperes (A).
     * @return A potência em Watts (W).
     */
    public static double calcularPotenciaVI(double tensao, double corrente) {
        // Fórmula de potência básica
        return tensao * corrente;
    }

    /**
     * 5. Calcula a Potência (P) usando Corrente e Resistência (Efeito Joule).
     * Fórmula: P = R * I²
     *
     * @param resistencia A resistência em Ohms (Ω).
     * @param corrente    A corrente em Amperes (A).
     * @return A potência em Watts (W).
     */
    public static double calcularPotenciaRI(double resistencia, double corrente) {
        // Potência dissipada (calor) em um resistor
        return resistencia * Math.pow(corrente, 2);
    }

    /**
     * 6. Calcula a Potência (P) usando Tensão e Resistência.
     * Fórmula: P = V² / R
     *
     * @param tensao      A tensão em Volts (V).
     * @param resistencia A resistência em Ohms (Ω).
     * @return A potência em Watts (W).
     */
    public static double calcularPotenciaVR(double tensao, double resistencia) {
        if (resistencia == 0) {
            System.err.println("A resistência não pode ser zero para calcular a potência.");
            return Double.NaN;
        }
        return Math.pow(tensao, 2) / resistencia;
    }

    // --- 7-10: Associação de Componentes ---

    /**
     * 7. Calcula a resistência equivalente (Req) de resistores em SÉRIE.
     * Fórmula: Req = R1 + R2 + ... + Rn
     *
     * @param resistores Um array (ou varargs) de valores de resistência em Ohms (Ω).
     * @return A resistência total equivalente em Ohms (Ω).
     */
    public static double calcularResistoresSerie(double... resistores) {
        double req = 0.0;
        // Na associação em série, a resistência total é a soma das individuais
        for (double r : resistores) {
            req += r;
        }
        return req;
    }

    /**
     * 8. Calcula a resistência equivalente (Req) de resistores em PARALELO.
     * Fórmula: 1/Req = 1/R1 + 1/R2 + ... + 1/Rn
     *
     * @param resistores Um array (ou varargs) de valores de resistência em Ohms (Ω).
     * @return A resistência total equivalente em Ohms (Ω).
     */
    public static double calcularResistoresParalelo(double... resistores) {
        double inversoReq = 0.0;
        // Na associação em paralelo, o inverso da Req é a soma dos inversos
        for (double r : resistores) {
            if (r == 0) {
                // Se um resistor é 0 (curto-circuito), a Req de paralelo é 0.
                return 0.0; 
            }
            inversoReq += (1.0 / r);
        }
        
        // Evita divisão por zero se o array estiver vazio
        if (inversoReq == 0) return 0.0;

        // A Req é o inverso da soma calculada
        return 1.0 / inversoReq;
    }

    /**
     * 9. Calcula a capacitância equivalente (Ceq) de capacitores em SÉRIE.
     * Fórmula: 1/Ceq = 1/C1 + 1/C2 + ... + 1/Cn
     *
     * @param capacitores Um array (ou varargs) de valores de capacitância em Farads (F).
     * @return A capacitância total equivalente em Farads (F).
     */
    public static double calcularCapacitoresSerie(double... capacitores) {
        double inversoCeq = 0.0;
        // A associação em série de capacitores funciona como a de paralelo para resistores
        for (double c : capacitores) {
            if (c == 0) {
                System.err.println("Capacitância não pode ser zero.");
                return Double.NaN;
            }
            inversoCeq += (1.0 / c);
        }

        if (inversoCeq == 0) return 0.0;
        
        return 1.0 / inversoCeq;
    }

    /**
     * 10. Calcula a capacitância equivalente (Ceq) de capacitores em PARALELO.
     * Fórmula: Ceq = C1 + C2 + ... + Cn
     *
     * @param capacitores Um array (ou varargs) de valores de capacitância em Farads (F).
     * @return A capacitância total equivalente em Farads (F).
     */
    public static double calcularCapacitoresParalelo(double... capacitores) {
        double ceq = 0.0;
        // A associação em paralelo de capacitores funciona como a de série para resistores
        for (double c : capacitores) {
            ceq += c;
        }
        return ceq;
    }

    // --- 11-13: Energia e Custo ---

    /**
     * 11. Calcula a Energia Elétrica consumida em Joules (J).
     * Fórmula: E = P * t (onde P é em Watts e t em segundos)
     *
     * @param potencia Potência do dispositivo em Watts (W).
     * @param segundos Tempo de uso em segundos (s).
     * @return A energia consumida em Joules (J).
     */
    public static double calcularEnergiaJoules(double potencia, double segundos) {
        // Joule é a unidade de energia do SI (W * s)
        return potencia * segundos;
    }

    /**
     * 12. Calcula a Energia Elétrica consumida em Kilowatt-hora (kWh).
     * Fórmula: E = P * t (onde P é em kW e t em horas)
     *
     * @param potenciaWatts Potência do dispositivo em Watts (W).
     * @param horas         Tempo de uso em horas (h).
     * @return A energia consumida em Kilowatt-hora (kWh).
     */
    public static double calcularEnergiaKWh(double potenciaWatts, double horas) {
        // 1. Converter Watts para Kilowatts (kW)
        double potenciaKW = potenciaWatts / 1000.0;
        // 2. Calcular a energia (kWh)
        return potenciaKW * horas;
    }

    /**
     * 13. Calcula o Custo do consumo de energia elétrica.
     *
     * @param energiaKWh A energia total consumida em kWh.
     * @param tarifaKWh  O preço (tarifa) de 1 kWh (ex: 0.75 para R$ 0,75).
     * @return O custo total em Reais (R$).
     */
    public static double calcularCustoEnergia(double energiaKWh, double tarifaKWh) {
        // Custo é simplesmente a energia multiplicada pelo preço unitário
        return energiaKWh * tarifaKWh;
    }

    // --- 14-20: Conceitos Fundamentais ---

    /**
     * 14. Calcula a Força Elétrica (F) usando a Lei de Coulomb.
     * Fórmula: F = k * |q1 * q2| / r²
     *
     * @param q1    Carga da primeira partícula em Coulombs (C).
     * @param q2    Carga da segunda partícula em Coulombs (C).
     * @param raio  Distância (raio) entre as cargas em metros (m).
     * @return A força eletrostática em Newtons (N).
     */
    public static double calcularLeiDeCoulomb(double q1, double q2, double raio) {
        if (raio == 0) {
            System.err.println("A distância (raio) não pode ser zero.");
            return Double.NaN;
        }
        // Aplica a fórmula da Lei de Coulomb
        double forca = CONSTANTE_COULOMB_k * (Math.abs(q1 * q2) / Math.pow(raio, 2));
        return forca;
    }
    
    /**
     * 15. Calcula a Carga Elétrica (Q) com base na corrente e no tempo.
     * Fórmula: Q = I * t
     *
     * @param corrente Corrente em Amperes (A).
     * @param segundos Tempo em segundos (s).
     * @return A carga total que fluiu em Coulombs (C).
     */
    public static double calcularCargaPorCorrente(double corrente, double segundos) {
        // Definição de corrente: I = Q / t  =>  Q = I * t
        return corrente * segundos;
    }

    /**
     * 16. Calcula a Carga Elétrica (Q) quantizada.
     * Fórmula: Q = n * e
     *
     * @param numeroDeEletrons O número (n) de elétrons (inteiro).
     * @return A carga total em Coulombs (C).
     */
    public static double calcularCargaQuantizada(long numeroDeEletrons) {
        // A carga é sempre um múltiplo da carga elementar 'e'
        return numeroDeEletrons * CARGA_ELEMENTAR_e;
    }
    
    /**
     * 17. Calcula a Definição de Capacitância (C).
     * Fórmula: C = Q / V
     *
     * @param cargaArmazenada Carga acumulada nas placas em Coulombs (C).
     * @param tensaoAplicada Tensão (ddp) entre as placas em Volts (V).
     * @return A capacitância em Farads (F).
     */
    public static double calcularCapacitancia(double cargaArmazenada, double tensaoAplicada) {
        if (tensaoAplicada == 0) {
             System.err.println("A tensão não pode ser zero para calcular capacitância.");
             return Double.NaN;
        }
        // Definição de capacitância
        return cargaArmazenada / tensaoAplicada;
    }

    /**
     * 18. Calcula o Campo Elétrico (E) gerado por uma carga pontual.
     * Fórmula: E = k * |Q| / r²
     *
     * @param cargaFonte A carga que gera o campo em Coulombs (C).
     * @param distancia Distância da carga fonte ao ponto em metros (m).
     * @return A intensidade do campo elétrico em Newtons por Coulomb (N/C).
     */
    public static double calcularCampoEletrico(double cargaFonte, double distancia) {
         if (distancia == 0) {
            System.err.println("A distância não pode ser zero.");
            return Double.NaN;
        }
        // Fórmula do campo elétrico
        return CONSTANTE_COULOMB_k * (Math.abs(cargaFonte) / Math.pow(distancia, 2));
    }
    
    /**
     * 19. Calcula a Energia Potencial Elétrica (U) armazenada em um capacitor.
     * Fórmula: U = (C * V²) / 2
     *
     * @param capacitancia A capacitância em Farads (F).
     * @param tensao A tensão aplicada ao capacitor em Volts (V).
     * @return A energia armazenada em Joules (J).
     */
    public static double calcularEnergiaCapacitor(double capacitancia, double tensao) {
        // Energia = 1/2 * C * V^2
        return (capacitancia * Math.pow(tensao, 2)) / 2.0;
    }

    /**
     * 20. Calcula a Resistência (R) usando a 2ª Lei de Ohm (Resistividade).
     * Fórmula: R = ρ * (L / A)
     *
     * @param resistividade Resistividade (rho) do material em Ohm-metro (Ω·m).
     * @param comprimento   Comprimento (L) do condutor em metros (m).
     * @param areaSecao     Área da seção transversal (A) em metros quadrados (m²).
     * @return A resistência do condutor em Ohms (Ω).
     */
    public static double calcularResistividade(double resistividade, double comprimento, double areaSecao) {
        if (areaSecao == 0) {
            System.err.println("A área da seção não pode ser zero.");
            return Double.NaN;
        }
        // Fórmula da Segunda Lei de Ohm
        return resistividade * (comprimento / areaSecao);
    }


    // --- Método Principal (main) para Demonstração ---

    /**
     * Método principal para demonstrar o uso de alguns dos cálculos.
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Calculadora de Eletricidade Básica ---");

        try {
            // Exemplo 1: Lei de Ohm (Tensão)
            System.out.println("\n[Exemplo 1: Calcular Tensão (V = R * I)]");
            System.out.print("Digite a resistência (Ohms): ");
            double r = scanner.nextDouble();
            System.out.print("Digite a corrente (Amperes): ");
            double i = scanner.nextDouble();
            double v = calcularTensao(r, i);
            System.out.printf("Resultado: A tensão é %.2f Volts.\n", v);

            // Exemplo 2: Resistores em Paralelo
            System.out.println("\n[Exemplo 2: Resistores em Paralelo (R1=100, R2=200)]");
            double reqParalelo = calcularResistoresParalelo(100.0, 200.0);
            System.out.printf("Resultado: A resistência equivalente é %.2f Ohms.\n", reqParalelo);

            // Exemplo 3: Custo de Energia
            System.out.println("\n[Exemplo 3: Custo de Energia]");
            System.out.print("Digite a potência do aparelho (Watts): ");
            double potencia = scanner.nextDouble();
            System.out.print("Digite o tempo de uso (Horas/dia): ");
            double horas = scanner.nextDouble();
            System.out.print("Digite a tarifa (R$/kWh) (ex: 0,75): ");
            double tarifa = scanner.nextDouble();
            
            // Calcula energia em kWh para 30 dias
            double energiaMensal = calcularEnergiaKWh(potencia, horas * 30);
            double custoMensal = calcularCustoEnergia(energiaMensal, tarifa);
            System.out.printf("Resultado: O custo mensal é R$ %.2f (Consumo: %.2f kWh)\n", custoMensal, energiaMensal);

        } catch (InputMismatchException e) {
            System.err.println("Erro: Entrada inválida. Por favor, insira apenas números.");
        } finally {
            scanner.close();
            System.out.println("\n--- Demonstração Concluída ---");
        }
    }
}
