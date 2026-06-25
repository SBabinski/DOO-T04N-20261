# AULA 05 - Atividade sobre Paradigmas

# Imperativo
Durante a aula de hoje percebemos que, no paradigma imperativo, você descreve passo a passo como o programa deve executar uma tarefa. Nesse modelo, são utilizados comandos explícitos, como estruturas de repetição e condicionais, e há controle direto do fluxo de execução, permitindo que você determine exatamente como o processo deve ser realizado.

# Declarativo

Também durante a mesma aula ficou claro que no paradigma declarativo, você descreve o que deseja que o programa faça, sem precisar detalhar todas as etapas do processo. Nesse modelo, o foco está no resultado final, utilizando recursos como exemplo as funções, deixando para a linguagem a responsabilidade de definir como a tarefa será executada.


# Exemplo Imperativo vs Declarativo

## Imperativo

```java  
import java.util.ArrayList;  
import java.util.List;  
  
public class ExemploImperativo {  
	public static void main(String[] args) {  
		List<Integer> numeros = new ArrayList<>();  
		numeros.add(10);  
		numeros.add(20);  
		numeros.add(30);  
  
		int soma = 0;  
  
		for (int i = 0; i < numeros.size(); i++) {  
			soma += numeros.get(i);  
		}  
  
		System.out.println("Soma: " + soma);  
	}  
}
````

## Declarativo
``` 
% Definimos fatos: a lista de números ([10, 20, 30]).

% Definimos uma regra para somar a lista 
soma_lista([], 0).
soma_lista([H|T], Soma) :-
    soma_lista(T, Resto),
    Soma is H + Resto.

% Consulta para obter a soma
% ?- numeros(L), soma_lista(L, S).
