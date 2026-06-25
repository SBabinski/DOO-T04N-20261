package weather;
import java.util.Locale;

public class WeatherInfo {

    private String local;
    private String data;
    private String horaConsulta;

    private Double temperaturaAtual;
    private Double temperaturaMaxima;
    private Double temperaturaMinima;
    private Double umidade;
    private String condicaoTempo;
    private Double precipitacao;
    private Double velocidadeVento;
    private Double direcaoVentoGraus;

    public void exibir() {
        System.out.println("\n=====---| CLIMA - CASCAVEL, PR |---=====");
        System.out.println("\n-- Local: " + texto(local));
        System.out.println("-- Data: " + texto(data));
        System.out.println("-- Hora da consulta: " + texto(horaConsulta));
        System.out.println("\n--- --- --- --- --- --- --- ---");
        System.out.println("\n-- Temperatura atual: " + formatarNumero(temperaturaAtual, " °C"));
        System.out.println("-- Máxima do dia: " + formatarNumero(temperaturaMaxima, " °C"));
        System.out.println("-- Mínima do dia: " + formatarNumero(temperaturaMinima, " °C"));
        System.out.println("-- Umidade do ar: " + formatarNumero(umidade, " %"));
        System.out.println("-- Condição do tempo: " + texto(condicaoTempo));
        System.out.println("-- Precipitação: " + formatarNumero(precipitacao, " mm"));
        System.out.println("-- Velocidade do vento: " + formatarNumero(velocidadeVento, " km/h"));
        System.out.println("-- Direção do vento: " + WindUtils.formatarDirecao(direcaoVentoGraus));
        System.out.println("\n=====---| --- |---=====");
    }

    private String formatarNumero(Double valor, String unidade) {
        if (valor == null) {
            return "-";
        }

        return String.format(Locale.US, "%.1f%s", valor, unidade);
    }

    private String texto(String valor) {
        if (valor == null || valor.trim().isEmpty()) {
            return "-";
        }

        return valor;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setHoraConsulta(String horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public void setTemperaturaAtual(Double temperaturaAtual) {
        this.temperaturaAtual = temperaturaAtual;
    }

    public void setTemperaturaMaxima(Double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public void setTemperaturaMinima(Double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    public void setUmidade(Double umidade) {
        this.umidade = umidade;
    }

    public void setCondicaoTempo(String condicaoTempo) {
        this.condicaoTempo = condicaoTempo;
    }

    public void setPrecipitacao(Double precipitacao) {
        this.precipitacao = precipitacao;
    }

    public void setVelocidadeVento(Double velocidadeVento) {
        this.velocidadeVento = velocidadeVento;
    }

    public void setDirecaoVentoGraus(Double direcaoVentoGraus) {
        this.direcaoVentoGraus = direcaoVentoGraus;
    }
}