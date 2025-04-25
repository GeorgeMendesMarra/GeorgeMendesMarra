import java.util.ArrayList; // Importa a classe ArrayList para criar listas dinâmicas.
import java.util.HashSet;  // Importa a classe HashSet para criar conjuntos de elementos únicos.
import java.util.List;   // Importa a interface List para representar listas ordenadas.
import java.util.Set;    // Importa a interface Set para representar conjuntos.

// Enumeração para representar os sinais de uma fórmula no tableau (Verdadeiro ou Falso).
enum Sinal {
    T, // Verdadeiro
    F  // Falso
}

// Classe para representar uma fórmula lógica com um sinal associado.
class FormulaComSinal {
    String formula; // A fórmula lógica (string).
    Sinal sinal;   // O sinal da fórmula (T ou F).

    // Construtor da classe FormulaComSinal.
    public FormulaComSinal(String formula, Sinal sinal) {
        this.formula = formula;
        this.sinal = sinal;
    }

    // Sobrescreve o método toString() para uma representação textual amigável.
    @Override
    public String toString() {
        return sinal + "(" + formula + ")";
    }

    // Sobrescreve o método equals() para comparar duas fórmulas com sinal.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Se for a mesma instância.
        if (o == null || getClass() != o.getClass()) return false; // Se for nulo ou de outra classe.
        FormulaComSinal that = (FormulaComSinal) o; // Faz um cast para FormulaComSinal.
        return formula.equals(that.formula) && sinal == that.sinal; // Compara fórmula e sinal.
    }

    // Sobrescreve o método hashCode() para ser consistente com equals().
    @Override
    public int hashCode() {
        int result = formula.hashCode();
        result = 31 * result + sinal.hashCode();
        return result;
    }
}

// Classe para representar um ramo do tableau semântico.
class Ramo {
    Set<FormulaComSinal> formulas; // Conjunto de fórmulas com sinal no ramo (para evitar duplicações).
    boolean fechado;                // Indica se o ramo está fechado (contém uma contradição).

    // Construtor padrão da classe Ramo.
    public Ramo() {
        this.formulas = new HashSet<>();
        this.fechado = false;
    }

    // Construtor da classe Ramo que inicializa com um conjunto de fórmulas.
    public Ramo(Set<FormulaComSinal> formulas) {
        this.formulas = new HashSet<>(formulas);
        this.fechado = false;
    }

    // Adiciona uma fórmula com sinal ao ramo e verifica se o ramo fechou.
    public void adicionarFormula(FormulaComSinal fc) {
        this.formulas.add(fc);
        verificarFechamento();
    }

    // Verifica se o ramo está fechado (se contém uma fórmula e sua negação com sinais opostos).
    public boolean verificarFechamento() {
        for (FormulaComSinal fc : formulas) {
            String negacao = "";
            Sinal sinalNegacao = (fc.sinal == Sinal.T) ? Sinal.F : Sinal.T; // Sinal oposto.

            // Determina a negação da fórmula.
            if (fc.formula.startsWith("!")) {
                negacao = fc.formula.substring(1); // Se começa com !, a negação é o restante.
            } else {
                negacao = "!" + fc.formula; // Caso contrário, a negação é ! mais a fórmula.
            }

            // Verifica se a negação com o sinal oposto já está no ramo.
            if (formulas.contains(new FormulaComSinal(negacao, sinalNegacao))) {
                this.fechado = true;
                return true; // O ramo está fechado.
            }
        }
        return false; // O ramo não está fechado.
    }

    // Aplica uma regra de expansão a uma fórmula com sinal no ramo.
    public Ramo aplicarRegra(FormulaComSinal fc) {
        if (fechado) {
            return this; // Se o ramo já está fechado, não faz nada.
        }

        Ramo novoRamo = new Ramo(this.formulas); // Cria um novo ramo com as fórmulas existentes.
        String formula = fc.formula;
        Sinal sinal = fc.sinal;

        // Aplica as regras de expansão dependendo do sinal e do conectivo principal da fórmula.
        if (sinal == Sinal.T) {
            if (formula.startsWith("!")) {
                // T(!A) -> F(A)
                novoRamo.adicionarFormula(new FormulaComSinal(formula.substring(1), Sinal.F));
            } else if (formula.contains("&")) {
                // T(A & B) -> T(A), T(B)
                String[] partes = formula.split("&", 2);
                novoRamo.adicionarFormula(new FormulaComSinal(partes[0], Sinal.T));
                novoRamo.adicionarFormula(new FormulaComSinal(partes[1], Sinal.T));
            } else if (formula.contains("|")) {
                // T(A | B) -> ramifica em T(A) e T(B) - não implementado diretamente aqui
                return null; // Indica que a regra de ramificação precisa ser tratada.
            } else if (formula.contains("->")) {
                // T(A -> B) -> ramifica em F(A) e T(B) - não implementado diretamente aqui
                return null; // Indica que a regra de ramificação precisa ser tratada.
            }
        } else if (sinal == Sinal.F) {
            if (formula.startsWith("!")) {
                // F(!A) -> T(A)
                novoRamo.adicionarFormula(new FormulaComSinal(formula.substring(1), Sinal.T));
            } else if (formula.contains("&")) {
                // F(A & B) -> ramifica em F(A) e F(B) - não implementado diretamente aqui
                return null; // Indica que a regra de ramificação precisa ser tratada.
            } else if (formula.contains("|")) {
                // F(A | B) -> F(A), F(B)
                String[] partes = formula.split("\\|", 2);
                novoRamo.adicionarFormula(new FormulaComSinal(partes[0], Sinal.F));
                novoRamo.adicionarFormula(new FormulaComSinal(partes[1], Sinal.F));
            } else if (formula.contains("->")) {
                // F(A -> B) -> T(A), F(B)
                String[] partes = formula.split("->", 2);
                novoRamo.adicionarFormula(new FormulaComSinal(partes[0], Sinal.T));
                novoRamo.adicionarFormula(new FormulaComSinal(partes[1], Sinal.F));
            }
        }
        return novoRamo; // Retorna o novo ramo após a aplicação da regra.
    }

    // Verifica se uma regra pode ser aplicada a uma fórmula com sinal neste ramo.
    public boolean podeAplicarRegra(FormulaComSinal fc) {
        if (fechado || formulas.contains(fc)) {
            return false; // Não pode aplicar regra se o ramo está fechado ou a fórmula já foi processada.
        }
        String formula = fc.formula;
        Sinal sinal = fc.sinal;

        // Verifica se a fórmula tem um conectivo principal que pode ser expandido.
        if (sinal == Sinal.T) {
            return formula.startsWith("!") || formula.contains("&") || formula.contains("|") || formula.contains("->");
        } else if (sinal == Sinal.F) {
            return formula.startsWith("!") || formula.contains("&") || formula.contains("|") || formula.contains("->");
        }
        return false; // Nenhuma regra aplicável.
    }
}

public class TableauxSemanticoProposicional {

    // Função principal para verificar se uma fórmula proposicional é uma tautologia usando Tableaux Semântico.
    public static boolean ehTautologia(String formula) {
        List<Ramo> ramos = new ArrayList<>(); // Lista para armazenar os ramos do tableau.
        ramos.add(new Ramo()); // Inicializa o tableau com um único ramo.
        ramos.get(0).adicionarFormula(new FormulaComSinal(formula, Sinal.F)); // Nega a fórmula inicial para buscar uma contradição.

        int iteracoes = 0; // Contador para evitar loops infinitos.
        while (true) {
            boolean algumRamoExpandiu = false; // Flag para verificar se algum ramo foi expandido nesta iteração.
            List<Ramo> novosRamos = new ArrayList<>(); // Lista para armazenar os ramos após a expansão.

            for (Ramo ramo : ramos) {
                if (ramo.fechado) {
                    novosRamos.add(ramo); // Ramos fechados permanecem fechados.
                    continue;
                }

                FormulaComSinal aplicada = null; // Fórmula à qual uma regra será aplicada.
                for (FormulaComSinal fc : ramo.formulas) {
                    if (ramo.podeAplicarRegra(fc)) {
                        aplicada = fc; // Encontra a primeira fórmula não processada para aplicar uma regra.
                        break;
                    }
                }

                if (aplicada != null) {
                    Ramo resultado = ramo.aplicarRegra(aplicada);
                    if (resultado != null) {
                        novosRamos.add(resultado); // Adiciona o ramo expandido.
                        algumRamoExpandiu = true;
                    } else {
                        // Lógica para regras de ramificação (| com T, -> com T, & com F)
                        // Requer criar novos ramos e adicionar fórmulas apropriadas.
                        // Esta é uma simplificação, a implementação completa precisa tratar isso.
                        return false; // Se uma regra de ramificação não é tratada, o resultado pode ser incorreto.
                    }
                } else {
                    novosRamos.add(ramo); // Se nenhuma regra pode ser aplicada, o ramo permanece como está.
                }
            }

            ramos = novosRamos; // Atualiza a lista de ramos.
            if (!algumRamoExpandiu) {
                break; // Se nenhum ramo foi expandido, o processo termina.
            }
            iteracoes++;
            if (iteracoes > 100) break; // Limite para evitar loops infinitos em casos complexos.
        }

        // Uma fórmula é uma tautologia se e somente se todos os ramos do tableau para sua negação fecham.
        return ramos.stream().allMatch(r -> r.fechado);
    }

    public static void main(String[] args) {
        String formula1 = "P|!P";
        String formula2 = "P&!P";
        String formula3 = "P->P";
        String formula4 = "P->Q";

        System.out.println("É tautologia '" + formula1 + "'? " + ehTautologia(formula1)); // Deve ser true
        System.out.println("É tautologia '" + formula2 + "'? " + ehTautologia(formula2)); // Deve ser false
        System.out.println("É tautologia '" + formula3 + "'? " + ehTautologia(formula3)); // Deve ser true
        System.out.println("É tautologia '" + formula4 + "'? " + ehTautologia(formula4)); // Deve ser false
    }
}
