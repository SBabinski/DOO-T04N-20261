package Fag.services;

import Fag.Serie;
import Fag.SerieComNota;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TvMazeService implements ApiService {

    private final ObjectMapper mapper = new ObjectMapper();

    // Sobrescreve a lista de series pela busca do nome.
    @Override
    public List<Serie> buscarSeries(String nome) throws Exception {
        String nomeLimpo = limparPesquisa(nome);

        if (nomeLimpo.isEmpty()) {
            throw new Exception("Digite o nome de uma série.");
        }

        // Busca na API o nome das séries.
        String nomeUrl = URLEncoder.encode(nomeLimpo, StandardCharsets.UTF_8.toString());
        String endereco = "https://api.tvmaze.com/search/shows?q=" + nomeUrl;

        String json = fazerRequisicao(endereco);
        return converterJsonParaSeries(json);
    }

    // Limpa barra de pesquisa.
    private String limparPesquisa(String texto) {
        if (texto == null) {
            return "";
        }

        // REGEX - Limpa caractere indevido.
        return texto.replaceAll("[^a-zA-Z0-9À-ÿ\\s]", "").trim();
    }

    // Requisição para consulta da API.
    private String fazerRequisicao(String endereco) throws Exception {
        URL url = new URL(endereco);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setConnectTimeout(10000);
        conexao.setReadTimeout(10000);

        int status = conexao.getResponseCode();

        if (status != 200) {
            throw new Exception("Erro ao consultar API. Código: " + status);
        }

        BufferedReader leitor = new BufferedReader(
                new InputStreamReader(conexao.getInputStream(), StandardCharsets.UTF_8)
        );

        StringBuilder resposta = new StringBuilder();
        String linha;

        while ((linha = leitor.readLine()) != null) {
            resposta.append(linha);
        }

        leitor.close();
        conexao.disconnect();

        return resposta.toString();
    }

    // Transforma o dado que vem da api para as tabelas.
    private List<Serie> converterJsonParaSeries(String json) throws Exception {
        List<Serie> series = new ArrayList<Serie>();

        // Leitor do JSON.
        JsonNode raiz = mapper.readTree(json);

        // Transforma cada dado retornado para uma variavel em lista.
        for (JsonNode item : raiz) {
            JsonNode show = item.get("show");

            int id = pegarInt(show, "id");
            String nome = pegarTexto(show, "name");
            String idioma = pegarTexto(show, "language");
            String estado = pegarTexto(show, "status");
            String dataEstreia = pegarTexto(show, "premiered");
            String dataTermino = pegarTexto(show, "ended");
            double nota = pegarNota(show);
            String emissora = pegarEmissora(show);
            List<String> generos = pegarGeneros(show);

            // Cria uma variavel para a série retornada.
            Serie serie = new SerieComNota(id, nome, idioma, generos, nota, estado, dataEstreia, dataTermino, emissora);
            series.add(serie);
        }

        return series;
    }

    // Pega um codigo para o item.
    private int pegarInt(JsonNode node, String campo) {
        if (node.has(campo) && !node.get(campo).isNull()) {
            return node.get(campo).asInt();
        }
        return 0;
    }

    // Pega o texto
    private String pegarTexto(JsonNode node, String campo) {
        if (node.has(campo) && !node.get(campo).isNull()) {
            return node.get(campo).asText();
        }
        return "Não informado";
    }

    // Pega a nota da série.
    private double pegarNota(JsonNode show) {
        JsonNode rating = show.get("rating");

        if (rating != null && rating.has("average") && !rating.get("average").isNull()) {
            return rating.get("average").asDouble();
        }

        return 0.0;
    }

    // Pega a emissora.
    private String pegarEmissora(JsonNode show) {
        JsonNode network = show.get("network");
        JsonNode webChannel = show.get("webChannel");

        if (network != null && !network.isNull() && network.has("name")) {
            return network.get("name").asText();
        }

        if (webChannel != null && !webChannel.isNull() && webChannel.has("name")) {
            return webChannel.get("name").asText();
        }

        return "Não informado";
    }

    // Pega o gênero da serie.
    private List<String> pegarGeneros(JsonNode show) {
        List<String> generos = new ArrayList<String>();
        JsonNode nodeGeneros = show.get("genres");

        if (nodeGeneros != null && nodeGeneros.isArray()) {
            for (JsonNode genero : nodeGeneros) {
                generos.add(genero.asText());
            }
        }

        return generos;
    }
}
