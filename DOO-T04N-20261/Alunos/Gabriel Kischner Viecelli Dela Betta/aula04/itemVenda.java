import java.time.LocalDate;

public class itemVenda {
    public LocalDate data;
    public float valor;

    itemVenda(float valor) {
        this.data = LocalDate.now();
        this.valor = valor;
    }
}
