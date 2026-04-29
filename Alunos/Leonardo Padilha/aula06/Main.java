package calculadora;

import calculadora.service.MenuService;
import calculadora.model.Vendedor;

public class Main {
    public static void main(String[] args) {

        MenuService menu = new MenuService();
        menu.exibirMenu();

        Vendedor vendedor = new Vendedor(
                "João",
                30,
                "My Plant",
                "Cascavel",
                "Centro",
                "Rua das Flores",
                2000
        );

        vendedor.apresentarSe();
        System.out.println("Média salários: " + vendedor.calcularMedia());
        System.out.println("Bônus: " + vendedor.calcularBonus());
    }
}