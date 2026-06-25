# Atividade Extra - Aula 13

**Nome:** João Vitor André Alves

**Música:** Nanowar of Steel
**Vídeo:** https://www.youtube.com/watch?v=BsfXZjKLT9A

---

## Conceito 1: Elvis Operators

**Timestamp:** 0:53

**O que é?**
É um operador que serve de atalho pra dar um valor padrao quando algo é nulo,em vez de escrever 'a != null ? a : b' (esse segundo trecho é a condicional, antes dos dois pontos é caso for true, depois dos dois pontos é o else, ou, caso for false) a gente consegue escrever a ?: b

**Pra que serve?**
Para evitar valores nulos e escrever menos código

**Como é normalmente utilizado?**
Na linguagem Kotlin e PHP, pra preencher um valor quando tem chance do dado vir nulo

**Exemplo de código:**
```kotlin
    val nome = nomeDigitado ?: "Visitante"
```

---

## Conceito 2: Native String Templating

**Timestamp:** 1:42

**O que é?**
É um recurso que deixa voce colocar variaveis dentro de texto, em vez de juntar pedacos com +, voce pode usar $nome

**Pra que serve?**
Montar um texto mais legível e menos sujeito a erro, concatenar textos grandes deixa o codigo minimamente confuso.

**Como é normalmente utilizado?**
"olá, meu nome é $nome"

**Exemplo de código:**
```kotlin
    val nome = "Joao Alves";
    println("olá, meu nome é $nome");
```