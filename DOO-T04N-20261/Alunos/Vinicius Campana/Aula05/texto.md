# Paradigmas de Programação

Os paradigmas de programação são formas diferentes de pensar na hora de criar um software. Eles ajudam a organizar o código e a decidir como resolver um problema. Os dois principais são o imperativo e o declarativo, e a principal diferença entre eles está em como a solução é construída.

## Paradigma Imperativo

No paradigma imperativo, o programador diz exatamente o que o computador deve fazer, passo a passo. É como dar instruções detalhadas: primeiro isso, depois aquilo, até chegar no resultado final. Nesse modelo, o foco está no controle da execução, usando estruturas como condições (`if/else`), repetições (`for/while`) e funções. Dentro desse paradigma, existem algumas formas de organizar o código. A programação estruturada busca deixar o código mais organizado e fácil de entender. A programação procedural divide o problema em funções menores. Já a programação orientada a objetos trabalha com objetos que juntam dados e comportamentos. Mesmo sendo diferentes entre si, todas seguem a mesma ideia: mostrar exatamente como o processo deve acontecer.

## Paradigma Declarativo

Já o paradigma declarativo funciona de outro jeito. Aqui, o programador não precisa explicar o passo a passo, mas sim dizer qual resultado quer obter. O sistema fica responsável por encontrar a melhor forma de chegar até esse resultado. A programação funcional segue essa ideia ao trabalhar com funções e evitar mudanças nos dados durante a execução, o que deixa o código mais previsível. Já a programação lógica se baseia em regras e fatos, permitindo que o próprio sistema encontre as respostas a partir dessas definições.

No geral, dá pra entender que o paradigma imperativo dá mais controle sobre o que está acontecendo, enquanto o declarativo deixa o código mais simples e direto, focando só no resultado. Saber a diferença entre eles é importante porque ajuda a escolher a melhor forma de resolver cada tipo de problema, principalmente hoje em dia, já que muitas linguagens misturam os dois estilos.

## Código em Java (Paradigma Imperativo)

O código em Java funciona de forma sequencial, ele segue um passo a passo bem definido. Primeiro, o programa pede dois números e uma operação ao usuário. Depois disso, ele utiliza estruturas condicionais para verificar qual operação foi escolhida e então executa o cálculo correspondente.

Além disso, o uso do `while(true)` faz com que o programa continue rodando indefinidamente, permitindo que o usuário realize vários cálculos sem precisar reiniciar. Nesse caso, o programador controla toda a execução do programa, dizendo exatamente o que deve acontecer em cada etapa. Isso é uma característica clara do paradigma imperativo, onde o foco está em como o problema será resolvido.

```java
package calculadora;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in


        while(true){
            System.out.println("\ndigite o primeiro número");
            float num1 = scanner.nextFloat();
            System.out.println("digite a operação (+ | - | * | /");
            char operacao = scanner.next().charAt(0);
            System.out.println("digite o segundo número");
            float num2 = scanner.nextFloat();
        
            if (operacao == '+') System.out.println(num1 + num2);
            else if (operacao == '-') System.out.println(num1 - num2);
            else if (operacao == '*') System.out.println(num1 * num2);
            else if (operacao == '/') System.out.println(num2 != 0 ? num1 / num2 : "Erro: Divisão por 0\n");
            else System.out.println("Operação inválida");
        }
    }
}
```

## Código em Prolog (Paradigma Declarativo / Lógico)

Já no Prolog, a lógica muda completamente. Em vez de definir um fluxo passo a passo, o programador cria regras que descrevem o que deve acontecer em cada situação. Cada operação matemática é representada por uma regra, e o próprio sistema se encarrega de encontrar o resultado quando uma consulta é feita.

Não existe um controle explícito de execução como no Java, nem estruturas como `while` ou `if` da mesma forma. O funcionamento é baseado em correspondência de padrões e regras. Por exemplo, se a operação for soma, o Prolog aplica a regra correspondente automaticamente. Casos como divisão por zero ou operação inválida também são tratados por regras específicas.

Essa abordagem é característica do paradigma declarativo, onde o foco está no que deve ser feito, e não em como fazer.

```prolog
inicio :- laço.

laço :-
    escreva('Digite o primeiro numero: ')
    leia(Num1),

    escreva('Digite a operacao (+, -, *, /): ')
    leia(Operacao),

    escreva('Digite o segundo numero: ')
    leia(Num2),

    calcular(Num1, Operacao, Num2, Resultado)
    escreva(Resultado)

    loop.

calcular(Num1, '+', Num2, Resultado) :- Resultado is Num1 + Num2.
calcular(Num1, '-', Num2, Resultado) :- Resultado is Num1 - Num2.
calcular(Num1, '*', Num2, Resultado) :- Resultado is Num1 * Num2.
calcular(Num1, '/', Num2, Resultado) :- Num2 =\= 0, Resultado is Num1 / Num2.
calcular(_, '/', 0, 'Erro: Divisao por 0').
calcular(_, Operacao, _, 'Operacao invalida') :-
    Operacao \= '+',
    Operacao \= '-',
    Operacao \= '*',
    Operacao \= '/'.
```

## Conclusão

Os dois códigos resolvem o mesmo problema, mas de formas diferentes. O Java segue o paradigma imperativo, onde o programador define todo o fluxo de execução, controlando cada etapa do processo. Já o Prolog segue o paradigma declarativo, onde o foco está na definição de regras, deixando que o próprio sistema encontre a solução. Essa comparação mostra na prática como paradigmas diferentes influenciam diretamente na forma de escrever e entender um programa.