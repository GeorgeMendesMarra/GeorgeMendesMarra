/*
 * Arquivo: exemplo04_imutabilidade_valor.c
 * Autor: Tutorial C
 * Descricao: Demonstracao prática de que a passagem por valor nao altera a variavel original.
 * Compilador Compativel: GCC (MinGW no Windows, GCC nativo no Linux) v11.0 ou superior
 * 
 * Como compilar (Linux):   gcc exemplo04_imutabilidade_valor.c -o programa
 * Como compilar (Windows): gcc exemplo04_imutabilidade_valor.c -o programa.exe
 */

#include <stdio.h>

void tentarIncrementar(int x) {
    x = x + 10; // Altera apenas a variável local 'x'
    printf("Dentro da funcao: x = %d\n", x);
}

int main() {
    int numero = 5;
    tentarIncrementar(numero);
    // 'numero' continua sendo 5 no main
    printf("No main (fora da funcao): numero = %d\n", numero);
    return 0;
}
