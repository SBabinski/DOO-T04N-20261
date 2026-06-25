package calculadora_dona_gabrielinha;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Pedido {

    // Variáveis
    private int id;
    private LocalDate dataCriacao;
    private LocalDate dataPagamento;
    private LocalDate dataVencimentoReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;

    // Arrays
    private ArrayList<Item> itens = new ArrayList<>();

    // Molde de dados
    public Pedido(int id, LocalDate dataCriacao, LocalDate dataPagamento, LocalDate dataVencimentoReserva, Cliente cliente, Vendedor vendedor, Loja loja) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
    }

    // Soma o valor de todos os itens do pedido
    public double calcularValorTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getValor();
        }
        return total;
    }

    // Exibe resumo do pedido
    public void gerarDescricaoVenda() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("\n===== PEDIDO #" + id + " =====");
        System.out.println("Data de criação: " + dataCriacao.format(fmt));
        System.out.println("Data pagamento: " + dataPagamento.format(fmt));
        System.out.println("Vencimento reserva: " + dataVencimentoReserva.format(fmt));
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Vendedor: " + vendedor.getNome());
        System.out.println("Loja: " + loja.nomeFantasia);
        System.out.println("\n--- Itens ---");
        for (Item item : itens) {
            item.gerarDescricao();
        }
        System.out.printf("%nValor total do pedido: R$ %.2f%n", calcularValorTotal());
        System.out.println("===========================\n");
    }

    // Getters
    public int getId() {return id;}
    public LocalDate getDataVencimentoReserva() {return dataVencimentoReserva;}
    public ArrayList<Item> getItens() {return itens;}
}
