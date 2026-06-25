import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculadora {

    private static List<Venda> registroVendas = new ArrayList<>();

    private static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int escolha;

        do {
            System.out.println("\nMENU LOJA");
            System.out.println("1- Calcular Preço");
            System.out.println("2- Calcular Troco");
            System.out.println("3- Registro de Vendas");
            System.out.println("4- Buscar por Data");
            System.out.println("5- Sair");

            escolha = scan.nextInt();

            switch (escolha) {
                case 1:
                    System.out.println("Digite a quantidade de itens:");
                    int quant = scan.nextInt();
                    System.out.println("Digite o valor do iten:");
                    double valunid = scan.nextDouble();
                    double precototal = quant * valunid;
                    if (quant > 10) {
                        System.out.println("Preco total R$" + precototal * 0.95);
                    } else {
                        System.out.println("Preco total R$" + precototal);
                    }
                    registroVendas.add(new Venda(quant, valunid));

                    System.out.println("Venda Registrada!");
                    break;

                case 2:
                    System.out.println("Digite o valor recebido:");
                    double valrecebido = scan.nextDouble();
                    System.out.println("Digite o valor total da compra:");
                    double valcompra = scan.nextDouble();
                    double troco = calculoTroco(valrecebido, valcompra);
                    System.out.println("Troco da Compra R$" + troco);
                    break;

                case 3:
                    System.out.println("\n ---  REGISTRO DE VENDAS  ---");
                    if (registroVendas.isEmpty()) {
                        System.out.println("Nenhuma venda registrada.");
                    } else {
                        for (Venda venda : registroVendas) {
                            System.out.println("Data da Venda: "
                                    + venda.getDataVenda().format(formatador)
                                    + ", Quantidade: " + venda.getQuant()
                                    + ", Valor Final: R$ " + venda.getValorFinal());
                        }
                    }
                    break;

                    case 4:
                        System.out.print("Digite a data para busca (dd/MM/yyyy): ");
                    String dataBusca = scan.next();
        
                    try {
                        LocalDate dataBuscaFormatada = LocalDate.parse(dataBusca, formatador);
                        System.out.println("\n -------VENDAS NO PERIODO " + dataBusca + "-------");
                        boolean encontrouVendas = false;
                        double faturamentoDia = 0.0;

                        for (Venda venda : registroVendas) {
                            if (venda.getDataVenda().equals(dataBuscaFormatada)) {
                                System.out.println("Quantidade: " + venda.getQuant()
                                        + ", Preco Unitario: R$ " + venda.getValunid()
                                        + ", Valor Final: R$ " + venda.getValorFinal());
                                faturamentoDia += venda.getValorFinal();
                                encontrouVendas = true;
                            }
                        
                        }
                        if (!encontrouVendas) {
                            System.out.println("Nenhuma venda encontrada neste periodo.");
                        } else {
                            System.err.println("Faturamento total R$ " + faturamentoDia);
                        }
                           
                    } catch (Exception e) {
                        System.out.println("Data invalida! Use o formato dd/MM/yyyy.");
                    }
                        break;
                case 5:
                    System.out.println("Fechando calculadora!");
                    break;

            }
        } while (escolha != 5);

        scan.close();
    }

    public static double calculopreco(int quant, double valunid) {
        return quant * valunid;
    }

    public static double calculoTroco(double valrecebido, double valcompra) {
        return valrecebido - valcompra;
    }

}
