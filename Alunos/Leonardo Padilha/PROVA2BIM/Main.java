import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import controller.UsuarioController;
import view.TelaPrincipal;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Em try para seguir com o tema padrão em caso de erro
        }

        SwingUtilities.invokeLater(() -> {
            UsuarioController usuarioController = new UsuarioController();

            if (usuarioController.getUsuario().getNome() == null ||
                    usuarioController.getUsuario().getNome().trim().isEmpty()) {

                String nomeInseridoInput = null;

                while (nomeInseridoInput == null || nomeInseridoInput.trim().isEmpty()) {
                    nomeInseridoInput = JOptionPane.showInputDialog(
                            null,
                            "Bem-vindo!\nPor favor, digite usuario:",
                            "Primeiro Acesso (Sist. Prova)",
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (nomeInseridoInput == null) {
                        System.exit(0);
                    }
                }

                usuarioController.atualizarNomeUsuario(nomeInseridoInput.trim());
            }

            TelaPrincipal telaMenuPrincipal = new TelaPrincipal(usuarioController);
            telaMenuPrincipal.setVisible(true);
        });
    }
}