# Aula 05 — Paradigmas de Programação

## Introdução

Um paradigma de programação define a maneira como um programador estrutura e expressa a solução de um problema. Mais do que uma escolha de linguagem, trata-se de uma forma de pensar: o código deve descrever *como* a tarefa deve ser executada, ou apenas *o que* se deseja obter? Essa distinção fundamental separa dois dos paradigmas mais importantes da computação: o **imperativo** e o **declarativo**.


## Paradigma Imperativo

No paradigma imperativo, o programador assume o controle total da execução. O programa é uma sequência de instruções explícitas que descrevem, passo a passo, como o computador deve agir para alcançar um resultado. Variáveis armazenam e modificam estado, laços de repetição controlam iterações e condicionais desviam o fluxo conforme necessário.

É o paradigma mais próximo do funcionamento interno da máquina, o que oferece alto controle sobre desempenho e comportamento. Linguagens como **Java**, **C** e **Python** são exemplos consolidados desse modelo.

---

## Paradigma Declarativo

No paradigma declarativo, a abordagem é inversa: o programador descreve *o que* é verdadeiro sobre o problema, sem detalhar os passos para resolvê-lo. A responsabilidade de determinar *como* executar a solução é delegada ao próprio sistema da linguagem.

Isso resulta em um código mais próximo da lógica matemática e, em geral, mais conciso e expressivo. O **Prolog**, principal representante do paradigma lógico (uma vertente do declarativo), opera com base em fatos e regras: o programador declara o conhecimento sobre o problema e o motor de inferência da linguagem deduz as respostas.

---

## Comparação Prática: Cálculo do Fatorial

O fatorial de um número inteiro não-negativo `n` é definido matematicamente como:

- `fatorial(0) = 1`
- `fatorial(n) = n × fatorial(n - 1)`, para `n > 0`

Esse problema clássico permite ilustrar com clareza a diferença de abordagem entre os dois paradigmas.

### Java — Imperativo

```java
public class Fatorial {
    public static int calcular(int n) {
        int resultado = 1;

        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }

        return resultado;
    }

    public static void main(String[] args) {
        System.out.println(calcular(5)); // Saída: 120
    }
}
```

No código Java, o programador descreve explicitamente cada etapa do cálculo: inicializa uma variável `resultado` com o valor 1, percorre os inteiros de 2 até `n` com um laço `for`, e multiplica cada valor ao acumulador. O processo é totalmente visível e controlado pelo programador, instrução por instrução.

---

### Prolog — Declarativo (Lógico)

```prolog
fatorial(0, 1).
fatorial(N, F) :-
    N > 0,
    N1 is N - 1,
    fatorial(N1, F1),
    F is N * F1.

% Consulta:
% ?- fatorial(5, F).
% F = 120.
```

Em Prolog, o fatorial é definido por duas regras lógicas que espelham diretamente a definição matemática: o fatorial de 0 é 1 (caso base), e o fatorial de qualquer `N > 0` é `N` multiplicado pelo fatorial de `N-1` (caso recursivo). Não há laço, não há variável acumuladora e não há controle de fluxo explícito. O programador declara *o que é verdadeiro* sobre o fatorial, e o motor de inferência do Prolog determina como derivar o resultado a partir dessas regras.


## Comparativo

Enquanto o código Java foca em **como executar o cálculo** — controlando explicitamente o fluxo por meio de um laço `for` —, o código Prolog foca em **o que é verdadeiro sobre o problema**, deixando o controle de execução a cargo da própria linguagem. Em termos de proximidade com a definição matemática, o Prolog leva vantagem: suas regras espelham diretamente a notação formal do fatorial, tornando o código mais legível para quem pensa no domínio do problema. O Java, por sua vez, exige que o programador traduza essa definição para uma sequência de operações de máquina, o que adiciona camadas de detalhe que nada têm a ver com a lógica em si.

---

## Conclusão

O exemplo do fatorial evidencia bem a diferença de mentalidade entre os dois paradigmas. Em Java, o programador pensa em termos de máquina: inicializar, iterar, acumular. Em Prolog, o programador pensa em termos do problema: o que *define* o fatorial? Ambas as abordagens chegam ao mesmo resultado, mas partem de perspectivas opostas.

Compreender essa distinção é fundamental para o engenheiro de software, pois cada paradigma se adequa melhor a diferentes tipos de problemas. O imperativo oferece controle e previsibilidade; o declarativo oferece expressividade e abstração. Dominar os dois amplia significativamente a capacidade de projetar soluções de qualidade.
