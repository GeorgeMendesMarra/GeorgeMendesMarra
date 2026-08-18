/*
 * Arquivo: exemplo07_multiplos_retornos.c
 * Autor: Tutorial C
 * Descricao: Retornando multiplos valores por referencia através de parametros de saida.
 * Compilador Compativel: GCC (MinGW no Windows, GCC nativo no Linux) v11.0 ou superior
 * 
 * Como compilar (Linux):   gcc exemplo07_multiplos_retornos.c -o programa
 * Como compilar (Windows): gcc exemplo07_multiplos_retornos.c -o programa.exe
 */

#include <stdio.h>

// Obtém a área e o perímetro de um retângulo de uma só vez
void calcularRetangulo(float largura, float altura, float *area, float *perimetro) {
    *area = largura * altura;                 // Altera 'areaRet' no main
    *perimetro = 2 * (largura + altura);      // Altera 'perimRet' no main
}

int main() {
    float areaRet, perimRet;
    
    // Passa os endereços onde quer salvar os resultados
    calcularRetangulo(5.0, 3.0, &areaRet, &perimRet);
    
    printf("Area: %.2f | Perimetro: %.2f\n", areaRet, perimRet);
    return 0;
}
