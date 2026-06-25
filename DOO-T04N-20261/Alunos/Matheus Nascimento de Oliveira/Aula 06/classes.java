import java.util.ArrayList;

class Vendedor {
    String nome;
    int idade;
    String loja;
    @SuppressWarnings("unused")
    String cidade;
    @SuppressWarnings("unused")
    String bairro;
    @SuppressWarnings("unused")
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
        this.salarioRecebido = new double[]{1800.0, 2100.0, 1950.0};
    }

    void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
    }

    double calcularMedia() {
        double soma = 0;
        for (int i = 0; i < salarioRecebido.length; i++) {
            soma += salarioRecebido[i];
        }
        double media = soma / salarioRecebido.length;
        return media;
    }

    double calcularBonus() {
        return salarioBase * 0.2;
    }
}

class Cliente {
    String nome;
    int idade;
    @SuppressWarnings("unused")
    String cidade;
    @SuppressWarnings("unused")
    String bairro;
    @SuppressWarnings("unused")
    String rua;

    Cliente(String nome, int idade, String cidade, String bairro, String rua) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
    }
}

class Loja {
    String nomeFantasia;
    @SuppressWarnings("unused")
    String razaoSocial;
    String cnpj;
    String cidade;
    String bairro;
    String rua;
    ArrayList<Vendedor> vendedores;
    ArrayList<Cliente> clientes;

    @SuppressWarnings("Convert2Diamond")
    Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.vendedores = new ArrayList<Vendedor>();
        this.clientes = new ArrayList<Cliente>();
    }

    int contarClientes() {
        return clientes.size();
    }

    int contarVendedores() {
        return vendedores.size();
    }

    void apresentarse() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + bairro + ", " + cidade);
    }
}

public class classes {
    public static void main(String[] args) {

        Loja loja = new Loja("My Plant", "My Plant Comercio de Plantas LTDA", "12.345.678/0001-99", "São Paulo", "Centro", "Rua das Flores, 100");

        Vendedor v1 = new Vendedor("Carlos", 28, "My Plant", "São Paulo", "Centro", "Rua das Flores, 100", 2000.0);
        Vendedor v2 = new Vendedor("Ana", 35, "My Plant", "São Paulo", "Centro", "Rua das Flores, 100", 2200.0);

        Cliente c1 = new Cliente("João", 45, "São Paulo", "Jardins", "Rua Augusta, 50");
        Cliente c2 = new Cliente("Maria", 30, "São Paulo", "Pinheiros", "Rua dos Pinheiros, 20");
        Cliente c3 = new Cliente("Pedro", 22, "São Paulo", "Centro", "Av. Paulista, 1000");

        loja.vendedores.add(v1);
        loja.vendedores.add(v2);
        loja.clientes.add(c1);
        loja.clientes.add(c2);
        loja.clientes.add(c3);

        System.out.println("=== DADOS DA LOJA ===");
        loja.apresentarse();
        System.out.println("Total de vendedores: " + loja.contarVendedores());
        System.out.println("Total de clientes: " + loja.contarClientes());

        System.out.println("\n=== DADOS DO VENDEDOR ===");
        v1.apresentarse();
        System.out.println("Media salarial: " + v1.calcularMedia());
        System.out.println("Bonus: " + v1.calcularBonus());

        System.out.println("\n=== DADOS DO CLIENTE ===");
        c1.apresentarse();
    }
}