import java.util.Date;

public class ProcessarPedido {
    public void processar(Pedido pedido) {
        Date dataAtual = new Date();
        System.out.println("\nProcessando pagamento do pedido #" + pedido.id + "...");

        if(confirmarPgto(dataAtual, pedido.dataVencimento)) {
            pedido.dataPgto = dataAtual;
            System.out.println("SUCESSO: Pagamento confirmado! Reserva garantida.");
        } else {
            System.out.println("FALHA: A data da reserva já venceu. Pedido cancelado.");
        }
    }

    private boolean confirmarPgto(Date dataAtual, Date dataVencimento) {
        return !dataAtual.after(dataVencimento);
    }
}