{ 
  Este é um programa Hello World em Pascal.
  O Pascal é uma linguagem estruturada, então o código 
  é dividido em blocos.
}

PROGRAM HelloWorld;    { Declara o nome do programa. É a primeira linha obrigatória. }

USES Crt;              { Cláusula opcional: 'USES' inclui bibliotecas. CRT é comum para limpar a tela ou pausar a saída. }

BEGIN                  { Início do bloco principal de execução do programa. }

    WRITELN('Hello World!'); { A função 'WRITELN' (Write Line) imprime a string e pula para a próxima linha. }
    
    READLN;            { Pausa a execução para que o usuário possa ver a saída antes que a janela do console feche. }

END.                   { Fim do programa. O ponto final (.) é obrigatório. }
