import java.awt.*;
import javax.swing.*;

public class CalculadoraGUI extends JFrame {
    private JTextField display;
    private double primeiroNumero = 0;
    private String operacao = "";
    private boolean novoNumero = true;
    private CalculadoraService service = new CalculadoraService();

    public CalculadoraGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Calculadora Japa");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 28));
        add(display, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridLayout(4, 4, 5, 5));
        
        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String texto : botoes) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.addActionListener(e -> acaoBotao(texto));
            painelBotoes.add(btn);
        }

        add(painelBotoes, BorderLayout.CENTER);
    }

    private void acaoBotao(String texto) {
        try {
            if ("0123456789".contains(texto)) {
                if (novoNumero) {
                    display.setText(texto);
                    novoNumero = false;
                } else {
                    display.setText(display.getText() + texto);
                }
            } else if (texto.equals("C")) {
                display.setText("0");
                primeiroNumero = 0;
                novoNumero = true;
            } else if (texto.equals("=")) {
                double segundoNumero = Double.parseDouble(display.getText());
                double resultado = service.calcular(primeiroNumero, segundoNumero, operacao);
                display.setText(String.valueOf(resultado));
                novoNumero = true;
            } else {
                primeiroNumero = Double.parseDouble(display.getText());
                operacao = texto;
                novoNumero = true;
            }
        } catch (CalculadoraException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            display.setText("0");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida!", "Erro", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGUI().setVisible(true));
    }
}