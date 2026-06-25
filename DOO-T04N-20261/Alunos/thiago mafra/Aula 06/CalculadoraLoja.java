package Aulas; 

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CalculadoraLoja {

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

        @Override
        public String toString() {
            return "Data: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                    ", Quantidade: " + quantidade +
                    ", Valor Total: R$ " + valorTotal;
        }
    }

    static class Vendedor {
        String nome;
        int idade;
        String loja;
        String cidade;
        String bairro;
        String rua;
        double salarioBase;
        List<Double> salarioRecebido = new ArrayList<>();

        Vendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {
            this.nome = nome;
            this.idade = idade;
            this.loja = loja;
            this.cidade = cidade;
            this.bairro = bairro;
            this.rua = rua;
            this.salarioBase = salarioBase;

            salarioRecebido.add(salarioBase);
            salarioRecebido.add(salarioBase + 200);
            salarioRecebido.add(salarioBase + 150);
        }

        public double calcularMedia() {
            double soma = 0;
            for (double s : salarioRecebido) soma += s;
            return soma / salarioRecebido.size();
        }

        public double calcularBonus() {
            return salarioBase * 0.2;
        }

        public void adicionarSalario(double valor) {
            salarioRecebido.add(valor);
        }
    }

    static class Cliente {
        String nome;
        int idade;
        String cidade;
        String bairro;
        String rua;

        Cliente(String nome, int idade, String cidade, String bairro, String rua) {
            this.nome = nome;
            this.idade = idade;
            this.cidade = cidade;
            this.bairro = bairro;
            this.rua = rua;
        }
    }

    static class Loja {
        String nomeFantasia;
        String razaoSocial;
        String cnpj;
        String cidade;
        String bairro;
        String rua;
        List<Vendedor> vendedores = new ArrayList<>();
        List<Cliente> clientes = new ArrayList<>();

        Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
            this.nomeFantasia = nomeFantasia;
            this.razaoSocial = razaoSocial;
            this.cnpj = cnpj;
            this.cidade = cidade;
            this.bairro = bairro;
            this.rua = rua;
        }

        public void apresentarSe() {
            System.out.println("Loja: " + nomeFantasia);
            System.out.println("CNPJ: " + cnpj);
            System.out.println("Endereço: " + rua + ", " + bairro + ", " + cidade);
            System.out.println("Quantidade de clientes: " + clientes.size());
            System.out.println("Quantidade de vendedores: " + vendedores.size());
        }
    }

    private static List<Venda> registroVendas = new ArrayList<>();

    public static void main(String[] args) {

        Loja loja = new Loja("My Plant", "My Plant LTDA", "12.345.678/0001-99", "Cascavel", "Centro", "Rua das Flores");

        Vendedor v1 = new Vendedor("João", 30, "My Plant", "Cascavel", "Centro", "Rua A", 2000);
        Vendedor v2 = new Vendedor("Maria", 28, "My Plant", "Cascavel", "Centro", "Rua B", 2200);

        loja.vendedores.add(v1);
        loja.vendedores.add(v2);

        Cliente c1 = new Cliente("Carlos", 40, "Cascavel", "Centro", "Rua C");
        Cliente c2 = new Cliente("Ana", 35, "Cascavel", "Centro", "Rua D");

        loja.clientes.add(c1);
        loja.clientes.add(c2);

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Sistema My Plant ===");
            loja.apresentarSe();

            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Ver Registro de Vendas");
            System.out.println("[4] - Consultar Vendas por Dia");
            System.out.println("[5] - Consultar Vendas por Mês");
            System.out.println("[6] - Ver Funcionários");
            System.out.println("[7] - Ver Clientes");
            System.out.println("[8] - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    calcularPrecoTotal(scanner, loja);
                    break;
                case 2:
                    calcularTroco(scanner);
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
                    mostrarVendedores(loja);
                    break;
                case 7:
                    mostrarClientes(loja);
                    break;
                case 8:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 8);

        scanner.close();
    }

    public static void mostrarVendedores(Loja loja) {
        System.out.println("\n=== Funcionários Cadastrados ===");
        for (Vendedor v : loja.vendedores) {
            System.out.println("Nome: " + v.nome);
            System.out.println("Idade: " + v.idade);
            System.out.println("Loja: " + v.loja);
            System.out.println("Cidade: " + v.cidade);
            System.out.println("Bairro: " + v.bairro);
            System.out.println("Rua: " + v.rua);
            System.out.println("Salário Base: " + v.salarioBase);
            System.out.println("Salários Recebidos: " + v.salarioRecebido);
            System.out.println("---------------------------");
        }
    }

    public static void mostrarClientes(Loja loja) {
        System.out.println("\n=== Clientes Cadastrados ===");
        for (Cliente c : loja.clientes) {
            System.out.println("Nome: " + c.nome);
            System.out.println("Idade: " + c.idade);
            System.out.println("Cidade: " + c.cidade);
            System.out.println("Bairro: " + c.bairro);
            System.out.println("Rua: " + c.rua);
            System.out.println("---------------------------");
        }
    }

    public static void calcularPrecoTotal(Scanner scanner, Loja loja) {
        System.out.print("Digite a quantidade de plantas: ");
        int quantidade = scanner.nextInt();

        System.out.print("Digite o preço unitário da planta: ");
        double precoUnitario = scanner.nextDouble();

        double valorBruto = quantidade * precoUnitario;
        double desconto = 0;

        if (quantidade > 10) {
            desconto = valorBruto * 0.05;
        }

        double valorFinal = valorBruto - desconto;

        System.out.println("Preço final da compra: R$ " + valorFinal);

        LocalDate dataVenda = LocalDate.now();
        registroVendas.add(new Venda(quantidade, precoUnitario, valorFinal, desconto, dataVenda));

        scanner.nextLine();
        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine();

        System.out.print("Idade do cliente: ");
        int idadeCliente = scanner.nextInt();

        Cliente novoCliente = new Cliente(nomeCliente, idadeCliente, loja.cidade, loja.bairro, loja.rua);
        loja.clientes.add(novoCliente);

        System.out.println("Escolha o vendedor:");
        for (int i = 0; i < loja.vendedores.size(); i++) {
            System.out.println(i + " - " + loja.vendedores.get(i).nome);
        }

        int vendedorEscolhido = scanner.nextInt();
        Vendedor v = loja.vendedores.get(vendedorEscolhido);

        double novoSalario = v.salarioBase + (valorFinal * 0.02);
        v.adicionarSalario(novoSalario);

        System.out.println("Venda registrada!");
        System.out.println("Total de clientes: " + loja.clientes.size());
        System.out.println("Nova média salarial do vendedor: " + v.calcularMedia());
    }

    public static void calcularTroco(Scanner scanner) {
        System.out.print("Digite o valor recebido: ");
        double valorRecebido = scanner.nextDouble();

        System.out.print("Digite o valor da compra: ");
        double valorCompra = scanner.nextDouble();

        double troco = valorRecebido - valorCompra;

        if (troco < 0) {
            System.out.println("Valor insuficiente!");
        } else {
            System.out.println("Troco: R$ " + troco);
        }
    }

    public static void mostrarRegistroVendas() {
        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (Venda venda : registroVendas) {
                System.out.println(venda);
            }
        }
    }

    public static void consultarVendasPorDia(Scanner scanner) {
        System.out.print("Digite a data (dd/MM/yyyy): ");
        scanner.nextLine();
        String dataStr = scanner.nextLine();
        LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        int total = registroVendas.stream()
                .filter(v -> v.data.equals(data))
                .mapToInt(v -> v.quantidade)
                .sum();

        System.out.println("Total de vendas no dia: " + total);
    }

    public static void consultarVendasPorMes(Scanner scanner) {
        System.out.print("Digite o mês e ano (MM/yyyy): ");
        scanner.nextLine();
        String mesAnoStr = scanner.nextLine();
        LocalDate mesAno = LocalDate.parse("01/" + mesAnoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        int total = registroVendas.stream()
                .filter(v -> v.data.getMonth() == mesAno.getMonth() && v.data.getYear() == mesAno.getYear())
                .mapToInt(v -> v.quantidade)
                .sum();

        System.out.println("Total de vendas no mês: " + total);
    }
}