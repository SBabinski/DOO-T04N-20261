package Alunos.Melissa_Ghellere.aula07;
import java.util.Date;

public class TesteAula07 {
    public static void main(String[] args) {
        Endereco end = new Endereco("PR", "Cascavel", "Centro", 100, "Sl 01");
        Gerente gerente = new Gerente("Melissa", 20, "My Plant Cascavel", end, 5000.0);
        
        gerente.apresentarse();
        System.out.println("Média Salarial: " + gerente.calcularMedia());
        System.out.println("Bônus: " + gerente.calcularBonus());

        Item item1 = new Item(1, "Orquídea", "Flor", 50.0);
        Item[] listaItens = {item1};

        ProcessaPedido processador = new ProcessaPedido();
        // Criando data de vencimento para amanhã (reserva válida)
        Date amanha = new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24));
        
        processador.processar(101, "Cliente FAG", "Vendedor Melissa", "Loja 01", listaItens, amanha);
    }
}
