import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
 
        System.out.println("=== Consulta de Clima ===");
        System.out.print("Informe sua chave de API: ");
        String apiKey = scanner.nextLine().trim();
 
        System.out.print("Informe a cidade: ");
        String cidade = scanner.nextLine().trim();
 
        scanner.close();
 
        if (apiKey.isEmpty() || cidade.isEmpty()) {
            System.out.println("Chave de API e cidade nao podem ser vazios.");
            return;
        }
 
        try {
            WeatherService servico = new WeatherService(apiKey);
            WeatherData dados = servico.buscarClima(cidade);
            WeatherFormatter.exibir(dados);
 
        } catch (WeatherApiException e) {
            WeatherFormatter.exibirErro(e.getMessage());
        }
    }
}
 
