package calculadora.objetos;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Calculadora extends JFrame implements ActionListener {

    private JTextField display;

    private double firstNumber = 0;
    private String secondNumber = "";
    private String operator = "";
    private boolean newNumber = true;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(350, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.HEIGHT);
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel panelBtns = new JPanel();
        panelBtns.setLayout(new GridLayout(5, 4, 5, 5));

        String[] btns = {
            "C", "", "", "",
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String text : btns) {
            if (text.equals("")) {
                panelBtns.add(new JLabel());
            } else {
                JButton button = new JButton(text);
                button.setFont(new Font("Arial", Font.BOLD, 24));
                button.addActionListener(this);
                panelBtns.add(button);
            }
        }

        add(panelBtns, BorderLayout.CENTER);
        configureKeyboard();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                showStartupWarning();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        try {
            processCommand(command);
        } catch (CalculadoraException ex) {
            showError(ex.getMessage());
        }
    }

    public void insertNumber(String number) {
        if (operator.isEmpty()) {
            if (newNumber) {
                display.setText(number);
                newNumber = false;
            } else {
                display.setText(display.getText() + number);
            }
        } else {
            secondNumber += number;
            display.setText(display.getText() + number);
            newNumber = false;
        }
    }

    public void insertPoint() {
        if (operator.isEmpty()) {
            if (newNumber) {
                display.setText("0.");
                newNumber = false;
            } else if (!display.getText().contains(".")) {
                display.setText(display.getText() + ".");
            }
        } else {
            if (secondNumber.isEmpty()) {
                secondNumber = "0.";
                display.setText(display.getText() + "0.");
            } else if (!secondNumber.contains(".")) {
                secondNumber += ".";
                display.setText(display.getText() + ".");
            }
        }
    }

    public void clear() {
        display.setText("");
        firstNumber = 0;
        operator = "";
        secondNumber = "";
        newNumber = true;
    }

    public void calcResult() throws CalculadoraException {
        if (operator.isEmpty() || secondNumber.isEmpty()) {
            throw new CalculadoraException("Informe a operação completa antes de calcular.");
        }

        double segundoNumero = Double.parseDouble(secondNumber);
        double resultado = 0;

        switch (operator) {
            case "+":
                resultado = firstNumber + segundoNumero;
                break;

            case "-":
                resultado = firstNumber - segundoNumero;
                break;

            case "*":
                resultado = firstNumber * segundoNumero;
                break;

            case "/":
                if (segundoNumero == 0) {
                    throw new CalculadoraException("Não é possível dividir por zero.");
                }

                resultado = firstNumber / segundoNumero;
                break;
        }

        display.setText(String.valueOf(resultado));
        firstNumber = resultado;
        operator = "";
        secondNumber = "";
        newNumber = true;
    }

    public void defineOperator(String newOperator) throws CalculadoraException {
        if (display.getText().isEmpty()) {
            throw new CalculadoraException("Digite um número antes de escolher uma operação.");
        }

        if (operator.isEmpty()) {
            firstNumber = Double.parseDouble(display.getText());
            operator = newOperator;
            secondNumber = "";

            display.setText(display.getText() + " " + newOperator + " ");
            newNumber = true;

        } else if (secondNumber.isEmpty()) {
            operator = newOperator;

            display.setText(
                    display.getText().replaceFirst("\\s[+\\-*/]\\s$", " " + newOperator + " ")
            );
        }
    }

    private void processCommand(String command) throws CalculadoraException {
        if (command.matches("[0-9]")) {
            insertNumber(command);

        } else if (command.equals(".")) {
            insertPoint();

        } else if (command.equals("C")) {
            clear();

        } else if (command.equals("=")) {
            calcResult();

        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            defineOperator(command);

        } else {
            throw new CalculadoraException("Caractere inválido. Use apenas números e operadores.");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Erro na calculadora",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void configureKeyboard() {
        String[] keys = {
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "+", "-", "*", "/", ".", "C"
        };

        for (String key : keys) {
            bindKey(key, key);
        }

        bindKey("ENTER", "=");
        bindKey("ESCAPE", "C");
    }

    private void bindKey(String keyStroke, String command) {
        getRootPane()
                .getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(KeyStroke.getKeyStroke(keyStroke), command);

        getRootPane()
                .getActionMap()
                .put(command, new javax.swing.AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            processCommand(command);
                        } catch (CalculadoraException ex) {
                            showError(ex.getMessage());
                        }
                    }
                });
    }

    private void showStartupWarning() {
        JOptionPane.showMessageDialog(
                this,
                "Regras de uso da calculadora:\n\n"
                + "- Utilize apenas números e operadores válidos.\n"
                + "- Operadores permitidos: +, -, * e /.\n"
                + "- Não é permitido dividir por zero.\n"
                + "- Pressione Enter para calcular.\n"
                + "- Pressione ESC ou C para limpar.\n"
                + "- Caracteres inválidos gerarão uma mensagem de erro.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
