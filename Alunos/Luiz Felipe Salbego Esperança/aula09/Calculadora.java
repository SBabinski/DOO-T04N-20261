import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class EntradaInvalidaException extends Exception {

    public EntradaInvalidaException(String mensagem) {
        super(mensagem);
    }
}

public class Calculadora extends JFrame implements ActionListener {

    private JTextField campo1;
    private JTextField campo2;

    private JLabel resultado;

    private JButton soma;
    private JButton subtracao;
    private JButton multiplicacao;
    private JButton divisao;

    public Calculadora() {

        setTitle("Calculadora");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel();

        painel.setLayout(new GridLayout(5, 2, 15, 15));
        painel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        campo1 = new JTextField();
        campo2 = new JTextField();

        resultado = new JLabel("Resultado: ");
        resultado.setHorizontalAlignment(SwingConstants.CENTER);

        soma = new JButton("+");
        subtracao = new JButton("-");
        multiplicacao = new JButton("X");
        divisao = new JButton("/");

        soma.addActionListener(this);
        subtracao.addActionListener(this);
        multiplicacao.addActionListener(this);
        divisao.addActionListener(this);

        painel.add(new JLabel("Primeiro Numero:"));
        painel.add(campo1);

        painel.add(new JLabel("Segundo Numero:"));
        painel.add(campo2);

        painel.add(soma);
        painel.add(subtracao);

        painel.add(multiplicacao);
        painel.add(divisao);

        painel.add(resultado);

        add(painel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {

            double numero1 = Double.parseDouble(campo1.getText());
            double numero2 = Double.parseDouble(campo2.getText());

            double valor = 0;

            if (e.getSource() == soma) {
                valor = numero1 + numero2;
            }

            else if (e.getSource() == subtracao) {
                valor = numero1 - numero2;
            }

            else if (e.getSource() == multiplicacao) {
                valor = numero1 * numero2;
            }

            else if (e.getSource() == divisao) {

                if (numero2 == 0) {
                    throw new EntradaInvalidaException("Nao e possivel dividir por zero.");
                }

                valor = numero1 / numero2;
            }

            resultado.setText("Resultado: " + valor);
        }

        catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite apenas numeros validos.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        catch (EntradaInvalidaException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {

        new Calculadora();
    }
}