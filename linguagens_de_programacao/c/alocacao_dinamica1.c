/* ============================================================================
 * Programa: Exemplo 01 - Alocação simples de um inteiro com malloc()
 * Descrição: Demonstra como reservar memória para uma única variável inteira
 *            na Heap, atribuir um valor, exibir e liberar a memória.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>
#include <stdlib.h> // Requerido para as funções malloc() e free()

int main(void) {
    // malloc() aloca 4 bytes (tamanho de int) na memória Heap
    int *p = (int *) malloc(sizeof(int));

    // Sempre verifique se o ponteiro retornado não é NULL
    if (p == NULL) {
        printf("Erro: Memória insuficiente!\n");
        return 1; // Encerra o programa se falhar
    }

    *p = 42; // Atribui o valor 42 à memória alocada

    printf("Valor armazenado na Heap: %d\n", *p);
    printf("Endereço de memória: %p\n", (void*)p);

    free(p);   // Libera o bloco de memória de volta ao sistema
    p = NULL;  // Zera o ponteiro para evitar o uso de ponteiro pendente (dangling pointer)

    return 0; // Indica execução com sucesso
}
