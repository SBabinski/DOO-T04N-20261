package climatempo.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa o objeto "currentConditions" do JSON, ou seja, as condicoes
 * do tempo no momento exato da consulta.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CondicoesAtuais {

    // Temperatura no momento da consulta
    @JsonProperty("temp")
    private double temperatura;

    // Humidade do ar (em %)
    @JsonProperty("humidity")
    private double humidade;

    // Condicao do tempo: ex "Rain", "Clear", "Partially cloudy"
    @JsonProperty("conditions")
    private String condicao;

    // Quantidade de precipitacao (chuva), pode vir null quando nao chove
    @JsonProperty("precip")
    private Double precipitacao;

    // Velocidade do vento
    @JsonProperty("windspeed")
    private double velocidadeVento;

    // Direcao do vento (em graus, 0-360)
    @JsonProperty("winddir")
    private double direcaoVento;

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
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

    public Double getPrecipitacao() {
        return precipitacao;
    }

    public void setPrecipitacao(Double precipitacao) {
        this.precipitacao = precipitacao;
    }

    public double getVelocidadeVento() {
        return velocidadeVento;
    }

    public void setVelocidadeVento(double velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public double getDirecaoVento() {
        return direcaoVento;
    }

    public void setDirecaoVento(double direcaoVento) {
        this.direcaoVento = direcaoVento;
    }
}
