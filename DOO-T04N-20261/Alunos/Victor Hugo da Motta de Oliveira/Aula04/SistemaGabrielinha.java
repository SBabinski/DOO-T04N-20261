import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SistemaGabrielinha {
    int totalVendasRegistradas = 0;
    double[] registroVendas = new double[100]; // Array dos valores das vendas
    double[][] valorVendasDiarias = new double[12][31]; // Novo array para registro diario
    int [][] quantidadeVendasDiarias = new int [12][31]; // Array para quantia de vendas diarias


// Método para calcular o preço total e verificar se o desconto vai ser aplicado baseado na quantidade de vendas
    public double calculoPrecoTotal(int quantidade, double precoUn, LocalDate dataVenda) {
        double precoTotal = quantidade * precoUn;

        if (quantidade > 10){
            double desconto = precoTotal * 0.05;
            precoTotal = precoTotal - desconto;
            System.out.println("Desconto aplicado de 5% para compras acima de 10 unidades. Novo preço total: R$" + precoTotal);
        }
        if (totalVendasRegistradas < 100) {
            registroVendas[totalVendasRegistradas] = precoTotal;
            totalVendasRegistradas++;
        } else {
            System.out.println("Limite de vendas registradas atingido. Não é possível registrar mais vendas.");
        }
        int mes = dataVenda.getMonthValue() -1; //Salva o mes da venda
        int dia = dataVenda.getDayOfMonth() -1; //Salva o dia da venda

        valorVendasDiarias[mes][dia] += precoTotal; // Soma no valor do dia e mes
        quantidadeVendasDiarias[mes][dia] ++; //Adiciona um no contador de vendas
        return precoTotal;
    }

//Método para calcular o troco, caso o troco seja inválido o sistema ira apresentar erro.
    public void calculoTroco(double valorPago, double valorTotal){
        double troco = valorPago - valorTotal;
        if (troco < 0) {
            System.out.println("O valor inserido é inválido e não é suficiente para o troco");
        } else {
            System.out.printf("O troco a ser devolvido é: R$%.2f\n", troco);
        }
    }

//Método de histórico de vendas
    public void exibirHistorico(){
        System.out.println("\n--- Histórico de Vendas ---");
        if (totalVendasRegistradas == 0) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (int i = 0; i < totalVendasRegistradas; i++) {
                System.out.printf("Venda %d: R$%.2f\n", i + 1, registroVendas[i]);
            }
        }
    }
//Método de busca na matriz
    public void buscarVendasPorData(LocalDate dataBusca) {
        int mes = dataBusca.getMonthValue() - 1;
        int dia = dataBusca.getDayOfMonth() - 1;

        int quantidade = quantidadeVendasDiarias[mes][dia];
        double valorTotal = valorVendasDiarias[mes][dia];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("\n=== Relatório do Dia: " + dataBusca.format(formatter) + " ===\n");
        if (quantidade ==0) {
            System.out.println("Nenhuma venda realizada nesse dia.");
        } else {
            System.out.println("Total de vendas realizadas: " + quantidade);
            System.out.printf("Faturamento total do dia: R$%.2f\n", valorTotal);
        }
    }
}
