/* ============================================================================
 * Programa: Exemplo 10 - Alocação Dinâmica de Memória
 * Descrição: Utiliza a função malloc() para reservar memória dinamicamente na
 *            Heap e free() para liberar a memória após o uso.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>
#include <stdlib.h> // Biblioteca necessária para as funções malloc() e free()

int main(void) {
    // malloc() reserva espaço na memória Heap do tamanho de um 'int' (4 bytes)
    // O retorno é convertido (cast) para o tipo ponteiro (int*)
    int *ptr = (int *) malloc(sizeof(int));

    // Verificação de segurança: checa se a alocação foi bem-sucedida
    if (ptr == NULL) {
        printf("Falha ao alocar memória.\n");
        return 1; // Encerra o programa com erro
    }

    *ptr = 42; // Grava o valor 42 na região de memória alocada dinamicamente

    printf("Valor alocado dinamicamente: %d\n", *ptr);

    // Boa prática: libera o bloco de memória que foi alocado com malloc
    free(ptr);

    // Anula o ponteiro para evitar o uso de "ponteiros pendentes" (dangling pointers)
    ptr = NULL;

    return 0;
}
