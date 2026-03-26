## 📊 Tabela de Exemplos com Contador – Torre de Hanói (n = 3)

| Passo | Contador | Chamada Recursiva (Java) | Ação Realizada | Exibição no Console |
|-------|----------|--------------------------|----------------|---------------------|
| 1 | 0 | `moverDiscos(3, 'A', 'C', 'B')` | Chamada inicial | |
| 2 | 0 | `moverDiscos(2, 'A', 'B', 'C')` | Move 2 discos de A → B | |
| 3 | 0 | `moverDiscos(1, 'A', 'C', 'B')` | Caso base – disco 1 | |
| 4 | **1** | `System.out.println(...)` | Move disco 1 de A → C | `"Mover disco 1 de A para C"` |
| 5 | 1 | Retorno da chamada (n=1) | | |
| 6 | 1 | `System.out.println(...)` | Move disco 2 de A → B | `"Mover disco 2 de A para B"` |
| 7 | **2** | `moverDiscos(1, 'C', 'B', 'A')` | Caso base – disco 1 | |
| 8 | **3** | `System.out.println(...)` | Move disco 1 de C → B | `"Mover disco 1 de C para B"` |
| 9 | 3 | Retorno da chamada (n=2) | | |
| 10 | 3 | `System.out.println(...)` | Move disco 3 de A → C | `"Mover disco 3 de A para C"` |
| 11 | **4** | `moverDiscos(2, 'B', 'C', 'A')` | Move 2 discos de B → C | |
| 12 | 4 | `moverDiscos(1, 'B', 'A', 'C')` | Caso base – disco 1 | |
| 13 | **5** | `System.out.println(...)` | Move disco 1 de B → A | `"Mover disco 1 de B para A"` |
| 14 | 5 | `System.out.println(...)` | Move disco 2 de B → C | `"Mover disco 2 de B para C"` |
| 15 | **6** | `moverDiscos(1, 'A', 'C', 'B')` | Caso base – disco 1 | |
| 16 | **7** | `System.out.println(...)` | Move disco 1 de A → C | `"Mover disco 1 de A para C"` |
| 17 | 7 | Retorno da chamada (n=2) | | |
| 18 | 7 | Retorno da chamada (n=3) | Fim do algoritmo | |

---

## 💻 Código Java Completo com Contador

```java
public class TorreDeHanoiComContador {
    
    // Variável estática para contar os movimentos
    static int contador = 0;
    
    public static void moverDiscos(int n, char origem, char destino, char auxiliar) {
        if (n == 1) {
            contador++; // Incrementa o contador antes de exibir
            System.out.println(contador + " - Mover disco 1 de " + origem + " para " + destino);
        } else {
            moverDiscos(n - 1, origem, auxiliar, destino);
            
            contador++; // Incrementa o contador antes de exibir
            System.out.println(contador + " - Mover disco " + n + " de " + origem + " para " + destino);
            
            moverDiscos(n - 1, auxiliar, destino, origem);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Torre de Hanói com " + 3 + " discos ===\n");
        moverDiscos(3, 'A', 'C', 'B');
        System.out.println("\n=== Total de movimentos: " + contador + " ===");
    }
}
```

### Saída esperada:
```
=== Torre de Hanói com 3 discos ===

1 - Mover disco 1 de A para C
2 - Mover disco 2 de A para B
3 - Mover disco 1 de C para B
4 - Mover disco 3 de A para C
5 - Mover disco 1 de B para A
6 - Mover disco 2 de B para C
7 - Mover disco 1 de A para C

=== Total de movimentos: 7 ===
```

---

## 📌 Explicação da Variável `contador`

| Aspecto | Detalhe |
|---------|--------|
| **Tipo** | `static int` – compartilhada por todas as chamadas recursivas |
| **Localização** | Declarada fora do método, no escopo da classe |
| **Incremento** | Ocorre **antes** de cada `System.out.println()`, registrando o movimento |
| **Propósito** | Contar o número total de movimentos realizados |
| **Valor final** | Deve ser igual a \( 2^n - 1 \) (no exemplo com n=3, contador = 7) |

---

## 📊 Comparação para Diferentes Valores de n

| n (discos) | Total de Movimentos | Contador Final |
|------------|---------------------|----------------|
| 1 | \( 2^1 - 1 = 1 \) | 1 |
| 2 | \( 2^2 - 1 = 3 \) | 3 |
| 3 | \( 2^3 - 1 = 7 \) | 7 |
| 4 | \( 2^4 - 1 = 15 \) | 15 |
| 5 | \( 2^5 - 1 = 31 \) | 31 |

---

A variável `contador` é essencial para validar que o algoritmo está gerando exatamente \( 2^n - 1 \) movimentos, confirmando a corretude da implementação recursiva.
