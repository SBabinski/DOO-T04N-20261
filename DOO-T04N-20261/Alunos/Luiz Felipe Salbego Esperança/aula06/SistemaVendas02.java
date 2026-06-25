import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SistemaVendas02 {

    static class Venda {
        double quantidade;
        double precoUnitario;
        double total;
        double desconto;
        LocalDate data;

        Venda(double quantidade, double precoUnitario, double total, double desconto, LocalDate data) {
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
            this.total = total;
            this.desconto = desconto;
            this.data = data;
        }

        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return "Data: " + data.format(formatter)
                    + " | Quantidade: " + quantidade
                    + " | Preco: R$ " + precoUnitario
                    + " | Total: R$ " + total
                    + " | Desconto: R$ " + desconto;
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
        double[] salarioRecebido;

        Vendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {
            this.nome = nome;
            this.idade = idade;
            this.loja = loja;
            this.cidade = cidade;
            this.bairro = bairro;
            this.rua = rua;
            this.salarioBase = salarioBase;
            this.salarioRecebido = new double[]{2000, 2100, 2200};
        }

        void apresentarse() {
            System.out.println("Nome: " + nome + ", Idade: " + idade + ", Loja: " + loja);
        }

        double calcularMedia() {
            double soma = 0;
            for (int i = 0; i < salarioRecebido.length; i++) {
                soma += salarioRecebido[i];
            }
            return soma / salarioRecebido.length;
        }

        double calcularBonus() {
            return salarioBase * 0.2;
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

        void apresentarse() {
            System.out.println("Nome: " + nome + ", Idade: " + idade);
        }
    }

    static class Loja {
        String nomeFantasia;
        String razaoSocial;
        String cnpj;
        String cidade;
        String bairro;
        String rua;

        ArrayList<Vendedor> vendedores = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();

        Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
            this.nomeFantasia = nomeFantasia;
            this.razaoSocial = razaoSocial;
            this.cnpj = cnpj;
            this.cidade = cidade;
            this.bairro = bairro;
            this.rua = rua;
        }

        void apresentarse() {
            System.out.println("Loja: " + nomeFantasia);
            System.out.println("CNPJ: " + cnpj);
            System.out.println("Endereco: " + rua + ", " + bairro + ", " + cidade);
        }

        void contarClientes() {
            System.out.println("Quantidade de clientes: " + clientes.size());
        }

        void contarVendedores() {
            System.out.println("Quantidade de vendedores: " + vendedores.size());
        }
    }

    static ArrayList<Venda> vendas = new ArrayList<>();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Loja loja = new Loja("My Plant", "My Plant LTDA", "123456789", "Cascavel", "Centro", "Rua A");

        loja.vendedores.add(new Vendedor("Joao", 30, "My Plant", "Cascavel", "Centro", "Rua A", 2000));
        loja.clientes.add(new Cliente("Maria", 25, "Cascavel", "Centro", "Rua B"));

        int opcao;

        do {
            menu();
            opcao = lerInt(sc, "Opcao: ");

            switch (opcao) {
                case 1:
                    calcularVenda(sc);
                    break;
                case 2:
                    calcularTroco(sc);
                    break;
                case 3:
                    listarVendas();
                    break;
                case 4:
                    buscarPorData(sc);
                    break;
                case 5:
                    loja.apresentarse();
                    loja.contarClientes();
                    loja.contarVendedores();
                    break;
                case 6:
                    for (Vendedor v : loja.vendedores) {
                        v.apresentarse();
                        System.out.println("Media: " + v.calcularMedia());
                        System.out.println("Bonus: " + v.calcularBonus());
                    }
                    break;
                case 7:
                    for (Cliente c : loja.clientes) {
                        c.apresentarse();
                    }
                    break;
                case 8:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida");
            }

            System.out.println();

        } while (opcao != 8);

        sc.close();
    }

    static void menu() {
        System.out.println("==== My Plant ====");
        System.out.println("1 - Nova venda");
        System.out.println("2 - Calcular troco");
        System.out.println("3 - Listar vendas");
        System.out.println("4 - Buscar por data");
        System.out.println("5 - Dados da loja");
        System.out.println("6 - Vendedores");
        System.out.println("7 - Clientes");
        System.out.println("8 - Sair");
    }

    static void calcularVenda(Scanner sc) {
        double qtd = lerDouble(sc, "Quantidade: ");
        double preco = lerDouble(sc, "Preco: ");

        double total = qtd * preco;
        double desconto = 0;

        if (qtd > 10) {
            desconto = total * 0.05;
            total -= desconto;
        }

        System.out.println("Total: R$ " + total);

        vendas.add(new Venda(qtd, preco, total, desconto, LocalDate.now()));
    }

    static void calcularTroco(Scanner sc) {
        double recebido = lerDouble(sc, "Valor recebido: ");
        double compra = lerDouble(sc, "Valor da compra: ");

        double troco = recebido - compra;

        if (troco < 0) {
            System.out.println("Faltam: R$ " + Math.abs(troco));
        } else {
            System.out.println("Troco: R$ " + troco);
        }
    }

    static void listarVendas() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda");
            return;
        }

        for (Venda v : vendas) {
            System.out.println(v);
        }
    }

    static void buscarPorData(Scanner sc) {
        System.out.print("Data (dd/MM/yyyy): ");
        String entrada = sc.nextLine();

        try {
            LocalDate data = LocalDate.parse(entrada, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            for (Venda v : vendas) {
                if (v.data.equals(data)) {
                    System.out.println(v);
                }
            }

        } catch (Exception e) {
            System.out.println("Data invalida");
        }
    }

    static int lerInt(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            if (sc.hasNextInt()) {
                int v = sc.nextInt();
                sc.nextLine();
                return v;
            }
            System.out.println("Erro");
            sc.nextLine();
        }
    }

    static double lerDouble(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            if (sc.hasNextDouble()) {
                double v = sc.nextDouble();
                sc.nextLine();
                return v;
            }
            System.out.println("Erro");
            sc.nextLine();
        }
    }
}