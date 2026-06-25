Paradigmas de Programacao: Imperativo e Declarativo

A forma como expressamos a solucao de um problema em codigo depende diretamente do paradigma de programacao adotado. Dois dos paradigmas mais fundamentais sao o imperativo e o declarativo, que diferem essencialmente na abordagem: enquanto o primeiro descreve como chegar a um resultado, o segundo descreve o que se deseja obter.

O paradigma imperativo e baseado na ideia de que um programa e uma sequencia de instrucoes que modificam o estado do sistema ao longo do tempo. O programador define passo a passo o caminho que o computador deve percorrer para atingir o resultado esperado. Linguagens como Java, C e Python seguem esse modelo. O foco esta no fluxo de execucao, com uso de variaveis mutaveis, atribuicoes e estruturas de controle explicitas como if, for e while.

No paradigma declarativo, o programador especifica o que o programa deve fazer, e nao como faze-lo. A logica de controle e o mecanismo de busca ficam a cargo da linguagem ou do motor de inferencia. Prolog, SQL e linguagens funcionais como Haskell sao exemplos desse paradigma. O programador define fatos, regras e relacoes, e o sistema se encarrega de encontrar a solucao.

Para ilustrar a diferenca entre os dois paradigmas, considere o problema de verificar se um numero e par. Em Java, o codigo seria escrito da seguinte forma:

```java
public class Exemplo {
    public static void main(String[] args) {
        int numero = 8;

        if (numero % 2 == 0) {
            System.out.println(numero + " e par.");
        } else {
            System.out.println(numero + " e impar.");
        }
    }
}
```

Nesse trecho, o programa segue uma logica sequencial clara. Uma variavel numero e declarada e inicializada com o valor 8. Em seguida, uma estrutura condicional if/else verifica explicitamente se o resto da divisao por 2 e igual a zero. Com base nessa verificacao, uma mensagem e impressa no console. O programador e responsavel por definir cada etapa da logica: a atribuicao, a condicao e a saida. O controle do fluxo e total e explicito.

Em Prolog, o mesmo problema e resolvido de forma completamente diferente:

```prolog
par(X) :- 0 is X mod 2.

?- par(8).
true.

?- par(7).
false.
```

Aqui, define-se uma regra: par(X) e verdadeiro se X mod 2 resultar em 0. Ao realizar uma consulta, o motor de inferencia do Prolog tenta satisfazer a regra com o valor fornecido e retorna true ou false sem que o programador precise descrever como a verificacao ocorre internamente. Nao ha lacos, nao ha atribuicoes tradicionais, nao ha controle de fluxo. O programador simplesmente declara uma relacao logica, e o Prolog se encarrega de verificar se ela pode ser satisfeita.

Os dois paradigmas representam filosofias distintas de resolucao de problemas. O paradigma imperativo oferece controle preciso e e amplamente utilizado no desenvolvimento de sistemas comerciais e aplicacoes de proposito geral. O paradigma declarativo permite expressar conhecimento e relacoes de forma concisa, sendo especialmente poderoso em dominios de inteligencia artificial, inferencia logica e processamento de linguagem natural. Compreender ambos e essencial para o desenvolvimento de uma visao ampla sobre computacao, permitindo ao programador escolher a abordagem mais adequada para cada problema.
