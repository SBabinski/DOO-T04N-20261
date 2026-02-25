package calculadora.service;

import java.util.Scanner;

public class MenuService {
    Scanner scan = new Scanner(System.in);
    CalculadoraService calculadora = new CalculadoraService();
    public void exibirMenu() {
        int opcao = 0;
        while (opcao != 3) {
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");
            opcao = this.scan.nextInt();

            switch (opcao) {
                case 1:
                    this.calculadora.CalcularPrecoTotal();
                    break;
                case 2:
                    this.calculadora.CalcularTroco();
                    break;
                case 3:
                    System.out.println("Calculadora encerrando...");
                    break;
                default:
                    System.out.println("Opção invalida!");
            }
        }
    }
}
