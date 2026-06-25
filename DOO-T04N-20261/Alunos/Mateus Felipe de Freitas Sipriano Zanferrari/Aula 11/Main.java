

import javax.swing.SwingUtilities;


public class Main {

    
    public static void main(String[] args) {
        // Ativa renderização de alta qualidade em monitores HiDPI/Retina (Java 9+)
        System.setProperty("sun.java2d.uiScale", "1.0");

        // Usa o look-and-feel do sistema operacional para barras de rolagem nativas
        try {
            javax.swing.UIManager.setLookAndFeel(
                javax.swing.UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ignored) {
        // Se falhar, mantém o padrão do Swing — não é crítico
        }

        // Toda interação com componentes Swing deve acontecer na EDT
        SwingUtilities.invokeLater(WeatherUI::new);
    }
}
