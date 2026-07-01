package PROVA2BIM;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ListaPanel extends JPanel {

    private final String tipoLista;
    private final Usuario usuario;
    private final PersistenciaService persistencia;
    private final JFrame parentFrame;

    private SerieTableModel tableModel;
    private JTable tabela;
    private JComboBox<OrdenacaoUtil.CriterioOrdenacao> comboOrdenacao;
    private JLabel lblInfo;

    public ListaPanel(String tipoLista, Usuario usuario,
                      PersistenciaService persistencia, JFrame parentFrame) {
        this.tipoLista = tipoLista;
        this.usuario = usuario;
        this.persistencia = persistencia;
        this.parentFrame = parentFrame;
        inicializarComponentes();
        atualizarLista();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(8, 8));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(245, 245, 250));

        JPanel topoPanel = new JPanel(new BorderLayout(8, 0));
        topoPanel.setBackground(new Color(245, 245, 250));

        JLabel lblTitulo = new JLabel(getTituloLista());
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(40, 60, 120));
        topoPanel.add(lblTitulo, BorderLayout.WEST);

        JPanel ordenacaoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        ordenacaoPanel.setBackground(new Color(245, 245, 250));
        ordenacaoPanel.add(new JLabel("Ordenar por:"));
        comboOrdenacao = new JComboBox<>(OrdenacaoUtil.CriterioOrdenacao.values());
        comboOrdenacao.setFont(new Font("SansSerif", Font.PLAIN, 12));
        comboOrdenacao.addActionListener(e -> aplicarOrdenacao());
        ordenacaoPanel.add(comboOrdenacao);
        topoPanel.add(ordenacaoPanel, BorderLayout.EAST);

        add(topoPanel, BorderLayout.NORTH);

        tableModel = new SerieTableModel();
        tabela = new JTable(tableModel);
        configurarTabela();

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 210, 230)));
        add(scrollPane, BorderLayout.CENTER);

        JPanel rodapePanel = new JPanel(new BorderLayout(8, 0));
        rodapePanel.setBackground(new Color(245, 245, 250));

        lblInfo = new JLabel(" ");
        lblInfo.setFont(new Font("SansSerif", Font.ITALIC, 11));
        lblInfo.setForeground(new Color(100, 100, 120));
        rodapePanel.add(lblInfo, BorderLayout.WEST);

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        botoesPanel.setBackground(new Color(245, 245, 250));

        JButton btnDetalhes = criarBotao("Ver Detalhes", new Color(60, 100, 200));
        JButton btnRemover = criarBotao("Remover da Lista", new Color(180, 60, 60));

        btnDetalhes.addActionListener(e -> abrirDetalhes());
        btnRemover.addActionListener(e -> removerSelecionada());

        botoesPanel.add(btnDetalhes);
        botoesPanel.add(btnRemover);
        rodapePanel.add(botoesPanel, BorderLayout.EAST);

        add(rodapePanel, BorderLayout.SOUTH);

        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) abrirDetalhes();
            }
        });
    }

    private void configurarTabela() {
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setRowHeight(24);
        tabela.setGridColor(new Color(220, 225, 240));
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        tabela.getTableHeader().setBackground(new Color(60, 100, 180));
        tabela.getTableHeader().setForeground(Color.WHITE);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabela.setFillsViewportHeight(true);

        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object val,
                    boolean sel, boolean foc, int row, int col) {
                super.getTableCellRendererComponent(t, val, sel, foc, row, col);
                if (!sel) {
                    setBackground(row % 2 == 0 ? Color.WHITE : new Color(235, 240, 255));
                }
                setBorder(new EmptyBorder(0, 6, 0, 6));
                return this;
            }
        };
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
    }

    public void atualizarLista() {
        List<Serie> lista = obterLista();
        tableModel.setSeries(lista);
        lblInfo.setText("Total: " + lista.size() + " série(s)");
    }

    private List<Serie> obterLista() {
        switch (tipoLista) {
            case "favoritos": return usuario.getFavoritos();
            case "assistidas": return usuario.getJaAssistidas();
            case "deseja": return usuario.getDesejassistir();
            default: return new java.util.ArrayList<>();
        }
    }

    private void aplicarOrdenacao() {
        OrdenacaoUtil.CriterioOrdenacao criterio =
            (OrdenacaoUtil.CriterioOrdenacao) comboOrdenacao.getSelectedItem();
        List<Serie> lista = obterLista();
        OrdenacaoUtil.ordenar(lista, criterio);
        tableModel.setSeries(lista);
    }

    private void abrirDetalhes() {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(parentFrame, "Selecione uma série primeiro.",
                    "Nenhuma seleção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Serie s = tableModel.getSerieNaLinha(row);
        if (s != null) {
            DetalheSerieDialog dlg = new DetalheSerieDialog(parentFrame, s, usuario, persistencia);
            dlg.setVisible(true);
            atualizarLista();
        }
    }

    private void removerSelecionada() {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(parentFrame, "Selecione uma série para remover.",
                    "Nenhuma seleção", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Serie s = tableModel.getSerieNaLinha(row);
        if (s == null) return;

        int conf = JOptionPane.showConfirmDialog(parentFrame,
                "Remover \"" + s.getNome() + "\" da lista?",
                "Confirmar remoção", JOptionPane.YES_NO_OPTION);
        if (conf != JOptionPane.YES_OPTION) return;

        switch (tipoLista) {
            case "favoritos": usuario.removerFavorito(s); break;
            case "assistidas": usuario.removerJaAssistida(s); break;
            case "deseja": usuario.removerDesejaAssistir(s); break;
        }

        try {
            persistencia.salvar(usuario);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame, "Erro ao salvar: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
        atualizarLista();
    }

    private String getTituloLista() {
        switch (tipoLista) {
            case "favoritos": return "⭐ Favoritos";
            case "assistidas": return "✓ Já Assistidas";
            case "deseja": return "📌 Quero Assistir";
            default: return "Lista";
        }
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton btn = new JButton(texto);
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Color hover = cor.darker();
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            public void mouseExited(MouseEvent e) { btn.setBackground(cor); }
        });
        return btn;
    }
}
