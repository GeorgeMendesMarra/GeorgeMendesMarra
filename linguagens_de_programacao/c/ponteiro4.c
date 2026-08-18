/* ============================================================================
 * Programa: Exemplo 04 - Percorrer vetor com ponteiros
 * Descrição: Explora o conceito de que o nome de um array é um ponteiro para o
 *            seu primeiro elemento e utiliza aritmética de ponteiros.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>

int main(void) {
    int vetor[3] = {10, 20, 30}; // Declara um vetor de 3 posições
    int *ptr = vetor;            // Em C, o nome do vetor já é um ponteiro para o primeiro elemento (vetor[0])

    for (int i = 0; i < 3; i++) {
        // *(ptr + i) calcula o endereço (início do vetor + i posições) e acessa o valor nessa posição
        printf("Elemento %d: %d\n", i, *(ptr + i));
    }

    return 0;
}
