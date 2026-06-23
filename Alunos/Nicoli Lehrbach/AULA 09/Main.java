package org.example;

import org.example.entidades.CalcException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Calculadora");
        frame.setSize(250, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Color fundo = new Color(255, 240, 245);
        Color rosa = new Color(255, 182, 193);
        Color rosaEscuro = new Color(219, 112, 147);
        Color branco = Color.WHITE;

        JPanel painelPrincipal = new JPanel(new BorderLayout(15, 15));
        painelPrincipal.setBackground(fundo);
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));

        JTextField primeiroNumero = criarCampo();
        JTextField segundoNumero = criarCampo();

        JLabel display = new JLabel("Digite os números ✨", SwingConstants.CENTER);
        display.setFont(new Font("Segoe UI", Font.BOLD, 18));
        display.setForeground(rosaEscuro);

        JLabel label1 = criarLabel("Número 1:");
        JLabel label2 = criarLabel("Número 2:");

        JPanel painelCampos = new JPanel(new GridLayout(2, 2, 15, 15));
        painelCampos.setBackground(fundo);

        painelCampos.add(label1);
        painelCampos.add(primeiroNumero);

        painelCampos.add(label2);
        painelCampos.add(segundoNumero);

        JButton somaBotao = criarBotao("+", rosa);
        JButton subtracaoBotao = criarBotao("-", rosa);
        JButton multiplicacaoBotao = criarBotao("×", rosa);
        JButton divisaoBotao = criarBotao("÷", rosa);

        JPanel painelBotoes = new JPanel(new GridLayout(2, 2, 15, 15));
        painelBotoes.setBackground(fundo);

        painelBotoes.add(somaBotao);
        painelBotoes.add(subtracaoBotao);
        painelBotoes.add(multiplicacaoBotao);
        painelBotoes.add(divisaoBotao);

        JPanel centro = new JPanel(new BorderLayout(15, 15));
        centro.setBackground(fundo);

        centro.add(painelCampos, BorderLayout.NORTH);
        centro.add(painelBotoes, BorderLayout.CENTER);
        centro.add(display, BorderLayout.SOUTH);

        painelPrincipal.add(centro, BorderLayout.CENTER);

        somaBotao.addActionListener(e ->
                calcular(primeiroNumero, segundoNumero, display, "+"));

        subtracaoBotao.addActionListener(e ->
                calcular(primeiroNumero, segundoNumero, display, "-"));

        multiplicacaoBotao.addActionListener(e ->
                calcular(primeiroNumero, segundoNumero, display, "*"));

        divisaoBotao.addActionListener(e ->
                calcular(primeiroNumero, segundoNumero, display, "/"));

        frame.add(painelPrincipal);
        frame.setVisible(true);
    }

    public static JButton criarBotao(String texto, Color cor) {

        JButton botao = new JButton(texto);

        botao.setFont(new Font("Segoe UI", Font.BOLD, 22));
        botao.setFocusPainted(false);

        botao.setBackground(cor);
        botao.setForeground(Color.WHITE);

        botao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        return botao;
    }

    public static JTextField criarCampo() {

        JTextField campo = new JTextField();

        campo.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        campo.setBackground(Color.WHITE);

        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 182, 193), 2),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));

        return campo;
    }

    public static JLabel criarLabel(String texto) {

        JLabel label = new JLabel(texto);

        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        label.setForeground(new Color(199, 21, 133));

        return label;
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
                        throw new CalcException("Não é possível dividir por zero.");
                    }

                    resultado = numero1 / numero2;
                    break;

                default:
                    throw new CalcException("Operação inválida.");
            }

            display.setText("Resultado: " + resultado + " ✨");

        } catch (CalcException e) {

            display.setText("Erro: " + e.getMessage());
        }
    }

    public static double lerNumero(String texto) throws CalcException {

        try {

            if (texto.trim().isEmpty()) {
                throw new CalcException("Preencha os dois campos.");
            }

            return Double.parseDouble(texto);

        } catch (NumberFormatException e) {

            throw new CalcException("Digite apenas números válidos.");
        }
    }
}