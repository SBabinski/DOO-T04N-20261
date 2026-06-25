#   Análise Comparativa de Paradigmas de   Programação: Imperativo vs. Declarativo

A evolução das **linguagens de programação** trouxe consigo diferentes formas de pensar e estruturar a solução de problemas. Essas abordagens fundamentais são conhecidas como ***paradigmas de programação***. Dentre os modelos mais discutidos na ciência da computação, destacam-se os paradigmas _imperativo e declarativo_, que representam filosofias essencialmente opostas no tocante ao controle de fluxo e à manipulação de estado.

###  O Paradigma Imperativo
O ***paradigma imperativo*** baseia-se na abstração da arquitetura de _Von Neumann_. Nele, o desenvolvedor deve focar no _"como"_ o problema será resolvido. O código é escrito como uma sequência explícita de comandos e instruções que alteram o estado do programa passo a passo.

Linguagens fortemente influenciadas por este paradigma, como Java, C e Python, exigem a definição rigorosa do controle de fluxo por meio de estruturas de repetição _(for, while)_ e seleção _(if, switch case)_, além da alocação e manipulação direta do valor das variáveis na memória.

### O Paradigma Declarativo
Em contrapartida, o ***paradigma declarativo*** eleva o nível de abstração, focando no _"o quê"_ deve ser resolvido, delegando a responsabilidade do _"como"_ ao compilador ou interpretador subjacente.

Neste modelo, o programador descreve as propriedades da solução desejada, através de fatos, regras lógicas ou funções matemáticas  sem ditar explicitamente o fluxo de controle computacional. O ***Prolog (Programming in Logic)*** é um dos maiores expoentes deste paradigma, operando sobre uma base de conhecimento e um motor de inferência que resolve problemas lógicos por meio de algoritmos internos de **unificação e backtracking.**

###    Estudo de Caso: O Problema da Ancestralidade
Para ilustrar as diferenças entre essas abordagens, analisaremos como ambas as linguagens resolvem um problema clássico: determinar uma rota de GPS do _ponto A_ até o _ponto B_.

####    Abordagem Declarativa (Prolog)
No ***Prolog***, nós não programamos loops ou condicionais. Nós apenas declaramos os "fatos" que sabemos que são verdadeiros (as estradas que existem) e a regra lógica do que significa viajar de um _ponto a outro_.

Prolog
> % Fatos: O GPS sabe que existem essas conexões diretas.

* rodovia(tres_barras, cascavel).
* rodovia(cascavel, toledo).

> % Regra: Como saber se é possível chegar de A até B?

> % Condição 1: É possível se existe uma rodovia direta de A para B.

* chegar(Origem, Destino) :- rodovia(Origem, Destino).

> % Condição 2: É possível se existe uma rodovia de A até uma Parada,
> e depois é possível chegar dessa Parada até B.

* chegar(Origem, Destino) :- rodovia(Origem, Parada), chegar(Parada, Destino).

> Por que isso é declarativo?

 Nós não dissemos ao Prolog como procurar. Se fizermos a pergunta ***?- chegar(tres_barras, toledo).***, o Prolog aciona seu motor de inferência interno. Ele olha para os fatos, percebe sozinho que Três Barras se conecta a Cascavel e Cascavel a Toledo, e devolve true. O trabalho pesado do algoritmo de busca gráfica foi abstraído.

#### Abordagem Imperativa (Java)
Em ***Java***, a máquina precisa ser guiada a cada momenta da execução. Para armazenar os dados, nós criamos vetores _(arrays)_ na memória. O programador precisa escrever os laços de repetição que vão iterar _índice por índice_ até achar o destino.

> Java

    public class NavegacaoGPS {
    // Vetores primitivos armazenando as rotas
    private String[] origens = new String[10];
    private String[] destinos = new String[10];
    private int totalRotas = 0;

    public void adicionarEstrada(String origem, String destino) {
        origens[totalRotas] = origem;
        destinos[totalRotas] = destino;
        totalRotas++;
    }

    // Método imperativo: Ensinando a máquina a procurar a rota passo a passo
    public boolean verificarRota(String pontoDePartida, String destinoFinal) {
        String cidadeAtual = pontoDePartida;
        boolean temCaminho = true;

        // O fluxo de controle primário
        while (temCaminho) {
            temCaminho = false; // Assume que é o fim da linha, a não ser que ache uma rota
            
            // Busca manual: itera sobre todo o vetor procurando de onde sair
            for (int i = 0; i < totalRotas; i++) {
                if (origens[i].equals(cidadeAtual)) {
                    String proximaCidade = destinos[i];
                    
                    // Condição de parada vitoriosa
                    if (proximaCidade.equals(destinoFinal)) {
                        return true; 
                    }
                    
                    // Mudança de estado: O "carro" avança fisicamente para a próxima cidade
                    cidadeAtual = proximaCidade;
                    temCaminho = true;
                    break; // Reinicia a busca a partir da nova cidade
                }
            }
        }
        
        return false; // Busca finalizada sem sucesso.
    }
}

> Por que isso é imperativo?

Observe o método ***verificarRota***. Nele, o programador está ditando exatamente o fluxo da CPU. Ele precisa instanciar variáveis de estado _(cidadeAtual, temCaminho)_, criar um laço externo _(while)_, um laço interno de varredura _(for)_, controlar os índices do vetor manualmente _(i)_ e usar comandos de interrupção explícitos _(break, return)_. O algoritmo é uma receita mecânica rigorosa elaborada pelo desenvolvedor.

#### Síntese
Enquanto o código em Java _(imperativo)_ fornece um roteiro algorítmico detalhado focando na manipulação de memória e controle da CPU, o código em Prolog _(declarativo)_ atua na lógica do domínio.

A escolha entre os paradigmas não se baseia em qual é superior, mas no tipo do problema: sistemas que necessitam de avaliação de regras complexas, inferência ou IA brilham no mundo declarativo. Por outro lado, softwares que exigem alta performance, controle de hardware ou que possuam altos fluxos de dados e sequenciais, encontram sua base fundamental na engenharia do paradigma imperativo.