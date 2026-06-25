import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Venda {
    int quantidade;
    double valorVenda;
    double desconto;
    LocalDate data;

    public Venda(int quantidade, double valorVenda, double desconto, LocalDate data) {
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
        this.desconto = desconto;
        this.data = data;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "\r\nData: " + data.format(fmt) + "\r\nQuantidade: " + quantidade + "\r\nValor de Venda: " + valorVenda
                + "\r\nDesconto Aplicado: " + desconto + "\r\n";
    }
}

public class Calculadora {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int alternativa = 0;
        List<Venda> vendas = new ArrayList<>();
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (alternativa != 6) {
            System.out.println("    [1] - Calcular Preço Total\r\n" +
                    "    [2] - Calcular Troco\r\n" +
                    "    [3] - Ver Registro de Vendas\r\n" +
                    "    [4] - Total de Vendas por Dia\r\n" +
                    "    [5] - Total de Vendas por Mês\r\n" +
                    "    [6] - Sair");
            alternativa = sc.nextInt();

            switch (alternativa) {
                case 1:
                    System.out.println("Digite o preço do produto: ");
                    double valor = sc.nextDouble();
                    System.out.println("Digite a quantidade: ");
                    int quantidade = sc.nextInt();
                    LocalDate dataVenda = LocalDate.now();
                    double total = valor * quantidade;
                    double desconto = 0;
                    double valorComDesconto = total;

                    if (quantidade > 10) {
                        desconto = total * 0.05;
                        valorComDesconto = total - desconto;
                        System.out.println("Você ganhou um desconto de 5%! ");
                    }

                    System.out.println("Data da venda: " + dataVenda.format(dataFormatter));
                    System.out.println("Quantidade vendida: " + quantidade);
                    System.out.println("Valor bruto: " + total);
                    System.out.println("Desconto aplicado: " + desconto);
                    System.out.println("Valor final da venda: " + valorComDesconto);
                    vendas.add(new Venda(quantidade, valorComDesconto, desconto, dataVenda));
                    break;

                case 2:
                    System.out.println("Digite o valor pago: ");
                    double valorPago = sc.nextDouble();
                    System.out.println("Digite o valor total: ");
                    double valorTotal = sc.nextDouble();
                    double troco = valorPago - valorTotal;
                    System.out.println("O troco é: " + troco);
                    break;

                case 3:
                    System.out.println("\r\nRegistro de Vendas:\r\n");
                    if (vendas.isEmpty()) {
                        System.out.println("Nenhuma venda registrada.");
                    } else {
                        for (int i = 0; i < vendas.size(); i++) {
                            System.out.println("Venda " + (i + 1) + ": " + vendas.get(i));
                        }
                    }
                    break;

                case 4:
                    System.out.println("Digite a data para consulta (dd/MM/yyyy): ");
                    String diaConsulta = sc.next();
                    try {
                        LocalDate queryDate = LocalDate.parse(diaConsulta, dataFormatter);
                        int totalQuantidade = 0;
                        double totalValor = 0;
                        double totalDesconto = 0;

                        for (Venda v : vendas) {
                            if (v.getData().equals(queryDate)) {
                                totalQuantidade += v.quantidade;
                                totalValor += v.valorVenda;
                                totalDesconto += v.desconto;
                            }
                        }

                        System.out.println("\r\nRelatório de " + queryDate.format(dataFormatter));
                        System.out.println("Total de itens vendidos: " + totalQuantidade);
                        System.out.println("Total faturado: " + totalValor);
                        System.out.println("Total de descontos: " + totalDesconto);
                    } catch (DateTimeParseException e) {
                        System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
                    }
                    break;

                case 5:
                    System.out.println("Digite o mês/ano para consulta (MM/yyyy): ");
                    String mesConsulta = sc.next();
                    try {
                        YearMonth queryMonth = YearMonth.parse(mesConsulta, DateTimeFormatter.ofPattern("MM/yyyy"));
                        int totalQuantidadeMes = 0;
                        double totalValorMes = 0;
                        double totalDescontoMes = 0;

                        for (Venda v : vendas) {
                            YearMonth vendaMonth = YearMonth.from(v.getData());
                            if (vendaMonth.equals(queryMonth)) {
                                totalQuantidadeMes += v.quantidade;
                                totalValorMes += v.valorVenda;
                                totalDescontoMes += v.desconto;
                            }
                        }

                        System.out.println("\r\nRelatório de " + queryMonth.toString().replace('-', '/'));
                        System.out.println("Total de itens vendidos no mês: " + totalQuantidadeMes);
                        System.out.println("Total faturado no mês: " + totalValorMes);
                        System.out.println("Total de descontos no mês: " + totalDescontoMes);
                    } catch (DateTimeParseException e) {
                        System.out.println("Mês inválido. Use o formato MM/yyyy.");
                    }
                    break;

                case 6:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        }

        sc.close();
    }
}