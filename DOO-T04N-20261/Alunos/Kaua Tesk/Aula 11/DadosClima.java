
public class DadosClima {

    // Informações da localização
    private String cidade;         // Nome da cidade resolvida pela API
    private String descricao;      // Condição do tempo (ex: "Partially cloudy")

   
    private double tempAtual;      // Temperatura no momento da consulta
    private double tempMaxima;     // Temperatura máxima do dia
    private double tempMinima;     // Temperatura mínima do dia

    // Outros dados meteorológicos
    private double umidade;        // Umidade relativa do ar em %
    private double precipitacao;   // Quantidade de chuva em mm
    private double velocidadeVento;// Velocidade do vento em km/h
    private double direcaoVento;   // Direção do vento em graus (0-360)
    private double sensacaoTermica;// Sensação térmica em °C
    private double uvIndex;        // Índice UV

    // Construtor completo: recebe todos os dados de uma vez
    public DadosClima(String cidade, String descricao, double tempAtual,
                      double tempMaxima, double tempMinima, double umidade,
                      double precipitacao, double velocidadeVento,
                      double direcaoVento, double sensacaoTermica, double uvIndex) {
        this.cidade          = cidade;
        this.descricao       = descricao;
        this.tempAtual       = tempAtual;
        this.tempMaxima      = tempMaxima;
        this.tempMinima      = tempMinima;
        this.umidade         = umidade;
        this.precipitacao    = precipitacao;
        this.velocidadeVento = velocidadeVento;
        this.direcaoVento    = direcaoVento;
        this.sensacaoTermica = sensacaoTermica;
        this.uvIndex         = uvIndex;
    }

    public String getCidade()          { return cidade; }
    public String getDescricao()       { return descricao; }
    public double getTempAtual()       { return tempAtual; }
    public double getTempMaxima()      { return tempMaxima; }
    public double getTempMinima()      { return tempMinima; }
    public double getUmidade()         { return umidade; }
    public double getPrecipitacao()    { return precipitacao; }
    public double getVelocidadeVento() { return velocidadeVento; }
    public double getDirecaoVento()    { return direcaoVento; }
    public double getSensacaoTermica() { return sensacaoTermica; }
    public double getUvIndex()         { return uvIndex; }

   
    public String getDirecaoVentoTexto() {
        String[] direcoes = {"Norte", "NE", "Leste", "SE", "Sul", "SO", "Oeste", "NO"};
        // Divide o círculo de 360° em 8 fatias de 45° cada
        int indice = (int) Math.round(direcaoVento / 45.0) % 8;
        return direcoes[indice];
    }

   
    public String getDescricaoTraduzida() {
        String d = descricao.toLowerCase();

        if (d.contains("thunderstorm") || d.contains("thunder")) return "☈  Tempestade";
        if (d.contains("snow"))                                   return "❄  Neve";
        if (d.contains("freezing"))                               return "🌨  Garoa Gelada";
        if (d.contains("heavy rain") || d.contains("rain,heavy")) return "🌧  Chuva Forte";
        if (d.contains("rain"))                                   return "🌦  Chuva";
        if (d.contains("drizzle"))                                return "🌧  Garoa";
        if (d.contains("fog") || d.contains("mist"))             return "🌫  Névoa / Neblina";
        if (d.contains("overcast"))                               return "☁  Muito Nublado";
        if (d.contains("partially cloudy") || d.contains("partly cloudy")) return "⛅  Parcialmente Nublado";
        if (d.contains("cloudy"))                                 return "☁  Nublado";
        if (d.contains("clear"))                                  return "☀  Céu Limpo";
        if (d.contains("wind"))                                   return "💨  Ventoso";

        
        return descricao;
    }
}
