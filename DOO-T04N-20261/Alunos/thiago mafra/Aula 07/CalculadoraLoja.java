package Aulas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculadoraLoja {

    static class Endereco {
        String estado;
        String cidade;
        String bairro;
        String numero;
        String complemento;

        Endereco(String estado, String cidade, String bairro, String numero, String complemento) {
            this.estado = estado;
            this.cidade = cidade;
            this.bairro = bairro;
            this.numero = numero;
            this.complemento = complemento;
        }

        public void apresentarLogradouro() {
            System.out.println("Endereço: " + bairro + ", " + numero + " - " + cidade + "/" + estado);

            if (complemento != null && !complemento.isEmpty()) {
                System.out.println("Complemento: " + complemento);
            }
        }
    }

    static class Pessoa {
        String nome;
        int idade;

        Pessoa(String nome, int idade) {
            this.nome = nome;
            this.idade = idade;
        }

        public void apresentarSe() {
            System.out.println("Nome: " + nome);
            System.out.println("Idade: " + idade);
        }
    }

    static class Vendedor extends Pessoa {
        String loja;
        String cidade;
        String bairro;
        String rua;

        Endereco endereco;

        double salarioBase;
        List<Double> salarioRecebido = new ArrayList<>();

        Vendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {
            super(nome, idade);

            this.loja = loja;
            this.cidade = cidade;
            this.bairro = bairro;
            this.rua = rua;

            this.endereco = new Endereco("PR", cidade, bairro, rua, "");

            this.salarioBase = salarioBase;

            salarioRecebido.add(salarioBase);
            salarioRecebido.add(salarioBase + 200);
            salarioRecebido.add(salarioBase + 150);
        }

        public void apresentarSe() {
            super.apresentarSe();
            System.out.println("Loja: " + loja);
            endereco.apresentarLogradouro();
            System.out.println("Salário Base: R$ " + salarioBase);
        }

        public double calcularMedia() {
            double soma = 0;

            for (double salario : salarioRecebido) {
                soma += salario;
            }

            return soma / salarioRecebido.size();
        }

        public double calcularBonus() {
            return salarioBase * 0.2;
        }

        public void adicionarSalario(double valor) {
            salarioRecebido.add(valor);
        }
    }

    static class Gerente extends Pessoa {
        String loja;
        String cidade;
        String bairro;
        String rua;

        Endereco endereco;

        double salarioBase;
        List<Double> salarioRecebido = new ArrayList<>();

        Gerente(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {
            super(nome, idade);

            this.loja = loja;
            this.cidade = cidade;
            this.bairro = bairro;
            this.rua = rua;

            this.endereco = new Endereco("PR", cidade, bairro, rua, "");

            this.salarioBase = salarioBase;

            salarioRecebido.add(salarioBase);
            salarioRecebido.add(salarioBase + 500);
            salarioRecebido.add(salarioBase + 800);
        }

        public void apresentarSe() {
            super.apresentarSe();
            System.out.println("Loja: " + loja);
            endereco.apresentarLogradouro();
            System.out.println("Salário Base: R$ " + salarioBase);
        }

        public double calcularMedia() {
            double soma = 0;

            for (double salario : salarioRecebido) {
                soma += salario;
            }

            return soma / salarioRecebido.size();
        }

        public double calcularBonus() {
            return salarioBase * 0.35;
        }
    }

    static class Cliente extends Pessoa {
        String cidade;
        String bairro;
        String rua;

        Endereco endereco;

        Cliente(String nome, int idade, String cidade, String bairro, String rua) {
            super(nome, idade);

            this.cidade = cidade;
            this.bairro = bairro;
            this.rua = rua;

            this.endereco = new Endereco("PR", cidade, bairro, rua, "");
        }

        public void apresentarSe() {
            super.apresentarSe();
            endereco.apresentarLogradouro();
        }
    }

    static class Loja {
        String nomeFantasia;
        String razaoSocial;
        String cnpj;

        String cidade;
        String bairro;
        String rua;

        Endereco endereco;

        List<Vendedor> vendedores = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();

        Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
            this.nomeFantasia = nomeFantasia;
            this.razaoSocial = razaoSocial;
            this.cnpj = cnpj;

            this.cidade = cidade;
            this.bairro = bairro;
            this.rua = rua;

            this.endereco = new Endereco("PR", cidade, bairro, rua, "");
        }

        public void apresentarSe() {
            System.out.println("Nome Fantasia: " + nomeFantasia);
            System.out.println("CNPJ: " + cnpj);
            endereco.apresentarLogradouro();
            System.out.println("Quantidade de clientes: " + clientes.size());
            System.out.println("Quantidade de vendedores: " + vendedores.size());
        }
    }

    static class Item {
        int id;
        String nome;
        String tipo;
        double valor;

        Item(int id, String nome, String tipo, double valor) {
            this.id = id;
            this.nome = nome;
            this.tipo = tipo;
            this.valor = valor;
        }

        public void gerarDescricao() {
            System.out.println("ID: " + id);
            System.out.println("Nome: " + nome);
            System.out.println("Tipo: " + tipo);
            System.out.println("Valor: R$ " + valor);
        }
    }

    static class Pedido {
        int id;
        LocalDate dataCriacao;
        LocalDate dataPagamento;
        LocalDate dataVencimentoReserva;
        Cliente cliente;
        Vendedor vendedor;
        Loja loja;
        List<Item> itens = new ArrayList<>();

        Pedido(int id, LocalDate dataCriacao, Cliente cliente, Vendedor vendedor, Loja loja) {
            this.id = id;
            this.dataCriacao = dataCriacao;
            this.dataVencimentoReserva = dataCriacao.plusDays(3);
            this.cliente = cliente;
            this.vendedor = vendedor;
            this.loja = loja;
        }

        public double calcularValorTotal() {
            double total = 0;

            for (Item item : itens) {
                total += item.valor;
            }

            return total;
        }

        public void gerarDescricaoVenda() {
            System.out.println("\n===== PEDIDO =====");
            System.out.println("ID do Pedido: " + id);
            System.out.println("Data de criação: " + dataCriacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Cliente: " + cliente.nome);
            System.out.println("Vendedor: " + vendedor.nome);

            System.out.println("\nItens:");
            for (Item item : itens) {
                System.out.println("- " + item.nome + " | R$ " + item.valor);
            }

            System.out.println("\nValor total: R$ " + calcularValorTotal());

            if (dataPagamento != null) {
                System.out.println("Pagamento realizado em: " +
                        dataPagamento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            } else {
                System.out.println("Pagamento pendente");
            }
        }
    }

    static class ProcessaPedido {

        public boolean processar(Pedido pedido) {
            if (confirmarPagamento()) {
                pedido.dataPagamento = LocalDate.now();
                return true;
            }

            return false;
        }

        private boolean confirmarPagamento() {
            return true;
        }
    }

    static class Venda {
        int quantidade;
        double precoUnitario;
        double valorTotal;
        double desconto;
        LocalDate data;

        Venda(int quantidade, double precoUnitario, double valorTotal, double desconto, LocalDate data) {
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
            this.valorTotal = valorTotal;
            this.desconto = desconto;
            this.data = data;
        }
    }

    static List<Venda> registroVendas = new ArrayList<>();
    static List<Pedido> pedidos = new ArrayList<>();

    public static void mostrarFuncionarios(Loja loja) {
        System.out.println("\n===== FUNCIONÁRIOS =====");

        for (Vendedor vendedor : loja.vendedores) {
            System.out.println("\n-------------------");
            vendedor.apresentarSe();

            System.out.println("Salários Recebidos:");
            for (double salario : vendedor.salarioRecebido) {
                System.out.println("R$ " + salario);
            }

            System.out.println("Média salarial: R$ " + vendedor.calcularMedia());
            System.out.println("Bônus: R$ " + vendedor.calcularBonus());
        }
    }

    public static void mostrarClientes(Loja loja) {
        System.out.println("\n===== CLIENTES =====");

        for (Cliente cliente : loja.clientes) {
            System.out.println("\n-------------------");
            cliente.apresentarSe();
        }
    }

    public static void mostrarRegistroVendas() {
        System.out.println("\n===== REGISTRO DE VENDAS =====");

        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        for (Venda venda : registroVendas) {
            System.out.println("----------------------");
            System.out.println("Data: " + venda.data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            System.out.println("Quantidade: " + venda.quantidade);
            System.out.println("Preço Unitário: R$ " + venda.precoUnitario);
            System.out.println("Desconto: R$ " + venda.desconto);
            System.out.println("Valor Total: R$ " + venda.valorTotal);
        }
    }

    public static void consultarVendasPorDia(Scanner scanner) {
        System.out.print("Digite a data (dd/MM/yyyy): ");
        scanner.nextLine();
        String dataTexto = scanner.nextLine();

        LocalDate dataBusca = LocalDate.parse(dataTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        boolean encontrou = false;

        for (Venda venda : registroVendas) {
            if (venda.data.equals(dataBusca)) {
                encontrou = true;

                System.out.println("----------------------");
                System.out.println("Quantidade: " + venda.quantidade);
                System.out.println("Valor Total: R$ " + venda.valorTotal);
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma venda encontrada nessa data.");
        }
    }

    public static void consultarVendasPorMes(Scanner scanner) {
        System.out.print("Digite o mês e ano (MM/yyyy): ");
        scanner.nextLine();
        String mesAno = scanner.nextLine();

        String[] partes = mesAno.split("/");
        int mes = Integer.parseInt(partes[0]);
        int ano = Integer.parseInt(partes[1]);

        boolean encontrou = false;

        for (Venda venda : registroVendas) {
            if (venda.data.getMonthValue() == mes && venda.data.getYear() == ano) {
                encontrou = true;

                System.out.println("----------------------");
                System.out.println("Data: " + venda.data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                System.out.println("Valor Total: R$ " + venda.valorTotal);
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma venda encontrada nesse mês.");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Loja loja = new Loja(
                "My Plant",
                "My Plant LTDA",
                "12.345.678/0001-99",
                "Cascavel",
                "Centro",
                "100"
        );

        Vendedor vendedor1 = new Vendedor(
                "João",
                28,
                "My Plant",
                "Cascavel",
                "Centro",
                "101",
                2500
        );

        Vendedor vendedor2 = new Vendedor(
                "Maria",
                32,
                "My Plant",
                "Cascavel",
                "Brasmadeira",
                "202",
                2800
        );

        loja.vendedores.add(vendedor1);
        loja.vendedores.add(vendedor2);

        Cliente cliente1 = new Cliente(
                "Carlos",
                40,
                "Cascavel",
                "Centro",
                "303"
        );

        Cliente cliente2 = new Cliente(
                "Fernanda",
                29,
                "Cascavel",
                "Neva",
                "404"
        );

        loja.clientes.add(cliente1);
        loja.clientes.add(cliente2);

        Gerente gerente = new Gerente(
                "Roberto",
                45,
                "My Plant",
                "Cascavel",
                "Centro",
                "500",
                5000
        );

        int opcao;

        do {
            System.out.println("\n========== MENU ==========");
            System.out.println("[1] Calcular preço total");
            System.out.println("[2] Calcular troco");
            System.out.println("[3] Ver registro de vendas");
            System.out.println("[4] Consultar vendas por dia");
            System.out.println("[5] Consultar vendas por mês");
            System.out.println("[6] Ver funcionários");
            System.out.println("[7] Ver clientes");
            System.out.println("[8] Criar pedido");
            System.out.println("[9] Ver gerente");
            System.out.println("[0] Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();

            switch (opcao) {

            case 1:
                System.out.print("Digite a quantidade: ");
                int quantidade = scanner.nextInt();

                System.out.print("Digite o preço unitário: ");
                double precoUnitario = scanner.nextDouble();

                double total = quantidade * precoUnitario;
                double desconto = 0;

                if (quantidade > 10) {
                    desconto = total * 0.05;
                }

                double valorFinal = total - desconto;

                System.out.println("Preço total: R$ " + total);
                System.out.println("Desconto: R$ " + desconto);
                System.out.println("Valor final: R$ " + valorFinal);

                scanner.nextLine();

                System.out.print("\nNome do cliente: ");
                String nomeCliente = scanner.nextLine();

                System.out.print("Idade do cliente: ");
                int idadeCliente = scanner.nextInt();

                scanner.nextLine();

                System.out.print("Estado: ");
                String estado = scanner.nextLine();

                System.out.print("Cidade: ");
                String cidade = scanner.nextLine();

                System.out.print("Bairro: ");
                String bairro = scanner.nextLine();

                System.out.print("Número: ");
                String numero = scanner.nextLine();

                System.out.print("Complemento: ");
                String complemento = scanner.nextLine();

                Cliente novoCliente = new Cliente(nomeCliente, idadeCliente, cidade, bairro, numero);
                novoCliente.endereco = new Endereco(estado, cidade, bairro, numero, complemento);

                loja.clientes.add(novoCliente);

                System.out.println("\nEscolha o vendedor responsável:");
                for (int i = 0; i < loja.vendedores.size(); i++) {
                    System.out.println(i + " - " + loja.vendedores.get(i).nome);
                }

                int vendedorEscolhido = scanner.nextInt();

                if (vendedorEscolhido >= 0 && vendedorEscolhido < loja.vendedores.size()) {

                    Vendedor vendedor = loja.vendedores.get(vendedorEscolhido);

                    double novoSalario = vendedor.salarioBase + (valorFinal * 0.02);
                    vendedor.adicionarSalario(novoSalario);

                    registroVendas.add(new Venda(
                            quantidade,
                            precoUnitario,
                            valorFinal,
                            desconto,
                            LocalDate.now()
                    ));

                    System.out.println("Venda registrada com sucesso! 🎉");
                    System.out.println("Cliente cadastrado com sucesso!");
                    System.out.println("Nova média salarial do vendedor: R$ " + vendedor.calcularMedia());

                } else {
                    System.out.println("Vendedor inválido! ⚠️");
                }

                break;

                case 2:
                    System.out.print("Digite o valor recebido: ");
                    double valorRecebido = scanner.nextDouble();

                    System.out.print("Digite o valor da compra: ");
                    double valorCompra = scanner.nextDouble();

                    if (valorRecebido >= valorCompra) {
                        System.out.println("Troco: R$ " + (valorRecebido - valorCompra));
                    } else {
                        System.out.println("Valor recebido insuficiente.");
                    }

                    break;

                case 3:
                    mostrarRegistroVendas();
                    break;

                case 4:
                    consultarVendasPorDia(scanner);
                    break;

                case 5:
                    consultarVendasPorMes(scanner);
                    break;

                case 6:
                    mostrarFuncionarios(loja);
                    break;

                case 7:
                    mostrarClientes(loja);
                    break;

                case 8:
                    if (loja.clientes.isEmpty() || loja.vendedores.isEmpty()) {
                        System.out.println("É necessário possuir clientes e vendedores cadastrados.");
                        break;
                    }

                    Pedido pedido = new Pedido(
                            pedidos.size() + 1,
                            LocalDate.now(),
                            loja.clientes.get(0),
                            loja.vendedores.get(0),
                            loja
                    );

                    Item item1 = new Item(1, "Vaso Decorativo", "Decoração", 79.90);
                    Item item2 = new Item(2, "Planta Suculenta", "Planta", 25.50);

                    pedido.itens.add(item1);
                    pedido.itens.add(item2);

                    ProcessaPedido processador = new ProcessaPedido();

                    if (processador.processar(pedido)) {
                        pedidos.add(pedido);
                        System.out.println("Pedido processado com sucesso! ✅");
                    } else {
                        System.out.println("Falha ao processar pedido.");
                    }

                    pedido.gerarDescricaoVenda();

                    break;

                case 9:
                    System.out.println("\n===== GERENTE =====");
                    gerente.apresentarSe();
                    System.out.println("Média salarial: R$ " + gerente.calcularMedia());
                    System.out.println("Bônus: R$ " + gerente.calcularBonus());
                    break;

                case 0:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}