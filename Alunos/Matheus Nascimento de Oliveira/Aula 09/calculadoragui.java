import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class calculadoragui extends JFrame {

    private final JTextField txtNumero1;
    private final JTextField txtNumero2;
    private final JTextField txtResultado;

    public calculadoragui() {
        super("Calculadora Simples");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(380, 220);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel painelEntrada = new JPanel(new GridLayout(3, 2, 8, 8));
        painelEntrada.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        painelEntrada.add(new JLabel("Número 1:"));
        txtNumero1 = new JTextField();
        painelEntrada.add(txtNumero1);

        painelEntrada.add(new JLabel("Número 2:"));
        txtNumero2 = new JTextField();
        painelEntrada.add(txtNumero2);

        painelEntrada.add(new JLabel("Resultado:"));
        txtResultado = new JTextField();
        txtResultado.setEditable(false);
        painelEntrada.add(txtResultado);

        add(painelEntrada, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel(new GridLayout(1, 4, 8, 8));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton btnSomar = criarBotao("+", e -> calcular(Operation.SOMA));
        JButton btnSubtrair = criarBotao("-", e -> calcular(Operation.SUBTRACAO));
        JButton btnMultiplicar = criarBotao("×", e -> calcular(Operation.MULTIPLICACAO));
        JButton btnDividir = criarBotao("÷", e -> calcular(Operation.DIVISAO));

        painelBotoes.add(btnSomar);
        painelBotoes.add(btnSubtrair);
        painelBotoes.add(btnMultiplicar);
        painelBotoes.add(btnDividir);

        add(painelBotoes, BorderLayout.CENTER);
    }

    private JButton criarBotao(String texto, ActionListener acao) {
        JButton botao = new JButton(texto);
        botao.setFont(botao.getFont().deriveFont(Font.BOLD, 18f));
        botao.addActionListener(acao);
        return botao;
    }

    private void calcular(Operation operacao) {
        try {
            double n1 = parseNumero(txtNumero1.getText());
            double n2 = parseNumero(txtNumero2.getText());
            double resultado;

            switch (operacao) {
                case SOMA:
                    resultado = n1 + n2;
                    break;
                case SUBTRACAO:
                    resultado = n1 - n2;
                    break;
                case MULTIPLICACAO:
                    resultado = n1 * n2;
                    break;
                case DIVISAO:
                    if (n2 == 0) {
                        throw new CalculadoraException("Divisão por zero não é permitida.");
                    }
                    resultado = n1 / n2;
                    break;
                default:
                    throw new CalculadoraException("Operação desconhecida.");
            }

            txtResultado.setText(String.valueOf(resultado));
        } catch (CalculadoraException ex) {
            txtResultado.setText("");
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de cálculo", JOptionPane.ERROR_MESSAGE);
        }
    }

    private double parseNumero(String texto) throws CalculadoraException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new CalculadoraException("Entrada inválida: informe um número em ambos os campos.");
        }

        try {
            return Double.parseDouble(texto.trim());
        } catch (NumberFormatException ex) {
            throw new CalculadoraException("Entrada inválida: utilize apenas caracteres numéricos.");
        }
    }

    private enum Operation {
        SOMA,
        SUBTRACAO,
        MULTIPLICACAO,
        DIVISAO
    }

    public static class CalculadoraException extends Exception {
        public CalculadoraException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            calculadoragui janela = new calculadoragui();
            janela.setVisible(true);
        });
    }
}
