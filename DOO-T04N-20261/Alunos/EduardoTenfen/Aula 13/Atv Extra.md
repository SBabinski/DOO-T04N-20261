Nome: Eduardo Irineu Xavier Tenfen

---

### Conceito escolhido: Null Safety (Segurança contra Nulos)
**Timestamp do vídeo que menciona o conceito:** [00:39] 

**O que é? Pra que serve? Como é normalmente utilizado?**
* **O que é:** O Kotlin possui um sistema de tipos rigoroso que distingue referências que podem conter nulo (nullable) daquelas que não podem (non-null). É a famosa proteção embutida contra o "Erro de um bilhão de dólares" (o `NullPointerException`).
* **Pra que serve:** Serve para evitar que o seu programa trave (crash) inesperadamente ao tentar acessar um objeto que não existe na memória. O compilador do Kotlin te obriga a tratar a possibilidade de um valor ser nulo *antes* mesmo do código compilar.
* **Como é normalmente utilizado:** Utilizando o operador de interrogação `?` logo após o tipo da variável para indicar que ela pode ser nula, e utilizando a chamada segura `?.` ou o operador Elvis `?:` para lidar com esses valores de forma elegante.

**Exemplo de código:**
fun main() {
    // Variável que NÃO aceita nulo (daria erro de compilação se tentasse atribuir null)
    var nome: String = "Samuel" 
    
    // Variável que ACEITA nulo (usando a interrogação)
    var apelido: String? = null

    // Chamada segura: só tenta ler o tamanho se o apelido não for nulo
    println(apelido?.length) // Saída: null (o programa não "quebra")
    
    // Operador Elvis (?:): se o que estiver à esquerda for nulo, usa o valor da direita
    val tamanho = apelido?.length ?: 0
    println("Tamanho do apelido: $tamanho")
}

---

### Conceito escolhido: Coroutines (Corrotinas)
**Timestamp do vídeo que menciona o conceito:** [03:08]

**O que é? Pra que serve? Como é normalmente utilizado?**
* **O que é:** Coroutines são um padrão de design de concorrência oficial do Kotlin utilizado para simplificar o código que é executado de forma assíncrona. Pense nelas como "threads super leves" que podem ser suspensas e retomadas de forma altamente eficiente.
* **Pra que serve:** Servem para executar tarefas demoradas (como fazer o download de uma imagem, buscar dados numa API ou acessar um banco de dados pesado) em segundo plano. Assim, a interface do usuário (a tela do aplicativo) não congela enquanto espera o resultado chegar.
* **Como é normalmente utilizado:** Utilizando a palavra-chave `suspend` (para marcar funções que podem ser pausadas sem bloquear a thread) e utilizando construtores como `launch` e `async` dentro de escopos bem definidos.

**Exemplo de código:**
import kotlinx.coroutines.*

// A palavra "suspend" avisa que essa função pode demorar e deve rodar numa coroutine
suspend fun buscarDadosDaAPI(): String {
    delay(2000L) // Simula uma espera de 2 segundos (ex: servidor na internet)
    return "Dados carregados com sucesso do Nanowar of Steel!"
}

fun main() = runBlocking {
    println("Iniciando busca...")
    
    // Lança a coroutine em segundo plano
    launch {
        val resultado = buscarDadosDaAPI()
        println(resultado)
    }
    
    println("Essa linha roda antes da busca terminar, pois não bloqueamos o sistema!")
}