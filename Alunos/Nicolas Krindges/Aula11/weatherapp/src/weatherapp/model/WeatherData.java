package weatherapp.model;

/**
 * armazena as informacoes do clima de uma cidade.
 *
 * os dados ja vem prontos para serem exibidos na interface
 * e nao podem ser alterados apos a criacao do objeto.
 */
public final class WeatherData {

    private final String cidade;
    private final double temperaturaAtual;
    private final double temperaturaMaxima;
    private final double temperaturaMinima;
    private final double umidade;
    private final String condicao;
    private final double precipitacao;
    private final double velocidadeVento;
    private final double direcaoVento;

    public WeatherData(String cidade,
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
     * Converte graus (0-360) na direcao cardinal correspondente (N, NE, L, SE...).
     * Facilita a leitura pelo usuario final, que entende melhor "NE" do que "45°".
     */
    public String getDirecaoVentoCardinal() {
        String[] direcoes = {"N", "NE", "L", "SE", "S", "SO", "O", "NO"};
        int indice = (int) Math.round(direcaoVento / 45.0) % 8;
        if (indice < 0) {
            indice += 8;
        }
        return direcoes[indice];
    }
}
