import calculadora.service.MenuService;
import calculadora.model.*;

public class Main {
    public static void main(String[] args) {

        MenuService menu = new MenuService();
        menu.exibirMenu();

        Endereco end = new Endereco("PR", "Cascavel", "Centro", 10, "Casa");

        Vendedor vendedor = new Vendedor(
                "João",
                30,
                "My Plant",
                end,
                2000
        );

        vendedor.apresentarSe();
        System.out.println("Média salários: " + vendedor.calcularMedia());
        System.out.println("Bônus: " + vendedor.calcularBonus());

        Gerente gerente = new Gerente(
                "Maria",
                40,
                "My Plant",
                end,
                5000
        );

        gerente.apresentarSe();
        System.out.println("Média salários: " + gerente.calcularMedia());
        System.out.println("Bônus: " + gerente.calcularBonus());
    }
}