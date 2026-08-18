/* ============================================================================
 * Programa: Exemplo 05 - Somar elementos de um vetor
 * Descrição: Utiliza incremento de ponteiro (ptr++) para iterar pelas posições
 *            de memória de um vetor acumulando o resultado.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>

int main(void) {
    int numeros[] = {1, 2, 3, 4, 5}; // Vetor com 5 números inteiros
    int *ptr = numeros;               // 'ptr' aponta para o primeiro elemento (numeros[0])
    int soma = 0;                     // Variável para acumular o resultado da soma

    for (int i = 0; i < 5; i++) {
        soma += *ptr; // Adiciona o valor apontado atualmente por 'ptr' à variável 'soma'
        ptr++;        // Aritmética de ponteiros: avança 'ptr' para o próximo endereço de memória
    }

    printf("Soma total: %d\n", soma); // Exibe o resultado da soma (15)

    return 0;
}
