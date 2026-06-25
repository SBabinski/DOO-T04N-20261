# Paradigmas de Programação: Imperativo vs Declarativo

## Introdução

Os paradigmas de programação representam diferentes formas de estruturar soluções computacionais. Entre os principais estão o paradigma imperativo e o paradigma declarativo. O primeiro enfatiza a descrição detalhada dos passos necessários para resolver um problema (o "como"), enquanto o segundo foca na definição do resultado desejado (o "quê"), deixando que o sistema determine a forma de alcançá-lo. Este texto compara esses dois estilos por meio de exemplos em Java e Prolog.

## Paradigma Imperativo

O paradigma imperativo baseia-se na execução sequencial de comandos que alteram o estado do programa ao longo do tempo. Nesse modelo, o desenvolvedor especifica explicitamente cada etapa da solução.

Suas principais características incluem:

- Controle explícito do fluxo de execução;
- Uso de variáveis mutáveis;
- Estruturas como loops e condicionais para controlar o comportamento do programa.

Linguagens como Java, C e Python adotam majoritariamente esse paradigma.

## Paradigma Declarativo

O paradigma declarativo, por sua vez, concentra-se na descrição das regras e relações que definem o problema. O programador declara fatos e condições, enquanto o mecanismo interno da linguagem se encarrega de encontrar a solução.

Entre suas características estão:

- Definição de fatos e regras;
- Ausência de controle explícito de fluxo;
- Resolução baseada em mecanismos automáticos de inferência.

Prolog é um exemplo clássico desse paradigma.

## Exemplo Prático: Cálculo do Fatorial

### Java (Imperativo)

```java
public class Fatorial {
    public static int calcularFatorial(int n) {
        int resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }
}

Neste exemplo, o algoritmo define passo a passo como o cálculo deve ocorrer. A variável resultado é inicializada com 1 e, a cada iteração do laço, seu valor é atualizado multiplicando pelo próximo número da sequência. O controle do fluxo é totalmente explícito.

Prolog (Declarativo)
fatorial(0, 1).
fatorial(N, F) :-
    N > 0,
    N1 is N - 1,
    fatorial(N1, F1),
    F is N * F1.

Ao realizar a consulta fatorial(5, X), o Prolog utiliza suas regras para inferir o resultado. O programa define apenas a relação matemática do fatorial, composta por um caso base e uma regra recursiva. O mecanismo de unificação e backtracking da linguagem explora as regras até satisfazer a consulta.

Comparação

Embora ambos os códigos resolvam o mesmo problema, a abordagem é distinta. Em Java, o programador controla diretamente cada etapa do processo, manipulando variáveis e estruturas de repetição. Já em Prolog, a solução emerge da aplicação de regras lógicas, sem que o fluxo de execução seja explicitamente definido.

Enquanto o paradigma imperativo é orientado à execução sequencial e à modificação de estado, o declarativo enfatiza a definição de relações e a inferência automática.

Conclusão

Os paradigmas imperativo e declarativo representam diferentes formas de raciocínio computacional. Compreender ambos amplia a capacidade analítica do desenvolvedor, permitindo escolher a abordagem mais adequada para cada tipo de problema. Mais do que concorrentes, esses paradigmas são complementares dentro da evolução da programação.