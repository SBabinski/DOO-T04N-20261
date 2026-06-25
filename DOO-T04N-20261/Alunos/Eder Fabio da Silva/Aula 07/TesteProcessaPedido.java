import java.util.ArrayList;
import java.util.Date;

public class TesteProcessaPedido {
    public static void main(String[] args) {
        // Dados fake
        Cliente cliente = new Cliente();
        Vendedor vendedor = new Vendedor();
        Loja loja = new Loja();
        ArrayList<Item> itens = new ArrayList<>();
        itens.add(new Item("Teste1", "Tipo1", 10.0));
        itens.add(new Item("Teste2", "Tipo2", 20.0));

        Date dataPagamento = new Date();

        ProcessaPedido processa = new ProcessaPedido();

        // Teste 1: reserva válida
        Vendas venda1 = processa.processar(dataPagamento, cliente, vendedor, loja, itens, 2);
        processa.testarConfirmacao(venda1);

        // Teste 2: reserva vencida (data de pagamento no passado, forçando vencimento)
        Vendas venda2 = processa.processar(dataPagamento, cliente, vendedor, loja, itens, 5);
        // Forçar reserva vencida para teste
        venda2.setDataVencimentoReserva(new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
        processa.testarConfirmacao(venda2);
    }
}
