# Paradigmas de Programação: Imperativo vs Declarativo

Os paradigmas de programação representam diferentes formas de pensar e estruturar soluções computacionais. Entre os mais relevantes estão o **paradigma imperativo** e o **paradigma declarativo**, que se distinguem principalmente pela maneira como expressam a lógica de um programa.

## Paradigma Imperativo

O paradigma imperativo baseia-se na descrição explícita dos **passos necessários** para alcançar um determinado resultado. Ou seja, o programador especifica *como* o problema deve ser resolvido, controlando diretamente o fluxo de execução por meio de instruções, variáveis e estruturas de controle como loops e condicionais.

A linguagem Java é um exemplo clássico desse paradigma.

```java
int soma = 0;
for (int i = 1; i <= 5; i++) {
    soma += i;
}
System.out.println(soma);
```

Nesse exemplo, o programa calcula a soma dos números de 1 a 5. O funcionamento ocorre da seguinte forma:

Inicializa-se a variável soma com valor 0;
Um laço for percorre de 1 até 5;
A cada iteração, o valor de i é somado à variável soma;
Ao final, o resultado é impresso.

Percebe-se que o programador controla detalhadamente cada etapa do processo, definindo explicitamente o fluxo de execução.

Paradigma Declarativo

Por outro lado, o paradigma declarativo foca em o que deve ser resolvido, e não em como resolver. Nesse modelo, o programador descreve as propriedades do problema, e o sistema se encarrega de encontrar a solução.

A linguagem Prolog é um exemplo representativo desse paradigma. O mesmo problema da soma pode ser representado da seguinte forma:
```
soma(0, 0).
soma(N, S) :-
    N > 0,
    N1 is N - 1,
    soma(N1, S1),
    S is S1 + N.
```
 Enquanto o código em Java detalha cada etapa do cálculo da soma, o código em Prolog define apenas as regras matemáticas envolvidas. O Java exige maior controle manual do processo, enquanto o Prolog abstrai esse controle, delegando ao interpretador a responsabilidade de encontrar a solução.

Conclusão

Os paradigmas imperativo e declarativo oferecem abordagens distintas para a resolução de problemas. O imperativo proporciona maior controle e previsibilidade sobre a execução, sendo amplamente utilizado em aplicações tradicionais. Já o declarativo favorece a expressividade e a abstração, sendo especialmente útil em problemas que envolvem lógica, inferência e relações formais.