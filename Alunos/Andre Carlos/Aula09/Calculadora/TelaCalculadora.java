import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaCalculadora extends JFrame implements ActionListener {

    JTextField campo1;
    JTextField campo2;

    JButton botaoSoma;
    JButton botaoSub;
    JButton botaoMult;
    JButton botaoDiv;

    JLabel resultado;

    public TelaCalculadora() {

        setTitle("Calculadora");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(6, 2));

        // Campos
        add(new JLabel("Primeiro número:"));

        campo1 = new JTextField();
        add(campo1);

        add(new JLabel("Segundo número:"));

        campo2 = new JTextField();
        add(campo2);

        // Botões
        botaoSoma = new JButton("+");
        botaoSub = new JButton("-");
        botaoMult = new JButton("*");
        botaoDiv = new JButton("/");

        add(botaoSoma);
        add(botaoSub);
        add(botaoMult);
        add(botaoDiv);

        // Evento dos botões
        botaoSoma.addActionListener(this);
        botaoSub.addActionListener(this);
        botaoMult.addActionListener(this);
        botaoDiv.addActionListener(this);

        // Resultado
        resultado = new JLabel("Resultado:");

        add(resultado);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            double num1 = Double.parseDouble(campo1.getText());
            double num2 = Double.parseDouble(campo2.getText());

            double res = 0;

            // Soma
            if (e.getSource() == botaoSoma) {
                res = num1 + num2;
            }

            // Subtração
            if (e.getSource() == botaoSub) {
                res = num1 - num2;
            }

            // Multiplicação
            if (e.getSource() == botaoMult) {
                res = num1 * num2;
            }

            // Divisão
            if (e.getSource() == botaoDiv) {

                if (num2 == 0) {
                    throw new CalculadoraException(
                            "Não existe divisão por zero."
                    );
                }

                res = num1 / num2;
            }

            resultado.setText("Resultado: " + res);

        } catch (NumberFormatException erro) {

            JOptionPane.showMessageDialog(
                    null,
                    "Digite apenas números."
            );

        } catch (CalculadoraException erro) {

            JOptionPane.showMessageDialog(
                    null,
                    erro.getMessage()
            );
        }
    }
}