package weather;

import java.util.Locale;

public class WindUtils {

    public static String formatarDirecao(Double graus) {
        if (graus == null) {
            return "-";
        }

        return String.format(Locale.US, "%.0f° (%s)", graus, direcaoCardeal(graus));
    }

    private static String direcaoCardeal(double graus) {
        String[] direcoes = {
                "N", "NE", "L", "SE", "S", "SO", "O", "NO"
        };

        int indice = (int) Math.round(graus / 45.0) % 8;

        return direcoes[indice];
    }
}