# Atividade Extra

**Nome:** Luiz Felipe Salbego Esperança

---

## Conceito 1: Parallel Programming

### Timestamp: 03:16

### O que é?
Programação paralela é uma técnica que permite executar várias tarefas ao mesmo tempo, aproveitando melhor os recursos do computador.

### Pra que serve?
Serve para aumentar o desempenho de aplicações, principalmente em tarefas demoradas, como processamento de dados, cálculos complexos e comunicação com servidores.

### Como é normalmente utilizada?
Em Kotlin, é comum utilizar coroutines, que facilitam a execução de tarefas assíncronas sem bloquear a aplicação.

### Exemplo de código:

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        println("Tarefa executando em paralelo")
    }

    println("Programa principal")
}
```

---

## Conceito 2: Syntax Shortcuts

### Timestamp: 03:26

### O que é?
Syntax Shortcuts são recursos da linguagem que permitem escrever menos código para realizar tarefas comuns.

### Pra que serve?
Tornam o código mais simples, legível e rápido de desenvolver.

### Como é normalmente utilizada?
Kotlin possui diversos atalhos de sintaxe, como inferência de tipos, funções de extensão e expressões simplificadas.

### Exemplo de código:

```kotlin
fun dobro(numero: Int) = numero * 2

fun main() {
    println(dobro(5))
}
```

Nesse exemplo, não foi necessário escrever o tipo de retorno da função explicitamente, pois o Kotlin consegue inferi-lo automaticamente.
