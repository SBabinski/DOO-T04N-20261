import java.util.Scanner;

public class Main {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Consulta de clima - Visual Crossing");

        String apiKey = readRequiredText("Informe sua chave da API Visual Crossing: ");
        WeatherService weatherService = new WeatherService(apiKey);

        boolean running = true;

        while (running) {
            System.out.println();
            System.out.println("1 - Consultar clima de uma cidade");
            System.out.println("0 - Sair");

            int option = readInt("Escolha uma opcao: ");

            switch (option) {
                case 1:
                    searchWeather(weatherService);
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Opcao invalida.");
                    break;
            }
        }

        SCANNER.close();
        System.out.println("Programa encerrado.");
    }

    private static void searchWeather(WeatherService weatherService) {
        String city = readRequiredText("Cidade alvo: ");

        try {
            WeatherReport report = weatherService.findTodayWeather(city);
            printReport(report);
        } catch (Exception e) {
            System.out.println("Nao foi possivel consultar o clima.");
            System.out.println("Motivo: " + e.getMessage());
        }
    }

    private static void printReport(WeatherReport report) {
        CurrentWeather current = report.getCurrentWeather();
        DailyWeather today = report.getTodayWeather();

        System.out.println();
        System.out.println("Clima em " + valueOrDefault(report.getResolvedAddress()));
        System.out.println("Fuso horario: " + valueOrDefault(report.getTimezone()));
        System.out.println();
        System.out.println("Temperatura agora: " + formatNumber(current.getTemperature()) + " C");
        System.out.println("Maxima do dia: " + formatNumber(today.getMaxTemperature()) + " C");
        System.out.println("Minima do dia: " + formatNumber(today.getMinTemperature()) + " C");
        System.out.println("Humidade do ar: " + formatNumber(current.getHumidity()) + "%");
        System.out.println("Condicao do tempo: " + valueOrDefault(current.getCondition()));
        System.out.println("Precipitacao: " + formatNumber(current.getPrecipitation()) + " mm");
        System.out.println("Velocidade do vento: " + formatNumber(current.getWindSpeed()) + " km/h");
        System.out.println("Direcao do vento: " + formatWindDirection(current.getWindDirection()));
    }

    private static String readRequiredText(String message) {
        while (true) {
            System.out.print(message);
            String value = SCANNER.nextLine().trim();

            if (!value.isBlank()) {
                return value;
            }

            System.out.println("Esse campo e obrigatorio.");
        }
    }

    private static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(SCANNER.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero valido.");
            }
        }
    }

    private static String formatNumber(Double value) {
        if (value == null) {
            return "nao informado";
        }

        return String.format("%.1f", value);
    }

    private static String formatWindDirection(Double value) {
        if (value == null) {
            return "nao informado";
        }

        String[] directions = {"N", "NE", "L", "SE", "S", "SO", "O", "NO"};
        int index = (int) Math.round(value / 45.0) % directions.length;
        return String.format("%.1f graus (%s)", value, directions[index]);
    }

    private static String valueOrDefault(String value) {
        if (value == null || value.isBlank()) {
            return "nao informado";
        }

        return value;
    }
}
