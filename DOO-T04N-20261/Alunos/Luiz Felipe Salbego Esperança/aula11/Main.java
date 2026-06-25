import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a cidade: ");
        String cidade = scanner.nextLine();

        WeatherService service = new WeatherService();

        try {

            WeatherResponse clima =
                    service.buscarClima(cidade);

            CurrentConditions atual =
                    clima.getCurrentConditions();

            Day hoje =
                    clima.getDays().get(0);

            System.out.println("\n===== CLIMA =====");

            System.out.println(
                    "Temperatura Atual: "
                            + atual.getTemp()
                            + " C");

            System.out.println(
                    "Temperatura Maxima: "
                            + hoje.getTempmax()
                            + " C");

            System.out.println(
                    "Temperatura Minima: "
                            + hoje.getTempmin()
                            + " C");

            System.out.println(
                    "Umidade: "
                            + atual.getHumidity()
                            + "%");

            System.out.println(
                    "Condicao: "
                            + atual.getConditions());

            System.out.println(
                    "Precipitacao: "
                            + (atual.getPrecip() == null
                            ? 0
                            : atual.getPrecip())
                            + " mm");

            System.out.println(
                    "Velocidade do vento: "
                            + atual.getWindspeed()
                            + " km/h");

            System.out.println(
                    "Direcao do vento: "
                            + atual.getWinddir()
                            + " graus");

        } catch (Exception e) {

            System.out.println(
                    "Erro ao consultar clima.");

            e.printStackTrace();
        }
    }
}