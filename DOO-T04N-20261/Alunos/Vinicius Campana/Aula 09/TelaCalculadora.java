package Calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaCalculadora extends JFrame {

    private JTextField campoNumero1;
    private JTextField campoNumero2;

    private JLabel labelResultado;

    private JButton botaoSoma;
    private JButton botaoSubtracao;
    private JButton botaoMultiplicacao;
    private JButton botaoDivisao;
    private JButton botaoLimpar;

    public TelaCalculadora() {

        setTitle("Calculadora Java");
        setSize(450, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 247, 250));

        JLabel titulo = new JLabel("Calculadora Simples");
        titulo.setBounds(95, 20, 300, 40);
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setForeground(new Color(45, 52, 54));
        add(titulo);

        JLabel label1 = new JLabel("Primeiro Número");
        label1.setBounds(50, 90, 150, 25);
        label1.setFont(new Font("Arial", Font.PLAIN, 15));
        add(label1);

        campoNumero1 = new JTextField();
        campoNumero1.setBounds(50, 120, 330, 35);
        campoNumero1.setFont(new Font("Arial", Font.PLAIN, 16));
        add(campoNumero1);

        JLabel label2 = new JLabel("Segundo Número");
        label2.setBounds(50, 170, 150, 25);
        label2.setFont(new Font("Arial", Font.PLAIN, 15));
        add(label2);

        campoNumero2 = new JTextField();
        campoNumero2.setBounds(50, 200, 330, 35);
        campoNumero2.setFont(new Font("Arial", Font.PLAIN, 16));
        add(campoNumero2);

        botaoSoma = criarBotao("+", 50, 270);

        botaoSubtracao = criarBotao("-", 130, 270);

        botaoMultiplicacao = criarBotao("×", 210, 270);
        
        botaoDivisao = criarBotao("÷", 290, 270);
        

        botaoLimpar = new JButton("Limpar");
        botaoLimpar.setBounds(140, 320, 140, 40);
        botaoLimpar.setBackground(new Color(99, 110, 114));
        botaoLimpar.setForeground(Color.WHITE);
        botaoLimpar.setFocusPainted(false);
        botaoLimpar.setFont(new Font("Arial", Font.PLAIN, 14));
        add(botaoLimpar);

      
        labelResultado = new JLabel("Resultado: ");
        labelResultado.setBounds(50, 370, 300, 30);
        labelResultado.setFont(new Font("Arial", Font.PLAIN, 18));
        labelResultado.setForeground(new Color(9, 132, 227));
        add(labelResultado);

       
        botaoSoma.addActionListener(e -> realizarOperacao("+"));

        botaoSubtracao.addActionListener(e -> realizarOperacao("-"));

        botaoMultiplicacao.addActionListener(e -> realizarOperacao("*"));

        botaoDivisao.addActionListener(e -> realizarOperacao("/"));
        
        botaoLimpar.addActionListener(e -> limparCampos());

        setVisible(true);
    }

   
    private JButton criarBotao(String texto, int x, int y) {

        JButton botao = new JButton(texto);

        botao.setBounds(x, y, 70, 40);

        botao.setBackground(new Color(9, 132, 227));

        botao.setForeground(Color.WHITE);

        botao.setFocusPainted(false);

        botao.setFont(new Font("Arial", Font.BOLD, 18));

        add(botao);

        return botao;
    }

    
    private void realizarOperacao(String operacao) {

        try {

        
            if (campoNumero1.getText().isEmpty()
                    || campoNumero2.getText().isEmpty()) {

                throw new CalculadoraException(
                        "Preencha todos os campos."
                );
            }

          
            double numero1 =
                    Double.parseDouble(campoNumero1.getText());

            double numero2 =
                    Double.parseDouble(campoNumero2.getText());

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

                        throw new CalculadoraException(
                                "Não é possível dividir por zero."
                        );
                    }

                    resultado = numero1 / numero2;
                    break;
            }

      
            labelResultado.setText(
                    "Resultado: " + resultado
            );

        }

        catch (NumberFormatException erro) {

            JOptionPane.showMessageDialog(
                    null,
                    "Digite apenas números válidos.",
                    "Erro de Entrada",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        
        catch (CalculadoraException erro) {

            JOptionPane.showMessageDialog(
                    null,
                    erro.getMessage(),
                    "Erro",
                    JOptionPane.WARNING_MESSAGE
            );
        }

        catch (Exception erro) {

            JOptionPane.showMessageDialog(
                    null,
                    "Ocorreu um erro inesperado.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

  
    private void limparCampos() {

        campoNumero1.setText("");

        campoNumero2.setText("");

        labelResultado.setText("Resultado: ");

        campoNumero1.requestFocus();
    }

}