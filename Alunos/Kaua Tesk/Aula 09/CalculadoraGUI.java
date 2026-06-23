import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CalculadoraGUI extends JFrame {

    
    private JTextField campoNumero1;
    private JTextField campoNumero2;

    
    private JLabel labelResultado;
  
    private String operacaoSelecionada = "";


    private Calculadora calculadora = new Calculadora();

    public CalculadoraGUI() {
        
        setTitle("Calculadora");
        setSize(320, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(10, 10));

 
        ((JPanel) getContentPane()).setBorder(
            BorderFactory.createEmptyBorder(16, 16, 16, 16)
        );

        JPanel painelCampos = new JPanel(new GridLayout(4, 1, 6, 6));

        painelCampos.add(new JLabel("Primeiro número:"));
        campoNumero1 = new JTextField();
        campoNumero1.setFont(new Font("Arial", Font.PLAIN, 14));
        painelCampos.add(campoNumero1);

        painelCampos.add(new JLabel("Segundo número:"));
        campoNumero2 = new JTextField();
        campoNumero2.setFont(new Font("Arial", Font.PLAIN, 14));
        painelCampos.add(campoNumero2);

        add(painelCampos, BorderLayout.NORTH);

        
        JPanel painelCentro = new JPanel(new GridLayout(3, 1, 8, 8));

        painelCentro.add(new JLabel("Selecione a operação:"));

     
        JPanel painelOps = new JPanel(new GridLayout(1, 4, 6, 0));
        String[] ops = {"+", "-", "×", "÷"};
        ButtonGroup grupo = new ButtonGroup(); 

        for (String op : ops) {
            
            JToggleButton btn = new JToggleButton(op);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            grupo.add(btn);
            btn.addActionListener(e -> operacaoSelecionada = op);
            painelOps.add(btn);
        }
        painelCentro.add(painelOps);

        
        JButton btnCalcular = new JButton("Calcular");
        btnCalcular.setFont(new Font("Arial", Font.BOLD, 14));
        btnCalcular.setBackground(new Color(70, 130, 180));
        btnCalcular.setForeground(Color.WHITE);
        btnCalcular.setFocusPainted(false);
        btnCalcular.addActionListener(e -> realizarCalculo());
        painelCentro.add(btnCalcular);

        add(painelCentro, BorderLayout.CENTER);

    
        JPanel painelResultado = new JPanel(new BorderLayout(4, 4));
        painelResultado.setBorder(BorderFactory.createTitledBorder("Resultado"));

        labelResultado = new JLabel("Aguardando cálculo...", SwingConstants.CENTER);
        labelResultado.setFont(new Font("Arial", Font.BOLD, 16));
        painelResultado.add(labelResultado, BorderLayout.CENTER);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setFont(new Font("Arial", Font.PLAIN, 12));
        btnLimpar.addActionListener(e -> limpar());
        painelResultado.add(btnLimpar, BorderLayout.SOUTH);

        add(painelResultado, BorderLayout.SOUTH);

        setVisible(true);
    }


    private void realizarCalculo() {
        if (operacaoSelecionada.isEmpty()) {
            labelResultado.setForeground(Color.RED);
            labelResultado.setText("Selecione uma operação!");
            return;
        }

     
        try {
            
            double num1 = calculadora.converterNumero(campoNumero1.getText(), "Primeiro número");
            double num2 = calculadora.converterNumero(campoNumero2.getText(), "Segundo número");

            double resultado = calculadora.calcular(num1, num2, operacaoSelecionada);

            labelResultado.setForeground(new Color(0, 128, 0));
            labelResultado.setText(
                calculadora.formatarResultado(num1) + " " + operacaoSelecionada +
                " " + calculadora.formatarResultado(num2) +
                " = " + calculadora.formatarResultado(resultado)
            );

        } catch (CalculadoraException e) {
           
            labelResultado.setForeground(Color.RED);
            labelResultado.setText("<html><center>" + e.getMessage() + "</center></html>");
            System.out.println("[" + e.getCodigoErro() + "] " + e.getMessage());
        }
    }

    
    private void limpar() {
        campoNumero1.setText("");
        campoNumero2.setText("");
        operacaoSelecionada = "";
        labelResultado.setForeground(Color.DARK_GRAY);
        labelResultado.setText("Aguardando cálculo...");
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculadoraGUI());
    }
}
