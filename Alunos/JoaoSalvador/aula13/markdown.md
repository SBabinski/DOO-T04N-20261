# Atividade 13

## Nome:

João Victor Salvador

---

## Conceito escolhido 1:

Native String Templating

## Timestamp do vídeo que menciona o conceito:

Aproximadamente 1:10 do vídeo.

## O que é?

Native String Templating é um recurso do Kotlin que permite colocar variáveis ou expressões diretamente dentro de uma String, usando `$` ou `${}`.

Ele serve para deixar o código mais simples e legível, evitando ficar juntando textos com `+`.

Na música, o trecho menciona “native string templating”, que é uma característica do Kotlin ligada à escrita de textos de forma mais prática. A documentação do Kotlin também explica que strings podem conter expressões avaliadas diretamente dentro delas. :contentReference[oaicite:0]{index=0} :contentReference[oaicite:1]{index=1}

## Como é normalmente utilizado?

É usado para montar mensagens, textos de retorno, logs, mensagens para o usuário ou qualquer situação em que seja necessário misturar texto com valores de variáveis.

## Exemplo de código:

````kotlin
fun main() {
    val nome = "Arthur"
    val idade = 22

    println("Olá, meu nome é $nome e tenho $idade anos.")
    println("Ano que vem terei ${idade + 1} anos.")
}.

## Conceito escolhido 2:

Smooth Operators Overloading

## Timestamp do vídeo que menciona o conceito:

Aproximadamente 1:15 do vídeo.

## O que é?

Smooth Operators Overloading, ou sobrecarga de operadores, é um recurso do Kotlin que permite personalizar o comportamento de operadores como `+`, `-`, `*`, `/`, entre outros.

Isso significa que podemos fazer objetos criados por nós utilizarem operadores de forma mais simples e natural.

## Pra que serve?

Serve para deixar o código mais limpo, fácil de entender e mais próximo da linguagem humana.

Por exemplo, ao invés de criar uma função chamada `somarProdutos()`, podemos simplesmente usar o operador `+`.

## Como é normalmente utilizado?

É normalmente utilizado em classes que representam valores, como produtos, dinheiro, datas, pontos ou qualquer objeto que faça sentido ser somado, subtraído ou comparado.

## Exemplo de código:

```kotlin
data class Produto(val preco: Double) {
    operator fun plus(outro: Produto): Produto {
        return Produto(this.preco + outro.preco)
    }
}

fun main() {
    val produto1 = Produto(50.0)
    val produto2 = Produto(30.0)

    val total = produto1 + produto2

    println("Total: R$ ${total.preco}")
}
````