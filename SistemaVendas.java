import java.time.LocalDate;
import java.util.HashMap;

public class SistemaVendas {

    HashMap<LocalDate, Integer> vendasPorDia = new HashMap<>();

    public double calcularPrecoTotal(int quantidade, double preco) {
        double total = quantidade * preco;

        if (quantidade > 10) {
            total *= 0.95; // desconto 5%
        }

        registrarVenda();
        return total;
    }

    public double calcularTroco(double pago, double total) {
        return pago - total;
    }

    private void registrarVenda() {
        LocalDate hoje = LocalDate.now();
        vendasPorDia.put(hoje, vendasPorDia.getOrDefault(hoje, 0) + 1);
    }

    public void consultarVendasDia(LocalDate data) {
        System.out.println("Vendas no dia: " + vendasPorDia.getOrDefault(data, 0));
    }

    public void consultarVendasMes(int mes) {
        long total = vendasPorDia.keySet().stream()
                .filter(d -> d.getMonthValue() == mes)
                .count();

        System.out.println("Dias com vendas no mês: " + total);
    }
}