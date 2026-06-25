/**
 * Modelo que representa os dados climáticos retornados pela API Visual Crossing.
 */
public class WeatherData {

    private final String cidade;
    private final double temperaturaAtual;
    private final double temperaturaMaxima;
    private final double temperaturaMinima;
    private final double umidade;
    private final String condicao;
    private final double precipitacao;
    private final double velocidadeVento;
    private final double direcaoVento;

    public WeatherData(
            String cidade,
            double temperaturaAtual,
            double temperaturaMaxima,
            double temperaturaMinima,
            double umidade,
            String condicao,
            double precipitacao,
            double velocidadeVento,
            double direcaoVento) {

        this.cidade = cidade;
        this.temperaturaAtual = temperaturaAtual;
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.umidade = umidade;
        this.condicao = condicao;
        this.precipitacao = precipitacao;
        this.velocidadeVento = velocidadeVento;
        this.direcaoVento = direcaoVento;
    }

    public String getCidade() {
        return cidade;
    }

    public double getTemperaturaAtual() {
        return temperaturaAtual;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public double getUmidade() {
        return umidade;
    }

    public String getCondicao() {
        return condicao;
    }

    public double getPrecipitacao() {
        return precipitacao;
    }

    public double getVelocidadeVento() {
        return velocidadeVento;
    }

    public double getDirecaoVento() {
        return direcaoVento;
    }

    /**
     * Converte graus (0-360) para direção cardinal (N, NE, L, SE, S, SO, O, NO).
     */
    public String getDirecaoCardinal() {
        String[] direcoes = {"N", "NE", "L", "SE", "S", "SO", "O", "NO"};
        int indice = (int) Math.round(direcaoVento / 45.0) % 8;
        return direcoes[indice];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("╔══════════════════════════════════════════╗\n");
        sb.append(String.format("  Previsão do Tempo — %s%n", cidade));
        sb.append("╚══════════════════════════════════════════╝\n");
        sb.append(String.format("  🌡  Temperatura atual : %.1f °C%n", temperaturaAtual));
        sb.append(String.format("  🔺  Máxima do dia     : %.1f °C%n", temperaturaMaxima));
        sb.append(String.format("  🔻  Mínima do dia     : %.1f °C%n", temperaturaMinima));
        sb.append(String.format("  💧  Umidade           : %.0f %%%n", umidade));
        sb.append(String.format("  ☁   Condição          : %s%n", condicao));
        sb.append(String.format("  🌧  Precipitação      : %.1f mm%n", precipitacao));
        sb.append(String.format("  💨  Velocidade vento  : %.1f km/h%n", velocidadeVento));
        sb.append(String.format("  🧭  Direção vento     : %.0f° (%s)%n", direcaoVento, getDirecaoCardinal()));
        return sb.toString();
    }
}
