package classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame {

    private JTextField campo1;
    private JTextField campo2;
    private JTextField resultado;

    private JButton botaoSoma;
    private JButton botaoSubtracao;
    private JButton botaoMultiplicacao;
    private JButton botaoDivisao;

    private Operacoes operacoes;

    public CalculadoraGUI() {

        operacoes = new Operacoes();

        setTitle("Calculadora Java Swing");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();

        painel.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel label1 = new JLabel("Primeiro Número:");
        campo1 = new JTextField();

        JLabel label2 = new JLabel("Segundo Número:");
        campo2 = new JTextField();

        JLabel labelResultado = new JLabel("Resultado:");
        resultado = new JTextField();
        resultado.setEditable(false);

        botaoSoma = new JButton("+");
        botaoSubtracao = new JButton("-");
        botaoMultiplicacao = new JButton("X");
        botaoDivisao = new JButton("/");

        painel.add(label1);
        painel.add(campo1);

        painel.add(label2);
        painel.add(campo2);

        painel.add(botaoSoma);
        painel.add(botaoSubtracao);

        painel.add(botaoMultiplicacao);
        painel.add(botaoDivisao);

        painel.add(labelResultado);
        painel.add(resultado);

        add(painel);

        botaoSoma.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                calcular("+");
            }
        });

        botaoSubtracao.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                calcular("-");
            }
        });

        botaoMultiplicacao.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                calcular("*");
            }
        });

        botaoDivisao.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                calcular("/");
            }
        });
    }

    public void calcular(String operacao) {

        try {

            double numero1 = Double.parseDouble(campo1.getText());
            double numero2 = Double.parseDouble(campo2.getText());

            double valorResultado = 0;

            if (operacao.equals("+")) {

                valorResultado = operacoes.somar(numero1, numero2);

            } else if (operacao.equals("-")) {

                valorResultado = operacoes.subtrair(numero1, numero2);

            } else if (operacao.equals("*")) {

                valorResultado = operacoes.multiplicar(numero1, numero2);

            } else if (operacao.equals("/")) {

                valorResultado = operacoes.dividir(numero1, numero2);
            }

            resultado.setText(String.valueOf(valorResultado));

        } catch (NumberFormatException erro) {

            JOptionPane.showMessageDialog(
                    null,
                    "Digite apenas números.",
                    "Erro de Entrada",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (ErroCalculadora erro) {

            JOptionPane.showMessageDialog(
                    null,
                    erro.getMessage(),
                    "Erro da Calculadora",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {

        CalculadoraGUI tela = new CalculadoraGUI();

        tela.setVisible(true);
    }
}