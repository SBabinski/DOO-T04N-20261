import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Aula04 {
   
    static class Venda {
        private double quantidade;
        private double precoUnitario;
        private double desconto;
        private double total;
        private LocalDate data;

        public Venda(double quantidade, double precoUnitario, double desconto, double total, LocalDate data) {
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
            this.desconto = desconto;
            this.total = total;
            this.data = data;
        }

     
        public double getQuantidade() {
            return quantidade;
        }

        public double getPrecoUnitario() {
            return precoUnitario;
        }

        public double getDesconto() {
            return desconto;
        }

        public double getTotal() {
            return total;
        }

        public LocalDate getData() {
            return data;
        }
    }

    
    private static List<Venda> registroDeVendas = new ArrayList<>();
  
    private static double ultimoTotalCalculado = 0.0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            System.out.print("Escolha uma op??o: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    calcularPrecoTotal(scanner);
                    break;
                case 2:
                    calcularTroco(scanner);
                    break;
                case 3:
                    exibirRegistroDeVendas();
                    break;
                case 4:
                    calcularQuantidadeTotalDiaMenu(scanner);
                    break;
                case 5:
                    buscarQuantidadeTotalMesDiaMenu(scanner);
                    break;
                case 6:
                    System.out.println("Encerrando a calculadora. Obrigada pela visita!");
                    break;
                default:
                    System.out.println("Op??o inv?lida. Tente novamente.");
            }
        } while (opcao != 6);

        scanner.close();
    }

   
    private static void exibirMenu() {
        System.out.println("\n== Loja de Plantas da Dona Gabrielinha ==");
        System.out.println("[1] - Calcular Pre?o Total");
        System.out.println("[2] - Calcular Troco");
        System.out.println("[3] - Ver Registro de Vendas");
        System.out.println("[4] - Calcular Quantidade Total de Vendas em um Dia");
        System.out.println("[5] - Buscar Quantidade Total pelo M?s e Dia");
        System.out.println("[6] - Sair");
    }

    
    private static void calcularPrecoTotal(Scanner scanner) {
        System.out.print("Quantidade vendida: ");
        double quantidade = scanner.nextDouble();
        System.out.print("Pre?o unit?rio: R$ ");
        double precoUnitario = scanner.nextDouble();

        double totalSemDesconto = quantidade * precoUnitario;
        double desconto = 0.0;
        double totalComDesconto = totalSemDesconto;

        if (quantidade > 10) {
            desconto = totalSemDesconto * 0.05;
            totalComDesconto = totalSemDesconto - desconto;
            System.out.printf("Desconto especial aplicado: R$ %.2f\n", desconto);
        }

        System.out.printf("Pre?o total da venda: R$ %.2f\n", totalComDesconto);

        Venda venda = new Venda(quantidade, precoUnitario, desconto, totalComDesconto, LocalDate.now());
        registroDeVendas.add(venda);
        ultimoTotalCalculado = totalComDesconto;
    }

  
    private static void calcularTroco(Scanner scanner) {
        System.out.print("Valor recebido do cliente: R$ ");
        double valorRecebido = scanner.nextDouble();

        double valorTotalCompra = ultimoTotalCalculado;
        if (valorTotalCompra == 0.0) {
            System.out.print("Valor total da compra: R$ ");
            valorTotalCompra = scanner.nextDouble();
        }

        double troco = valorRecebido - valorTotalCompra;
        System.out.printf("Troco a ser devolvido: R$ %.2f\n", troco);
    }

    
    private static void exibirRegistroDeVendas() {
        if (registroDeVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada ainda");
        } else {
            System.out.println("\n=== Registro de Vendas ===");
            for (int i = 0; i < registroDeVendas.size(); i++) {
                Venda venda = registroDeVendas.get(i);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataFormatada = venda.getData().format(formatter);
                System.out.printf("Venda %d: Data: %s, Quantidade: %.0f, Pre?o Unit?rio: R$ %.2f, Desconto: R$ %.2f, Total: R$ %.2f\n",
                        i + 1, dataFormatada, venda.getQuantidade(), venda.getPrecoUnitario(), venda.getDesconto(), venda.getTotal());
            }
        }
    }

    private static double calcularQuantidadeTotalDia(int ano, int mes, int dia) {
        double totalQuantidade = 0.0;
        for (Venda venda : registroDeVendas) {
            if (venda.getData().getYear() == ano && venda.getData().getMonthValue() == mes && venda.getData().getDayOfMonth() == dia) {
                totalQuantidade += venda.getQuantidade();
            }
        }
        return totalQuantidade;
    }

    private static double buscarQuantidadeTotalMesDia(int mes, int dia) {
        int anoAtual = LocalDate.now().getYear();
        return calcularQuantidadeTotalDia(anoAtual, mes, dia);
    }

    private static void calcularQuantidadeTotalDiaMenu(Scanner scanner) {
        System.out.print("Ano: ");
        int ano = scanner.nextInt();
        System.out.print("M?s: ");
        int mes = scanner.nextInt();
        System.out.print("Dia: ");
        int dia = scanner.nextInt();

        double total = calcularQuantidadeTotalDia(ano, mes, dia);
        System.out.printf("Quantidade total de vendas em %02d/%02d/%d: %.0f\n", dia, mes, ano, total);
    }

    private static void buscarQuantidadeTotalMesDiaMenu(Scanner scanner) {
        System.out.print("M?s: ");
        int mes = scanner.nextInt();
        System.out.print("Dia: ");
        int dia = scanner.nextInt();

        double total = buscarQuantidadeTotalMesDia(mes, dia);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = LocalDate.of(LocalDate.now().getYear(), mes, dia).format(formatter);
        System.out.printf("Quantidade total de vendas em %s: %.0f\n", dataFormatada, total);
    }
}