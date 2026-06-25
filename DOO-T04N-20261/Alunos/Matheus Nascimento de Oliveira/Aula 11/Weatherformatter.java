public class WeatherFormatter {

    public static void exibir(WeatherData dados) {
        System.out.println("\n========================================");
        System.out.println("  CLIMA PARA: " + dados.getEnderecoResolvido());
        System.out.println("========================================");
        System.out.printf("  Temperatura atual : %.1f C%n", dados.getTemperaturaAtual());
        System.out.printf("  Maxima do dia     : %.1f C%n", dados.getTemperaturaMaxima());
        System.out.printf("  Minima do dia     : %.1f C%n", dados.getTemperaturaMinima());
        System.out.printf("  Umidade           : %.0f%%%n", dados.getUmidade());
        System.out.printf("  Condicao          : %s%n", dados.getCondicao());
        System.out.printf("  Precipitacao      : %.1f mm%n", dados.getPrecipitacao());
        System.out.printf("  Velocidade vento  : %.1f km/h%n", dados.getVelocidadeVento());
        System.out.printf("  Direcao vento     : %s%n", dados.getDirecaoVento());
        System.out.println("========================================\n");
    }

    public static void exibirErro(String mensagem) {
        System.err.println("Erro: " + mensagem);
    }
}
