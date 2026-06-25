import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SistemaGabrielinha {
    int totalVendasRegistradas = 0;
    double[] registroVendas = new double[100]; 
    double[][] valorVendasDiarias = new double[12][31]; 
    int[][] quantidadeVendasDiarias = new int [12][31]; 

    public double calculoPrecoTotal(int quantidade, double precoUn, LocalDate dataVenda) {
        double precoTotal = quantidade * precoUn;

        if (quantidade > 10){
            double desconto = precoTotal * 0.05;
            precoTotal = precoTotal - desconto;
            System.out.println("Desconto de 5% aplicado. Novo total: R$" + precoTotal);
        }
        if (totalVendasRegistradas < 100) {
            registroVendas[totalVendasRegistradas] = precoTotal;
            totalVendasRegistradas++;
        }
        int mes = dataVenda.getMonthValue() - 1; 
        int dia = dataVenda.getDayOfMonth() - 1; 

        valorVendasDiarias[mes][dia] += precoTotal; 
        quantidadeVendasDiarias[mes][dia]++; 
        return precoTotal;
    }

    public void calculoTroco(double valorPago, double valorTotal){
        double troco = valorPago - valorTotal;
        if (troco < 0) {
            System.out.println("O valor inserido é inválido.");
        } else {
            System.out.printf("O troco a ser devolvido é: R$%.2f\n", troco);
        }
    }

    public void exibirHistorico(){
        System.out.println("\n--- Histórico de Vendas ---");
        for (int i = 0; i < totalVendasRegistradas; i++) {
            System.out.printf("Venda %d: R$%.2f\n", i + 1, registroVendas[i]);
        }
    }

    public void buscarVendasPorData(LocalDate dataBusca) {
        int mes = dataBusca.getMonthValue() - 1;
        int dia = dataBusca.getDayOfMonth() - 1;
        int quantidade = quantidadeVendasDiarias[mes][dia];
        double valorTotal = valorVendasDiarias[mes][dia];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("\n=== Relatório do Dia: " + dataBusca.format(formatter) + " ===\n");
        System.out.println("Total de vendas: " + quantidade + " | Faturamento: R$" + valorTotal);
    }
}