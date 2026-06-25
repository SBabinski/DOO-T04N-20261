# Atividade Aula 04 - Explicação
### 01 - início
* O começo da aula foi explicando o que são linguagens de marcação usando exemplos como md, yaml, yml, xml entre outras portanto no início aprendemos como são e porque são importantes visto que nelas aprendemos como usar a documentação a nosso favor e produzir desenvolvimentos com mais comprometimento inclusive usando alguns exemplos de como desenvolver como títulos e subtítulos.

### 02 - Paradigmas de programação
* Na aula foram ditos alguns conceitos de paradigmas (regras que todos seguem ou maneira de pensar que todos seguem) de programação deixarei alguns em exemplo:

Paradigmas de programação são moldes ou abordagens para resolver problemas, divididos principalmente em imperativos (como fazer) e declarativos (o que fazer). Os principais exemplos incluem Orientação a Objetos (Java), Funcional (Haskell), Procedural (C), Lógico (Prolog) e Reativo (RxJS).

* Paradigma imperativo : como foi explicado antes é uma sequência de instruções realizadas uma a uma consiste em executar um conjunto de instruções e cada uma das declarações representa uma ação.
 ## Exemplos: 
 ### 1.0 Programação orientada a objetos
 ### 2.0 programação estruturada 
 ## #3.0 programação procedural.

1.0 - (abordado na linha x)

2.0 - Visando otimizar o código, ela trabalha com fluxo de blocos e estruturas de decisão como if, else e then.

3.0 - É um paradigma que organiza a estrutura do código em funções/sub-rotinas e instruções para resolver problemas.

* Paradigma declarativo : Foca na lógica em si e no resultado atingido onde o controle não é o mais importante e sim o que veio de resultado em si.

## Exemplos:
### 1.0 Funcional.
### 2.0 Lógica.

1.0 - Parte do pressuposto que tudo são funções matemáticas para o computador resolver que no fim irão entregar um resultado esperado.

### Diferença entre Programação Funcional e Procedural

**A grande difernça entre funcional x procedural é que A programação procedural foca no "como" fazer, usando uma sequência linear de passos e alterando estados (variáveis) para alcançar um resultado. A programação funcional foca no "o que" fazer, usando funções puras, imutabilidade de dados e evitando estados compartilhados.**

2.0 - Programador define o problema que ele quer resolver com isso o programa encontra a melhor solução para o problema usando a lógica.

## Exemplo de programação Lógica (Prolog):

```
PROLOG INICIO

% Fatos
pai(joao, maria).
% Declarando que pai de maria é joao
pai(joao, pedro).
mae(ana, maria).
% Declarando que mae de maria é ana
mae(ana, pedro).

% Regra: X é irmão de Y se têm o mesmo pai e não são a mesma pessoa
% Definindo as regras para serem processadas
irmao(X, Y) :-
    pai(P, X),
    pai(P, Y),
    X \= Y.
%Após isso eu realizo as queries dentro do sistema que me devolvem os valores sendo true ou false.
%EX:   ?- pai(joao, maria).   ESPERADO: TRUE

PROLOG FIM
```

## POO - Programação orientada a objetos
Criada na década de 1970 por Alan Kay, cientista da computação. Se baseia na criação de objetos que possuem propriedadas e métodos próprios permitindo encapsulamento (de propriedades do objeto) e a reutilização do código em POO todos os componentes do programa são representados como objetos

### A ideia de Alan era modelar entidades do mundo real com atributos (estado) e métodos (comportamento) EX: árvore tem atributos como madeira, cor, galhos, flores(Angiospermas) entre outros, para exemplificar métodos uma árvore teria todos esses atributos citados e teria os métodos: Crescer(), DarFrutos() e CairFolhas().

## Exemplo de POO (Java)
```
// Aqui temos a classe Carro que tem atributos como cor e velocidade
class Carro {
    String cor;
    int velocidade;
//aqui temos um exemplo de método dentro da classe que se refere a aceleração do veículo
    void acelerar() {
        velocidade += 10;
    }
}

```