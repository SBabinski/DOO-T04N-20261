import java.util.ArrayList;
import java.util.Date;

public class ProcessaPedido {

    public void processar(int id, String cli, String vend, String loja, ArrayList<Item> itens) {
        Pedido p = new Pedido(id, cli, vend, loja, itens);
        
        if (confirmarPagamento(p)) {
            p.setDataPagamento(new Date());
            System.out.println("Pedido " + id + " processado.");
        } else {
            System.out.println("Nao foi possivel processar: Reserva vencida.");
        }
    }

    private boolean confirmarPagamento(Pedido p) {
        Date hoje = new Date();
        return hoje.before(p.getDataVencimentoReserva());
    }
}