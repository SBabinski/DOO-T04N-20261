# Paradigmas de Programação

## Introdução

Os paradigmas de programação representam diferentes formas de pensar e estruturar a resolução de problemas computacionais. Entre eles, destacam-se os paradigmas imperativo e declarativo, que se diferenciam principalmente pela maneira como as instruções são expressas.

## Paradigma Imperativo

O paradigma imperativo baseia-se na descrição explícita das etapas necessárias para atingir um determinado resultado. Nesse modelo, o programador controla o fluxo de execução por meio de comandos sequenciais, estruturas de repetição e decisões condicionais. Linguagens como Java são amplamente utilizadas dentro desse paradigma.

```
int soma = 0;
for (int i = 1; i <= 5; i++) {
    soma += i;
}
System.out.println(soma);
```

## Paradigma Declarativo

O paradigma declarativo propõe uma abordagem mais abstrata, focando no que deve ser feito, e não em como fazer. Linguagens como Prolog utilizam regras lógicas e mecanismos de inferência para resolver problemas.

```
soma(0, 0).
soma(N, Resultado) :-
    N > 0,
    N1 is N - 1,
    soma(N1, R1),
    Resultado is R1 + N.
```

## Comparação entre os Paradigmas

A principal diferença entre esses paradigmas está no nível de abstração. O paradigma imperativo exige que o programador descreva cada etapa da solução, enquanto o declarativo permite uma descrição mais direta do objetivo.

## Conclusão

Ambos os paradigmas são importantes na computação. O imperativo oferece maior controle, enquanto o declarativo proporciona maior abstração.