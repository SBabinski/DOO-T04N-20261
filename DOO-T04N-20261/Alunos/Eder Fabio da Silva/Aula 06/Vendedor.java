


public class Vendedor {
    String nome;
    int idade;
    String loja;
    String cidade;
    String bairro;
    String rua;
    double salarioBase;
    double salarioRecebido[] = {2500, 3000, 3500};


    public static void menuVendedor() {
        int opcaoVendedor = 0;
        do {
            System.out.println("----MENU DO VENDEDOR----");
            System.out.println("[1] Contar Vendedores");
            System.out.println("[2] Apresentar Vendedores");
            System.out.println("[3] Media Salarial dos Vendedores");
            System.out.println("[4] Bonus do Vendedor");
            System.out.println("[0] Menu da Loja");
            opcaoVendedor = Loja.scanner.nextInt();
            Loja.scanner.nextLine(); // Limpar o buffer do scanner
            validarEscolhaVendedor(opcaoVendedor);
        } while (opcaoVendedor != 0);
    }

    // Método para validar a escolha do vendedor
    public static void validarEscolhaVendedor(int opcaoVendedor) {
        switch (opcaoVendedor) {
            case 1:
                Loja.contarVendedores();
                break;
            case 2:
                apresentarVendedores();
                break;
            case 3:
                calcularMedia();
                break;
            case 4:
                calcularBonus();
                break;
            case 0:
                System.out.println("Voltando ao Menu da Loja...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }



    // Método para apresentar vendedor
    public static void apresentarVendedores() {
        if (Loja.vendedores.isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado.");
            return;
        }
        for (Vendedor v : Loja.vendedores) {
            v.exibirDadosVendedor();
        }
        
    }

    public void exibirDadosVendedor() {
        System.out.println("Nome do vendedor: " + nome);
        System.out.println("Idade do vendedor: " + idade);
        System.out.println("Loja do vendedor: " + loja);
        System.out.println("-----------------------------");
    }

    // Método para calcular bonus
    public static void calcularBonus() {
        for (Vendedor v : Loja.vendedores) {
            double bonus = v.salarioBase * 0.2;
            System.out.printf("O bonus do vendedor %s é R$ %.2f\n", v.nome, bonus);
        }
    }
    
    // Método para calcular a média dos salários
    public static void calcularMedia() {
        if (Loja.vendedores.isEmpty()) {
            System.out.println("Nenhum vendedor cadastrado.");
            return;
        }
        for (Vendedor v : Loja.vendedores) {
            double soma = 0;
            for (double s : v.salarioRecebido) {
                soma += s;
            }
            double media = soma / v.salarioRecebido.length;
            System.out.printf("A média salarial do vendedor %s é: R$ %.2f\n", v.nome, media);
        }
       
    }
}

