import java.util.Date;

public class ProcessaPedido {

    public void processar(int id, Date c, Date p, Date v,
                          Cliente cl, Vendedor ve,
                          String loja, Item[] it) {

        Pedido ped = new Pedido(id, c, p, v, cl, ve, loja, it);

        if (confirmar(ped)) {
            System.out.println("Pagamento ok");
            ped.descricao();
        } else {
            System.out.println("Reserva vencida");
        }
    }

    private boolean confirmar(Pedido p) {
        return new Date().before(p.vencimento);
    }
}