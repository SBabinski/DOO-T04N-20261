import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Interface gráfica da Calculadora — Java Swing.
 *
 * Layout estilo calculadora física:
 *   - Display duplo (número atual e histórico da operação)
 *   - Botões numéricos 0–9, ponto decimal, +/−
 *   - Botões de operação +  −  ×  ÷  =
 *   - Botão C (limpar tudo) e ⌫ (apagar último dígito)
 *
 * Tratamento de exceções:
 *   - try-catch em calcular() captura CalculadoraException
 *   - Cada TipoErro exibe mensagem amigável no display
 */
public class CalculadoraGUI extends JFrame {

    // ── Estado interno ────────────────────────────────────────────────────────
    private String numeroAtual   = "0";   // dígitos sendo digitados
    private String numeroAnterior = "";   // primeiro operando
    private String operacaoPendente = ""; // operação aguardando segundo operando
    private boolean novaEntrada = true;   // true = próximo dígito substitui display

    // ── Componentes ───────────────────────────────────────────────────────────
    private JLabel displayHistorico;  // linha pequena acima (ex: "12 ×")
    private JLabel displayPrincipal; // número atual em destaque

    // ── Lógica ───────────────────────────────────────────────────────────────
    private final CalculadoraLogica logica = new CalculadoraLogica();

    // ── Paleta ────────────────────────────────────────────────────────────────
    private static final Color C_FUNDO       = new Color(15, 15, 25);
    private static final Color C_DISPLAY     = new Color(10, 10, 18);
    private static final Color C_BTN_NUM     = new Color(32, 32, 52);
    private static final Color C_BTN_NUM_H   = new Color(48, 48, 75);
    private static final Color C_BTN_OP      = new Color(55, 60, 110);
    private static final Color C_BTN_OP_H    = new Color(80, 90, 160);
    private static final Color C_BTN_IGUAL   = new Color(70, 110, 230);
    private static final Color C_BTN_IGUAL_H = new Color(95, 140, 255);
    private static final Color C_BTN_CLEAR   = new Color(180, 50, 60);
    private static final Color C_BTN_CLEAR_H = new Color(210, 70, 80);
    private static final Color C_TEXTO       = new Color(230, 230, 255);
    private static final Color C_HIST        = new Color(120, 120, 170);
    private static final Color C_ERRO        = new Color(255, 90, 90);
    private static final Color C_ACENTO      = new Color(100, 120, 255);

    // ── Construtor ────────────────────────────────────────────────────────────
    public CalculadoraGUI() {
        configurarJanela();
        construirInterface();
        pack();
        setLocationRelativeTo(null);
    }

    // ── Janela ────────────────────────────────────────────────────────────────
    private void configurarJanela() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(C_FUNDO);
    }

    // ── Interface ─────────────────────────────────────────────────────────────
    private void construirInterface() {
        JPanel raiz = new JPanel(new BorderLayout(0, 0));
        raiz.setBackground(C_FUNDO);
        raiz.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        raiz.add(criarDisplay(), BorderLayout.NORTH);
        raiz.add(criarTeclado(), BorderLayout.CENTER);

        add(raiz);
    }

    // ── Display ───────────────────────────────────────────────────────────────
    private JPanel criarDisplay() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBackground(C_DISPLAY);
        painel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(C_ACENTO.darker(), 1, true),
            BorderFactory.createEmptyBorder(12, 16, 12, 16)
        ));

        // Linha de histórico (operação anterior)
        displayHistorico = new JLabel(" ");
        displayHistorico.setFont(new Font("Monospaced", Font.PLAIN, 13));
        displayHistorico.setForeground(C_HIST);
        displayHistorico.setHorizontalAlignment(SwingConstants.RIGHT);
        displayHistorico.setPreferredSize(new Dimension(0, 20));

        // Display principal
        displayPrincipal = new JLabel("0");
        displayPrincipal.setFont(new Font("Monospaced", Font.BOLD, 38));
        displayPrincipal.setForeground(C_TEXTO);
        displayPrincipal.setHorizontalAlignment(SwingConstants.RIGHT);
        displayPrincipal.setPreferredSize(new Dimension(320, 52));

        painel.add(displayHistorico, BorderLayout.NORTH);
        painel.add(displayPrincipal, BorderLayout.CENTER);

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setBackground(C_FUNDO);
        wrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 12, 0));
        wrapper.add(painel);
        return wrapper;
    }

    // ── Teclado numérico + operações ──────────────────────────────────────────
    private JPanel criarTeclado() {
        /*
         * Grade 5×4:
         *  [ C ]  [ ⌫ ]  [ +/− ]  [ ÷ ]
         *  [ 7 ]  [ 8  ]  [ 9  ]  [ × ]
         *  [ 4 ]  [ 5  ]  [ 6  ]  [ − ]
         *  [ 1 ]  [ 2  ]  [ 3  ]  [ + ]
         *  [ 0 (largo) ]  [ .  ]  [ = ]
         */
        JPanel grade = new JPanel(new GridLayout(5, 4, 8, 8));
        grade.setBackground(C_FUNDO);

        // Linha 1 — utilitários + divisão
        grade.add(criarBotaoLimpar("C"));
        grade.add(criarBotaoUtil("⌫"));
        grade.add(criarBotaoUtil("+/−"));
        grade.add(criarBotaoOp("÷"));

        // Linha 2
        grade.add(criarBotaoNum("7"));
        grade.add(criarBotaoNum("8"));
        grade.add(criarBotaoNum("9"));
        grade.add(criarBotaoOp("×"));

        // Linha 3
        grade.add(criarBotaoNum("4"));
        grade.add(criarBotaoNum("5"));
        grade.add(criarBotaoNum("6"));
        grade.add(criarBotaoOp("−"));

        // Linha 4
        grade.add(criarBotaoNum("1"));
        grade.add(criarBotaoNum("2"));
        grade.add(criarBotaoNum("3"));
        grade.add(criarBotaoOp("+"));

        // Linha 5 — zero largo, ponto, igual
        // Para o zero "largo" usamos um painel interno com GridLayout 1x4
        // e colocamos um JPanel vazio no lugar do 2º slot
        JPanel zero = new JPanel(new GridLayout(1, 1));
        zero.setBackground(C_FUNDO);
        JButton btnZero = fabricarBotao("0", C_BTN_NUM, C_BTN_NUM_H, C_TEXTO, 16);
        btnZero.addActionListener(e -> pressionarDigito("0"));
        zero.add(btnZero);

        // Substituímos os 2 primeiros slots da linha 5 por um painel 2-colunas
        JPanel linhaCinco = new JPanel(new GridLayout(1, 3, 8, 0));
        linhaCinco.setBackground(C_FUNDO);

        // Zero ocupa 2 colunas — workaround: painel com GridLayout 1x1 e preferredSize
        JPanel zeroWrapper = new JPanel(new BorderLayout());
        zeroWrapper.setBackground(C_FUNDO);
        zeroWrapper.add(btnZero);

        linhaCinco.add(zeroWrapper);
        linhaCinco.add(criarBotaoNum("."));
        linhaCinco.add(criarBotaoIgual());

        // A grade 5×4 tem 4 slots na linha 5.
        // Vamos adicionar o painel da linha cinco abrangendo 4 colunas
        // usando um truque: remontamos como GridLayout externo.
        // Mais simples: usar BorderLayout no painel raiz do teclado.

        // Reconstrução: painel principal do teclado com layout vertical
        JPanel teclado = new JPanel(new BorderLayout(0, 8));
        teclado.setBackground(C_FUNDO);

        // Linhas 1-4 numa grade 4×4
        JPanel grade4x4 = new JPanel(new GridLayout(4, 4, 8, 8));
        grade4x4.setBackground(C_FUNDO);

        grade4x4.add(criarBotaoLimpar("C"));
        grade4x4.add(criarBotaoUtil("⌫"));
        grade4x4.add(criarBotaoUtil("+/−"));
        grade4x4.add(criarBotaoOp("÷"));

        grade4x4.add(criarBotaoNum("7"));
        grade4x4.add(criarBotaoNum("8"));
        grade4x4.add(criarBotaoNum("9"));
        grade4x4.add(criarBotaoOp("×"));

        grade4x4.add(criarBotaoNum("4"));
        grade4x4.add(criarBotaoNum("5"));
        grade4x4.add(criarBotaoNum("6"));
        grade4x4.add(criarBotaoOp("−"));

        grade4x4.add(criarBotaoNum("1"));
        grade4x4.add(criarBotaoNum("2"));
        grade4x4.add(criarBotaoNum("3"));
        grade4x4.add(criarBotaoOp("+"));

        // Linha 5: [  0  ][  0  ][ . ][ = ]  → GridLayout 1x4
        JPanel linha5 = new JPanel(new GridLayout(1, 4, 8, 0));
        linha5.setBackground(C_FUNDO);

        JButton btn0a = fabricarBotao("0", C_BTN_NUM, C_BTN_NUM_H, C_TEXTO, 16);
        btn0a.addActionListener(e -> pressionarDigito("0"));
        JButton btn0b = fabricarBotao("", C_BTN_NUM, C_BTN_NUM_H, C_TEXTO, 16); // fantasma invisível
        btn0b.setEnabled(false);
        btn0b.setBackground(C_BTN_NUM);
        btn0b.setBorder(BorderFactory.createEmptyBorder());
        btn0b.setVisible(false); // esconde

        // Melhor abordagem: só 3 elementos, zero com colspan via GridBagLayout
        linha5.removeAll();
        linha5.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.insets = new java.awt.Insets(0, 0, 0, 4);

        // Zero — peso 2 (ocupa 2 colunas)
        gbc.gridx = 0; gbc.weightx = 2.0;
        JButton btnZeroFinal = fabricarBotao("0", C_BTN_NUM, C_BTN_NUM_H, C_TEXTO, 16);
        btnZeroFinal.addActionListener(e -> pressionarDigito("0"));
        linha5.add(btnZeroFinal, gbc);

        // Ponto — peso 1
        gbc.gridx = 1; gbc.weightx = 1.0;
        gbc.insets = new java.awt.Insets(0, 0, 0, 4);
        JButton btnPonto = fabricarBotao(".", C_BTN_NUM, C_BTN_NUM_H, C_TEXTO, 16);
        btnPonto.addActionListener(e -> pressionarPonto());
        linha5.add(btnPonto, gbc);

        // Igual — peso 1
        gbc.gridx = 2; gbc.weightx = 1.0;
        gbc.insets = new java.awt.Insets(0, 0, 0, 0);
        JButton btnIgual = fabricarBotao("=", C_BTN_IGUAL, C_BTN_IGUAL_H, Color.WHITE, 18);
        btnIgual.addActionListener(e -> pressionarIgual());
        linha5.add(btnIgual, gbc);

        linha5.setPreferredSize(new Dimension(0, 56));

        teclado.add(grade4x4, BorderLayout.CENTER);
        teclado.add(linha5, BorderLayout.SOUTH);

        return teclado;
    }

    // ── Fábrica de botões ─────────────────────────────────────────────────────
    private JButton fabricarBotao(String label, Color bg, Color hover, Color fg, int fontSize) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setBorderPainted(true);
        btn.setBorder(new LineBorder(bg.brighter(), 1, true));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(72, 52));
        btn.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { btn.setBackground(hover); }
            @Override public void mouseExited(MouseEvent e)  { btn.setBackground(bg);    }
        });
        return btn;
    }

    private JButton criarBotaoNum(String digito) {
        JButton btn = fabricarBotao(digito, C_BTN_NUM, C_BTN_NUM_H, C_TEXTO, 16);
        btn.addActionListener(e -> pressionarDigito(digito));
        return btn;
    }

    private JButton criarBotaoOp(String op) {
        // Converte símbolo de exibição para símbolo interno
        String opInterno = op.equals("−") ? "-" : op;
        JButton btn = fabricarBotao(op, C_BTN_OP, C_BTN_OP_H, C_TEXTO, 18);
        btn.addActionListener(e -> pressionarOperacao(opInterno));
        return btn;
    }

    private JButton criarBotaoIgual() {
        JButton btn = fabricarBotao("=", C_BTN_IGUAL, C_BTN_IGUAL_H, Color.WHITE, 18);
        btn.addActionListener(e -> pressionarIgual());
        return btn;
    }

    private JButton criarBotaoLimpar(String label) {
        JButton btn = fabricarBotao(label, C_BTN_CLEAR, C_BTN_CLEAR_H, Color.WHITE, 15);
        btn.addActionListener(e -> limparTudo());
        return btn;
    }

    private JButton criarBotaoUtil(String label) {
        JButton btn = fabricarBotao(label, C_BTN_NUM, C_BTN_NUM_H, new Color(200, 200, 255), 14);
        btn.addActionListener(e -> {
            if (label.equals("⌫"))   apagarUltimo();
            if (label.equals("+/−")) inverterSinal();
        });
        return btn;
    }

    // ── Lógica dos botões ─────────────────────────────────────────────────────

    /**
     * Acumula dígitos no número atual.
     * Se novaEntrada=true, começa do zero (após uma operação ou erro).
     */
    private void pressionarDigito(String digito) {
        if (novaEntrada) {
            numeroAtual = digito;
            novaEntrada = false;
        } else {
            // Limite de 15 dígitos para não explodir o display
            if (numeroAtual.replace("-", "").replace(".", "").length() >= 15) return;
            if (numeroAtual.equals("0") && !digito.equals(".")) {
                numeroAtual = digito;
            } else {
                numeroAtual += digito;
            }
        }
        atualizarDisplay();
    }

    /** Adiciona ponto decimal (apenas uma vez por número). */
    private void pressionarPonto() {
        if (novaEntrada) {
            numeroAtual = "0.";
            novaEntrada = false;
        } else if (!numeroAtual.contains(".")) {
            numeroAtual += ".";
        }
        atualizarDisplay();
    }

    /**
     * Armazena o operando atual, registra a operação e aguarda o segundo número.
     * Se já havia uma operação pendente, calcula o resultado parcial primeiro
     * (comportamento encadeado: 3 + 4 × → calcula 7 antes de registrar ×).
     */
    private void pressionarOperacao(String op) {
        // Se não estava em novaEntrada, pode haver operação encadeada
        if (!novaEntrada && !operacaoPendente.isEmpty()) {
            calcularResultado();  // calcula intermediário
        }
        numeroAnterior = numeroAtual;
        operacaoPendente = op;
        novaEntrada = true;

        // Exibe histórico: "12 ×"
        String opExibida = op.equals("-") ? "−" : op;
        displayHistorico.setText(numeroAnterior + " " + opExibida);
        displayHistorico.setForeground(C_HIST);
    }

    /**
     * Executa o cálculo com try-catch na CalculadoraException.
     */
    private void pressionarIgual() {
        if (operacaoPendente.isEmpty()) return;

        String v2 = numeroAtual;
        String opExibida = operacaoPendente.equals("-") ? "−" : operacaoPendente;
        displayHistorico.setText(numeroAnterior + " " + opExibida + " " + v2 + " =");
        displayHistorico.setForeground(C_HIST);

        calcularResultado();
        operacaoPendente = "";
    }

    private void calcularResultado() {
        try {
            String resultado = logica.calcular(numeroAnterior, numeroAtual, operacaoPendente);
            numeroAtual = resultado;
            novaEntrada = true;
            displayPrincipal.setForeground(C_TEXTO);
            displayPrincipal.setText(resultado);

        } catch (CalculadoraException ex) {
            tratarExcecao(ex);
        }
    }

    /**
     * Trata cada TipoErro com mensagem amigável no display.
     */
    private void tratarExcecao(CalculadoraException ex) {
        System.err.println("[ERRO] " + ex); // log para depuração no VS Code

        displayPrincipal.setForeground(C_ERRO);

        switch (ex.getTipoErro()) {
            case DIVISAO_POR_ZERO:
                displayPrincipal.setText("Div/Zero");
                displayHistorico.setText(ex.getMessage());
                break;
            case ENTRADA_INVALIDA:
                displayPrincipal.setText("Inválido");
                displayHistorico.setText(ex.getMessage());
                break;
            case CAMPO_VAZIO:
                displayPrincipal.setText("Vazio");
                displayHistorico.setText(ex.getMessage());
                break;
            case OVERFLOW_NUMERICO:
                displayPrincipal.setText("Overflow");
                displayHistorico.setText(ex.getMessage());
                break;
            default:
                displayPrincipal.setText("Erro");
                displayHistorico.setText(ex.getMessage());
        }

        displayHistorico.setForeground(C_ERRO);
        // Reseta estado para permitir novo cálculo
        numeroAtual = "0";
        numeroAnterior = "";
        operacaoPendente = "";
        novaEntrada = true;
    }

    /** Apaga o último dígito digitado. */
    private void apagarUltimo() {
        if (novaEntrada || numeroAtual.length() <= 1) {
            numeroAtual = "0";
        } else {
            numeroAtual = numeroAtual.substring(0, numeroAtual.length() - 1);
            if (numeroAtual.equals("-")) numeroAtual = "0";
        }
        displayPrincipal.setForeground(C_TEXTO);
        atualizarDisplay();
    }

    /** Inverte o sinal do número atual. */
    private void inverterSinal() {
        if (numeroAtual.equals("0")) return;
        if (numeroAtual.startsWith("-")) {
            numeroAtual = numeroAtual.substring(1);
        } else {
            numeroAtual = "-" + numeroAtual;
        }
        atualizarDisplay();
    }

    /** Limpa tudo e volta ao estado inicial. */
    private void limparTudo() {
        numeroAtual = "0";
        numeroAnterior = "";
        operacaoPendente = "";
        novaEntrada = true;
        displayPrincipal.setForeground(C_TEXTO);
        displayPrincipal.setText("0");
        displayHistorico.setText(" ");
        displayHistorico.setForeground(C_HIST);
    }

    private void atualizarDisplay() {
        displayPrincipal.setText(numeroAtual);
    }

    // ── Main ──────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Aviso: Look and Feel padrão em uso.");
            }
            new CalculadoraGUI().setVisible(true);
        });
    }
}