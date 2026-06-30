package Fag.gui;

import Fag.Serie;
import Fag.Usuario;
import Fag.comparadores.ComparadorEstado;
import Fag.comparadores.ComparadorEstreia;
import Fag.comparadores.ComparadorNome;
import Fag.comparadores.ComparadorNota;
import Fag.services.PersistenciaException;
import Fag.services.PersistenciaService;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// tela que mostra uma das listas do usuario, permite ordenar e remover.
public class TelaLista extends TelaBase {

    private final List<Serie> lista;
    private final Usuario usuario;
    private final PersistenciaService persistencia;

    private final DefaultListModel<Serie> modelo = new DefaultListModel<>();
    private final JList<Serie> visual = new JList<>(modelo);
    private final JComboBox<String> ordenacao = new JComboBox<>(new String[]{
            "ordem alfabética",
            "nota geral",
            "estado",
            "data de estreia"
    });

    public TelaLista(String titulo, List<Serie> lista, Usuario usuario, PersistenciaService persistencia) {
        super(titulo, 520, 460);
        this.lista = lista;
        this.usuario = usuario;
        this.persistencia = persistencia;
        montarTela();
        atualizarLista();
    }

    private void montarTela() {
        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topo.add(new JLabel("ordenar por:"));
        topo.add(ordenacao);
        JButton aplicar = new JButton("aplicar");
        aplicar.addActionListener(e -> atualizarLista());
        topo.add(aplicar);

        visual.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton detalhes = new JButton("ver detalhes");
        detalhes.addActionListener(e -> verDetalhes());
        JButton remover = new JButton("remover");
        remover.addActionListener(e -> remover());
        rodape.add(detalhes);
        rodape.add(remover);

        setLayout(new BorderLayout(8, 8));
        add(topo, BorderLayout.NORTH);
        add(new JScrollPane(visual), BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);
    }

    // aqui a stream api ordena a lista conforme o criterio escolhido.
    private void atualizarLista() {
        Comparator<Serie> criterio = escolherComparador();

        List<Serie> ordenada = lista.stream()
                .sorted(criterio)
                .collect(Collectors.toList());

        modelo.clear();
        for (Serie s : ordenada) {
            modelo.addElement(s);
        }
    }

    // escolhe o comparador conforme a opcao selecionada (polimorfismo:
    // todos sao tratados como comparator<serie>, mas cada um ordena diferente).
    private Comparator<Serie> escolherComparador() {
        switch (ordenacao.getSelectedIndex()) {
            case 1: return new ComparadorNota();
            case 2: return new ComparadorEstado();
            case 3: return new ComparadorEstreia();
            default: return new ComparadorNome();
        }
    }

    private void verDetalhes() {
        Serie s = visual.getSelectedValue();
        if (s == null) {
            mostrarInfo("selecione uma série.");
            return;
        }
        new TelaDetalhes(s).setVisible(true);
    }

    private void remover() {
        Serie s = visual.getSelectedValue();
        if (s == null) {
            mostrarInfo("selecione uma série.");
            return;
        }
        usuario.remover(lista, s);
        try {
            persistencia.salvar(usuario);
        } catch (PersistenciaException ex) {
            mostrarErro(ex.getMessage());
        }
        atualizarLista();
    }
}
