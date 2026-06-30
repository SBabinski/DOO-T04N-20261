import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class TelaLista extends JFrame {

    // guarda a lista recebida
    private List<Serie> series;

    // componentes da tela
    private JList<Serie> lista;

    private DefaultListModel<Serie> modelo;

    private JComboBox<String> cbOrdenacao;

    private JButton btnRemover;

    private Usuario usuario;

    public TelaLista(
            String titulo,
            List<Serie> series,
            Usuario usuario) {

        this.series = series;
        this.usuario = usuario;

        setTitle(titulo);

        setSize(600, 400);

        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // painel superior com as opcoes de ordenacao
        JPanel painelSuperior = new JPanel();

        cbOrdenacao = new JComboBox<>();

        cbOrdenacao.addItem("Nome");
        cbOrdenacao.addItem("Nota");
        cbOrdenacao.addItem("Status");
        cbOrdenacao.addItem("Estreia");

        btnRemover = new JButton("Remover Série");

        painelSuperior.add(
                new JLabel("Ordenar por:")
        );

        painelSuperior.add(cbOrdenacao);

        painelSuperior.add(btnRemover);

        add(
                painelSuperior,
                BorderLayout.NORTH
        );

        // modelo que vai armazenar as series da lista
        modelo = new DefaultListModel<>();

        // pega todas as series recebidas e coloca na lista visual
        for (Serie serie : series) {

            modelo.addElement(
                    serie
            );
        }

        // lista visual que mostra as series
        lista = new JList<>(modelo);

        JScrollPane scroll =
                new JScrollPane(lista);

        add(
                scroll,
                BorderLayout.CENTER
        );

        configurarEventos();

        setVisible(true);
    }

    // aqui ficam os eventos dos botoes da tela
    private void configurarEventos() {

        btnRemover.addActionListener(e -> {

            Serie serie =
                    lista.getSelectedValue();

            if (serie != null) {

                series.remove(serie);

                modelo.removeElement(
                        serie
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Série removida!"
                );
            }
        });

        // quando trocar a opcao da combo box, ordena a lista
        cbOrdenacao.addActionListener(e -> {

            String criterio =
                    (String) cbOrdenacao.getSelectedItem();

            if (criterio == null) {
                return;
            }

            switch (criterio) {

                case "Nome":

                    Collections.sort(
                            series,
                            Comparator.comparing(
                                    Serie::getNome
                            )
                    );

                    break;

                case "Nota":

                    Collections.sort(
                            series,
                            Comparator.comparingDouble(
                                    Serie::getNota
                            ).reversed()
                    );

                    break;

                case "Status":

                    Collections.sort(
                            series,
                            Comparator.comparing(
                                    Serie::getStatus
                            )
                    );

                    break;

                case "Estreia":

                    Collections.sort(
                            series,
                            Comparator.comparing(
                                    Serie::getEstreia
                            )
                    );

                    break;
            }

            atualizarLista();

        });

    }

    // limpa e monta a lista denovo depois da ordenacao
    private void atualizarLista() {

        modelo.clear();

        for (Serie serie : series) {

            modelo.addElement(
                    serie
            );
        }

    }

}