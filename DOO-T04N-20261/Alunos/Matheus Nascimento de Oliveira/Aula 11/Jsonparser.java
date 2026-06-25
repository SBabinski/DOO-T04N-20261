// classe auxiliar pra extrair valores do JSON sem precisar de biblioteca externa
public class JsonParser {

    private JsonParser() {}

    public static String extrairString(String json, String chave) {
        String padrao = "\"" + chave + "\"";
        int idx = json.indexOf(padrao);
        if (idx < 0) return "";

        int inicio = json.indexOf("\"", idx + padrao.length() + 1);
        if (inicio < 0) return "";

        int fim = json.indexOf("\"", inicio + 1);
        if (fim < 0) return "";

        return json.substring(inicio + 1, fim);
    }

    public static double extrairDouble(String json, String chave) {
        String padrao = "\"" + chave + "\":";
        int idx = json.indexOf(padrao);
        if (idx < 0) return 0.0;

        int inicio = idx + padrao.length();

        while (inicio < json.length() && Character.isWhitespace(json.charAt(inicio))) {
            inicio++;
        }

        int fim = inicio;
        while (fim < json.length()) {
            char c = json.charAt(fim);
            if (c == ',' || c == '}' || c == ']' || c == '\n') break;
            fim++;
        }

        String valor = json.substring(inicio, fim).trim();

        if (valor.equalsIgnoreCase("null") || valor.isEmpty()) return 0.0;

        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public static String extrairObjeto(String json, String chave) {
        String padrao = "\"" + chave + "\":";
        int idx = json.indexOf(padrao);
        if (idx < 0) return "";

        int abreChave = json.indexOf("{", idx + padrao.length());
        if (abreChave < 0) return "";

        int profundidade = 0;
        int pos = abreChave;

        while (pos < json.length()) {
            char c = json.charAt(pos);
            if (c == '{') profundidade++;
            else if (c == '}') {
                profundidade--;
                if (profundidade == 0) return json.substring(abreChave, pos + 1);
            }
            pos++;
        }

        return "";
    }

    public static String extrairPrimeiroDoArray(String json, String chave) {
        String padrao = "\"" + chave + "\":";
        int idx = json.indexOf(padrao);
        if (idx < 0) return "";

        int abreColchete = json.indexOf("[", idx + padrao.length());
        if (abreColchete < 0) return "";

        int abreChave = json.indexOf("{", abreColchete);
        if (abreChave < 0) return "";

        int profundidade = 0;
        int pos = abreChave;

        while (pos < json.length()) {
            char c = json.charAt(pos);
            if (c == '{') profundidade++;
            else if (c == '}') {
                profundidade--;
                if (profundidade == 0) return json.substring(abreChave, pos + 1);
            }
            pos++;
        }

        return "";
    }
}
