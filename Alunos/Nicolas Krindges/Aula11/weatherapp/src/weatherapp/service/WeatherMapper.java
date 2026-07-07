package weatherapp.service;

import weatherapp.exception.WeatherApiException;
import weatherapp.json.JsonParseException;
import weatherapp.json.JsonParser;
import weatherapp.model.WeatherData;

import java.util.List;
import java.util.Map;

/**
 * Converte o JSON bruto retornado pela Timeline Weather API
 * (Visual Crossing) em um objeto {@link WeatherData} pronto para
 * ser exibido na interface grafica.
 */
final class WeatherMapper {

    private WeatherMapper() {
        // classe utilitaria, nao deve ser instanciada
    }

    static WeatherData paraWeatherData(String cidade, String json) throws WeatherApiException {
        Map<String, Object> raiz;
        try {
            raiz = JsonParser.parseObject(json);
        } catch (JsonParseException e) {
            throw new WeatherApiException("Resposta da API em formato inesperado.", e);
        }

        Map<String, Object> currentConditions = obterMapa(raiz, "currentConditions");
        Map<String, Object> diaDeHoje = obterPrimeiroDia(raiz);

        double temperaturaAtual = obterNumero(currentConditions, "temp");
        double umidade = obterNumero(currentConditions, "humidity");
        String condicao = obterTexto(currentConditions, "conditions");
        double precipitacao = obterNumero(currentConditions, "precip");
        double velocidadeVento = obterNumero(currentConditions, "windspeed");
        double direcaoVento = obterNumero(currentConditions, "winddir");

        double temperaturaMaxima = obterNumero(diaDeHoje, "tempmax");
        double temperaturaMinima = obterNumero(diaDeHoje, "tempmin");

        return new WeatherData(
                cidade,
                temperaturaAtual,
                temperaturaMaxima,
                temperaturaMinima,
                umidade,
                condicao,
                precipitacao,
                velocidadeVento,
                direcaoVento
        );
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> obterMapa(Map<String, Object> pai, String chave) throws WeatherApiException {
        Object valor = pai.get(chave);
        if (!(valor instanceof Map)) {
            throw new WeatherApiException(
                    "Campo obrigatorio ausente na resposta da API: \"" + chave + "\".");
        }
        return (Map<String, Object>) valor;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> obterPrimeiroDia(Map<String, Object> raiz) throws WeatherApiException {
        Object diasObj = raiz.get("days");
        if (!(diasObj instanceof List) || ((List<Object>) diasObj).isEmpty()) {
            throw new WeatherApiException("A resposta da API nao contem dados diarios (\"days\").");
        }
        List<Object> dias = (List<Object>) diasObj;
        Object primeiroDia = dias.get(0);
        if (!(primeiroDia instanceof Map)) {
            throw new WeatherApiException("Formato invalido para os dados diarios.");
        }
        return (Map<String, Object>) primeiroDia;
    }

    private static double obterNumero(Map<String, Object> mapa, String chave) {
        Object valor = mapa.get(chave);
        if (valor instanceof Double) {
            return (Double) valor;
        }
        return 0.0;
    }

    private static String obterTexto(Map<String, Object> mapa, String chave) {
        Object valor = mapa.get(chave);
        if (valor instanceof String) {
            return (String) valor;
        }
        return "Nao informado";
    }
}
