# Comparação de Paradigmas de Programação

# Introdução

Os paradigmas de programação representam diferentes formas de pensar e estruturar a solução de problemas computacionais. Neste trabalho, será realizada uma comparação entre o paradigma imperativo, utilizando a linguagem Java, e o paradigma declarativo, utilizando a linguagem Prolog.

O exemplo utilizado é um sistema de vendas de plantas da Dona Gabrielinha que foi exemplo de outras atividades até agora.

# Paradigma Imperativo (Java)

O paradigma imperativo é baseado na execução de instruções passo a passo. Nele, o programador define exatamente como o programa deve funcionar, controlando o fluxo com estruturas como `if`, `switch` e laços de repetição.

# Estrutura do Sistema

O sistema foi desenvolvido com um menu interativo que permite ao usuário escolher diferentes funcionalidades.

```java
public static void menu() {
    System.out.println("Escolha uma opção abaixo");
    System.out.println("1 - Preço Total");
    System.out.println("2 - Troco");
    System.out.println("3 - Registrar venda");
    System.out.println("4 - Consultar vendas por data");
    System.out.println("5 - Sair");

    int escolha = scan.nextInt();

    switch (escolha) {
    case 1:
        calculoPT();
        break;
    case 2:
        troco();
        break;
    case 3:
        registroDeVenda();
        break;
    case 4:
        consultarVendasPorData();
        break;
    case 5:
        System.out.println("Saindo do sistema.");
        return;
    default:
        System.out.println("Opção inválida!!");
    }

    menu();
}
```

Esse trecho mostra claramente o controle do fluxo do programa, característica principal do paradigma imperativo.



# Cálculo do Preço Total

```java
double valorBruto = quantidadeTemp * valorTemp;
double desconto = (quantidadeTemp > 10) ? valorBruto * 0.05 : 0;
double total = valorBruto - desconto;
```

Nesse caso, o programador define todas as etapas do cálculo manualmente, desde a multiplicação até a aplicação do desconto.



# Registro e Consulta de Vendas

O sistema permite registrar vendas com data e consultá-las posteriormente.

```java
Vendas venda = new Vendas(quantidadeTemp, valorTemp, data);
vendas.add(venda);
```

Além disso, há uma busca por data:

```java
for (Vendas v : vendas) {
    if (v.getData().equals(data)) {
        v.mostrarVenda();
    }
}
```

Essas operações demonstram o uso de estruturas de dados e controle manual de busca.


# Uso de Orientação a Objetos

O sistema também utiliza conceitos de orientação a objetos, através da classe `Vendas`.

```java
public Vendas(int quantidade, double valorUnitario, LocalDate data) {
    this.quantidade = quantidade;
    this.valorBruto = quantidade * valorUnitario;

    if (quantidade > 10) {
        this.desconto = valorBruto * 0.05;
    } else {
        this.desconto = 0;
    }

    this.total = valorBruto - desconto;
    this.data = data;
}
```

Essa classe encapsula os dados de uma venda, melhorando a organização e reutilização do código.


# Paradigma Declarativo (Prolog)

O paradigma declarativo difere do imperativo pois foca em descrever o problema e não o passo a passo da solução.

Em Prolog, definimos regras e relações, e o próprio sistema encontra a solução.

# Exemplo em Prolog

```prolog
desconto(Quantidade, Desconto) :-
    Quantidade > 10,
    Desconto is 0.05.

desconto(Quantidade, 0) :-
    Quantidade =< 10.

total(Quantidade, ValorUnitario, Total) :-
    ValorBruto is Quantidade * ValorUnitario,
    desconto(Quantidade, Desc),
    Total is ValorBruto - (ValorBruto * Desc).
```

Nesse modelo, o programador não define o fluxo de execução, a   penas as regras. O Prolog decide como chegar ao resultado.


# Comparação entre os Paradigmas

| Característica | Imperativo (Java) | Declarativo (Prolog) |
| -------------- | ----------------- | -------------------- |
| Controle       | Manual            | Automático           |
| Foco           | Como fazer        | O que fazer          |
| Estrutura      | Sequencial        | Baseada em regras    |
| Complexidade   | Maior controle    | Mais abstrato        |


# Conclusão

O paradigma imperativo oferece maior controle sobre a execução do programa, sendo ideal para sistemas completos e aplicações do dia a dia, como o sistema de vendas desenvolvido.

Já o paradigma declarativo proporciona uma forma mais simples e direta de resolver problemas baseados em lógica, sendo muito utilizado em inteligência artificial e sistemas especialistas.

Ambos possuem suas vantagens, e o conhecimento dos dois paradigmas é fundamental para o desenvolvimento de soluções mais eficientes e adequadas a cada contexto.

