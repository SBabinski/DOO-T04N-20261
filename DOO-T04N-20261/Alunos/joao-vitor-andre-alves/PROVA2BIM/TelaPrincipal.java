import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class TelaPrincipal extends JFrame {

    private final Usuario usuario;
    private final Persistencia persistencia;

    private JTextField campoBusca;
    private DefaultListModel<Serie> modeloLista;
    private JList<Serie> listaSeries;
    private JTextArea areaDetalhes;
    private List<Serie> listaAtual;

    public TelaPrincipal(Usuario usuario) {
        this.usuario = usuario;
        this.persistencia = new Persistencia();
        this.listaAtual = new ArrayList<>();

        setTitle("Minhas Series - " + usuario.getNomeOuApelido());
        setSize(820, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(criarPainelBusca(), BorderLayout.NORTH);
        add(criarPainelCentral(), BorderLayout.CENTER);
        add(criarPainelAcoes(), BorderLayout.SOUTH);
        estilizar();
    }

    private JPanel criarPainelBusca() {
        JPanel painel = new JPanel(new BorderLayout());
        campoBusca = new JTextField();
        JButton botaoBuscar = new JButton("Buscar");
        botaoBuscar.addActionListener(e -> buscar());
        painel.add(campoBusca, BorderLayout.CENTER);
        painel.add(botaoBuscar, BorderLayout.EAST);
        return painel;
    }

    private JSplitPane criarPainelCentral() {
        modeloLista = new DefaultListModel<>();
        listaSeries = new JList<>(modeloLista);
        listaSeries.addListSelectionListener(e -> mostrarDetalhes());
        JScrollPane rolagemLista = new JScrollPane(listaSeries);

        areaDetalhes = new JTextArea();
        areaDetalhes.setEditable(false);
        JScrollPane rolagemDetalhes = new JScrollPane(areaDetalhes);

        JSplitPane divisor = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, rolagemLista, rolagemDetalhes);
        divisor.setDividerLocation(300);
        return divisor;
    }

    private JPanel criarPainelAcoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton verFavoritas = new JButton("Ver favoritas");
        verFavoritas.addActionListener(e -> mostrarLista(usuario.getFavoritas()));

        JButton verAssistidas = new JButton("Ver assistidas");
        verAssistidas.addActionListener(e -> mostrarLista(usuario.getAssistidas()));

        JButton verDesejo = new JButton("Ver desejo assistir");
        verDesejo.addActionListener(e -> mostrarLista(usuario.getDesejoAssistir()));

        JButton addFavorita = new JButton("+ Favorita");
        addFavorita.addActionListener(e -> adicionarFavorita());

        JButton addAssistida = new JButton("+ Assistida");
        addAssistida.addActionListener(e -> adicionarAssistida());

        JButton addDesejo = new JButton("+ Desejo");
        addDesejo.addActionListener(e -> adicionarDesejo());

        JButton remover = new JButton("Remover da lista");
        remover.addActionListener(e -> remover());

        JComboBox<String> comboOrdenar = new JComboBox<>(
                new String[] { "Ordenar...", "Nome", "Nota", "Estado", "Estreia" });
        comboOrdenar.addActionListener(e -> ordenar(comboOrdenar.getSelectedIndex()));

        painel.add(verFavoritas);
        painel.add(verAssistidas);
        painel.add(verDesejo);
        painel.add(addFavorita);
        painel.add(addAssistida);
        painel.add(addDesejo);
        painel.add(remover);
        painel.add(comboOrdenar);
        return painel;
    }

    private void buscar() {
        try {
            TVMazeService servico = new TVMazeService();
            List<Serie> resultados = servico.buscarSeries(campoBusca.getText());
            if (resultados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma serie encontrada.");
                return;
            }
            mostrarLista(resultados);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar: " + ex.getMessage());
        }
    }

    private void mostrarLista(List<Serie> series) {
        listaAtual = series;
        modeloLista.clear();
        for (Serie s : series) {
            modeloLista.addElement(s);
        }
        areaDetalhes.setText("");
    }

    private void mostrarDetalhes() {
        Serie s = listaSeries.getSelectedValue();
        if (s == null) {
            return;
        }
        areaDetalhes.setText("");
        areaDetalhes.append("Nome:     " + s.getNome() + "\n");
        areaDetalhes.append("Idioma:   " + s.getIdioma() + "\n");
        areaDetalhes.append("Generos:  " + s.getGeneros() + "\n");
        areaDetalhes.append("Nota:     " + s.getNota() + "\n");
        areaDetalhes.append("Estado:   " + s.getStatus() + "\n");
        areaDetalhes.append("Estreia:  " + s.getDataEstreia() + "\n");
        areaDetalhes.append("Termino:  " + s.getDataTermino() + "\n");
        areaDetalhes.append("Emissora: " + s.getEmissora() + "\n");
    }

    private void adicionarFavorita() {
        Serie s = listaSeries.getSelectedValue();
        if (s == null) {
            return;
        }
        usuario.adicionarFavorita(s);
        salvar();
    }

    private void adicionarAssistida() {
        Serie s = listaSeries.getSelectedValue();
        if (s == null) {
            return;
        }
        usuario.adicionarAssistida(s);
        salvar();
    }

    private void adicionarDesejo() {
        Serie s = listaSeries.getSelectedValue();
        if (s == null) {
            return;
        }
        usuario.adicionarDesejoAssistir(s);
        salvar();
    }

    private void remover() {
        Serie s = listaSeries.getSelectedValue();
        if (s == null) {
            return;
        }
        listaAtual.remove(s);
        modeloLista.removeElement(s);
        salvar();
    }

    private void ordenar(int indice) {
        Comparator<Serie> comparador;
        if (indice == 1) {
            comparador = ComparadoresSerie.POR_NOME;
        } else if (indice == 2) {
            comparador = ComparadoresSerie.POR_NOTA;
        } else if (indice == 3) {
            comparador = ComparadoresSerie.POR_ESTADO;
        } else if (indice == 4) {
            comparador = ComparadoresSerie.POR_DATA_ESTREIA;
        } else {
            return;
        }
        listaAtual.sort(comparador);
        mostrarLista(listaAtual);
    }

    private void salvar() {
        try {
            persistencia.salvar(usuario);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + ex.getMessage());
        }
    }

    // melhorias no ux do sistema
    private void estilizar() {
        java.awt.Color fundo = new java.awt.Color(30, 30, 40);
        java.awt.Color texto = new java.awt.Color(230, 230, 235);
        java.awt.Font fonte = new java.awt.Font("Cascadia Mono", java.awt.Font.PLAIN, 14);

        areaDetalhes.setBackground(fundo);
        areaDetalhes.setForeground(texto);
        areaDetalhes.setFont(fonte);

        listaSeries.setFont(fonte);
        listaSeries.setBackground(fundo);
        listaSeries.setForeground(texto);
        listaSeries.setSelectionBackground(new java.awt.Color(70, 90, 140));

        campoBusca.setFont(fonte);
    }
}