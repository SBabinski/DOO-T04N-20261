package fag.objetos;
import java.time.LocalDateTime;

public class ProcessaPedido {
    private Pedido pedido;
    private boolean status;

    public boolean processar(Pedido pedido) {
        this.pedido = pedido;
        status = confirmarPagamento();

        return status;
    }

    public boolean getStatus() {
        return status;
    }

    public void processarPagamento() {
        status = confirmarPagamento();
    }

    private boolean confirmarPagamento() {
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataVencimentoReserva = pedido.getDataVencimentoReserva();

        if(dataAtual.isAfter(dataVencimentoReserva)) {
            return false;
        }

        return true;
    }
}
