/*
 * Arquivo: exemplo03_multiplos_tipos.c
 * Autor: Tutorial C
 * Descricao: Passagem por valor com tipos de dados mistos (float e int).
 * Compilador Compativel: GCC (MinGW no Windows, GCC nativo no Linux) v11.0 ou superior
 * 
 * Como compilar (Linux):   gcc exemplo03_multiplos_tipos.c -o programa
 * Como compilar (Windows): gcc exemplo03_multiplos_tipos.c -o programa.exe
 */

#include <stdio.h>

// Função que calcula o valor total de uma compra
float calcularTotal(float precoUnitario, int quantidade) {
    float total = precoUnitario * quantidade;
    return total;
}

int main() {
    float preco = 12.50;
    int qtd = 4;
    float final = calcularTotal(preco, qtd); // Envia float e int
    printf("Total a pagar: R$ %.2f\n", final);
    return 0;
}
