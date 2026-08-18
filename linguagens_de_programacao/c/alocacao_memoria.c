#include <stdio.h>
#include <stdlib.h>

int globalInic = 100;     // -> Seção DATA
int globalNaoInic;        // -> Seção BSS

void funcaoExemplo(int p) { // -> 'p' fica na STACK
    int local = 50;         // -> 'local' fica na STACK
    
    int *ponteiroHeap = (int *) malloc(sizeof(int)); // -> 'ponteiroHeap' fica na STACK
                                                      // -> O valor alocado *(ponteiroHeap) fica no HEAP
    
    *ponteiroHeap = 200;
    
    free(ponteiroHeap);     // -> Libera o espaço no HEAP
}

int main() {                // -> Código executável fica na seção TEXT
    funcaoExemplo(10);
    return 0;
}
