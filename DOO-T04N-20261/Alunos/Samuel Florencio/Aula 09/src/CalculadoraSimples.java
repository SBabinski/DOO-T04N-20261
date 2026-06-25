import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraSimples extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
    private JTextField textoNum1, textoNum2;
    private JLabel Resultado;
    private JButton Somar, Subtrair, Multiplicar, Dividir;

    public CalculadoraSimples() {
        setTitle("Calculadora Simples");
        setSize(275, 275);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1, 10, 10));
        JPanel painelNum1 = new JPanel(new FlowLayout());
        JPanel painelNum2 = new JPanel(new FlowLayout());
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JPanel painelResultado = new JPanel(new FlowLayout());
        painelNum1.add(new JLabel("Número 1:"));
        textoNum1 = new JTextField(10);
        painelNum1.add(textoNum1);
        painelNum2.add(new JLabel("Número 2:"));
        textoNum2 = new JTextField(10);
        painelNum2.add(textoNum2);
        Somar = new JButton("+");
        Subtrair = new JButton("-");
        Multiplicar = new JButton("×");
        Dividir = new JButton("÷");
        Somar.addActionListener(this);
        Subtrair.addActionListener(this);
        Multiplicar.addActionListener(this);
        Dividir.addActionListener(this);
        painelBotoes.add(Somar);
        painelBotoes.add(Subtrair);
        painelBotoes.add(Multiplicar);
        painelBotoes.add(Dividir);
        Resultado = new JLabel("Resultado:  .");
        Resultado.setFont(new Font("Arial", Font.BOLD, 12));
        painelResultado.add(Resultado);
        add(painelNum1);
        add(painelNum2);
        add(painelBotoes);
        add(painelResultado);
    }
    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = converterParaDouble(textoNum1.getText());
            double num2 = converterParaDouble(textoNum2.getText());
            double resultado = 0;
            if (e.getSource() == Somar) {
                resultado = num1 + num2;
            } else if (e.getSource() == Subtrair) {
                resultado = num1 - num2;
            } else if (e.getSource() == Multiplicar) {
                resultado = num1 * num2;
            } else if (e.getSource() == Dividir) {
                if (num2 == 0) {
                    throw new CalculadoraException("Erro: Não é possível dividir por zero!");
                }
                resultado = num1 / num2;
            }
            Resultado.setText(String.format("Resultado: %.2f", resultado));
            if (resultado >= 0) {
            	Resultado.setForeground(Color.GREEN);
            } else {
            	Resultado.setForeground(Color.RED);
            }
        } catch (CalculadoraException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Operação", JOptionPane.ERROR_MESSAGE);
            Resultado.setText("Erro!");
            Resultado.setForeground(Color.RED);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro inesperado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    private double converterParaDouble(String texto) throws CalculadoraException {
        try {
            return Double.parseDouble(texto.replace(",", "."));
        } catch (NumberFormatException e) {
        	throw new CalculadoraException("Erro: Digite apenas números válidos nos campos!");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculadoraSimples().setVisible(true);
        });
    }
}