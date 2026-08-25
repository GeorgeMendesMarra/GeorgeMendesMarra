/*
 * calculadora.c
 * ----------------------------------------------------
 * Calculadora simples em linguagem C.
 *
 * Objetivo didático: mostrar modularização por meio de
 * funções, cada uma responsável por uma única operação
 * (princípio de coesão), e passagem de parâmetros por
 * valor.
 *
 * Operações suportadas: soma, subtração, multiplicação
 * e divisão (com tratamento de divisão por zero).
 * ----------------------------------------------------
 */

#include <stdio.h>

/* ---------- Protótipos das funções ----------
 * Um PROTOTIPO (ex.: "void exibirMenu(void);") declara ao
 * compilador que uma funcao existe -- seu nome, tipo de
 * retorno e parametros -- antes de o corpo dela ser
 * implementado no arquivo. Como o C le o codigo de cima
 * para baixo, isso permite chamar a funcao dentro do main()
 * mesmo com a implementacao completa aparecendo mais abaixo,
 * no arquivo. Repare que o protótipo termina com ";" e nao
 * tem chaves { } -- a implementacao real vem depois.
 *
 * Cada função recebe dois números (double) por VALOR
 * e devolve o resultado da operação correspondente.
 * Passagem por valor: as funções recebem CÓPIAS de a e b,
 * então não alteram as variáveis originais do main().
 */
double somar(double a, double b);
double subtrair(double a, double b);
double multiplicar(double a, double b);
int dividir(double a, double b, double *resultado);
/* dividir() usa um PONTEIRO (passagem por referência) para
 * "resultado", pois precisa devolver dois valores ao mesmo
 * tempo: se a divisão foi bem-sucedida (retorno int) e o
 * valor calculado (via ponteiro). Isso ilustra a diferença
 * entre passagem por valor e por referência.
 */

void exibirMenu(void);

int main(void) {
    double num1, num2, resultado;
    int opcao;
    int continuar = 1;

    while (continuar) {
        exibirMenu();
        printf("Escolha uma opcao: ");

        /* Validacao basica de entrada */
        if (scanf("%d", &opcao) != 1) {
            printf("Entrada invalida.\n");
            /* Limpa o buffer de entrada em caso de erro */
            while (getchar() != '\n');
            continue;
        }

        if (opcao == 5) {
            continuar = 0;
            break;
        }

        if (opcao < 1 || opcao > 4) {
            printf("Opcao invalida! Tente novamente.\n\n");
            continue;
        }

        printf("Digite o primeiro numero: ");
        scanf("%lf", &num1);
        printf("Digite o segundo numero: ");
        scanf("%lf", &num2);

        /* Cada operacao delegada a uma funcao especifica
         * (modularizacao: main() apenas coordena o fluxo,
         * a logica de calculo fica isolada em cada funcao) */
        switch (opcao) {
            case 1:
                /* PASSAGEM POR VALOR: num1 e num2 sao copiados para
                 * dentro de somar(); a funcao nao tem acesso as
                 * variaveis originais, so as copias */
                resultado = somar(num1, num2);
                printf("Resultado: %.2f\n\n", resultado);
                break;

            case 2:
                /* PASSAGEM POR VALOR: mesma logica de somar() */
                resultado = subtrair(num1, num2);
                printf("Resultado: %.2f\n\n", resultado);
                break;

            case 3:
                /* PASSAGEM POR VALOR: mesma logica de somar() */
                resultado = multiplicar(num1, num2);
                printf("Resultado: %.2f\n\n", resultado);
                break;

            case 4:
                /* dividir() retorna 0 em caso de erro (divisao por zero)
                 * e 1 em caso de sucesso; o resultado real vem pelo
                 * ponteiro &resultado */
                /* PASSAGEM POR REFERENCIA: "&resultado" envia o
                 * ENDERECO da variavel resultado (nao uma copia).
                 * Assim, dividir() escreve diretamente na variavel
                 * do main(), permitindo que a funcao "devolva" o
                 * valor calculado por essa via, alem do retorno int */
                if (dividir(num1, num2, &resultado)) {
                    printf("Resultado: %.2f\n\n", resultado);
                } else {
                    printf("Erro: divisao por zero nao permitida!\n\n");
                }
                break;
        }
    }

    printf("Encerrando a calculadora. Ate mais!\n");
    return 0;
}

/* ---------- Implementacao das funcoes ---------- */

/* Exibe as opcoes disponiveis ao usuario */
void exibirMenu(void) {
    printf("===== CALCULADORA =====\n");
    printf("1 - Somar\n");
    printf("2 - Subtrair\n");
    printf("3 - Multiplicar\n");
    printf("4 - Dividir\n");
    printf("5 - Sair\n");
}

/* Retorna a soma de a e b
 * PARAMETROS POR VALOR: "a" e "b" sao variaveis LOCAIS a esta
 * funcao, contendo copias dos argumentos passados na chamada.
 * Alterar "a" ou "b" aqui dentro nao afeta num1/num2 no main() */
double somar(double a, double b) {
    return a + b;
}

/* Retorna a diferenca entre a e b
 * PARAMETROS POR VALOR (mesma logica de somar) */
double subtrair(double a, double b) {
    return a - b;
}

/* Retorna o produto de a e b
 * PARAMETROS POR VALOR (mesma logica de somar) */
double multiplicar(double a, double b) {
    return a * b;
}

/*
 * Calcula a divisao de a por b.
 * Parametros:
 *   a, b       - operandos (passados por valor)
 *   resultado  - ponteiro onde o resultado sera armazenado
 *                (passagem por referencia: a funcao escreve
 *                 diretamente na variavel do chamador)
 * Retorno:
 *   1 se a divisao foi realizada com sucesso
 *   0 se b for igual a zero (divisao invalida)
 */
/* PARAMETROS: "a" e "b" por VALOR (copias);
 * "resultado" por REFERENCIA (ponteiro double*, recebe um
 * ENDERECO de memoria, nao um valor) */
int dividir(double a, double b, double *resultado) {
    if (b == 0) {
        return 0;
    }
    /* "*resultado" DESREFERENCIA o ponteiro: acessa e escreve
     * diretamente no endereco recebido, ou seja, na variavel
     * do chamador (resultado do main()) */
    *resultado = a / b;
    return 1;
}
