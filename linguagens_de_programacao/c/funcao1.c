/*
 * Arquivo: exemplo01_void_valor.c
 * Autor: Tutorial C
 * Descricao: Exemplo de funcao void recebendo parametro por valor.
 * Compilador Compativel: GCC (MinGW no Windows, GCC nativo no Linux) v11.0 ou superior
 * 
 * Como compilar (Linux):   gcc exemplo01_void_valor.c -o programa
 * Como compilar (Windows): gcc exemplo01_void_valor.c -o programa.exe
 */

#include <stdio.h>

// Função que apenas imprime uma saudação personalizada
void saudarUsuario(int idade) {
    // 'idade' é uma cópia do valor enviado
    printf("Voce tem %d anos!\n", idade);
}

int main() {
    int minhaIdade = 25;
    saudarUsuario(minhaIdade); // Passa o valor 25 para a função
    return 0;
}
