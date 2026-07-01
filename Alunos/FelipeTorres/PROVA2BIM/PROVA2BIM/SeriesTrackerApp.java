package PROVA2BIM;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class SeriesTrackerApp extends JFrame {

    private Usuario usuario;
    private final PersistenciaService persistencia;
    private final TVMazeService tvMazeService;

    private JLabel lblBoasVindas;
    private ListaPanel panelFavoritos;
    private ListaPanel panelAssistidas;
    private ListaPanel panelDeseja;
    private JTabbedPane abas;

    public SeriesTrackerApp(Usuario usuario, PersistenciaService persistencia,
                            TVMazeService tvMazeService) {
        this.usuario = usuario;
        this.persistencia = persistencia;
        this.tvMazeService = tvMazeService;
        configurarJanela();
        inicializarComponentes();
    }

    private void configurarJanela() {
        setTitle("📺 Series Tracker");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(900, 620);
        setMinimumSize(new Dimension(750, 500));
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                salvarAoFechar();
            }
        });

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
    }

    private void inicializarComponentes() {
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBackground(new Color(240, 242, 250));

        JPanel cabecalho = criarCabecalho();
        painelPrincipal.add(cabecalho, BorderLayout.NORTH);

        abas = new JTabbedPane(JTabbedPane.TOP);
        abas.setFont(new Font("SansSerif", Font.BOLD, 13));
        abas.setBackground(new Color(240, 242, 250));

        BuscaPanel panelBusca = new BuscaPanel(usuario, persistencia, this, tvMazeService);
        abas.addTab("🔍 Buscar", panelBusca);

        panelFavoritos = new ListaPanel("favoritos", usuario, persistencia, this);
        abas.addTab("⭐ Favoritos", panelFavoritos);

        panelAssistidas = new ListaPanel("assistidas", usuario, persistencia, this);
        abas.addTab("✓ Já Assistidas", panelAssistidas);

        panelDeseja = new ListaPanel("deseja", usuario, persistencia, this);
        abas.addTab("📌 Quero Assistir", panelDeseja);

        abas.addChangeListener(e -> {
            int idx = abas.getSelectedIndex();
            if (idx == 1) panelFavoritos.atualizarLista();
            else if (idx == 2) panelAssistidas.atualizarLista();
            else if (idx == 3) panelDeseja.atualizarLista();
        });

        painelPrincipal.add(abas, BorderLayout.CENTER);

        JLabel statusBar = new JLabel("  Sistema de Acompanhamento de Séries | TVMaze API");
        statusBar.setFont(new Font("SansSerif", Font.PLAIN, 10));
        statusBar.setForeground(new Color(120, 120, 140));
        statusBar.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(200, 210, 230)),
            new EmptyBorder(3, 5, 3, 5)
        ));
        painelPrincipal.add(statusBar, BorderLayout.SOUTH);

        setContentPane(painelPrincipal);
    }

    private JPanel criarCabecalho() {
        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setBackground(new Color(40, 60, 120));
        cabecalho.setBorder(new EmptyBorder(12, 16, 12, 16));

        JLabel lblTitulo = new JLabel("📺 Series Tracker");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);

        lblBoasVindas = new JLabel("Olá, " + usuario.getNome() + "!");
        lblBoasVindas.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblBoasVindas.setForeground(new Color(200, 210, 240));

        JPanel titulos = new JPanel(new GridLayout(2, 1));
        titulos.setBackground(new Color(40, 60, 120));
        titulos.add(lblTitulo);
        titulos.add(lblBoasVindas);
        cabecalho.add(titulos, BorderLayout.WEST);

        JButton btnEditarNome = new JButton("✏ Editar Perfil");
        btnEditarNome.setBackground(new Color(60, 90, 160));
        btnEditarNome.setForeground(Color.WHITE);
        btnEditarNome.setFocusPainted(false);
        btnEditarNome.setBorderPainted(false);
        btnEditarNome.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnEditarNome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEditarNome.addActionListener(e -> editarNomeUsuario());
        cabecalho.add(btnEditarNome, BorderLayout.EAST);

        return cabecalho;
    }

    private void editarNomeUsuario() {
        String novoNome = JOptionPane.showInputDialog(this,
                "Digite seu nome ou apelido:",
                usuario.getNome());
        if (novoNome == null) return;
        novoNome = novoNome.trim();
        if (novoNome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O nome não pode ser vazio.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        usuario.setNome(novoNome);
        lblBoasVindas.setText("Olá, " + novoNome + "!");
        try {
            persistencia.salvar(usuario);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar o nome: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarAoFechar() {
        try {
            persistencia.salvar(usuario);
        } catch (Exception ex) {
            int op = JOptionPane.showConfirmDialog(this,
                    "Erro ao salvar os dados:\n" + ex.getMessage() +
                    "\n\nDeseja fechar mesmo assim?",
                    "Erro ao salvar", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (op != JOptionPane.YES_OPTION) return;
        }
        dispose();
        System.exit(0);
    }
}
