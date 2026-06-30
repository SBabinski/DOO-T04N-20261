# Atividade Extra — Conceitos de Programação na Música

Nome: Kauã Andrey Tesk

---

## Conceito 1: Sealed Class

**Conceito escolhido:** Sealed class (classe selada)

**Timestamp do vídeo que menciona o conceito:** [01:51] — trecho do Verso 2, na linha que fala sobre "controlled inheritance" usando esse recurso

**O que é?**
Uma `sealed class` é um recurso do Kotlin que permite **restringir** quais classes podem herdar de uma classe específica. Diferente do `abstract` que vimos em Java — onde qualquer classe pode estender a classe abstrata, em qualquer lugar do projeto — uma sealed class só permite que um conjunto **fechado e conhecido** de subclasses seja criado, geralmente todas declaradas dentro do mesmo arquivo.

**Pra que serve?**
Ela serve para modelar situações em que sabemos, de antemão, **todas as possibilidades** que algo pode assumir. Por exemplo: o resultado de uma operação pode ser só "Sucesso" ou "Erro" — nada mais. Com uma sealed class, o compilador sabe exatamente quais subtipos existem e consegue verificar se tratamos todos os casos possíveis, evitando bugs de "esqueci de tratar esse caso".

**Como é normalmente utilizado?**
É muito usado para representar resultados de operações (sucesso/erro), estados de uma tela (carregando/carregado/erro) ou eventos de um sistema. Combinada com a expressão `when` (equivalente ao `switch` do Java), o compilador obriga o programador a tratar **todos** os subtipos da sealed class, sem precisar de um `else` "pra garantir".

**Exemplo de código (Kotlin):**
```kotlin
// Define uma sealed class com apenas dois subtipos possíveis
sealed class Resultado

class Sucesso(val dado: String) : Resultado()
class Erro(val mensagem: String) : Resultado()

fun tratarResultado(resultado: Resultado) {
    // O "when" sabe que só existem esses dois subtipos -- não precisa de "else"
    when (resultado) {
        is Sucesso -> println("Deu certo: ${resultado.dado}")
        is Erro -> println("Falhou: ${resultado.mensagem}")
    }
}
```

Em Java, faríamos algo parecido com uma classe `abstract` e duas filhas, mas o compilador Java **não garante** que tratamos todos os casos em um `switch` ou `if/else` — é fácil esquecer de tratar um subtipo novo se ele for adicionado depois.

---

## Conceito 2: Null Safety

**Conceito escolhido:** Null Safety (segurança contra valores nulos)

**Timestamp do vídeo que menciona o conceito:** [00:50] — trecho do Pré-Coro do Verso 1, na linha sobre "no more null pointer exceptions" e tipos anuláveis

**O que é?**
Null Safety é um conjunto de regras do **sistema de tipos** do Kotlin que obriga o programador a declarar, desde a criação da variável, se ela **pode ou não** ser nula. Isso é feito com o símbolo `?` depois do tipo. Se uma variável não tem o `?`, o compilador garante (em tempo de compilação) que ela nunca vai receber `null`.

**Pra que serve?**
O objetivo é eliminar — ou ao menos reduzir drasticamente — o **`NullPointerException`** (NPE), um dos erros mais comuns e frustrantes em Java. Em Java, qualquer variável de objeto pode ser `null` sem aviso prévio, e só descobrimos o problema quando o programa quebra em tempo de execução. No Kotlin, esse erro é pego **antes mesmo de compilar**.

**Como é normalmente utilizado?**
- Tipo comum (`String`): nunca pode ser `null`.
- Tipo anulável (`String?`): pode ser `null`, mas o compilador obriga a tratar esse caso antes de usar a variável.
- Operadores como `?.` (safe call) e `?:` (elvis operator) ajudam a lidar com valores nulos de forma segura e em uma linha só.

**Exemplo de código (Kotlin):**
```kotlin
var nome: String = "Maria"      // Nunca pode ser null
var sobrenome: String? = null   // Pode ser null -- precisa do "?"

fun imprimirNome(sobrenome: String?) {
    // "?." só executa length() se sobrenome não for null;
    // caso contrário, retorna null sem lançar erro
    val tamanho = sobrenome?.length

    // "?:" (elvis operator) define um valor padrão caso seja null
    val tamanhoFinal = tamanho ?: 0

    println("Tamanho do sobrenome: $tamanhoFinal")
}
```

Em Java, o mesmo código exigiria uma verificação manual (`if (sobrenome != null)`) antes de cada uso da variável, e esquecer essa verificação é exatamente o que causa o famoso `NullPointerException` em tempo de execução.

---

## Conclusão

Tanto a **sealed class** quanto o **null safety** mostram uma filosofia central do Kotlin: tentar resolver, em tempo de **compilação**, problemas que em Java só aparecem em tempo de **execução**. Isso torna o código mais seguro e reduz a quantidade de bugs que chegam até a produção.
