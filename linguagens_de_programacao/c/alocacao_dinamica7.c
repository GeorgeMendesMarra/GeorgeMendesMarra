/* ============================================================================
 * Programa: Exemplo 07 - Alocação Dinâmica de Estruturas (Struct)
 * Descrição: Aloca memória na Heap para uma estrutura do tipo 'Pessoa' e acessa
 *            seus campos utilizando o operador seta (->).
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Definição da estrutura Pessoa
typedef struct {
    char nome[40];
    int idade;
} Pessoa;

int main(void) {
    // Aloca espaço na Heap para 1 estrutura Pessoa completa
    Pessoa *p = (Pessoa *) malloc(sizeof(Pessoa));

    if (p == NULL) {
        printf("Erro ao alocar memória para struct.\n");
        return 1;
    }

    // Utiliza o operador '->' para atribuir valores aos campos via ponteiro
    strcpy(p->nome, "Carlos Eduardo");
    p->idade = 25;

    printf("Nome: %s\n", p->nome);
    printf("Idade: %d anos\n", p->idade);

    free(p);  // Libera toda a estrutura alocada
    p = NULL;

    return 0;
}
