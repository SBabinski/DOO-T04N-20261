import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraUI extends JFrame {

    private JTextField campoPrimeiroNumero;
    private JTextField campoSegundoNumero;
    private JTextField campoResultado;
    private JLabel labelOperacaoSelecionada;
    private String operacaoAtual = "+";

    private Calculadora calculadora;

    public CalculadoraUI() {
        calculadora = new Calculadora();
        configurarJanela();
        construirInterface();
    }

    private void configurarJanela() {
        setTitle("Calculadora — Aula 09");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 380);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(45, 45, 48));
    }

    private void construirInterface() {
        setLayout(new BorderLayout(10, 10));

        add(criarPainelTitulo(), BorderLayout.NORTH);
        add(criarPainelEntradas(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);

        pack();
        setSize(420, 380);
    }

    private JPanel criarPainelTitulo() {
        JPanel painel = new JPanel();
        painel.setBackground(new Color(0, 122, 204));
        painel.setBorder(BorderFactory.createEmptyBorder(12, 10, 12, 10));

        JLabel titulo = new JLabel("Calculadora Java Swing");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setForeground(Color.WHITE);
        painel.add(titulo);
        return painel;
    }

    private JPanel criarPainelEntradas() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBackground(new Color(45, 45, 48));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 5, 6, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        painel.add(criarLabel("Primeiro número:"), gbc);

        campoPrimeiroNumero = criarCampoTexto();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        painel.add(campoPrimeiroNumero, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        painel.add(criarLabel("Segundo número:"), gbc);

        campoSegundoNumero = criarCampoTexto();
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        painel.add(campoSegundoNumero, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        painel.add(criarLabel("Operação:"), gbc);

        labelOperacaoSelecionada = new JLabel("  +  (Adição)");
        labelOperacaoSelecionada.setFont(new Font("Segoe UI", Font.BOLD, 13));
        labelOperacaoSelecionada.setForeground(new Color(78, 201, 176));
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        painel.add(labelOperacaoSelecionada, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        painel.add(criarLabel("Resultado:"), gbc);

        campoResultado = criarCampoTexto();
        campoResultado.setEditable(false);
        campoResultado.setBackground(new Color(30, 30, 30));
        campoResultado.setForeground(new Color(86, 225, 123));
        campoResultado.setFont(new Font("Consolas", Font.BOLD, 15));
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        painel.add(campoResultado, gbc);

        return painel;
    }

    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new BorderLayout(5, 5));
        painel.setBackground(new Color(45, 45, 48));
        painel.setBorder(BorderFactory.createEmptyBorder(5, 20, 15, 20));

        JPanel painelOperacoes = new JPanel(new GridLayout(1, 4, 8, 0));
        painelOperacoes.setBackground(new Color(45, 45, 48));

        String[] operacoes = { "+", "-", "×", "÷" };
        String[] nomes = { "Adição", "Subtração", "Multiplicação", "Divisão" };

        for (int i = 0; i < operacoes.length; i++) {
            final String op = operacoes[i];
            final String nome = nomes[i];

            JButton btn = criarBotaoOperacao(op);

            btn.addActionListener(e -> selecionarOperacao(op, nome));

            painelOperacoes.add(btn);
        }

        JButton btnCalcular = new JButton("▶  Calcular");
        btnCalcular.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCalcular.setBackground(new Color(0, 122, 204));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setFocusPainted(false);
        btnCalcular.setBorderPainted(false);
        btnCalcular.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCalcular.setPreferredSize(new Dimension(0, 40));

        btnCalcular.addActionListener(e -> realizarCalculo());

        JButton btnLimpar = new JButton("✕  Limpar");
        btnLimpar.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btnLimpar.setBackground(new Color(80, 80, 84));
        btnLimpar.setForeground(Color.WHITE);
        btnLimpar.setFocusPainted(false);
        btnLimpar.setBorderPainted(false);
        btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLimpar.setPreferredSize(new Dimension(0, 40));
        btnLimpar.addActionListener(e -> limparCampos());

        JPanel painelAcoes = new JPanel(new GridLayout(1, 2, 8, 0));
        painelAcoes.setBackground(new Color(45, 45, 48));
        painelAcoes.add(btnCalcular);
        painelAcoes.add(btnLimpar);

        painel.add(painelOperacoes, BorderLayout.NORTH);
        painel.add(Box.createVerticalStrut(8), BorderLayout.CENTER); // Espaçador
        painel.add(painelAcoes, BorderLayout.SOUTH);

        return painel;
    }

    private JLabel criarLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        label.setForeground(new Color(200, 200, 200));
        return label;
    }

    private JTextField criarCampoTexto() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Consolas", Font.PLAIN, 14));
        campo.setBackground(new Color(60, 60, 64));
        campo.setForeground(Color.WHITE);
        campo.setCaretColor(Color.WHITE);
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(80, 80, 90)),
                BorderFactory.createEmptyBorder(4, 8, 4, 8)));
        campo.setPreferredSize(new Dimension(0, 32));
        return campo;
    }

    private JButton criarBotaoOperacao(String simbolo) {
        JButton btn = new JButton(simbolo);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(new Color(63, 63, 70));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(0, 38));
        return btn;
    }

    private void selecionarOperacao(String operacao, String nome) {
        operacaoAtual = operacao;
        labelOperacaoSelecionada.setText("  " + operacao + "  (" + nome + ")");
    }

    private void realizarCalculo() {
        try {
            String textoNum1 = campoPrimeiroNumero.getText();
            String textoNum2 = campoSegundoNumero.getText();

            double num1 = calculadora.validarEntrada(textoNum1, "Primeiro número");
            double num2 = calculadora.validarEntrada(textoNum2, "Segundo número");

            double resultado = calculadora.calcular(num1, num2, operacaoAtual);

            campoResultado.setText(calculadora.formatarResultado(resultado));
            campoResultado.setForeground(new Color(86, 225, 123));

        } catch (CalculadoraException e) {

            campoResultado.setText("Erro!");
            campoResultado.setForeground(new Color(244, 71, 71));

            String titulo = switch (e.getTipoErro()) {
                case DIVISAO_POR_ZERO -> "Divisão por Zero";
                case ENTRADA_INVALIDA -> "Entrada Inválida";
                case CAMPO_VAZIO -> "Campo Vazio";
            };

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    titulo,
                    JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            campoResultado.setText("Erro!");
            JOptionPane.showMessageDialog(
                    this,
                    "Ocorreu um erro inesperado: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        campoPrimeiroNumero.setText("");
        campoSegundoNumero.setText("");
        campoResultado.setText("");
        campoResultado.setForeground(new Color(86, 225, 123));
        operacaoAtual = "+";
        labelOperacaoSelecionada.setText("  +  (Adição)");
        campoPrimeiroNumero.requestFocus(); // Coloca o cursor no 1º campo
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraUI app = new CalculadoraUI();
            app.setVisible(true); // Torna a janela visível
        });
    }
}
