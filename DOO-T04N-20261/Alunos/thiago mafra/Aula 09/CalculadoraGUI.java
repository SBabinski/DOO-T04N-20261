package Aula09;

import javax.swing.*;
import java.awt.*;

public class CalculadoraGUI extends JFrame {

    private JTextField campo1;
    private JTextField campo2;
    private JTextField campoResultado;

    public CalculadoraGUI() {
        setTitle("Calculadora Java Swing");
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("Número 1:"));
        campo1 = new JTextField();
        add(campo1);

        add(new JLabel("Número 2:"));
        campo2 = new JTextField();
        add(campo2);

        JButton btnSoma = new JButton("+");
        JButton btnSub = new JButton("-");
        JButton btnMult = new JButton("×");
        JButton btnDiv = new JButton("÷");

        add(btnSoma);
        add(btnSub);
        add(btnMult);
        add(btnDiv);

        add(new JLabel("Resultado:"));
        campoResultado = new JTextField();
        campoResultado.setEditable(false);
        add(campoResultado);

        // Eventos
        btnSoma.addActionListener(e -> calcular("+"));
        btnSub.addActionListener(e -> calcular("-"));
        btnMult.addActionListener(e -> calcular("*"));
        btnDiv.addActionListener(e -> calcular("/"));
    }

    private void calcular(String op) {
        try {
            double n1 = Operacoes.converter(campo1.getText());
            double n2 = Operacoes.converter(campo2.getText());
            double res = 0;

            switch (op) {
                case "+":
                    res = Operacoes.somar(n1, n2);
                    break;
                case "-":
                    res = Operacoes.subtrair(n1, n2);
                    break;
                case "*":
                    res = Operacoes.multiplicar(n1, n2);
                    break;
                case "/":
                    res = Operacoes.dividir(n1, n2);
                    break;
            }

            campoResultado.setText(String.valueOf(res));

        } catch (CalculadoraException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            campoResultado.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculadoraGUI().setVisible(true);
        });
    }
}