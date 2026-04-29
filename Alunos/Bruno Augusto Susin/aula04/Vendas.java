import java.time.LocalDate;

public class Vendas {


    int quantidade;
    double valorFinal;
    double desconto;
    LocalDate data;

    public Vendas (int quantidade, double valorFinal, double desconto, LocalDate data){
        this.quantidade = quantidade;
        this.valorFinal = valorFinal;
        this.desconto = desconto;
        this.data = data;
    }
}
