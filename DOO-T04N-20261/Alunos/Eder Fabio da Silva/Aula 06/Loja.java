
import java.util.ArrayList;
import java.util.Scanner;

public class Loja {
    String nomeFantasia;
    String razaoSocial; 
    String cnpj;
    String cidade;
    String bairro;  
    String rua;
// Listas para armazenar vendedores, clientes e lojas
    public static ArrayList<Vendedor> vendedores = new ArrayList<>();
    public static ArrayList<Cliente> clientes = new ArrayList<>();
    public static ArrayList<Loja> lojas = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);


   
// Método para exibir o menu da loja
    public static void menuLoja() {
       int opcaoLoja = 0;
        do {
            System.out.println("------MENU DA LOJA------");
            System.out.println("[1] Contar Clientes");
            System.out.println("[2] Apresentar Clientes");
            System.out.println("[3] Menu do Vendedores");
            System.out.println("[4] Apresentar Loja");
            System.out.println("[0] Menu da Calculadora");            
            opcaoLoja = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
            validarEscolhaLoja(opcaoLoja);
        } while (opcaoLoja != 0);
        }
        
    // Método para validar a escolha da loja
    public static void validarEscolhaLoja(int opcaoLoja) {
        switch (opcaoLoja) {
            case 1:
                contarClientes();
                break;
            case 2:
                Cliente.apresentarCliente();
                break;            
            case 3:
                Vendedor.menuVendedor();
                break;
            case 4:
                apresentarLojas();
                break;
            
            case 0:
                System.out.println("Voltando ao Menu da Calculadora...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

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
        System.out.println("Cidade: " + cidade);
        System.out.println("Bairro: " + bairro);
        System.out.println("Rua: " + rua);
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
}
