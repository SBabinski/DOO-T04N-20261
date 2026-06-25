package clima;


public class ClimaVizualizador {

    private static final String LINHA = "═══════════════════════════════════════════";

    /**
     * Exibe os dados climáticos formatados no console.
     *
     * @param dados Objeto ClimaData com as informações do clima
     */
    public void exibir(ClimaData dados) {
        System.out.println("\n" + LINHA);
        System.out.println("  🌤  PREVISÃO DO TEMPO");
        System.out.println(LINHA);
        System.out.printf("  📍 Cidade        : %s%n", dados.getCidade());
        System.out.println(LINHA);

        System.out.printf("  🌡  Temperatura atual : %.1f °C%n", dados.getTemperaturaAtual());
        System.out.printf("  🔺 Máxima do dia     : %.1f °C%n", dados.getTemperaturaMaxima());
        System.out.printf("  🔻 Mínima do dia     : %.1f °C%n", dados.getTemperaturaMinima());
        System.out.println(LINHA);

        System.out.printf("  💧 Humidade          : %.1f %%%n", dados.getHumidade());
        System.out.printf("  ☁  Condição          : %s%n", dados.getCondicao());

        if (dados.getPrecipitacao() > 0) {
            System.out.printf("  🌧  Precipitação      : %.1f mm%n", dados.getPrecipitacao());
        } else {
            System.out.printf("  🌧  Precipitação      : Sem chuva%n");
        }

        System.out.println(LINHA);
        System.out.printf("  💨 Vento             : %.1f km/h — Direção: %s%n",
                dados.getVelocidadeVento(), dados.getDirecaoVento());
        System.out.println(LINHA + "\n");
    }

   
    public void exibirErro(String mensagem) {
        System.err.println("\n[ERRO] " + mensagem + "\n");
    }

   
    public void exibirCarregando(String cidade) {
        System.out.printf("%nBuscando dados climáticos para: %s...%n", cidade);
    }
}
