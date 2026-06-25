# Paradigmas de Programação: Imperativo vs Declarativo

Os paradigmas de programação são diferentes formas de pensar e estruturar soluções computacionais. Entre os principais, destacam-se o imperativo e o declarativo, que se diferenciam principalmente pela maneira como expressam a solução de um problema.

O paradigma imperativo foca em explicar como resolver um problema. Nele, devemos descrever passo a passo o que o computador deve fazer, usando variáveis, estruturas de controle (como if, for, while). Aprendemos durante aula que a linguagem Java é um exemplo clássico desse tipo de abordagem.

Já o paradigma declarativo se concentra em dizer o que precisa ser resolvido, sem entrar nos detalhes de como isso será feito. O programador define regras, relações ou propriedades, e o sistema se encarrega de encontrar a solução. Um exemplo também mostrado durante a aula é o Prolog, que trabalha com lógica e mecanismos de inferência.

## Comparação entre Java (Imperativo) e Prolog (Declarativo)

### Exemplo em Java (Imperativo)
```java
boolean pertence(int[] lista, int valor) {
    for (int i = 0; i < lista.length; i++) {
        if (lista[i] == valor) {
            return true;
        }
    }
    return false;
} 
```
Nesse caso, o programa descreve exatamente o que fazer:
Percorre a lista usando um laço for;
Compara cada elemento com o valor desejado;
Para a execução assim que encontra o valor.
Ou seja, cada passo da solução é explicitamente definido.

### Exemplo em Prolog (Declarativo)
```
pertence(X, [X|_]).
pertence(X, [_|T]) :- pertence(X, T).
```
Um elemento pertence à lista se for o primeiro;
Caso contrário, ele pertence se estiver no restante da lista.
Não há laços ou controle de fluxo explícito. O próprio Prolog testa as possibilidades automaticamente usando um mecanismo chamado backtracking.

#### Imperativo vs Declarativo
A principal diferença entre os paradigmas está em quem controla a execução:

**Imperativo (Java):** o programador controla tudo. Isso torna o comportamento mais previsível.
**Declarativo (Prolog):** o programador descreve o problema, e o sistema cuida da execução. 

##### Conclusão

A escolha entre esses paradigmas depende do tipo de problema que você quer resolver. O estilo imperativo é muito usado no dia a dia por oferecer controle. Já o declarativo é bastante útil em problemas baseados em lógica.
