# Atividade Extra - HelloWorld.java (Nanowar of Steel).

**Nome:** Fernando Otávio Folador.

---

## Conceito 1: Garbage Collector.

**Conceito escolhido:** Garbage Collector.

**Timestamp do vídeo que menciona o conceito:** Aproximadamente 02:10.

### O que é?

O Garbage Collector é um recurso da JVM (Java Virtual Machine) responsável por liberar automaticamente a memória ocupada por objetos que não estão mais sendo utilizados pelo programa.

### Pra que serve?

Ele serve para evitar o desperdício de memória e diminuir a chance de erros causados pelo gerenciamento manual de memória. Dessa forma, o desenvolvedor não precisa se preocupar em liberar objetos que não são mais necessários.

### Como é normalmente utilizado?

O Garbage Collector funciona automaticamente em segundo plano. Quando um objeto deixa de possuir referências apontando para ele, a JVM pode remover esse objeto da memória quando considerar necessário.

### Exemplo de código:

```java
public class ExemploGarbageCollector {

    public static void main(String[] args) {

        String mensagem = new String("Olá Mundo");

        mensagem = null;

        System.out.println("Objeto disponível para coleta de lixo.");
    }
}
```

---

## Conceito 2: Null Safety.

**Conceito escolhido:** Null Safety.

**Timestamp do vídeo que menciona o conceito:** Aproximadamente 00:40.

### O que é?

Null Safety é um recurso presente na linguagem Kotlin que ajuda a evitar erros causados pelo acesso a objetos nulos. O compilador verifica possíveis problemas antes mesmo da execução do programa.

### Pra que serve?

Serve para reduzir a ocorrência do erro conhecido como NullPointerException, que acontece quando tentamos acessar um objeto que não possui valor.

### Como é normalmente utilizado?

No Kotlin, uma variável só pode receber o valor null quando isso for informado explicitamente através do símbolo "?". Além disso, existem operadores que permitem trabalhar com valores nulos de forma segura.

### Exemplo de código:

```kotlin
fun main() {

    var nome: String? = null

    println(nome?.length)

    val tamanho = nome?.length ?: 0

    println(tamanho)
}
```
