import java.time.LocalDate;

public class TesteProcessaPedido {
    public static void main(String[] args) {
        ProcessaPedido processaPedido = new ProcessaPedido();

        Pedido pedido = new Pedido();
        pedido.setDataVencimentoReserva(LocalDate.now().plusDays(1));

        boolean confirmado = processaPedido.confirmarPagamento(pedido);
        System.out.println("Pagamento confirmado: " + confirmado);

        pedido.setDataVencimentoReserva(LocalDate.now().minusDays(1)); 
        confirmado = processaPedido.confirmarPagamento(pedido);
        System.out.println("Pagamento confirmado (vencido): " + confirmado);
    }
}