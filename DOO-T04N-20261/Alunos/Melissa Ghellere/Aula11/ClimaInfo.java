public class ClimaInfo {
    public String tempAtual;
    public String tempMax;
    public String tempMin;
    public String umidade;
    public String condicao;
    public String precipitacao;
    public String velVento;
    public String dirVento;

    public ClimaInfo(String tempAtual, String tempMax, String tempMin, String umidade, 
                     String condicao, String precipitacao, String velVento, String dirVento) {
        this.tempAtual = tempAtual;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.umidade = umidade;
        this.condicao = condicao;
        this.precipitacao = precipitacao;
        this.velVento = velVento;
        this.dirVento = dirVento;
    }

    public void exibirInformacoes() {
        System.out.println("========================================");
        System.out.println(" Condição Atual: " + condicao);
        System.out.println(" Temperatura Atual: " + tempAtual + " °C");
        System.out.println(" Máxima do Dia: " + tempMax + " °C");
        System.out.println(" Mínima do Dia: " + tempMin + " °C");
        System.out.println(" Umidade do Ar: " + umidade + "%");
        
        double precip = 0.0;
        try {
            // Só tenta converter para número se não for "N/D"
            if (!precipitacao.equals("N/D") && precipitacao != null) {
                precip = Double.parseDouble(precipitacao);
            }
        } catch (NumberFormatException e) {
            // Se vier alguma letra ou lixo da API que não dê para converter, a chuva fica zero.
            precip = 0.0;
        }

        if (precip > 0) {
            System.out.println(" Precipitação: " + precip + " mm (Chuva detectada)");
        } else {
            System.out.println(" Precipitação: 0.0 mm (Sem previsão de chuva)");
        }
        
        System.out.println(" Velocidade do Vento: " + velVento + " km/h");
        System.out.println(" Direção do Vento: " + dirVento + "°");
        System.out.println("========================================");
    }
}