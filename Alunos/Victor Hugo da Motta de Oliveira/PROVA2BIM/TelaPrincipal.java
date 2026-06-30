import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

// gui do sistema com java swing
public class TelaPrincipal extends JFrame {

    // variaveis essenciais pra rodar o programa e guardar o estado do usuario
    private GerenciadorDados gerenciador;
    private Usuario usuario;
    private TVMazeAPICliente apiClient;

    // componentes de busca
    private JTextField txtBusca;
    private JButton btnBuscar;
    private DefaultListModel<Serie> listModelBusca;
    private JList<Serie> listBusca;
    
    // componentes de detalhes
    private JTextArea txtDetalhes;
    private JLabel lblPoster;
    private JButton btnFavoritos, btnAssistidas, btnDesejaAssistir;
    private Serie serieSelecionadaAtual;

    public TelaPrincipal() {
        // carrega coisas essenciais assim que a tela nasce
        gerenciador = new GerenciadorDados();
        usuario = gerenciador.carregarUsuario();
        apiClient = new TVMazeAPICliente();

        // config basica da janela
        setTitle("Minhas Séries - Usuário: " + usuario.getApelido() + " (" + usuario.getNome() + ")");
        setSize(1050, 750); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // quando fechar no X salva o json 
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gerenciador.salvarUsuario(usuario);
                System.exit(0);
            }
        });

        // cria o menu de abas em cima com uma fonte amigavel
        JTabbedPane abas = new JTabbedPane();
        abas.setFont(new Font("SansSerif", Font.BOLD, 14));
        
        abas.addTab("🔍 Buscar Séries", montarAbaBusca());
        abas.addTab("⭐ Meus Favoritos", montarAbaLista(usuario.getFavoritos()));
        abas.addTab("✔️ Já Assistidas", montarAbaLista(usuario.getJaAssistidas()));
        abas.addTab("🕒 Quero Assistir", montarAbaLista(usuario.getDesejaAssistir()));
        
        add(abas);
    }

    // criador da aba pesquisa com layout melhorado
    private JPanel montarAbaBusca() {
        JPanel painelBusca = new JPanel(new BorderLayout(15, 15));
        painelBusca.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); 

        // parte superior 
        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        JLabel lblBusca = new JLabel("Nome da Série:");
        lblBusca.setFont(new Font("SansSerif", Font.BOLD, 14));
        
        txtBusca = new JTextField(35);
        txtBusca.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtBusca.setMargin(new Insets(4, 4, 4, 4)); 
        
        btnBuscar = new JButton("Buscar na API");
        btnBuscar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR)); 

        painelTopo.add(lblBusca);
        painelTopo.add(txtBusca);
        painelTopo.add(btnBuscar);

        // lado esquerdo com a lista de resultados
        listModelBusca = new DefaultListModel<>();
        listBusca = new JList<>(listModelBusca);
        listBusca.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listBusca.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        JScrollPane scrollBusca = new JScrollPane(listBusca);
        scrollBusca.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY), 
            "Resultados da Busca", 
            TitledBorder.LEFT, 
            TitledBorder.TOP, 
            new Font("SansSerif", Font.BOLD, 12)
        ));

        // lado direito com detalhes e poster
        JPanel painelDireita = new JPanel(new BorderLayout(15, 15));
        painelDireita.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY), 
            "Detalhes da Série", 
            TitledBorder.LEFT, 
            TitledBorder.TOP, 
            new Font("SansSerif", Font.BOLD, 12)
        ));
        
        JPanel painelInfoCentral = new JPanel(new BorderLayout(15, 15));
        painelInfoCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        
        lblPoster = new JLabel("Sem imagem", SwingConstants.CENTER);
        lblPoster.setPreferredSize(new Dimension(210, 295));
        lblPoster.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        lblPoster.setOpaque(true);
        lblPoster.setBackground(new Color(245, 245, 245)); 
        
        txtDetalhes = new JTextArea();
        txtDetalhes.setEditable(false);
        txtDetalhes.setFont(new Font("Monospaced", Font.PLAIN, 14));
        txtDetalhes.setBackground(new Color(250, 250, 250));
        txtDetalhes.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scrollDetalhes = new JScrollPane(txtDetalhes);
        scrollDetalhes.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        painelInfoCentral.add(lblPoster, BorderLayout.WEST);
        painelInfoCentral.add(scrollDetalhes, BorderLayout.CENTER);
        
        // botoes de acao
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        
        btnFavoritos = new JButton("⭐ Adicionar aos Favoritos");
        btnAssistidas = new JButton("✔️ Marcar como Assistida");
        btnDesejaAssistir = new JButton("🕒 Quero Assistir");
        
        Font fonteBotoes = new Font("SansSerif", Font.BOLD, 13);
        JButton[] botoes = {btnFavoritos, btnAssistidas, btnDesejaAssistir};
        for (JButton btn : botoes) {
            btn.setFont(fonteBotoes);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setFocusPainted(false); 
        }
        
        ativarBotoes(false);

        painelBotoes.add(btnFavoritos);
        painelBotoes.add(btnAssistidas);
        painelBotoes.add(btnDesejaAssistir);

        painelDireita.add(painelInfoCentral, BorderLayout.CENTER);
        painelDireita.add(painelBotoes, BorderLayout.SOUTH);

        // barra de divisao da tela
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollBusca, painelDireita);
        splitPane.setDividerLocation(350); 
        splitPane.setDividerSize(8); 
        splitPane.setBorder(null); 

        painelBusca.add(painelTopo, BorderLayout.NORTH);
        painelBusca.add(splitPane, BorderLayout.CENTER);

        // acoes basicas
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

        // clique duplo na lista de busca abre a janela de detalhes totais
        listBusca.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Serie selecionada = listBusca.getSelectedValue();
                    if (selecionada != null) {
                        exibirDetalhesPopup(selecionada);
                    }
                }
            }
        });

        btnFavoritos.addActionListener(e -> adicionarESalvar(usuario.getFavoritos(), "Favoritos"));
        btnAssistidas.addActionListener(e -> adicionarESalvar(usuario.getJaAssistidas(), "Já Assistidas"));
        btnDesejaAssistir.addActionListener(e -> adicionarESalvar(usuario.getDesejaAssistir(), "Desejos"));

        return painelBusca;
    }
    
    // atalho pra salvar na hora e mostrar a caixinha de aviso
    private void adicionarESalvar(List<Serie> lista, String nomeLista) {
        if(!lista.contains(serieSelecionadaAtual)) {
            lista.add(serieSelecionadaAtual);
            gerenciador.salvarUsuario(usuario); 
            JOptionPane.showMessageDialog(this, "Série adicionada em: " + nomeLista);
        } else {
            JOptionPane.showMessageDialog(this, "Esta série já está na lista!");
        }
    }

    // cria as outras abas q sao pra gerenciar suas proprias listas
    private JPanel montarAbaLista(List<Serie> listaAtual) {
        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // selecao de ordenacao
        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel lblOrdem = new JLabel("Ordenar por:");
        lblOrdem.setFont(new Font("SansSerif", Font.BOLD, 14));
        painelTopo.add(lblOrdem);
        
        JComboBox<String> comboOrdem = new JComboBox<>(new String[]{
            "Selecione...", "Ordem Alfabética", "Maior Nota Geral"
        });
        comboOrdem.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        JButton btnAtualizar = new JButton("🔄 Atualizar Tabela");
        btnAtualizar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnAtualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        painelTopo.add(comboOrdem);
        painelTopo.add(btnAtualizar);
        painel.add(painelTopo, BorderLayout.NORTH);

        // colunas da tabela
        String[] colunas = {"Nome da Série", "Nota", "Estado", "Estreia"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        JTable tabela = new JTable(model);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tabela.setRowHeight(25); 
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14)); 
        
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);

        // rotina que varre a lista e imprime na tela 
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

        // polimorfismo nas tabelas
        comboOrdem.addActionListener(e -> {
            int index = comboOrdem.getSelectedIndex();
            EstrategiaOrdenacao estrategia = null;
            
            if (index == 1) estrategia = new OrdenacaoAlfabetica();
            if (index == 2) estrategia = new OrdenacaoNota();
            
            if (estrategia != null) {
                estrategia.ordenar(listaAtual); 
                atualizarTabela.run();
            }
        });

        // clique duplo na tabela das listas abre a janela de detalhes totais
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // detecta os dois cliques
                    int linha = tabela.getSelectedRow();
                    if (linha >= 0 && linha < listaAtual.size()) {
                        Serie selecionada = listaAtual.get(linha);
                        exibirDetalhesPopup(selecionada);
                    }
                }
            }
        });

        // area de deletar
        JPanel painelRodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRemover = new JButton("🗑️ Remover Série Selecionada");
        btnRemover.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnRemover.setForeground(new Color(200, 0, 0)); 
        btnRemover.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        painelRodape.add(btnRemover);
        painel.add(painelRodape, BorderLayout.SOUTH);

        btnRemover.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                listaAtual.remove(linhaSelecionada);
                gerenciador.salvarUsuario(usuario); 
                atualizarTabela.run(); 
                JOptionPane.showMessageDialog(painel, "Série removida com sucesso!");
            } else {
                JOptionPane.showMessageDialog(painel, "Selecione uma série na tabela para remover.");
            }
        });

        atualizarTabela.run();
        return painel;
    }

    // preenche os dados do lado direito da busca
    private void mostrarDetalhes(Serie serie) {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(serie.getName()).append("\n\n");
        sb.append("Idioma: ").append(serie.getLanguage()).append("\n");
        sb.append("Gêneros: ").append(serie.getGenres().isEmpty() ? "N/A" : String.join(", ", serie.getGenres())).append("\n");
        sb.append("Nota: ").append(serie.getScore() > 0 ? serie.getScore() : "S/N").append("\n");
        sb.append("Status: ").append(serie.getStatus()).append("\n");
        sb.append("Estreia: ").append(serie.getPremiered()).append("\n");
        sb.append("Fim: ").append(serie.getEnded()).append("\n");
        sb.append("Rede: ").append(serie.getNetworkName()).append("\n");

        txtDetalhes.setText(sb.toString());

        // baixa imagem
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
                        lblPoster.setText("Erro ao carregar imagem");
                    }
                }
            };
            workerImagem.execute();
        } else {
            lblPoster.setIcon(null);
            lblPoster.setText("Sem imagem");
        }
    }

    // popup com as infos completas ao dar dois cliques em qualquer lugar
    private void exibirDetalhesPopup(Serie serie) {
        JDialog dialog = new JDialog(this, "Detalhes da Série: " + serie.getName(), true);
        dialog.setSize(650, 400);
        dialog.setLayout(new BorderLayout(15, 15));
        dialog.setLocationRelativeTo(this);

        // area de texto do popup
        JTextArea txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        txtInfo.setFont(new Font("Monospaced", Font.PLAIN, 15));
        txtInfo.setMargin(new Insets(15, 15, 15, 15));
        txtInfo.setBackground(new Color(250, 250, 250));

        // monta as infos completas
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(serie.getName()).append("\n\n");
        sb.append("Idioma: ").append(serie.getLanguage()).append("\n");
        sb.append("Gêneros: ").append(serie.getGenres().isEmpty() ? "N/A" : String.join(", ", serie.getGenres())).append("\n");
        sb.append("Nota: ").append(serie.getScore() > 0 ? serie.getScore() : "S/N").append("\n");
        sb.append("Status: ").append(serie.getStatus()).append("\n");
        sb.append("Estreia: ").append(serie.getPremiered()).append("\n");
        sb.append("Fim: ").append(serie.getEnded()).append("\n");
        sb.append("Rede: ").append(serie.getNetworkName()).append("\n");
        txtInfo.setText(sb.toString());

        // area da foto no popup
        JLabel lblFotoPopup = new JLabel("Buscando imagem...", SwingConstants.CENTER);
        lblFotoPopup.setPreferredSize(new Dimension(230, 315));
        lblFotoPopup.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // baixa a imagem na hora que a janela abre
        if (serie.getImageUrl() != null && !serie.getImageUrl().equals("N/A")) {
            SwingWorker<Image, Void> workerImagemPopup = new SwingWorker<>() {
                @Override
                protected Image doInBackground() throws Exception {
                    return ImageIO.read(URI.create(serie.getImageUrl()).toURL());
                }
                @Override
                protected void done() {
                    try {
                        Image img = get();
                        lblFotoPopup.setIcon(new ImageIcon(img.getScaledInstance(210, 295, Image.SCALE_SMOOTH)));
                        lblFotoPopup.setText("");
                    } catch (Exception ex) {
                        lblFotoPopup.setIcon(null);
                        lblFotoPopup.setText("Sem imagem");
                    }
                }
            };
            workerImagemPopup.execute();
        } else {
            lblFotoPopup.setText("Sem imagem");
        }

        // junta tudo e exibe o popup pro usuario
        dialog.add(lblFotoPopup, BorderLayout.WEST);
        dialog.add(new JScrollPane(txtInfo), BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    // trava os botoes quando precisa
    private void ativarBotoes(boolean ativo) {
        btnFavoritos.setEnabled(ativo);
        btnAssistidas.setEnabled(ativo);
        btnDesejaAssistir.setEnabled(ativo);
    }

    // processo de pesquisa q vai na api
    private void buscarSerie() {
        String termo = txtBusca.getText().trim();
        if (termo.isEmpty()) return;

        btnBuscar.setEnabled(false);
        btnBuscar.setText("Buscando...");
        listModelBusca.clear();
        txtDetalhes.setText("");
        lblPoster.setIcon(null);
        lblPoster.setText("Buscando imagem...");
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
                    btnBuscar.setText("Buscar na API");
                }
            }
        };
        worker.execute();
    }
}