Comparação entre Paradigmas de Programação: Imperativo e Declarativo:

O desenvolvimento de software utiliza diferentes abordagens lógicas para resolver problemas. As duas principais são o paradigma imperativo e o paradigma declarativo, que se distinguem pela forma como as instruções são transmitidas ao computador.

O Paradigma Imperativo:
No modelo imperativo, o foco está no procedimento. O programador dita exatamente os passos que o computador deve seguir para chegar ao resultado. É uma sequência de comandos que descreve o "como" fazer.

O Paradigma Declarativo:
Já no modelo declarativo, o foco está na lógica. Em vez de descrever o fluxo de execução, o programador descreve o resultado desejado ou as regras do problema. O sistema de execução da linguagem decide a melhor forma de processar essas regras. O foco aqui é o "o que" deve ser feito.
Estudo de Caso: Somar Números Pares
Comparamos como os dois paradigmas lidam com a tarefa de filtrar e somar apenas os números pares de uma lista.

Exemplo em Java (Imperativo)
Em Java, utilizamos estruturas de repetição e condicionais para controlar manualmente:


public class SomaPares {
    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5, 6};
        int soma = 0;

        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] % 2 == 0) {
                soma += numeros[i];
            }
        }
        
        System.out.println("Resultado: " + soma);
    }
}

Funcionamento: O código inicia uma variável que acumula valores, percorre a lista elemento por elemento, testa se o resto da divisão por dois é zero e, se for, adiciona o valor à soma.  O controle do índice e do acúmulo é aparente.

Exemplo em Prolog (Declarativo)
Em Prolog, definimos o que é um número par e como a soma deve se comportar através de regras lógicas.


% Definição: um número é par se o resto da divisão por 2 for 0.
par(N) :- 0 is N mod 2.

% Regras para somar pares em uma lista
soma_pares([], 0).
soma_pares([H|T], S) :- par(H), soma_pares(T, S1), S is H + S1.
soma_pares([H|T], S) :- \+ par(H), soma_pares(T, S).

Funcionamento: Não há um loop for. Definimos que a soma de uma lista vazia é zero e criamos regras: se o primeiro item for par, a soma total é esse item mais a soma do restante da lista. Se não for par, apenas ignoramos o item e seguimos. O motor do Prolog busca resolver essas condições automaticamente.

Conclusão:
Enquanto o paradigma imperativo (Java) é eficiente para detalhar processos e controlar a memória passo a passo, o paradigma declarativo (Prolog) permite expressar problemas complexos, focando nas regras que comandam os dados.
