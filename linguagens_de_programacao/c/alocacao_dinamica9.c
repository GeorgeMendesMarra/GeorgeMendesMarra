/* ============================================================================
 * Programa: Exemplo 09 - Função auxiliar para alocar e retornar vetor
 * Descrição: Encapsula a lógica de alocação e checagem dentro de uma função,
 *            retornando o ponteiro criado para o programa principal.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>

// Função que aloca e inicializa um vetor com um valor padrão
int* criarVetor(int tam, int valorInicial) {
    int *v = (int *) malloc(tam * sizeof(int));
    if (v == NULL) {
        return NULL; // Retorna NULL em caso de falha na alocação
    }

    for (int i = 0; i < tam; i++) {
        v[i] = valorInicial; // Preenche cada posição
    }

    return v; // Retorna o ponteiro com a memória alocada na Heap
}

int main(void) {
    int tamanho = 4;
    int *meuVetor = criarVetor(tamanho, 7); // Cria vetor de 4 elementos preenchidos com 7

    if (meuVetor == NULL) {
        printf("Erro ao criar vetor.\n");
        return 1;
    }

    for (int i = 0; i < tamanho; i++) {
        printf("meuVetor[%d] = %d\n", i, meuVetor[i]);
    }

    free(meuVetor); // A responsabilidade de liberar a memória é de quem chamou a função
    meuVetor = NULL;

    return 0;
}
