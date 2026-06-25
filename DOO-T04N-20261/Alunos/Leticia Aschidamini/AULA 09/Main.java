import entidades.CalculadoraException;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Calculadora");
        frame.setSize(450, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Calculadora da Lê :)" , SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        JTextField primeiroNumero = new JTextField();
        JTextField segundoNumero = new JTextField();

        JLabel display = new JLabel("", SwingConstants.CENTER);
        display.setFont(new Font("Arial", Font.BOLD, 18));

        JPanel painelCampos = new JPanel(new GridLayout(2, 2, 10, 10));
        painelCampos.add(new JLabel("1º número:"));
        painelCampos.add(primeiroNumero);
        painelCampos.add(new JLabel("2º número:"));
        painelCampos.add(segundoNumero);

        JButton somaBotao = criarBotao("+");
        JButton subtracaoBotao = criarBotao("-");
        JButton multiplicacaoBotao = criarBotao("*");
        JButton divisaoBotao = criarBotao("/");

        JPanel painelBotoes = new JPanel(new GridLayout(2, 2, 10, 10));

        painelBotoes.add(somaBotao);
        painelBotoes.add(subtracaoBotao);
        painelBotoes.add(multiplicacaoBotao);
        painelBotoes.add(divisaoBotao);

        JPanel centro = new JPanel(new BorderLayout(10, 10));
        centro.add(painelCampos, BorderLayout.NORTH);
        centro.add(painelBotoes, BorderLayout.CENTER);
        centro.add(display, BorderLayout.SOUTH);

        painelPrincipal.add(titulo, BorderLayout.NORTH);
        painelPrincipal.add(centro, BorderLayout.CENTER);

        somaBotao.addActionListener(e -> calcular(primeiroNumero, segundoNumero, display, "+"));
        subtracaoBotao.addActionListener(e -> calcular(primeiroNumero, segundoNumero, display, "-"));
        multiplicacaoBotao.addActionListener(e -> calcular(primeiroNumero, segundoNumero, display, "*"));
        divisaoBotao.addActionListener(e -> calcular(primeiroNumero, segundoNumero, display, "/"));

        frame.add(painelPrincipal);
        frame.setVisible(true);
    }

    public static JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("Arial", Font.BOLD, 22));
        botao.setFocusPainted(false);
        botao.setBackground(new Color(255, 192, 203));
        botao.setForeground(Color.WHITE);
        return botao;
    }

    public static void calcular(
            JTextField primeiroNumero,
            JTextField segundoNumero,
            JLabel display,
            String operacao
    ) {
        try {
            double numero1 = lerNumero(primeiroNumero.getText());
            double numero2 = lerNumero(segundoNumero.getText());

            double resultado;

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
                        throw new CalculadoraException(''"Não é possível dividir por zero.");
                    }
                    resultado = numero1 / numero2;
                    break;
                default:
                    throw new CalculadoraException("Operação inválida.");
            }

            display.setText("Resultado: " + resultado);

        } catch (CalculadoraException e) {
            display.setText("Erro: " + e.getMessage());
        }
    }

    public static double lerNumero(String texto) throws CalculadoraException {
        try {
            if (texto.trim().isEmpty()) {
                throw new CalculadoraException("Preencha os dois campos.");
            }

            return Double.parseDouble(texto);

        } catch (NumberFormatException e) {
            throw new CalculadoraException("Digite apenas números válidos.");
        }
    }
}