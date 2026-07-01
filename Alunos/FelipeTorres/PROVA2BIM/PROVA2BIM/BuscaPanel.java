package PROVA2BIM;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class BuscaPanel extends JPanel {

    private final Usuario usuario;
    private final PersistenciaService persistencia;
    private final JFrame parentFrame;
    private final TVMazeService tvMazeService;

    private JTextField campoBusca;
    private JButton btnBuscar;
    private SerieTableModel tableModel;
    private JTable tabela;
    private JLabel lblStatus;
    private JButton btnDetalhes;
    private JButton btnFav;
    private JButton btnAssistida;
    private JButton btnDeseja;

    public BuscaPanel(Usuario usuario, PersistenciaService persistencia,
                      JFrame parentFrame, TVMazeService tvMazeService) {
        this.usuario = usuario;
        this.persistencia = persistencia;
        this.parentFrame = parentFrame;
        this.tvMazeService = tvMazeService;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout(8, 8));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(new Color(245, 245, 250));

        JPanel topoPanel = new JPanel(new BorderLayout(8, 0));
        topoPanel.setBackground(new Color(245, 245, 250));

        JLabel lblTitulo = new JLabel("🔍 Buscar Séries");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(40, 60, 120));
        topoPanel.add(lblTitulo, BorderLayout.NORTH);

        JPanel campoBuscaPanel = new JPanel(new BorderLayout(6, 0));
        campoBuscaPanel.setBackground(new Color(245, 245, 250));
        campoBuscaPanel.setBorder(new EmptyBorder(8, 0, 0, 0));

        campoBusca = new JTextField();
        campoBusca.setFont(new Font("SansSerif", Font.PLAIN, 14));
        campoBusca.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 170, 220), 1),
            new EmptyBorder(6, 8, 6, 8)
        ));
        campoBusca.addActionListener(e -> realizarBusca());

        btnBuscar = criarBotao("Buscar", new Color(60, 100, 200));
        btnBuscar.setPreferredSize(new Dimension(100, 36));
        btnBuscar.addActionListener(e -> realizarBusca());

        campoBuscaPanel.add(campoBusca, BorderLayout.CENTER);
        campoBuscaPanel.add(btnBuscar, BorderLayout.EAST);
        topoPanel.add(campoBuscaPanel, BorderLayout.CENTER);

        add(topoPanel, BorderLayout.NORTH);

        tableModel = new SerieTableModel();
        tabela = new JTable(tableModel);
        configurarTabela();

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 210, 230)));
        add(scrollPane, BorderLayout.CENTER);

        JPanel rodapePanel = new JPanel(new BorderLayout(8, 0));
        rodapePanel.setBackground(new Color(245, 245, 250));
        rodapePanel.setBorder(new EmptyBorder(6, 0, 0, 0));

        lblStatus = new JLabel("Digite o nome de uma série e pressione Buscar.");
        lblStatus.setFont(new Font("SansSerif", Font.ITALIC, 11));
        lblStatus.setForeground(new Color(100, 100, 120));
        rodapePanel.add(lblStatus, BorderLayout.WEST);

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        botoesPanel.setBackground(new Color(245, 245, 250));

        btnDetalhes = criarBotao("Ver Detalhes", new Color(60, 100, 200));
        btnFav = criarBotao("⭐ Favoritar", new Color(200, 160, 0));
        btnAssistida = criarBotao("✓ Já Assisti", new Color(60, 150, 60));
        btnDeseja = criarBotao("📌 Quero Ver", new Color(80, 80, 200));

        btnDetalhes.addActionListener(e -> abrirDetalhes());
        btnFav.addActionListener(e -> adicionarALista("favoritos"));
        btnAssistida.addActionListener(e -> adicionarALista("assistidas"));
        btnDeseja.addActionListener(e -> adicionarALista("deseja"));

        botoesPanel.add(btnDetalhes);
        botoesPanel.add(btnFav);
        botoesPanel.add(btnAssistida);
        botoesPanel.add(btnDeseja);
        rodapePanel.add(botoesPanel, BorderLayout.EAST);

        add(rodapePanel, BorderLayout.SOUTH);

        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) abrirDetalhes();
            }
        });
    }

    private void realizarBusca() {
        String termo = campoBusca.getText().trim();
        if (termo.isEmpty()) {
            JOptionPane.showMessageDialog(parentFrame,
                    "Por favor, digite o nome de uma série para buscar.",
                    "Campo vazio", JOptionPane.WARNING_MESSAGE);
            return;
        }

        btnBuscar.setEnabled(false);
        lblStatus.setText("Buscando...");
        tableModel.setSeries(new java.util.ArrayList<>());

        SwingWorker<List<Serie>, Void> worker = new SwingWorker<List<Serie>, Void>() {
            @Override
            protected List<Serie> doInBackground() throws Exception {
                return tvMazeService.buscarPorNome(termo);
            }

            @Override
            protected void done() {
                try {
                    List<Serie> resultados = get();
                    tableModel.setSeries(resultados);
                    if (resultados.isEmpty()) {
                        lblStatus.setText("Nenhuma série encontrada para \"" + termo + "\".");
                    } else {
                        lblStatus.setText(resultados.size() + " resultado(s) encontrado(s).");
                    }
                } catch (java.util.concurrent.ExecutionException ex) {
                    String msg = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
                    lblStatus.setText("Erro na busca.");
                    JOptionPane.showMessageDialog(parentFrame,
                            "Erro ao buscar séries:\n" + msg,
                            "Erro de conexão", JOptionPane.ERROR_MESSAGE);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    lblStatus.setText("Busca interrompida.");
                } finally {
                    btnBuscar.setEnabled(true);
                }
            }
        };
        worker.execute();
    }

    private void abrirDetalhes() {
        Serie s = getSeriesSelecionada();
        if (s == null) return;
        DetalheSerieDialog dlg = new DetalheSerieDialog(parentFrame, s, usuario, persistencia);
        dlg.setVisible(true);
    }

    private void adicionarALista(String tipo) {
        Serie s = getSeriesSelecionada();
        if (s == null) return;

        boolean adicionado;
        String nomeLista;
        switch (tipo) {
            case "favoritos":
                adicionado = usuario.adicionarFavorito(s);
                nomeLista = "Favoritos";
                break;
            case "assistidas":
                adicionado = usuario.adicionarJaAssistida(s);
                nomeLista = "Já Assistidas";
                break;
            case "deseja":
                adicionado = usuario.adicionarDesejaAssistir(s);
                nomeLista = "Quero Assistir";
                break;
            default: return;
        }

        if (!adicionado) {
            JOptionPane.showMessageDialog(parentFrame,
                    "\"" + s.getNome() + "\" já está na lista de " + nomeLista + ".",
                    "Já adicionado", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        try {
            persistencia.salvar(usuario);
            JOptionPane.showMessageDialog(parentFrame,
                    "\"" + s.getNome() + "\" adicionada à lista de " + nomeLista + "!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(parentFrame,
                    "Série adicionada, mas houve erro ao salvar:\n" + ex.getMessage(),
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private Serie getSeriesSelecionada() {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(parentFrame,
                    "Selecione uma série na tabela primeiro.",
                    "Nenhuma seleção", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        return tableModel.getSerieNaLinha(row);
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
