package climatempo;

import climatempo.services.ClimaService;
import climatempo.services.CondicoesAtuais;
import climatempo.services.Dia;
import climatempo.services.RespostaClima;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a cidade: ");
        String cidade = scanner.nextLine();

        // Consulta a API
        RespostaClima clima = ClimaService.buscarClima(cidade);

        // Se deu erro, a service ja imprimiu o motivo e devolveu null
        if (clima == null) {
            System.out.println("Nao foi possivel obter o clima dessa cidade.");
            scanner.close();
            return;
        }

        CondicoesAtuais agora = clima.getCondicoesAtuais();
        Dia hoje = clima.getDias().get(0); // days[0] = dia de hoje

        System.out.println();
        System.out.println("==== Clima em " + clima.getEndereco() + " ====");
        System.out.println("Temperatura agora : " + agora.getTemperatura() + " C");
        System.out.println("Maxima do dia     : " + hoje.getTemperaturaMaxima() + " C");
        System.out.println("Minima do dia     : " + hoje.getTemperaturaMinima() + " C");
        System.out.println("Humidade do ar    : " + agora.getHumidade() + " %");
        System.out.println("Condicao          : " + agora.getCondicao());

        // So mostra a precipitacao se realmente choveu (valor maior que zero)
        Double precip = agora.getPrecipitacao();
        if (precip != null && precip > 0) {
            System.out.println("Precipitacao      : " + precip + " mm");
        } else {
            System.out.println("Precipitacao      : sem chuva no momento");
        }

        System.out.println("Velocidade vento  : " + agora.getVelocidadeVento() + " km/h");
        System.out.println("Direcao vento     : " + agora.getDirecaoVento() + " graus ("
                + direcaoCardeal(agora.getDirecaoVento()) + ")");

        scanner.close();
    }

    private static String direcaoCardeal(double graus) {
        String[] pontos = {"N", "NE", "L", "SE", "S", "SO", "O", "NO"};
        int indice = (int) Math.round(graus / 45.0) % 8;
        return pontos[indice];
    }
}
