import javax.swing.*;

public class App {

    public static void main(String[] args) {
        // Handler global: qualquer exceção não capturada na thread de eventos
        // é mostrada ao usuário em vez de derrubar o programa.
        Thread.setDefaultUncaughtExceptionHandler((thread, throwable) -> {
            throwable.printStackTrace();
            SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(null,
                            "Ocorreu um erro inesperado:\n" + throwable.getMessage()
                                    + "\n\nO programa continuará funcionando.",
                            "Erro inesperado", JOptionPane.ERROR_MESSAGE));
        });

        SwingUtilities.invokeLater(App::iniciar);
    }

    private static void iniciar() {

        SerieService service = new SerieService();

        // Carrega dados persistidos, se existirem.
        try {
            service.carregarUsuario();
        } catch (PersistenceException e) {
            JOptionPane.showMessageDialog(null,
                    "Não foi possível carregar os dados salvos:\n" + e.getMessage()
                            + "\n\nO programa iniciará com dados vazios.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }

        // Solicita o nome do usuário se ainda não houver um.
        String nome = service.getNomeUsuario();
        if (nome == null || nome.trim().isEmpty()) {
            nome = solicitarNome();
            try {
                service.definirNomeUsuario(nome);
            } catch (PersistenceException e) {
                JOptionPane.showMessageDialog(null,
                        "Não foi possível salvar o nome agora: " + e.getMessage(),
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }

        MainFrame frame = new MainFrame(service);
        frame.setVisible(true);
    }

    private static String solicitarNome() {
        String nome = null;
        while (nome == null || nome.trim().isEmpty()) {
            nome = JOptionPane.showInputDialog(null,
                    "Bem-vindo ao SérieTracker!\nComo você gostaria de ser chamado?",
                    "Identificação", JOptionPane.QUESTION_MESSAGE);
            if (nome == null) {
                // Usuário cancelou: usa um nome padrão.
                return "visitante";
            }
        }
        return nome.trim();
    }
}
