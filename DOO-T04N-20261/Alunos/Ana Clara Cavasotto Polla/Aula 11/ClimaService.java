package fag;

import com.google.gson.Gson;
import fag.objetos.DadosClima;
import fag.objetos.CondicoesAtuais;
import fag.objetos.DiaClima;
import fag.objetos.Previsao;
import java.net.URLEncoder;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ClimaService {

    private static final String NOME_VARIAVEL_CHAVE = "ChaveApiClima";

    private final HttpClient client = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public DadosClima consultarCidade(String cidade) throws Exception {

        String chave = System.getenv(NOME_VARIAVEL_CHAVE);

        if (chave == null || chave.isBlank()) {
            throw new IllegalStateException(
                    "Variavel de ambiente " + NOME_VARIAVEL_CHAVE + " nao encontrada.");
        }

        if (cidade == null || cidade.isBlank()) {
            throw new IllegalArgumentException("Informe uma cidade para consultar.");
        }

        String localConsulta = cidade.trim();
        String cidadeUrl = URLEncoder.encode(localConsulta, StandardCharsets.UTF_8);

        String url =
            "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
            + cidadeUrl
            + "?unitGroup=metric"
            + "&key=" + chave
            + "&contentType=json"
            + "&include=current,days"
            + "&lang=pt";

        HttpRequest request =
            HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response =
            client.send(
                request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IllegalStateException(
                    "Erro ao consultar a API. Codigo HTTP: " + response.statusCode());
        }

        String json = response.body();

        Previsao previsao = gson.fromJson(json, Previsao.class);
        CondicoesAtuais condicoesAtuais = previsao.getCurrentConditions();
        DiaClima diaAtual = previsao.getDiaAtual();

        if (condicoesAtuais == null) {
            throw new IllegalStateException("A API nao retornou as condicoes atuais.");
        }

        String endereco = escolherEndereco(previsao.getResolvedAddress(), cidade.trim());

        return new DadosClima(
                endereco,
                condicoesAtuais.getTemp(),
                diaAtual.getTempmax(),
                diaAtual.getTempmin(),
                condicoesAtuais.getHumidity(),
                condicoesAtuais.getConditions(),
                condicoesAtuais.getPrecip(),
                condicoesAtuais.getWindspeed(),
                condicoesAtuais.getWinddir());
    }

    private String escolherEndereco(String enderecoApi, String enderecoDigitado) {
        if (enderecoApi == null || enderecoApi.isBlank()) {
            return enderecoDigitado;
        }

        if (contemCaracteresOrientais(enderecoApi)) {
            return enderecoDigitado;
        }

        return enderecoApi;
    }

    private boolean contemCaracteresOrientais(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            Character.UnicodeBlock bloco = Character.UnicodeBlock.of(texto.charAt(i));

            if (bloco == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || bloco == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                    || bloco == Character.UnicodeBlock.HIRAGANA
                    || bloco == Character.UnicodeBlock.KATAKANA
                    || bloco == Character.UnicodeBlock.HANGUL_SYLLABLES) {
                return true;
            }
        }

        return false;
    }

}
