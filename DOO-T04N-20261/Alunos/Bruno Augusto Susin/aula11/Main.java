import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome da cidade: ");
        String cidade = scanner.nextLine();

        ClimaService service = new ClimaService();

        Clima clima = service.buscarClima(cidade);

        if (clima != null) {

            System.out.println("\n===== DADOS DO CLIMA =====");

            System.out.println("Cidade: " + cidade);
            System.out.println("Temperatura Atual: " + clima.getTemperaturaAtual() + " °C");
            System.out.println("Temperatura Máxima: " + clima.getTemperaturaMaxima() + " °C");
            System.out.println("Temperatura Mínima: " + clima.getTemperaturaMinima() + " °C");
            System.out.println("Umidade: " + clima.getUmidade() + "%");
            System.out.println("Condicao: " + clima.getCondicao());
            System.out.println("Precipitacao: " + clima.getPrecipitacao() + " mm");
            System.out.println("Velocidade do Vento: " + clima.getVelocidadeVento() + " km/h");
            System.out.println("Direcao do Vento: " + clima.getDirecaoVento() + "°");

        } else {
            System.out.println("Nao foi possível obter os dados do clima.");
        }

        scanner.close();
    }
}