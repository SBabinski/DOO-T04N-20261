# Entendendo os paradigmas

## Imperativo: 
    No paradigma imperativo o código é orientado como se fosse um robo orientado a fazer um passo a passo aonde cada um dos passos tem uma delimitação e uma série de obrigações para que não faça mais nem menos do que o algoritmo pede (é literalmente o que ele é), em outras palavras, orientado por comandos e direções explícitas, o desenvolvedor escreve uma sequência de instruções de como o computador deve seguir, dizendo como realzar cada tarefa. Nesse caso, um ponto que me trouxe dúvida foi em relação a declaração de variáveis e métodos, se tudo é explícitamente explicado ao computador, a ordem do código deve influênciar, isso significa que a ordem deve ser estruturada de forma lógica, pois a execução segue uma ordem, algumas execuções podem ser afetadas em casos de mudança de ordem.

## Declarativo:
    No paradigma declarativo o código está mais focado no que o programa deve fazer, ou seja, no resultado final, não nos passos específicos de cada ato. Em vez de definir uma sequência de instruções, ele foca em descrever a lógica e as condições necessárias para que o sistema chegue no resultado desejado. Nesse paradigma, a ordem de declaração de variáveis e métodos tem menos impacto, já que o foco não é a sequência de execução, não que deixe de importar, mas que passa a ser mais flexível.

# Comparação de resolução de desafio de RH

## Problema

    Na empresa aonde trabalho como desenvolvedor, o meu próximo desafio vai ser desenvolver um ''gestor de rh'', devido a gestão do projeto vou desenvolver por módulos e ir implantando um por um de acordo com a ordem lógica dos processos que o RH já possui, nesse caso vou usar como desafio a parte de comissionamento

### Lógica por trás da solução

    Já possuimos um sistema enorme, é um ecossistema aonde recebe cadastro de vendas de geradores, vendas de consumidores, vendas de geradores e realiza toda a gestão desses 3 objetos, que são extremamente grandes e complexos (gestão de cooperativa de energia solar). Por onde pensei em começar no módulo de comissionamento, abrindo o registro do usuário e inputando uma regra de comissão, em seguida ao executar a ação de calcular (pode tanto ser iniciada em momento específico quando por rotina) um comissionamento (mês referência) ele vai obter a regra de comissionamento atual que está registrada na tabela do usuário, consultar a regra referente na tabela de cálculo de regras e usar os valores da regra para calcular a comissão. Exemplos:

        - id: 1; regra: "porcentagem da margem"; comissaoPercent: 0.05;
        - id: 2; regra: "porcentagem da venda"; comissaoPercent: 0.025;

    Após o cálculo usando a regra escolhida no registro do usuário, registra em uma tabela de histórico de comissionamento a comissão do mês referente, funciona sem problemas já que sempre sempre antes de calcular ele vai consultar a regra que está na tabela de registro do usuário

#### Resolução em Java

    ```java
    
    import java.util.HashMap;
    import java.util.Map;

    class ComissaoRH {
        
        private Map<Integer, String> regrasComissao;
        private Map<Integer, Double> comissaoRegistros;

        private Map<Integer, Double> historicoComissoes;
        
        public ComissaoRh() {
            regrasComissao = new HashMap<>();
            comissaoReistros = new HashMap<>();
            historicoComissoes = new HashMap<>();
        }

        // Registrar regra de comissão
        public void registrarRegra(int id, String regra, double comissaoPercent) {
            regrasComissao.put(id, regra);
            comissaoRegistro.put(regraId);

            double comissao = 0;

            // Calcula comissao de acordo com a regra
            if (regra.equals("porcentagem da margem")) {
                comissao = valorMargem * comissaoPercent;
            } else if (regra.equals("porcentagem da venda")) {
                comissao = valorVenda * comissaoPercent;
            } else {
                System.out.println("Essa regra ainda não foi definida dentro das regras do RH. Por favor consulte o responsável pelo setor.")
            }

            // Insere no histórico todo registro que passar por aqui
            historicoComissoes.put(usuarioId, comissao);

            return comissao;
        }

        public void exibirHistorico() {
            // Pega o id como parametro, eu usaria o id que estaria em uma tabela de cache (o id do usuario e as respectivas informacoes relevantes sao preenchidas ao logar no sistema) dessa forma sempre que logar no sistema a tabela de cache tera o id do usuario, ficará sempre atualizado e nao teria problema nos parametros
            for (Map.Entry<Integer, Double> entry : historicoComissoes.entrySet()) {
                System.out.println("Usuário ID: " + entry.getKey() + " - Comissão: R$ " + entry.getValue());
            }
        }

        public class ModuloComissionamento {
            public static void main(String[] args) {
                ComissaoRh sistema = new ComissaoRh();

                sistema.registrarRegra(1, "porcentagem da margem", 0.05);
                sistema.registrarRegra(2, "porcentagem da venda", 0.025);

                double comissao1 = sistema.calcularComissao(101, 1, 10000);

                sistema.exibirHistorico();
            }
        }
    }

    ```

#### Resolução em Prolog

    ```prolog

    % Fatos: Regras de comissionamento
    regra(1, 'porcentagem da margem', 0.05).
    regra(2, 'porcentagem da venda', 0.025).

    % Fatos: Histórico de comissões (id_usuario, comissao_calculada)
    historico_comissao(101, 500.0).
    historico_comissao(102, 375.0).

    % Regra para calcular a comissão de um usuário
    calcular_comissao(UsuarioId, RegraId, ValorVenda, ComissaoCalculada) :-
        regra(RegraId, _, Percentual),
        ComissaoCalculada is ValorVenda * Percentual,
        assertz(historico_comissao(UsuarioId, ComissaoCalculada)).

    % Regra para exibir o histórico de comissões
    exibir_historico :-
        historico_comissao(UsuarioId, Comissao),
        write('Usuário ID: '), write(UsuarioId), nl,
        write('Comissão: R$ '), write(Comissao), nl,
        fail.
    exibir_historico.

    % Consultas:
    % - ?- calcular_comissao(101, 1, 10000, Comissao).
    % - ?- exibir_historico.

    ```

#### Entendendo as resoluções

    Java: sendo de paradigma imperativo, descreve exatamento o passo a passo de como o computador deve se comportar durante a execução.

    Prolog: sendo de paradigma delcarativo, o programador descreve o objetivo que deseja alcançar, não o processo detalhado de como deve ser feito, note que nem o tipo da variável é parte da atribuição.