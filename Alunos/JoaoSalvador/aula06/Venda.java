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

    // Molde de dados
    public Venda(int quantidade, float valorUn, LocalDate dataVenda) {
        this.quantidade = quantidade;
        this.valorUn = valorUn;
        this.dataVenda = dataVenda;
        calcularTotal();
    }

    // Calculo do valor total dos produtos
    private void calcularTotal() {
        total = quantidade * valorUn;

        if (quantidade > 10) {
            desconto = total * 0.05f;
            total -= desconto;
        } else {
            desconto = 0;
        }
    }

    // Retorna qtd
    public int getQuantidade() {
        return quantidade;
    }

    // Retorna desconto
    public float getDesconto() {
        return desconto;
    }

    // Retorna valor total
    public float getTotal() {
        return total;
    }

    // Retorna a data da venda
    public LocalDate getDataVenda() {
        return dataVenda;
    }

    // Formatador de data
    public String getDataFormatada() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dataVenda.format(formatter);
    }
}