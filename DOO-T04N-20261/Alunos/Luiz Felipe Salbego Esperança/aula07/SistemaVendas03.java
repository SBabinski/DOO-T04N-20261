import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;

public class SistemaVendas03 {

    static class Endereco {
        String estado, cidade, bairro, numero, complemento;

        Endereco(String estado, String cidade, String bairro, String numero, String complemento) {
            this.estado = estado;
            this.cidade = cidade;
            this.bairro = bairro;
            this.numero = numero;
            this.complemento = complemento;
        }

        void apresentarLogradouro() {
            System.out.println(bairro + ", " + cidade + " - " + estado + " N " + numero + " (" + complemento + ")");
        }
    }

    static class Pessoa {
        String nome;
        int idade;
        Endereco endereco;

        Pessoa(String nome, int idade, Endereco endereco) {
            this.nome = nome;
            this.idade = idade;
            this.endereco = endereco;
        }
    }

    static class Venda {
        double quantidade;
        double precoUnitario;
        double total;
        double desconto;
        Date data;

        Venda(double quantidade, double precoUnitario, double total, double desconto, Date data) {
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
            this.total = total;
            this.desconto = desconto;
            this.data = data;
        }

        public String toString() {
            return "Data: " + data
                    + " | Quantidade: " + quantidade
                    + " | Preco: R$ " + precoUnitario
                    + " | Total: R$ " + total
                    + " | Desconto: R$ " + desconto;
        }
    }

    static class Vendedor extends Pessoa {
        String loja;
        double salarioBase;
        double[] salarioRecebido;

        Vendedor(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
            super(nome, idade, endereco);
            this.loja = loja;
            this.salarioBase = salarioBase;
            this.salarioRecebido = new double[]{2000, 2100, 2200};
        }

        void apresentarse() {
            System.out.println("Nome: " + nome + ", Idade: " + idade + ", Loja: " + loja);
        }

        double calcularMedia() {
            double soma = 0;
            for (double s : salarioRecebido) soma += s;
            return soma / salarioRecebido.length;
        }

        double calcularBonus() {
            return salarioBase * 0.2;
        }
    }

    static class Gerente extends Vendedor {

        Gerente(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
            super(nome, idade, loja, endereco, salarioBase);
        }

        double calcularBonus() {
            return salarioBase * 0.35;
        }
    }

    static class Cliente extends Pessoa {

        Cliente(String nome, int idade, Endereco endereco) {
            super(nome, idade, endereco);
        }

        void apresentarse() {
            System.out.println("Nome: " + nome + ", Idade: " + idade);
        }
    }

    static class Loja {
        String nomeFantasia;
        String razaoSocial;
        String cnpj;
        Endereco endereco;

        ArrayList<Vendedor> vendedores = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();

        Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
            this.nomeFantasia = nomeFantasia;
            this.razaoSocial = razaoSocial;
            this.cnpj = cnpj;
            this.endereco = endereco;
        }

        void apresentarse() {
            System.out.println("Loja: " + nomeFantasia);
            System.out.println("CNPJ: " + cnpj);
            endereco.apresentarLogradouro();
        }

        void contarClientes() {
            System.out.println("Quantidade de clientes: " + clientes.size());
        }

        void contarVendedores() {
            System.out.println("Quantidade de vendedores: " + vendedores.size());
        }
    }

    static class Item {
        int id;
        String nome, tipo;
        double valor;

        Item(int id, String nome, String tipo, double valor) {
            this.id = id;
            this.nome = nome;
            this.tipo = tipo;
            this.valor = valor;
        }

        void gerarDescricao() {
            System.out.println(id + " - " + nome + " (" + tipo + ") R$ " + valor);
        }
    }

    static class Pedido {
        int id;
        Date dataCriacao;
        Date dataPagamento;
        Date dataVencimentoReserva;
        Cliente cliente;
        Vendedor vendedor;
        Loja loja;
        ArrayList<Item> itens;

        Pedido(int id, Cliente cliente, Vendedor vendedor, Loja loja, ArrayList<Item> itens) {
            this.id = id;
            this.cliente = cliente;
            this.vendedor = vendedor;
            this.loja = loja;
            this.itens = itens;

            dataCriacao = new Date();

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 2);
            dataVencimentoReserva = cal.getTime();
        }

        double calcularValorTotal() {
            double total = 0;
            for (Item i : itens) total += i.valor;
            return total;
        }

        void gerarDescricaoVenda() {
            System.out.println("Data: " + dataCriacao);
            System.out.println("Total: R$ " + calcularValorTotal());
        }
    }

    static class ProcessaPedido {

        Pedido processar(int id, Cliente cliente, Vendedor vendedor, Loja loja, ArrayList<Item> itens) {
            Pedido p = new Pedido(id, cliente, vendedor, loja, itens);

            if (confirmarPagamento(p)) {
                p.dataPagamento = new Date();
                System.out.println("Pagamento confirmado!");
            } else {
                System.out.println("Reserva vencida!");
            }

            return p;
        }

        private boolean confirmarPagamento(Pedido p) {
            return new Date().before(p.dataVencimentoReserva);
        }
    }

    static ArrayList<Venda> vendas = new ArrayList<>();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Endereco end = new Endereco("PR", "Cascavel", "Centro", "123", "Casa");

        Loja loja = new Loja("My Plant", "My Plant LTDA", "123456789", end);

        Vendedor vendedor = new Vendedor("Joao", 30, "My Plant", end, 2000);
        Gerente gerente = new Gerente("Carlos", 40, "My Plant", end, 5000);
        Cliente cliente = new Cliente("Maria", 25, end);

        loja.vendedores.add(vendedor);
        loja.clientes.add(cliente);

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
                    loja.apresentarse();
                    loja.contarClientes();
                    loja.contarVendedores();
                    break;
                case 5:
                    vendedor.apresentarse();
                    System.out.println("Media: " + vendedor.calcularMedia());
                    System.out.println("Bonus: " + vendedor.calcularBonus());
                    gerente.apresentarse();
                    System.out.println("Bonus gerente: " + gerente.calcularBonus());
                    break;
                case 6:
                    cliente.apresentarse();
                    break;
                case 7:
                    criarPedidoFake(cliente, vendedor, loja);
                    break;
                case 8:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opcao invalida");
            }

        } while (opcao != 8);

        sc.close();
    }

    static void menu() {
        System.out.println("1 - Nova venda");
        System.out.println("2 - Calcular troco");
        System.out.println("3 - Listar vendas");
        System.out.println("4 - Dados da loja");
        System.out.println("5 - Vendedores");
        System.out.println("6 - Clientes");
        System.out.println("7 - Criar pedido");
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

        vendas.add(new Venda(qtd, preco, total, desconto, new Date()));
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

    static void criarPedidoFake(Cliente cliente, Vendedor vendedor, Loja loja) {
        ArrayList<Item> itens = new ArrayList<>();
        itens.add(new Item(1, "Planta", "Natural", 50));
        itens.add(new Item(2, "Vaso", "Decoracao", 30));

        ProcessaPedido proc = new ProcessaPedido();
        Pedido p = proc.processar(1, cliente, vendedor, loja, itens);

        p.gerarDescricaoVenda();
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