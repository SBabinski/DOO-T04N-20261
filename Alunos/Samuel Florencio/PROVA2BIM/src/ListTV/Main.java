package ListTV;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {     	
            throwable.printStackTrace();
            SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(null,
                            "Ocorreu um erro inesperado.\nAlgumas Funcionalidades podem não funcionar corretamente.\n"
                                    + throwable,
                            "Erro inesperado", JOptionPane.ERROR_MESSAGE));
        });
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignorado) {
            
            }
            new TelaPrincipal().setVisible(true);
        });
    }
}
