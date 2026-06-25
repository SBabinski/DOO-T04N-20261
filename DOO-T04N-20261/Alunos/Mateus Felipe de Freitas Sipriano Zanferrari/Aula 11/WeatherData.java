

public class WeatherData {

    private final String cidade;
    private final double temperaturaAtual;
    private final double temperaturaMaxima;
    private final double temperaturaMinima;
    private final double humidade;
    private final String condicao;
    private final double precipitacao;
    private final double velocidadeVento;
    private final double direcaoVento;
    private final long   timestamp; 


    // Construtor que recebe todos os dados climáticos e o nome da cidade.
    public WeatherData(String cidade, double temperaturaAtual, double temperaturaMaxima,
                       double temperaturaMinima, double humidade, String condicao,
                       double precipitacao, double velocidadeVento, double direcaoVento) {
        this.cidade           = cidade;
        this.temperaturaAtual = temperaturaAtual;
        this.temperaturaMaxima = temperaturaMaxima;
        this.temperaturaMinima = temperaturaMinima;
        this.humidade         = humidade;
        this.condicao         = condicao;
        this.precipitacao     = precipitacao;
        this.velocidadeVento  = velocidadeVento;
        this.direcaoVento     = direcaoVento;
        this.timestamp        = System.currentTimeMillis();
    }

    // Getters

    // Retorna o nome da cidade para a qual os dados climáticos foram consultados.
    public String getCidade()             { return cidade; }

    // Retorna a temperatura atual em graus Celsius.
    public double getTemperaturaAtual()   { return temperaturaAtual; }

    // Retorna a temperatura máxima prevista para o dia, em graus Celsius.
    public double getTemperaturaMaxima()  { return temperaturaMaxima; }

    // Retorna a temperatura mínima prevista para o dia, em graus Celsius.
    public double getTemperaturaMinima()  { return temperaturaMinima; }

    // Retorna a humidade relativa do ar em porcentagem (0-100%).
    public double getHumidade()           { return humidade; }

    // Retorna a descrição textual da condição climática (ex: "Ensolarado", "Chuva leve", etc.).
    public String getCondicao()           { return condicao; }

    // Retorna a quantidade de precipitação prevista para o dia, em milímetros.
    public double getPrecipitacao()       { return precipitacao; }

    // Retorna a velocidade do vento em km/h.
    public double getVelocidadeVento()    { return velocidadeVento; }

    // Retorna a direção do vento em graus (0° = Norte, 90° = Leste, etc.). 
    public double getDirecaoVento()       { return direcaoVento; }

    // Retorna o timestamp da consulta, em milissegundos.
    public long getTimestamp()            { return timestamp; }

    // Métodos auxiliares

    // Converte a direção do vento de graus para uma representação cardinal (N, NE, E, etc.).
    public String getDirecaoVentoCardinal() {
        String[] direcoes = {
            "Norte", "Nordeste", "Leste", "Sudeste",
            "Sul", "Sudoeste", "Oeste", "Noroeste"
        };
        int indice = (int) Math.round(direcaoVento / 45.0) % 8;
        return direcoes[indice];
    }

    // Retorna uma classificação da temperatura atual para exibição ao usuário.
    public String getClassificacaoTermica() {
        if (temperaturaAtual < 10) return "Muito frio";
        if (temperaturaAtual < 18) return "Frio";
        if (temperaturaAtual < 24) return "Agradável";
        if (temperaturaAtual < 30) return "Quente";
        return "Muito quente";
    }

    // Retorna uma classificação da humidade relativa para exibição ao usuário.
    public String getClassificacaoHumidade() {
        if (humidade < 30) return "Muito seca";
        if (humidade < 50) return "Seca";
        if (humidade < 70) return "Confortável";
        if (humidade < 85) return "Úmida";
        return "Muito úmida";
    }

    // Retorna uma classificação da velocidade do vento para exibição ao usuário.
    public String getClassificacaoVento() {
        if (velocidadeVento < 5)  return "Calmaria";
        if (velocidadeVento < 20) return "Brisa leve";
        if (velocidadeVento < 40) return "Moderado";
        if (velocidadeVento < 62) return "Forte";
        return "Muito forte";
    }

    // Sobrescreve o toString para exibir todas as informações de forma organizada.
    @Override
    public String toString() {
        return String.format(
            "\n========================================\n" +
            "  PREVISÃO DO TEMPO — %s\n"                  +
            "========================================\n"   +
            "  Temperatura atual : %.1f °C  (%s)\n"        +
            "  Máxima do dia     : %.1f °C\n"              +
            "  Mínima do dia     : %.1f °C\n"              +
            "  Humidade do ar    : %.0f%%  (%s)\n"         +
            "  Condição          : %s\n"                   +
            "  Precipitação      : %.1f mm\n"              +
            "  Vento             : %.1f km/h — %s (%s, %.0f°)\n" +
            "========================================\n",
            cidade,
            temperaturaAtual, getClassificacaoTermica(),
            temperaturaMaxima,
            temperaturaMinima,
            humidade, getClassificacaoHumidade(),
            condicao,
            precipitacao,
            velocidadeVento, getDirecaoVentoCardinal(), getClassificacaoVento(), direcaoVento
        );
    }
}
