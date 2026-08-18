/* ============================================================================
 * Programa: Exemplo 09 - Ponteiro para Ponteiro (Dupla Indireção)
 * Descrição: Demonstra como declarar e utilizar um ponteiro que armazena o
 *            endereço de outro ponteiro.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>

int main(void) {
    int valor = 100;     // Declara uma variável inteira comum
    int *p1 = &valor;    // 'p1' guarda o endereço de memória de 'valor'
    int **p2 = &p1;      // 'p2' é um ponteiro duplo que guarda o endereço de memória do ponteiro 'p1'

    printf("Valor direto: %d\n", valor); // Acessa diretamente a variável
    printf("Via p1: %d\n", *p1);         // Desreferencia 'p1' para obter o valor de 'valor'
    printf("Via p2: %d\n", **p2);        // Desreferencia duas vezes 'p2' para obter o valor final de 'valor'

    return 0;
}
