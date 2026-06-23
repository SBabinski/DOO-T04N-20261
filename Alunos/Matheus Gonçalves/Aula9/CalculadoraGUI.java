package fag;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalculadoraGUI extends JFrame {
	private JTextField txtDisplay;
    private double primeiroNumero;
    private char operacao;
    private boolean novaEntrada;

    public CalculadoraGUI() {
        setTitle("Calculadora");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        txtDisplay = new JTextField("0");
        txtDisplay.setFont(new Font("Arial", Font.BOLD, 28));
        txtDisplay.setHorizontalAlignment(JTextField.RIGHT);
        add(txtDisplay, BorderLayout.NORTH);

        JPanel painel = new JPanel(new GridLayout(4, 4, 5, 5));

        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.BOLD, 22));
            botao.addActionListener(new BotaoListener());
            painel.add(botao);
        }

        add(painel, BorderLayout.CENTER);

        novaEntrada = true;
    }

    private class BotaoListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String comando = ((JButton) e.getSource()).getText();

            try {
                if (comando.matches("[0-9]")) {
                    adicionarNumero(comando);
                } else if (comando.matches("[+\\-*/]")) {
                    definirOperacao(comando.charAt(0));
                } else if (comando.equals("=")) {
                    calcularResultado();
                } else if (comando.equals("C")) {
                    limpar();
                }
            } catch (CalculadoraException ex) {
                JOptionPane.showMessageDialog(
                    CalculadoraGUI.this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                limpar();
            }
        }
    }

    private void adicionarNumero(String numero) {
        if (novaEntrada || txtDisplay.getText().equals("0")) {
            txtDisplay.setText(numero);
            novaEntrada = false;
        } else {
            txtDisplay.setText(txtDisplay.getText() + numero);
        }
    }
    
    private void definirOperacao(char op) throws CalculadoraException {
        primeiroNumero = lerNumero(txtDisplay.getText());
        operacao = op;
        novaEntrada = true;
    }

    private void calcularResultado() throws CalculadoraException {
        double segundoNumero = lerNumero(txtDisplay.getText());
        double resultado = calcular(primeiroNumero, segundoNumero, operacao);

        if (resultado == (long) resultado) {
            txtDisplay.setText(String.valueOf((long) resultado));
        } else {
            txtDisplay.setText(String.valueOf(resultado));
        }

        novaEntrada = true;
    }

    private void limpar() {
        txtDisplay.setText("0");
        primeiroNumero = 0;
        operacao = '\0';
        novaEntrada = true;
    }

    private double lerNumero(String texto) throws CalculadoraException {
        try {
            return Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            throw new CalculadoraException(
                "Entrada inválida. Digite apenas números."
            );
        }
    }

    private double calcular(double num1, double num2, char operacao)
            throws CalculadoraException {

        switch (operacao) {
            case '+':
                return num1 + num2;

            case '-':
                return num1 - num2;

            case '*':
                return num1 * num2;

            case '/':
                if (num2 == 0) {
                    throw new CalculadoraException(
                        "Não é possível dividir por zero."
                    );
                }
                return num1 / num2;

            default:
                throw new CalculadoraException("Nenhuma operação selecionada.");
        }
    }
}