package calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Principal extends JFrame implements ActionListener {

    JTextField display;

    double numero1;
    String operacao;

    public Calculadora() {

        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 30));

        add(display, BorderLayout.NORTH);

        JPanel painel = new JPanel();

        painel.setLayout(new GridLayout(4, 4));

        String[] botoes = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "=", "+", "C"
        };

        for (String texto : botoes) {

            JButton botao = new JButton(texto);

            botao.setFont(new Font("Arial", Font.BOLD, 20));

            botao.addActionListener(this);

            painel.add(botao);
        }

        add(painel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();

        try {

            if (comando.matches("[0-9]")) {

                display.setText(display.getText() + comando);
            }

            else if (comando.matches("[+\\-*/]")) {

                numero1 = Double.parseDouble(display.getText());

                operacao = comando;

                display.setText("");
            }

            else if (comando.equals("=")) {

                double numero2 =
                        Double.parseDouble(display.getText());

                double resultado = 0;

                switch (operacao) {

                    case "+":
                        resultado = numero1 + numero2;
                        break;

                    case "-":
                        resultado = numero1 - numero2;
                        break;

                    case "*":
                        resultado = numero1 * numero2;
                        break;

                    case "/":

                        if (numero2 == 0) {

                            throw new Message(
                                    "Divisão por zero!"
                            );
                        }

                        resultado = numero1 / numero2;
                        break;
                }

                display.setText("" + resultado);
            }

            else if (comando.equals("C")) {

                display.setText("");
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    null,
                    "Digite apenas números!"
            );

        } catch (Message ex) {

            JOptionPane.showMessageDialog(
                    null,
                    ex.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        new Calculadora();
    }
}