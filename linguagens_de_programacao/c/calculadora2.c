/*
 * biblioteca_matematica.c
 * ----------------------------------------------------
 * Entregavel 1 - Modulo 1 (Modularizacao)
 * Disciplina: Algoritmos e Estrutura de Dados I
 *
 * Objetivo didatico: reunir uma "biblioteca" de funcoes
 * matematicas, cada uma isolada, coesa e testavel
 * separadamente -- o mesmo principio de modularizacao
 * usado em bibliotecas padrao como <math.h>.
 *
 * Inclui:
 *  - Funcoes iterativas e recursivas
 *  - Funcoes que usam <math.h>
 *  - Funcoes com passagem por referencia (ponteiro)
 *  - Um pequeno menu interativo para testar cada uma
 * ----------------------------------------------------
 */

#include <stdio.h>
#include <math.h>   /* necessario para sqrt(), pow() etc. */

/* ---------- Protótipos das funções ----------
 * Cada protótipo abaixo é a "assinatura" da função:
 * nome, tipo de retorno e tipos dos parâmetros.
 * Isso permite chamar as funções no main() mesmo com a
 * implementação completa aparecendo mais abaixo no arquivo.
 */

/* Basicas / recursivas */
long fatorial(int n);
long fibonacci(int n);
int ehPrimo(int n);

/* Aritmetica de dois numeros (passagem por VALOR) */
int mdc(int a, int b);          /* Maximo Divisor Comum (recursivo - Euclides) */
int mmc(int a, int b);          /* Minimo Multiplo Comum (usa mdc) */
double media(double a, double b);

/* Passagem por REFERENCIA: a funcao devolve dois
 * resultados ao mesmo tempo, escrevendo diretamente
 * nos enderecos apontados por quociente e resto */
void divisaoInteira(int a, int b, int *quociente, int *resto);

/* Wrappers sobre <math.h>, com validacao de entrada */
int raizQuadrada(double x, double *resultado); /* retorna 0 se x < 0 */
double potencia(double base, double expoente);

void exibirMenu(void);

int main(void) {
    int opcao;
    int continuar = 1;

    while (continuar) {
        exibirMenu();
        printf("Escolha uma opcao: ");

        if (scanf("%d", &opcao) != 1) {
            printf("Entrada invalida.\n");
            while (getchar() != '\n');
            continue;
        }

        switch (opcao) {
            case 1: {
                int n;
                printf("Digite n para calcular n!: ");
                scanf("%d", &n);
                if (n < 0) {
                    printf("Fatorial nao definido para negativos.\n\n");
                } else {
                    printf("%d! = %ld\n\n", n, fatorial(n));
                }
                break;
            }
            case 2: {
                int n;
                printf("Digite a posicao n da sequencia de Fibonacci: ");
                scanf("%d", &n);
                printf("Fibonacci(%d) = %ld\n\n", n, fibonacci(n));
                break;
            }
            case 3: {
                int n;
                printf("Digite um numero inteiro: ");
                scanf("%d", &n);
                printf("%d %s primo.\n\n", n, ehPrimo(n) ? "e" : "nao e");
                break;
            }
            case 4: {
                int a, b;
                printf("Digite dois inteiros (a e b): ");
                scanf("%d %d", &a, &b);
                /* PASSAGEM POR VALOR: a e b sao copiados para
                 * dentro de mdc()/mmc() */
                printf("MDC = %d\n", mdc(a, b));
                printf("MMC = %d\n\n", mmc(a, b));
                break;
            }
            case 5: {
                double a, b;
                printf("Digite dois numeros reais (a e b): ");
                scanf("%lf %lf", &a, &b);
                printf("Media = %.2f\n\n", media(a, b));
                break;
            }
            case 6: {
                int a, b, q, r;
                printf("Digite dividendo e divisor: ");
                scanf("%d %d", &a, &b);
                if (b == 0) {
                    printf("Divisao por zero nao permitida.\n\n");
                    break;
                }
                /* PASSAGEM POR REFERENCIA: &q e &r enviam os
                 * ENDERECOS de q e r; divisaoInteira() escreve
                 * diretamente nessas variaveis do main() */
                divisaoInteira(a, b, &q, &r);
                printf("Quociente = %d, Resto = %d\n\n", q, r);
                break;
            }
            case 7: {
                double x, resultado;
                printf("Digite um numero para raiz quadrada: ");
                scanf("%lf", &x);
                /* PASSAGEM POR REFERENCIA: &resultado */
                if (raizQuadrada(x, &resultado)) {
                    printf("Raiz quadrada = %.4f\n\n", resultado);
                } else {
                    printf("Nao existe raiz real para numero negativo.\n\n");
                }
                break;
            }
            case 8: {
                double base, expoente;
                printf("Digite base e expoente: ");
                scanf("%lf %lf", &base, &expoente);
                printf("Resultado = %.4f\n\n", potencia(base, expoente));
                break;
            }
            case 9:
                continuar = 0;
                break;
            default:
                printf("Opcao invalida! Tente novamente.\n\n");
        }
    }

    printf("Encerrando a biblioteca matematica. Ate mais!\n");
    return 0;
}

/* ---------- Implementacao das funcoes ---------- */

void exibirMenu(void) {
    printf("===== BIBLIOTECA DE FUNCOES MATEMATICAS =====\n");
    printf("1 - Fatorial (recursivo)\n");
    printf("2 - Fibonacci (recursivo)\n");
    printf("3 - Verificar se e primo\n");
    printf("4 - MDC e MMC\n");
    printf("5 - Media de dois numeros\n");
    printf("6 - Divisao inteira (quociente e resto)\n");
    printf("7 - Raiz quadrada (com validacao)\n");
    printf("8 - Potencia\n");
    printf("9 - Sair\n");
}

/*
 * Calcula n! (fatorial) de forma RECURSIVA.
 * Caso base: 0! = 1.
 * Caso recursivo: n! = n * (n-1)!
 */
long fatorial(int n) {
    if (n == 0) {
        return 1;
    }
    return n * fatorial(n - 1);
}

/*
 * Calcula o n-esimo termo da sequencia de Fibonacci,
 * de forma RECURSIVA (versao didatica; para n grande
 * uma versao iterativa seria mais eficiente, pois esta
 * recalcula os mesmos termos varias vezes).
 * Casos base: fibonacci(0) = 0, fibonacci(1) = 1.
 */
long fibonacci(int n) {
    if (n <= 1) {
        return n;
    }
    return fibonacci(n - 1) + fibonacci(n - 2);
}

/*
 * Verifica se n e um numero primo.
 * Retorna 1 (verdadeiro) ou 0 (falso).
 * So testa divisores ate a raiz quadrada de n,
 * pois se n tem um divisor maior que sqrt(n),
 * necessariamente tem tambem um menor -- reduz o
 * numero de iteracoes necessarias.
 */
int ehPrimo(int n) {
    int i;

    if (n < 2) {
        return 0;
    }

    for (i = 2; i <= sqrt((double) n); i++) {
        if (n % i == 0) {
            return 0;
        }
    }
    return 1;
}

/*
 * Calcula o Maximo Divisor Comum de a e b usando o
 * Algoritmo de Euclides, de forma RECURSIVA.
 * PARAMETROS POR VALOR: a e b sao copias locais.
 */
int mdc(int a, int b) {
    if (b == 0) {
        return a;
    }
    return mdc(b, a % b);
}

/*
 * Calcula o Minimo Multiplo Comum de a e b, reutilizando
 * a funcao mdc() ja implementada -- exemplo de
 * modularizacao: uma funcao construida sobre a outra.
 * Formula: mmc(a,b) = (a * b) / mdc(a,b)
 */
int mmc(int a, int b) {
    return (a * b) / mdc(a, b);
}

/* Retorna a media aritmetica simples entre a e b */
double media(double a, double b) {
    return (a + b) / 2.0;
}

/*
 * Calcula quociente e resto da divisao inteira de a por b.
 * PARAMETROS POR REFERENCIA: "quociente" e "resto" sao
 * ponteiros (int*); a funcao escreve diretamente nos
 * enderecos recebidos, permitindo devolver DOIS resultados
 * ao mesmo tempo -- coisa que um "return" simples nao faria.
 */
void divisaoInteira(int a, int b, int *quociente, int *resto) {
    *quociente = a / b;
    *resto = a % b;
}

/*
 * Calcula a raiz quadrada de x, validando o dominio.
 * PARAMETRO POR REFERENCIA: "resultado" recebe o valor
 * calculado via ponteiro (double*).
 * Retorno: 1 se x >= 0 (sucesso), 0 se x < 0 (erro,
 * raiz real nao definida para numeros negativos).
 */
int raizQuadrada(double x, double *resultado) {
    if (x < 0) {
        return 0;
    }
    *resultado = sqrt(x);  /* funcao de <math.h> */
    return 1;
}

/* Calcula base elevado a expoente, usando pow() de <math.h> */
double potencia(double base, double expoente) {
    return pow(base, expoente);
}
