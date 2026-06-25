import java.time.LocalDate;
import java.util.ArrayList;

public class TestProcessaPedido {
    public static void main(String[] args) {
        ProcessaPedido processaPedido = new ProcessaPedido();

        // Criar um pedido de teste
        Pedido pedido = new Pedido();
        pedido.setDataVencimentoReserva(LocalDate.now().plusDays(1)); // Vence amanhã

        // Testar confirmarPagamento
        boolean confirmado = processaPedido.confirmarPagamento(pedido);
        System.out.println("Pagamento confirmado: " + confirmado);

        // Testar com data vencida
        pedido.setDataVencimentoReserva(LocalDate.now().minusDays(1)); // Já venceu
        confirmado = processaPedido.confirmarPagamento(pedido);
        System.out.println("Pagamento confirmado (vencido): " + confirmado);
    }
}