import java.awt.*;
import javax.swing.*;

public class CalculadoraGUI extends JFrame {
    private JTextField txtNum1, txtNum2;
    private JLabel lblResult;
    private CalculadoraService service = new CalculadoraService();
    
    public CalculadoraGUI() {
        setTitle("Calculadora Simples");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1, 10, 10));

        txtNum1 = new JTextField();
        txtNum2 = new JTextField();
        lblResult = new JLabel("Resultado: ", SwingConstants.CENTER);

        JButton btnSoma = new JButton("+");
        JButton btnSubtracao = new JButton("-");
        JButton btnMultiplicacao = new JButton("*");
        JButton btnDivisao = new JButton("/");

        btnSoma.addActionListener(e -> executar("+"));
        btnSubtracao.addActionListener(e -> executar("-"));
        btnMultiplicacao.addActionListener(e -> executar("*"));
        btnDivisao.addActionListener(e -> executar("/"));

        JPanel painelBotoes = new JPanel(new GridLayout(1, 4, 5, 5));
        painelBotoes.add(btnSoma);
        painelBotoes.add(btnSubtracao);
        painelBotoes.add(btnMultiplicacao);
        painelBotoes.add(btnDivisao);

        add(new JLabel("Número 1:"));
        add(txtNum1);
        add(new JLabel("Número 2:"));
        add(txtNum2);
        add(painelBotoes);
        add(lblResult);
    }

    private void executar(String operacao) {
        try {
            double resultado = service.calcular(txtNum1.getText(), txtNum2.getText(), operacao);
            lblResult.setText(String.format("Resultado: %.2f", resultado));
        } catch (CalculadoraException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Cálculo", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida! Digite apenas números.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGUI().setVisible(true));
    }
}