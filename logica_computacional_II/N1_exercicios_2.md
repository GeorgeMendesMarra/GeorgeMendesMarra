Teste os programas abaixo no SWI-Prolog

%Monty Python (http://www.allisons.org/ll/Logic/Prolog/Examples/witch/)
witch(X) :- burns(X), female(X).
burns(X) :- wooden(X).
wooden(X) :- floats(X).
floats(X) :- sameweight(duck, X).

female(girl).
sameweight(duck,girl).

=============================================================

%Computador
causadopor(X,Y) :- causa(Y, X); causa(Z, X), causadopor(Z, Y).

causa(muitas_janelas, falta_ram).
causa(muitos_arquivos, disco_cheio).
causa(falta_ram, swap).
causa(swap, muitos_arquivos).
causa(swap, lento).
causa(indexacao, lento).
causa(sem_energia, nao_liga).
causa(fora_da_tomada, sem_energia).

=============================================================

%GoT
grandparent(X,Y) :- parent(X,Z), parent(Z,Y).
sibling(X,Y) :- parent(Z,X), parent(Z,Y), X \= Y.
tio(X,Y) :- sibling(X,Z), parent(Z,Y).
cousin(X,Y) :- parent(Z,X), parent(W,Y), sibling(Z,W), X \= Y, Z \= W.
child(X,Y) :- parent(Y,X).
descendant(X, Y) :- child(X, Y); child(X, Z), descendant(Z, Y).
ancestor(X, Y) :- descendant(Y, X).
incest(X, Y) :- sibling(X, Y), parent(X, Z), parent(Y, Z).

%Lannister
parent(tytos, tywin).
parent(tywin, tyrion).
parent(tywin, jaime).
parent(tywin, cersei).
parent(jaime, joffrey).
parent(cersei, joffrey).
parent(jaime, myrcella).
parent(cersei, myrcella).
parent(jaime, tommen).
parent(cersei, tommen).

%Stark
parent(rickard, eddard).
parent(rickard, lyanna).
parent(rickard, benjen).
parent(eddard, robb).
parent(catelyn, robb).
parent(eddard, sansa).
parent(catelyn, sansa).
parent(eddard, brandon).
parent(catelyn, brandon).
parent(eddard, arya).
parent(catelyn, arya).
parent(eddard, rickon).
parent(catelyn, rickon).

%Targaryen
parent(maekar, aemon).
parent(maekar, aegon).
parent(aegon, jaehaerys).
parent(aegon, rhaelle).
parent(jaehaerys, aerys).
parent(aerys, rhaegar).
parent(aerys, viserys).
parent(aerys, daenerys).

parent(rhaegar, jon).
parent(lyanna, jon).

=============================================================

%Triangle
triangle(X,Y,Z) :- X < Y + Z, Y < X + Z, Z < X + Y.
equilatero(X,Y,Z) :- triangle(X,Y,Z), X =:= Y, X =:= Z.
escaleno(X,Y,Z) :- triangle(X,Y,Z), X =\= Y, X =\= Z, Y =\= Z.
isosceles(X,Y,Z) :- triangle(X,Y,Z), ((X =:= Y, X =\= Z); (X =:= Z, X =\= Y) 

=============================================================

%Arithmetic
product(X,Y,Z) :- Z is X * Y.

=============================================================

%Bhaskara
quadratic(A,B,C,X1,X2) :- A =:= 0, B =\= 0, X1 is -C/B, X2 is -C/B, !.
quadratic(A,B,C,X1,X2) :- A =\= 0, Delta is B*B - 4*A*C, Delta >= 0, sqrt(Delta, Z), X1 is -B + Z / (2*A), X2 is -B - Z / (2*A).

 =============================================================

%Prime
divisible(X, Y) :- X mod Y =:= 0.
prime(X) :- \+ (Z is X-1, between(2, Z, Y), divisible(X, Y)), X =\= 1. 

 =============================================================

%Quicksort (https://stackoverflow.com/a/59227103/834518)
qsort([], []).        %Quicksort de uma lista vazia é uma lista vazia
qsort([X], [X]).    %Quicksort de uma lista com 1 elemento é a mesma lista
qsort([Head, Pivot|Tail], Sorted) :-    %Quicksort de uma lista composta por Head, Pivot e Tail, produz uma lista Sorted
        partition(Pivot,[Head|Tail],Less,Greater), %Se a partição de uma lista composta por Head e Tail, separada por Pivot, gera 2 listas Less e Greater, de números menores e maiores que Pivot, respectivamente
        qsort(Less,SortedLess),            %Se o quicksort da lista Less produz uma lista ordenada SortedLess
        qsort(Greater,SortedGreater),    %Se o quicksort lista Greater produz uma lista ordenada SortedGreater
        append(SortedLess,[Pivot|SortedGreater],Sorted).    %Se a concatenação da lista SortedLess com o Pivot e a lista SortedGreater produz uma lista Sorted
        
partition(_, [], [], []).    %A partição de uma lista vazia por qualquer pivô, gera lista uma lista vazia de valores menores que o pivô e lista vazia de valores maiores que o pivô
partition(Pivot, [X|T], [X|Less], Greater) :-     %A partição de uma lista composta pelo elemento X e lista T pelo elemento Pivot resulta em uma lista composta por X e a lista Less e também uma lista Greater
        X =< Pivot, partition(Pivot,T,Less,Greater).    %Se X é menor ou igual ao Pivot e a partição da lista T por Pivot resulta nas listas Less e Greater
partition(Pivot, [X|T], Less, [X|Greater]) :-    %A partição de uma lista composta pelo elemento X e lista T pelo elemento Pivot resulta em uma lista Less e também uma lista composta por X e a lista Greater
        X > Pivot, partition(Pivot,T,Less,Greater).        %Se X é maior que o Pivot e a partição da lista T por Pivot resulta nas listas Less e Greater

 =============================================================

 Exemplos:

/** <examples>
?- witch(girl).
?- causadopor(lento, X).
?- causadopor(lento, X), causadopor(disco_cheio, X).
?- causadopor(lento, X), causadopor(disco_cheio, X), causadopor(nao_liga, X).
?- grandparent(X, Y).
?- grandparent(X, _).
?- distinct([X], grandparent(X, _)).
?- parent(cersei, X).
?- parent(X, Y), tio(X, Y).
?- tio(X, Y).
?- cousin(X, Y).
?- distinct([X], incest(X, _)).
?- triangle(0, 0, 0).
?- triangle(1, 2, 3).
?- triangle(2, 6, 7).
?- between(0, 10, X), equilatero(2, 2, X).
?- between(0, 10, X), isosceles(2, 2, X).
?- between(0, 10, X), escaleno(2, 2, X).
?- between(0, 10, X), escaleno(2, 3, X).
?- product(2, 3, X).
?- product(2, 3, 6).
?- product(2, 3, 8).
?- between(-10, 10, X), product(2, X, 8).
?- quadratic(1,2,3,X1,X2).
?- quadratic(1,2,-3,X1,X2).
?- quadratic(0,2,-3,X1,X2).
?- between(-10,10,A), between(-10,10,B), between(-10,10,C), quadratic(A,B,C,X1,X2).
?- prime(1).
?- prime(2).
?- prime(3).
?- prime(4).
?- qsort([5,7,2,7,9,4,3,7,4], Sorted).
?- between(0,10,X), qsort([X,7,2,7,9,4,3,7,4], Sorted).
?- append([1,2,3],[4,5,6],X).
?- partition(4, [5,7,2,7,9,4,3,7,4], Less, Greater).
*/ 
