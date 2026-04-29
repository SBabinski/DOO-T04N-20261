package calculadora_dona_gabrielinha;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {

    // Variáveis
    private int quantidade;
    private float valorUn;
    private float desconto;
    private float total;
    private LocalDate dataVenda;

    public Venda(int quantidade, float valorUn, LocalDate dataVenda) {
        this.quantidade = quantidade;
        this.valorUn = valorUn;
        this.dataVenda = dataVenda;
        calcularTotal();
    }

    // Aplica desconto de 5% para compras acima de 10 unidades
    private void calcularTotal() {
        total = quantidade * valorUn;
        if (quantidade > 10) {
            desconto = total * 0.05f;
            total -= desconto;
        } else {
            desconto = 0;
        }
    }

    public int getQuantidade() {return quantidade;}
    public float getDesconto() {return desconto;}
    public float getTotal() {return total;}
    public LocalDate getDataVenda() {return dataVenda;}

    public String getDataFormatada() {
        return dataVenda.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
