import java.util.Scanner;

public class Main {
    // Utiliza a biblioteca org.json para leitura da resposta da API.

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== CONSULTA DE CLIMA ===");

        System.out.print("Digite a cidade: ");
        String cidade = scanner.nextLine();

        ServicoClima service = new ServicoClima();

        try {

            WeatherData clima =
                    service.buscarClima(cidade);

            System.out.println(clima);

        } catch (ApiException e) {

            System.out.println(
                    "Erro: " + e.getMessage());
        }

        scanner.close();
    }
}