public class SistemaGabrielinha {
    int totalVendasRegistradas = 0;
    double[] registroVendas = new double[100]; // Array dos valores das vendas

// Método para calcular o preço total e verificar se o desconto vai ser aplicado baseado na quantidade de vendas
    public double calculoPrecoTotal(int quantidade, double precoUn) {
        double precoTotal = quantidade * precoUn;

        if (quantidade > 10){
            double desconto = precoTotal * 0.05;
            precoTotal = precoTotal - desconto;
            System.out.println("Desconto aplicado de 5% para compras acima de 10 unidades.");
        }
        if (totalVendasRegistradas < 100) {
            registroVendas[totalVendasRegistradas] = precoTotal;
            totalVendasRegistradas++;
        } else {
            System.out.println("Limite de vendas registradas atingido. Não é possível registrar mais vendas.");
        }

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
}
