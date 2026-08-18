/* ============================================================================
 * Programa: Exemplo 03 - Troca de variáveis (Passagem por Referência)
 * Descrição: Utiliza ponteiros como parâmetros de função para modificar o valor
 *            de variáveis no escopo da função chamadora.
 * Compilador Compatível: GCC (GNU Compiler Collection) v7.0 ou superior
 * Sistemas Operacionais: Windows (MinGW/MSYS2), Linux (GCC nativo)
 * Comando de Compilação: gcc -std=c99 -Wall programa.c -o programa
 * ============================================================================
 */

#include <stdio.h>

// Função que recebe dois ponteiros como argumentos
void trocar(int *a, int *b) {
    int temp = *a; // Salva o valor guardado no endereço 'a' na variável auxiliar 'temp'
    *a = *b;       // Copia o valor do endereço 'b' para o endereço 'a'
    *b = temp;     // Copia o valor salvo em 'temp' para o endereço 'b'
}

int main(void) {
    int x = 5, y = 10; // Declara as variáveis 'x' e 'y'

    // Passa os endereços de 'x' e 'y' (&) para que a função altere os valores originais
    trocar(&x, &y);

    // Imprime os valores trocados
    printf("x = %d, y = %d\n", x, y); // Resultado: x = 10, y = 5

    return 0;
}
