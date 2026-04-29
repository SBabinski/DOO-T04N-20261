# Paradigmas de Linguagem de Programação: Imperativo e Declarativo

## Introdução

A forma como um programador organiza e expressa o raciocínio computacional está diretamente relacionada ao conceito de **paradigma de programação**. Um paradigma não é apenas um estilo de escrita, mas uma maneira distinta de pensar sobre problemas e comunicar soluções ao computador. Entre os paradigmas existentes, dois se destacam como os mais fundamentais: o **imperativo** e o **declarativo**. Compreender as diferenças entre eles é essencial para qualquer profissional da área de computação, pois essa escolha influencia diretamente a legibilidade, a manutenibilidade e a eficiência do software desenvolvido.

---

## O Paradigma Imperativo

O paradigma imperativo é caracterizado por uma abordagem direta e sequencial: o programador descreve **como** o computador deve executar cada etapa para alcançar um resultado. Em outras palavras, o código funciona como uma lista de ordens que a máquina deve obedecer na ordem em que foram escritas. O foco está no **processo**, não no objetivo final.

Esse paradigma se subdivide em algumas categorias importantes:

- **Estruturado**: organiza o código em blocos e estruturas de controle (condicionais, laços de repetição), sendo a base da maioria das linguagens modernas. Seu principal objetivo é tornar o código mais legível e organizado.
- **Procedural**: baseia-se na criação de sub-rotinas e funções, permitindo que o programa seja dividido em procedimentos reutilizáveis. Linguagens como C, Pascal e Java utilizam fortemente esse estilo.
- **Orientado a Objetos**: agrupa dados e comportamentos em unidades chamadas objetos, promovendo conceitos como encapsulamento, herança e polimorfismo.

---

## O Paradigma Declarativo

No paradigma declarativo, a lógica se inverte: o programador descreve **o que** deseja obter, e não como obtê-lo. Cabe ao sistema ou à linguagem determinar o caminho para atingir o resultado. Isso eleva o nível de abstração e, em muitos casos, torna o código mais conciso e expressivo.

Suas principais subcategorias são:

- **Funcional**: o programa é estruturado como uma composição de funções matemáticas. A principal diferença em relação ao modelo procedural está na **ordem de execução**: enquanto no imperativo as instruções são executadas em sequência definida pelo programador, no funcional a avaliação pode ocorrer de forma mais flexível, evitando efeitos colaterais. Exemplos de linguagens: Haskell, Scala, Clojure e, parcialmente, JavaScript.
- **Lógico**: o programador declara **fatos** e **regras** que descrevem o domínio do problema. A partir dessas declarações, o sistema de inferência da linguagem determina automaticamente como resolver consultas. O Prolog é o principal representante desse modelo.

---

## Comparação Prática: Java e Prolog

Para ilustrar a diferença entre os dois paradigmas, considera-se o problema clássico de verificar se um número é par.

### Java — Paradigma Imperativo (Procedural/Orientado a Objetos)

```java
public class Paridade {
    public static boolean isPar(int numero) {
        if (numero % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int numero = 4;
        if (isPar(numero)) {
            System.out.println(numero + " é par.");
        } else {
            System.out.println(numero + " é ímpar.");
        }
    }
}
```

Nesse código Java, o programador define **passo a passo** o que o computador deve fazer: recebe um número, realiza a operação de módulo, avalia a condição e retorna um resultado. O fluxo de execução é explícito e sequencial. O método `isPar` é um procedimento que encapsula uma lógica bem definida, e o método `main` controla a ordem de chamada das instruções. O programador está no controle total do **como**.

---

### Prolog — Paradigma Declarativo (Lógico)

```prolog
par(X) :- 0 is X mod 2.
```

Em Prolog, o código acima declara uma **regra lógica**: `X` é par se o resto da divisão de `X` por 2 for igual a zero. Não há instruções sequenciais, laços ou condicionais explícitos. O programador simplesmente enuncia um fato sobre o mundo — a definição matemática de paridade — e o motor de inferência do Prolog se encarrega de verificar se essa regra se aplica a qualquer valor consultado.

Para usar essa regra, basta fazer uma consulta ao sistema:

```prolog
?- par(4).
true.

?- par(7).
false.
```

O Prolog avalia internamente se a regra é satisfeita, sem que o programador precise descrever como fazer essa verificação.

---

## Análise Comparativa

| Aspecto              | Java (Imperativo)                          | Prolog (Declarativo/Lógico)               |
|----------------------|--------------------------------------------|-------------------------------------------|
| Foco                 | Como realizar a tarefa                     | O que se deseja saber                     |
| Controle do fluxo    | Explícito pelo programador                 | Gerenciado pelo motor de inferência       |
| Estrutura do código  | Sequência de instruções e procedimentos    | Fatos e regras lógicas                    |
| Legibilidade         | Mais próxima da linguagem de máquina       | Mais próxima da linguagem natural/formal  |
| Casos de uso típicos | Sistemas gerais, aplicações corporativas   | IA, processamento de linguagem natural, lógica formal |

---

## Conclusão

Os paradigmas imperativo e declarativo representam filosofias distintas sobre como expressar soluções computacionais. O paradigma imperativo, presente em linguagens como Java, exige que o programador descreva detalhadamente cada etapa da solução, oferecendo controle preciso sobre o fluxo de execução. Já o paradigma declarativo, exemplificado pelo Prolog, eleva o nível de abstração ao permitir que o programador se concentre na lógica do problema, delegando ao sistema a responsabilidade de determinar como resolvê-lo.

Não existe um paradigma universalmente superior; a escolha ideal depende do domínio do problema, dos requisitos do sistema e das características da equipe de desenvolvimento. O conhecimento de ambas as abordagens é, portanto, um diferencial importante na formação de um profissional de computação completo.
