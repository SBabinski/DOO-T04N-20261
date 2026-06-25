package Alunos.Melissa_Ghellere.aula06;
import java.util.Date;

public class TesteAula06 {
    public static void main(String[] args) {
        Vendedor v1 = new Vendedor("Melissa", 2500.0);
        Gerente g1 = new Gerente("Samuel");

        v1.apresentarse();
        g1.cobrarVendedor();
        
        System.out.println("Data da atividade: " + new Date());
    }
}
