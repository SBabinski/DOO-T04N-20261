

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

/* 
 * Interface gráfica principal do aplicativo. Responsável por construir toda a janela, 
 * organizar os componentes, lidar com eventos de usuário e exibir os dados climáticos
 * de forma clara e atraente.
 */
public class WeatherUI extends JFrame {

    // Paleta de cores principal
    private static final Color COR_FUNDO_TOP   = new Color(10, 35, 80);
    private static final Color COR_FUNDO_BOT   = new Color(18, 90, 180);
    private static final Color COR_CARD        = new Color(255, 255, 255, 28);
    private static final Color COR_CARD_BORDA  = new Color(255, 255, 255, 55);
    private static final Color COR_TEXTO       = Color.WHITE;
    private static final Color COR_TEXTO_SUB   = new Color(190, 215, 255);
    private static final Color COR_BOTAO       = new Color(55, 155, 240);
    private static final Color COR_BOTAO_HOVER = new Color(90, 175, 255);
    private static final Color COR_ERRO_BG     = new Color(180, 30, 30, 80);
    private static final Color COR_ERRO_BORDA  = new Color(255, 100, 100, 120);
    private static final Color COR_HIST_HOVER  = new Color(255, 255, 255, 18);
    private static final Color COR_SEPARADOR   = new Color(255, 255, 255, 30);

    // Cores das barras de classificação
    private static final Color COR_BARRA_FRIA  = new Color(80, 160, 255);
    private static final Color COR_BARRA_MED   = new Color(80, 220, 140);
    private static final Color COR_BARRA_QUENTE = new Color(255, 140, 60);

    // Fontes
    private static final Font F_TITULO   = new Font("SansSerif", Font.BOLD,  17);
    private static final Font F_TEMP     = new Font("SansSerif", Font.BOLD,  68);
    private static final Font F_CIDADE   = new Font("SansSerif", Font.BOLD,  18);
    private static final Font F_COND     = new Font("SansSerif", Font.PLAIN, 14);
    private static final Font F_LABEL    = new Font("SansSerif", Font.PLAIN, 10);
    private static final Font F_VALOR    = new Font("SansSerif", Font.BOLD,  15);
    private static final Font F_ICONE    = new Font("Segoe UI Emoji", Font.PLAIN, 26);
    private static final Font F_ICONE_G  = new Font("Segoe UI Emoji", Font.PLAIN, 54);
    private static final Font F_BOTAO    = new Font("SansSerif", Font.BOLD,  13);
    private static final Font F_CAMPO    = new Font("SansSerif", Font.PLAIN, 13);
    private static final Font F_HIST     = new Font("SansSerif", Font.PLAIN, 12);
    private static final Font F_CLASS    = new Font("SansSerif", Font.PLAIN, 11);
    private static final Font F_HORA     = new Font("SansSerif", Font.PLAIN, 10);

    // CAMPOS DA INSTÂNCIA
    private JTextField   campoCidade;
    private JButton      btnBuscar;
    private JPanel       painelResultado;
    private JLabel       lblStatus;
    private JPanel       painelHistorico;

    // Serviço de consulta climática (API) usado para buscar os dados do clima.
    private final WeatherService servico;

    // Lista de histórico de buscas recentes para exibição no rodapé.
    private final List<String> historico = new ArrayList<>();

    // Construtor 
    public WeatherUI() {
        servico = new WeatherService();
        configurarJanela();
        construirLayout();
        setVisible(true);
    }

    // CONFIGURAÇÃO DA JANELA

    /* 
     * Configura as propriedades básicas da janela, como título, tamanho, 
     * comportamento de fechamento e ícone.
     */
    private void configurarJanela() {
        setTitle("Previsão do Tempo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(470, 760);
        setMinimumSize(new Dimension(400, 580));
        setLocationRelativeTo(null);
        setResizable(true);
        setIconImage(gerarIconeApp());
    }

    // CONSTRUÇÃO DO LAYOUT PRINCIPAL

    /* 
     * Organiza os componentes principais da interface: topo com busca, 
     * área central de resultados e rodapé de histórico.
     */     
    private void construirLayout() {

        // Painel raiz com gradiente vertical como fundo
        JPanel raiz = criarPainelGradiente();
        raiz.setLayout(new BorderLayout(0, 0));
        raiz.setBorder(new EmptyBorder(20, 18, 16, 18));

        // Seção do topo
        JPanel topo = new JPanel(new BorderLayout(0, 10));
        topo.setOpaque(false);
        topo.add(criarCabecalho(),  BorderLayout.NORTH);
        topo.add(criarBarraBusca(), BorderLayout.CENTER);

        lblStatus = new JLabel("", SwingConstants.CENTER);
        lblStatus.setFont(F_COND);
        lblStatus.setForeground(COR_TEXTO_SUB);
        lblStatus.setBorder(new EmptyBorder(6, 0, 0, 0));
        topo.add(lblStatus, BorderLayout.SOUTH);

        // Área central rolável com os cartões de resultado
        painelResultado = new JPanel();
        painelResultado.setLayout(new BoxLayout(painelResultado, BoxLayout.Y_AXIS));
        painelResultado.setOpaque(false);

        JScrollPane scroll = new JScrollPane(painelResultado);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.getVerticalScrollBar().setUnitIncrement(12);

        // Painel do histórico de buscas (rodapé)
        painelHistorico = new JPanel();
        painelHistorico.setLayout(new BoxLayout(painelHistorico, BoxLayout.Y_AXIS));
        painelHistorico.setOpaque(false);

        raiz.add(topo,            BorderLayout.NORTH);
        raiz.add(scroll,          BorderLayout.CENTER);
        raiz.add(painelHistorico, BorderLayout.SOUTH);

        setContentPane(raiz);
    }

    // COMPONENTES DO TOPO

    // Cria o painel de cabeçalho com um ícone e o título do aplicativo.
    private JPanel criarCabecalho() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 0));
        p.setOpaque(false);

        JLabel icone = new JLabel("🌐");
        icone.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));

        JLabel titulo = new JLabel("Previsão do Tempo");
        titulo.setFont(F_TITULO);
        titulo.setForeground(COR_TEXTO);

        p.add(icone);
        p.add(titulo);
        return p;
    }

    // Cria o painel de busca com um campo de texto para a cidade e um botão de busca estilizado.
    private JPanel criarBarraBusca() {
        JPanel painel = new JPanel(new BorderLayout(8, 0));
        painel.setOpaque(false);

        // Campo de texto com fundo translúcido e bordas arredondadas
        campoCidade = new JTextField("Cascavel, BR") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(255, 255, 255, 22));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        campoCidade.setFont(F_CAMPO);
        campoCidade.setForeground(COR_TEXTO);
        campoCidade.setCaretColor(COR_TEXTO);
        campoCidade.setOpaque(false);
        campoCidade.setBorder(new CompoundBorder(
            new BordaArredondada(12, COR_CARD_BORDA),
            new EmptyBorder(9, 12, 9, 12)
        ));

        // Apaga o texto padrão ao focar o campo pela primeira vez
        campoCidade.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (campoCidade.getText().equals("Cascavel, BR"))
                    campoCidade.setText("");
            }
        });

        // Botão de busca com hover animado
        btnBuscar = new JButton("🔍  Buscar") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getModel().isRollover() ? COR_BOTAO_HOVER : COR_BOTAO);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);
                super.paintComponent(g);
                g2.dispose();
            }
        };
        btnBuscar.setFont(F_BOTAO);
        btnBuscar.setForeground(COR_TEXTO);
        btnBuscar.setOpaque(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBuscar.setPreferredSize(new Dimension(115, 38));

        // Registra a ação de busca no botão e na tecla Enter do campo
        ActionListener acao = e -> iniciarBusca();
        btnBuscar.addActionListener(acao);
        campoCidade.addActionListener(acao);

        painel.add(campoCidade, BorderLayout.CENTER);
        painel.add(btnBuscar,   BorderLayout.EAST);
        return painel;
    }

    // LÓGICA DE BUSCA

    /* 
     * Inicia o processo de busca do clima para a cidade informada, atualizando a interface 
     * para mostrar o status e, posteriormente, os resultados ou erros.
     */
    private void iniciarBusca() {
        String cidade = campoCidade.getText().trim();
        
        if (cidade.isEmpty() || cidade.equals("Cascavel, BR")) {
            mostrarStatusErro("⚠  Digite o nome de uma cidade antes de buscar.");
            return;
        }

        // Desabilita controles e exibe loading
        btnBuscar.setEnabled(false);
        btnBuscar.setText("⏳  Buscando...");
        mostrarStatusInfo("Consultando dados para \"" + cidade + "\"...");
        painelResultado.removeAll();
        painelResultado.add(criarPainelLoading());
        painelResultado.revalidate();
        painelResultado.repaint();

        // Executa a chamada de rede fora da EDT para não travar a interface
        SwingWorker<WeatherData, Void> worker = new SwingWorker<>() {

            /*
             * Realiza a consulta à API e retorna os dados climáticos. Se ocorrer um erro, 
             * a exceção será capturada no método done().
             */
            @Override
            protected WeatherData doInBackground() throws Exception {
                return servico.buscarClima(cidade);
            }

            // Atualiza a interface com o resultado quando a thread termina.
            @Override
            protected void done() {
                btnBuscar.setEnabled(true);
                btnBuscar.setText("🔍  Buscar");

                try {
                    WeatherData dados = get();
                    mostrarStatusInfo("");
                    exibirResultado(dados);
                    adicionarAoHistorico(cidade);

                } catch (Exception ex) {
                    // Extrai a mensagem real da causa do ExecutionException
                    String msg = (ex.getCause() != null)
                        ? ex.getCause().getMessage()
                        : ex.getMessage();
                    mostrarStatusErro("Erro: " + msg);
                    painelResultado.removeAll();
                    painelResultado.add(criarPainelErro(msg));
                    painelResultado.revalidate();
                    painelResultado.repaint();
                }
            }
        };

        worker.execute();
    }
    
    // EXIBIÇÃO DOS RESULTADOS

    /* 
     * Limpa a área de resultados e constrói os componentes para exibir os 
     * dados climáticos de forma organizada e visualmente atraente.
     */
    private void exibirResultado(WeatherData d) {
        painelResultado.removeAll();
        painelResultado.setBorder(new EmptyBorder(14, 0, 0, 0));

        painelResultado.add(criarCardPrincipal(d));
        painelResultado.add(espaco(10));
        painelResultado.add(criarGridInfos(d));
        painelResultado.add(espaco(10));
        painelResultado.add(criarBarrasClassificacao(d));
        painelResultado.add(espaco(10));
        painelResultado.add(criarRodape());

        painelResultado.revalidate();
        painelResultado.repaint();
    }

    // Cartão principal

    /*
     * Cria o cartão principal que exibe a temperatura atual, 
     * condição climática e nome da cidade de forma destacada.
     */
    private JPanel criarCardPrincipal(WeatherData d) {
        JPanel card = new CardPanel();
        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(18, 18, 18, 18));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 240));

        // Linha superior: ícone do clima + temperatura atual
        JPanel linhaTemp = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
        linhaTemp.setOpaque(false);

        JLabel lblIcone = new JLabel(resolverIcone(d.getCondicao(), d.getPrecipitacao()));
        lblIcone.setFont(F_ICONE_G);

        JLabel lblTemp = new JLabel(String.format("%.0f°C", d.getTemperaturaAtual()));
        lblTemp.setFont(F_TEMP);
        lblTemp.setForeground(COR_TEXTO);

        linhaTemp.add(lblIcone);
        linhaTemp.add(lblTemp);

        // Linha do meio: nome da cidade
        JLabel lblCidade = new JLabel(d.getCidade(), SwingConstants.CENTER);
        lblCidade.setFont(F_CIDADE);
        lblCidade.setForeground(COR_TEXTO);
        lblCidade.setBorder(new EmptyBorder(4, 0, 2, 0));

        // Linha inferior: condição + hora da consulta
        String horaAtual = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        JLabel lblCond = new JLabel(
            d.getCondicao() + "  ·  Consultado às " + horaAtual,
            SwingConstants.CENTER
        );
        lblCond.setFont(F_COND);
        lblCond.setForeground(COR_TEXTO_SUB);

        // Separador fino entre cidade e condição
        JSeparator sep = new JSeparator();
        sep.setForeground(COR_SEPARADOR);
        sep.setBackground(COR_SEPARADOR);

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setOpaque(false);

        // Centraliza cada componente no eixo X
        for (JComponent comp : new JComponent[]{linhaTemp, lblCidade, sep, lblCond}) {
            comp.setAlignmentX(Component.CENTER_ALIGNMENT);
            if (comp instanceof JLabel) ((JLabel) comp).setHorizontalAlignment(SwingConstants.CENTER);
            centro.add(comp);
        }

        card.add(centro, BorderLayout.CENTER);
        return card;
    }

    // Grade de informações 

    /*
     * Cria uma grade 2x2 de cartões informativos que mostram detalhes 
     *como temperatura máxima/mínima, humidade, precipitação e vento.
     */
    private JPanel criarGridInfos(WeatherData d) {
        JPanel grid = new JPanel(new GridLayout(2, 2, 10, 10));
        grid.setOpaque(false);
        grid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        // Cartão de temperatura máxima e mínima do dia
        grid.add(criarCardInfo(
            "🌡", "MÁX / MÍN DO DIA",
            String.format("%.0f°  /  %.0f°", d.getTemperaturaMaxima(), d.getTemperaturaMinima()),
            d.getClassificacaoTermica()
        ));

        // Cartão de humidade do ar
        grid.add(criarCardInfo(
            "💧", "HUMIDADE DO AR",
            String.format("%.0f%%", d.getHumidade()),
            d.getClassificacaoHumidade()
        ));

        // Cartão de precipitação (volume de chuva)
        String valorPrecip = d.getPrecipitacao() > 0
            ? String.format("%.1f mm", d.getPrecipitacao())
            : "Sem chuva";
        grid.add(criarCardInfo(
            "🌧", "PRECIPITAÇÃO",
            valorPrecip,
            d.getPrecipitacao() > 0 ? "Chuva registrada" : "Céu sem precipitação"
        ));

        // Cartão de vento com velocidade e direção
        grid.add(criarCardInfo(
            "💨", "VENTO",
            String.format("%.0f km/h", d.getVelocidadeVento()),
            d.getDirecaoVentoCardinal() + "  ·  " + d.getClassificacaoVento()
        ));

        return grid;
    }

    // Método auxiliar para criar um cartão de informação individual com ícone, rótulo, valor e classificação.
    private JPanel criarCardInfo(String icone, String rotulo, String valor, String classificacao) {
        JPanel card = new CardPanel();
        card.setLayout(new BorderLayout(0, 6));
        card.setBorder(new EmptyBorder(14, 14, 14, 14));

        // Topo: ícone à esquerda
        JLabel lblIcone = new JLabel(icone);
        lblIcone.setFont(F_ICONE);

        // Rótulo em letras pequenas e maiúsculas
        JLabel lblRotulo = new JLabel(rotulo);
        lblRotulo.setFont(F_LABEL);
        lblRotulo.setForeground(COR_TEXTO_SUB);

        // Valor principal em destaque
        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(F_VALOR);
        lblValor.setForeground(COR_TEXTO);

        // Linha de classificação secundária
        JLabel lblClass = new JLabel(classificacao);
        lblClass.setFont(F_CLASS);
        lblClass.setForeground(COR_TEXTO_SUB);

        JPanel textos = new JPanel();
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        textos.setOpaque(false);
        textos.add(lblRotulo);
        textos.add(Box.createVerticalStrut(3));
        textos.add(lblValor);
        textos.add(Box.createVerticalStrut(2));
        textos.add(lblClass);

        card.add(lblIcone, BorderLayout.NORTH);
        card.add(textos,   BorderLayout.CENTER);
        return card;
    }

    // Barras de classificação 

    /*
     * Cria o painel com três barras de progresso visuais que representam
     * temperatura, humidade e vento de forma gráfica e colorida.
     */
    private JPanel criarBarrasClassificacao(WeatherData d) {
        JPanel card = new CardPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(new EmptyBorder(14, 16, 14, 16));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 130));

        JLabel titulo = new JLabel("📊  Índices do momento");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 12));
        titulo.setForeground(COR_TEXTO_SUB);
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        card.add(titulo);
        card.add(espaco(10));

        // Temperatura: 0°C = 0%, 40°C = 100%
        double pctTemp = Math.min(100, Math.max(0, (d.getTemperaturaAtual() / 40.0) * 100));
        card.add(criarLinhaBarraProg("🌡  Temperatura", pctTemp, corBarra(pctTemp)));
        card.add(espaco(6));

        // Humidade: já vem em %
        double pctHum = Math.min(100, Math.max(0, d.getHumidade()));
        card.add(criarLinhaBarraProg("💧  Humidade", pctHum, COR_BARRA_FRIA));
        card.add(espaco(6));

        // Vento: 0–80 km/h mapeado para 0–100%
        double pctVento = Math.min(100, Math.max(0, (d.getVelocidadeVento() / 80.0) * 100));
        card.add(criarLinhaBarraProg("💨  Vento", pctVento, COR_BARRA_MED));

        return card;
    }

    // Método auxiliar para criar uma linha com rótulo e barra de progresso personalizada.
    private JPanel criarLinhaBarraProg(String rotulo, double pct, Color cor) {
        JPanel linha = new JPanel(new BorderLayout(8, 0));
        linha.setOpaque(false);
        linha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 22));
        linha.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lbl = new JLabel(rotulo + String.format("  %.0f%%", pct));
        lbl.setFont(F_CLASS);
        lbl.setForeground(COR_TEXTO_SUB);
        lbl.setPreferredSize(new Dimension(165, 16));

        // Barra de progresso personalizada com fundo translúcido
        JPanel barra = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);

                // Trilho de fundo
                g2.setColor(new Color(255, 255, 255, 25));
                g2.fillRoundRect(0, 4, getWidth(), 8, 8, 8);

                // Preenchimento proporcional ao valor
                int largura = (int) (getWidth() * pct / 100.0);
                g2.setColor(cor);
                if (largura > 0)
                    g2.fillRoundRect(0, 4, largura, 8, 8, 8);
                g2.dispose();
            }
        };
        barra.setOpaque(false);

        linha.add(lbl,   BorderLayout.WEST);
        linha.add(barra, BorderLayout.CENTER);
        return linha;
    }

    // Rodapé

    /*
     * Cria o rodapé com um aviso discreto sobre a fonte dos dados climáticos,
     *  estilizado para se integrar ao design geral.
     */
    private JPanel criarRodape() {
        JPanel rod = new JPanel(new FlowLayout(FlowLayout.CENTER, 4, 0));
        rod.setOpaque(false);

        JLabel lbl = new JLabel("Dados fornecidos por Visual Crossing Weather API");
        lbl.setFont(F_HORA);
        lbl.setForeground(new Color(140, 170, 215));
        rod.add(lbl);
        return rod;
    }

    // Painéis de estado

    // Painel de loading com ícone e mensagem centralizados, exibido enquanto os dados estão sendo buscados.
    private JPanel criarPainelLoading() {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.setBorder(new EmptyBorder(40, 0, 0, 0));
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setOpaque(false);

        JLabel icone = new JLabel("⏳");
        icone.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        icone.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel msg = new JLabel("Buscando dados do clima...");
        msg.setFont(F_COND);
        msg.setForeground(COR_TEXTO_SUB);
        msg.setAlignmentX(Component.CENTER_ALIGNMENT);

        centro.add(icone);
        centro.add(espaco(10));
        centro.add(msg);
        p.add(centro, BorderLayout.CENTER);
        return p;
    }

    /*
     * Cria o painel de erro com destaque visual em vermelho translúcido,
     * exibindo a mensagem completa da exceção capturada.
     */
    private JPanel criarPainelErro(String mensagem) {
        JPanel card = new JPanel(new BorderLayout(0, 8)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(COR_ERRO_BG);
                g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 14, 14));
                g2.setColor(COR_ERRO_BORDA);
                g2.setStroke(new BasicStroke(1.2f));
                g2.draw(new RoundRectangle2D.Float(0.5f, 0.5f,
                    getWidth() - 1, getHeight() - 1, 14, 14));
                g2.dispose();
            }
        };
        card.setOpaque(false);
        card.setBorder(new EmptyBorder(16, 16, 16, 16));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        JLabel icone = new JLabel("⚠");
        icone.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));

        JLabel msg = new JLabel("<html>" + mensagem.replace("\n", "<br>") + "</html>");
        msg.setFont(F_CLASS);
        msg.setForeground(new Color(255, 180, 180));

        card.add(icone, BorderLayout.WEST);
        card.add(msg,   BorderLayout.CENTER);

        JPanel wrapper = new JPanel();
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));
        wrapper.setOpaque(false);
        wrapper.setBorder(new EmptyBorder(14, 0, 0, 0));
        wrapper.add(card);
        return wrapper;
    }

    // HISTÓRICO DE BUSCAS
    
    /*
     * Adiciona a cidade ao topo do histórico (máximo 5 itens)
     * e redesenha o painel de histórico no rodapé.
     */
    private void adicionarAoHistorico(String cidade) {
        // Evita duplicatas consecutivas
        if (!historico.isEmpty() && historico.get(0).equalsIgnoreCase(cidade)) return;

        historico.remove(cidade); // remove se já existia em outra posição
        historico.add(0, cidade); // insere no topo
        if (historico.size() > 5)
            historico.remove(historico.size() - 1);

        atualizarPainelHistorico();
    }

    // Atualiza o painel de histórico, criando um item clicável para cada cidade buscada recentemente.
    private void atualizarPainelHistorico() {
        painelHistorico.removeAll();

        if (historico.isEmpty()) {
            painelHistorico.revalidate();
            painelHistorico.repaint();
            return;
        }

        // Separador visual antes do histórico
        JSeparator sep = new JSeparator();
        sep.setForeground(COR_SEPARADOR);
        sep.setBackground(COR_SEPARADOR);
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        sep.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel titulo = new JLabel("  🕓  Buscas recentes");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 11));
        titulo.setForeground(COR_TEXTO_SUB);
        titulo.setBorder(new EmptyBorder(8, 0, 4, 0));

        painelHistorico.add(sep);
        painelHistorico.add(titulo);

        // Cria um botão clicável para cada cidade do histórico
        for (String cidade : historico) {
            painelHistorico.add(criarItemHistorico(cidade));
        }

        painelHistorico.revalidate();
        painelHistorico.repaint();
    }

    /*
     * Cria um item clicável do histórico que, ao ser clicado,
     * preenche o campo e aciona uma nova busca para aquela cidade.
     */
    private JPanel criarItemHistorico(String cidade) {
        JPanel item = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 3)) {
            private boolean hover = false;

            {
                setOpaque(false);
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                addMouseListener(new MouseAdapter() {
                    @Override public void mouseEntered(MouseEvent e) { hover = true;  repaint(); }
                    @Override public void mouseExited (MouseEvent e) { hover = false; repaint(); }
                    @Override public void mouseClicked(MouseEvent e) {
                        // Preenche o campo e aciona a busca ao clicar
                        campoCidade.setText(cidade);
                        iniciarBusca();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                if (hover) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(COR_HIST_HOVER);
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };

        JLabel icone = new JLabel("📍");
        icone.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 11));

        JLabel lbl = new JLabel(cidade);
        lbl.setFont(F_HIST);
        lbl.setForeground(COR_TEXTO_SUB);

        item.add(icone);
        item.add(lbl);
        item.setMaximumSize(new Dimension(Integer.MAX_VALUE, 28));
        return item;
    }

    // HELPERS DE STATUS

    // Exibe uma mensagem de status informativa (cor azulada) na barra de status.
    private void mostrarStatusInfo(String msg) {
        lblStatus.setText(msg);
        lblStatus.setForeground(COR_TEXTO_SUB);
    }

    // Exibe uma mensagem de erro (cor avermelhada) na barra de status.
    private void mostrarStatusErro(String msg) {
        lblStatus.setText(msg);
        lblStatus.setForeground(new Color(255, 110, 110));
    }

    // HELPERS VISUAIS
    
    /*
     * Seleciona o emoji mais adequado para representar as condições
     * climáticas descritas no campo "conditions" da API.
     */
    private String resolverIcone(String condicao, double precip) {
        if (condicao == null || condicao.isEmpty()) return "🌡";
        String c = condicao.toLowerCase();

        if (precip > 8 || c.contains("thunder") || c.contains("trovoada")) return "⛈";
        if (c.contains("snow")    || c.contains("neve"))                    return "❄️";
        if (c.contains("rain")    || c.contains("chuva") || precip > 0)     return "🌧";
        if (c.contains("drizzle") || c.contains("garoa"))                   return "🌦";
        if (c.contains("fog")     || c.contains("mist")  ||
            c.contains("neblina") || c.contains("névoa"))                   return "🌫";
        if (c.contains("overcast")|| c.contains("nublado"))                 return "☁️";
        if (c.contains("cloud")   || c.contains("parcial")||
            c.contains("partial"))                                          return "⛅";
        if (c.contains("clear")   || c.contains("limpo") ||
            c.contains("sunny")   || c.contains("sol"))                     return "☀️";
        return "🌤";
    }

    /*
     * Escolhe a cor da barra de progresso de temperatura:
     * azul para frio, verde para moderado e laranja para quente.
     */
    private Color corBarra(double pct) {
        if (pct < 35) return COR_BARRA_FRIA;
        if (pct < 65) return COR_BARRA_MED;
        return COR_BARRA_QUENTE;
    }

    // Método auxiliar para criar um espaço vertical fixo entre componentes.
    private Component espaco(int altura) {
        return Box.createRigidArea(new Dimension(0, altura));
    }

    // Cria um painel personalizado que pinta um gradiente vertical como fundo da interface.
    private JPanel criarPainelGradiente() {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                                    RenderingHints.VALUE_RENDER_QUALITY);
                g2.setPaint(new GradientPaint(
                    0, 0,          COR_FUNDO_TOP,
                    0, getHeight(), COR_FUNDO_BOT
                ));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
    }

    /*
     * Gera o ícone 32×32 do aplicativo exibido na barra de tarefas
     * do sistema operacional, usando desenho 2D em tempo de execução.
     */
    private Image gerarIconeApp() {
        int sz = 32;
        BufferedImage img = new BufferedImage(sz, sz, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Fundo circular azul escuro
        g2.setColor(COR_FUNDO_TOP);
        g2.fillOval(0, 0, sz, sz);
        // Emoji centralizado
        g2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        g2.setColor(Color.WHITE);
        FontMetrics fm = g2.getFontMetrics();
        String s = "🌤";
        g2.drawString(s, (sz - fm.stringWidth(s)) / 2, (sz + fm.getAscent()) / 2 - 2);
        g2.dispose();
        return img;
    }

    // CLASSES INTERNAS
    
    /*
     * Painel com fundo semi-transparente e cantos arredondados,
     * usado como base visual para todos os cartões do aplicativo.
     */
    private static class CardPanel extends JPanel {
        CardPanel() { setOpaque(false); }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(COR_CARD);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 16, 16));
            g2.setColor(COR_CARD_BORDA);
            g2.setStroke(new BasicStroke(1f));
            g2.draw(new RoundRectangle2D.Float(0.5f, 0.5f,
                getWidth() - 1f, getHeight() - 1f, 16, 16));
            g2.dispose();
        }
    }

    /*
     * Borda com cantos arredondados e cor personalizada,
     * usada no campo de texto de busca da cidade.
     */
    private static class BordaArredondada extends AbstractBorder {
        private final int raio;
        private final Color cor;

        BordaArredondada(int raio, Color cor) {
            this.raio = raio;
            this.cor  = cor;
        }

        // Desenha a borda arredondada ao redor do componente.
        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int w, int h) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(cor);
            g2.setStroke(new BasicStroke(1.2f));
            g2.drawRoundRect(x, y, w - 1, h - 1, raio, raio);
            g2.dispose();
        }

        // Define o espaço interno mínimo reservado pela borda.
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(4, 4, 4, 4);
        }
    }
}
