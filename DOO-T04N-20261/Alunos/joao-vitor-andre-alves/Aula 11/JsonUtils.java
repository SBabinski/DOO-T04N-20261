import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonUtils {

    private JsonUtils() {
    }

    public static String extractString(String json, String fieldName) {
        if (json == null) {
            return null;
        }

        Matcher matcher = Pattern
                .compile("\"" + Pattern.quote(fieldName) + "\"\\s*:\\s*\"(.*?)\"")
                .matcher(json);

        if (!matcher.find()) {
            return null;
        }

        return matcher.group(1);
    }

    public static Double extractDouble(String json, String fieldName) {
        if (json == null) {
            return null;
        }

        Matcher matcher = Pattern
                .compile("\"" + Pattern.quote(fieldName) + "\"\\s*:\\s*(-?\\d+(?:\\.\\d+)?)")
                .matcher(json);

        if (!matcher.find()) {
            return null;
        }

        return Double.parseDouble(matcher.group(1));
    }

    public static String extractObject(String json, String fieldName) {
        if (json == null) {
            return null;
        }

        int fieldStart = json.indexOf("\"" + fieldName + "\"");

        if (fieldStart < 0) {
            return null;
        }

        int objectStart = json.indexOf('{', fieldStart);
        return extractBlock(json, objectStart, '{', '}');
    }

    public static String extractArray(String json, String fieldName) {
        if (json == null) {
            return null;
        }

        int fieldStart = json.indexOf("\"" + fieldName + "\"");

        if (fieldStart < 0) {
            return null;
        }

        int arrayStart = json.indexOf('[', fieldStart);
        return extractBlock(json, arrayStart, '[', ']');
    }

    public static String firstObjectFromArray(String arrayJson) {
        if (arrayJson == null) {
            return null;
        }

        int objectStart = arrayJson.indexOf('{');
        return extractBlock(arrayJson, objectStart, '{', '}');
    }

    private static String extractBlock(String text, int start, char open, char close) {
        if (start < 0) {
            return null;
        }

        int level = 0;

        for (int i = start; i < text.length(); i++) {
            char current = text.charAt(i);

            if (current == open) {
                level++;
            } else if (current == close) {
                level--;

                if (level == 0) {
                    return text.substring(start, i + 1);
                }
            }
        }

        return null;
    }
}
