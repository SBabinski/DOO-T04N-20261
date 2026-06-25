package clima;

public class ClimaParser {
	
    public ClimaData parse(String json, String cidade) throws Exception {
        if (json == null || json.trim().isEmpty()) {
            throw new Exception("JSON vazio ou nulo recebido da API.");
        }

        ClimaData dados = new ClimaData();
        dados.setCidade(cidade);

        // Extrai o endereço resolvido (nome oficial da cidade)
        String enderecoResolvido = extrairString(json, "resolvedAddress");
        if (enderecoResolvido != null && !enderecoResolvido.isEmpty()) {
            dados.setCidade(enderecoResolvido);
        }

        // Dados do dia (bloco "days[0]")
        String blocoDay = extrairPrimeiroDay(json);
        if (blocoDay == null) {
            throw new Exception("Bloco 'days' não encontrado no JSON.");
        }
        dados.setTemperaturaMaxima(extrairDouble(blocoDay, "tempmax"));
        dados.setTemperaturaMinima(extrairDouble(blocoDay, "tempmin"));
        dados.setPrecipitacao(extrairDouble(blocoDay, "precip"));

        // Dados das condições atuais (bloco "currentConditions")
        String blocoAtual = extrairBloco(json, "currentConditions");
        if (blocoAtual == null) {
            throw new Exception("Bloco 'currentConditions' não encontrado no JSON.");
        }
        dados.setTemperaturaAtual(extrairDouble(blocoAtual, "temp"));
        dados.setHumidade(extrairDouble(blocoAtual, "humidity"));
        dados.setCondicao(extrairString(blocoAtual, "conditions"));
        dados.setVelocidadeVento(extrairDouble(blocoAtual, "windspeed"));
        dados.setDirecaoVento(grausParaDirecao(extrairDouble(blocoAtual, "winddir")));

        return dados;
    }

   
    private String extrairString(String json, String chave) {
        String busca = "\"" + chave + "\"";
        int idx = json.indexOf(busca);
        if (idx < 0) return null;

        int inicio = json.indexOf('"', idx + busca.length());
        if (inicio < 0) return null;
        inicio++; // pula a aspas de abertura

        int fim = json.indexOf('"', inicio);
        if (fim < 0) return null;

        return json.substring(inicio, fim);
    }

  
    private double extrairDouble(String json, String chave) {
        String busca = "\"" + chave + "\"";
        int idx = json.indexOf(busca);
        if (idx < 0) return 0.0;

        // Avança após a chave e o ':'
        int pos = idx + busca.length();
        while (pos < json.length() && (json.charAt(pos) == ' ' || json.charAt(pos) == ':')) {
            pos++;
        }

        // Lê caracteres numéricos (inclui ponto decimal e sinal negativo)
        StringBuilder numero = new StringBuilder();
        while (pos < json.length()) {
            char c = json.charAt(pos);
            if (Character.isDigit(c) || c == '.' || c == '-') {
                numero.append(c);
                pos++;
            } else {
                break;
            }
        }

        try {
            return numero.length() > 0 ? Double.parseDouble(numero.toString()) : 0.0;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

   
    private String extrairBloco(String json, String chave) {
        String busca = "\"" + chave + "\"";
        int idx = json.indexOf(busca);
        if (idx < 0) return null;

        int abreChave = json.indexOf('{', idx + busca.length());
        if (abreChave < 0) return null;

        int profundidade = 0;
        int pos = abreChave;
        while (pos < json.length()) {
            char c = json.charAt(pos);
            if (c == '{') profundidade++;
            else if (c == '}') {
                profundidade--;
                if (profundidade == 0) {
                    return json.substring(abreChave, pos + 1);
                }
            }
            pos++;
        }
        return null;
    }

    private String extrairPrimeiroDay(String json) {
        String busca = "\"days\"";
        int idx = json.indexOf(busca);
        if (idx < 0) return null;

        int abreArray = json.indexOf('[', idx + busca.length());
        if (abreArray < 0) return null;

        int abreObjeto = json.indexOf('{', abreArray);
        if (abreObjeto < 0) return null;

        int profundidade = 0;
        int pos = abreObjeto;
        while (pos < json.length()) {
            char c = json.charAt(pos);
            if (c == '{') profundidade++;
            else if (c == '}') {
                profundidade--;
                if (profundidade == 0) {
                    return json.substring(abreObjeto, pos + 1);
                }
            }
            pos++;
        }
        return null;
    }

    public String grausParaDirecao(double graus) {
        String[] direcoes = {"N", "NE", "L", "SE", "S", "SO", "O", "NO"};
        int indice = (int) Math.round(graus / 45.0) % 8;
        return direcoes[indice];
    }
}
