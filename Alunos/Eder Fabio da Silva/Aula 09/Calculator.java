import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Calculadora com botões numéricos clicáveis, interface Java Swing
 * e tratamento de exceções personalizado.
 */
public class Calculator extends JFrame {

    // --- Estado interno ---
    private double firstOperand = 0;
    private String pendingOp = null;
    private boolean waitingSecond = false;
    private boolean justCalculated = false;

    // --- Componentes de exibição ---
    private final JLabel display;
    private final JLabel labelError;

    // --- Cores ---
    private static final Color BG         = new Color(30,  30,  40);
    private static final Color DISPLAY_BG = new Color(20,  20,  30);
    private static final Color NUM_BG     = new Color(60,  60,  75);
    private static final Color NUM_HOVER  = new Color(80,  80,  100);
    private static final Color OP_BG      = new Color(70,  130, 180);
    private static final Color OP_HOVER   = new Color(100, 160, 210);
    private static final Color EQUALS_BG  = new Color(46,  160, 67);
    private static final Color EQUALS_HOV = new Color(60,  200, 85);
    private static final Color CLEAR_BG   = new Color(180, 50,  50);
    private static final Color CLEAR_HOV  = new Color(220, 70,  70);

    public Calculator() {
        super("Calculadora");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(BG);

        JPanel root = new JPanel(new BorderLayout(0, 10));
        root.setBackground(BG);
        root.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(root);

        // ---- Display ----
        JPanel displayPanel = new JPanel(new BorderLayout(0, 4));
        displayPanel.setBackground(BG);

        display = new JLabel("0", SwingConstants.RIGHT);
        display.setFont(new Font("Segoe UI", Font.BOLD, 36));
        display.setForeground(Color.WHITE);
        display.setOpaque(true);
        display.setBackground(DISPLAY_BG);
        display.setBorder(new EmptyBorder(12, 12, 12, 12));
        displayPanel.add(display, BorderLayout.CENTER);

        labelError = new JLabel(" ", SwingConstants.CENTER);
        labelError.setFont(new Font("Segoe UI", Font.ITALIC, 11));
        labelError.setForeground(new Color(255, 100, 100));
        displayPanel.add(labelError, BorderLayout.SOUTH);

        root.add(displayPanel, BorderLayout.NORTH);

        // ---- Grade de botões ----
        // Layout:
        //  C   ±  DEL  ÷
        //  7   8   9   ×
        //  4   5   6   −
        //  1   2   3   +
        //  0   0   .   =
        JPanel grid = new JPanel(new GridLayout(5, 4, 8, 8));
        grid.setBackground(BG);

        String[][] labels = {
            {"C",  "±",  "DEL",  "÷"},
            {"7",  "8",  "9",  "×"},
            {"4",  "5",  "6",  "−"},
            {"1",  "2",  "3",  "+"},
            {"0",  "00", ".",  "="}
        };

        for (String[] row : labels) {
            for (String lbl : row) {
                grid.add(buildButton(lbl));
            }
        }

        root.add(grid, BorderLayout.CENTER);

        pack();
        setMinimumSize(new Dimension(300, 420));
        setLocationRelativeTo(null);
    }

    // -----------------------------------------------------------------------
    //  Criação de botões
    // -----------------------------------------------------------------------

    private JButton buildButton(String label) {
        JButton btn = new JButton(label);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        Color normal, hover;
        switch (label) {
            case "=":
                normal = EQUALS_BG; hover = EQUALS_HOV; break;
            case "+": case "−": case "×": case "÷":
                normal = OP_BG; hover = OP_HOVER; break;
            case "C": case "DEL":
                normal = CLEAR_BG; hover = CLEAR_HOV; break;
            default:
                normal = NUM_BG; hover = NUM_HOVER; break;
        }

        btn.setBackground(normal);
        Color finalNormal = normal;
        Color finalHover  = hover;
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(finalHover); }
            public void mouseExited (java.awt.event.MouseEvent e) { btn.setBackground(finalNormal); }
        });
        
        btn.addActionListener(e -> handleInput(label));
        return btn;
    }

    // -----------------------------------------------------------------------
    //  Tratamento de entrada dos botões
    // -----------------------------------------------------------------------

    private void handleInput(String label) {
        clearError();

        switch (label) {
            // --- Dígitos ---
            case "0": case "1": case "2": case "3": case "4":
            case "5": case "6": case "7": case "8": case "9":
                appendDigit(label);
                break;
            case "00":
                appendDigit("0");
                appendDigit("0");
                break;

            // --- Ponto decimal ---
            case ".":
                appendDot();
                break;

            // --- Operadores ---
            case "+": case "−": case "×": case "÷":
                handleOperator(label);
                break;

            // --- Igual ---
            case "=":
                handleEquals();
                break;

            // --- Sinal ---
            case "±":
                toggleSign();
                break;

            // --- Apagar último dígito ---
            case "DEL":
                backspace();
                break;

            // --- Limpar tudo ---
            case "C":
                clearAll();
                break;
        }
    }

    private void appendDigit(String digit) {
        if (waitingSecond || justCalculated) {
            display.setText(digit);
            waitingSecond  = false;
            justCalculated = false;
        } else {
            String cur = display.getText();
            display.setText(cur.equals("0") ? digit : cur + digit);
        }
    }

    private void appendDot() {
        if (waitingSecond || justCalculated) {
            display.setText("0.");
            waitingSecond  = false;
            justCalculated = false;
            return;
        }
        if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    private void handleOperator(String op) {
        try {
            double current = parseDisplay();

            // Encadeia operações: calcula o resultado pendente antes de armazenar novo operador
            if (pendingOp != null && !waitingSecond) {
                double result = applyOperation(pendingOp, firstOperand, current);
                setDisplay(result);
                firstOperand = result;
            } else {
                firstOperand = current;
            }

            pendingOp     = op;
            waitingSecond = true;
            justCalculated = false;

        } catch (CalculatorException ex) {
            showError(ex.getMessage());
        }
    }

    private void handleEquals() {
        if (pendingOp == null) return;

        try {
            double second = parseDisplay();
            double result = applyOperation(pendingOp, firstOperand, second);

            setDisplay(result);
            pendingOp      = null;
            waitingSecond  = false;
            justCalculated = true;

        } catch (CalculatorException ex) {
            showError(ex.getMessage());
            pendingOp = null;
        }
    }

    private void toggleSign() {
        try {
            double val = parseDisplay();
            setDisplay(val * -1);
        } catch (CalculatorException ex) {
            showError(ex.getMessage());
        }
    }

    private void backspace() {
        if (justCalculated) { clearAll(); return; }
        String cur = display.getText();
        if (cur.length() <= 1 || (cur.length() == 2 && cur.startsWith("-"))) {
            display.setText("0");
        } else {
            display.setText(cur.substring(0, cur.length() - 1));
        }
    }

    // -----------------------------------------------------------------------
    //  Lógica matemática
    // -----------------------------------------------------------------------

    private double parseDisplay() throws CalculatorException {
        String text = display.getText().trim();
        if (text.isEmpty() || text.equals("-")) {
            throw CalculatorException.emptyField("display");
        }
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            throw CalculatorException.invalidInput("display");
        }
    }

    private double applyOperation(String op, double a, double b) throws CalculatorException {
        switch (op) {
            case "+": return a + b;
            case "−": return a - b;
            case "×": return a * b;
            case "÷":
                if (b == 0) throw CalculatorException.divisionByZero();
                return a / b;
            default:
                throw new IllegalArgumentException("Operação desconhecida: " + op);
        }
    }

    // -----------------------------------------------------------------------
    //  Utilitários de UI
    // -----------------------------------------------------------------------

    private void setDisplay(double value) {
        if (value == Math.floor(value) && !Double.isInfinite(value) && Math.abs(value) < 1e15) {
            display.setText(String.valueOf((long) value));
        } else {
            display.setText(String.format("%.8f", value)
                .replaceAll("0+$", "")
                .replaceAll("\\.$", ""));
        }
    }

    private void showError(String msg) {
        labelError.setText(msg);
        display.setText("Erro");
    }

    private void clearError() {
        labelError.setText(" ");
    }

    private void clearAll() {
        display.setText("0");
        firstOperand   = 0;
        pendingOp      = null;
        waitingSecond  = false;
        justCalculated = false;
        clearError();
    }

    // -----------------------------------------------------------------------
    //  Ponto de entrada
    // -----------------------------------------------------------------------

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> new Calculator().setVisible(true));
    }
}
