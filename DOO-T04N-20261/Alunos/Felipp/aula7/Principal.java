package fag;

import fag.objetos.Cliente;
import fag.objetos.Endereco;
import fag.objetos.Funcionario;
import fag.objetos.Gerente;
import fag.objetos.Item;
import fag.objetos.Loja;
import fag.objetos.Pedido;
import fag.objetos.ProcessaPedido;
import fag.objetos.VendaPlanta;
import fag.objetos.Vendedor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.UUID;

public class Principal {

    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    static ArrayList<VendaPlanta> vendas = new ArrayList<>();
    static ArrayList<Vendedor> vendedores = new ArrayList<>();
    static ArrayList<Gerente> gerentes = new ArrayList<>();
    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<Item> itens = new ArrayList<>();
    static ArrayList<ProcessaPedido> processaPedidos = new ArrayList<>();
    static ArrayList<Pedido> pedidos = new ArrayList<>();
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static final Loja loja = new Loja("Venda da Dona Gabriela - Matriz", "Gabriela Comercio LTDA", "12.345.678/0001-99", new Endereco("Paraná", "Cascavel", "Centro", "Quinta das Flores", 7689, "Apartamento"));

    public static void main(String[] args) {
        populaDados();
        menu();
    }

    private static void menu() {
        boolean sair = false;

        while (sair == false) {
            System.out.println("Bem vindo a calculadora capaz de realizar as seguintes operações");

            System.out.println("[1] - Visualizar as vendas");
            System.out.println("[2] - Calcular Venda");
            System.out.println("[3] - Visualizar informações da loja");
            System.out.println("[4] - Cadastrar Vendedor");
            System.out.println("[5] - Cadastrar Gerente");
            System.out.println("[6] - Cadastrar Cliente");
            System.out.println("[7] - Cadastrar Item");
            System.out.println("[8] - Mostrar Itens");
            System.out.println("[9] - Criar Pedido");
            System.out.println("[10] - Mostrar Pedidos");
            System.out.println("[11] - Sair");
            final int opcao = scanner.nextInt();

            if (opcao == 11) {
                sair = true;
            }

            validarOpcaoEscolhida(opcao);
        }
    }

    private static void validarOpcaoEscolhida(int opcao) {
        switch (opcao) {
            case 1:
                mostraVendas();
                break;
            case 2:
                cadastrarVenda();
                break;
            case 3:
                mostrarInformacoesLoja();
                break;
            case 4:
                cadastrarFuncionario("vendedor");
                break;
            case 5:
                cadastrarFuncionario("gerente");
                break;
            case 6:
                cadastrarCliente();
                break;
            case 7:
                cadastrarItem();
                break;
            case 8:
                mostrarItens();
                break;
            case 9:
                criarPedido();
                break;
            case 10:
                mostrarPedidos();
                break;
            case 11:
                System.out.println("Volte sempre!");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private static void cadastrarVenda() {
        int percentualDesconto = 5;
        boolean temDesconto = false;
        float desconto = 0;
        float troco = 0;

        System.out.println("Informe a quantidade de planta: ");
        final int quantidadePlanta = scanner.nextInt();

        System.out.println("Informe o valor unitário da planta: ");
        final float valorPlantaUnitario = scanner.nextFloat();

        float resultadoCalculoPrecoTotal = calculoPrecoTotal(quantidadePlanta, valorPlantaUnitario);

        if (quantidadePlanta > 10) {
            desconto = resultadoCalculoPrecoTotal / percentualDesconto;
            temDesconto = true;
        }

        resultadoCalculoPrecoTotal -= desconto;

        System.out.printf("O valor total é de: %.2f\n\n", resultadoCalculoPrecoTotal);

        System.out.println("Precisa de troco? (1 -> Sim) (2 -> Não)");
        final int opcao = scanner.nextInt();

        if (opcao == 1) {
            System.out.println("Informe o valor recebido pelo cliente: ");
            final float valorRecebidoCliente = scanner.nextFloat();

            System.out.printf("O valor da compra é: ", resultadoCalculoPrecoTotal);

            final float resultadoTroco = calculoTroco(valorRecebidoCliente, resultadoCalculoPrecoTotal);

            if (resultadoTroco > 0) {
                System.out.printf("O valor do troco é de: %.2f\n\n", resultadoTroco);
                troco = resultadoTroco;
            } else if (resultadoTroco == 0) {
                System.out.println("Não é necessário dar troco de volta!");
            } else {
                System.out.println("Valor Insuficiente da parte do cliente.");
                System.out.printf("Valor faltante: %.2f\n\n", Math.abs(resultadoTroco));
            }
        }

        System.out.println(
                "Você quer salvar a venda com a data de hoje ou selecionar uma data específica para essa venda? (1 - Pega automaticamente a data de hoje, 2 - Seleciona manualmente uma data)");
        final int opcaoData = scanner.nextInt();
        scanner.nextLine();

        LocalDate dataRegistro = LocalDate.now();
        if (opcaoData == 2) {
            String dataEntrada = null;

            while (dataEntrada == null) {
                try {
                    System.out.println("Informe a data (dd/MM/yyyy)");
                    dataEntrada = scanner.nextLine();

                    dataRegistro = LocalDate.parse(dataEntrada, formatter);
                } catch (Exception error) {
                    System.out.println(error);
                }
            }
        }

        vendas.add(new VendaPlanta(quantidadePlanta, resultadoCalculoPrecoTotal, desconto, temDesconto,
                troco, dataRegistro));
    }

    private static void mostraVendas() {
        if (vendas.size() > 0) {
            System.out.println(
                    "Você quer mostrar todas as vendas ou filtrar por dia, mês e ano? (1 - Mostrar todas as vendas, 2 - Filtrar por dia, mês e ano)");
            final int opcao = scanner.nextInt();

            if (opcao == 1) {
                for (VendaPlanta venda : vendas) {
                    venda.mostrarRegistroVendas();
                    System.out.println();
                }
            }

            scanner.nextLine();
            if (opcao == 2) {
                LocalDate dataInicio = null;
                LocalDate dataFinal = null;

                while (dataInicio == null || dataFinal == null) {
                    try {
                        System.out.println("Informe uma data início no formato (dd/MM/yyyy)");
                        final String dataInicioEntrada = scanner.nextLine();

                        System.out.println("Informe uma data final no formato (dd/MM/yyyy)");
                        final String dataFinalEntrada = scanner.nextLine();

                        dataInicio = LocalDate.parse(dataInicioEntrada, formatter);
                        dataFinal = LocalDate.parse(dataFinalEntrada, formatter);
                        boolean nenhumaVenda = false;

                        for (VendaPlanta venda : vendas) {
                            if (!venda.getDataRegistro().isBefore(dataInicio)
                                    && !venda.getDataRegistro().isAfter(dataFinal)) {
                                venda.mostrarRegistroVendas();
                                System.out.println();

                                nenhumaVenda = true;
                            }
                        }

                        if (!nenhumaVenda) {
                            System.out.println("Nenhuma venda entre as datas informadas.");
                        }
                    } catch (Exception error) {
                        System.out.println("Data inválida, tente novamente.");
                    }
                }
            }
        } else {
            System.out.println("Nenhuma venda feita ainda!");
        }
    }

    private static float calculoPrecoTotal(int quantidadePlanta, float precoPlanta) {
        float total = 0;

        total = precoPlanta * quantidadePlanta;

        return total;
    }

    private static float calculoTroco(float valorCliente, float valorTotalCompra) {
        float total = 0;

        total = valorCliente - valorTotalCompra;

        return total;
    }

    private static void mostrarInformacoesLoja() {
        System.out.println("---------- LOJA ----------");
        loja.mostrarInformacoesLoja();
        loja.endereco.apresentarLogradouro();

        mostrarFuncionarios(vendedores, "VENDEDORES", "Vendedor", true, 0.2);
        mostrarFuncionarios(gerentes, "GERENTES", "Gerente", true, 0.35);
        System.out.println("");
        mostrarClientes();
    }

    private static void cadastrarFuncionario(String tipo) {
        scanner.nextLine();
        System.out.println("Digite o nome do vendedor:");
        String nome = scanner.nextLine();

        System.out.println("Digite a idade:");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o salário base:");
        double salarioBase = scanner.nextDouble();
        scanner.nextLine();

        final Endereco endereco = retornaEndereco();

        if (tipo.equalsIgnoreCase("Vendedor")) {
            vendedores.add(new Vendedor(nome, idade, endereco, loja, salarioBase));
            System.out.println("Vendedor cadastrado com sucesso!");
        } else if (tipo.equalsIgnoreCase("Gerente")) {
            gerentes.add(new Gerente(nome, idade, endereco, loja, salarioBase));
            System.out.println("Gerente cadastrado com sucesso!");
        } else {
            System.out.println("Tipo de funcionário inválido.");
        }
    }

    private static void cadastrarCliente() {
        scanner.nextLine();

        System.out.println("Digite o nome do cliente:");
        final String nome = scanner.nextLine();

        System.out.println("Digite a idade:");
        final int idade = scanner.nextInt();
        scanner.nextLine();

        final Endereco endereco = retornaEndereco();

        clientes.add(new Cliente(nome, idade, endereco));

        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void cadastrarItem() {
        scanner.nextLine();
        System.out.println("Informe o nome da planta:");
        final String nome = scanner.nextLine();

        System.out.println("Informe o tipo da planta: ");
        final String tipo = scanner.nextLine();

        System.out.println("Qual o valor dessa planta?");
        final double valorPlanta = scanner.nextDouble();
        scanner.nextLine();

        final String id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        itens.add(new Item(id, nome, tipo, valorPlanta));
    }

    private static void mostrarItens() {
        if (itens.isEmpty()) {
            System.out.println("Não tem itens cadastrados!");
            return;
        }

        for (Item i : itens) {
            i.gerarDescricao();
        }
    }

    private static void mostrarPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("Não tem pedidos criados!");
            return;
        }

        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            p.gerarDescricaoVenda();

            if (i < processaPedidos.size()) {
                ProcessaPedido proc = processaPedidos.get(i);
                final String statusPedidoFormatado = proc.getStatus() == true ? "Aprovado" : "Pendente";

                System.out.println("Status do Pedido: " + statusPedidoFormatado);
            }
        }
    }

    private static void criarPedido() {
        if (itens.size() != 0 && vendedores.size() != 0 && clientes.size() != 0) {
            System.out.println("Vamos criar sua ordem de reserva!");
            System.out.println("");

            final int quantidadeCliente = clientes.size();
            System.out.println("Informe o cliente de 1 até " + quantidadeCliente);
            mostrarClientes();
            final int clienteEscolhido = scanner.nextInt();
            final Cliente cliente = clientes.get(clienteEscolhido - 1);

            final int quantidadeVendedores = vendedores.size();
            System.out.println("Informe o vendedor de 1 até " + quantidadeVendedores);
            mostrarFuncionarios(vendedores, "VENDEDORES", "Vendedor", false, 0.2);
            final int vendedorEscolhido = scanner.nextInt();
            final Vendedor vendedor = vendedores.get(vendedorEscolhido - 1);

            List<Item> itensSelecionados = new ArrayList<>(itens.size());
            System.out.println("");
            System.out.println("Agora escolha os itens para serem processados!");
            System.out.println("Você pode escolher mais de 1 item!");
            final int quantidadeItensDisponiveis = itens.size();

            System.out.println("Informe os itens podendo ser de 1 até " + quantidadeItensDisponiveis);
            boolean selecionandoItens = true;

            int contador = 0;
            do {
                if (contador != 0 && itens.size() == contador) {
                    break;
                }

                mostrarItens();
                final int itemEscolhido = scanner.nextInt();
                final Item item = itens.get(itemEscolhido - 1);

                System.out.println("Terminou de selecionar os itens? 1 - Não | 2 - Sim");
                final int opcaoItens = scanner.nextInt();

                selecionandoItens = opcaoItens == 1 ? true : false;

                if (opcaoItens != 1 && opcaoItens != 2) {
                    continue;
                }
                itensSelecionados.add(item);
                contador++;
            } while (selecionandoItens);
            scanner.nextLine();

            System.out.println("Agora preciso que informa a data de pagamento com o seguinte formato (dd/mm/yyyy hh:mm)");
            String pegaDataPagamento = scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataPagamento = LocalDateTime.parse(pegaDataPagamento, formatter);

            System.out.println("Qual é a data da reserva? (dd/mm/yyyy HH:mm)");
            String pegaDataVencimentoReserva = scanner.nextLine();

            LocalDateTime dataVencimentoReserva = LocalDateTime.parse(pegaDataVencimentoReserva, formatter);

            final String id = UUID.randomUUID().toString();
            LocalDateTime dataCriacao = LocalDateTime.now();
            Pedido pedido = new Pedido(
                    id,
                    dataCriacao,
                    dataPagamento,
                    dataVencimentoReserva,
                    cliente,
                    vendedor,
                    loja,
                    itensSelecionados
            );
            pedidos.add(pedido);

            processaPedidos.add(new ProcessaPedido());
            final int processaPedidoAtual = processaPedidos.size();
            ProcessaPedido pedidoAtual = processaPedidos.get(processaPedidoAtual - 1);
            pedidoAtual.processar(pedido);

            System.out.println("Criado a ordem de pedido!");
        } else {
            System.out.println("É necessário ter pelo menos 1 item cadastrado, 1 vendedor cadastrado e 1 cliente cadastrado!");
        }
    }

    private static Endereco retornaEndereco() {
        System.out.println("Digite o estado:");
        String estado = scanner.nextLine();

        System.out.println("Digite a cidade:");
        String cidade = scanner.nextLine();

        System.out.println("Digite o bairro:");
        String bairro = scanner.nextLine();

        System.out.println("Digite a rua:");
        String rua = scanner.nextLine();

        System.out.println("Digite o número:");
        int numero = scanner.nextInt();

        System.out.println("Digite o complemento:");
        String complemento = scanner.nextLine();

        Endereco endereco = new Endereco(estado, cidade, bairro, rua, numero, complemento);
        return endereco;
    }

    private static void mostrarClientes() {
        System.out.println("---------- CLIENTES ----------");
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado!");
        } else {
            System.out.println("Clientes cadastrados:");
            for (Cliente c : clientes) {
                c.apresentarse();
                System.out.println("");
            }
        }
        System.out.println("");
    }

    private static void populaDados() {
        Endereco enderecoCliente1 = new Endereco(
                "Paraná", "Cascavel", "Centro", "Rua Paraná", 123, "Casa"
        );

        Endereco enderecoCliente2 = new Endereco(
                "Paraná", "Toledo", "Jardim Gisela", "Rua das Flores", 456, "Apartamento"
        );

        Endereco enderecoVendedor1 = new Endereco(
                "Paraná", "Cascavel", "Neva", "Rua Vitória", 321, "Casa"
        );

        Endereco enderecoVendedor2 = new Endereco(
                "Paraná", "Cascavel", "Pioneiros", "Rua São Paulo", 654, "Sala 2"
        );

        Cliente cliente1 = new Cliente("João da Silva", 32, enderecoCliente1);
        Cliente cliente2 = new Cliente("Maria Oliveira", 27, enderecoCliente2);

        Vendedor vendedor1 = new Vendedor("Carlos Souza", 29, enderecoVendedor1, loja, 2500.00);
        Vendedor vendedor2 = new Vendedor("Fernanda Lima", 35, enderecoVendedor2, loja, 3200.00);

        Item item1 = new Item("ITM001", "Samambaia", "Planta ornamental", 35.90);
        Item item2 = new Item("ITM002", "Orquídea", "Planta florida", 59.90);
        Item item3 = new Item("ITM003", "Cacto", "Planta suculenta", 22.50);

        clientes.add(cliente1);
        clientes.add(cliente2);

        vendedores.add(vendedor1);
        vendedores.add(vendedor2);

        itens.add(item1);
        itens.add(item2);
        itens.add(item3);

        List<Item> itensPedido1 = new ArrayList<>();
        itensPedido1.add(item1);
        itensPedido1.add(item2);

        Pedido pedido1 = new Pedido(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(2),
                LocalDateTime.now().plusDays(1),
                cliente1,
                vendedor1,
                loja,
                itensPedido1
        );

        pedidos.add(pedido1);

        ProcessaPedido processaPedido1 = new ProcessaPedido();
        processaPedido1.processar(pedido1);
        processaPedidos.add(processaPedido1);

        vendas.add(new VendaPlanta(
                5,
                179.50f,
                0,
                false,
                20,
                LocalDate.now()
        ));

        System.out.println("Dados fake carregados com sucesso!");
    }

    private static void mostrarFuncionarios(List<? extends Funcionario> funcionarios, String titulo, String descricaoCargo, boolean mostraInformacoesSensiveis, double porcentagemBonus) {
        System.out.println("\n---------- " + titulo + " ----------");

        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum " + descricaoCargo.toLowerCase() + " cadastrado!");
            return;
        }

        double somaMedias = 0;
        int totalFuncionarios = funcionarios.size();

        for (Funcionario f : funcionarios) {
            f.apresentarse();

            if (mostraInformacoesSensiveis) {
                double mediaFuncionario = f.calcularMedia();
                double bonusFuncionario = f.calcularBonus(porcentagemBonus);
                somaMedias += mediaFuncionario;

                System.out.println("INFORMAÇÕES " + descricaoCargo.toUpperCase());
                System.out.println("Salário Base: " + f.getSalarioBase()
                        + " | Bônus: " + bonusFuncionario);
            }

            System.out.println("");
        }

        if (mostraInformacoesSensiveis) {
            double mediaSalarial = totalFuncionarios > 0 ? somaMedias / totalFuncionarios : 0;
            System.out.printf("Média salarial de todos os %s: %.2f%n",
                    titulo.toLowerCase(), mediaSalarial);
            System.out.println("");
        }
    }
}
