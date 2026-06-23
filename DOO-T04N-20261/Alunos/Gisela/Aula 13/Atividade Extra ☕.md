# Aula 13 - Atividade Extra ☕

## Nome: Gisela

---

### Conceito escolhido 1: Data Classes

**Timestamp do vídeo:** 2:40  

**O que é?**  
São classes especiais que automaticamente geram métodos como `equals`, `hashCode` e `toString`.  

**Pra que serve?**  
Facilitam a criação de objetos que servem apenas para armazenar dados, sem precisar escrever muito código repetitivo, sua justificativa de estar aqui, é que no Java tradicional, esses recursos não são nativos e devem ser definidos manualmente.  

**Como é usado?**  
Usado para representar entidades simples, como registros ou modelos de dados.  

**Exemplo de código:**

```kotlin
data class Usuario(val nome: String, val idade: Int)

fun main() {
    val u1 = Usuario("Ana", 25)
    val u2 = Usuario("Ana", 25)
    println(u1 == u2) // true, pois equals é gerado automaticamente
    println(u1)       // Saída: Usuario(nome=Ana, idade=25)
}
```

### Conceito escolhido 2: Courotines

**Timestamp do vídeo: 3:20**

**O que é?**
São uma forma de lidar com programação assíncrona e concorrente sem complicação, a justificativa de estar descrito aqui é a superação das threads do Java.

**Pra serve serve?**
Permite executar tarefas em paralelo (como operações pesadas), sem travar a aplicação

**Como é usado?**
Usado em aplicações android e servidores para rodar operações assíncronas de forma eficiente.

**Exemplo de código:**

```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    launch {
        delay(1000L)
        println("Coroutines são poderosas!")
    }
    println("Início")
}
```