/*
 * Arquivo: exemplo05_passagem_referencia.c
 * Autor: Tutorial C
 * Descricao: Modificando a variavel original usando ponteiros e o operador &.
 * Compilador Compativel: GCC (MinGW no Windows, GCC nativo no Linux) v11.0 ou superior
 * 
 * Como compilar (Linux):   gcc exemplo05_passagem_referencia.c -o programa
 * Como compilar (Windows): gcc exemplo05_passagem_referencia.c -o programa.exe
 */

#include <stdio.h>

// O '*' indica que 'x' é um ponteiro que guarda um endereço de memória
void incrementarDeVerdade(int *x) {
    *x = *x + 10; // O '*' acessa o valor no endereço recebido e o altera
}

int main() {
    int numero = 5;
    // O '&' envia o endereço de memória da variável 'numero'
    incrementarDeVerdade(&numero);
    printf("No main apos a funcao: numero = %d\n", numero); // Imprime 15
    return 0;
}
