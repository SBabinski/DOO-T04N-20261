import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SistemaLoja {

    ArrayList<Venda> vendas = new ArrayList<>();

    public double calcularPrecoTotal(int quantidade, double precoUnitario) {

        double valorTotal = quantidade * precoUnitario;

        if (quantidade > 10) {
            valorTotal -= valorTotal * 0.05;
        }

        return valorTotal;
    }

    public double calcularDesconto(int quantidade, double precoUnitario) {

        if (quantidade > 10) {
            return (quantidade * precoUnitario) * 0.05;
        }

        return 0;
    }

    public double calcularTroco(double valorPago, double valorCompra) {
        return valorPago - valorCompra;
    }

    public void registrarVenda(int quantidade, double valorTotal, double desconto) {

        Venda venda = new Venda(
                quantidade,
                valorTotal,
                desconto,
                LocalDate.now()
        );

        vendas.add(venda);

        System.out.println("Venda registrada com sucesso!");
    }

    public void buscarVendasPorData(String dataTexto) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dataBusca = LocalDate.parse(dataTexto, formatter);

        int totalVendas = 0;

        for (Venda venda : vendas) {

            if (venda.data.equals(dataBusca)) {
                totalVendas++;
            }
        }

        System.out.println("Quantidade de vendas em "
                + dataBusca.format(formatter)
                + ": " + totalVendas);
    }
}