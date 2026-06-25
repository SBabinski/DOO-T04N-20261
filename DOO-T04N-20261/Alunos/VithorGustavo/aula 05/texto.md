# Paradigmas de Programação: Imperativo vs Declarativo

## Introdução

Na programação, os paradigmas definem a abordagem utilizada para resolver problemas computacionais. Dois paradigmas fundamentais são o imperativo e o declarativo. O paradigma imperativo foca em descrever como o computador deve executar uma tarefa, através de sequências de comandos que alteram o estado do programa. Já o paradigma declarativo concentra-se no que deve ser alcançado, especificando o resultado desejado sem detalhar os passos intermediários.

## Paradigma Imperativo

No paradigma imperativo, o programador instrui o computador passo a passo sobre como realizar uma operação. Linguagens como Java exemplificam esse paradigma, onde o código é estruturado em comandos que modificam variáveis e controlam o fluxo de execução.

### Exemplo em Java: Cálculo do Fatorial

```java
public class Fatorial {
    public static int calcularFatorial(int n) {
        int resultado = 1;
        for (int i = 1; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }
}
```

Neste código, o método `calcularFatorial` utiliza um loop `for` para iterar de 1 até `n`, multiplicando o `resultado` em cada iteração. O foco está na manipulação explícita de variáveis e no controle do fluxo, característico do estilo imperativo.

## Paradigma Declarativo

O paradigma declarativo, por outro lado, descreve o problema em termos de declarações lógicas ou funcionais, deixando para o sistema a responsabilidade de determinar como alcançar o resultado. Linguagens como Prolog, baseadas em lógica, são exemplos desse paradigma.

### Exemplo em Prolog: Cálculo do Fatorial

```prolog
factorial(0, 1).
factorial(N, F) :- N > 0, N1 is N - 1, factorial(N1, F1), F is F1 * N.
```

Aqui, o predicado `factorial` define regras: o fatorial de 0 é 1, e para N > 0, calcula recursivamente o fatorial de N-1 e multiplica por N. O Prolog utiliza unificação e backtracking para encontrar soluções que satisfaçam as regras declaradas.

## Comparação

Ambos os trechos de código alcançam o mesmo objetivo: calcular o fatorial de um número. No entanto, o código em Java é imperativo, exigindo que o programador especifique explicitamente o loop e as operações de multiplicação, controlando o estado através de variáveis mutáveis. Em contraste, o código em Prolog é declarativo, definindo relações lógicas que o interpretador resolve automaticamente, sem necessidade de gerenciar estados intermediários.

Essa diferença reflete filosofias distintas: o imperativo é mais próximo da máquina, oferecendo controle fino e eficiência, enquanto o declarativo abstrai detalhes de implementação, facilitando a expressão de problemas complexos em termos de regras e fatos.

## Conclusão

Entender esses paradigmas é essencial para escolher a ferramenta adequada para cada problema. O imperativo é ideal para tarefas que requerem otimização e controle detalhado, como em sistemas embarcados, enquanto o declarativo brilha em áreas como inteligência artificial e processamento de consultas, onde a clareza lógica prevalece.