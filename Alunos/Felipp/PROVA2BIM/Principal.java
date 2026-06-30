import Fag.FonteDeSeries;
import Fag.Usuario;
import Fag.gui.TelaLogin;
import Fag.services.PersistenciaException;
import Fag.services.PersistenciaService;
import Fag.services.TVMazeService;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

class Principal {

    public static void main(String[] args) {

        // tratador global de excecoes: se algum erro nao previsto acontecer na
        // interface, ele mostra uma mensagem em vez de fechar o programa de forma inesperada.
        Thread.setDefaultUncaughtExceptionHandler((thread, erro) ->
                JOptionPane.showMessageDialog(null,
                        "ocorreu um erro inesperado: " + erro.getMessage(),
                        "erro", JOptionPane.ERROR_MESSAGE));

        PersistenciaService persistencia = new PersistenciaService();
        FonteDeSeries fonte = new TVMazeService();

        Usuario usuario;
        try {
            usuario = persistencia.carregar();
        } catch (PersistenciaException e) {
            // se o arquivo estiver corrompido ou ilegivel, comeca do zero.
            usuario = new Usuario();
        }

        final Usuario usuarioFinal = usuario;

        // rodando na thread do Swing
        SwingUtilities.invokeLater(() ->
                new TelaLogin(usuarioFinal, persistencia, fonte).setVisible(true));
    }
}
