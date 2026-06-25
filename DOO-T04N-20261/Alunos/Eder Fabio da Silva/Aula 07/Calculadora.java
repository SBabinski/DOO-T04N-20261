
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Calculadora {

    // Formato para exibir a data no relatório de vendas
    private static SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
    // Scanner para ler a entrada do usuário
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Gerador.gerarLoja();
        Gerador.gerarVendedor();
        Gerador.gerarCliente();
        Gerador.gerarGerente();
        Gerador.gerarItem();

        // Exibir o menu da calculadora
        Controle.mostrarMenu();
        scanner.close();

    }

// Método para calcular o preço total de uma venda, permitindo ao usuário escolher o item, 
// a quantidade, o cliente, o vendedor e a loja, e registrando a venda no sistema.
    public static void calcularPrecoTotal() {
        // Exibe os itens disponíveis
        System.out.println("=== Itens disponíveis ===");
        for (int i = 0; i < Loja.itens.size(); i++) {
            Item item = Loja.itens.get(i);
            System.out.println(i + " - " + item.getNome() + " | R$ " + item.getValor());
        }

        // Usuário escolhe o item
        System.out.println("Escolha o número do item:");
        int indice = scanner.nextInt();

        System.out.println("Digite a quantidade:");
        int quantidade = scanner.nextInt();

        // Exibe os clientes disponíveis
        System.out.println("=== Clientes ===");
        for (int i = 0; i < Loja.clientes.size(); i++) {
            System.out.println(i + " - " + Loja.clientes.get(i).nome);
        }
        System.out.println("Escolha o número do cliente:");
        int indiceCliente = scanner.nextInt();

        // Exibe os vendedores disponíveis
        System.out.println("=== Vendedores ===");
        for (int i = 0; i < Loja.vendedores.size(); i++) {
            System.out.println(i + " - " + Loja.vendedores.get(i).nome);
        }
        System.out.println("Escolha o número do vendedor:");
        int indiceVendedor = scanner.nextInt();

        // Exibe as lojas disponíveis
        System.out.println("=== Lojas ===");
        for (int i = 0; i < Loja.lojas.size(); i++) {
            System.out.println(i + " - " + Loja.lojas.get(i).nomeFantasia);
        }
        System.out.println("Escolha o número da loja:");
        int indiceLoja = scanner.nextInt();

        // Monta a lista com o item escolhido
        ArrayList<Item> itensDaVenda = new ArrayList<>();
        itensDaVenda.add(Loja.itens.get(indice));

        // Processa e cria o pedido
        ProcessaPedido processa = new ProcessaPedido();
        Vendas venda = processa.processar(
                new Date(),
                Loja.clientes.get(indiceCliente),
                Loja.vendedores.get(indiceVendedor),
                Loja.lojas.get(indiceLoja),
                itensDaVenda,
                quantidade
        );
        processa.testarConfirmacao(venda);
    }
    // Método para calcular o troco, solicitando ao usuário o valor pago e o valor total da 
    // compra, e exibindo o troco ou a mensagem de valor insuficiente.
    public static void calcularTroco() {
        System.out.println("Digite o valor recebido R$ ");
        double valorPago = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer do scanner

        System.out.println("Digite o valor total R$ ");
        double valorTotal = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer do scanner

        double troco = valorPago - valorTotal;
        if (troco < 0) {
            System.out.printf("Valor pago e insuficiente. Faltam R$ %.2f\n", -troco);
        } else {
            System.out.printf("O troco e R$ %.2f\n", troco);
        }

    }

    // Método para exibir o relatório de vendas
    public static void relatorioVendas() {
        if (ProcessaPedido.registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            System.out.println("Relatorio de Vendas:");
            for (Vendas venda : ProcessaPedido.registroVendas) {
                System.out.printf("Quantidade: %d, Preço Unitário: R$ %.2f, Valor Total: R$ %.2f\n",
                        venda.getQuantidade(), venda.getPrecoUnitario(), venda.getValorTotal());
            }
        }
    }

    // Método para exibir o relatório de vendas por data
    public static void relatorioBuscaPorData() {
        System.out.println("Digite a data para busca (dd/MM/yyyy):");
        String dataBuscaString = scanner.nextLine();

        try {
            Date dataFormatada = formatador.parse(dataBuscaString);
            System.out.println("\n ------VENDAS NA DATA " + dataBuscaString);

            boolean vendaEncontrada = false;
            double fatumentoDia = 0.0;

            for (Vendas venda : ProcessaPedido.registroVendas) {
                String dataDaVendaFormatada = formatador.format(venda.getDataVenda());

                if (dataDaVendaFormatada.equals(dataBuscaString)) {
                    System.out.printf("Venda encontrada: Quantidade: %d, Preco Unitario: R$ %.2f, Valor Total: R$ %.2f\n",
                            venda.getQuantidade(), venda.getPrecoUnitario(), venda.getValorTotal());

                    fatumentoDia += venda.getValorTotal();
                    vendaEncontrada = true;
                }
            }

            if (!vendaEncontrada) {
                System.out.println("Nenhuma venda encontrada para a data: " + dataBuscaString);
            } else {
                System.out.printf("Faturamento total do dia %s: R$ %.2f\n", dataBuscaString, fatumentoDia);
            }

        } catch (Exception e) {
            System.out.println("Data inválida. Por favor, use o formato dd/MM/yyyy.");
        }
    }
}
