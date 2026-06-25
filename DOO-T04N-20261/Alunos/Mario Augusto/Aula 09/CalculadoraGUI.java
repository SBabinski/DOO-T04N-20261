import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Interface gráfica da Calculadora utilizando Java Swing.
 * Contém campos de entrada, botões de operação e área de resultado.
 */
public class CalculadoraGUI extends JFrame {

    // ── Componentes de entrada ──────────────────────────────────────────────
    private JTextField campoNumero1;
    private JTextField campoNumero2;

    // ── Área de resultado ───────────────────────────────────────────────────
    private JTextArea areaResultado;

    // ── Lógica da calculadora ───────────────────────────────────────────────
    private final CalculadoraLogica logica = new CalculadoraLogica();

    // ── Paleta de cores ─────────────────────────────────────────────────────
    private static final Color COR_FUNDO        = new Color(18, 18, 28);
    private static final Color COR_PAINEL       = new Color(28, 28, 42);
    private static final Color COR_CAMPO        = new Color(38, 38, 56);
    private static final Color COR_TEXTO        = new Color(220, 220, 240);
    private static final Color COR_PLACEHOLDER  = new Color(100, 100, 130);
    private static final Color COR_SOMA         = new Color(72, 199, 142);
    private static final Color COR_SUB          = new Color(255, 112, 112);
    private static final Color COR_MULT         = new Color(100, 181, 246);
    private static final Color COR_DIV          = new Color(255, 193, 7);
    private static final Color COR_LIMPAR       = new Color(90, 90, 115);
    private static final Color COR_RESULTADO_OK = new Color(72, 199, 142);
    private static final Color COR_RESULTADO_ERR= new Color(255, 112, 112);

    // ── Construtor ──────────────────────────────────────────────────────────
    public CalculadoraGUI() {
        super("Calculadora");
        configurarJanela();
        construirInterface();
        setVisible(true);
    }

    // ── Configuração da janela ──────────────────────────────────────────────
    private void configurarJanela() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 540);
        setMinimumSize(new Dimension(380, 500));
        setLocationRelativeTo(null);
        setResizable(true);
        getContentPane().setBackground(COR_FUNDO);
    }

    // ── Construção da interface ─────────────────────────────────────────────
    private void construirInterface() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(0, 16));
        painelPrincipal.setBackground(COR_FUNDO);
        painelPrincipal.setBorder(new EmptyBorder(24, 24, 24, 24));

        // Título
        JLabel titulo = new JLabel("CALCULADORA", SwingConstants.CENTER);
        titulo.setFont(new Font("Monospaced", Font.BOLD, 22));
        titulo.setForeground(COR_TEXTO);
        titulo.setBorder(new EmptyBorder(0, 0, 8, 0));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        // Centro: campos + botões
        JPanel painelCentro = new JPanel(new BorderLayout(0, 14));
        painelCentro.setBackground(COR_FUNDO);

        painelCentro.add(criarPainelCampos(), BorderLayout.NORTH);
        painelCentro.add(criarPainelBotoes(), BorderLayout.CENTER);

        painelPrincipal.add(painelCentro, BorderLayout.CENTER);

        // Área de resultado
        painelPrincipal.add(criarPainelResultado(), BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    // ── Painel de campos de entrada ─────────────────────────────────────────
    private JPanel criarPainelCampos() {
        JPanel painel = new JPanel(new GridLayout(2, 1, 0, 10));
        painel.setBackground(COR_FUNDO);

        campoNumero1 = criarCampoTexto("Primeiro número");
        campoNumero2 = criarCampoTexto("Segundo número");

        painel.add(criarLabeledField("Número 1", campoNumero1));
        painel.add(criarLabeledField("Número 2", campoNumero2));

        return painel;
    }

    private JTextField criarCampoTexto(String placeholder) {
        JTextField campo = new JTextField();
        campo.setBackground(COR_CAMPO);
        campo.setForeground(COR_PLACEHOLDER);
        campo.setCaretColor(COR_TEXTO);
        campo.setFont(new Font("Monospaced", Font.PLAIN, 16));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 60, 85), 1),
            new EmptyBorder(8, 12, 8, 12)
        ));
        campo.setText(placeholder);

        // Comportamento de placeholder
        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (campo.getText().equals(placeholder)) {
                    campo.setText("");
                    campo.setForeground(COR_TEXTO);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (campo.getText().isEmpty()) {
                    campo.setText(placeholder);
                    campo.setForeground(COR_PLACEHOLDER);
                }
            }
        });

        // Enter aciona divisão (último botão "padrão")
        campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    executarOperacao("/");
                }
            }
        });

        return campo;
    }

    private JPanel criarLabeledField(String rotulo, JTextField campo) {
        JPanel painel = new JPanel(new BorderLayout(0, 4));
        painel.setBackground(COR_FUNDO);

        JLabel label = new JLabel(rotulo);
        label.setFont(new Font("Monospaced", Font.BOLD, 11));
        label.setForeground(COR_PLACEHOLDER);
        painel.add(label, BorderLayout.NORTH);
        painel.add(campo, BorderLayout.CENTER);

        return painel;
    }

    // ── Painel de botões ────────────────────────────────────────────────────
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new GridLayout(2, 2, 10, 10));
        painel.setBackground(COR_FUNDO);
        painel.setBorder(new EmptyBorder(4, 0, 4, 0));

        painel.add(criarBotao("+  Somar",       "+", COR_SOMA));
        painel.add(criarBotao("−  Subtrair",    "-", COR_SUB));
        painel.add(criarBotao("×  Multiplicar", "*", COR_MULT));
        painel.add(criarBotao("÷  Dividir",     "/", COR_DIV));

        // Botão limpar (linha extra)
        JPanel painelComLimpar = new JPanel(new BorderLayout(0, 10));
        painelComLimpar.setBackground(COR_FUNDO);
        painelComLimpar.add(painel, BorderLayout.CENTER);

        JButton btnLimpar = criarBotao("⌫  Limpar", "LIMPAR", COR_LIMPAR);
        painelComLimpar.add(btnLimpar, BorderLayout.SOUTH);

        return painelComLimpar;
    }

    private JButton criarBotao(String texto, String operacao, Color cor) {
        JButton botao = new JButton(texto);
        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);
        botao.setFont(new Font("Monospaced", Font.BOLD, 14));
        botao.setFocusPainted(false);
        botao.setBorderPainted(false);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botao.setBorder(new EmptyBorder(12, 8, 12, 8));

        // Hover effect
        Color corOriginal = cor;
        Color corHover = cor.brighter();
        botao.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseEntered(java.awt.event.MouseEvent e) {
                botao.setBackground(corHover);
            }
            @Override public void mouseExited(java.awt.event.MouseEvent e) {
                botao.setBackground(corOriginal);
            }
        });

        botao.addActionListener((ActionEvent e) -> {
            if ("LIMPAR".equals(operacao)) {
                limpar();
            } else {
                executarOperacao(operacao);
            }
        });

        return botao;
    }

    // ── Painel de resultado ─────────────────────────────────────────────────
    private JPanel criarPainelResultado() {
        JPanel painel = new JPanel(new BorderLayout(0, 6));
        painel.setBackground(COR_FUNDO);

        JLabel labelResultado = new JLabel("Resultado");
        labelResultado.setFont(new Font("Monospaced", Font.BOLD, 11));
        labelResultado.setForeground(COR_PLACEHOLDER);
        painel.add(labelResultado, BorderLayout.NORTH);

        areaResultado = new JTextArea(3, 1);
        areaResultado.setEditable(false);
        areaResultado.setLineWrap(true);
        areaResultado.setWrapStyleWord(true);
        areaResultado.setBackground(COR_PAINEL);
        areaResultado.setForeground(COR_RESULTADO_OK);
        areaResultado.setFont(new Font("Monospaced", Font.BOLD, 15));
        areaResultado.setBorder(new EmptyBorder(10, 14, 10, 14));
        areaResultado.setText("Aguardando operação...");

        JScrollPane scroll = new JScrollPane(areaResultado);
        scroll.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 85), 1));
        painel.add(scroll, BorderLayout.CENTER);

        return painel;
    }

    // ── Lógica de execução ──────────────────────────────────────────────────
    private void executarOperacao(String operacao) {
        try {
            String textoN1 = obterTextoReal(campoNumero1, "Primeiro número");
            String textoN2 = obterTextoReal(campoNumero2, "Segundo número");

            double n1 = logica.parseNumero(textoN1, "Número 1");
            double n2 = logica.parseNumero(textoN2, "Número 2");

            double resultado;
            String simbolo;

            switch (operacao) {
                case "+" -> { resultado = logica.somar(n1, n2);        simbolo = "+"; }
                case "-" -> { resultado = logica.subtrair(n1, n2);     simbolo = "−"; }
                case "*" -> { resultado = logica.multiplicar(n1, n2);  simbolo = "×"; }
                case "/" -> { resultado = logica.dividir(n1, n2);      simbolo = "÷"; }
                default  -> throw new CalculadoraException(
                    "Operação desconhecida: " + operacao,
                    CalculadoraException.TipoErro.ENTRADA_INVALIDA
                );
            }

            String textoResultado = logica.formatarResultado(n1)
                + " " + simbolo + " "
                + logica.formatarResultado(n2)
                + " = "
                + logica.formatarResultado(resultado);

            exibirResultado(textoResultado, true);

        } catch (CalculadoraException ex) {
            // Trata exceção personalizada com mensagem amigável
            String icone = switch (ex.getTipoErro()) {
                case DIVISAO_POR_ZERO  -> "⚠ Divisão por zero: ";
                case ENTRADA_INVALIDA  -> "✗ Entrada inválida: ";
                case CAMPO_VAZIO       -> "○ Campo vazio: ";
                case OVERFLOW          -> "∞ Overflow: ";
            };
            exibirResultado(icone + ex.getMessage(), false);

        } catch (Exception ex) {
            // Captura qualquer exceção inesperada
            exibirResultado("Erro inesperado: " + ex.getMessage(), false);
        }
    }

    /**
     * Retorna o conteúdo real do campo, ignorando o texto de placeholder.
     */
    private String obterTextoReal(JTextField campo, String placeholder) {
        String texto = campo.getText();
        if (texto.equals("Primeiro número") || texto.equals("Segundo número")) {
            return "";
        }
        return texto;
    }

    private void exibirResultado(String mensagem, boolean sucesso) {
        areaResultado.setForeground(sucesso ? COR_RESULTADO_OK : COR_RESULTADO_ERR);
        areaResultado.setText(mensagem);
    }

    private void limpar() {
        campoNumero1.setText("Primeiro número");
        campoNumero1.setForeground(COR_PLACEHOLDER);
        campoNumero2.setText("Segundo número");
        campoNumero2.setForeground(COR_PLACEHOLDER);
        areaResultado.setForeground(COR_RESULTADO_OK);
        areaResultado.setText("Aguardando operação...");
    }
}
