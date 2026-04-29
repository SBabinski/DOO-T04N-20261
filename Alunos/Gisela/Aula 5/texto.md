Nome: Gisela T. - Turma: T04N

//texto.md

# <span style="color: darkcyan"> PESQUISA PROLOG E JAVA</span>

<div style="color:darkcyan;">


## APRESENTAÇÃO
O que será apresentado nesse documento:
- Descrição de ambas as linguagens;
- Comparação entre paradigmas imperativos e declarativos;
- Comparação entre códigos Java e Prolog.

##

## O que são paradigmas de programação
Um paradigma de programação é um conjunto de regras não escritas, paradigmas ditam como um código deve ser escrito bem como sua estrutura, cada um deles representa uma forma de pensar para resolver um problema.

## Tipos de paradigmas
### Paradigma Declarativo 
Focado na resolução do problema, não no fluxo. Seus pilares são:

-Funcional: Todo o código é feito de funções, a ordem em que ele acontece não determina o resultado;

-Lógico: Aqui é onde o dev define qual o problema a ser resolvido através da declaração de regras e fatos, o sistema resolve com base na suposição (ou inferência).

### Paradigma Imperativo
É composto por um conjunto de instruções, e cada uma delas dita como dever ser feito e o que deve ser feito. Seus pilares são:
- Programação Estruturada: Tem como objetivo otimizar o código, o deixando mais legível por meio dos blocos de decisão (if, else, elseif), das interações (for e while) e também as funções, tudo contribui para leitura do código e sua reutilização;
- Programação Procedural: São uma lista de instruções que guiam o computador passo a passo. Se baseia na chamada de procedimento, em que cada instrução também é estruturada em procedimentos, que são as funções.

## Comparação entre códigos Java e Prolog.
Em seguida veremos exemplos de ambas linguagens,
- Problema: Verificar a soma dos elementos de uma lista;
- diferentes formas de pensar: Imperativa e descritiva, respectivamente:
</div>

```java
import java.util.List;
import java.util.Arrays;

public class SomaLista {
    public static int soma(List<Integer> lista) {
        int total = 0;
        for (int num : lista) {
            total += num;
        }
        return total;
    }

    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(soma(numeros)); 
    }
}
```


```prolog
soma([], 0).
soma([H|T], S) :- 
    soma(T, S1),
    S is H + S1.

% Consulta:
?- soma([1,2,3,4,5], Resultado).
```

<div style="color:darkcyan;">

### Conclusão:
Como visto em aula teórica, ambas as linguagens visam resolver um problema, mas pensando de maneiras distintas.

Java ☕ detalha cada passo, declara variáveis, define funções e loops, acumula a soma e a exibe na tela.

ProLog exige apenas regras, o próprio código encontra a solução através da lógica.