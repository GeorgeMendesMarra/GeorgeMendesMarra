/* ============================================================================
 * Programa: Exemplo 01 - Declarar e imprimir um ponteiro
 * Descrição: Demonstra como declarar um ponteiro, armazenar o endereço de uma
 *            variável e acessar seu valor e endereço de memória.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>

int main(void) {
    int x = 10;        // Declara uma variável inteira 'x' e atribui o valor 10
    int *ptr = &x;     // Declara um ponteiro 'ptr' e atribui a ele o endereço de memória de 'x' (&)

    // Exibe o valor armazenado na variável 'x'
    printf("Valor de x: %d\n", x);

    // Exibe o endereço de memória de 'x' no formato hexadecimal (%p)
    printf("Endereço de x: %p\n", (void*)&x);

    // Usa o operador de desreferenciação (*) para acessar o valor apontado por 'ptr'
    printf("Valor apontado por ptr: %d\n", *ptr);

    return 0; // Indica que o programa finalizou com sucesso
}
