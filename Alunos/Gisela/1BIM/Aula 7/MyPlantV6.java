//Main
import java.util.ArrayList;
import java.util.Scanner;

public class MyPlantV6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Loja loja = new Loja();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        ProcessaPedido processador = new ProcessaPedido();

        int opcao;
        do {
            System.out.println("\n===== MENU MY PLANT ==");
            System.out.println("1 - Cadastrar Loja");
            System.out.println("2 - Cadastrar Cliente");
            System.out.println("3 - Cadastrar Vendedor");
            System.out.println("4 - Criar Pedido (dados fakes)");
            System.out.println("5 - Listar Pedidos");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao) {
                case 1:
                    Endereco endLoja = new Endereco();
                    System.out.print("Nome da Loja: ");
                    loja.setNomeFantasia(sc.nextLine());
                    System.out.print("Razão Social: ");
                    loja.setRazaoSocial(sc.nextLine());
                    System.out.print("CNPJ: ");
                    loja.setCnpj(sc.nextLine());
                    System.out.print("Cidade: ");
                    endLoja.setCidade(sc.nextLine());
                    System.out.print("Estado: ");
                    endLoja.setEstado(sc.nextLine());
                    loja.setEndereco(endLoja);
                    System.out.println("Loja cadastrada com sucesso!");
                    break;

                case 2:
                    Cliente cliente = new Cliente();
                    Endereco endCli = new Endereco();
                    System.out.print("Nome do Cliente: ");
                    cliente.setNome(sc.nextLine());
                    System.out.print("Idade: ");
                    cliente.setIdade(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Cidade: ");
                    endCli.setCidade(sc.nextLine());
                    cliente.setEndereco(endCli);
                    loja.adicionaCliente(cliente);
                    System.out.println("Cliente cadastrado!");
                    break;

                case 3:
                    Vendedor vendedor = new Vendedor();
                    Endereco endVend = new Endereco();
                    System.out.print("Nome do Vendedor: ");
                    vendedor.setNome(sc.nextLine());
                    System.out.print("Idade: ");
                    vendedor.setIdade(sc.nextInt());
                    sc.nextLine();
                    System.out.print("Salário Base: ");
                    vendedor.setSalarioBase(sc.nextDouble());
                    sc.nextLine();
                    System.out.print("Cidade: ");
                    endVend.setCidade(sc.nextLine());
                    vendedor.setEndereco(endVend);
                    vendedor.setLoja(loja.getNomeFantasia());
                    loja.adicionaVendedor(vendedor);
                    System.out.println("Vendedor cadastrado!");
                    break;

                case 4:
                    if(loja.contarClientes() == 0 || loja.contarVendedores() == 0) {
                        System.out.println("Cadastre ao menos 1 cliente e 1 vendedor antes de criar pedido!");
                    } else {
                        Cliente cli = loja.contarClientes() > 0 ? loja.getClientes().get(0) : null;
                        Vendedor vend = loja.contarVendedores() > 0 ? loja.getVendedores().get(0) : null;
                        Pedido pedido = processador.processar(cli, vend, loja);
                        pedidos.add(pedido);
                        pedido.gerarDescricaoVenda();
                        processador.testar(pedido);
                    }
                    break;

                case 5:
                    for(Pedido p : pedidos) {
                        p.gerarDescricaoVenda();
                    }
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while(opcao != 0);

        sc.close();
    }
}