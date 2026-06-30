import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
        // Bemmmmm resumido aqui é o menu do sistema, onde as acoes vao ser escolhidas

        private Usuario usuario;

        // botoes da tela principal, ficam aqui em cima pq vao ser usados em
        // outros metodos tbm, tipo o configurarEventos
        private JButton btnBuscar;
        private JButton btnFavoritos;
        private JButton btnAssistidas;
        private JButton btnDeseja;

        public TelaPrincipal() {

                usuario = GerenciadorJson.carregar();

                if (usuario == null) {

                        usuario = new Usuario("Gabriel");

                } // cara aqui ele meiod que vai trazer os dados do json tlg
                setTitle("TV Tracker");
                setSize(500, 350);
                setLocationRelativeTo(null);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                addWindowListener(new java.awt.event.WindowAdapter() {

                        @Override
                        public void windowClosing(java.awt.event.WindowEvent e) {

                                GerenciadorJson.salvar(usuario);

                        }

                });// e aqio essa bomba salva os dados
                        

                JPanel painel = new JPanel();
                painel.setLayout(new GridLayout(5, 1, 10, 10));

                JLabel lblUsuario = new JLabel("Usuário: " + usuario.getNome());

                btnBuscar = new JButton("Buscar Série");

                btnFavoritos = new JButton("Favoritos");

                btnAssistidas = new JButton("Assistidas");

                btnDeseja = new JButton("Deseja Assistir");

                painel.add(lblUsuario);

                painel.add(btnBuscar);
                // cria o botao e ele vai abrir a tela de busca quando clicar

                painel.add(btnFavoritos);

                painel.add(btnAssistidas);

                painel.add(btnDeseja);

                add(painel);

                // chama o metodo la de baixo que configura tudo que acontece quando
                // clicar nos botoes
                configurarEventos();

                setVisible(true);
        }

        // aqui ficam todas as acoes dos botoes, ai nao mistura com o codigo da tela
        private void configurarEventos() {

                btnBuscar.addActionListener(e -> {

                        // abre a tela onde o usuario pesquisa series
                        new TelaBusca(usuario);

                });

                btnFavoritos.addActionListener(e -> {

                        new TelaLista(
                                        "Favoritos",
                                        usuario.getFavoritos(),
                                        usuario);

                });

                btnAssistidas.addActionListener(e -> {

                        new TelaLista(
                                        "Assistidas",
                                        usuario.getAssistidas(),
                                        usuario);

                });

                btnDeseja.addActionListener(e -> {

                        new TelaLista(
                                        "Deseja Assistir",
                                        usuario.getDesejaAssistir(),
                                        usuario);
                });

        }

}