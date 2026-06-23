import java.awt.*;
import javax.swing.*;

public class Main extends JFrame {
    private JTextField num1;
    private JTextField num2;
    private JTextArea resultado;
    
    public Main() {
        setTitle("Calculadora");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JPanel entrada = new JPanel(new GridLayout(2, 2, 10, 10));
        entrada.setBorder(BorderFactory.createTitledBorder("Numeros"));
        
        entrada.add(new JLabel("Numero 1:"));
        num1 = new JTextField();
        entrada.add(num1);
        
        entrada.add(new JLabel("Numero 2:"));
        num2 = new JTextField();
        entrada.add(num2);
        
        JPanel botoes = new JPanel(new GridLayout(2, 2, 10, 10));
        botoes.setBorder(BorderFactory.createTitledBorder("Operacoes"));
        
        JButton btnMais = new JButton("+");
        btnMais.addActionListener(e -> calcular("+"));
        botoes.add(btnMais);
        
        JButton btnMenos = new JButton("-");
        btnMenos.addActionListener(e -> calcular("-"));
        botoes.add(btnMenos);
        
        JButton btnMult = new JButton("*");
        btnMult.addActionListener(e -> calcular("*"));
        botoes.add(btnMult);
        
        JButton btnDiv = new JButton("/");
        btnDiv.addActionListener(e -> calcular("/"));
        botoes.add(btnDiv);
        
        resultado = new JTextArea();
        resultado.setEditable(false);
        resultado.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(resultado);
        
        painel.add(entrada, BorderLayout.NORTH);
        painel.add(botoes, BorderLayout.CENTER);
        painel.add(scroll, BorderLayout.SOUTH);
        
        add(painel);
    }
    
    private void calcular(String op) {
        try {
            String n1 = num1.getText().trim();
            String n2 = num2.getText().trim();
            
            if (n1.isEmpty() || n2.isEmpty()) {
                throw new CalculoException("Erro: Preencha os dois campos!");
            }
            
            double a = Double.parseDouble(n1);
            double b = Double.parseDouble(n2);
            double res = 0;
            
            if (op.equals("+")) {
                res = a + b;
            } else if (op.equals("-")) {
                res = a - b;
            } else if (op.equals("*")) {
                res = a * b;
            } else if (op.equals("/")) {
                if (b == 0) {
                    throw new CalculoException("Erro: Divisao por zero!");
                }
                res = a / b;
            }
            
            resultado.setText(n1 + " " + op + " " + n2 + " = " + res);
            
        } catch (CalculoException ex) {
            resultado.setText(ex.getMessage());
        } catch (NumberFormatException ex) {
            resultado.setText("Erro: Entrada invalida!");
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main calc = new Main();
            calc.setVisible(true);
        });
    }
}
