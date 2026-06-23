package aula09;

import javax.swing.*;
import java.awt.*;

public class Principal extends JFrame {

    private JLabel display;
    private String entradaAtual = "";
    private String operador = "";
    private double primeiroNumero = 0;
    private boolean novaEntrada = false;

    private CalculadoraLogica logica = new CalculadoraLogica();

    public Principal() {
        setTitle("Calculadora Aula 09");
        setSize(300, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        criarDisplay();
        criarBotoes();
    }

    private void criarDisplay() {
        display = new JLabel("0", SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 36));
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);
        display.setOpaque(true);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);
    }

    private void criarBotoes() {
    	 JPanel painelGeral = new JPanel();
    	    painelGeral.setLayout(new BorderLayout(5, 5));

    	    // botão C largo em cima
    	    JButton botaoC = new JButton("C");
    	    botaoC.setFont(new Font("Arial", Font.BOLD, 20));
    	    botaoC.setBackground(new Color(180, 0, 0));
    	    botaoC.setForeground(Color.WHITE);
    	    botaoC.addActionListener(e -> processarClique("C"));
    	    painelGeral.add(botaoC, BorderLayout.NORTH);

    	    // grade 4x4
    	    JPanel painel = new JPanel();
    	    painel.setLayout(new GridLayout(4, 4, 5, 5));

    	    String[] labels = {
    	        "7", "8", "9", "÷",
    	        "4", "5", "6", "×",
    	        "1", "2", "3", "-",
    	        "0", ".", "=", "+"
    	    };

    	    for (String label : labels) {
    	        JButton botao = new JButton(label);
    	        botao.setFont(new Font("Arial", Font.BOLD, 20));

    	        if (label.matches("[+\\-×÷=]")) {
    	            botao.setBackground(new Color(255, 149, 0));
    	            botao.setForeground(Color.WHITE);
    	        } else if (label.equals(".")) {
    	            botao.setBackground(new Color(0, 255, 0));
    	            botao.setForeground(Color.WHITE);
    	        }

    	        botao.addActionListener(e -> processarClique(label));
    	        painel.add(botao);
    	    }

    	    painelGeral.add(painel, BorderLayout.CENTER);
    	    add(painelGeral, BorderLayout.CENTER);
    	}

    private void processarClique(String label) {
        switch (label) {
            case "C":
                entradaAtual = "";
                operador = "";
                primeiroNumero = 0;
                novaEntrada = false;
                display.setText("0");
                break;

            case "+": case "-": case "×": case "÷":
                try {
                    primeiroNumero = logica.parseNumero(entradaAtual);
                    operador = label;
                    novaEntrada = true;
                } catch (CalculadoraException ex) {
                    mostrarErro(ex);
                }
                break;

            case "=":
                try {
                    double segundoNumero = logica.parseNumero(entradaAtual);
                    double resultado = logica.calcular(primeiroNumero, segundoNumero, operador);

                    // formata: se for inteiro, mostra sem casas decimais
                    if (resultado == (long) resultado) {
                        display.setText(String.valueOf((long) resultado));
                    } else {
                        display.setText(String.valueOf(resultado));
                    }

                    entradaAtual = display.getText();
                    operador = "";
                    novaEntrada = false;
                } catch (CalculadoraException ex) {
                    mostrarErro(ex);
                }
                break;

            default: // números
                if (novaEntrada) {
                    entradaAtual = "";
                    novaEntrada = false;
                }
                entradaAtual += label;
                display.setText(entradaAtual);
                break;
        }
    }

    private void mostrarErro(CalculadoraException ex) {
        entradaAtual = "";
        novaEntrada = false;
        display.setText("Erro");
        JOptionPane.showMessageDialog(
            this,
            ex.getMessage(),
            "Erro: " + ex.getTipoErro(),
            JOptionPane.ERROR_MESSAGE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Principal().setVisible(true);
        });
    }
}