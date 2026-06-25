import java.util.ArrayList;
import java.util.Scanner;

public class TesteMenu {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        ProcessaPedido proc = new ProcessaPedido();
        
        int op = 0;
        while(op != 2) {
            System.out.println("\n1 - Criar Pedido");
            System.out.println("2 - Sair");
            op = leitor.nextInt();

            if (op == 1) {
                ArrayList<Item> lista = new ArrayList<>();
                lista.add(new Item(1, "Semente de Girassol", "Sementes", 5.50));
                lista.add(new Item(2, "Vaso Decorado", "Decora", 25.00));
                
                proc.processar(55, "Cliente Teste", "Vendedor Teste", "Loja Centro", lista);
            }
        }
        leitor.close();
    }
}