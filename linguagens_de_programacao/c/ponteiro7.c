/* ============================================================================
 * Programa: Exemplo 07 - Encontrar maior valor em vetor
 * Descrição: Passa um vetor como ponteiro para uma função e localiza o elemento
 *            com o maior valor numérico.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>

// Função recebe o ponteiro do vetor e o número total de elementos
int encontrarMaior(int *arr, int tamanho) {
    int maior = *arr; // Assume temporariamente que o primeiro elemento (*arr) é o maior

    for (int i = 1; i < tamanho; i++) {
        // Compara o elemento atual *(arr + i) com o maior já encontrado
        if (*(arr + i) > maior) {
            maior = *(arr + i); // Atualiza o maior valor se encontrar um elemento superior
        }
    }

    return maior; // Retorna o maior valor encontrado
}

int main(void) {
    int dados[] = {12, 45, 7, 89, 23}; // Vetor de testes
    
    // Chama a função e imprime o maior número
    printf("Maior valor: %d\n", encontrarMaior(dados, 5));

    return 0;
}
