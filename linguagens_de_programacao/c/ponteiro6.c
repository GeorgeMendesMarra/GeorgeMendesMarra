/* ============================================================================
 * Programa: Exemplo 06 - Tamanho de uma string com ponteiro
 * Descrição: Percorre uma cadeia de caracteres (char*) até encontrar o caractere
 *            nulo ('\0') utilizando incremento de ponteiro.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>

// Função que recebe um ponteiro para char (início da string)
int tamanhoString(char *str) {
    int tamanho = 0; // Contador de caracteres

    // O laço executa até que o conteúdo no ponteiro seja o caractere terminador de string '\0'
    while (*str != '\0') {
        tamanho++; // Incrementa o contador
        str++;     // Avança o ponteiro para o caractere seguinte
    }

    return tamanho; // Retorna o total de caracteres contados
}

int main(void) {
    char texto[] = "Ponteiros"; // String finalizada automaticamente com '\0' pelo compilador
    
    // Chama a função passando a string e imprime o resultado
    printf("Tamanho: %d\n", tamanhoString(texto));

    return 0;
}
