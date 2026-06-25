import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraFrame extends JFrame implements ActionListener {

    JTextField numero1;
    JTextField numero2;
    JLabel resultado;

    JButton somar;
    JButton subtrair;
    JButton multiplicar;
    JButton dividir;

    public CalculadoraFrame() {

        setTitle("Calculadora");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Numero 1:"));
        numero1 = new JTextField();
        add(numero1);

        add(new JLabel("Numero 2:"));
        numero2 = new JTextField();
        add(numero2);

        somar = new JButton("+");
        subtrair = new JButton("-");
        multiplicar = new JButton("X");
        dividir = new JButton("/");

        add(somar);
        add(subtrair);
        add(multiplicar);
        add(dividir);

        somar.addActionListener(this);
        subtrair.addActionListener(this);
        multiplicar.addActionListener(this);
        dividir.addActionListener(this);

        resultado = new JLabel("Resultado:");
        add(resultado);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            double n1;
            double n2;
            double res = 0;

            try {
                n1 = Double.parseDouble(numero1.getText());
                n2 = Double.parseDouble(numero2.getText());
            } catch (Exception ex) {
                throw new CalculadoraException("Digite apenas numeros");
            }

            if (e.getSource() == somar) {
                res = n1 + n2;
            }

            if (e.getSource() == subtrair) {
                res = n1 - n2;
            }

            if (e.getSource() == multiplicar) {
                res = n1 * n2;
            }

            if (e.getSource() == dividir) {

                if (n2 == 0) {
                    throw new CalculadoraException("Nao existe divisao por zero");
                }

                res = n1 / n2;
            }

            resultado.setText("Resultado: " + res);

        } catch (CalculadoraException ex) {

            JOptionPane.showMessageDialog(null, ex.getMessage());

        }
    }
}