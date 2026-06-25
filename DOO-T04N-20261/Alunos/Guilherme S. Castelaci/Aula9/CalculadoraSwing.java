import javax.swing.*;
import java.awt.*;

class CalculadoraException extends Exception {

    public CalculadoraException(String mensagem) {
        super(mensagem);
    }
}

public class CalculadoraSwing extends JFrame {

    private JTextField campo1;
    private JTextField campo2;
    private JTextField resultado;

    public CalculadoraSwing() {

        setTitle("Calculadora Swing");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel label1 = new JLabel("Primeiro número:");
        campo1 = new JTextField();

        JLabel label2 = new JLabel("Segundo número:");
        campo2 = new JTextField();

        JLabel labelResultado = new JLabel("Resultado:");
        resultado = new JTextField();
        resultado.setEditable(false);

        JButton botaoSoma = new JButton("+");
        JButton botaoSub = new JButton("-");
        JButton botaoMult = new JButton("×");
        JButton botaoDiv = new JButton("÷");

        painel.add(label1);
        painel.add(campo1);

        painel.add(label2);
        painel.add(campo2);

        painel.add(botaoSoma);
        painel.add(botaoSub);

        painel.add(botaoMult);
        painel.add(botaoDiv);

        painel.add(labelResultado);
        painel.add(resultado);

        botaoSoma.addActionListener(e -> calcular("+"));
        botaoSub.addActionListener(e -> calcular("-"));
        botaoMult.addActionListener(e -> calcular("*"));
        botaoDiv.addActionListener(e -> calcular("/"));

        add(painel);
    }

    private void calcular(String operacao) {

        try {

            double num1;
            double num2;
            double res = 0;

            try {

                num1 = Double.parseDouble(campo1.getText());
                num2 = Double.parseDouble(campo2.getText());

            } catch (NumberFormatException e) {

                throw new CalculadoraException("Digite apenas números válidos.");
            }

            switch (operacao) {

                case "+":
                    res = num1 + num2;
                    break;

                case "-":
                    res = num1 - num2;
                    break;

                case "*":
                    res = num1 * num2;
                    break;

                case "/":

                    if (num2 == 0) {
                        throw new CalculadoraException("Não é permitido dividir por zero.");
                    }

                    res = num1 / num2;
                    break;
            }

            resultado.setText(String.valueOf(res));

        } catch (CalculadoraException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new CalculadoraSwing().setVisible(true);
        });
    }
}