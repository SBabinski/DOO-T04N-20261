package view;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import controller.UsuarioController;
import model.Serie;

public class TelaListas extends JFrame {
    private final UsuarioController usuarioController;

    private JTabbedPane abasAbasCategorias;
    private JList<Serie> listaFavoritos, listaAssistidas, listaQueroAssistir;
    private DefaultListModel<Serie> modeloFav, modeloAssis, modeloQuero;

    public TelaListas(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
        configurarJanela();
        construirInterfaceGrafica();
        sincronizarListasInterface();
    }

    private void configurarJanela() {
        setTitle("Sist. Prova - Minhas Coleções Pessoais");
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

        JLabel labelGerenciar = new JLabel("Gerenciar Itens", SwingConstants.CENTER);
        labelGerenciar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        labelGerenciar.setForeground(TelaPrincipal.COR_TEXTO_MAIN);

        JPanel painelBotoesManipulacao = new JPanel(new GridLayout(2, 1, 0, 20));
        painelBotoesManipulacao.setBackground(TelaPrincipal.COR_CARD_CLARO);

        JButton botaoRemover = TelaPrincipal.criarBotaoEstilizado("Remover Item Selecionado", new Color(231, 76, 60));
        JButton botaoVoltar = TelaPrincipal.criarBotaoEstilizado("← Voltar ao Menu", new Color(149, 165, 166));

        botaoRemover.addActionListener(e -> executarExclusaoItem());
        botaoVoltar.addActionListener(e -> {
            new TelaPrincipal(usuarioController).setVisible(true);
            this.dispose();
        });

        painelBotoesManipulacao.add(botaoRemover);
        painelBotoesManipulacao.add(botaoVoltar);

        painelEsquerdoAcoes.add(labelGerenciar, BorderLayout.NORTH);
        painelEsquerdoAcoes.add(painelBotoesManipulacao, BorderLayout.CENTER);

        // Painel do meio
        JPanel painelCentralOrdenacao = new JPanel(new BorderLayout(0, 15));
        painelCentralOrdenacao.setBackground(TelaPrincipal.COR_CARD_CLARO);
        painelCentralOrdenacao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230)),
                BorderFactory.createEmptyBorder(20, 15, 20, 15)
        ));

        JLabel labelOrdenacao = new JLabel("Ordenar Lista Atual por:", SwingConstants.CENTER);
        labelOrdenacao.setFont(new Font("Segoe UI", Font.BOLD, 15));
        labelOrdenacao.setForeground(TelaPrincipal.COR_TEXTO_MAIN);

        JPanel painelBotoesFiltros = new JPanel(new GridLayout(4, 1, 0, 12));
        painelBotoesFiltros.setBackground(TelaPrincipal.COR_CARD_CLARO);

        JButton btnNome = TelaPrincipal.criarBotaoEstilizado("Ordem Alfabética (Nome)", TelaPrincipal.COR_AZUL_PADRAO);
        JButton btnNota = TelaPrincipal.criarBotaoEstilizado("Melhor Avaliação (Nota)", TelaPrincipal.COR_AZUL_PADRAO);
        JButton btnStatus = TelaPrincipal.criarBotaoEstilizado("Status de Transmissão", TelaPrincipal.COR_AZUL_PADRAO);
        JButton btnData = TelaPrincipal.criarBotaoEstilizado("Ano de Lançamento (Estreia)", TelaPrincipal.COR_AZUL_PADRAO);

        btnNome.addActionListener(e -> dispararOrdenacao("nome"));
        btnNota.addActionListener(e -> dispararOrdenacao("nota"));
        btnStatus.addActionListener(e -> dispararOrdenacao("status"));
        btnData.addActionListener(e -> dispararOrdenacao("data"));

        painelBotoesFiltros.add(btnNome);
        painelBotoesFiltros.add(btnNota);
        painelBotoesFiltros.add(btnStatus);
        painelBotoesFiltros.add(btnData);

        painelCentralOrdenacao.add(labelOrdenacao, BorderLayout.NORTH);
        painelCentralOrdenacao.add(painelBotoesFiltros, BorderLayout.CENTER);

        // Painel da direita
        JPanel painelDireitoListagem = new JPanel(new BorderLayout());
        painelDireitoListagem.setBackground(TelaPrincipal.COR_CARD_CLARO);
        painelDireitoListagem.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230)),
                BorderFactory.createEmptyBorder(15, 10, 15, 10)
        ));

        modeloFav = new DefaultListModel<>();
        listaFavoritos = new JList<>(modeloFav);
        configurarRenderizadorDeNome(listaFavoritos);

        modeloAssis = new DefaultListModel<>();
        listaAssistidas = new JList<>(modeloAssis);
        configurarRenderizadorDeNome(listaAssistidas);

        modeloQuero = new DefaultListModel<>();
        listaQueroAssistir = new JList<>(modeloQuero);
        configurarRenderizadorDeNome(listaQueroAssistir);

        abasAbasCategorias = new JTabbedPane();
        abasAbasCategorias.setFont(new Font("Segoe UI", Font.BOLD, 12));
        abasAbasCategorias.addTab("Favoritos", new JScrollPane(listaFavoritos));
        abasAbasCategorias.addTab("Assistidos", new JScrollPane(listaAssistidas));
        abasAbasCategorias.addTab("Quero Ver", new JScrollPane(listaQueroAssistir));

        painelDireitoListagem.add(abasAbasCategorias, BorderLayout.CENTER);

        painelMestreTresColunas.add(painelEsquerdoAcoes);
        painelMestreTresColunas.add(painelCentralOrdenacao);
        painelMestreTresColunas.add(painelDireitoListagem);

        add(painelMestreTresColunas);
    }

    private void configurarRenderizadorDeNome(JList<Serie> listaAlvo) {
        listaAlvo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        listaAlvo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaAlvo.setCellRenderer((list, serie, index, isSelected, cellHasFocus) -> {
            DefaultListCellRenderer renderer = new DefaultListCellRenderer();
            JLabel label = (JLabel) renderer.getListCellRendererComponent(list, serie, index, isSelected, cellHasFocus);
            if (serie != null) {
                String statusTransmissao = (serie.getStatus() != null) ? serie.getStatus() : "Desconhecido";

                label.setText(String.format("%s  •  [Nota: %.1f]  •  [%s]", serie.getNome(), serie.getNota(), statusTransmissao));
                label.setBorder(BorderFactory.createEmptyBorder(6, 8, 6, 8));
            }
            return label;
        });
    }

    private void sincronizarListasInterface() {
        povoarModelo(modeloFav, usuarioController.getUsuario().getFavoritos());
        povoarModelo(modeloAssis, usuarioController.getUsuario().getAssistidos());
        povoarModelo(modeloQuero, usuarioController.getUsuario().getQueroAssistir());
    }

    private void povoarModelo(DefaultListModel<Serie> model, List<Serie> listaOriginal) {
        model.clear();
        for (Serie s : listaOriginal) {
            model.addElement(s);
        }
    }

    private void dispararOrdenacao(String criterio) {
        int abaSelecionada = abasAbasCategorias.getSelectedIndex();
        List<Serie> listaAlvo = null;
        DefaultListModel<Serie> modeloAlvo = null;
        JList<Serie> componenteListaAlvo = null;

        if (abaSelecionada == 0) {
            listaAlvo = usuarioController.getUsuario().getFavoritos();
            modeloAlvo = modeloFav;
            componenteListaAlvo = listaFavoritos;
        } else if (abaSelecionada == 1) {
            listaAlvo = usuarioController.getUsuario().getAssistidos();
            modeloAlvo = modeloAssis;
            componenteListaAlvo = listaAssistidas;
        } else if (abaSelecionada == 2) {
            listaAlvo = usuarioController.getUsuario().getQueroAssistir();
            modeloAlvo = modeloQuero;
            componenteListaAlvo = listaQueroAssistir;
        }

        if (listaAlvo == null || listaAlvo.isEmpty()) return;

        switch (criterio) {
            case "nome"   -> usuarioController.ordenarListaPorNome(listaAlvo);
            case "nota"   -> usuarioController.ordenarListaPorNota(listaAlvo);
            case "status" -> usuarioController.ordenarListaPorStatus(listaAlvo);
            case "data"   -> usuarioController.ordenarListaPorDataEstreia(listaAlvo);
        }

        povoarModelo(modeloAlvo, listaAlvo);
        if (componenteListaAlvo != null) {
            componenteListaAlvo.repaint();
        }
    }

    private void executarExclusaoItem() {
        int abaSelecionada = abasAbasCategorias.getSelectedIndex();
        Serie s = null;

        if (abaSelecionada == 0) s = listaFavoritos.getSelectedValue();
        else if (abaSelecionada == 1) s = listaAssistidas.getSelectedValue();
        else if (abaSelecionada == 2) s = listaQueroAssistir.getSelectedValue();

        if (s == null) {
            JOptionPane.showMessageDialog(this, "Selecione um item na lista da direita primeiro.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int escolha = JOptionPane.showConfirmDialog(this, "Remover '" + s.getNome() + "' da lista atual?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (escolha == JOptionPane.YES_OPTION) {
            if (abaSelecionada == 0) usuarioController.desfavoritarSerie(s);
            else if (abaSelecionada == 1) usuarioController.removerDeAssistidas(s);
            else if (abaSelecionada == 2) usuarioController.removerListaQueroAssistir(s);

            sincronizarListasInterface();
        }
    }
}