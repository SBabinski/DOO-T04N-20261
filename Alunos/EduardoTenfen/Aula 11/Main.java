package clima;

import java.util.Scanner;


public class Main {

   
    private static final String API_KEY = "Q38PLH3MY3BDWS9P4MY3QJQ3Z";

    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║       APLICATIVO DE CLIMA / TEMPO         ║");
        System.out.println("║      Powered by Visual Crossing API       ║");
        System.out.println("╚═══════════════════════════════════════════╝");

        // Monta as dependências manualmente (sem framework de injeção)
        ClimaApiService apiService   = new ClimaApiService(API_KEY);
        ClimaParser     parser       = new ClimaParser();
        ClimaVizualizador visualizador = new ClimaVizualizador();
        ClimaController controller   = new ClimaController(apiService, parser, visualizador);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nDigite o nome da cidade (ou 'sair' para encerrar): ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("sair") || entrada.isEmpty() && entrada.equals("")) {
                if (entrada.equalsIgnoreCase("sair")) {
                    System.out.println("\nEncerrando o aplicativo. Até mais!\n");
                    break;
                }
            }

            if (entrada.isEmpty()) {
                System.out.println("[AVISO] Digite um nome de cidade válido.");
                continue;
            }

            if (entrada.equalsIgnoreCase("sair")) {
                System.out.println("\nEncerrando o aplicativo. Até mais!\n");
                break;
            }

            controller.buscarEExibir(entrada);
        }

        scanner.close();
    }
}
