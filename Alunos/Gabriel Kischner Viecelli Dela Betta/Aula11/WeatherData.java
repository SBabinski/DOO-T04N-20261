public class WeatherData {

    private double temperaturaAtual;
    private double temperaturaMaxima;
    private double temperaturaMinima;
    private double umidade;
    private String condicao;
    private double precipitacao;
    private double velocidadeVento;
    private double direcaoVento;

    public WeatherData(
            double temperaturaAtual,
            double temperaturaMaxima,
            double temperaturaMinima,
            double umidade,
            String condicao,
            double precipitacao,
            double velocidadeVento,
            double direcaoVento) {

        this.temperaturaAtual = temperaturaAtual;
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.umidade = umidade;
        this.condicao = condicao;
        this.precipitacao = precipitacao;
        this.velocidadeVento = velocidadeVento;
        this.direcaoVento = direcaoVento;
    }

    @Override
    public String toString() {
        return  
                "\nTemperatura Atual: " + temperaturaAtual + " °C" +
                "\nTemperatura Máxima: " + temperaturaMaxima + " °C" +
                "\nTemperatura Mínima: " + temperaturaMinima + " °C" +
                "\nUmidade: " + umidade + "%" +
                "\nCondição: " + condicao +
                "\nPrecipitação: " + precipitacao + " mm" +
                "\nVelocidade do Vento: " + velocidadeVento + " km/h" +
                "\nDireção do Vento: " + direcaoVento + "°";
    }
}