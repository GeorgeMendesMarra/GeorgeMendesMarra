/*
 * Arquivo: exemplo08_passagem_array.c
 * Autor: Tutorial C
 * Descricao: Alterando um vetor diretamente no main ao passa-lo como parametro.
 * Compilador Compativel: GCC (MinGW no Windows, GCC nativo no Linux) v11.0 ou superior
 * 
 * Como compilar (Linux):   gcc exemplo08_passagem_array.c -o programa
 * Como compilar (Windows): gcc exemplo08_passagem_array.c -o programa.exe
 */

#include <stdio.h>

// 'int notas[]' atua como um ponteiro para o primeiro elemento do array
void dobrarNotas(int notas[], int tamanho) {
    for(int i = 0; i < tamanho; i++) {
        notas[i] = notas[i] * 2; // Altera o array original no main
    }
}

int main() {
    int minhasNotas[3] = {2, 3, 4};
    
    // Não é necessário usar '&' ao passar um array inteiro
    dobrarNotas(minhasNotas, 3);
    
    printf("Notas alteradas: %d, %d, %d\n", minhasNotas[0], minhasNotas[1], minhasNotas[2]);
    return 0;
}
