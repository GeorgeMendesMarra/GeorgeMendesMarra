/*
 * Arquivo: exemplo02_retorno_valor.c
 * Autor: Tutorial C
 * Descricao: Funcao com retorno do tipo inteiro usando passagem por valor.
 * Compilador Compativel: GCC (MinGW no Windows, GCC nativo no Linux) v11.0 ou superior
 * 
 * Como compilar (Linux):   gcc exemplo02_retorno_valor.c -o programa
 * Como compilar (Windows): gcc exemplo02_retorno_valor.c -o programa.exe
 */

#include <stdio.h>

// Função que calcula o dobro de um número
int calcularDobro(int numero) {
    return numero * 2; // Devolve o resultado diretamente
}

int main() {
    int valor = 7;
    int resultado = calcularDobro(valor); // Recebe o valor 14
    printf("O dobro de %d e: %d\n", valor, resultado);
    return 0;
}
