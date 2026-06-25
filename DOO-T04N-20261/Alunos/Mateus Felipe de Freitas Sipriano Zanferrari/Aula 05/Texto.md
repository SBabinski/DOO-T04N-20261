# Paradigmas de Programação: Imperativo vs. Declarativo

No estudo da Ciência da Computação, os paradigmas definem a estratégia intelectual para a resolução de problemas via software. A distinção fundamental entre o modelo imperativo (Java) e o declarativo/lógico (Prolog) reside na responsabilidade sobre o fluxo de execução.

## Paradigma Imperativo

O paradigma imperativo, exemplificado pelo Java, foca na descrição detalhada dos passos que o computador deve seguir. O programador dita uma sequência de comandos que alteram o estado da aplicação (variáveis) e gerenciam explicitamente a interação com o mundo externo (I/O). É uma abordagem procedimental: primeiro faça A, depois armazene em B, então exiba C.

## Paradigma Declarativo

O paradigma declarativo, representado aqui pelo Prolog, foca na definição de relações e propriedades. Em vez de fornecer um algoritmo de "passo a passo", o programador declara fatos e regras lógicas. O "motor de inferência" da linguagem é quem decide como processar essas informações para chegar a uma conclusão ou resultado.

## Comparação de Código: Operação de Soma

Abaixo, analisamos como cada linguagem lida com a tarefa de somar dois valores.

### Exemplo em Java (Imperativo)
O Java utiliza um fluxo linear e sequencial para capturar dados, processar e devolver uma resposta.


```java 
import java.util.Scanner; 

public class SomaSimples { 
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); // Alocação de recurso para entrada

        System.out.print("Digite o primeiro número: "); 
        double num1 = scanner.nextDouble(); // Comando de leitura (espera ativa)

        System.out.print("Digite o segundo número: "); 
        double num2 = scanner.nextDouble(); // Mudança de estado (atribuição)

        double soma = num1 + num2; // Instrução aritmética direta

        System.out.println("A soma é: " + soma); // Comando de saída

        scanner.close(); // Liberação manual de recurso
    } 
}
```
O código em Java segue o modelo de Arquitetura de Von Neumann, onde o programa é uma sequência de instruções armazenadas que manipulam dados na memória.
 
- **Gerenciamento de Estado:** Nas linhas 6, 8 e 9, o programa reserva espaços na memória (variáveis num1, num2 e soma). O valor desses espaços muda conforme as instruções são executadas.

- **Controle de Fluxo:** O Scanner (linha 4) interrompe a execução do processador e coloca o programa em estado de espera até que o usuário forneça uma entrada. O programa não pode avançar para a linha 9 sem que as linhas 6 e 8 tenham sido satisfeitas.

- **Procedimento de Saída:** A linha 10 não apenas "sabe" o resultado, ela recebe uma ordem explícita para concatenar uma String com o valor contido na variável soma e enviá-la para o fluxo de saída padrão (System.out).

Em resumo o Java atinge o objetivo através de uma receita procedimental onde o programador é responsável por gerenciar a entrada, o armazenamento, o cálculo e a exibição.

---
### Exemplo em Prolog

```prolog
% Definição da regra de soma
somar(A, B, Resultado) :- 
    Resultado is A + B.

% Exemplo de consulta no terminal (Query):
% ?- somar(10, 5, S).
% S = 15.
```
O código em Prolog define uma verdade matemática e aguarda uma consulta.
- **Relação Lógica:** O código não é uma ordem de "faça a soma", mas uma definição: "A relação somar entre A, B e Resultado é verdadeira se o Resultado for a soma de A e B".

- **Unificação:** Quando consultamos somar(10, 5, S), o Prolog tenta "unificar" os valores. Ele não armazena 10 em A e depois soma; ele busca um valor para a variável lógica S que torne a sentença verdadeira.

- **Abstração de I/O:** O Prolog não precisa de comandos de leitura (Scanner) ou escrita para processar a lógica central; ele responde a perguntas baseadas em sua base de conhecimento.

Em resumo o Prolog atinge o objetivo através da satisfação de metas. Você descreve a lógica do problema e a linguagem se encarrega de encontrar a resposta que satisfaça as condições dadas.

---
### Análise Comparativa

- **Fluxo de Execução:**
  - **Java:** Segue uma trilha linear e temporal de comandos.
  - **Prolog:** Segue uma busca por satisfação de metas lógicas, sem ordem fixa obrigatória.
- **Manipulação de Variáveis:**
  - **Java:** Variáveis são espaços de memória que mudam de valor (estado mutável).
  - **Prolog:** Variáveis são incógnitas lógicas que, uma vez instanciadas em uma consulta, mantêm seu valor para aquela prova (unificação).
- **Responsabilidade do Programador:**
  - **Java:** O desenvolvedor deve prever cada passo, incluindo a captura de dados e a exibição do resultado.
  - **Prolog:** O desenvolvedor define as regras do domínio e a linguagem decide como percorrê-las.
- **Objetivo Final:**
  - **Java:** Focado na eficiência do procedimento (algoritmo).
  - **Prolog:** Focado na expressividade da lógica (regras de negócio).

## Conclusão
A escolha entre um e outro depende da natureza do problema. O Java é superior para sistemas de gestão, aplicações móveis e onde o desempenho de execução sequencial é vital. O Prolog é imbatível em sistemas especialistas, processamento de linguagem natural e problemas de satisfação de restrições (como quebra-cabeças lógicos ou cronogramas complexos), onde descrever o "passo a passo" seria humanamente exaustivo.