/* ============================================================================
 * Programa: Exemplo 04 - Vetor zerado de números reais (float) com calloc()
 * Descrição: Cria um vetor de números de ponto flutuante zerados, permitindo
 *            modificar elementos específicos e calcular a média.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int qtd = 5; // Definição fixa da quantidade de notas
    
    // calloc(5, sizeof(float)) aloca 5 floats inicializados com 0.0f
    float *notas = (float *) calloc(qtd, sizeof(float));

    if (notas == NULL) {
        printf("Erro ao alocar memória.\n");
        return 1;
    }

    // Atribui valores apenas a algumas posições do vetor
    notas[0] = 8.5f;
    notas[1] = 9.0f;
    notas[2] = 7.5f;

    // As posições 3 e 4 permanecem com 0.0f devido ao uso de calloc
    for (int i = 0; i < qtd; i++) {
        printf("Nota [%d]: %.2f\n", i, notas[i]);
    }

    free(notas);  // Desaloca o vetor da memória
    notas = NULL; // Anula o ponteiro

    return 0;
}
