# Nome:

Felipe

# Conceito escolhido:

Garbage Collection

## Timestamp do vídeo que menciona o conceito:

Aproximadamente 2:15 ("Garbage Collection")

## O que é?

Garbage Collection (coleta de lixo) é um mecanismo do Java responsável por liberar automaticamente a memória ocupada por objetos que não estão mais sendo utilizados pelo programa.

## Para que serve?

Serve para evitar desperdício de memória e reduzir a necessidade de o programador liberar memória manualmente.

## Como é normalmente utilizado?

O programador cria objetos normalmente e a JVM identifica quando eles não possuem mais referências, removendo-os da memória quando necessário.

## Exemplo de código

```java
public class ExemploGC {
    public static void main(String[] args) {
        String texto = new String("Olá Mundo");

        texto = null; // objeto sem referência

        System.gc(); // solicita execução do Garbage Collector
    }
}
```

---

# Conceito escolhido:

JVM (Java Virtual Machine)

## Timestamp do vídeo que menciona o conceito:

Aproximadamente 1:55 ("The JVM will manage it for me")

## O que é?

A JVM (Java Virtual Machine) é uma máquina virtual responsável por executar programas Java. Ela interpreta o bytecode gerado pelo compilador e o executa no sistema operacional.

## Para que serve?

Permite que o mesmo programa Java funcione em diferentes sistemas operacionais sem precisar ser reescrito.

## Como é normalmente utilizado?

Quando um arquivo `.java` é compilado, ele gera um arquivo `.class` contendo bytecode. Esse bytecode é executado pela JVM.

## Exemplo de código

```java
public class ExemploJVM {
    public static void main(String[] args) {
        System.out.println("Executando na JVM!");
    }
}
```

A JVM executará esse programa da mesma forma em Windows, Linux ou macOS, desde que exista uma JVM instalada.
