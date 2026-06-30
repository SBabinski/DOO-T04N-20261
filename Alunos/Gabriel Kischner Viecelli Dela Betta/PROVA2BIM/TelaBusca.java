import javax.swing.*;
import java.awt.*;

public class TelaBusca extends JFrame {

    private Usuario usuario;

    private JTextField txtPesquisa;

    private JButton btnBuscar;

    private JList<Serie> listaSeries;

    private DefaultListModel<Serie> modeloLista;

    private JTextArea areaDetalhes;

    private JButton btnFavorito;
    private JButton btnAssistida;
    private JButton btnDesejaAssistir;

    public TelaBusca(Usuario usuario) {

        this.usuario = usuario;

        setTitle("Buscar Série");
        setSize(800, 500);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        //botao de vbussa, faz oque o nome diz, ele é o buscar, lupinha


        JPanel painelPesquisa = new JPanel();

        txtPesquisa = new JTextField(25);

        btnBuscar = new JButton("Buscar");

        painelPesquisa.add(
                new JLabel("Nome da Série:")
        );

        painelPesquisa.add(txtPesquisa);

        painelPesquisa.add(btnBuscar);

        add(
                painelPesquisa,
                BorderLayout.NORTH
        );


        modeloLista = new DefaultListModel<>();

        listaSeries =
                new JList<>(modeloLista);

        JScrollPane scrollLista =
                new JScrollPane(listaSeries);
                // vai tipo guardar as serie tlg


        areaDetalhes = new JTextArea();
        // esse aqui vai mostrar os detalhes das series

        areaDetalhes.setEditable(false);

        JScrollPane scrollDetalhes =
                new JScrollPane(areaDetalhes);


        JSplitPane splitPane =
                new JSplitPane(
                        JSplitPane.HORIZONTAL_SPLIT,
                        scrollLista,
                        scrollDetalhes
                );

        add(
                splitPane,
                BorderLayout.CENTER
        );


        JPanel painelBotoes = new JPanel();
// botoes pra favoritar , marcar assistiva enfim, eles executam algumas acoes dentro do sistema
        btnFavorito =
                new JButton("Favoritar");

        btnAssistida =
                new JButton("Marcar Assistida");

        btnDesejaAssistir =
                new JButton("Deseja Assistir");

        painelBotoes.add(btnFavorito);
        painelBotoes.add(btnAssistida);
        painelBotoes.add(btnDesejaAssistir);

        add(
                painelBotoes,
                BorderLayout.SOUTH
        );

       

        configurarEventos();

        setVisible(true);
    }
// la em cima vai puxar sse aqui Gabriel, lembra disso, pra dai sim executar os comandso
    private void configurarEventos() {
        btnBuscar.addActionListener(e -> {

    try {

        String pesquisa =
                txtPesquisa.getText();

        if (pesquisa.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite o nome de uma série."
            );

            return;
        }

        modeloLista.clear();

        ServicoTvMaze servico =
                new ServicoTvMaze();

        var resultados =
                servico.buscarSeries(
                        pesquisa
                );

        for (Serie serie : resultados) {

            modeloLista.addElement(
                    serie
            );
        }

    }
    catch (Exception ex) {

        JOptionPane.showMessageDialog(
                this,
                "Erro ao buscar séries."
        );

        ex.printStackTrace();
    }
});

        listaSeries.addListSelectionListener(e -> {

            Serie serie =
                    listaSeries.getSelectedValue();

            if (serie != null) {

                areaDetalhes.setText(
                        "Nome: " + serie.getNome()
                        + "\nIdioma: " + serie.getIdioma()
                        + "\nGêneros: " + serie.getGeneros()
                        + "\nNota: " + serie.getNota()
                        + "\nStatus: " + serie.getStatus()
                        + "\nEstreia: " + serie.getEstreia()
                        + "\nFim: " + serie.getTermino()
                        + "\nEmissora: " + serie.getEmissora()
                );
            }
        });

        btnFavorito.addActionListener(e -> {

            Serie serie =
                    listaSeries.getSelectedValue();

            if (serie != null) {

                usuario.adicionarFavorito(
                        serie
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Adicionada aos favoritos!"
                );
            }
        });

        btnAssistida.addActionListener(e -> {

            Serie serie =
                    listaSeries.getSelectedValue();

            if (serie != null) {

                usuario.adicionarAssistida(
                        serie
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Marcada como assistida!"
                );
            }
        });

        btnDesejaAssistir.addActionListener(e -> {

            Serie serie =
                    listaSeries.getSelectedValue();

            if (serie != null) {

                usuario.adicionarDesejaAssistir(
                        serie
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Adicionada à lista!"
                );
            }
        });

    }
}