package calculadora_dona_gabrielinha;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Venda {

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

    private void calcularTotal() {
        total = quantidade * valorUn;

        if (quantidade > 10) {
            desconto = total * 0.05f;
            total -= desconto;
        } else {
            desconto = 0;
        }
    }

    public int getQuantidade() {
        return quantidade;
    }

    public float getDesconto() {
        return desconto;
    }

    public float getTotal() {
        return total;
    }

     public LocalDate getDataVenda() {
        return dataVenda;
    }

        public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataVenda.format(formatter);
    }
}