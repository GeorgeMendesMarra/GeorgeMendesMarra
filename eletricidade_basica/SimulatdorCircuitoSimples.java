/**
 * SimpleCircuitSimulator
 * Exemplo didático que resolve um sistema linear para correntes em duas malhas
 * usando métodos simples (Cramer's rule). Não é um simulador completo.
 *
 * As equações fornecidas devem estar na forma Ax = b.
 */
public class SimulatdorCircuitoSimples {

    /** Resolve 2x2 por regra de Cramer */
    public static double[] solve2x2(double a11, double a12, double a21, double a22, double b1, double b2) {
        double det = a11 * a22 - a12 * a21;
        if (Math.abs(det) < 1e-12) throw new IllegalArgumentException("Sistema singular.");
        double x = (b1 * a22 - a12 * b2) / det;
        double y = (a11 * b2 - b1 * a21) / det;
        return new double[]{x, y};
    }

    public static void main(String[] args) {
        // Exemplo: duas malhas com resistências e fontes (valores hipotéticos)
        //  R11 * I1 + R12 * I2 = V1
        //  R21 * I1 + R22 * I2 = V2
        double[] sol = solve2x2(10, 2, 2, 5, 12, 0); // apenas exemplo
        System.out.println("I1 = " + sol[0] + " A, I2 = " + sol[1] + " A");
    }
}
