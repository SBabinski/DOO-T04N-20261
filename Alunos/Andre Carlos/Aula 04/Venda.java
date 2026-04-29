import java.time.LocalDate;

public class Venda {

    int quantidade;
    double valorTotal;
    double desconto;
    LocalDate data;

    public Venda(int quantidade, double valorTotal, double desconto, LocalDate data) {
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.desconto = desconto;
        this.data = data;
    }
}