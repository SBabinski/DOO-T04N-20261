import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> iniciar());
    }

    private static void iniciar() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {
            // se nao achar o 'nimbus' vai usar o visual padrao
        }

        Persistencia persistencia = new Persistencia();
        Usuario usuario = carregarOuCriar(persistencia);
        new TelaPrincipal(usuario).setVisible(true);
    }

    private static Usuario carregarOuCriar(Persistencia persistencia) {
        try {
            Usuario salvo = persistencia.carregar();
            if (salvo != null) {
                return salvo;
            }
        } catch (Exception ex) {
            // arquivo corrompido ou ilegivel: comeca um usuario novo
        }

        Usuario novo = new Usuario(TelaLogin.pedirNome());
        preCarregar(novo);
        try {
            persistencia.salvar(novo);
        } catch (Exception ex) {
            // sem permissao de gravar: segue com os dados em memoria
        }
        return novo;
    }

    // dados coletados na requiscao
    // https://api.tvmaze.com/singlesearch/shows?q=breaking bad no postman
    private static void preCarregar(Usuario usuario) {
        usuario.adicionarFavorita(new Serie(169, "Breaking Bad", "English",
                List.of("Drama", "Crime", "Thriller"), 9.2, "Ended", "2008-01-20", "2013-09-29", "AMC"));
        usuario.adicionarAssistida(new Serie(2993, "Dark", "German",
                List.of("Drama", "Crime", "Science-Fiction"), 8.4, "Ended", "2017-12-01", "2020-06-27", "Netflix"));
        usuario.adicionarDesejoAssistir(new Serie(82, "Game of Thrones", "English",
                List.of("Drama", "Adventure", "Fantasy"), 9.0, "Ended", "2011-04-17", "2019-05-19", "HBO"));
    }
}