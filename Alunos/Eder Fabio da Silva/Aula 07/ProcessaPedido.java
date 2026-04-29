
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProcessaPedido {

    // Lista para armazenar as vendas
    public static ArrayList<Vendas> registroVendas = new ArrayList<>();

    // Método para processar o pedido
    public Vendas processar(Date dataPagamento, Cliente cliente, Vendedor vendedor,
            Loja loja, ArrayList<Item> itens, int quantidade) {
        Vendas venda = new Vendas(dataPagamento, cliente, vendedor, loja, itens, quantidade);

        // Adiciona a venda ao registro de vendas                        
        registroVendas.add(venda);
        
        // Imprime o pedido
        venda.gerarDescricaoVenda();
        
        return venda;
    }

    // Verifica se a reserva ainda não venceu
    private boolean confirmarPagamento(Vendas venda) {
        Date hoje = new Date();
        return !hoje.after(venda.getDataVencimentoReserva());
    }

    // Teste do método confirmarPagamento
    public void testarConfirmacao(Vendas venda) {
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        if (confirmarPagamento(venda)) {
            System.out.println("Pagamento confirmado! Reserva válida até: " + formatador.format(venda.getDataVencimentoReserva()));
        } else {
            System.out.println("Reserva vencida! Não é possível confirmar o pagamento.");
        }
    }
}
