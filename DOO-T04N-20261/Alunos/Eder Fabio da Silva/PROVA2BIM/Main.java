package com.tvtracker;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.tvtracker.controller.AppController;
import com.tvtracker.view.MainFrame;

/**
 * Ponto de entrada da aplicação TV Tracker.
 * Inicializa o controlador e lança a interface Swing na EDT.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Usa o visual nativo do sistema operacional
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Mantém o LAF padrão do Swing caso falhe
            }

            try {
                AppController controller = new AppController();
                MainFrame frame = new MainFrame(controller);
                frame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Erro fatal ao iniciar a aplicacao:\n" + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
