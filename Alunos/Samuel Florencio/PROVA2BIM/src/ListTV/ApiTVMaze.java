package ListTV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// classe que fala com a API do TVMaze pra buscar series pelo nome
public class ApiTVMaze {

    private static final int TIMEOUT_MS = 8000; // tempo de espera de 8 segundos por uma resposta

    public List<SerieTV> buscarSerie(String nome) throws TvMazeException {
        List<SerieTV> resultados = new ArrayList<>();
        HttpURLConnection conn = null;

        try {
            // troca espaco por %20 em vez de + (fica mais correto pra URL)
            String nomeCodificado = URLEncoder.encode(nome, StandardCharsets.UTF_8.toString())
                                              .replaceAll("\\+", "%20");
            String urlStr = "https://api.tvmaze.com/search/shows?q=" + nomeCodificado;
            URL url = URI.create(urlStr).toURL();
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(TIMEOUT_MS);
            conn.setReadTimeout(TIMEOUT_MS);
            int statusCode = conn.getResponseCode();
            if (statusCode != 200) {
                throw new TvMazeException("A API do TVMaze respondeu com codigo " + statusCode + ".");
            }

            String resposta = lerResposta(conn);

            Object parsed = JsonUtil.parse(resposta);
            List<Object> array = JsonUtil.asList(parsed);
            for (Object item : array) {
                Map<String, Object> wrapper = JsonUtil.asMap(item);
                Map<String, Object> show = JsonUtil.asMap(wrapper.get("show"));
                if (show != null && !show.isEmpty()) {
                    resultados.add(converterParaSerieTV(show));
                }
            }

            return resultados;

        } catch (IOException e) {
            throw new TvMazeException("Falha de conexao com a API do TVMaze. "
                    + "Verifique sua internet e tente novamente.", e);
        } catch (Exception e) {
            // pega qualquer outro erro que pode acontecer (parse, nulo, etc)
            throw new TvMazeException("Erro inesperado ao processar a resposta da API.", e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private String lerResposta(HttpURLConnection conn) throws IOException {
        StringBuilder resposta = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                resposta.append(linha);
            }
        }
        return resposta.toString();
    }

    @SuppressWarnings("unchecked")
    private SerieTV converterParaSerieTV(Map<String, Object> show) {
        int id = JsonUtil.getInt(show, "id");
        String nomeSerie = JsonUtil.getString(show, "name");
        String idioma = JsonUtil.getString(show, "language");
        String status = JsonUtil.getString(show, "status");
        String estreia = JsonUtil.getString(show, "premiered");
        String termino = JsonUtil.getString(show, "ended");

        // emissora pode vir em "network" (TV aberta/cabo) ou "webChannel" (streaming)
        String emissora = "N/A";
        Object networkObj = show.get("network");
        Object webChannelObj = show.get("webChannel");
        if (networkObj instanceof Map) {
            Object n = ((Map<String, Object>) networkObj).get("name");
            if (n != null) emissora = String.valueOf(n);
        } else if (webChannelObj instanceof Map) {
            Object n = ((Map<String, Object>) webChannelObj).get("name");
            if (n != null) emissora = String.valueOf(n);
        }

        Double nota = null;
        Object ratingObj = show.get("rating");
        if (ratingObj instanceof Map) {
            nota = JsonUtil.getDouble((Map<String, Object>) ratingObj, "average");
        }

        // generos vem como uma lista de strings
        List<String> generos = new ArrayList<>();
        Object genresObj = show.get("genres");
        if (genresObj instanceof List) {
            for (Object g : (List<Object>) genresObj) {
                generos.add(String.valueOf(g));
            }
        }

        return new SerieTV(id, nomeSerie, idioma, generos, nota, status, estreia, termino, emissora);
    }
}

// excecao pra erro especifico de quando algo da errado na API do TVMaze
class TvMazeException extends Exception {
    public TvMazeException(String mensagem) {
        super(mensagem);
    }
    public TvMazeException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}

class JsonUtil {

    public static Object parse(String json) {
        Parser p = new Parser(json);
        p.skipWhitespace();
        Object valor = p.parseValue();
        p.skipWhitespace();
        return valor;
    }

    // classe que percorre o texto JSON caractere por caractere
    private static class Parser {
        private final String s;
        private int pos;

        Parser(String s) {
            this.s = s;
            this.pos = 0;
        }

        void skipWhitespace() {
            while (pos < s.length() && Character.isWhitespace(s.charAt(pos))) pos++;
        }

        char peek() {
            return s.charAt(pos);
        }

        // olha o caractere atual e decide que tipo de valor vem a seguir
        Object parseValue() {
            skipWhitespace();
            char c = peek();
            switch (c) {
                case '{': return parseObject();
                case '[': return parseArray();
                case '"': return parseString();
                case 't':
                case 'f': return parseBoolean();
                case 'n': return parseNull();
                default: return parseNumber();
            }
        }

        Map<String, Object> parseObject() {
            Map<String, Object> map = new LinkedHashMap<>();
            pos++; // pula o {
            skipWhitespace();
            if (peek() == '}') { pos++; return map; }
            while (true) {
                skipWhitespace();
                String chave = parseString();
                skipWhitespace();
                if (peek() != ':') throw new RuntimeException("JSON invalido: esperado ':' na posicao " + pos);
                pos++; // pula o :
                Object valor = parseValue();
                map.put(chave, valor);
                skipWhitespace();
                char c = peek();
                if (c == ',') { pos++; continue; }
                if (c == '}') { pos++; break; }
                throw new RuntimeException("JSON invalido: esperado ',' ou '}' na posicao " + pos);
            }
            return map;
        }

        List<Object> parseArray() {
            List<Object> lista = new ArrayList<>();
            pos++; // pula o [
            skipWhitespace();
            if (peek() == ']') { pos++; return lista; }
            while (true) {
                Object valor = parseValue();
                lista.add(valor);
                skipWhitespace();
                char c = peek();
                if (c == ',') { pos++; continue; }
                if (c == ']') { pos++; break; }
                throw new RuntimeException("JSON invalido: esperado ',' ou ']' na posicao " + pos);
            }
            return lista;
        }

        String parseString() {
            if (peek() != '"') throw new RuntimeException("JSON invalido: esperada aspas na posicao " + pos);
            pos++; 
            StringBuilder sb = new StringBuilder();
            while (true) {
                char c = s.charAt(pos++);
                if (c == '"') break;
                if (c == '\\') {
                    char esc = s.charAt(pos++);
                    switch (esc) {
                        case '"': sb.append('"'); break;
                        case '\\': sb.append('\\'); break;
                        case '/': sb.append('/'); break;
                        case 'n': sb.append('\n'); break;
                        case 't': sb.append('\t'); break;
                        case 'r': sb.append('\r'); break;
                        case 'b': sb.append('\b'); break;
                        case 'f': sb.append('\f'); break;
                        case 'u':
                            String hex = s.substring(pos, pos + 4);
                            sb.append((char) Integer.parseInt(hex, 16));
                            pos += 4;
                            break;
                        default: sb.append(esc);
                    }
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }

        Object parseNumber() {
            int inicio = pos;
            if (peek() == '-') pos++;
            while (pos < s.length() && (Character.isDigit(s.charAt(pos)) || s.charAt(pos) == '.'
                    || s.charAt(pos) == 'e' || s.charAt(pos) == 'E' || s.charAt(pos) == '+' || s.charAt(pos) == '-')) {
                pos++;
            }
            String numStr = s.substring(inicio, pos);
            return Double.parseDouble(numStr); // sempre devolve como Double
        }

        Boolean parseBoolean() {
            if (s.startsWith("true", pos)) { pos += 4; return Boolean.TRUE; }
            if (s.startsWith("false", pos)) { pos += 5; return Boolean.FALSE; }
            throw new RuntimeException("JSON invalido: valor booleano esperado na posicao " + pos);
        }

        Object parseNull() {
            if (s.startsWith("null", pos)) { pos += 4; return null; }
            throw new RuntimeException("JSON invalido: 'null' esperado na posicao " + pos);
        }
    }

    // metodos pra acessar os dados parseados sem dar erro toda hora

    @SuppressWarnings("unchecked")
    public static Map<String, Object> asMap(Object o) {
        if (o == null) return new LinkedHashMap<>();
        if (o instanceof Map) return (Map<String, Object>) o;
        throw new RuntimeException("Esperado um objeto JSON (map), mas veio: " + o.getClass());
    }

    @SuppressWarnings("unchecked")
    public static List<Object> asList(Object o) {
        if (o == null) return new ArrayList<>();
        if (o instanceof List) return (List<Object>) o;
        throw new RuntimeException("Esperada uma lista JSON (array), mas veio: " + o.getClass());
    }

    public static String getString(Map<String, Object> map, String chave) {
        Object v = map.get(chave);
        return (v == null) ? null : String.valueOf(v);
    }

    public static int getInt(Map<String, Object> map, String chave) {
        Object v = map.get(chave);
        if (v == null) return 0;
        if (v instanceof Number) return ((Number) v).intValue();
        return Integer.parseInt(String.valueOf(v));
    }

    public static Double getDouble(Map<String, Object> map, String chave) {
        Object v = map.get(chave);
        if (v == null) return null;
        if (v instanceof Number) return ((Number) v).doubleValue();
        return Double.valueOf(String.valueOf(v));
    }

    // escapa caracteres especiais pra poder colocar um texto dentro de um JSON sem quebrar
    public static String escape(String texto) {
        if (texto == null) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            switch (c) {
                case '"': sb.append("\\\""); break;
                case '\\': sb.append("\\\\"); break;
                case '\n': sb.append("\\n"); break;
                case '\r': sb.append("\\r"); break;
                case '\t': sb.append("\\t"); break;
                default:
                    if (c < 0x20) {
                        sb.append(String.format("\\u%04x", (int) c));
                    } else {
                        sb.append(c);
                    }
            }
        }
        return sb.toString();
    }
}