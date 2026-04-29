# Paradigmas de Programação: Imperativo vs Declarativo

Este documento apresenta uma análise comparativa entre os paradigmas Imperativo e Declarativo, utilizando as linguagens Java e Prolog como base, conforme solicitado na Aula 05.

## 1. Paradigma Imperativo (Java)
No paradigma imperativo, o foco principal é o **"como"** resolver um problema. O programador escreve uma sequência de comandos que o computador deve seguir passo a passo para alterar o estado do programa e chegar ao resultado.

**Exemplo Prático:**
Se quisermos somar números de 1 a 5 em Java, precisamos criar uma variável, definir um laço de repetição (loop) e somar os valores manualmente:
```java
int soma = 0;
for (int i = 1; i <= 5; i++) {
    soma += i;
}
2. Paradigma Declarativo (Prolog)
Já no paradigma declarativo, o foco é o "o quê" deve ser resolvido. Em vez de dar ordens de passos técnicos, nós declaramos fatos e regras lógicas. O computador utiliza um motor de inferência para descobrir a resposta sozinho.

Exemplo Prático:
Em Prolog, não usamos loops. Apenas dizemos quem é pai de quem e criamos uma regra de que, se duas pessoas têm o mesmo pai, elas são irmãs:

Prolog
pai(joao, maria).
pai(joao, jose).
irmao(X, Y) :- pai(P, X), pai(P, Y).
Conclusão
Enquanto o Java é excelente para sistemas que exigem controle total do fluxo e performance (como o sistema da My Plant), o Prolog é ideal para inteligência artificial e bancos de dados lógicos, onde a relação entre as informações é mais importante que o algoritmo em si.
