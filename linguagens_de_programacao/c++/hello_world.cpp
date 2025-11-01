// Este é um programa básico em C++ que exibe "Hello World" no console.

// 1. Inclui a biblioteca de entrada e saída (Input/Output stream).
// A biblioteca iostream permite que o programa use funcionalidades como 'cout' (saída no console).
#include <iostream>

// 2. Define a função principal (main function).
// Todo programa C++ começa a ser executado a partir desta função.
// O 'int' indica que a função retornará um valor inteiro no final.
int main() {
    // 3. Imprime a mensagem "Hello World!" no console.
    // 'std::cout' é o objeto que representa o fluxo de saída padrão (a tela).
    // '<<' é o operador de inserção, que envia o texto para a saída.
    // 'std::endl' insere uma quebra de linha e libera o buffer de saída.
    std::cout << "Hello World!" << std::endl;

    // 4. Retorna 0 (zero).
    // O valor 0 indica que o programa foi executado com sucesso e encerrou normalmente.
    return 0;
}
