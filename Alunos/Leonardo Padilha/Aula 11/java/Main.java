import javax.swing.SwingUtilities;
import view.WeatherApp;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new WeatherApp().setVisible(true);
        });
    }
}