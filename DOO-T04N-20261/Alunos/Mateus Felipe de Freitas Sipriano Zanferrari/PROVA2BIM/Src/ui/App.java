package ui;

import javax.swing.*;

/**
 * Classe de entrada da aplicação.
 * Separa o `App` da lógica da interface (classe Interface).
 */
public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            // Instancia e mostra a janela principal
            new Interface().setVisible(true);
        });
    }
}
