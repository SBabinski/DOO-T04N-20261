package weather;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonUtils {

    public static String extrairTexto(String json, String campo) {
        Pattern pattern = Pattern.compile("\"" + campo + "\"\\s*:\\s*\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return null;
    }

    public static Double extrairNumero(String json, String campo) {
        Pattern pattern = Pattern.compile("\"" + campo + "\"\\s*:\\s*(null|-?\\d+(\\.\\d+)?)");
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            String valor = matcher.group(1);

            if (valor == null || valor.equals("null")) {
                return null;
            }

            return Double.parseDouble(valor);
        }

        return null;
    }

    public static String extrairObjeto(String json, String campo) {
        String busca = "\"" + campo + "\"";
        int posCampo = json.indexOf(busca);

        if (posCampo == -1) {
            return null;
        }

        int inicioObjeto = json.indexOf("{", posCampo);

        if (inicioObjeto == -1) {
            return null;
        }

        return capturarObjeto(json, inicioObjeto);
    }

    public static String extrairPrimeiroObjetoDoArray(String json, String campo) {
        String busca = "\"" + campo + "\"";
        int posCampo = json.indexOf(busca);

        if (posCampo == -1) {
            return null;
        }

        int inicioArray = json.indexOf("[", posCampo);

        if (inicioArray == -1) {
            return null;
        }

        int inicioObjeto = json.indexOf("{", inicioArray);

        if (inicioObjeto == -1) {
            return null;
        }

        return capturarObjeto(json, inicioObjeto);
    }

    private static String capturarObjeto(String json, int inicioObjeto) {
        int contador = 0;

        for (int i = inicioObjeto; i < json.length(); i++) {
            char c = json.charAt(i);

            if (c == '{') {
                contador++;
            } else if (c == '}') {
                contador--;

                if (contador == 0) {
                    return json.substring(inicioObjeto, i + 1);
                }
            }
        }

        return null;
    }
}