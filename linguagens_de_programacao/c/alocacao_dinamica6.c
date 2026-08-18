/* ============================================================================
 * Programa: Exemplo 06 - String Dinâmica com malloc()
 * Descrição: Aloca espaço para armazenar uma frase digitada pelo usuário,
 *            lembrando de incluir o byte do caractere nulo ('\0').
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(void) {
    int tam = 50; // Tamanho do buffer da string

    // Aloca espaço para 50 caracteres (char possui 1 byte)
    char *str = (char *) malloc(tam * sizeof(char));

    if (str == NULL) {
        printf("Erro de alocação de memória.\n");
        return 1;
    }

    // Copia com segurança um texto para o espaço alocado
    strncpy(str, "Linguagem C - Alocação Dinâmica", tam - 1);
    str[tam - 1] = '\0'; // Garante o caractere terminador de string

    printf("Conteúdo da String: %s\n", str);
    printf("Tamanho da String: %zu caracteres\n", strlen(str));

    free(str);  // Libera a memória ocupada pela string
    str = NULL; // Anula o ponteiro

    return 0;
}
