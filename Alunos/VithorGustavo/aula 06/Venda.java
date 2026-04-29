import java.time.LocalDate;

public class Venda {
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