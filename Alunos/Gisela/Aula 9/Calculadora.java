import javax.swing.*;
import java.awt.*;

public class Calculadora extends JFrame {
    private JTextField txtNum1, txtNum2;
    private JLabel lblResultado;

    public Calculadora() {
        super("Calculadora");

        //paletinha de cores
        Color violeta = new color(148, 0, 211);
          Color ciano = new Color(0, 255, 255);
        Color rosa = new Color(255, 182, 193);
        Color fundo = new Color(20, 20, 30);

        setLayout(new GridLayout(4, 2, 10, 10));
        getContentPane().setBackground(fundo);

        txtNum1 = new JTextField();
        txtNum2 = new JTextField();
        lblResultado = new JLabel("Resultado: ", SwingConstants.CENTER);
        lblResultado.setForeground(ciano);

        JButton btnSoma = criarBotao("+", violeta);
        JButton btnSub = criarBotao("-", rosa);
        JButton btnMult = criarBotao("×", ciano);
        JButton btnDiv = criarBotao("÷", violeta);

        add(new JLabel("Número 1:", SwingConstants.CENTER));
        add(txtNum1);
        add(new JLabel("Número 2:", SwingConstants.CENTER));
        add(txtNum2);
        add(btnSoma);
        add(btnSub);
        add(btnMult);
        add(btnDiv);
        add(lblResultado);

        btnSoma.addActionListener(e -> calcular("+"));
        btnSub.addActionListener(e -> calcular("-"));
        btnMult.addActionListener(e -> calcular("*"));
        btnDiv.addActionListener(e -> calcular("/"));

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton criarBotao(String texto, Color cor) {
        JButton botao = new JButton(texto);
        botao.setBackground(cor);
        botao.setForeground(Color.BLACK);
        botao.setFont(new Font("Consolas", Font.BOLD, 18));
        return botao;
    }

    private void calcular(String operacao) {
        try {
            double n1 = validarEntrada(txtNum1.getText());
            double n2 = validarEntrada(txtNum2.getText());
            double res = 0;

            switch (operacao) {
                case "+": res = n1 + n2; break;
                case "-": res = n1 - n2; break;
                case "*": res = n1 * n2; break;
                case "/":
                    if (n2 == 0) throw new EntradaInvalidaException("Divisão por zero não permitida!");
                    res = n1 / n2;
                    break;
            }
            lblResultado.setText("Resultado: " + res);
        } catch (EntradaInvalidaException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida! Digite apenas números.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double validarEntrada(String texto) throws EntradaInvalidaException {
        if(texto == null || texto.trim().isEmpty()) {
            throw new EntradaInvalidaException("Campo vazio! Informe um número.");
        }
        return Double.parseDouble(texto);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculadora());
    }
}