/* ============================================================================
 * Programa: Exemplo 08 - Matriz Dinâmica Bidimensional (Ponteiro duplo)
 * Descrição: Cria uma matriz de 'linhas x colunas' alocando um vetor de ponteiros
 *            e depois alocando cada linha individualmente. Libera tudo ao final.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int linhas = 3, colunas = 3;

    // 1. Aloca um vetor de ponteiros para as linhas (int*)
    int **matriz = (int **) malloc(linhas * sizeof(int *));
    if (matriz == NULL) return 1;

    // 2. Aloca as colunas (int) de cada linha individualmente
    for (int i = 0; i < linhas; i++) {
        matriz[i] = (int *) malloc(colunas * sizeof(int));
        if (matriz[i] == NULL) return 1;
    }

    // Preenche e exibe os elementos da matriz
    for (int i = 0; i < linhas; i++) {
        for (int j = 0; j < colunas; j++) {
            matriz[i][j] = (i + 1) * (j + 1); // Atribui valores
            printf("%2d ", matriz[i][j]);
        }
        printf("\n");
    }

    // 3. Libera a memória na ordem INVERSA da alocação
    for (int i = 0; i < linhas; i++) {
        free(matriz[i]); // Libera a memória de cada linha
    }
    free(matriz); // Libera o vetor de ponteiros principal
    matriz = NULL;

    return 0;
}
