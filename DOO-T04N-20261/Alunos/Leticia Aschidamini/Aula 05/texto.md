# Aula 05 – Paradigmas de Programação
## Paradigma Imperativo
No paradigma imperativo, você diz ao computador passo a passo como fazer. Você controla o fluxo do programa usando loops, condicionais e variáveis. Aqui, você explica detalhadamente como chegar ao resultado.
### Exemplo Imperativo – Subtrair números (Java)
```java
import java.util.Arrays;

public class SubtracaoImperativa {
    public static void main(String[] args) {
        int[] numeros = {50, 10, 5, 2};
        int resultado = numeros[0];

        for (int i = 1; i < numeros.length; i++) {
            resultado -= numeros[i];
        }

        System.out.println("Resultado da subtração: " + resultado);
    }
}
```
#### Explicação:
Usamos um loop for para percorrer cada número do array e subtrair do resultado acumulado. Detalhamos como o programa deve realizar a subtração.

## Paradigma Declarativo

No paradigma declarativo, você foca em o que quer fazer, sem detalhar passo a passo. Você descreve o resultado desejado, e a linguagem decide como executar. Esse paradigma é comum em Prolog e programação funcional.
### Exemplo Declarativo – Subtrair números (Prolog)

```% Lista de números  
numeros([50, 10, 5, 2]).  
  
% Regra para subtrair elementos da lista  
subtrai_lista([X], X).  % se só tiver um número, ele é o resultado  
subtrai_lista([H|T], Resultado) :-  
	 subtrai_lista(T, Resto),  
	 Resultado is H - Resto.  
  
% Consulta para obter o resultado  
% ?- numeros(L), subtrai_lista(L, R).
```

#### Explicação:
Não dizemos como percorrer a lista, apenas definimos o que queremos: o resultado da subtração dos números. O Prolog cuida do fluxo de execução.