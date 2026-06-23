import java.awt.*;
import javax.swing.*;

// ================================================================

public class ClimaGUI extends JFrame {

    
    private JTextField campoCidade;     // Campo onde usuário digita a cidade
    private JButton    btnBuscar;       // Botão de busca
    private JLabel     lblStatus;       // Mensagem de status ou erro

    // Labels que exibem os dados do clima
    private JLabel lblCidade;
    private JLabel lblCondicao;
    private JLabel lblTempAtual;
    private JLabel lblTempMax;
    private JLabel lblTempMin;
    private JLabel lblSensacao;
    private JLabel lblUmidade;
    private JLabel lblPrecipitacao;
    private JLabel lblUV;
    private JLabel lblVento;

    private ClimaService climaService = new ClimaService();

    private static final Color COR_AZUL       = new Color(30, 100, 180);
    private static final Color COR_FUNDO      = new Color(235, 245, 255);
    private static final Color COR_CARD       = Color.WHITE;
    private static final Color COR_LABEL      = new Color(90, 90, 110);
    private static final Color COR_TEXTO      = new Color(30, 30, 50);
    private static final Color COR_VERMELHO   = new Color(180, 40, 40);
    private static final Color COR_LARANJA    = new Color(200, 80, 20);
    private static final Color COR_AZUL_FRIO  = new Color(20, 80, 180);
    private static final Color COR_VERDE      = new Color(20, 130, 60);

    public ClimaGUI() {
        setTitle("App de Clima");
        setSize(460, 620);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COR_FUNDO);
        setLayout(new BorderLayout());

        montarInterface();
        setVisible(true);
    }

    
    private void montarInterface() {

        
        JPanel painelTopo = new JPanel(new GridBagLayout());
        painelTopo.setBackground(COR_AZUL);
        painelTopo.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);

        // Título
        JLabel lblTitulo = new JLabel("☁  Consulta de Clima");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(Color.WHITE);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.weightx = 1.0;
        painelTopo.add(lblTitulo, gbc);

        // Campo de cidade
        campoCidade = new JTextField();
        campoCidade.setFont(new Font("Arial", Font.PLAIN, 14));
        campoCidade.setToolTipText("Ex: Cascavel, Curitiba, São Paulo");
        campoCidade.addActionListener(e -> buscarClima()); // Enter para buscar
        gbc.gridy = 1; gbc.gridwidth = 1; gbc.weightx = 1.0;
        painelTopo.add(campoCidade, gbc);

        // Botão buscar
        btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 13));
        btnBuscar.setBackground(Color.WHITE);
        btnBuscar.setForeground(COR_AZUL);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscar.addActionListener(e -> buscarClima());
        gbc.gridx = 1; gbc.weightx = 0;
        painelTopo.add(btnBuscar, gbc);

        
        lblStatus = new JLabel("Digite o nome de uma cidade e clique em Buscar.");
        lblStatus.setFont(new Font("Arial", Font.ITALIC, 12));
        lblStatus.setForeground(new Color(180, 220, 255));
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.weightx = 1.0;
        painelTopo.add(lblStatus, gbc);

        add(painelTopo, BorderLayout.NORTH);

  
        JPanel painelCentro = new JPanel();
        painelCentro.setLayout(new BoxLayout(painelCentro, BoxLayout.Y_AXIS));
        painelCentro.setBackground(COR_FUNDO);
        painelCentro.setBorder(BorderFactory.createEmptyBorder(14, 14, 14, 14));

        
        JPanel card1 = criarCard();
        card1.setLayout(new BoxLayout(card1, BoxLayout.Y_AXIS));

        lblCidade   = novoLabel("—", 15, COR_LABEL, Font.PLAIN);
        lblCondicao = novoLabel("—", 14, COR_AZUL,  Font.BOLD);
        lblTempAtual = novoLabel("—", 44, COR_TEXTO, Font.BOLD);

        card1.add(lblCidade);
        card1.add(Box.createVerticalStrut(2));
        card1.add(lblCondicao);
        card1.add(Box.createVerticalStrut(6));
        card1.add(lblTempAtual);

        painelCentro.add(card1);
        painelCentro.add(Box.createVerticalStrut(10));

        
        JPanel card2 = criarCard();
        card2.setLayout(new GridLayout(1, 3, 8, 0));

       
        lblTempMax  = novoLabel("—", 18, COR_LARANJA,  Font.BOLD);
        lblTempMin  = novoLabel("—", 18, COR_AZUL_FRIO, Font.BOLD);
        lblSensacao = novoLabel("—", 18, COR_LABEL,     Font.BOLD);

        card2.add(montarMiniCard("🌡  Máxima",  lblTempMax));
        card2.add(montarMiniCard("❄  Mínima",   lblTempMin));
        card2.add(montarMiniCard("🤔  Sensação", lblSensacao));

        painelCentro.add(card2);
        painelCentro.add(Box.createVerticalStrut(10));

       
        JPanel card3 = criarCard();
        card3.setLayout(new GridLayout(1, 3, 8, 0));

        lblUmidade      = novoLabel("—", 18, COR_AZUL,   Font.BOLD);
        lblPrecipitacao = novoLabel("—", 18, COR_AZUL,   Font.BOLD);
        lblUV           = novoLabel("—", 18, COR_LARANJA, Font.BOLD);

        card3.add(montarMiniCard("💧  Umidade",    lblUmidade));
        card3.add(montarMiniCard("🌧  Precipit.",  lblPrecipitacao));
        card3.add(montarMiniCard("☀  Índice UV",   lblUV));

        painelCentro.add(card3);
        painelCentro.add(Box.createVerticalStrut(10));

        
        JPanel card4 = criarCard();
        card4.setLayout(new BoxLayout(card4, BoxLayout.Y_AXIS));

        JLabel lblVentoTit = novoLabel("💨  Vento", 12, COR_LABEL, Font.PLAIN);
        lblVento = novoLabel("—", 17, COR_TEXTO, Font.BOLD);

        card4.add(lblVentoTit);
        card4.add(Box.createVerticalStrut(4));
        card4.add(lblVento);

        painelCentro.add(card4);

    
        JScrollPane scroll = new JScrollPane(painelCentro);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(12);
        add(scroll, BorderLayout.CENTER);
    }

   
    private void buscarClima() {
        String cidade = campoCidade.getText().trim();

        if (cidade.isEmpty()) {
            lblStatus.setForeground(new Color(255, 200, 100));
            lblStatus.setText("⚠  Digite o nome de uma cidade.");
            return;
        }

        btnBuscar.setEnabled(false);
        btnBuscar.setText("...");
        lblStatus.setForeground(new Color(180, 220, 255));
        lblStatus.setText("Consultando API...");

       
        SwingWorker<DadosClima, Void> worker = new SwingWorker<>() {
            @Override
            protected DadosClima doInBackground() throws Exception {
              
                return climaService.buscarClima(cidade);
            }

            @Override
            protected void done() {
            
                btnBuscar.setEnabled(true);
                btnBuscar.setText("Buscar");
                try {
                    DadosClima dados = get();
                    exibirDados(dados);
                    lblStatus.setForeground(new Color(150, 230, 150));
                    lblStatus.setText("✔  Dados atualizados com sucesso.");
                } catch (Exception ex) {
                    Throwable causa = ex.getCause() != null ? ex.getCause() : ex;
                    lblStatus.setForeground(new Color(255, 150, 100));
                    lblStatus.setText("✘  " + causa.getMessage());
                }
            }
        };
        worker.execute();
    }

    
    private void exibirDados(DadosClima d) {
        lblCidade.setText(d.getCidade());
        lblCondicao.setText(d.getDescricaoTraduzida());
        lblTempAtual.setText(String.format("%.1f °C", d.getTempAtual()));
        lblTempMax.setText(String.format("%.1f °C", d.getTempMaxima()));
        lblTempMin.setText(String.format("%.1f °C", d.getTempMinima()));
        lblSensacao.setText(String.format("%.1f °C", d.getSensacaoTermica()));
        lblUmidade.setText(String.format("%.0f%%", d.getUmidade()));
        lblPrecipitacao.setText(String.format("%.1f mm", d.getPrecipitacao()));
        lblUV.setText(String.format("%.1f", d.getUvIndex()));
        lblVento.setText(String.format("%.1f km/h  →  %s (%.0f°)",
            d.getVelocidadeVento(), d.getDirecaoVentoTexto(), d.getDirecaoVento()));
    }

    

    
    private JLabel novoLabel(String texto, int tamanho, Color cor, int estilo) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Arial", estilo, tamanho));
        lbl.setForeground(cor);
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private JPanel montarMiniCard(String titulo, JLabel lblValor) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);

        JLabel lblTit = new JLabel(titulo);
        lblTit.setFont(new Font("Arial", Font.PLAIN, 11));
        lblTit.setForeground(COR_LABEL);

        p.add(lblTit);
        p.add(Box.createVerticalStrut(4));
        p.add(lblValor); 
        return p;
    }

    
    private JPanel criarCard() {
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        card.setOpaque(false);
        card.setBackground(COR_CARD);
        card.setBorder(BorderFactory.createEmptyBorder(14, 16, 14, 16));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        return card;
    }

    // ================================================================
    // MAIN: ponto de entrada do programa
    // ================================================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Usa o look padrão do Java se não conseguir o do sistema
            }
            new ClimaGUI();
        });
    }
}
