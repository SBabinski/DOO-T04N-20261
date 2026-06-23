package model;

public class Clima {

    private double temperaturaAtual;
    private double temperaturaMaxima;
    private double temperaturaMinima;
    private double umidade;
    private String condicaoTempo;
    private double precipitacao;
    private double velocidadeVento;
    private double direcaoVento;

    private boolean cidadeEncontrada = true;

    public double getTemperaturaAtual() {
        return temperaturaAtual;
    }

    public void setTemperaturaAtual(
            double temperaturaAtual) {

        this.temperaturaAtual =
                temperaturaAtual;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(
            double temperaturaMaxima) {

        this.temperaturaMaxima =
                temperaturaMaxima;
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(
            double temperaturaMinima) {

        this.temperaturaMinima =
                temperaturaMinima;
    }

    public double getUmidade() {
        return umidade;
    }

    public void setUmidade(double umidade) {
        this.umidade = umidade;
    }

    public String getCondicaoTempo() {
        return condicaoTempo;
    }

    public void setCondicaoTempo(
            String condicaoTempo) {

        this.condicaoTempo =
                condicaoTempo;
    }

    public double getPrecipitacao() {
        return precipitacao;
    }

    public void setPrecipitacao(
            double precipitacao) {

        this.precipitacao =
                precipitacao;
    }

    public double getVelocidadeVento() {
        return velocidadeVento;
    }

    public void setVelocidadeVento(
            double velocidadeVento) {

        this.velocidadeVento =
                velocidadeVento;
    }

    public double getDirecaoVento() {
        return direcaoVento;
    }

    public void setDirecaoVento(
            double direcaoVento) {

        this.direcaoVento =
                direcaoVento;
    }

    public boolean isCidadeEncontrada() {
        return cidadeEncontrada;
    }

    public void setCidadeEncontrada(
            boolean cidadeEncontrada) {

        this.cidadeEncontrada =
                cidadeEncontrada;
    }
}