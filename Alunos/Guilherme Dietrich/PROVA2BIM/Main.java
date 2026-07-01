package objetos;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception erroLookAndFeel) {
                // Mantem o Look & Feel padrao do Swing sem interromper o programa
            }

            iniciarPrograma();
        });
    }

    private static void iniciarPrograma() {
        PersistenciaJSON armazenamento = new PersistenciaJSON();
        Usuario usuarioAtual;

        try {
            // se tiver dados salvos ele carrega
            usuarioAtual = armazenamento.carregar();

            if (usuarioAtual == null) {
                // caso seja a primeira vez
                usuarioAtual = solicitarNomeUsuario();

                if (usuarioAtual == null) {
                    System.exit(0);
                    return;
                }
            } else {
                // se voltar aparece as boas vindas
                JOptionPane.showMessageDialog(null,
                        "Bem-vindo de volta, " + usuarioAtual.getNome() + "!\n" +
                                "Seus dados foram carregados com sucesso.",
                        "Ola!", JOptionPane.INFORMATION_MESSAGE);
            }

            // pra nao dar erro
        } catch (Exception erroCarregamento) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao carregar dados salvos: " + erroCarregamento.getMessage() + "\n" +
                            "O programa iniciara com dados novos.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);

            usuarioAtual = solicitarNomeUsuario();
            if (usuarioAtual == null) {
                System.exit(0);
                return;
            }
        }

        MainFrame janelaPrincipal = new MainFrame(usuarioAtual, armazenamento);
        janelaPrincipal.setVisible(true);
    }

    private static Usuario solicitarNomeUsuario() {
        String nomeInformado = null;

        while (nomeInformado == null || nomeInformado.trim().isEmpty()) {
            nomeInformado = JOptionPane.showInputDialog(null,
                    "Bem-vindo ao SeriesTV! \n\nDigite seu nome?",
                    "Primeiro acesso", JOptionPane.QUESTION_MESSAGE);

            if (nomeInformado == null) {
                return null;
            }

            if (nomeInformado.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "Por favor, digite seu nome para continuar.",
                        "Nome obrigatorio", JOptionPane.WARNING_MESSAGE);
            }
        }

        return new Usuario(nomeInformado.trim());
    }
}
