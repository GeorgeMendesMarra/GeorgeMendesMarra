// ==========================================
// CLASSE PRODUTO (molde para criar produtos)
// ==========================================

// class = criando um novo tipo de objeto
// Produto = nome da classe (sempre com letra maiúscula)
// SEM o "public" = esta classe só pode ser usada dentro deste arquivo
class Produto {
    
    // ==========================================
    // ATRIBUTOS (características do produto)
    // ==========================================
    
    // private = só o código DENTRO desta classe pode acessar
    // String = tipo texto
    // nome = nome da variável que guarda o nome do produto
    private String nome;
    
    // double = tipo número decimal (pode ter casas decimais)
    // preco = nome da variável que guarda o preço
    private double preco;
    
    // ==========================================
    // GETTER DO NOME (método que PEGA o valor do nome)
    // ==========================================
    
    // public = qualquer um pode chamar este método
    // String = este método devolve um texto
    // getNome = nome do método (padrão: get + NomeDoAtributo)
    public String getNome() {
        // return = devolver algo
        // nome = a variável que guarda o nome
        // Este método simplesmente devolve o valor guardado em "nome"
        return nome;
    }
    
    // ==========================================
    // SETTER DO NOME (método que COLOCA/ALTERA o valor do nome)
    // ==========================================
    
    // public = qualquer um pode chamar
    // void = este método NÃO devolve nada (só executa ações)
    // setNome = nome do método (padrão: set + NomeDoAtributo)
    // (String nome) = parâmetro: recebe um texto que será o novo nome
    public void setNome(String nome) {
        
        // ===== VALIDAÇÃO: verifica se o nome é válido =====
        
        // nome == null => o usuário passou "nada" como nome?
        // || significa "OU"
        // nome.trim() => remove espaços do começo e fim
        // .isEmpty() => verifica se ficou vazio (sem letras)
        if (nome == null || nome.trim().isEmpty()) {
            
            // throw = lançar um erro (interrompe o programa)
            // new = criar um novo objeto do tipo erro
            // IllegalArgumentException = erro de parâmetro inválido
            // ("Nome inválido") = mensagem do erro
            throw new IllegalArgumentException("Nome inválido");
            // O programa PARA aqui se o nome for inválido
            // As linhas abaixo NÃO são executadas
        }
        
        // ===== SE PASSOU NA VALIDAÇÃO, guarda o nome =====
        
        // this.nome = a variável da classe (private String nome)
        // nome = o valor que veio no parâmetro
        // this = "este objeto" - para diferenciar a variável da classe do parâmetro
        this.nome = nome;
        
        // Agora o atributo "nome" foi atualizado com o novo valor válido
    }
    
    // ==========================================
    // GETTER DO PREÇO
    // ==========================================
    
    // public = qualquer um pode acessar
    // double = devolve um número decimal
    // getPreco = nome do método
    public double getPreco() {
        // Simplesmente devolve o valor guardado em "preco"
        return preco;
    }
    
    // ==========================================
    // SETTER DO PREÇO
    // ==========================================
    
    // public = qualquer um pode chamar
    // void = não devolve nada
    // (double preco) = recebe um número decimal como parâmetro
    public void setPreco(double preco) {
        
        // ===== VALIDAÇÃO: preço negativo? =====
        
        // if = SE
        // preco < 0 = o valor recebido é menor que zero?
        if (preco < 0) {
            // this.preco = variável da classe
            // = 0 = guarda o valor 0 (zero)
            this.preco = 0;
            // Suponha que o usuário passou -100
            // O programa guarda 0 ao invés de -100
            // Assim nunca teremos preço negativo!
            
        } else {
            // else = SENÃO (se o preço NÃO for negativo)
            // Guarda o valor normal que veio no parâmetro
            this.preco = preco;
            // Exemplo: se veio 1500, guarda 1500
        }
    }
}

// ==========================================
// CLASSE MAIN (programa principal)
// ==========================================

// public = pode ser acessada de fora
// class Main = criando a classe principal
// O arquivo DEVE se chamar Main.java por causa do "public"
public class Getter_Setter {
    
    // ==========================================
    // MÉTODO MAIN (ponto de entrada do programa)
    // ==========================================
    
    // public = acessível de fora
    // static = não precisa criar objeto para usar (o Java chama sozinho)
    // void = não devolve nada
    // main = nome especial que o Java procura para começar
    // String[] args = parâmetro para receber argumentos do terminal
    public static void main(String[] args) {
        
        // ===== CRIANDO UM PRODUTO =====
        
        // Produto = tipo da variável (classe Produto)
        // produto = nome da variável (pode ser qualquer nome)
        // new = palavra mágica que CRIA um novo objeto
        // Produto() = chama o construtor padrão (cria um produto vazio)
        Produto produto = new Produto();
        
        // Neste momento:
        // produto.nome = null (vazio)
        // produto.preco = 0.0 (zero)
        
        // ===== USANDO O SETTER PARA DEFINIR O NOME =====
        
        // produto.setNome() = chama o método setNome do objeto produto
        // "Celular" = texto que será o novo nome
        produto.setNome("Celular");
        
        // O que acontece dentro de setNome():
        // 1. Verifica se "Celular" é null? NÃO
        // 2. Verifica se "Celular".trim().isEmpty()? "Celular" tem letras? SIM
        // 3. Passou na validação
        // 4. Guarda "Celular" no atributo produto.nome
        
        // ===== USANDO O SETTER PARA DEFINIR O PREÇO =====
        
        // produto.setPreco() = chama o método setPreco
        // 1500.00 = valor do preço
        produto.setPreco(1500.00);
        
        // O que acontece dentro de setPreco():
        // 1. Verifica se 1500.00 < 0? NÃO (1500 é maior que zero)
        // 2. Entra no else
        // 3. Guarda 1500.00 no atributo produto.preco
        
        // ===== USANDO OS GETTERS PARA LER OS VALORES =====
        
        // System.out.println() = comando para mostrar no console
        // "Produto: " + produto.getNome() = texto fixo + o resultado de getNome()
        // produto.getNome() = chama o método que devolve o nome
        // Devolve "Celular"
        System.out.println("Produto: " + produto.getNome());
        
        // "Preço: R$ " + produto.getPreco() = texto fixo + o preço
        // produto.getPreco() = devolve 1500.00
        System.out.println("Preço: R$ " + produto.getPreco());
        
        // ===== SAÍDA NO CONSOLE =====
        // Produto: Celular
        // Preço: R$ 1500.0
    }
}

// ==========================================
// POR QUE TUDO ISSO É NECESSÁRIO?
// ==========================================

/*
SEM getters e setters (atributos públicos):

class Produto {
    public String nome;  // público - qualquer um acessa
    public double preco;
}

Produto p = new Produto();
p.nome = "";        // PERMITIDO - nome vazio! (problema)
p.preco = -100;     // PERMITIDO - preço negativo! (problema)

COM getters e setters (como fizemos):

Produto p = new Produto();
p.setNome("");      // NÃO PERMITIDO - lança erro!
p.setPreco(-100);   // NÃO PERMITIDO - guarda 0 (corrige o problema)

BENEFÍCIOS:
1. CONTROLE: Você decide o que pode acontecer
2. VALIDAÇÃO: Impede dados errados
3. MANUTENÇÃO: Se precisar mudar a regra, muda SÓ no setter
4. SEGURANÇA: Atributos ficam escondidos (private)
5. PADRÃO: Frameworks (Spring, Hibernate) exigem isso
*/
