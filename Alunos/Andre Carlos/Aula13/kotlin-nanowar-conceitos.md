# Kotlin – Nanowar of Steel · Timestamps + Conceitos

https://www.youtube.com/watch?v=BsfXZjKLT9A

Timestamps aproximados.

---

## 0:55 – Null safety (e o Elvis operator)

O Kotlin foi feito pra evitar o NullPointerException, erro que costuma derrubar programas em Java.Por padrão, uma variável não pode ser nula. Se você quiser que ela aceite null, precisa avisar colocando um `?` no tipo, tipo `String?` em vez de `String`. Isso obriga você a lidar com o null já na hora de escrever o código, em vez de descobrir o problema só quando o programa quebra.

Pra facilitar, existe o Elvis operator (`?:`), que serve pra definir um valor reserva caso algo seja null. Por exemplo, `apelido?.length ?: 0` retorna o tamanho do apelido, ou 0 se ele for null. Assim o programa segue funcionando em vez de estourar um erro.

---

## 3:40 – Coroutines (programação paralela)

Coroutines são a forma de fazer várias coisas ao mesmo tempo sem a complexidade das threads tradicionais. Uma coroutine é uma tarefa leve que pode pausar e continuar depois, sem travar a thread inteira. Isso é útil, por exemplo, pra baixar dados da internet enquanto a interface continua respondendo.

A base disso são as suspending functions, declaradas com a palavra `suspend`. Elas conseguem pausar a execução num ponto, liberar a thread pra outra coisa, e voltar dali quando estiverem prontas. Pra disparar essas tarefas, usam-se construções como `launch` e `runBlocking`. O resultado é um código que parece sequencial e fácil de ler, mas que roda de forma assíncrona por baixo dos panos.
