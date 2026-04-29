# Análise Comparativa de Paradigmas de Programação: Imperativo e Declarativo

```1. Introdução```

A ciência da computação fundamenta-se em diferentes modelos conceituais para a resolução de problemas, conhecidos como paradigmas de programação. Esses paradigmas não representam apenas variações sintáticas entre linguagens, mas distintas formas de estruturar o raciocínio lógico e a execução de instruções pelo hardware.

Conforme Sebesta (2018), os paradigmas de programação representam diferentes formas de estruturar linguagens e organizar a execução de programas, influenciando diretamente a maneira como os problemas computacionais são modelados e resolvidos.

Enquanto alguns paradigmas priorizam o controle explícito do fluxo de execução e a mutação de estados, outros concentram-se na descrição de relações e regras lógicas. Nesse contexto, este trabalho analisa as diferenças entre os paradigmas imperativo e declarativo, utilizando a lógica de um sistema de vendas para exemplificar como cada abordagem trata o cálculo de preços e descontos.

```2. O Paradigma Imperativo: O Fluxo em Java```

O paradigma imperativo é caracterizado pela execução sequencial de instruções que alteram o estado do programa ao longo do tempo. De acordo com Setzer e Silva (2005), essa abordagem está diretamente relacionada à construção de algoritmos que descrevem, passo a passo, como uma tarefa deve ser realizada.

No código Java a seguir, observa-se a manipulação explícita de variáveis e o controle do fluxo de execução para o cálculo do preço total de uma venda:

```java
public float calcularPrecoTotal(Produto produto) {
    float resultado = produto.qtd * produto.prcUni;

    if (produto.qtd > 10) {
        float valorDoDesconto = resultado * 0.05f;
        resultado = resultado - valorDoDesconto;
    }

    return resultado;
}
```

Neste modelo, o desenvolvedor define claramente a sequência de operações: inicialmente calcula-se o valor bruto da compra, em seguida verifica-se uma condição e, por fim, aplica-se uma modificação no valor armazenado.

Segundo Setzer e Silva (2005, p. 25), “um algoritmo é uma sequência finita de passos bem definidos para a solução de um problema”, o que reforça a característica central do paradigma imperativo, baseado na execução ordenada de instruções.

```3. O Paradigma Declarativo: A Regra em Prolog```

Em contraste, o paradigma declarativo — especialmente na sua vertente lógica — enfatiza a descrição do problema em termos de relações e regras, em vez de instruções detalhadas de execução. Segundo Sebesta (2018), linguagens declarativas permitem que o programador especifique o que deve ser resolvido, deixando ao sistema a responsabilidade de determinar como alcançar o resultado.

A mesma lógica implementada em Java pode ser representada em Prolog da seguinte forma:

```prolog
% Regra 1: Preço com desconto (se Qtd > 10)
preco_total(Qtd, PrcUni, Total) :- 
    Qtd > 10,
    Bruto is Qtd * PrcUni,
    Total is Bruto * 0.95.

% Regra 2: Preço padrão (se Qtd <= 10)
preco_total(Qtd, PrcUni, Total) :- 
    Qtd =< 10,
    Total is Qtd * PrcUni.
```

Nesse modelo, não há controle explícito de fluxo nem mutação de variáveis. Em vez disso, definem-se relações lógicas independentes. Quando o sistema recebe uma consulta, ele utiliza mecanismos de inferência para selecionar e aplicar a regra apropriada, conforme os dados fornecidos.

```4. Comparação entre os Paradigmas```

A principal diferença entre os paradigmas imperativo e declarativo reside na forma como o problema é abordado. No paradigma imperativo, o programador é responsável por especificar detalhadamente cada etapa da execução, garantindo que a sequência de instruções produza o resultado esperado. Já no paradigma declarativo, o foco está na definição de regras e relações, permitindo que o sistema determine automaticamente o processo de resolução.

Enquanto o modelo imperativo oferece maior controle sobre o comportamento do programa e é amplamente utilizado em aplicações práticas, o modelo declarativo proporciona maior nível de abstração, sendo particularmente útil em sistemas baseados em lógica, inteligência artificial e banco de dados.

```5. Conclusão```

A análise comparativa evidencia que ambos os paradigmas possuem características complementares e são fundamentais para o desenvolvimento de soluções computacionais. O paradigma imperativo destaca-se pela clareza na definição do fluxo de execução e pelo controle direto sobre os estados do sistema. Por outro lado, o paradigma declarativo permite uma representação mais abstrata e próxima da lógica formal, facilitando a modelagem de problemas complexos.

Compreender essas diferenças é essencial para a formação de um profissional capaz de escolher a abordagem mais adequada para cada tipo de problema, explorando de forma eficiente os recursos oferecidos por diferentes linguagens e paradigmas.

```Referências```

SEBESTA, Robert W. *Conceitos de linguagens de programação*. 11. ed. Porto Alegre: Bookman, 2018.

SETZER, Valdemar W.; SILVA, Flavio Soares Corrêa da. *Lógica de programação: algoritmos, estruturas de dados e objetos*. São Paulo: Escrituras, 2005.