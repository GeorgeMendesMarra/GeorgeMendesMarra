import java.util.HashMap; // Importa a classe HashMap para criar mapas (chave-valor).
import java.util.Map;     // Importa a interface Map que representa um mapa.
import java.util.Set;     // Importa a interface Set que representa um conjunto de elementos únicos.

// Representa um objeto no nosso domínio (o universo de discurso).
class Objeto {
    String nome; // O nome do objeto.

    // Construtor da classe Objeto.
    public Objeto(String nome) {
        this.nome = nome;
    }

    // Sobrescreve o método toString() para uma representação textual amigável do objeto.
    @Override
    public String toString() {
        return nome;
    }

    // Sobrescreve o método equals() para comparar objetos pelo seu nome.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Se for o mesmo objeto na memória.
        if (obj == null || getClass() != obj.getClass()) return false; // Se for nulo ou de outra classe.
        Objeto objeto = (Objeto) obj; // Faz um cast para o tipo Objeto.
        return nome.equals(objeto.nome); // Compara os nomes dos objetos.
    }

    // Sobrescreve o método hashCode() para ser consistente com o método equals().
    @Override
    public int hashCode() {
        return nome.hashCode(); // Usa o hashCode da String nome.
    }
}

// Interface para representar um predicado que aceita um objeto como argumento e retorna um booleano.
interface PredicadoUnario {
    boolean avaliar(Objeto x); // Método a ser implementado para avaliar o predicado para um objeto.
}

// Interface para representar um predicado que aceita dois objetos como argumentos e retorna um booleano.
interface PredicadoBinario {
    boolean avaliar(Objeto x, Objeto y); // Método a ser implementado para avaliar o predicado para dois objetos.
}

public class LogicaPredicadosSimples {

    public static void main(String[] args) {
        // Definindo nosso domínio de objetos (as entidades sobre as quais falaremos).
        Set<Objeto> dominio = Set.of(
                new Objeto("alice"),
                new Objeto("bob"),
                new Objeto("carol")
        );

        // Definindo alguns predicados unários (propriedades de um único objeto).
        Map<String, PredicadoUnario> predicadosUnarios = new HashMap<>();
        // O predicado "EhHumano" é verdadeiro para "alice" e "bob".
        predicadosUnarios.put("EhHumano", x -> x.nome.equals("alice") || x.nome.equals("bob"));
        // O predicado "EhMulher" é verdadeiro para "alice" e "carol".
        predicadosUnarios.put("EhMulher", x -> x.nome.equals("alice") || x.nome.equals("carol"));

        // Definindo alguns predicados binários (relações entre dois objetos).
        Map<String, PredicadoBinario> predicadosBinarios = new HashMap<>();
        // O predicado "Ama" é verdadeiro se "alice" ama "bob" ou "bob" ama "alice".
        predicadosBinarios.put("Ama", (x, y) -> (x.nome.equals("alice") && y.nome.equals("bob")) ||
                                            (x.nome.equals("bob") && y.nome.equals("alice")));

        // Avaliando algumas expressões simples da lógica de predicados.

        // Criando instâncias de objetos do domínio para usar nas avaliações.
        Objeto alice = new Objeto("alice");
        Objeto carol = new Objeto("carol");
        Objeto bob = new Objeto("bob");

        // Avaliando um predicado unário para um objeto específico.
        System.out.println("EhHumano(alice): " + predicadosUnarios.get("EhHumano").avaliar(alice)); // Saída: true
        System.out.println("EhHumano(carol): " + predicadosUnarios.get("EhHumano").avaliar(carol)); // Saída: false

        // Avaliando um predicado binário para dois objetos específicos.
        System.out.println("Ama(alice, bob): " + predicadosBinarios.get("Ama").avaliar(alice, bob)); // Saída: true
        System.out.println("Ama(bob, carol): " + predicadosBinarios.get("Ama").avaliar(bob, carol)); // Saída: false

        // Simulação rudimentar do quantificador existencial (para um domínio finito).
        // Verifica se existe algum objeto no domínio para o qual o predicado "EhHumano" é verdadeiro.
        boolean existeHumano = false;
        for (Objeto obj : dominio) {
            if (predicadosUnarios.get("EhHumano").avaliar(obj)) {
                existeHumano = true;
                break; // Se encontrarmos um humano, não precisamos verificar os outros.
            }
        }
        System.out.println("Existe x tal que EhHumano(x): " + existeHumano); // Saída: true

        // Simulação rudimentar do quantificador universal (para um domínio finito).
        // Verifica se o predicado "EhHumano" é verdadeiro para todos os objetos no domínio.
        boolean todosSaoHumanos = true;
        for (Objeto obj : dominio) {
            if (!predicadosUnarios.get("EhHumano").avaliar(obj)) {
                todosSaoHumanos = false;
                break; // Se encontrarmos um não humano, não precisamos verificar os outros.
            }
        }
        System.out.println("Para todo x, EhHumano(x): " + todosSaoHumanos); // Saída: false

        // Combinação da avaliação de dois predicados usando um conectivo lógico (AND - && em Java).
        // Verifica se "alice" é tanto humana quanto mulher.
        boolean aliceEhHumanoEMulher = predicadosUnarios.get("EhHumano").avaliar(alice) &&
                                        predicadosUnarios.get("EhMulher").avaliar(alice);
        System.out.println("EhHumano(alice) E EhMulher(alice): " + aliceEhHumanoEMulher); // Saída: true
    }
}
