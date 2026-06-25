import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCalculadora extends JFrame implements ActionListener {

    private JTextField txt1;
    private JTextField txt2;

    private JLabel lbl;

    private JButton botao1;
    private JButton botao2;
    private JButton botao3;
    private JButton botao4;

    private Calculadora calc;

    public TelaCalculadora() {

        calc = new Calculadora();

        setTitle("Calculadora");
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Calculadora Simples");
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        add(titulo, BorderLayout.NORTH);

        painel.add(new JLabel("Primeiro número:"));

        txt1 = new JTextField();
        painel.add(txt1);

        painel.add(new JLabel("Segundo número:"));

        txt2 = new JTextField();
        painel.add(txt2);

        botao1 = new JButton("+");
        botao2 = new JButton("-");
        botao3 = new JButton("×");
        botao4 = new JButton("÷");

        botao1.setFont(new Font("Arial", Font.BOLD, 18));
        botao2.setFont(new Font("Arial", Font.BOLD, 18));
        botao3.setFont(new Font("Arial", Font.BOLD, 18));
        botao4.setFont(new Font("Arial", Font.BOLD, 18));

        painel.add(botao1);
        painel.add(botao2);
        painel.add(botao3);
        painel.add(botao4);

        lbl = new JLabel("Resultado: ");
        lbl.setFont(new Font("Arial", Font.BOLD, 18));
        lbl.setHorizontalAlignment(SwingConstants.CENTER);

        painel.add(lbl);

        add(painel, BorderLayout.CENTER);

        botao1.addActionListener(this);
        botao2.addActionListener(this);
        botao3.addActionListener(this);
        botao4.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            double n1 = Double.parseDouble(txt1.getText());
            double n2 = Double.parseDouble(txt2.getText());

            double resultado = 0;

            if (e.getSource() == botao1) {
                resultado = calc.somar(n1, n2);
            }

            else if (e.getSource() == botao2) {
                resultado = calc.subtrair(n1, n2);
            }

            else if (e.getSource() == botao3) {
                resultado = calc.multiplicar(n1, n2);
            }

            else if (e.getSource() == botao4) {
                resultado = calc.dividir(n1, n2);
            }

            lbl.setText("Resultado: " + resultado);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite apenas números!"
            );

        } catch (ErroCalculadoraException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage()
            );
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new TelaCalculadora().setVisible(true);
        });
    }
}