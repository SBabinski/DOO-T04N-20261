package weather;

public class Main {

    public static void main(String[] args) {
        try {
            String apiKey;

            if (args.length > 0) {
                apiKey = args[0];
            } else {
                apiKey = System.getenv("VC_API_KEY");
            }

            if (apiKey == null || apiKey.trim().isEmpty()) {
                System.out.println("Informe sua chave da API.");
                return;
            }

            WeatherApiClient client = new WeatherApiClient(apiKey);

            WeatherInfo clima = client.buscarClimaAtual("Cascavel,PR,Brazil");

            clima.exibir();

        } catch (Exception e) {
            System.out.println("Erro ao executar aplicação:");
            System.out.println(e.getMessage());
        }
    }
}