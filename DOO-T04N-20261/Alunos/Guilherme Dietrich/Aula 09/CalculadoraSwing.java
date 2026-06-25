

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraSwing extends JFrame implements ActionListener {

    private JTextField campo1, campo2;
    private JLabel resultado;

    private JButton soma, subtracao, multiplicacao, divisao;

    public CalculadoraSwing() {

        setTitle("Calculadora Java Swing");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Campo 1
        add(new JLabel("Primeiro número:"));
        campo1 = new JTextField();
        add(campo1);

        // Campo 2
        add(new JLabel("Segundo número:"));
        campo2 = new JTextField();
        add(campo2);

        // Botões
        soma = new JButton("+");
        subtracao = new JButton("-");
        multiplicacao = new JButton("×");
        divisao = new JButton("÷");

        add(soma);
        add(subtracao);
        add(multiplicacao);
        add(divisao);

        // Resultado
        add(new JLabel("Resultado:"));
        resultado = new JLabel("");
        add(resultado);

        // Eventos
        soma.addActionListener(this);
        subtracao.addActionListener(this);
        multiplicacao.addActionListener(this);
        divisao.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            double num1 = validarNumero(campo1.getText());
            double num2 = validarNumero(campo2.getText());

            double res = 0;

            // Soma
            if (e.getSource() == soma) {

                res = num1 + num2;

            }

            // Subtração
            else if (e.getSource() == subtracao) {

                res = num1 - num2;

            }

            // Multiplicação
            else if (e.getSource() == multiplicacao) {

                res = num1 * num2;

            }

            // Divisão
            else if (e.getSource() == divisao) {

                if (num2 == 0) {

                    throw new CalculadoraException(
                            "Erro: divisão por zero não é permitida."
                    );

                }

                res = num1 / num2;

            }

            resultado.setText(String.valueOf(res));

        }

        // Exception personalizada
        catch (CalculadoraException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

        }

        // Outras exceptions
        catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro inesperado.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

        }
    }

    // Validação de número
    private double validarNumero(String texto)
            throws CalculadoraException {

        try {

            return Double.parseDouble(texto);

        }

        catch (NumberFormatException e) {

            throw new CalculadoraException(
                    "Entrada inválida: digite apenas números."
            );

        }

    }

}