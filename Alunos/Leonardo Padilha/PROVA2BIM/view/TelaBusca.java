package view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import controller.TvMazeController;
import controller.UsuarioController;
import model.Serie;

public class TelaBusca extends JFrame {
    private final UsuarioController usuarioController;
    private final TvMazeController tvMazeController;

    private JTextField campoEntradaBusca;
    private JList<Serie> componenteListaResultados;
    private DefaultListModel<Serie> modeloListaResultados;
    private JTextArea areaTextoDetalhes;

    public TelaBusca(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
        this.tvMazeController = new TvMazeController();
        configurarJanela();
        construirInterfaceGrafica();
    }

    private void configurarJanela() {
        setTitle("Sist. Prova - Buscar Catálogo");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void construirInterfaceGrafica() {
        JPanel painelMestreTresColunas = new JPanel(new GridLayout(1, 3, 15, 0));
        painelMestreTresColunas.setBackground(TelaPrincipal.COR_FUNDO_CLARO);
        painelMestreTresColunas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Painel da esquerda
        JPanel painelEsquerdoAcoes = new JPanel(new BorderLayout());
        painelEsquerdoAcoes.setBackground(TelaPrincipal.COR_CARD_CLARO);
        painelEsquerdoAcoes.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230)),
                BorderFactory.createEmptyBorder(25, 20, 25, 20)
        ));

        JLabel labelMenu = new JLabel("Ações Disponíveis", SwingConstants.CENTER);
        labelMenu.setFont(new Font("Segoe UI", Font.BOLD, 18));
        labelMenu.setForeground(TelaPrincipal.COR_TEXTO_MAIN);

        JPanel painelBotoesListas = new JPanel(new GridLayout(4, 1, 0, 12));
        painelBotoesListas.setBackground(TelaPrincipal.COR_CARD_CLARO);

        JButton botaoAdicionarFav = TelaPrincipal.criarBotaoEstilizado("Adicionar aos Favoritos", new Color(241, 196, 15));
        JButton botaoAdicionarAssistido = TelaPrincipal.criarBotaoEstilizado("Marcar como Já Assisti", new Color(46, 204, 113));
        JButton botaoAdicionarQuero = TelaPrincipal.criarBotaoEstilizado("Salvar em Quero Assistir", TelaPrincipal.COR_AZUL_PADRAO);
        JButton botaoVoltarMenu = TelaPrincipal.criarBotaoEstilizado("← Voltar ao Menu", new Color(149, 165, 166));

        botaoAdicionarFav.addActionListener(e -> gerenciarInclusaoLista("favoritos"));
        botaoAdicionarAssistido.addActionListener(e -> gerenciarInclusaoLista("assistidas"));
        botaoAdicionarQuero.addActionListener(e -> gerenciarInclusaoLista("queroAssistir"));
        botaoVoltarMenu.addActionListener(e -> {
            new TelaPrincipal(usuarioController).setVisible(true);
            this.dispose();
        });

        painelBotoesListas.add(botaoAdicionarFav);
        painelBotoesListas.add(botaoAdicionarAssistido);
        painelBotoesListas.add(botaoAdicionarQuero);
        painelBotoesListas.add(botaoVoltarMenu);

        painelEsquerdoAcoes.add(labelMenu, BorderLayout.NORTH);
        painelEsquerdoAcoes.add(painelBotoesListas, BorderLayout.CENTER);

        // Painel do meio
        JPanel painelCentralPesquisa = new JPanel(new BorderLayout(0, 15));
        painelCentralPesquisa.setBackground(TelaPrincipal.COR_CARD_CLARO);
        painelCentralPesquisa.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230)),
                BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));

        JLabel labelFiltro = new JLabel("Digite o nome da série:", SwingConstants.LEFT);
        labelFiltro.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelFiltro.setForeground(TelaPrincipal.COR_TEXTO_MAIN);

        campoEntradaBusca = new JTextField();
        campoEntradaBusca.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoEntradaBusca.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));

        JButton botaoPesquisar = TelaPrincipal.criarBotaoEstilizado("Pesquisar na API", TelaPrincipal.COR_AZUL_PADRAO);
        botaoPesquisar.addActionListener(e -> executarBuscaApi());
        campoEntradaBusca.addActionListener(e -> executarBuscaApi());

        JPanel painelTopoBusca = new JPanel(new BorderLayout(0, 5));
        painelTopoBusca.setBackground(TelaPrincipal.COR_CARD_CLARO);
        painelTopoBusca.add(labelFiltro, BorderLayout.NORTH);
        painelTopoBusca.add(campoEntradaBusca, BorderLayout.CENTER);
        painelTopoBusca.add(botaoPesquisar, BorderLayout.SOUTH);

        modeloListaResultados = new DefaultListModel<>();
        componenteListaResultados = new JList<>(modeloListaResultados);
        componenteListaResultados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        componenteListaResultados.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        componenteListaResultados.setCellRenderer((list, serie, index, isSelected, cellHasFocus) -> {
            DefaultListCellRenderer renderer = new DefaultListCellRenderer();
            JLabel label = (JLabel) renderer.getListCellRendererComponent(list, serie, index, isSelected, cellHasFocus);
            if (serie != null) {
                label.setText(serie.getNome() + " (" + String.format("%.1f", serie.getNota()) + ")");
                label.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
            }
            return label;
        });

        componenteListaResultados.addListSelectionListener(e -> atualizarPainelDetalhes());

        JScrollPane scrollLista = new JScrollPane(componenteListaResultados);
        scrollLista.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230)));

        painelCentralPesquisa.add(painelTopoBusca, BorderLayout.NORTH);
        painelCentralPesquisa.add(scrollLista, BorderLayout.CENTER);

        // Painel da direita
        JPanel painelDireitoDetalhes = new JPanel(new BorderLayout(0, 10));
        painelDireitoDetalhes.setBackground(TelaPrincipal.COR_CARD_CLARO);
        painelDireitoDetalhes.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230)),
                BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));

        JLabel labelDetalhes = new JLabel("Ficha Técnica da Série", SwingConstants.CENTER);
        labelDetalhes.setFont(new Font("Segoe UI", Font.BOLD, 15));
        labelDetalhes.setForeground(TelaPrincipal.COR_TEXTO_MAIN);

        areaTextoDetalhes = new JTextArea("Selecione uma série na lista ao lado para ler os detalhes técnicos e sinopse.");
        areaTextoDetalhes.setEditable(false);
        areaTextoDetalhes.setLineWrap(true);
        areaTextoDetalhes.setWrapStyleWord(true);
        areaTextoDetalhes.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        areaTextoDetalhes.setForeground(TelaPrincipal.COR_TEXTO_MUTED);

        JScrollPane scrollDetalhes = new JScrollPane(areaTextoDetalhes);
        scrollDetalhes.setBorder(BorderFactory.createEmptyBorder());

        painelDireitoDetalhes.add(labelDetalhes, BorderLayout.NORTH);
        painelDireitoDetalhes.add(scrollDetalhes, BorderLayout.CENTER);

        painelMestreTresColunas.add(painelEsquerdoAcoes);
        painelMestreTresColunas.add(painelCentralPesquisa);
        painelMestreTresColunas.add(painelDireitoDetalhes);

        add(painelMestreTresColunas);
    }

    private void executarBuscaApi() {
        String texto = campoEntradaBusca.getText().trim();
        if (texto.isEmpty()) return;

        modeloListaResultados.clear();
        areaTextoDetalhes.setText("Buscando dados...");

        SwingWorker<List<Serie>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Serie> doInBackground() {
                return tvMazeController.consultarSeriesPorNome(texto);
            }
            @Override
            protected void done() {
                try {
                    List<Serie> resultado = get();
                    if (resultado.isEmpty()) {
                        areaTextoDetalhes.setText("Nenhum resultado condizente encontrado.");
                    } else {
                        for (Serie s : resultado) modeloListaResultados.addElement(s);
                        areaTextoDetalhes.setText("Selecione um item da lista acima para detalhar.");
                    }
                } catch (Exception e) {
                    areaTextoDetalhes.setText("Erro ao tentar contactar a API.");
                }
            }
        };
        worker.execute();
    }

    private void atualizarPainelDetalhes() {
        Serie s = componenteListaResultados.getSelectedValue();
        if (s == null) return;

        String ficha = String.format(
                "Título: %s\n\nAvaliação: %.1f / 10\nIdioma: %s\nStatus: %s\nEstreia: %s\nGêneros: %s\nEmissora: %s\n\nSinopse:\n%s",
                s.getNome(), s.getNota(), s.getIdioma(), s.getStatus(), s.getDataEstreia(), s.getGeneros(), s.getEmissora(), s.getSumario()
        );
        areaTextoDetalhes.setText(ficha);
        areaTextoDetalhes.setForeground(TelaPrincipal.COR_TEXTO_MAIN);
    }

    private void gerenciarInclusaoLista(String tipoLista) {
        Serie s = componenteListaResultados.getSelectedValue();
        if (s == null) {
            JOptionPane.showMessageDialog(this, "Selecione uma série da lista antes.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        switch (tipoLista) {
            case "favoritos"     -> usuarioController.favoritarSerie(s);
            case "assistidas"    -> usuarioController.marcarComoAssistida(s);
            case "queroAssistir" -> usuarioController.adicionarListaQueroAssistir(s);
        }
        JOptionPane.showMessageDialog(this, "Adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }
}