package objects;

import javax.swing.*;
import java.awt.*;

public class CalculadoraSwing extends JFrame {

    private JTextField campoNumero1;
    private JTextField campoNumero2;
    private JLabel labelResultado;

    public CalculadoraSwing() {
        setTitle("Calculadora - Aula 09");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1, 5, 5));

        JLabel titulo = new JLabel("Calculadora com Java Swing - Aula 09", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        campoNumero1 = new JTextField();
        campoNumero2 = new JTextField();

        JPanel painelBotoes = new JPanel(new GridLayout(1, 4, 5, 5));

        JButton botaoSomar = new JButton("+");
        JButton botaoSubtrair = new JButton("-");
        JButton botaoMultiplicar = new JButton("x");
        JButton botaoDividir = new JButton("/");

        painelBotoes.add(botaoSomar);
        painelBotoes.add(botaoSubtrair);
        painelBotoes.add(botaoMultiplicar);
        painelBotoes.add(botaoDividir);

        labelResultado = new JLabel("Resultado da operacao:", SwingConstants.CENTER);
        labelResultado.setFont(new Font("Montserrat", Font.BOLD, 16));

        add(titulo);
        add(criarPainelComLabel("Numero 1:", campoNumero1));
        add(criarPainelComLabel("Numero 2:", campoNumero2));
        add(painelBotoes);
        add(labelResultado);

        botaoSomar.addActionListener(e -> calcular("+"));
        botaoSubtrair.addActionListener(e -> calcular("-"));
        botaoMultiplicar.addActionListener(e -> calcular("*"));
        botaoDividir.addActionListener(e -> calcular("/"));
    }

    private JPanel criarPainelComLabel(String texto, JTextField campo) {
        JPanel painel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(texto);

        painel.add(label, BorderLayout.WEST);
        painel.add(campo, BorderLayout.CENTER);

        return painel;
    }

    private double lerNumero(String texto) throws CalculadoraException {
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new CalculadoraException("Entrada invalida. Digite apenas numeros.");
        }
    }

    private void calcular(String operacao) {
        try {
            double numero1 = lerNumero(campoNumero1.getText());
            double numero2 = lerNumero(campoNumero2.getText());

            double resultado = 0;

            switch (operacao) {
                case "+":
                    resultado = Operacoes.somar(numero1, numero2);
                    break;
                case "-":
                    resultado = Operacoes.subtrair(numero1, numero2);
                    break;
                case "*":
                    resultado = Operacoes.multiplicar(numero1, numero2);
                    break;
                case "/":
                    resultado = Operacoes.dividir(numero1, numero2);
                    break;
                default:
                    throw new CalculadoraException("Operacao invalida.");
            }

            labelResultado.setText("Resultado: " + resultado);
        } catch (CalculadoraException e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}