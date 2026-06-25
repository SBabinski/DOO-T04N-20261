package Alunos.Melissa_Ghellere.aula07;
import java.util.Date;

public class ProcessaPedido {
    public void processar(int id, String cliente, String vendedor, String loja, Item[] itens, Date vencimento) {
        Pedido pedido = new Pedido(id, cliente, vendedor, loja, itens, vencimento);
        confirmarPagamento(pedido);
        pedido.gerarDescricaoVenda();
    }

    private void confirmarPagamento(Pedido pedido) {
        Date dataAtual = new Date();
        if (dataAtual.before(pedido.getDataVencimentoReserva())) {
            System.out.println("Pagamento Confirmado!");
        } else {
            System.out.println("Erro: Reserva Vencida!");
        }
    }
}
