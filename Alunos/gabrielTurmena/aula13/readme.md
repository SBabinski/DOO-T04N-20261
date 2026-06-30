# Atividade extra

**Nome:** Gabriel Turmena Carvalho

## Conceito escolhido 1: Coroutines

**Timestamp do vídeo:** 3:17
**Trecho relacionado:** *made intuitive with coroutines*

### O que é?

Coroutines são uma forma de escrever código concorrente de maneira mais simples e legível. Elas permitem executar tarefas que podem demorar, como acessar uma API, consultar um banco de dados ou esperar uma operação terminar, sem bloquear totalmente a execução do programa.

Apesar de parecerem com threads, coroutines são mais leves. Em vez de criar várias threads pesadas, o programa pode suspender e retomar coroutines conforme necessário.

### Pra que serve?

Elas servem para facilitar a programação concorrente, principalmente quando precisamos lidar com várias tarefas acontecendo ao mesmo tempo ou aguardando respostas externas.

Um uso comum é em aplicações Android, servidores e sistemas que fazem muitas operações de entrada e saída, como chamadas HTTP.

### Como é normalmente utilizado?

Em Kotlin, coroutines normalmente são usadas com funções como `launch`, `async` e `runBlocking`. Elas permitem iniciar tarefas concorrentes de forma organizada.

### Exemplo de código

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        delay(1000)
        println("Tarefa finalizada")
    }

    println("Programa continua executando")
}
```

Nesse exemplo, a coroutine espera 1 segundo usando `delay`, mas sem bloquear a thread principal da mesma forma que um `Thread.sleep` faria.

---

## Conceito escolhido 2: Suspending functions

**Timestamp do vídeo:** 3:17
**Trecho relacionado:** *unleash the power of suspending functions*

### O que é?

Uma suspending function, ou função suspensa, é uma função em Kotlin marcada com a palavra-chave `suspend`. Esse tipo de função pode pausar sua execução temporariamente e depois continuar de onde parou.

Isso é útil porque o programa não precisa ficar travado esperando uma tarefa demorada terminar. A função é “suspensa” enquanto espera e, quando o resultado fica disponível, ela é retomada.

### Pra que serve?

Suspending functions servem para escrever código assíncrono de forma mais parecida com código sequencial comum. Isso deixa o programa mais fácil de entender, evitando muitos callbacks ou estruturas complexas.

Elas são muito usadas para chamadas de rede, consultas a banco de dados, leitura de arquivos e outras operações que podem demorar.

### Como é normalmente utilizado?

Uma função `suspend` só pode ser chamada dentro de outra função `suspend` ou dentro de uma coroutine. Ela combina muito bem com coroutines porque permite suspender tarefas sem bloquear completamente a execução do programa.

### Exemplo de código

```kotlin
import kotlinx.coroutines.*

suspend fun buscarDados(): String {
    delay(2000)
    return "Dados recebidos"
}

fun main() = runBlocking {
    println("Buscando dados...")

    val resultado = buscarDados()

    println(resultado)
}
```

Nesse exemplo, a função `buscarDados` simula uma operação demorada. Como ela é `suspend`, pode esperar usando `delay` e depois retornar o resultado sem precisar bloquear a thread de forma tradicional.

---

## Relação com programação paralela

Esses dois conceitos ajudam a tornar a programação concorrente mais simples. É importante observar que coroutines não significam automaticamente paralelismo real, pois paralelismo envolve executar tarefas ao mesmo tempo em diferentes núcleos ou threads. Porém, coroutines facilitam muito a organização de tarefas simultâneas e podem ser usadas junto com diferentes dispatchers para aproveitar melhor os recursos do sistema.

#### Vídeo de explicação para maiores dúvidas

O vídeo a seguir explica conceitos de Coroutines e Concorrência em Kotlin e como ele lida com essas situações de maneira mais aprofundada: https://www.youtube.com/watch?v=e7tKQDJsTGs