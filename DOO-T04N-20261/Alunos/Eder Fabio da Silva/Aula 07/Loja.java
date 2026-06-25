
import java.util.ArrayList;


public class Loja {
    String nomeFantasia;
    String razaoSocial; 
    String cnpj;
    public Endereco enderecoLoja;

    // Listas para armazenar vendedores, clientes, gerentes e lojas
    public static ArrayList<Vendedor> vendedores = new ArrayList<>();
    public static ArrayList<Cliente> clientes = new ArrayList<>();
    public static ArrayList<Gerente> gerentes = new ArrayList<>();
    public static ArrayList<Loja> lojas = new ArrayList<>();
    public static ArrayList<Item> itens = new ArrayList<>();

   
     // Método para apresentar a loja
    public static void apresentarLojas() {
        if (lojas.isEmpty()) {
            System.out.println("Nenhuma loja cadastrada.");
            return;
        }
       for (Loja loja : lojas) {
            loja.exibirDadosLoja();
        
        }
    }
// Método para exibir os dados da loja
    public void exibirDadosLoja() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        if (enderecoLoja != null) {
            enderecoLoja.apresentarLogradouro();
        } else {
            System.out.println("Endereço não cadastrado.");
        }
        System.out.println("-------------------------");
    }

    // Método para contar clientes
    public static void  contarClientes() {
        System.out.println("Total de clientes: " + clientes.size());
    }
    
    // Método para contar vendedores
    public static void  contarVendedores() {
        System.out.println("Total de vendedores: " + vendedores.size());
    }

    // Método para apresentar vendedores
    public static void apresentarVendedores() {
        if (vendedores.isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado.");
            return;
        }
        for (Vendedor v : vendedores) {
            v.exibirDados();
        }
    }

    // Método para calcular média salarial dos vendedores
    public static void calcularMediaVendedores() {
        if (vendedores.isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado.");
            return;
        }
        for (Vendedor v : vendedores) {
            double media = v.calcularMedia();
            System.out.printf("A média salarial do vendedor %s é: R$ %.2f\n", v.nome, media);
        }
    }

    // Método para calcular bônus dos vendedores
    public static void calcularBonusVendedores() {
        for (Vendedor v : vendedores) {
            v.calcularBonus(); // Chama o método polimórfico
        }
    }

    // Método para contar gerentes
    public static void contarGerentes() {
        System.out.println("Total de gerentes: " + gerentes.size());
    }

    // Método para apresentar gerentes
    public static void apresentarGerentes() {
        if (gerentes.isEmpty()) {
            System.out.println("Nenhum gerente cadastrado.");
            return;
        }
        for (Gerente g : gerentes) {
            g.exibirDadosGerente();
        }
    }

    // Método para calcular média salarial dos gerentes
    public static void calcularMediaGerentes() {
        if (gerentes.isEmpty()) {
            System.out.println("Nenhum gerente cadastrado.");
            return;
        }
        for (Gerente g : gerentes) {
            double media = g.calcularMedia();
            System.out.printf("A média salarial do gerente %s é: R$ %.2f\n", g.nome, media);
        }
    }

    // Método para calcular bônus dos gerentes
    public static void calcularBonusGerentes() {
        for (Gerente g : gerentes) {
            g.calcularBonus(); // Chama o método polimórfico
        }
    }
}
