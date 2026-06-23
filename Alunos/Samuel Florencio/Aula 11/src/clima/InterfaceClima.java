package clima;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;

public class InterfaceClima extends JFrame {

    private JTextField txtCidade;
    private JTextArea txtResultado;

    private ApiClima serviço;

    public InterfaceClima() {

        serviço = new ApiClima();

        setTitle("Consultar Clima");
        setSize(520, 375);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(
                new Color(15, 23, 42));

        criarComponentes();

        setVisible(true);
    }

    private void criarComponentes() {

        JPanel painelSuperior =
                new JPanel(new FlowLayout());
        painelSuperior.setBackground(
                new Color(15, 23, 42));

        JLabel lblCidade =
                new JLabel("Cidade (Cidade, Estado, Pais):");
        lblCidade.setForeground(
                Color.WHITE);
        txtCidade =
                new JTextField(20);

        JButton btnBuscar =
                new JButton("Buscar");
        btnBuscar.setBackground(
                new Color(59, 130, 246));
        btnBuscar.setForeground(
                Color.WHITE);
        painelSuperior.add(lblCidade);
        painelSuperior.add(txtCidade);
        painelSuperior.add(btnBuscar);
        txtResultado =
                new JTextArea();
        txtResultado.setBackground(
                new Color(30, 41, 59));
        txtResultado.setForeground(
                Color.WHITE);
        txtResultado.setEditable(false);
        add(painelSuperior, BorderLayout.NORTH);
        add(
            new JScrollPane(txtResultado),
            BorderLayout.CENTER
        );
        btnBuscar.addActionListener(
                e -> buscarClima());
    }
    private void buscarClima() {
        String cidade =
                txtCidade.getText().trim();
        if (cidade.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Digite uma cidade.");
            return;
        }
        try {
            InfoClima data =
                    serviço.buscarClima(cidade);
            String resultado =
                    "Cidade: " + cidade + "\n\n"
                    + "Temperatura Atual: "
                    + data.getTempAtual() + " °C\n"

                    + "Temperatura Máxima: "
                    + data.getTempMaxima() + " °C\n"

                    + "Temperatura Mínima: "
                    + data.getTempMinima() + " °C\n"

                    + "Umidade: "
                    + data.getUmidade() + "%\n"

                    + "Condição: "
                    + data.getCondicao() + "\n"

                    + "Precipitação: "
                    + data.getPrecipitacao() + " mm\n"

                    + "Velocidade do vento: "
                    + data.getVelocidadeVento() + " km/h\n"

                    + "Direção do vento: "
                    + data.getDirecaoVento();

            txtResultado.setText(resultado);
        } catch (ClimaException e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}