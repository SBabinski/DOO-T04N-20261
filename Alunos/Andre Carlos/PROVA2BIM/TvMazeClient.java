import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TvMazeClient {

    private static final String BASE_URL = "https://api.tvmaze.com";
    private static final int TIMEOUT_MS = 10000;

    @SuppressWarnings("unchecked")
    public List<Serie> buscarPorNome(String termo) throws ApiException {
        if (termo == null || termo.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String urlStr;
        try {
            urlStr = BASE_URL + "/search/shows?q="
                    + URLEncoder.encode(termo.trim(), StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            throw new ApiException("Erro ao montar a URL de busca.", e);
        }

        String resposta = executarGet(urlStr);

        Object parsed;
        try {
            parsed = JsonParser.parse(resposta);
        } catch (Exception e) {
            throw new ApiException("Resposta da API em formato inválido.", e);
        }

        List<Serie> resultado = new ArrayList<>();
        if (!(parsed instanceof List)) {
            return resultado;
        }

        List<Object> itens = (List<Object>) parsed;
        for (Object item : itens) {
            if (!(item instanceof Map)) {
                continue;
            }
            Map<String, Object> wrapper = (Map<String, Object>) item;
            Object showObj = wrapper.get("show");
            if (showObj instanceof Map) {
                Serie serie = mapearSerie((Map<String, Object>) showObj);
                if (serie != null) {
                    resultado.add(serie);
                }
            }
        }
        return resultado;
    }

    @SuppressWarnings("unchecked")
    private Serie mapearSerie(Map<String, Object> show) {
        try {
            Serie serie = new Serie();

            Object id = show.get("id");
            if (id instanceof Number) {
                serie.setId(((Number) id).intValue());
            }

            serie.setNome(asString(show.get("name")));
            serie.setIdioma(asString(show.get("language")));

            // Gêneros
            Object generosObj = show.get("genres");
            List<String> generos = new ArrayList<>();
            if (generosObj instanceof List) {
                for (Object g : (List<Object>) generosObj) {
                    if (g != null) {
                        generos.add(g.toString());
                    }
                }
            }
            serie.setGeneros(generos);

            // Nota geral (rating.average)
            Object ratingObj = show.get("rating");
            if (ratingObj instanceof Map) {
                Object avg = ((Map<String, Object>) ratingObj).get("average");
                if (avg instanceof Number) {
                    serie.setNotaGeral(((Number) avg).doubleValue());
                }
            }

            // Status
            String statusApi = asString(show.get("status"));
            serie.setStatusOriginal(statusApi);
            serie.setStatus(StatusSerie.fromApi(statusApi));

            // Datas
            serie.setDataEstreia(asString(show.get("premiered")));
            serie.setDataTermino(asString(show.get("ended")));

            // Emissora (network ou webChannel)
            String emissora = extrairEmissora(show);
            serie.setEmissora(emissora);

            // Imagem
            Object imageObj = show.get("image");
            if (imageObj instanceof Map) {
                serie.setUrlImagem(asString(((Map<String, Object>) imageObj).get("medium")));
            }

            // Resumo (remove tags HTML)
            String resumo = asString(show.get("summary"));
            if (resumo != null) {
                resumo = resumo.replaceAll("<[^>]+>", "").trim();
            }
            serie.setResumo(resumo);

            return serie;
        } catch (Exception e) {
            // Em caso de série malformada, ignora aquele item em vez de quebrar tudo.
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private String extrairEmissora(Map<String, Object> show) {
        Object network = show.get("network");
        if (network instanceof Map) {
            String nome = asString(((Map<String, Object>) network).get("name"));
            if (nome != null) {
                return nome;
            }
        }
        Object webChannel = show.get("webChannel");
        if (webChannel instanceof Map) {
            String nome = asString(((Map<String, Object>) webChannel).get("name"));
            if (nome != null) {
                return nome;
            }
        }
        return null;
    }

    private String asString(Object o) {
        return (o != null) ? o.toString() : null;
    }

    private String executarGet(String urlStr) throws ApiException {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(TIMEOUT_MS);
            conn.setReadTimeout(TIMEOUT_MS);
            conn.setRequestProperty("User-Agent", "SerieTracker/1.0");
            conn.setRequestProperty("Accept", "application/json");

            int codigo = conn.getResponseCode();
            if (codigo == 429) {
                throw new ApiException("Limite de requisições atingido. Aguarde alguns segundos e tente novamente.");
            }
            if (codigo < 200 || codigo >= 300) {
                throw new ApiException("A API retornou o código HTTP " + codigo + ".");
            }

            try (InputStream is = conn.getInputStream();
                 BufferedReader br = new BufferedReader(
                         new InputStreamReader(is, StandardCharsets.UTF_8))) {
                StringBuilder sb = new StringBuilder();
                String linha;
                while ((linha = br.readLine()) != null) {
                    sb.append(linha);
                }
                return sb.toString();
            }
        } catch (ApiException e) {
            throw e;
        } catch (java.net.UnknownHostException e) {
            throw new ApiException("Sem conexão com a internet. Verifique sua rede.", e);
        } catch (java.net.SocketTimeoutException e) {
            throw new ApiException("Tempo de resposta esgotado. Tente novamente.", e);
        } catch (Exception e) {
            throw new ApiException("Erro ao acessar a API TVMaze: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
