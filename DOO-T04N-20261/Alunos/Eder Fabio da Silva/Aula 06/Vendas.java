
import java.time.LocalDate;

public class Vendas {

    private double precoUnitario;
    private int quantidade;
    private LocalDate dataVenda;

    // Construtor
    public Vendas(double precoUnitario, int quantidade) {
        this.precoUnitario = precoUnitario;
        this.quantidade = quantidade;
        this.dataVenda = LocalDate.now();
    }

    //getters 
    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }
// Método para calcular o valor total da venda, aplicando desconto se necessário

    public double getValorTotal() {
        double total = precoUnitario * quantidade;
        if (quantidade > 10) {
            return total * 0.95; // Aplicar desconto de 5%
        }
        return total;
    }

}
