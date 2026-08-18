/* ============================================================================
 * Programa: Exemplo 08 - Múltiplos retornos via ponteiros
 * Descrição: Contorna a limitação do comando 'return' utilizando ponteiros para
 *            preencher e devolver múltiplos resultados a partir de uma função.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>

// Função que calcula soma e produto e envia as respostas através de ponteiros
void calcularOperacoes(int a, int b, int *soma, int *prod) {
    *soma = a + b; // Armazena a soma no endereço apontado por 'soma'
    *prod = a * b; // Armazena o produto no endereço apontado por 'prod'
}

int main(void) {
    int s, p; // Variáveis que vão guardar os resultados calculados

    // Passa os valores (4 e 5) e os endereços onde os resultados devem ser gravados (&s, &p)
    calcularOperacoes(4, 5, &s, &p);

    // Imprime os valores gravados nas variáveis locais através dos ponteiros
    printf("Soma: %d, Produto: %d\n", s, p);

    return 0;
}
