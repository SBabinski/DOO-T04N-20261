package org.example;

import org.example.entidades.CalcException;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora");
        frame.setSize(600, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JTextField primeiroNumero = new JTextField();
        JTextField segundoNumero = new JTextField();
        JLabel display = new JLabel("", SwingConstants.CENTER);

        JPanel painelCampos = new JPanel(new GridLayout(2, 2, 10, 10));
        painelCampos.add(new JLabel("Numero 1:"));
        painelCampos.add(primeiroNumero);
        painelCampos.add(new JLabel("Numero 2:"));
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
        botao.setFocusPainted(false);
        botao.setBackground(new Color(100, 192, 203));
        botao.setForeground(Color.BLACK);
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
                        throw new CalcException("Não é possível dividir por zero.");
                    }
                    resultado = numero1 / numero2;
                    break;
                default:
                    throw new CalcException("Operação inválida.");
            }

            display.setText("Resultado: " + resultado);

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