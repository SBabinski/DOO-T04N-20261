# Entendendo os Paradigmas de Programação:

Na aula desta semana, foram apresentados os paradigmas de programação imperativo e declarativo. Foi possível entender que, mesmo com objetivos semelhantes, cada paradigma resolve problemas de formas diferentes.
## Imperativo:
O paradigma imperativo, utilizado em linguagens como Java, funciona com base em
instruções passo a passo. Ou seja, o programador precisa dizer exatamente como o
computador deve executar cada ação até chegar no resultado final.

Um exemplo simples em Java seria:

```
int soma = 0;
for (int i = 1; i <= 5; i++) {
    soma += i;
}
System.out.println(soma);
```

Nesse exemplo, é possível ver claramente todas as etapas que o programa executa até chegar ao resultado.

## Declarativo:
Já o paradigma declarativo, utilizado em linguagens como Prolog, tem uma abordagem diferente. Em vez de focar em como resolver o problema, ele descreve o que deve ser feito, deixando que o próprio sistema encontre a solução.

Um exemplo em Prolog seria:

```
soma(0, 0).
soma(N, Resultado) :-
    N > 0,
    N1 is N - 1,
    soma(N1, R),
    Resultado is R + N.
```

Nesse caso, o código define regras e relações, sem detalhar o passo a passo da execução.

Comparando os dois paradigmas, é possível perceber que o imperativo oferece mais controle sobre o fluxo do programa, sendo mais direto e detalhado. Por outro lado, o declarativo é mais abstrato, focando no resultado final e deixando a lógica de execução por conta do sistema.

Cada abordagem possui suas vantagens, e a escolha depende do tipo de problema que se deseja resolver. Durante a aula, ficou claro que diferentes formas de pensar podem levar ao mesmo resultado, mas com estruturas bem diferentes.