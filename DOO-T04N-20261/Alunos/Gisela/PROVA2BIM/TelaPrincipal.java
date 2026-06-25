package PROVA2BIM;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaPrincipal extends JFrame {
    private TvMazeServico servico;

    public TelaPrincipal() {
        super("Sistema de Séries - PROVA2BIM");
        servico = new TvMazeServico();

        setLayout(new BorderLayout());

        JTextField campoBusca = new JTextField(20);
        JButton botaoBuscar = new JButton("Buscar");
        JTextArea areaResultado = new JTextArea();
        areaResultado.setEditable(false);

        JPanel painelTopo = new JPanel();
        painelTopo.add(new JLabel("Nome da série:"));
        painelTopo.add(campoBusca);
        painelTopo.add(botaoBuscar);

        add(painelTopo, BorderLayout.NORTH);
        add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        botaoBuscar.addActionListener(e -> {
            String nome = campoBusca.getText();
            List<Serie> series = servico.buscarSeries(nome);
            areaResultado.setText("");
            for (Serie s : series) {
                areaResultado.append(s.toString() + "\n");
            }
        });

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
