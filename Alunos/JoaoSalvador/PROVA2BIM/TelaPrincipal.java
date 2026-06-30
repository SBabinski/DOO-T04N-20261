package Fag;

import Fag.services.JsonService;
import Fag.services.TvMazeService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TelaPrincipal extends JFrame {

    private Usuario usuario;
    private final TvMazeService tvMazeService = new TvMazeService();
    private final JsonService jsonService = new JsonService();
    private final GerenciadorSeries gerenciador = new GerenciadorSeries();

    private JTextField txtNome;
    private JTextField txtPesquisa;
    private JTable tabelaBusca;
    private JTable tabelaFavoritos;
    private JTable tabelaAssistidas;
    private JTable tabelaAssistir;

    private List<Serie> resultadoBusca = new ArrayList<Serie>();

    // Variaveis de cor.
    private final Color color_background = new Color(252, 252, 252);
    private final Color color_title = new Color(0, 15, 105);
    private final Color color_button = new Color(0, 15, 158);

    // Construtor da tela principal.
    public TelaPrincipal() {
        usuario = jsonService.carregarUsuario();

        setTitle("TrackingSeries");
        setSize(1200, 720);
        setMinimumSize(new Dimension(1050, 650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(color_background);

        montarTopo();
        montarAbas();
        atualizarTabelasDasListas();
    }

    // Header da aplicação.
    private void montarTopo() {
        // Painel do header.
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(new EmptyBorder(12, 15, 12, 15));
        header.setBackground(color_background);

        JLabel titulo = new JLabel("TrackingSeries");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(color_title);

        JPanel painelTitulo = new JPanel(new BorderLayout());
        painelTitulo.setBackground(color_background);
        painelTitulo.add(titulo, BorderLayout.NORTH);

        JPanel painelCampos = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        painelCampos.setBackground(color_background);

        JLabel lblNome = new JLabel("Nome:");
        txtNome = new JTextField(usuario.getNome(), 14);
        JButton btnSalvarNome = criarBotao("Salvar nome", 145);
        btnSalvarNome.setBackground(color_button);
        btnSalvarNome.setForeground(Color.WHITE);

        btnSalvarNome.addActionListener(e -> salvarNome());

        painelCampos.add(lblNome);
        painelCampos.add(txtNome);
        painelCampos.add(btnSalvarNome);

        header.add(painelTitulo, BorderLayout.NORTH);
        header.add(painelCampos, BorderLayout.SOUTH);

        add(header, BorderLayout.NORTH);
    }

    // Construtor das abas da aplicação.
    private void montarAbas() {
        // Painel geral das abas.
        JTabbedPane abas = new JTabbedPane();
        abas.setFont(new Font("Arial", Font.BOLD, 13));
        abas.setBorder(new EmptyBorder(0, 12, 12, 12));

        // Construção das tabelas
        tabelaBusca = criarTabela();
        tabelaFavoritos = criarTabela();
        tabelaAssistidas = criarTabela();
        tabelaAssistir = criarTabela();

        // Adição das funções em cada aba.
        abas.add("Buscar", montarPainelBusca());
        abas.add("Meus Favoritos", montarPainelLista(tabelaFavoritos, "Favoritos"));
        abas.add("Séries já assistidas", montarPainelLista(tabelaAssistidas, "Assistidas"));
        abas.add("Séries que deseja assistir", montarPainelLista(tabelaAssistir, "Assistir"));

        add(abas, BorderLayout.CENTER);
    }

    // Construtor da aba de Buscar.
    private JPanel montarPainelBusca() {
        // Painel geral da aba de busca.
        JPanel painel = new JPanel(new BorderLayout(8, 8));
        painel.setBorder(new EmptyBorder(10, 10, 10, 10));
        painel.setBackground(Color.WHITE);

        // Painel dos botões e campo de pesquisa.
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        botoes.setBackground(Color.WHITE);

        // Label do campo de pesquisa.
        JLabel lblPesquisa = new JLabel("Série:");
        txtPesquisa = new JTextField(28);

        // Botões da aba.
        JButton btnFavorito = criarBotao("Adicionar favorito", 170);
        JButton btnAssistida = criarBotao("Adicionar assistida", 175);
        JButton btnDesejo = criarBotao("Adicionar quero assistir", 220);
        JButton btnDetalhes = criarBotao("Ver detalhes", 130);
        JButton btnPesquisar = criarBotao("Pesquisar", 120);
        btnPesquisar.setBackground(color_button);
        btnPesquisar.setForeground(Color.WHITE);

        // Funções atreladas aos botões
        btnFavorito.addActionListener(e -> adicionarSerieNaLista("Favoritos"));
        btnAssistida.addActionListener(e -> adicionarSerieNaLista("Assistidas"));
        btnDesejo.addActionListener(e -> adicionarSerieNaLista("Assistir"));
        btnDetalhes.addActionListener(e -> mostrarDetalhes(getSerieSelecionada(tabelaBusca, resultadoBusca)));
        btnPesquisar.addActionListener(e -> pesquisarSeries());

        botoes.add(btnFavorito);
        botoes.add(btnAssistida);
        botoes.add(btnDesejo);
        botoes.add(btnDetalhes);
        botoes.add(lblPesquisa);
        botoes.add(txtPesquisa);
        botoes.add(btnPesquisar);

        painel.add(botoes, BorderLayout.NORTH);
        painel.add(new JScrollPane(tabelaBusca), BorderLayout.CENTER);

        return painel;
    }

    // Construtor das listas.
    private JPanel montarPainelLista(JTable tabela, String nomeLista) {
        // Painel geral das listas.
        JPanel painel = new JPanel(new BorderLayout(8, 8));
        painel.setBorder(new EmptyBorder(10, 10, 10, 10));
        painel.setBackground(Color.WHITE);

        // Painel dos botões.
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        botoes.setBackground(Color.WHITE);

        // Botões da aba.
        JButton btnRemover = criarBotao("Remover", 110);
        JButton btnDetalhes = criarBotao("Ver detalhes", 130);
        JButton btnOrdenar = criarBotao("Ordenar", 110);

        // Ordenação de items.
        JComboBox<String> comboOrdenacao = new JComboBox<String>(new String[] {
                "Nome", "Nota geral", "Estado", "Data de estreia"
        });
        comboOrdenacao.setPreferredSize(new Dimension(165, 34));

        // Funções atreladas aos botões
        btnRemover.addActionListener(e -> removerSerieDaLista(nomeLista));
        btnDetalhes.addActionListener(e -> mostrarDetalhes(getSerieSelecionadaDaLista(tabela, nomeLista)));
        btnOrdenar.addActionListener(e -> ordenarLista(nomeLista, String.valueOf(comboOrdenacao.getSelectedItem())));

        botoes.add(new JLabel("Ordenar por:", SwingConstants.LEFT));
        botoes.add(comboOrdenacao);
        botoes.add(btnOrdenar);
        botoes.add(btnRemover);
        botoes.add(btnDetalhes);

        painel.add(botoes, BorderLayout.NORTH);
        painel.add(new JScrollPane(tabela), BorderLayout.CENTER);

        return painel;
    }

    // Criador de botão padrão
    private JButton criarBotao(String texto, int largura) {
        JButton botao = new JButton(texto);
        botao.setPreferredSize(new Dimension(largura, 36));
        botao.setMinimumSize(new Dimension(largura, 36));
        botao.setFocusPainted(false);
        botao.setBackground(color_button);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Arial", Font.PLAIN, 13));
        return botao;
    }

    // Criador de tabela padrão
    private JTable criarTabela() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nome");
        modelo.addColumn("Idioma");
        modelo.addColumn("Gêneros");
        modelo.addColumn("Nota");
        modelo.addColumn("Estado");
        modelo.addColumn("Estreia");
        modelo.addColumn("Término");
        modelo.addColumn("Emissora");

        JTable tabela = new JTable(modelo);
        tabela.setRowHeight(26);
        tabela.setFont(new Font("Arial", Font.PLAIN, 13));
        tabela.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setFillsViewportHeight(true);

        // Scroll nas tabelas.
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(210);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(90);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(220);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(70);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(130);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(110);
        tabela.getColumnModel().getColumn(6).setPreferredWidth(110);
        tabela.getColumnModel().getColumn(7).setPreferredWidth(190);

        return tabela;
    }

    // Pesquisa.
    private void pesquisarSeries() {
        try {
            resultadoBusca = tvMazeService.buscarSeries(txtPesquisa.getText());
            preencherTabela(tabelaBusca, resultadoBusca);

            if (resultadoBusca.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nenhuma série encontrada.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Add serie em lista.
    private void adicionarSerieNaLista(String nomeLista) {
        try {
            Serie serie = getSerieSelecionada(tabelaBusca, resultadoBusca);
            List<Serie> lista = getListaPorNome(nomeLista);

            gerenciador.adicionarSerie(lista, serie);
            salvarDados();
            atualizarTabelasDasListas();

            JOptionPane.showMessageDialog(this, "Série adicionada com sucesso.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Remove serie em lista.
    private void removerSerieDaLista(String nomeLista) {
        try {
            JTable tabela = getTabelaPorNome(nomeLista);
            Serie serie = getSerieSelecionadaDaLista(tabela, nomeLista);
            List<Serie> lista = getListaPorNome(nomeLista);

            gerenciador.removerSerie(lista, serie);
            salvarDados();
            atualizarTabelasDasListas();

            JOptionPane.showMessageDialog(this, "Série removida com sucesso.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Ordenação das listas.
    private void ordenarLista(String nomeLista, String tipo) {
        List<Serie> listaOrdenada = gerenciador.ordenar(getListaPorNome(nomeLista), tipo);

        if ("Favoritos".equals(nomeLista)) {
            usuario.setFavoritos(listaOrdenada);
        } else if ("Assistidas".equals(nomeLista)) {
            usuario.setAssistidas(listaOrdenada);
        } else {
            usuario.setAssistir(listaOrdenada);
        }

        try {
            salvarDados();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        atualizarTabelasDasListas();
    }

    // Salva o nome do usuário.
    private void salvarNome() {
        try {
            usuario.setNome(txtNome.getText().trim());
            salvarDados();
            JOptionPane.showMessageDialog(this, "Nome salvo com sucesso.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Salva os dados do usuário.
    private void salvarDados() throws Exception {
        jsonService.salvarUsuario(usuario);
    }

    // Atualiza tabelas.
    private void atualizarTabelasDasListas() {
        preencherTabela(tabelaFavoritos, usuario.getFavoritos());
        preencherTabela(tabelaAssistidas, usuario.getAssistidas());
        preencherTabela(tabelaAssistir, usuario.getAssistir());
    }

    // Popula tabelas.
    private void preencherTabela(JTable tabela, List<Serie> series) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);

        for (Serie serie : series) {
            modelo.addRow(new Object[] {
                    serie.getNome(),
                    serie.getIdioma(),
                    serie.getGenerosTexto(),
                    serie.getNotaGeral(),
                    serie.getEstado(),
                    serie.getDataEstreia(),
                    serie.getDataTermino(),
                    serie.getEmissora()
            });
        }
    }

    // Seleciona serie na tabela
    private Serie getSerieSelecionada(JTable tabela, List<Serie> lista) {
        int linha = tabela.getSelectedRow();

        if (linha < 0) {
            return null;
        }

        return lista.get(linha);
    }

    // Retorna serie selecionada da tabela.
    private Serie getSerieSelecionadaDaLista(JTable tabela, String nomeLista) {
        return getSerieSelecionada(tabela, getListaPorNome(nomeLista));
    }

    // Atualiza dados da lista conforme aba.
    private List<Serie> getListaPorNome(String nomeLista) {
        if ("Favoritos".equals(nomeLista)) {
            return usuario.getFavoritos();
        }

        if ("Assistidas".equals(nomeLista)) {
            return usuario.getAssistidas();
        }

        return usuario.getAssistir();
    }

    // Mostra lista atualizada na tabela.
    private JTable getTabelaPorNome(String nomeLista) {
        if ("Favoritos".equals(nomeLista)) {
            return tabelaFavoritos;
        }

        if ("Assistidas".equals(nomeLista)) {
            return tabelaAssistidas;
        }

        return tabelaAssistir;
    }

    // Mostra detalhes da serie selecionada.
    private void mostrarDetalhes(Serie serie) {
        if (serie == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma série primeiro.");
            return;
        }

        String mensagem = "Nome: " + serie.getNome()
                + "\nIdioma: " + serie.getIdioma()
                + "\nGêneros: " + serie.getGenerosTexto()
                + "\nNota geral: " + serie.getNotaGeral()
                + "\nEstado: " + serie.getEstado()
                + "\nData de estreia: " + serie.getDataEstreia()
                + "\nData de término: " + serie.getDataTermino()
                + "\nEmissora: " + serie.getEmissora();

        JOptionPane.showMessageDialog(this, mensagem, "Detalhes da série", JOptionPane.INFORMATION_MESSAGE);
    }
}
