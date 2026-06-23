package Objetos;

public class WeatherData {

    private String temperaturaAtual;
    private String temperaturaMaxima;
    private String temperaturaMinima;
    private String umidade;
    private String condicao;
    private String precipitacao;
    private String velocidadeVento;
    private String direcaoVento;

    public WeatherData(String temperaturaAtual,
                       String temperaturaMaxima,
                       String temperaturaMinima,
                       String umidade,
                       String condicao,
                       String precipitacao,
                       String velocidadeVento,
                       String direcaoVento) {

        this.temperaturaAtual = temperaturaAtual;
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.umidade = umidade;
        this.condicao = condicao;
        this.precipitacao = precipitacao;
        this.velocidadeVento = velocidadeVento;
        this.direcaoVento = direcaoVento;
    }

    public String getTemperaturaAtual() {
        return temperaturaAtual;
    }

    public String getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public String getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public String getUmidade() {
        return umidade;
    }

    public String getCondicao() {
        return condicao;
    }

    public String getPrecipitacao() {
        return precipitacao;
    }

    public String getVelocidadeVento() {
        return velocidadeVento;
    }

    public String getDirecaoVento() {
        return direcaoVento;
    }
}