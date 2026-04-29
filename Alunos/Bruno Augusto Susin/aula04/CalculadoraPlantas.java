import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class CalculadoraPlantas {
    public static void main(String[] args) {
        List<Vendas> vendas = new ArrayList<>();
        Scanner scan = new Scanner(System.in);

        int opcao = 0;

        while (opcao != 6){
            System.out.println("1 - Registrar venda");
            System.out.println("2 - Listar vendas");
            System.out.println("3 - Calcular troco");
            System.out.println("4 - Buscar vendas por dia");
            System.out.println("5 - Buscar vendas por mês");
            System.out.println("6 - Sair");

            opcao = scan.nextInt();
            scan.nextLine();

            if (opcao == 1){

                System.out.println("Digite a data (dd/MM/yyyy): ");
                String dataStr = scan.nextLine();

                LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                System.out.println("Digite a quantidade de plantas!");
                int quantidade = scan.nextInt();

                System.out.println("Digite o valor das plantas!");
                double valor = scan.nextDouble();
                scan.nextLine();

                System.out.println("Calculando DESCONTO!");
                double subtotal = quantidade * valor;
                double desconto = 0;

                if (quantidade > 10) {
                    desconto = subtotal * 0.05;
                }

                double total = subtotal - desconto;

                Vendas venda = new Vendas(quantidade, total, desconto, data);
                vendas.add(venda);

                System.out.println("Venda registrada com sucesso!");
                System.out.println("Desconto aplicado: R$ " + desconto);
                System.out.println("Total: R$ " + total);

            }else if (opcao == 2){

                if (vendas.isEmpty()) {
                    System.out.println("Nenhuma venda registrada.");
                } else {
                    for (Vendas v : vendas) {
                        System.out.println("----------------------------");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        System.out.println("Data: " + v.data.format(formatter));
                        System.out.println("Quantidade: " + v.quantidade);
                        System.out.println("Valor final: R$ " + v.valorFinal);
                        System.out.println("Desconto: R$ " + v.desconto);
                        System.out.println("----------------------------");
                    }
                }

            }else if (opcao == 3){

                System.out.println("Digite o valor pago pelo cliente:");
                double pago = scan.nextDouble();
                scan.nextLine();

                if (vendas.isEmpty()) {
                    System.out.println("Nenhuma venda registrada.");
                } else {
                    Vendas ultima = vendas.get(vendas.size() - 1);
                    double troco = pago - ultima.valorFinal;
                    System.out.println("Troco: R$ " + troco);
                }

            }else if (opcao == 4){

                System.out.println("Digite a data (dd/MM/yyyy): ");
                String dataStr = scan.nextLine();
                LocalDate dataBusca = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                int total = 0;

                for (Vendas v : vendas) {
                    if (v.data.equals(dataBusca)) {
                        total += v.quantidade;
                    }
                }

                if (total == 0) {
                    System.out.println("Nenhuma venda encontrada nesse dia.");
                } else {
                    System.out.println("Total de plantas vendidas no dia: " + total);
                }

            } else if (opcao == 5){

                System.out.println("Digite o mês e ano (MM/yyyy): ");
                String mesStr = scan.nextLine();

                int mes = Integer.parseInt(mesStr.substring(0, 2));
                int ano = Integer.parseInt(mesStr.substring(3));

                int totalMes = 0;

                for (Vendas v : vendas) {
                    if (v.data.getMonthValue() == mes &&
                            v.data.getYear() == ano) {

                        totalMes += v.quantidade;
                    }
                }

                if (totalMes == 0) {
                    System.out.println("Nenhuma venda encontrada nesse mês.");
                } else {
                    System.out.println("Total de plantas vendidas no mês: " + totalMes);
                }

            } else if (opcao == 6){
                System.out.println("Saindo do sistema...");
            }else{
                System.out.println("Opção invalida");
            }
        }
    }
}
