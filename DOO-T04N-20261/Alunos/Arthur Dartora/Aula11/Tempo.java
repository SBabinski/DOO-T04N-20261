/**
 * Guarda os dados de clima de uma cidade.
 *
 * <p>Unidades (sistema métrico): temperaturas em °C, umidade em %,
 * chuva em mm, vento em km/h e direção em graus (0-360).
 *
 * @param cidade   nome da cidade
 * @param temp     temperatura no momento da consulta (°C)
 * @param max      máxima do dia (°C)
 * @param min      mínima do dia (°C)
 * @param umidade  umidade do ar (%)
 * @param condicao descrição do tempo (ex.: "Parcialmente nublado")
 * @param chuva    quantidade de precipitação (mm)
 * @param vento    velocidade do vento (km/h)
 * @param direcao  direção de onde o vento sopra, em graus
 */
public record Tempo(
        String cidade,
        double temp,
        double max,
        double min,
        double umidade,
        String condicao,
        double chuva,
        double vento,
        double direcao) {

    private static final String[] PONTOS = {
            "Norte", "Nordeste", "Leste", "Sudeste",
            "Sul", "Sudoeste", "Oeste", "Noroeste"
    };

    /** Converte a direção do vento de graus para ponto cardeal (ex.: 90° -&gt; "Leste"). */
    public String direcaoCardeal() {
        int i = ((int) Math.round(direcao / 45.0) % 8 + 8) % 8;
        return PONTOS[i];
    }

    /** Monta o texto do relatório para exibir no console. */
    public String formatar() {
        return """
               === Clima em %s ===
               Condição.........: %s
               Temperatura......: %.1f °C
               Máxima do dia....: %.1f °C
               Mínima do dia....: %.1f °C
               Umidade..........: %.0f %%
               Chuva............: %.1f mm
               Vento............: %.1f km/h (%s, %.0f°)
               """.formatted(
                cidade, condicao, temp, max, min,
                umidade, chuva, vento, direcaoCardeal(), direcao);
    }
}
