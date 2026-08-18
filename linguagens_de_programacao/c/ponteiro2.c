/* ============================================================================
 * Programa: Exemplo 02 - Modificar variável via ponteiro
 * Descrição: Demonstra como alterar o valor de uma variável diretamente na
 *            memória utilizando o operador de desreferenciação (*).
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>

int main(void) {
    int numero = 5;    // Declara 'numero' e inicializa com 5
    int *p = &numero;  // 'p' armazena o endereço da variável 'numero'

    *p = 20;           // Altera diretamente o conteúdo do endereço apontado por 'p' (muda 'numero' para 20)

    // Como alteramos a memória diretamente, 'numero' agora reflete o novo valor
    printf("Novo valor de numero: %d\n", numero); // Imprime 20

    return 0;
}
