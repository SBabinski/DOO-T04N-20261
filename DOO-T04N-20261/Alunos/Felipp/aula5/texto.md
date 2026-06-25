# Paradigmas de Programação: Imperativo e Declarativo

## Introdução

Na aula teórica, foram apresentados dois paradigmas importantes da programação: o **imperativo** e o **declarativo**. Cada um deles representa uma forma diferente de pensar a construção de soluções computacionais. Além disso, o conteúdo se relaciona com a **Programação Orientada a Objetos (POO)**, que normalmente está associada ao paradigma imperativo, pois organiza o programa em torno de classes, objetos, atributos e métodos.

Compreender esses paradigmas é importante porque eles influenciam diretamente a forma como o desenvolvedor escreve, estrutura e interpreta o código. Enquanto um paradigma enfatiza o controle detalhado da execução, o outro prioriza a descrição lógica do problema.

## Paradigma Imperativo

O paradigma imperativo é baseado na ideia de informar ao computador **como** uma tarefa deve ser realizada. Nesse modelo, o programador descreve uma sequência de passos, instruções e operações que devem ser executadas em determinada ordem para alcançar um resultado.

A **Programação Orientada a Objetos (POO)** é uma abordagem que se encaixa nesse paradigma, pois em linguagens como Java o desenvolvedor cria classes e objetos para representar entidades do mundo real e define comportamentos por meio de métodos. Dessa forma, além de resolver o problema, o código também fica mais organizado, modular e reutilizável.

### Exemplo em Java
### Arquivo do Objeto Carro.java
```java
public class Carro {
    private String marca;
    private String modelo;
    private int ano;
    private boolean ligado;

    public Carro(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.ligado = false;
    }

    public void ligar() {
        ligado = true;
        System.out.println("O carro foi ligado.");
    }

    public void desligar() {
        ligado = false;
        System.out.println("O carro foi desligado.");
    }

    public void acelerar() {
        if (ligado) {
            System.out.println("O carro está acelerando...");
        } else {
            System.out.println("O carro precisa estar ligado para acelerar.");
        }
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public boolean isLigado() {
        return ligado;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
```
### Utilização desse Objeto
### Arquivo Principal.java
```Java
public class Main {
    public static void main(String[] args) {

        // Criando um objeto (instância)
        Carro carro1 = new Carro("Toyota", "Corolla", 2023);

        // Usando métodos
        carro1.ligar();
        carro1.acelerar();

        System.out.println("Marca: " + carro1.getMarca());
        System.out.println("Modelo: " + carro1.getModelo());
        System.out.println("Ano: " + carro1.getAno());

        carro1.desligar();
    }
}
```

### Como o código em Java funciona

No exemplo acima, o programa foi escrito em Java, uma linguagem amplamente associada à Programação Orientada a Objetos. O código cria uma classe chamada Carro, dentro da qual é feita a estruturação baseada em um objeto do mundo real. Validações, valores de entrada e saída e comportamentos são definidos dentro desse objeto, permitindo que ele seja reutilizado no arquivo principal.

Dentro da classe Carro, são declarados atributos como marca, modelo, ano e estado (ligado ou desligado), que representam as características do objeto. Esses atributos são definidos como privados, garantindo o princípio do encapsulamento, ou seja, protegendo os dados contra acessos diretos indevidos.

Além disso, a classe possui um construtor, responsável por inicializar os valores do objeto no momento em que ele é criado. Também são definidos métodos, como ligar(), desligar() e acelerar(), que representam os comportamentos do carro e permitem a interação com o objeto de forma controlada.

Os métodos getters e setters são utilizados para acessar e modificar os atributos de maneira segura, reforçando ainda mais o conceito de encapsulamento.

No arquivo principal (Main), um objeto da classe Carro é instanciado, e seus métodos são utilizados para simular o funcionamento de um carro, como ligá-lo e acelerá-lo. Dessa forma, o código demonstra como a Programação Orientada a Objetos organiza e estrutura um sistema, aproximando-o de situações do mundo real e facilitando a manutenção, reutilização e compreensão do código.

## Paradigma Declarativo

O paradigma declarativo adota uma lógica diferente. Em vez de dizer ao computador **como** executar a tarefa, o programador descreve **o que** deseja obter. Assim, a responsabilidade pela resolução fica mais concentrada no próprio mecanismo da linguagem.

Um dos principais exemplos desse paradigma é o **Prolog**, linguagem baseada em lógica. Nela, o desenvolvedor trabalha com fatos, regras e consultas. Em vez de construir manualmente a sequência de instruções, ele estabelece relações lógicas e deixa que o sistema encontre a resposta.

### Exemplo em Prolog

```prolog
soma(A, B, Resultado) :- Resultado is A + B.
```

### Consulta em Prolog

```prolog
?- soma(5, 3, R).
```

### Resultado esperado

```prolog
R = 8.
```

### Como o código em Prolog funciona

No exemplo em Prolog, foi definida uma regra chamada `soma`, que estabelece a relação entre dois valores e seu resultado. Quando a consulta `soma(5, 3, R)` é feita, o sistema interpreta a regra e calcula automaticamente o valor de `R`, retornando 8.

Diferentemente do Java, o programador não descreve uma sequência detalhada de passos de execução. Ele apenas define a lógica da relação entre os elementos. Dessa forma, o paradigma declarativo se concentra mais na descrição do problema do que na implementação detalhada do processo.

## Comparação entre Java e Prolog

Embora os dois exemplos apresentados possuam objetivos diferentes — um voltado à modelagem de um objeto do mundo real (Java) e outro à realização de uma operação lógica (Prolog) — ambos permitem compreender claramente as diferenças entre os paradigmas.

No **Java**, o programa segue uma lógica imperativa. O desenvolvedor controla cada ação executada pela máquina, definindo variáveis, operações e saída. Além disso, por estar dentro de uma estrutura de classe, o código também demonstra a influência da Programação Orientada a Objetos, ainda que em um exemplo simples.

No **Prolog**, por outro lado, a solução é declarativa. O programador apenas declara a regra da soma, e a linguagem se encarrega de processar a consulta e encontrar a resposta correta. Não há um fluxo sequencial visível como no Java, mas sim uma relação lógica que o interpretador utiliza para chegar ao resultado.

Essa comparação evidencia uma diferença essencial entre os paradigmas: no imperativo, o foco está no **processo**; no declarativo, o foco está no **resultado desejado**.

## Relação com a Programação Orientada a Objetos

A Programação Orientada a Objetos merece destaque nesse contexto porque ela representa uma forma de organizar o paradigma imperativo. Em vez de escrever apenas comandos soltos, o desenvolvedor estrutura a solução com base em objetos que possuem características e comportamentos.

Na prática, a POO oferece vantagens como:
- melhor organização do código;
- maior reutilização de componentes;
- facilidade de manutenção;
- aproximação entre a modelagem do sistema e elementos do mundo real.

Por isso, linguagens como Java são amplamente utilizadas em projetos maiores, nos quais a organização e a escalabilidade do software são fundamentais.

## Conclusão

Os paradigmas imperativo e declarativo representam maneiras distintas de construir soluções computacionais. O paradigma imperativo, exemplificado pelo Java, enfatiza o controle passo a passo da execução e se relaciona diretamente com a Programação Orientada a Objetos, que contribui para a organização e reutilização do código. Já o paradigma declarativo, exemplificado pelo Prolog, concentra-se na descrição lógica do problema, deixando a resolução a cargo da própria linguagem.

Assim, mesmo quando dois programas possuem o mesmo objetivo, a forma de atingi-lo pode variar bastante conforme o paradigma adotado. Estudar essas diferenças é essencial para compreender melhor a lógica das linguagens de programação e ampliar a capacidade de escolher a abordagem mais adequada para cada problema.