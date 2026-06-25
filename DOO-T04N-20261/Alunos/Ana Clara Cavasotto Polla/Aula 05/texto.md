**Nome:** Ana Clara Cavasotto Polla  
**Turma:** T04N - 3⁰ Semestre  
**Curso:** Engenharia de Software

---

PARADIGMAS DE PROGRAMAÇÃO: DECLARATIVO E IMPERATIVO</span>



## APRESENTAÇÃO
O que será apresentado nesse documento:
- Introdução aos paradigmas de programação;
- Detalhamento do Paradigma Declarativo;
- Detalhamento do Paradigma Imperativo;
- Comparação prática entre códigos Prolog e Java.

##

## O que são paradigmas de programação
Os paradigmas de programação são formas de organizar e estruturar o código, influenciando diretamente como os problemas são resolvidos dentro de um programa. Cada paradigma representa uma maneira diferente de pensar a solução. Entre os principais, destacam-se o paradigma declarativo e o imperativo, que se diferenciam principalmente pela forma como as instruções são dadas ao computador. Enquanto um foca no resultado desejado, o outro detalha todo o processo para alcançá-lo.

## Tipos de paradigmas

### Paradigma Declarativo
O paradigma declarativo caracteriza-se por uma abordagem de programação em que o desenvolvedor especifica o que deseja obter como resultado, sem necessariamente descrever detalhadamente como o sistema deve executar cada passo. A ênfase está na lógica do problema e na relação entre os dados.

Seus pilares são:
- **Fatos:** Representam informações conhecidas ou verdadeiras.
- **Regras:** Definem relações lógicas entre esses fatos.

### Paradigma Imperativo
Constitui uma das formas mais tradicionais e difundidas de programação. Nesse modelo, o programador precisa fornecer ao computador uma sequência explícita de instruções que definem, passo a passo, como o problema deve ser resolvido. O foco está em descrever o processo (o "como").

</div>


### Exemplo em Prolog (Paradigma Declarativo)
```prolog
% Fato
idade(ana, 20).

% Regra
maior_de_idade(X) :- idade(X, I), I >= 18.

% Para consultar:
% ?- maior_de_idade(ana).

```

## Explicação da Lógica

### Lógica no Prolog
No exemplo apresentado, o trecho `idade(ana, 20).` representa um fato. A instrução `maior_de_idade(X) :- idade(X, I), I >= 18.` corresponde a uma regra lógica. 

O programador não descreve o procedimento detalhado de verificação, mas apenas estabelece as condições lógicas que devem ser satisfeitas. O próprio mecanismo de inferência do Prolog analisa os fatos e aplica as regras automaticamente para chegar a uma conclusão.

### Lógica no Java
No paradigma imperativo, o programador precisa definir explicitamente:
- **Dados:** Quais informações serão utilizadas e como serão armazenadas (variáveis);
- **Condição:** Qual lógica de decisão será verificada (`if`);
- **Ações:** Quais instruções devem ser executadas em cada situação (`System.out.println`).

## Conclusão
Em suma, a diferença entre os paradigmas está no foco: o **Java (imperativo)** exige que o programador dite o passo a passo de **"como"** resolver o problema, garantindo controle total sobre o fluxo. Já o **Prolog (declarativo)** foca no **"o que"** deve ser resolvido, permitindo que o sistema encontre a solução automaticamente através de fatos e regras.

Portanto, a comparação entre essas duas linguagens demonstra que não existe um paradigma superior, mas sim ferramentas diferentes para objetivos distintos. Enquanto o Java se destaca pela previsibilidade e controle em aplicações estruturadas, o Prolog se destaca pela facilidade em modelar problemas complexos de lógica e inteligência artificial.

</div>