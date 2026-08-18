/* ============================================================================
 * Programa: Exemplo 02 - Diferença entre malloc() e calloc() (Inicialização em Zero)
 * Descrição: Demonstra como a função calloc() inicializa automaticamente todos
 *            os bytes alocados com o valor zero (0).
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
    // calloc recebe 2 argumentos: número de elementos (1) e tamanho de cada (sizeof(int))
    int *p = (int *) calloc(1, sizeof(int));

    if (p == NULL) {
        printf("Erro ao alocar memória.\n");
        return 1;
    }

    // O valor impresso será garantidamente 0, pois calloc limpa a memória
    printf("Valor inicial garantido pelo calloc: %d\n", *p);

    free(p);  // Libera a memória alocada
    p = NULL; // Anula o ponteiro

    return 0;
}
