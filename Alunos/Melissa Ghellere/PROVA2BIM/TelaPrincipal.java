import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// a janela principal rosa e fofa (✿ ♥‿♥)
public class TelaPrincipal extends JFrame {

    private GerenciadorDados gerenciador;
    private Usuario usuario;
    private TVMazeAPICliente apiClient;

    private JTextField txtBusca;
    private JButton btnBuscar;
    private DefaultListModel<Serie> listModelBusca;
    private JList<Serie> listBusca;
    
    private JTextArea txtDetalhes;
    private JLabel lblPoster;
    private JButton btnFavoritos, btnAssistidas, btnDesejaAssistir;
    private Serie serieSelecionadaAtual;

    public TelaPrincipal() {
        gerenciador = new GerenciadorDados();
        usuario = gerenciador.carregarUsuario();
        apiClient = new TVMazeAPICliente();

        // titulo com seu nome! ✨
        setTitle("✨ Minhas Séries 📺 - Usuária: " + usuario.getApelido() + " (" + usuario.getNome() + ") ✨");
        setSize(1050, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // fundo principal rosa (🌸)
        getContentPane().setBackground(new Color(255, 228, 242));

        // salva ao clicar no X (❌)
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gerenciador.salvarUsuario(usuario);
                System.exit(0);
            }
        });

        // abas coloridinhas 📁
        JTabbedPane abas = new JTabbedPane();
        abas.setBackground(new Color(255, 182, 193));
        abas.addTab("Buscar Séries (🔍)", montarAbaBusca());
        abas.addTab("Meus Favoritos (💖)", montarAbaLista(usuario.getFavoritos()));
        abas.addTab("Já Assistidas (🍿)", montarAbaLista(usuario.getJaAssistidas()));
        abas.addTab("Quero Assistir (✨)", montarAbaLista(usuario.getDesejaAssistir()));
        
        add(abas);
    }

    // monta a area de pesquisar 🔎
    private JPanel montarAbaBusca() {
        JPanel painelBusca = new JPanel(new BorderLayout());
        painelBusca.setBackground(new Color(255, 240, 245));

        JPanel painelTopo = new JPanel();
        painelTopo.setBackground(new Color(255, 182, 193)); // fundo rosa mais escuro
        
        txtBusca = new JTextField(30);
        btnBuscar = new JButton("Buscar API (≧◡≦)");
        btnBuscar.setBackground(Color.WHITE);
        
        painelTopo.add(new JLabel("Nome da Série (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ :"));
        painelTopo.add(txtBusca);
        painelTopo.add(btnBuscar);

        listModelBusca = new DefaultListModel<>();
        listBusca = new JList<>(listModelBusca);
        listBusca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listBusca.setBackground(new Color(255, 250, 250)); // branquinho/neve
        JScrollPane scrollBusca = new JScrollPane(listBusca);

        JPanel painelDireita = new JPanel(new BorderLayout());
        JPanel painelInfoCentral = new JPanel(new BorderLayout(10, 10));
        painelInfoCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelInfoCentral.setBackground(new Color(255, 240, 245));
        
        // onde vai a imagem 🖼️
        lblPoster = new JLabel("Sem fotinha (╥﹏╥)", SwingConstants.CENTER);
        lblPoster.setPreferredSize(new Dimension(210, 295));
        lblPoster.setBorder(BorderFactory.createLineBorder(new Color(255, 105, 180), 2)); // borda pink
        
        txtDetalhes = new JTextArea();
        txtDetalhes.setEditable(false);
        txtDetalhes.setFont(new Font("Monospaced", Font.BOLD, 14));
        txtDetalhes.setBackground(new Color(255, 228, 225)); // misty rose
        
        painelInfoCentral.add(lblPoster, BorderLayout.WEST);
        painelInfoCentral.add(new JScrollPane(txtDetalhes), BorderLayout.CENTER);
        
        // botoes fofos em baixo 🎀
        JPanel painelBotoes = new JPanel(new GridLayout(1, 3, 5, 5));
        painelBotoes.setBackground(new Color(255, 240, 245));
        btnFavoritos = new JButton("Favoritos (💖)");
        btnAssistidas = new JButton("Já Assistida (🍿)");
        btnDesejaAssistir = new JButton("Quero Assistir (✨)");
        
        Color corBotao = new Color(255, 192, 203);
        btnFavoritos.setBackground(corBotao);
        btnAssistidas.setBackground(corBotao);
        btnDesejaAssistir.setBackground(corBotao);
        
        ativarBotoes(false); // desliga no inicio

        painelBotoes.add(btnFavoritos);
        painelBotoes.add(btnAssistidas);
        painelBotoes.add(btnDesejaAssistir);

        painelDireita.add(painelInfoCentral, BorderLayout.CENTER);
        painelDireita.add(painelBotoes, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollBusca, painelDireita);
        splitPane.setDividerLocation(300);

        painelBusca.add(painelTopo, BorderLayout.NORTH);
        painelBusca.add(splitPane, BorderLayout.CENTER);

        // acoes! 🎬
        btnBuscar.addActionListener(e -> buscarSerie());
        
        listBusca.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                serieSelecionadaAtual = listBusca.getSelectedValue();
                if (serieSelecionadaAtual != null) {
                    mostrarDetalhes(serieSelecionadaAtual);
                    ativarBotoes(true);
                }
            }
        });

        btnFavoritos.addActionListener(e -> adicionarESalvar(usuario.getFavoritos(), "Favoritos (💖)"));
        btnAssistidas.addActionListener(e -> adicionarESalvar(usuario.getJaAssistidas(), "Já Assistidas (🍿)"));
        btnDesejaAssistir.addActionListener(e -> adicionarESalvar(usuario.getDesejaAssistir(), "Desejos (✨)"));

        return painelBusca;
    }
    
    // avisa na tela que salvou 💌
    private void adicionarESalvar(List<Serie> lista, String nomeLista) {
        if(!lista.contains(serieSelecionadaAtual)) {
            lista.add(serieSelecionadaAtual);
            gerenciador.salvarUsuario(usuario); 
            JOptionPane.showMessageDialog(this, "Série guardadinha em: " + nomeLista + " (ﾉ◕ヮ◕)ﾉ");
        } else {
            JOptionPane.showMessageDialog(this, "Ops! Você já colocou essa série aí! (¬_¬ )");
        }
    }

    // monta as abinhas com as suas listas 📋
    private JPanel montarAbaLista(List<Serie> listaAtual) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(new Color(255, 240, 245));

        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelTopo.setBackground(new Color(255, 192, 203));
        painelTopo.add(new JLabel("Organizar por (｡♥‿♥｡):"));
        
        // combo com todas as ordens que a professora pediu 📚
        JComboBox<String> comboOrdem = new JComboBox<>(new String[]{
            "Selecione...", "A-Z (Ordem Alfabética 🔤)", "Melhor Nota (🌟)", "Estado da Série (📺)", "Data de Estreia (📅)"
        });
        
        JButton btnAtualizar = new JButton("Atualizar (🔄)");
        btnAtualizar.setBackground(Color.WHITE);
        
        painelTopo.add(comboOrdem);
        painelTopo.add(btnAtualizar);
        painel.add(painelTopo, BorderLayout.NORTH);

        String[] colunas = {"Nome da Série", "Nota", "Estado", "Estreia"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable tabela = new JTable(model);
        tabela.setBackground(new Color(255, 250, 250));
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);

        // limpa e desenha a tabela 🎨
        Runnable atualizarTabela = () -> {
            model.setRowCount(0); 
            for (Serie s : listaAtual) {
                String notaStr = s.getScore() > 0 ? String.valueOf(s.getScore()) : "S/N";
                String estadoStr = s.getStatus() != null && !s.getStatus().equals("N/A") ? s.getStatus() : "N/A";
                String estreiaStr = s.getPremiered() != null && !s.getPremiered().equals("N/A") ? s.getPremiered() : "N/A";
                model.addRow(new Object[]{s.getName(), notaStr, estadoStr, estreiaStr});
            }
        };

        btnAtualizar.addActionListener(e -> atualizarTabela.run());

        // polimorfismo mágico pra ordenar ✨
        comboOrdem.addActionListener(e -> {
            int index = comboOrdem.getSelectedIndex();
            EstrategiaOrdenacao estrategia = null;
            
            if (index == 1) estrategia = new OrdenacaoAlfabetica();
            if (index == 2) estrategia = new OrdenacaoNota();
            if (index == 3) estrategia = new OrdenacaoEstado();
            if (index == 4) estrategia = new OrdenacaoEstreia();
            
            if (estrategia != null) {
                estrategia.ordenar(listaAtual); 
                atualizarTabela.run();
            }
        });

        JPanel painelRodape = new JPanel();
        painelRodape.setBackground(new Color(255, 240, 245));
        JButton btnRemover = new JButton("Remover Série (🗑️)");
        btnRemover.setBackground(new Color(255, 105, 180));
        btnRemover.setForeground(Color.WHITE);
        painelRodape.add(btnRemover);
        painel.add(painelRodape, BorderLayout.SOUTH);

        btnRemover.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                listaAtual.remove(linhaSelecionada);
                gerenciador.salvarUsuario(usuario); 
                atualizarTabela.run(); 
                JOptionPane.showMessageDialog(painel, "Foi pro lixo! (X_X)");
            }
        });

        atualizarTabela.run();
        return painel;
    }

    // mostra tudo da serie selecionada 🔎
    private void mostrarDetalhes(Serie serie) {
        StringBuilder sb = new StringBuilder();
        sb.append("🎀 Nome: ").append(serie.getName()).append("\n\n");
        sb.append("🗣️ Idioma: ").append(serie.getLanguage()).append("\n");
        sb.append("🎭 Gêneros: ").append(serie.getGenres().isEmpty() ? "N/A" : String.join(", ", serie.getGenres())).append("\n");
        sb.append("⭐ Nota: ").append(serie.getScore() > 0 ? serie.getScore() : "S/N").append("\n");
        sb.append("📺 Status: ").append(serie.getStatus()).append("\n");
        sb.append("📅 Estreia: ").append(serie.getPremiered()).append("\n");
        sb.append("🏁 Fim: ").append(serie.getEnded()).append("\n");
        sb.append("📡 Emissora: ").append(serie.getNetworkName()).append("\n");

        txtDetalhes.setText(sb.toString());

        // tenta carregar a fotinha de fundo (☁️)
        if (serie.getImageUrl() != null && !serie.getImageUrl().equals("N/A")) {
            SwingWorker<Image, Void> workerImagem = new SwingWorker<>() {
                @Override
                protected Image doInBackground() throws Exception {
                    return ImageIO.read(URI.create(serie.getImageUrl()).toURL());
                }
                @Override
                protected void done() {
                    try {
                        Image img = get();
                        lblPoster.setIcon(new ImageIcon(img.getScaledInstance(210, 295, Image.SCALE_SMOOTH)));
                        lblPoster.setText("");
                    } catch (Exception ex) {
                        lblPoster.setIcon(null);
                        lblPoster.setText("Sem foto (╥﹏╥)");
                    }
                }
            };
            workerImagem.execute();
        } else {
            lblPoster.setIcon(null);
            lblPoster.setText("Sem foto (╥﹏╥)");
        }
    }

    private void ativarBotoes(boolean ativo) {
        btnFavoritos.setEnabled(ativo);
        btnAssistidas.setEnabled(ativo);
        btnDesejaAssistir.setEnabled(ativo);
    }

    // vai la na net buscar (🚀)
    private void buscarSerie() {
        String termo = txtBusca.getText().trim();
        if (termo.isEmpty()) return;

        btnBuscar.setEnabled(false);
        btnBuscar.setText("Procurando... ⏳");
        listModelBusca.clear();
        txtDetalhes.setText("");
        lblPoster.setIcon(null);
        lblPoster.setText("Carregando... ☁️");
        ativarBotoes(false);

        SwingWorker<List<Serie>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Serie> doInBackground() {
                return apiClient.buscarSeriePorNome(termo);
            }
            @Override
            protected void done() {
                try {
                    List<Serie> resultados = get();
                    for (Serie s : resultados) listModelBusca.addElement(s);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(TelaPrincipal.this, "Erro: " + ex.getMessage());
                } finally {
                    btnBuscar.setEnabled(true);
                    btnBuscar.setText("Buscar API (≧◡≦)");
                }
            }
        };
        worker.execute();
    }
}