package ui;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import model.Serie;
import model.Usuario;
import service.JsonPersistence;
import service.TvMazeService;

/*
 * Classe `Interface` (janela principal)
 * - Responsável apenas pela UI Swing (estende JFrame)
 * - Não contém mais o método `main`; a inicialização da aplicação
 *   foi movida para `App.java` para separar responsabilidades.
 *
 * Comentários foram adicionados para facilitar leitura e manutenção.
 */

public class Interface extends JFrame {
    private static final Color COR_FUNDO = new Color(40, 45, 55);
    private static final Color COR_PAINEL = new Color(55, 63, 76);
    private static final Color COR_BOTAO_BUSCAR = new Color(210, 180, 140);
    private static final Color COR_BOTAO_DETALHES = new Color(173, 216, 230);
    private static final Color COR_BOTAO_REMOVER = new Color(255, 182, 193);
    private static final Color COR_BOTAO_FAVORITOS = new Color(255, 255, 153);
    private static final Color COR_BOTAO_ASSISTIDAS = new Color(255, 182, 193);
    private static final Color COR_BOTAO_DESEJO = new Color(166, 255, 156);
    private static final Color COR_BOTAO_TEXTO = Color.BLACK;
    private static final Color COR_TEXTO = Color.WHITE;

    private Usuario usuario;
    private JPanel panelListasCards;
    private JPanel panelBuscaCards;
    private JTextField txtBusca;
    private JComboBox<String> comboListas;
    private JComboBox<String> comboOrdenacao;

    public Interface() {
        // Inicializa os dados do usuário (carregar ou criar)
        inicializarDados();
        // Configura a janela e componentes Swing
        configurarJanela();
    }

    private void inicializarDados() {
        // Pergunta o nome ao usuário (caixa de diálogo)
        String nome = JOptionPane.showInputDialog(this, "Qual seu nome/apelido?", "Configuração", JOptionPane.QUESTION_MESSAGE);
        if (nome == null || nome.trim().isEmpty()) nome = "Usuário Local";
        nome = nome.trim();

        // Tenta carregar os dados a partir do JSON. Se não existir, cria novo usuário
        usuario = JsonPersistence.carregar(nome);
        if (usuario == null) {
            usuario = new Usuario(nome);
            if ("mateus".equalsIgnoreCase(nome)) {
                // Aviso opcional caso o arquivo de Mateus não tenha sido encontrado
                JOptionPane.showMessageDialog(this, "Não foi possível carregar os dados de Mateus. Um novo arquivo será criado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            // Salva o arquivo inicial para o usuário criado
            JsonPersistence.salvar(usuario);
        }
    }

    private void configurarJanela() {
        setTitle("Rastreador de Séries de TV");
        setSize(850, 600);
        
        // CORREÇÃO CRÍTICA: Diz ao Java para não fechar sozinho, nós controlamos o encerramento.
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        // Adiciona listener para salvar os dados ao fechar a janela
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Garante que o salvamento execute por completo antes de encerrar
                JsonPersistence.salvar(usuario);
                System.exit(0);
            }
        });
        setLocationRelativeTo(null);
        getContentPane().setBackground(COR_FUNDO);

        JPanel panelTopo = new JPanel(new BorderLayout());
        panelTopo.setBackground(COR_PAINEL);
        panelTopo.setBorder(new EmptyBorder(10, 15, 10, 15));
        JLabel lblBoasVindas = new JLabel("Olá, " + usuario.getNome() + "! Acompanhe suas séries favoritas aqui.");
        lblBoasVindas.setForeground(COR_TEXTO);
        lblBoasVindas.setFont(new Font("Arial", Font.BOLD, 14));
        panelTopo.add(lblBoasVindas, BorderLayout.WEST);
        add(panelTopo, BorderLayout.NORTH);

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Minhas Listas", criarAbaMinhasListas());
        abas.addTab("Buscar Novas Séries", criarAbaBuscar());
        add(abas, BorderLayout.CENTER);

        atualizarPainelListas();
    }

    private JPanel criarAbaMinhasListas() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(COR_FUNDO);
        painel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel painelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelFiltros.setBackground(COR_PAINEL);
        comboListas = new JComboBox<>(new String[]{"Favoritos", "Já Assistidas", "Deseja Assistir"});
        comboOrdenacao = new JComboBox<>(new String[]{"Ordem Alfabética", "Nota Geral", "Estado", "Data de Estreia"});
        comboListas.setBackground(Color.WHITE);
        comboListas.setForeground(Color.BLACK);
        comboOrdenacao.setBackground(Color.WHITE);
        comboOrdenacao.setForeground(Color.BLACK);
        
        // Atualiza o painel sempre que o usuário trocar a lista ou a ordenação
        comboListas.addActionListener(e -> atualizarPainelListas());
        comboOrdenacao.addActionListener(e -> atualizarPainelListas());

        JLabel lblLista = new JLabel("Selecionar Lista: ");
        lblLista.setForeground(COR_TEXTO);
        JLabel lblOrdenar = new JLabel("  Ordenar por: ");
        lblOrdenar.setForeground(COR_TEXTO);
        painelFiltros.add(lblLista);
        painelFiltros.add(comboListas);
        painelFiltros.add(lblOrdenar);
        painelFiltros.add(comboOrdenacao);
        painel.add(painelFiltros, BorderLayout.NORTH);

        panelListasCards = new JPanel();
        panelListasCards.setLayout(new BoxLayout(panelListasCards, BoxLayout.Y_AXIS));
        panelListasCards.setBackground(COR_FUNDO);
        JScrollPane scrollListas = new JScrollPane(panelListasCards);
        scrollListas.getViewport().setBackground(COR_FUNDO);
        painel.add(scrollListas, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarAbaBuscar() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(COR_FUNDO);
        painel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel painelBarra = new JPanel(new BorderLayout(5, 5));
        painelBarra.setBackground(COR_PAINEL);
        txtBusca = new JTextField();
        txtBusca.setBackground(Color.WHITE);
        txtBusca.setForeground(Color.BLACK);
        txtBusca.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JButton btnBusca = criarBotao("Buscar na API", COR_BOTAO_BUSCAR, COR_BOTAO_TEXTO);
        
        txtBusca.addActionListener(e -> btnBusca.doClick());
        painelBarra.add(txtBusca, BorderLayout.CENTER);
        painelBarra.add(btnBusca, BorderLayout.EAST);
        painel.add(painelBarra, BorderLayout.NORTH);

        panelBuscaCards = new JPanel();
        panelBuscaCards.setLayout(new BoxLayout(panelBuscaCards, BoxLayout.Y_AXIS));
        panelBuscaCards.setBackground(COR_FUNDO);
        JScrollPane scrollBusca = new JScrollPane(panelBuscaCards);
        scrollBusca.getViewport().setBackground(COR_FUNDO);
        painel.add(scrollBusca, BorderLayout.CENTER);

        // Busca séries na API (opera em background para não travar a UI)
        btnBusca.addActionListener(e -> {
            String busca = txtBusca.getText().trim();
            if (!busca.isEmpty()) {
                panelBuscaCards.removeAll();
                JLabel lblCarregando = new JLabel("Buscando dados na API... Por favor, aguarde.", JLabel.CENTER);
                lblCarregando.setForeground(COR_TEXTO);
                panelBuscaCards.add(lblCarregando);
                panelBuscaCards.revalidate();
                panelBuscaCards.repaint();

                new SwingWorker<List<Serie>, Void>() {
                    @Override protected List<Serie> doInBackground() { return TvMazeService.buscarSeries(busca); }
                    @Override protected void done() {
                        try {
                            renderizarResultadosBusca(get());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(Interface.this, "Erro ao processar resultados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }.execute();
            }
        });
        return painel;
    }

    private void atualizarPainelListas() {
        // Limpa e re-popula o painel com os cards de séries
        panelListasCards.removeAll();
        List<Serie> lista = switch (comboListas.getSelectedIndex()) {
            case 0 -> usuario.getFavoritos();
            case 1 -> usuario.getJaAssistidas();
            default -> usuario.getDesejaAssistir();
        };

        // BOAS PRÁTICAS: Delega a ordenação para o objeto usuario
        usuario.ordenarLista(lista, comboOrdenacao.getSelectedIndex());

        if (lista.isEmpty()) {
            JLabel lblVazio = new JLabel("Nenhuma série adicionada a esta lista ainda.", JLabel.CENTER);
            lblVazio.setForeground(COR_TEXTO);
            lblVazio.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelListasCards.add(lblVazio);
        } else {
            for (Serie s : lista) {
                JPanel card = criarCardSerie(s, true);
                card.setAlignmentX(Component.LEFT_ALIGNMENT);
                panelListasCards.add(card);
                panelListasCards.add(Box.createVerticalStrut(8));
            }
        }
        panelListasCards.revalidate();
        panelListasCards.repaint();
    }

    private void renderizarResultadosBusca(List<Serie> series) {
        panelBuscaCards.removeAll();
        if (series.isEmpty()) {
            JLabel lblNenhum = new JLabel("Nenhuma série encontrada para o termo digitado.", JLabel.CENTER);
            lblNenhum.setForeground(COR_TEXTO);
            lblNenhum.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelBuscaCards.add(lblNenhum);
        } else {
            for (Serie s : series) {
                JPanel card = criarCardSerie(s, false);
                card.setAlignmentX(Component.LEFT_ALIGNMENT);
                panelBuscaCards.add(card);
                panelBuscaCards.add(Box.createVerticalStrut(8));
            }
        }
        panelBuscaCards.revalidate();
        panelBuscaCards.repaint();
    }

    private JPanel criarCardSerie(Serie s, boolean daListaLocal) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        card.setBackground(Color.WHITE);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JPanel info = new JPanel(new GridLayout(2, 1));
        info.setBackground(Color.WHITE);
        JLabel lblNome = new JLabel(s.getNome() + " (" + s.getIdioma() + ")");
        lblNome.setFont(new Font("Arial", Font.BOLD, 14));
        JLabel lblSub = new JLabel("Nota: " + (s.getNotaGeral() > 0 ? s.getNotaGeral() : "N/A") + " | Estado: " + s.getEstado());
        lblSub.setForeground(Color.GRAY);
        info.add(lblNome);
        info.add(lblSub);
        card.add(info, BorderLayout.CENTER);

        JPanel acoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acoes.setBackground(Color.WHITE);

        JButton btnDetalhes = criarBotao("Ver Detalhes", COR_BOTAO_DETALHES, COR_BOTAO_TEXTO);
        btnDetalhes.addActionListener(e -> exibirDetalhes(s));
        acoes.add(btnDetalhes);

        // Quando a série vem da busca (não está nas listas locais), mostra botões de adicionar
        if (!daListaLocal) {
            JButton btnFav = criarBotao("+ Favoritos", COR_BOTAO_FAVORITOS, COR_BOTAO_TEXTO);
            btnFav.addActionListener(e -> adicionarAALista(s, usuario.getFavoritos()));
            JButton btnAssistida = criarBotao("+ Assistidas", COR_BOTAO_ASSISTIDAS, COR_BOTAO_TEXTO);
            btnAssistida.addActionListener(e -> adicionarAALista(s, usuario.getJaAssistidas()));
            JButton btnDesejo = criarBotao("+ Quero Assistir", COR_BOTAO_DESEJO, COR_BOTAO_TEXTO);
            btnDesejo.addActionListener(e -> adicionarAALista(s, usuario.getDesejaAssistir()));
            acoes.add(btnFav); acoes.add(btnAssistida); acoes.add(btnDesejo);
        } else {
            JButton btnRemover = criarBotao("Remover", COR_BOTAO_REMOVER, COR_BOTAO_TEXTO);
            btnRemover.addActionListener(e -> {
                switch (comboListas.getSelectedIndex()) {
                    case 0 -> usuario.getFavoritos().remove(s);
                    case 1 -> usuario.getJaAssistidas().remove(s);
                    default -> usuario.getDesejaAssistir().remove(s);
                }
                JsonPersistence.salvar(usuario);
                atualizarPainelListas();
            });
            acoes.add(btnRemover);
        }

        card.add(acoes, BorderLayout.EAST);
        return card;
    }

    private void adicionarAALista(Serie s, List<Serie> listaAlvo) {
        // Adiciona a série na lista alvo e salva imediatamente
        if (!listaAlvo.contains(s)) {
            listaAlvo.add(s);
            JsonPersistence.salvar(usuario);
            atualizarPainelListas();
            JOptionPane.showMessageDialog(this, s.getNome() + " adicionada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, s.getNome() + " já está inclusa nesta lista.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private JButton criarBotao(String texto, Color fundo, Color textoCor) {
        JButton botao = new JButton(texto);
        botao.setBackground(fundo);
        botao.setForeground(textoCor);
        botao.setFocusPainted(false);
        botao.setOpaque(true);
        botao.setContentAreaFilled(true);
        botao.setBorderPainted(false);
        botao.setBorder(BorderFactory.createEmptyBorder(6, 14, 6, 14));
        return botao;
    }

    private void exibirDetalhes(Serie s) {
        JDialog dialog = new JDialog(this, "Detalhes da Série", true);
        dialog.setSize(400, 320);
        dialog.setLocationRelativeTo(this);
        JPanel p = new JPanel(new GridLayout(8, 1, 5, 5));
        p.setBorder(new EmptyBorder(15, 15, 15, 15));

        p.add(new JLabel("Nome: " + s.getNome()));
        p.add(new JLabel("Idioma: " + s.getIdioma()));
        p.add(new JLabel("Gêneros: " + s.getGeneros().toString()));
        p.add(new JLabel("Nota Geral: " + (s.getNotaGeral() > 0 ? s.getNotaGeral() : "N/A")));
        p.add(new JLabel("Estado: " + s.getEstado()));
        p.add(new JLabel("Data de Estreia: " + s.getDataEstreia()));
        p.add(new JLabel("Data de Término: " + s.getDataTermino()));
        p.add(new JLabel("Emissora Original: " + s.getEmissora()));

        dialog.add(p, BorderLayout.CENTER);
        JButton btnFechar = criarBotao("Fechar", COR_BOTAO_DETALHES, COR_BOTAO_TEXTO);
        btnFechar.addActionListener(e -> dialog.dispose());
        dialog.add(btnFechar, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
}