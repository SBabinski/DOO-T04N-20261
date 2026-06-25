import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Parser JSON mínimo em Java puro (sem bibliotecas externas nem build).
 *
 * <p>Converte um texto JSON em estruturas nativas:
 * objeto -&gt; {@link Map}, array -&gt; {@link List}, texto -&gt; {@link String},
 * número -&gt; {@link Double}, boolean -&gt; {@link Boolean}, null -&gt; {@code null}.
 */
final class Json {

    private final String txt;
    private int pos;

    private Json(String txt) {
        this.txt = txt;
    }

    /** Converte um texto JSON em objetos Java. */
    static Object parse(String txt) {
        Json p = new Json(txt);
        p.espacos();
        Object valor = p.valor();
        p.espacos();
        if (p.pos < p.txt.length()) {
            throw new IllegalArgumentException("Conteúdo extra após o JSON.");
        }
        return valor;
    }

    private Object valor() {
        char c = atual();
        return switch (c) {
            case '{' -> objeto();
            case '[' -> array();
            case '"' -> string();
            case 't', 'f' -> bool();
            case 'n' -> nulo();
            default -> numero();
        };
    }

    private Map<String, Object> objeto() {
        Map<String, Object> mapa = new LinkedHashMap<>();
        usar('{');
        espacos();
        if (atual() == '}') {
            usar('}');
            return mapa;
        }
        while (true) {
            espacos();
            String chave = string();
            espacos();
            usar(':');
            espacos();
            mapa.put(chave, valor());
            espacos();
            char c = proximo();
            if (c == '}') return mapa;
            if (c != ',') throw new IllegalArgumentException("Esperado ',' ou '}'.");
        }
    }

    private List<Object> array() {
        List<Object> lista = new ArrayList<>();
        usar('[');
        espacos();
        if (atual() == ']') {
            usar(']');
            return lista;
        }
        while (true) {
            espacos();
            lista.add(valor());
            espacos();
            char c = proximo();
            if (c == ']') return lista;
            if (c != ',') throw new IllegalArgumentException("Esperado ',' ou ']'.");
        }
    }

    private String string() {
        usar('"');
        StringBuilder sb = new StringBuilder();
        while (true) {
            char c = proximo();
            if (c == '"') return sb.toString();
            if (c == '\\') {
                char e = proximo();
                switch (e) {
                    case '"' -> sb.append('"');
                    case '\\' -> sb.append('\\');
                    case '/' -> sb.append('/');
                    case 'b' -> sb.append('\b');
                    case 'f' -> sb.append('\f');
                    case 'n' -> sb.append('\n');
                    case 'r' -> sb.append('\r');
                    case 't' -> sb.append('\t');
                    case 'u' -> {
                        sb.append((char) Integer.parseInt(txt.substring(pos, pos + 4), 16));
                        pos += 4;
                    }
                    default -> throw new IllegalArgumentException("Escape inválido: \\" + e);
                }
            } else {
                sb.append(c);
            }
        }
    }

    private Double numero() {
        int ini = pos;
        while (pos < txt.length() && "+-.eE0123456789".indexOf(txt.charAt(pos)) >= 0) {
            pos++;
        }
        if (pos == ini) throw new IllegalArgumentException("Número inválido.");
        return Double.parseDouble(txt.substring(ini, pos));
    }

    private Boolean bool() {
        if (txt.startsWith("true", pos)) {
            pos += 4;
            return Boolean.TRUE;
        }
        if (txt.startsWith("false", pos)) {
            pos += 5;
            return Boolean.FALSE;
        }
        throw new IllegalArgumentException("Booleano inválido.");
    }

    private Object nulo() {
        if (txt.startsWith("null", pos)) {
            pos += 4;
            return null;
        }
        throw new IllegalArgumentException("Valor inválido.");
    }

    private char atual() {
        if (pos >= txt.length()) throw new IllegalArgumentException("Fim inesperado do JSON.");
        return txt.charAt(pos);
    }

    private void usar(char esperado) {
        if (proximo() != esperado) {
            throw new IllegalArgumentException("Esperado '" + esperado + "'.");
        }
    }

    private char proximo() {
        return txt.charAt(pos++);
    }

    private void espacos() {
        while (pos < txt.length() && Character.isWhitespace(txt.charAt(pos))) {
            pos++;
        }
    }
}
