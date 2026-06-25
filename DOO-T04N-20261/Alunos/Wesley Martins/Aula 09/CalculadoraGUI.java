import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe que implementa a interface gráfica da calculadora usando Java Swing
 * Utiliza a classe LogicaCalculadora para realizar as operações
 * e trata exceções com mensagens amigáveis ao usuário
 */
public class CalculadoraGUI extends JFrame implements ActionListener {
    
    private JTextField campoNumero1;
    private JTextField campoNumero2;
    private JLabel labelResultado;
    private JButton botaoAdicionar;
    private JButton botaoSubtrair;
    private JButton botaoMultiplicar;
    private JButton botaoDividir;
    private JButton botaoLimpar;
    
    /**
     * Construtor que inicializa a interface gráfica da calculadora
     */
    public CalculadoraGUI() {
        // Configuração da janela
        setTitle("Calculadora - Java Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Painel principal com layout de grade
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new GridBagLayout());
        painelPrincipal.setBackground(new Color(240, 240, 240));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Título
        JLabel labelTitulo = new JLabel("CALCULADORA");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        painelPrincipal.add(labelTitulo, gbc);
        
        // Label e campo para primeiro número
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        JLabel label1 = new JLabel("Número 1:");
        label1.setFont(new Font("Arial", Font.PLAIN, 12));
        painelPrincipal.add(label1, gbc);
        
        gbc.gridx = 1;
        campoNumero1 = new JTextField();
        campoNumero1.setFont(new Font("Arial", Font.PLAIN, 12));
        campoNumero1.setPreferredSize(new Dimension(150, 30));
        painelPrincipal.add(campoNumero1, gbc);
        
        // Label e campo para segundo número
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel label2 = new JLabel("Número 2:");
        label2.setFont(new Font("Arial", Font.PLAIN, 12));
        painelPrincipal.add(label2, gbc);
        
        gbc.gridx = 1;
        campoNumero2 = new JTextField();
        campoNumero2.setFont(new Font("Arial", Font.PLAIN, 12));
        campoNumero2.setPreferredSize(new Dimension(150, 30));
        painelPrincipal.add(campoNumero2, gbc);
        
        // Painel com botões de operações
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 4, 5, 0));
        painelBotoes.setBackground(new Color(240, 240, 240));
        
        botaoAdicionar = new JButton("+");
        botaoAdicionar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoAdicionar.setBackground(new Color(76, 175, 80));
        botaoAdicionar.setForeground(Color.WHITE);
        botaoAdicionar.addActionListener(this);
        painelBotoes.add(botaoAdicionar);
        
        botaoSubtrair = new JButton("-");
        botaoSubtrair.setFont(new Font("Arial", Font.BOLD, 14));
        botaoSubtrair.setBackground(new Color(33, 150, 243));
        botaoSubtrair.setForeground(Color.WHITE);
        botaoSubtrair.addActionListener(this);
        painelBotoes.add(botaoSubtrair);
        
        botaoMultiplicar = new JButton("×");
        botaoMultiplicar.setFont(new Font("Arial", Font.BOLD, 14));
        botaoMultiplicar.setBackground(new Color(255, 152, 0));
        botaoMultiplicar.setForeground(Color.WHITE);
        botaoMultiplicar.addActionListener(this);
        painelBotoes.add(botaoMultiplicar);
        
        botaoDividir = new JButton("÷");
        botaoDividir.setFont(new Font("Arial", Font.BOLD, 14));
        botaoDividir.setBackground(new Color(244, 67, 54));
        botaoDividir.setForeground(Color.WHITE);
        botaoDividir.addActionListener(this);
        painelBotoes.add(botaoDividir);
        
        painelPrincipal.add(painelBotoes, gbc);
        
        // Label de resultado
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        JLabel labelRes = new JLabel("Resultado:");
        labelRes.setFont(new Font("Arial", Font.PLAIN, 12));
        painelPrincipal.add(labelRes, gbc);
        
        gbc.gridx = 1;
        labelResultado = new JLabel("0");
        labelResultado.setFont(new Font("Arial", Font.BOLD, 14));
        labelResultado.setForeground(new Color(33, 150, 243));
        painelPrincipal.add(labelResultado, gbc);
        
        // Botão Limpar
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        botaoLimpar = new JButton("Limpar");
        botaoLimpar.setFont(new Font("Arial", Font.PLAIN, 12));
        botaoLimpar.setBackground(new Color(158, 158, 158));
        botaoLimpar.setForeground(Color.WHITE);
        botaoLimpar.setPreferredSize(new Dimension(100, 35));
        botaoLimpar.addActionListener(this);
        painelPrincipal.add(botaoLimpar, gbc);
        
        // Adiciona o painel à janela
        add(painelPrincipal);
        
        // Torna a janela visível
        setVisible(true);
    }
    
    /**
     * Trata os eventos dos botões
     * @param e O evento de ação
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object fonte = e.getSource();
        
        try {
            double resultado = 0;
            
            if (fonte == botaoAdicionar) {
                resultado = LogicaCalculadora.adicionar(
                    campoNumero1.getText(),
                    campoNumero2.getText()
                );
            } else if (fonte == botaoSubtrair) {
                resultado = LogicaCalculadora.subtrair(
                    campoNumero1.getText(),
                    campoNumero2.getText()
                );
            } else if (fonte == botaoMultiplicar) {
                resultado = LogicaCalculadora.multiplicar(
                    campoNumero1.getText(),
                    campoNumero2.getText()
                );
            } else if (fonte == botaoDividir) {
                resultado = LogicaCalculadora.dividir(
                    campoNumero1.getText(),
                    campoNumero2.getText()
                );
            } else if (fonte == botaoLimpar) {
                limparCampos();
                return;
            }
            
            // Exibe o resultado formatado
            labelResultado.setText(LogicaCalculadora.formatarResultado(resultado));
            
        } catch (CalculadoraException ex) {
            // Exibe mensagem de erro amigável em uma janela de diálogo
            JOptionPane.showMessageDialog(
                this,
                ex.getMessage(),
                "Erro na Calculadora",
                JOptionPane.ERROR_MESSAGE
            );
            
            // Reseta o resultado
            labelResultado.setText("0");
        }
    }
    
    /**
     * Limpa todos os campos de entrada e o resultado
     */
    private void limparCampos() {
        campoNumero1.setText("");
        campoNumero2.setText("");
        labelResultado.setText("0");
        campoNumero1.requestFocus();
    }
    
    /**
     * Método principal para executar a calculadora
     * @param args Argumentos da linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Executa a interface gráfica na thread de eventos
        SwingUtilities.invokeLater(() -> new CalculadoraGUI());
    }
}
