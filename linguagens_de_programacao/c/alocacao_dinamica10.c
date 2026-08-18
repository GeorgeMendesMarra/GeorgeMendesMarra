/* ============================================================================
 * Programa: Exemplo 10 - Buffer/Vetor dinâmico auto-expansível
 * Descrição: Simula uma lista dinâmica que duplica sua capacidade com realloc()
 *            sempre que atinge o limite máximo de inserções.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int capacidade = 2; // Capacidade inicial do vetor
    int qtd = 0;        // Quantidade de elementos inseridos até o momento

    // Alocação inicial para 2 inteiros
    int *lista = (int *) malloc(capacidade * sizeof(int));
    if (lista == NULL) return 1;

    // Simula a inserção de 5 elementos em um vetor com capacidade inicial 2
    for (int i = 1; i <= 5; i++) {
        if (qtd == capacidade) {
            capacidade *= 2; // Duplica a capacidade (2 -> 4 -> 8...)
            int *temp = (int *) realloc(lista, capacidade * sizeof(int));
            if (temp == NULL) {
                free(lista); // Libera caso falhe o redimensionamento
                return 1;
            }
            lista = temp;
            printf("-- Capacidade expandida para: %d --\n", capacidade);
        }

        lista[qtd] = i * 100; // Insere o novo dado
        qtd++;                // Incrementa a quantidade
    }

    printf("\nElementos armazenados (%d total):\n", qtd);
    for (int i = 0; i < qtd; i++) {
        printf("lista[%d] = %d\n", i, lista[i]);
    }

    free(lista); // Libera o vetor dinâmico expandido
    lista = NULL;

    return 0;
}
