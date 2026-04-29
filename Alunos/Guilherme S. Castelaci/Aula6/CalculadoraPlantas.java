class Vendedor {
    String nome;
    int idade;
    String loja;
    String cidade;
    String bairro;
    String rua;
    double salarioBase;
    double[] salarioRecebido;

    public Vendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;

        this.salarioRecebido = new double[]{2500, 2700, 2600};
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
    }

    public double calcularMedia() {
        double soma = 0;
        for (double s : salarioRecebido) {
            soma += s;
        }
        return soma / salarioRecebido.length;
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}

class Cliente {
    String nome;
    int idade;
    String cidade;
    String bairro;
    String rua;

    public Cliente(String nome, int idade, String cidade, String bairro, String rua) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
    }
}

public class CalculadoraPlantas {
    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    String cidade;
    String bairro;
    String rua;

    Vendedor[] vendedores;
    Cliente[] clientes;

    public CalculadoraPlantas(String nomeFantasia, String razaoSocial, String cnpj,
                              String cidade, String bairro, String rua,
                              Vendedor[] vendedores, Cliente[] clientes) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.vendedores = vendedores;
        this.clientes = clientes;
    }

    public int contarClientes() {
        return clientes.length;
    }

    public int contarVendedores() {
        return vendedores.length;
    }

    public void apresentarse() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + bairro + ", " + cidade);
    }

    public static void main(String[] args) {

        Vendedor v1 = new Vendedor("Ana", 30, "My Plant", "Cascavel", "Centro", "Rua A", 3000);
        Vendedor v2 = new Vendedor("Carlos", 25, "My Plant", "Cascavel", "Centro", "Rua B", 3200);

        Cliente c1 = new Cliente("Maria", 22, "Cascavel", "Centro", "Rua C");
        Cliente c2 = new Cliente("João", 28, "Cascavel", "Centro", "Rua D");

        CalculadoraPlantas loja = new CalculadoraPlantas(
                "My Plant",
                "My Plant LTDA",
                "12.345.678/0001-99",
                "Cascavel",
                "Centro",
                "Av Brasil",
                new Vendedor[]{v1, v2},
                new Cliente[]{c1, c2}
        );

        loja.apresentarse();

        System.out.println("\nClientes: " + loja.contarClientes());
        System.out.println("Vendedores: " + loja.contarVendedores());

        System.out.println("\nMédia salário v1: " + v1.calcularMedia());
        System.out.println("Bônus v1: " + v1.calcularBonus());
    }
}