/*
 * Arquivo: exemplo06_troca_valores.c
 * Autor: Tutorial C
 * Descricao: Algoritmo de troca de valores (Swap) utilizando passagem por referencia.
 * Compilador Compativel: GCC (MinGW no Windows, GCC nativo no Linux) v11.0 ou superior
 * 
 * Como compilar (Linux):   gcc exemplo06_troca_valores.c -o programa
 * Como compilar (Windows): gcc exemplo06_troca_valores.c -o programa.exe
 */

#include <stdio.h>

// Troca os valores contidos em duas variáveis do main
void trocar(int *a, int *b) {
    int temp = *a; // Guarda temporariamente o valor de 'a'
    *a = *b;       // Atribui o valor de 'b' em 'a'
    *b = temp;     // Atribui o valor antigo de 'a' em 'b'
}

int main() {
    int x = 10, y = 20;
    printf("Antes: x = %d, y = %d\n", x, y);
    
    trocar(&x, &y); // Envia o endereço das duas variáveis
    
    printf("Depois: x = %d, y = %d\n", x, y); // Imprime x = 20, y = 10
    return 0;
}
