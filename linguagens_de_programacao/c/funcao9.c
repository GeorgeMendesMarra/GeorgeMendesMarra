/*
 * Arquivo: exemplo09_referencia_constante.c
 * Autor: Tutorial C
 * Descricao: Uso de ponteiro constante para leitura segura sem risco de alteracao.
 * Compilador Compativel: GCC (MinGW no Windows, GCC nativo no Linux) v11.0 ou superior
 * 
 * Como compilar (Linux):   gcc exemplo09_referencia_constante.c -o programa
 * Como compilar (Windows): gcc exemplo09_referencia_constante.c -o programa.exe
 */

#include <stdio.h>

// 'const int *' garante que a função apenas LEIA o valor sem alterá-lo
void exibirDadoSeguro(const int *numero) {
    // *numero = 100; // Gera erro de compilação se tentar alterar
    printf("O valor seguro e: %d\n", *numero);
}

int main() {
    int valor = 50;
    exibirDadoSeguro(&valor);
    return 0;
}
