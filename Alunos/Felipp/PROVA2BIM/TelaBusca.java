package Fag.gui;

import Fag.FonteDeSeries;
import Fag.Serie;
import Fag.Usuario;
import Fag.services.PersistenciaException;
import Fag.services.PersistenciaService;
import Fag.services.ServiceException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

// tela de busca de series pelo nome, usando a fonte (tvmaze).
public class TelaBusca extends TelaBase {

    private final Usuario usuario;
    private final PersistenciaService persistencia;
    private final FonteDeSeries fonte;

    private final JTextField campoBusca = new JTextField(20);
    private final DefaultListModel<Serie> modelo = new DefaultListModel<>();
    private final JList<Serie> listaResultados = new JList<>(modelo);

    public TelaBusca(Usuario usuario, PersistenciaService persistencia, FonteDeSeries fonte) {
        super("buscar séries", 520, 460);
        this.usuario = usuario;
        this.persistencia = persistencia;
        this.fonte = fonte;
        montarTela();
    }

    private void montarTela() {
        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topo.add(new JLabel("nome:"));
        topo.add(campoBusca);
        JButton botaoBuscar = new JButton("buscar");
        botaoBuscar.addActionListener(e -> buscar());
        topo.add(botaoBuscar);

        listaResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton detalhes = new JButton("ver detalhes");
        detalhes.addActionListener(e -> verDetalhes());
        JButton favoritar = new JButton("add favoritos");
        favoritar.addActionListener(e -> adicionar(usuario.getFavoritos()));
        JButton assistida = new JButton("add assistidas");
        assistida.addActionListener(e -> adicionar(usuario.getAssistidas()));
        JButton desejar = new JButton("add quero assistir");
        desejar.addActionListener(e -> adicionar(usuario.getDesejaAssistir()));
        rodape.add(detalhes);
        rodape.add(favoritar);
        rodape.add(assistida);
        rodape.add(desejar);

        setLayout(new BorderLayout(8, 8));
        add(topo, BorderLayout.NORTH);
        add(new JScrollPane(listaResultados), BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);
    }

    private void buscar() {
        try {
            List<Serie> resultados = fonte.buscarPorNome(campoBusca.getText());
            modelo.clear();
            if (resultados.isEmpty()) {
                mostrarInfo("nenhuma série encontrada.");
                return;
            }
            for (Serie s : resultados) {
                modelo.addElement(s);
            }
        } catch (ServiceException ex) {
            mostrarErro(ex.getMessage());
        }
    }

    private Serie selecionada() {
        Serie s = listaResultados.getSelectedValue();
        if (s == null) {
            mostrarInfo("selecione uma série na lista.");
        }
        return s;
    }

    private void verDetalhes() {
        Serie s = selecionada();
        if (s != null) {
            new TelaDetalhes(s).setVisible(true);
        }
    }

    private void adicionar(List<Serie> lista) {
        Serie s = selecionada();
        if (s == null) {
            return;
        }
        usuario.adicionar(lista, s);
        salvar();
        mostrarInfo("\"" + s.getNome() + "\" adicionada.");
    }

    private void salvar() {
        try {
            persistencia.salvar(usuario);
        } catch (PersistenciaException ex) {
            mostrarErro(ex.getMessage());
        }
    }
}
