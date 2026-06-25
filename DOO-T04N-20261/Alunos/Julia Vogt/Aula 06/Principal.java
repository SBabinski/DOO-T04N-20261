package Calculadora;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<LocalDate> historicoDatas = new ArrayList<>();

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static Loja loja = new Loja("My Plant", "My Plant LTDA", "123456",
            "Cascavel", "Centro", "Rua A");

    public static void main(String[] args) {

        int menu = 0;

        while (menu != 13) {

            System.out.println("\n1 - Calcular Preço Total");
            System.out.println("2 - Calcular Troco");
            System.out.println("3 - Histórico de Vendas");
            System.out.println("4 - Buscar vendas por DIA");
            System.out.println("5 - Buscar vendas por MÊS");
            System.out.println("6 - Apresentar Loja");
            System.out.println("7 - Contar Clientes");
            System.out.println("8 - Contar Vendedores");
            System.out.println("9 - Apresentar Vendedor");
            System.out.println("10 - Cadastrar Cliente");
            System.out.println("11 - Cadastrar Vendedor");
            System.out.println("12 - Apresentar Cliente");
            System.out.println("13 - Sair");

            menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {

                case 1:
                    precoTotal();
                    break;

                case 2:
                    troco();
                    break;

                case 3:
                    exibirHistorico();
                    break;

                case 4:
                    buscarPorDia();
                    break;

                case 5:
                    buscarPorMes();
                    break;

                case 6:
                    loja.apresentarSe();
                    break;

                case 7:
                    loja.contarClientes();
                    break;

                case 8:
                    loja.contarVendedores();
                    break;

                case 9:
                    if (!loja.vendedores.isEmpty()) {
                        Vendedor v = loja.vendedores.get(0);
                        v.apresentarSe();
                        v.calcularMedia();
                        v.calcularBonus();
                    } else {
                        System.out.println("Nenhum vendedor cadastrado.");
                    }
                    break;

                case 10:
                    cadastrarCliente();
                    break;

                case 11:
                    cadastrarVendedor();
                    break;
                    
                case 12: 
                	 if (!loja.clientes.isEmpty()) {

                	        Cliente c = loja.clientes.get(0);
                	        c.apresentarSe();

                	    } else {
                	        System.out.println("Nenhum cliente cadastrado.");
                	    }
                	break;

                case 13:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void precoTotal() {

        System.out.println("Quantidade:");
        int quantidade = scanner.nextInt();

        System.out.println("Preço unitário:");
        float preco = scanner.nextFloat();

        float total = quantidade * preco;

        if (quantidade > 10) {
            total -= total * 0.05f;
            System.out.println("Desconto aplicado!");
        }

        System.out.println("Total: " + total);

        historicoDatas.add(LocalDate.now());
    }

    public static void troco() {

        System.out.println("Valor pago:");
        float pago = scanner.nextFloat();

        System.out.println("Valor compra:");
        float compra = scanner.nextFloat();

        System.out.println("Troco: " + (pago - compra));
    }

    public static void exibirHistorico() {

        if (historicoDatas.isEmpty()) {
            System.out.println("Sem vendas.");
        } else {
            for (LocalDate data : historicoDatas) {
                System.out.println(data.format(formatter));
            }
        }
    }

    public static void buscarPorDia() {

        System.out.println("Data (dd/MM/yyyy):");
        String input = scanner.nextLine();

        LocalDate busca = LocalDate.parse(input, formatter);

        int cont = 0;

        for (LocalDate d : historicoDatas) {
            if (d.equals(busca)) cont++;
        }

        System.out.println("Total no dia: " + cont);
    }

    public static void buscarPorMes() {

        System.out.println("Mês/Ano (MM/yyyy):");
        String input = scanner.nextLine();

        String[] p = input.split("/");

        int mes = Integer.parseInt(p[0]);
        int ano = Integer.parseInt(p[1]);

        int cont = 0;

        for (LocalDate d : historicoDatas) {
            if (d.getMonthValue() == mes && d.getYear() == ano) cont++;
        }

        System.out.println("Total no mês: " + cont);
    }

    public static void cadastrarCliente() {

        System.out.println("Nome:");
        String nome = scanner.nextLine();

        System.out.println("Idade:");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Cidade:");
        String cidade = scanner.nextLine();

        System.out.println("Bairro:");
        String bairro = scanner.nextLine();

        System.out.println("Rua:");
        String rua = scanner.nextLine();

        Cliente c = new Cliente(nome, idade, cidade, bairro, rua);

        loja.clientes.add(c);

        System.out.println("Cliente cadastrado!");
    }

    public static void cadastrarVendedor() {

        System.out.println("Nome:");
        String nome = scanner.nextLine();

        System.out.println("Idade:");
        int idade = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Cidade:");
        String cidade = scanner.nextLine();

        System.out.println("Bairro:");
        String bairro = scanner.nextLine();

        System.out.println("Rua:");
        String rua = scanner.nextLine();

        System.out.println("Salário base:");
        float salario = scanner.nextFloat();
        scanner.nextLine();

        Vendedor v = new Vendedor(nome, idade, loja, cidade, bairro, rua, salario);

        loja.vendedores.add(v);

        System.out.println("Vendedor cadastrado!");
    }
}