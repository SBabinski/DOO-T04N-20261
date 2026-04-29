import calculadora.model.Pedido;
import java.time.LocalDate;

public class ProcessaPedido {

    public Pedido processar(Pedido pedido) {

        if (confirmarPagamento(pedido)) {
            pedido.confirmarPagamento();
            System.out.println("Pagamento aprovado!");
        } else {
            System.out.println("Pagamento NEGADO (pedido vencido)");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        return LocalDate.now().isBefore(pedido.getDataVencimentoReserva())
                || LocalDate.now().isEqual(pedido.getDataVencimentoReserva());
    }
}