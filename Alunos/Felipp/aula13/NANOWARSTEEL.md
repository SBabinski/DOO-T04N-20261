## 1. Programação paralela com coroutines

**Conceito escolhido:** programação paralela (concorrência) com coroutines, ou corrotinas e o poder das suspending functions

**Timestamp do vídeo que menciona o conceito:** Minuto 3:17 até o minuto 3:25

**O que é?**
Coroutines são um recurso do Kotlin para escrever código assíncrono e concorrente. Funcionam como "threads leves" gerenciadas pela própria runtime da linguagem: uma função marcada com `suspend` consegue pausar a execução em um ponto e retomar depois, sem bloquear a thread do sistema operacional enquanto espera.

**Pra que serve?**
Servem para executar várias tarefas ao mesmo tempo (chamadas de rede, consultas a banco, leitura de arquivos) sem travar a aplicação e sem cair no famoso "callback hell". A vantagem é que o código continua parecendo sequencial e legível, mesmo rodando de forma assíncrona.

**Como é normalmente utilizado?**
Os blocos mais comuns são: `launch`, para disparar uma corrotina sem retorno; `async` com `await`, quando você precisa de um resultado; `runBlocking`, para fazer a ponte com código bloqueante (como a função `main`); e os dispatchers (`Dispatchers.IO`, `Dispatchers.Default`, `Dispatchers.Main`), que dizem em qual pool de threads o trabalho vai rodar.

**Exemplo de código:**
```kotlin
import kotlinx.coroutines.*

fun main() = runBlocking {
    // as duas chamadas começam quase ao mesmo tempo
    val usuario = async { buscarUsuario() }
    val pedidos = async { buscarPedidos() }

    // await() espera o resultado sem bloquear a thread
    println("${usuario.await()} fez ${pedidos.await()} pedidos")
}

suspend fun buscarUsuario(): String {
    delay(1000) // simula uma chamada de rede de 1 segundo
    return "joão"
}

suspend fun buscarPedidos(): Int {
    delay(1000)
    return 5
}
// total de espera: cerca de 1 segundo, não 2, porque rodam em paralelo
```

---
## 2. Interoperabilidade Java e Kotlin

**Conceito escolhido:** interoperabilidade entre Java e Kotlin

**Timestamp do vídeo que menciona o conceito:** aproximadamente 0:35, quando a letra descreve uma linguagem da JVM com interoperabilidade com Java e build com Gradle, Maven e Amper

**O que é?**
Kotlin é compilado para o bytecode da JVM, a mesma máquina virtual usada pelo Java. Por causa disso, código Kotlin e código Java convivem no mesmo projeto: dá para chamar classes Java a partir do Kotlin e o contrário também, sem nenhuma camada de tradução no meio.

**Pra que serve?**
Serve para reaproveitar todo o ecossistema Java já existente (bibliotecas, frameworks como Spring, sistemas legados) e para migrar projetos de forma gradual, arquivo por arquivo, sem precisar reescrever tudo de uma vez.

**Como é normalmente utilizado?**
No dia a dia, basta importar e usar classes Java diretamente no código Kotlin. No sentido inverso (código Kotlin sendo consumido por Java), existem anotações como `@JvmStatic`, `@JvmField` e `@JvmOverloads`, que ajustam como o Kotlin aparece para o lado Java. Também tem os "platform types", que são tipos vindos do Java sobre os quais o Kotlin não sabe se aceitam nulo ou não.

**Exemplo de código:**
```kotlin
import java.time.LocalDate // classe padrão da API do Java

fun main() {
    // chamando uma classe Java diretamente de dentro do Kotlin
    val hoje = LocalDate.now()
    val proximoAno = hoje.plusYears(1)

    println("hoje: $hoje, daqui a um ano: $proximoAno")
}
```

---
## 3. Variáveis mutáveis em Kotlin

**Conceito escolhido:** variáveis mutáveis (`var`) e a diferença para as somente leitura (`val`)

**Timestamp do vídeo que menciona o conceito:** 0:56 no primeiro pré-refrão, quando a letra cita variáveis mutáveis e read-only com `var` e `val`

**O que é?**
Em Kotlin, toda variável é declarada com uma de duas palavras-chave. `var` cria uma variável mutável, que pode receber um novo valor depois de criada. `val` cria uma referência somente leitura (read-only), que não pode ser reatribuída após a primeira atribuição.

**Pra que serve?**
`var` serve para guardar estado que muda com o tempo: um contador, um acumulador, um campo que o usuário edita. A boa prática recomendada é preferir `val` sempre que der e só usar `var` quando o valor realmente precisar mudar, porque isso deixa o código mais previsível e fácil de raciocinar.

**Como é normalmente utilizado?**
A declaração pode usar inferência de tipo (`var idade = 30`) ou tipo explícito (`var idade: Int = 30`). Dois cuidados importantes: mutável não é a mesma coisa que nulável (são conceitos diferentes), e `val` não congela o conteúdo interno de objetos mutáveis (uma lista mutável guardada em um `val` ainda pode receber novos itens, o que não pode é trocar a lista por outra).

**Exemplo de código:**
```kotlin
fun main() {
    var contador = 0        // mutável - pode mudar de valor
    val nome = "i.riedi"    // somente leitura

    contador = contador + 1 // var aceita reatribuição
    contador += 1

    // nome = "outro"       // erro de compilação: val não aceita reatribuição

    println("$nome processou $contador eventos")
}
```