import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class JsonParser {

    private final String json;
    private int pos;

    private JsonParser(String json) {
        this.json = json;
        this.pos = 0;
    }

    public static Object parse(String json) {
        if (json == null) {
            return null;
        }
        JsonParser p = new JsonParser(json);
        p.skipWhitespace();
        Object result = p.parseValue();
        p.skipWhitespace();
        return result;
    }

    private Object parseValue() {
        skipWhitespace();
        if (pos >= json.length()) {
            return null;
        }
        char c = json.charAt(pos);
        switch (c) {
            case '{':
                return parseObject();
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

    private Map<String, Object> parseObject() {
        Map<String, Object> map = new LinkedHashMap<>();
        pos++; // consome '{'
        skipWhitespace();
        if (peek() == '}') {
            pos++;
            return map;
        }
        while (pos < json.length()) {
            skipWhitespace();
            String key = parseString();
            skipWhitespace();
            expect(':');
            Object value = parseValue();
            map.put(key, value);
            skipWhitespace();
            char c = next();
            if (c == '}') {
                break;
            } else if (c != ',') {
                throw new IllegalStateException("Esperado ',' ou '}' na posição " + pos);
            }
        }
        return map;
    }

    private List<Object> parseArray() {
        List<Object> list = new ArrayList<>();
        pos++; // consome '['
        skipWhitespace();
        if (peek() == ']') {
            pos++;
            return list;
        }
        while (pos < json.length()) {
            Object value = parseValue();
            list.add(value);
            skipWhitespace();
            char c = next();
            if (c == ']') {
                break;
            } else if (c != ',') {
                throw new IllegalStateException("Esperado ',' ou ']' na posição " + pos);
            }
        }
        return list;
    }

    private String parseString() {
        skipWhitespace();
        expect('"');
        StringBuilder sb = new StringBuilder();
        while (pos < json.length()) {
            char c = json.charAt(pos++);
            if (c == '"') {
                return sb.toString();
            } else if (c == '\\') {
                char esc = json.charAt(pos++);
                switch (esc) {
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
                        sb.append(esc);
                }
            } else {
                sb.append(c);
            }
        }
        throw new IllegalStateException("String não terminada");
    }

    private Double parseNumber() {
        int start = pos;
        while (pos < json.length()) {
            char c = json.charAt(pos);
            if (c == '-' || c == '+' || c == '.' || c == 'e' || c == 'E'
                    || (c >= '0' && c <= '9')) {
                pos++;
            } else {
                break;
            }
        }
        String num = json.substring(start, pos);
        try {
            return Double.parseDouble(num);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Número inválido: " + num);
        }
    }

    private Boolean parseBoolean() {
        if (json.startsWith("true", pos)) {
            pos += 4;
            return Boolean.TRUE;
        } else if (json.startsWith("false", pos)) {
            pos += 5;
            return Boolean.FALSE;
        }
        throw new IllegalStateException("Booleano inválido na posição " + pos);
    }

    private Object parseNull() {
        if (json.startsWith("null", pos)) {
            pos += 4;
            return null;
        }
        throw new IllegalStateException("Valor 'null' inválido na posição " + pos);
    }

    // ---- Helpers ----

    private void skipWhitespace() {
        while (pos < json.length() && Character.isWhitespace(json.charAt(pos))) {
            pos++;
        }
    }

    private char peek() {
        skipWhitespace();
        return pos < json.length() ? json.charAt(pos) : '\0';
    }

    private char next() {
        return json.charAt(pos++);
    }

    private void expect(char expected) {
        skipWhitespace();
        char c = json.charAt(pos++);
        if (c != expected) {
            throw new IllegalStateException("Esperado '" + expected + "' mas encontrado '" + c + "' na posição " + pos);
        }
    }
}
