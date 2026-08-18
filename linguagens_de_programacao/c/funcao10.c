/*
 * Arquivo: exemplo10_validacao_status.c
 * Autor: Tutorial C
 * Descricao: Retornando codigo de erro via return e o resultado por referencia.
 * Compilador Compativel: GCC (MinGW no Windows, GCC nativo no Linux) v11.0 ou superior
 * 
 * Como compilar (Linux):   gcc exemplo10_validacao_status.c -o programa
 * Como compilar (Windows): gcc exemplo10_validacao_status.c -o programa.exe
 */

#include <stdio.h>

// Retorna 1 se a divisão for realizada com sucesso e 0 caso haja erro
int dividir(float a, float b, float *resultado) {
    if (b == 0) {
        return 0; // Erro: Divisão por zero
    }
    *resultado = a / b;
    return 1; // Sucesso
}

int main() {
    float res;
    int sucesso = dividir(10.0, 2.0, &res);
    
    if (sucesso) {
        printf("Resultado da divisao: %.2f\n", res);
    } else {
        printf("Erro: Divisao por zero nao permitida!\n");
    }
    return 0;
}
