package weatherapp.json;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Le um texto JSON e o converte para tipos do Java. (parser)
 *
 * Converte objetos em map, listas em List, textos em string,
 * numeros em double, valores booleanos em noolean e null em null.
 *
 * é usado para interpretar a resposta da API.
 */
public final class JsonParser {

    private final String json;
    private int pos;

    private JsonParser(String json) {
        this.json = json;
        this.pos = 0;
    }

    /**
     * Ponto de entrada publico do parser.
     *
     * @param json texto JSON bruto retornado pela API
     * @return Map representando o objeto JSON raiz
     */
    public static Map<String, Object> parseObject(String json) {
        JsonParser parser = new JsonParser(json);
        parser.skipWhitespace();
        Object result = parser.parseValue();
        if (!(result instanceof Map)) {
            throw new JsonParseException("O JSON informado nao representa um objeto na raiz.");
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> map = (Map<String, Object>) result;
        return map;
    }

    private Object parseValue() {
        skipWhitespace();
        char c = peek();
        switch (c) {
            case '{':
                return parseObjectInternal();
            case '[':
                return parseArray();
            case '"':
                return parseString();
            case 't':
            case 'f':
                return parseBoolean();
            case 'n':
                return parseNull();
            default:
                return parseNumber();
        }
    }

    private Map<String, Object> parseObjectInternal() {
        Map<String, Object> map = new LinkedHashMap<>();
        expect('{');
        skipWhitespace();
        if (peek() == '}') {
            pos++;
            return map;
        }
        while (true) {
            skipWhitespace();
            String key = parseString();
            skipWhitespace();
            expect(':');
            Object value = parseValue();
            map.put(key, value);
            skipWhitespace();
            char next = peek();
            if (next == ',') {
                pos++;
            } else if (next == '}') {
                pos++;
                break;
            } else {
                throw new JsonParseException("Esperado ',' ou '}' na posicao " + pos);
            }
        }
        return map;
    }

    private List<Object> parseArray() {
        List<Object> list = new ArrayList<>();
        expect('[');
        skipWhitespace();
        if (peek() == ']') {
            pos++;
            return list;
        }
        while (true) {
            Object value = parseValue();
            list.add(value);
            skipWhitespace();
            char next = peek();
            if (next == ',') {
                pos++;
            } else if (next == ']') {
                pos++;
                break;
            } else {
                throw new JsonParseException("Esperado ',' ou ']' na posicao " + pos);
            }
        }
        return list;
    }

    private String parseString() {
        expect('"');
        StringBuilder sb = new StringBuilder();
        while (true) {
            char c = json.charAt(pos++);
            if (c == '"') {
                break;
            }
            if (c == '\\') {
                char escaped = json.charAt(pos++);
                switch (escaped) {
                    case '"': sb.append('"'); break;
                    case '\\': sb.append('\\'); break;
                    case '/': sb.append('/'); break;
                    case 'b': sb.append('\b'); break;
                    case 'f': sb.append('\f'); break;
                    case 'n': sb.append('\n'); break;
                    case 'r': sb.append('\r'); break;
                    case 't': sb.append('\t'); break;
                    case 'u':
                        String hex = json.substring(pos, pos + 4);
                        sb.append((char) Integer.parseInt(hex, 16));
                        pos += 4;
                        break;
                    default:
                        throw new JsonParseException("Escape invalido: \\" + escaped);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private Double parseNumber() {
        int start = pos;
        if (peek() == '-') {
            pos++;
        }
        while (pos < json.length() && (Character.isDigit(peek()) || peek() == '.'
                || peek() == 'e' || peek() == 'E' || peek() == '+' || peek() == '-')) {
            pos++;
        }
        String numberStr = json.substring(start, pos);
        try {
            return Double.parseDouble(numberStr);
        } catch (NumberFormatException e) {
            throw new JsonParseException("Numero invalido na posicao " + start + ": " + numberStr);
        }
    }

    private Boolean parseBoolean() {
        if (json.startsWith("true", pos)) {
            pos += 4;
            return Boolean.TRUE;
        }
        if (json.startsWith("false", pos)) {
            pos += 5;
            return Boolean.FALSE;
        }
        throw new JsonParseException("Valor booleano invalido na posicao " + pos);
    }

    private Object parseNull() {
        if (json.startsWith("null", pos)) {
            pos += 4;
            return null;
        }
        throw new JsonParseException("Valor nulo invalido na posicao " + pos);
    }

    private void expect(char expected) {
        if (pos >= json.length() || json.charAt(pos) != expected) {
            throw new JsonParseException("Esperado '" + expected + "' na posicao " + pos);
        }
        pos++;
    }

    private char peek() {
        if (pos >= json.length()) {
            throw new JsonParseException("Fim inesperado do JSON na posicao " + pos);
        }
        return json.charAt(pos);
    }

    private void skipWhitespace() {
        while (pos < json.length() && Character.isWhitespace(json.charAt(pos))) {
            pos++;
        }
    }
}
