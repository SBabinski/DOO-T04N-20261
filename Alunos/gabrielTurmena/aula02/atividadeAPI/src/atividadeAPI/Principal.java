package atividadeAPI;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        BuscaDados busca = new BuscaDados();

        System.out.print("Digite a cidade: ");
        String cidade = scanner.nextLine();

        busca.buscarClima(cidade);

        scanner.close();
    }
}