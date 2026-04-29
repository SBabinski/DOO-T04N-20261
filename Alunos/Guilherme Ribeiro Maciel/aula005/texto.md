# Aula 005 - Paradigmas de Programação

Os paradigmas de programação são basicamente formas de abordar e resolver problemas ao escrever código. Eles são como conjuntos de regras que guiam os desenvolvedores. Cada linguagem de programação é criada com base nessas regras. Cada uma tem seu próprio conjunto de paradigmas que os desenvolvedores seguem ao escrever o código.

Os paradigmas de programação ajudam a estabelecer padrões para escrever código. Quando um especialista muda para uma linguagem de programação que segue um paradigma com o qual está familiarizado, é mais fácil para ele superar as diferenças entre as linguagens.

Existem dois principais paradigmas: o imperativo e o declarativo. Cada um deles inclui subtipos. O imperativo abrange a programação estruturada e a orientada a objetos (OOP), enquanto o declarativo engloba a programação funcional e lógica.

## Paradigma Interativo

O paradigma imperativo é uma forma de programar que se concentra em dar instruções claras sobre como realizar uma tarefa. É como se fosse uma receita de cozinha, onde você segue passos específicos para preparar aquele prato de doce ou salgado que você gosta. 

Além disso, podemos organizar nossos comandos em funções, que são como passos reutilizáveis em uma receita. Precisamos ter atenção à ordem em que os comandos são dados, pois ela faz diferença no resultado final. Podemos dividir nossa tarefa em pequenas etapas para facilitar a compreensão e organização.

## Paradigma Declarativo

O paradigma declarativo é um paradigma de programação que foca em descrever o que deve ser alcançado, em vez de como alcançá-lo. Em vez de fornecer uma sequência de instruções detalhadas, o programador declara as propriedades e relações que o resultado final deve ter. 

## Exemplos

### Java

> <Java.Fatorial>

```
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int resultado = 1;
        for (int i = 1; i <= n; i++) {
            resultado *= i;
        }
        System.out.println(resultado);
    }
}
```

Como exemplo de um codigo usando o Paradigma Imperativo temos um codigo em java que, em contexto geral, calcula o fatorial de um numero declarado pelo usuario criando uma variavel para quardar o resultado iniciado em 1 e fazendo o calculo em si dentro de um laço de repetição For onde ele conta de 1 ate o numero declarado pelo usuario e multiplica o resultado com o contador.

### Prolog

> <Prolog.Fatorial>
```
fatorial(0, 1).
fatorial(N, R) :-
N > 0,
N1 is N - 1,
fatorial(N1, R1),
R is N * R1.

main :-
read(N),
fatorial(N, R),
write(R), nl.
```

Ja para exemplificação do Paradigma Declarativo podemos usar um programa escrito em Prolog, com o mesmo objetivo final do codigo em Java de calcular o fatorial de um numero declarado pelo o usuario, onde ele inicia a função que calcula o fatorial indicando que o numero declarado pelo usuario deve sempre ser maior que 0 e calcula o resultado do fatorial ate que a variavel N1, que a cada rotação recebe o numero declarado pelo usuario menos 1, chegue a 0.