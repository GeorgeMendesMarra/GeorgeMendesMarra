/* ============================================================================
 * Programa: Exemplo 05 - Redimensionar vetor com realloc()
 * Descrição: Aloca um vetor inicial com malloc(), expande seu tamanho usando
 *            realloc() sem perder os dados anteriores e depois libera.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
    // Aloca espaço inicial para 2 inteiros
    int *p = (int *) malloc(2 * sizeof(int));
    if (p == NULL) return 1;

    p[0] = 100; // Define o primeiro valor
    p[1] = 200; // Define o segundo valor

    // Redimensiona o vetor de 2 inteiros para 4 inteiros
    int *temp = (int *) realloc(p, 4 * sizeof(int));

    // Usa um ponteiro temporário para evitar perda de dados se o realloc falhar
    if (temp == NULL) {
        free(p); // Libera o bloco original caso ocorra erro
        return 1;
    }
    p = temp; // Atualiza o ponteiro principal com o novo endereço retornado

    p[2] = 300; // Adiciona os novos dados nas posições expandidas
    p[3] = 400;

    for (int i = 0; i < 4; i++) {
        printf("p[%d] = %d\n", i, p[i]);
    }

    free(p);  // Libera o bloco redimensionado
    p = NULL;

    return 0;
}
