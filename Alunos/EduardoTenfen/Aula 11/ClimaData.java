package clima;

public class ClimaData {

    private String cidade;
    private double temperaturaAtual;
    private double temperaturaMaxima;
    private double temperaturaMinima;
    private double humidade;
    private String condicao;
    private double precipitacao;
    private double velocidadeVento;
    private String direcaoVento;

    // Construtor padrão
    public ClimaData() {}

    // Construtor completo
    public ClimaData(String cidade, double temperaturaAtual, double temperaturaMaxima,
                     double temperaturaMinima, double humidade, String condicao,
                     double precipitacao, double velocidadeVento, String direcaoVento) {
        this.cidade = cidade;
        this.temperaturaAtual = temperaturaAtual;
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.humidade = humidade;
        this.condicao = condicao;
        this.precipitacao = precipitacao;
        this.velocidadeVento = velocidadeVento;
        this.direcaoVento = direcaoVento;
    }

    // Getters e Setters
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public double getTemperaturaAtual() {
        return temperaturaAtual;
    }

    public void setTemperaturaAtual(double temperaturaAtual) {
        this.temperaturaAtual = temperaturaAtual;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public double getHumidade() {
        return humidade;
    }

    public void setHumidade(double humidade) {
        this.humidade = humidade;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public double getPrecipitacao() {
        return precipitacao;
    }

    public void setPrecipitacao(double precipitacao) {
        this.precipitacao = precipitacao;
    }

    public double getVelocidadeVento() {
        return velocidadeVento;
    }

    public void setVelocidadeVento(double velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public String getDirecaoVento() {
        return direcaoVento;
    }

    public void setDirecaoVento(String direcaoVento) {
        this.direcaoVento = direcaoVento;
    }

    @Override
    public String toString() {
        return "ClimaData{" +
                "cidade='" + cidade + '\'' +
                ", temperaturaAtual=" + temperaturaAtual +
                ", temperaturaMaxima=" + temperaturaMaxima +
                ", temperaturaMinima=" + temperaturaMinima +
                ", humidade=" + humidade +
                ", condicao='" + condicao + '\'' +
                ", precipitacao=" + precipitacao +
                ", velocidadeVento=" + velocidadeVento +
                ", direcaoVento='" + direcaoVento + '\'' +
                '}';
    }
}
