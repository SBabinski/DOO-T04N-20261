# Atividade Extra — HelloWorld.java (Nanowar of Steel)

**Nome:** Mario  
**Música:** [HelloWorld.java — Nanowar of Steel](https://www.youtube.com/watch?v=BsfXZjKLT9A)

---

## Conceito 1: Garbage Collector

**Conceito escolhido:** Garbage Collector (Coletor de Lixo)

**Timestamp do vídeo que menciona o conceito:** ~2:10 — `"Garbage collector!"`

### O que é?

O Garbage Collector (GC) é um mecanismo automático de gerenciamento de memória presente em linguagens como Java. Quando um programa cria objetos, ele reserva espaço na memória (heap) para armazená-los. O GC é o componente da JVM responsável por identificar quais objetos não são mais referenciados por nenhuma parte do código e liberar automaticamente a memória que eles ocupam.

### Para que serve?

Serve para evitar **memory leaks** (vazamentos de memória), que acontecem quando a memória alocada nunca é liberada, fazendo o programa consumir cada vez mais recursos até travar ou ser encerrado pelo sistema operacional. Em linguagens como C e C++, o programador precisa liberar a memória manualmente (`free()` / `delete`), o que é uma fonte frequente de bugs graves. No Java, o GC cuida disso automaticamente.

### Como é normalmente utilizado?

O programador não precisa chamar o GC diretamente — ele roda em segundo plano gerenciado pela JVM. Basta deixar de ter referências a um objeto para que ele se torne elegível para coleta:

```java
public class ExemploGC {
    public static void main(String[] args) {
        // Objeto criado e referenciado por "texto"
        String texto = new String("Olá, mundo!");

        // Ao atribuir null, o objeto perde sua única referência
        // e se torna elegível para o Garbage Collector
        texto = null;

        // O GC pode liberar a memória em algum momento após isso.
        // Não chamamos nada — ele age automaticamente.
        System.out.println("Memória gerenciada pela JVM!");
    }
}
```

> **Observação:** É possível *sugerir* uma coleta com `System.gc()`, mas a JVM não é obrigada a executá-la imediatamente. Na prática, confia-se no comportamento automático do GC.

---

## Conceito 2: Tipagem Estática e Forte (Static & Strong Typing)

**Conceito escolhido:** Tipagem Estática e Forte / Type Safety

**Timestamp do vídeo que menciona o conceito:** ~1:55 — `"With static and strong typing, will let my programs be type safe!"`

### O que é?

**Tipagem estática** significa que o tipo de cada variável é definido e verificado em **tempo de compilação**, ou seja, antes do programa ser executado. Se você tentar guardar um valor do tipo errado em uma variável, o compilador acusa o erro antes mesmo de rodar o código.

**Tipagem forte** significa que o Java não faz conversões implícitas entre tipos incompatíveis — você precisa ser explícito ao converter (`casting`). Juntas, essas características tornam o código **type-safe** (seguro em relação a tipos), reduzindo uma categoria inteira de bugs.

### Para que serve?

Serve para garantir que os dados sejam manipulados de forma correta e previsível. Erros de tipo são capturados cedo (na compilação), e não em produção, onde poderiam causar comportamentos inesperados ou falhas de segurança.

### Como é normalmente utilizado?

No Java, toda variável deve ter seu tipo declarado explicitamente:

```java
public class ExemploTipagem {
    public static void main(String[] args) {

        // Tipagem estática: tipo declarado em tempo de compilação
        int idade = 22;
        String nome = "Mario";
        double salario = 3500.50;

        // ERRO DE COMPILAÇÃO — tipagem forte impede isso:
        // int valor = "texto";  // incompatível: String não é int

        // Conversão explícita (casting) quando necessário
        double numero = 9.99;
        int truncado = (int) numero; // casting explícito → resultado: 9

        System.out.println(nome + " tem " + idade + " anos.");
        System.out.println("Valor truncado: " + truncado);
    }
}
```

Em linguagens com **tipagem dinâmica** (como Python ou JavaScript), o tipo é verificado apenas em tempo de execução, o que dá mais flexibilidade mas também mais margem para bugs sutis. O Java prioriza a segurança e a previsibilidade ao exigir tipos explícitos.

---

*Arquivo gerado para a atividade extra de Aula 13.*
