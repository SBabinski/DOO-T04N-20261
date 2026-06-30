package view;

import java.awt.*;
import javax.swing.*;
import controller.UsuarioController;

public class TelaPrincipal extends JFrame {
    private final UsuarioController usuarioController;

    protected static final Color COR_FUNDO_CLARO = new Color(245, 246, 250);
    protected static final Color COR_CARD_CLARO  = new Color(255, 255, 255);
    protected static final Color COR_TEXTO_MAIN  = new Color(47, 53, 66);
    protected static final Color COR_TEXTO_MUTED = new Color(116, 125, 140);
    protected static final Color COR_AZUL_PADRAO = new Color(74, 144, 226);

    public TelaPrincipal(UsuarioController usuarioController) {
        this.usuarioController = usuarioController;
        configurarConfiguracoesJanela();
        construirInterfaceGrafica();
    }

    private void configurarConfiguracoesJanela() {
        setTitle("Sist. Prova");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                exibirConfirmacaoSaida();
            }
        });
    }

    private void construirInterfaceGrafica() {
        JPanel painelMestreTresColunas = new JPanel(new GridLayout(1, 3, 15, 0));
        painelMestreTresColunas.setBackground(COR_FUNDO_CLARO);
        painelMestreTresColunas.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Painel da esquerda
        JPanel painelEsquerdoMenu = new JPanel(new BorderLayout());
        painelEsquerdoMenu.setBackground(COR_CARD_CLARO);
        painelEsquerdoMenu.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230)),
                BorderFactory.createEmptyBorder(25, 20, 25, 20)
        ));

        JLabel labelTituloSistema = new JLabel("TV Tracker", SwingConstants.CENTER);
        labelTituloSistema.setFont(new Font("Segoe UI", Font.BOLD, 26));
        labelTituloSistema.setForeground(COR_TEXTO_MAIN);

        JPanel painelBotoesNavegacao = new JPanel(new GridLayout(3, 1, 0, 15));
        painelBotoesNavegacao.setBackground(COR_CARD_CLARO);

        JButton botaoAbrirBusca = criarBotaoEstilizado("Buscar Novas Séries", COR_AZUL_PADRAO);
        JButton botaoAbrirListas = criarBotaoEstilizado("Ver Minhas Listas", new Color(46, 204, 113));
        JButton botaoSairSistema = criarBotaoEstilizado("Salvar e Sair", new Color(231, 76, 60));

        botaoAbrirBusca.addActionListener(e -> {
            new TelaBusca(usuarioController).setVisible(true);
            this.dispose();
        });
        botaoAbrirListas.addActionListener(e -> {
            new TelaListas(usuarioController).setVisible(true);
            this.dispose();
        });
        botaoSairSistema.addActionListener(e -> exibirConfirmacaoSaida());

        painelBotoesNavegacao.add(botaoAbrirBusca);
        painelBotoesNavegacao.add(botaoAbrirListas);
        painelBotoesNavegacao.add(botaoSairSistema);

        painelEsquerdoMenu.add(labelTituloSistema, BorderLayout.NORTH);
        painelEsquerdoMenu.add(painelBotoesNavegacao, BorderLayout.CENTER);

        // Painel do meio
        JPanel painelCentralInfo = new JPanel(new GridBagLayout());
        painelCentralInfo.setBackground(COR_CARD_CLARO);
        painelCentralInfo.setBorder(BorderFactory.createLineBorder(new Color(220, 224, 230)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel labelSaudacao = new JLabel("Bem-vindo de volta,", SwingConstants.CENTER);
        labelSaudacao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        labelSaudacao.setForeground(COR_TEXTO_MUTED);
        painelCentralInfo.add(labelSaudacao, gbc);

        gbc.gridy = 1;
        JLabel labelNomeUsuario = new JLabel(usuarioController.getUsuario().getNome(), SwingConstants.CENTER);
        labelNomeUsuario.setFont(new Font("Segoe UI", Font.BOLD, 22));
        labelNomeUsuario.setForeground(COR_TEXTO_MAIN);
        painelCentralInfo.add(labelNomeUsuario, gbc);

        // Painel da direita
        JPanel painelDireitoDashboard = new JPanel(new GridLayout(3, 1, 0, 15));
        painelDireitoDashboard.setBackground(COR_CARD_CLARO);
        painelDireitoDashboard.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 224, 230)),
                BorderFactory.createEmptyBorder(30, 20, 30, 20)
        ));

        painelDireitoDashboard.add(criarCardEstatistica("Favoritas", String.valueOf(usuarioController.getUsuario().getFavoritos().size())));
        painelDireitoDashboard.add(criarCardEstatistica("Já Assistidas", String.valueOf(usuarioController.getUsuario().getAssistidos().size())));
        painelDireitoDashboard.add(criarCardEstatistica("Quero Assistir", String.valueOf(usuarioController.getUsuario().getQueroAssistir().size())));

        painelMestreTresColunas.add(painelEsquerdoMenu);
        painelMestreTresColunas.add(painelCentralInfo);
        painelMestreTresColunas.add(painelDireitoDashboard);

        add(painelMestreTresColunas);
    }

    private JPanel criarCardEstatistica(String titulo, String valor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(COR_FUNDO_CLARO);
        card.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.LEFT);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblTitulo.setForeground(COR_TEXTO_MUTED);

        JLabel lblValor = new JLabel(valor, SwingConstants.RIGHT);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblValor.setForeground(COR_AZUL_PADRAO);

        card.add(lblTitulo, BorderLayout.NORTH);
        card.add(lblValor, BorderLayout.SOUTH);
        return card;
    }

    public static JButton criarBotaoEstilizado(String texto, Color corBase) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Segoe UI", Font.BOLD, 13));
        botao.setBackground(corBase);
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));
        return botao;
    }

    private void exibirConfirmacaoSaida() {
        int resultado = JOptionPane.showConfirmDialog(this, "Deseja salvar e sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (resultado == JOptionPane.YES_OPTION) {
            try {
                usuarioController.salvarDados();
                System.exit(0);
            } catch (Exception ex) {
                System.exit(0);
            }
        }
    }
}