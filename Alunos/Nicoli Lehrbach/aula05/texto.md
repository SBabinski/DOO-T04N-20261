# Paradigmas de Programação: Imperativo vs Declarativo

Os paradigmas de programação representam diferentes formas de pensar. O paradigma imperativo e o declarativo, que possuem abordagens distintas na maneira de resolver problemas.

O paradigma imperativo se baseia na descrição detalhada de como uma tarefa deve ser executada. Nesse modelo, o programador define passo a passo as instruções, controlando o fluxo do programa por meio de estruturas como condicionais e laços de repetição. A linguagem Java é um exemplo desse paradigma, pois exige que o desenvolvedor especifique exatamente cada etapa necessária para alcançar o resultado desejado.

Por outro lado, o paradigma declarativo tem como foco descrever o que deve ser feito, sem necessariamente detalhar como isso será executado. Nesse caso, o programador define regras, fatos ou expressões, e o sistema se encarrega de processar essas informações para encontrar a solução. A linguagem Prolog é um exemplo.

## Comparação entre Java (Imperativo) e Prolog (Declarativo)

Ao comparar códigos nesses dois paradigmas, a diferença se torna bastante evidente.

### Exemplo em Java (Imperativo)
```java
boolean pertence(int[] lista, int valor) {
    for (int i = 0; i < lista.length; i++) {
        if (lista[i] == valor) {
            return true;
        }
    }
    return false;
} 
```
Nesse caso, o programa descreve exatamente o que deve ser feito:

Percorrer a lista usando um laço for;
Comparar cada elemento com o valor desejado;
Encerrar a execução assim que encontrar o valor.

Ou seja, cada passo da solução é explicitamente definido, demonstrando controle total do programador.

### Exemplo em Prolog (Declarativo)
```
pertence(X, [X|_]).
pertence(X, [_|T]) :- pertence(X, T).
```

Aqui, a lógica é expressa de forma declarativa:

Um elemento pertence à lista se for o primeiro;
Caso contrário, ele pertence se estiver no restante da lista.

Não há laços ou controle de fluxo explícito. O próprio Prolog testa automaticamente as possibilidades por meio de um mecanismo chamado backtracking.

## Diferença Fundamental
A principal diferença entre os paradigmas está em quem controla a execução:

Imperativo (Java): o programador controla todo o fluxo da execução, tornando o comportamento mais previsível e detalhado.
Declarativo (Prolog): o programador descreve o problema, e o sistema se encarrega de encontrar a solução.


