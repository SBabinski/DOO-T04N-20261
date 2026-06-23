package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalculadoraView extends JFrame {

    private final JPanel painel;
    private final JLabel display;
    private final JTextField primeiroNumero;
    private final JTextField segundoNumero;

    private final JButton somaBotao;
    private final JButton subtracaoBotao;
    private final JButton multiplicacaoBotao;
    private final JButton divisaoBotao;

    public CalculadoraView() {
        super("Calculadora");

        setSize(450, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        // Painel principal
        painel = new JPanel();
        painel.setBackground(new Color(245, 245, 245));
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.setLayout(new BorderLayout(10, 10));

        // Display
        display = new JLabel("Resultado: 0");
        display.setFont(new Font("Arial", Font.BOLD, 18));
        display.setHorizontalAlignment(SwingConstants.CENTER);
        display.setOpaque(true);
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Campos de entrada
        JPanel camposPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        camposPanel.setBackground(new Color(245, 245, 245));

        primeiroNumero = new JTextField();
        segundoNumero = new JTextField();

        primeiroNumero.setFont(new Font("Arial", Font.PLAIN, 16));
        segundoNumero.setFont(new Font("Arial", Font.PLAIN, 16));

        primeiroNumero.setHorizontalAlignment(SwingConstants.CENTER);
        segundoNumero.setHorizontalAlignment(SwingConstants.CENTER);

        primeiroNumero.setBorder(
                BorderFactory.createTitledBorder("Primeiro Número")
        );

        segundoNumero.setBorder(
                BorderFactory.createTitledBorder("Segundo Número")
        );

        camposPanel.add(primeiroNumero);
        camposPanel.add(segundoNumero);

        // Painel dos botões
        JPanel botoesPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        botoesPanel.setBackground(new Color(245, 245, 245));

        somaBotao = criarBotao("+");
        subtracaoBotao = criarBotao("-");
        multiplicacaoBotao = criarBotao("*");
        divisaoBotao = criarBotao("/");

        botoesPanel.add(somaBotao);
        botoesPanel.add(subtracaoBotao);
        botoesPanel.add(multiplicacaoBotao);
        botoesPanel.add(divisaoBotao);

        // Adicionando componentes
        painel.add(display, BorderLayout.NORTH);
        painel.add(camposPanel, BorderLayout.CENTER);
        painel.add(botoesPanel, BorderLayout.SOUTH);

        add(painel);

        setVisible(true);
    }

    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);

        botao.setFont(new Font("Arial", Font.BOLD, 16));
        botao.setFocusPainted(false);
        botao.setBackground(new Color(70, 130, 180));
        botao.setForeground(Color.WHITE);
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return botao;
    }

    // Getters

    public JTextField getPrimeiroNumero() {
        return primeiroNumero;
    }

    public JTextField getSegundoNumero() {
        return segundoNumero;
    }

    public JLabel getDisplay() {
        return display;
    }

    public JButton getSomaBotao() {
        return somaBotao;
    }

    public JButton getSubtracaoBotao() {
        return subtracaoBotao;
    }

    public JButton getMultiplicacaoBotao() {
        return multiplicacaoBotao;
    }

    public JButton getDivisaoBotao() {
        return divisaoBotao;
    }
}