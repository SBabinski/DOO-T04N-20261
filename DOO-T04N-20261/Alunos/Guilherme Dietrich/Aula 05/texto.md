 # Introdução:
 Dentro da aula do dia 24/03, aprendemos sobre dois paradigmas diferentes dentro
 da programação, dentro deles, estão o modo imperativo e o declarativo, onde os dois
 podem ser utilizados para casos diferentes dentro do código.

 ## Imperativo:
 O paradigma imperativo foca totalmente no passo a passo do código, alterando completamente
 a forma em que ele é organizado, uma das maiores formas em que aparecem o modo imperativo
 é dentro de loops ou condições, pois dentro delas, as instruções mandadas para o computador
 são totalmente coordenadas pelo dono do código, algumas das linguagens que mais utilizam o
 imperativo são: Java, C e Pythom. Por conta de manter um passo a passo, o projeto pode ficar
 um pouco mais longo e complexo, porém mais completo.

## Declarativo:
 O paradigma declarativo busca principalmente a objetividade dentro do código, onde o programador
 apenas descreve o que ele deseja ser feito, sem passar pelo processo do passo a passo, ele facilita
 com que o código seja mais curto e claro, se baseando mais em uma linguagem humana, muito diferente
 da imperativa que utiliza uma linguagem mais "robótica".

## Exemplo dos códigos:

### Java:

 ```
 import java.util.ArrayList;
import java.util.List;

public class SomaPares {

    public static void main(String[] args) {

        List<Integer> numeros = new ArrayList<>();

        numeros.add(1);
        numeros.add(2);
        numeros.add(3);
        numeros.add(4);
        numeros.add(5);
        numeros.add(6);
        numeros.add(7);
        numeros.add(8);

        int soma = 0;

        for (int i = 0; i < numeros.size(); i++) {
            int numeroAtual = numeros.get(i);

            if (numeroAtual % 2 == 0) {
                soma = soma + numeroAtual;
            }
        }

        System.out.println("Soma dos pares: " + soma);
    }
}

 ```

### Prolog:

 ```
 % verifica se um número é par
par(X) :-
    0 is X mod 2.

% soma dos números pares
soma_pares([], 0).

soma_pares([H|T], Soma) :-
    par(H),
    soma_pares(T, SomaRestante),
    Soma is SomaRestante + H.

soma_pares([H|T], Soma) :-
    + par(H),
    soma_pares(T, Soma).

% lista de exemplo
lista([1,2,3,4,5,6,7,8]).

% consulta principal
resultado(Soma) :-
    lista(L),
    soma_pares(L, Soma).

 ```

## Diferença entre os códigos:
 A diferença entre os códigos é totalmente visivel, principalmente por conta da linguagem mais em
 forma de computador dentro do primeiro código, muito diferente do segundo, que aparenta ser bem 
 mais "escrito", outra diferença é de que o modo imperativo está mostrando a criação da lista, os
 loops para jogar os numeros na condição que verifica se é par, já no código em prolog, você meio
 que define o que é um numero par,  o código é resolvido pelo sistema e não se tem um passo a 
 passo, é tudo definido pelas regras criadas dentro do programa.
 