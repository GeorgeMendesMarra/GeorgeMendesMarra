/* ============================================================================
 * Programa: Exemplo 03 - Vetor dinâmico de inteiros com malloc()
 * Descrição: Solicita ao usuário o tamanho do vetor, aloca o espaço dinamicamente,
 *            preenche com dados, calcula a soma e libera a memória.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

int main(void) {
    int n, soma = 0; // Variáveis de controle e acumulador

    printf("Digite a quantidade de elementos: ");
    if (scanf("%d", &n) != 1 || n <= 0) {
        printf("Tamanho inválido!\n");
        return 1;
    }

    // Aloca espaço suficiente para 'n' inteiros na memória Heap
    int *v = (int *) malloc(n * sizeof(int));
    if (v == NULL) {
        printf("Falha na alocação de memória.\n");
        return 1;
    }

    // Preenche o vetor dinâmico e acumula a soma
    for (int i = 0; i < n; i++) {
        v[i] = (i + 1) * 10; // Atribui valores (10, 20, 30...)
        soma += v[i];        // Soma o elemento atual
    }

    printf("Soma de todos os elementos: %d\n", soma);

    free(v);  // Libera todo o bloco do vetor de uma só vez
    v = NULL; // Anula o ponteiro

    return 0;
}
