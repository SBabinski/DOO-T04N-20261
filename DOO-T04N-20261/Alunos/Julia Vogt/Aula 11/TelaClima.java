package climatempo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TelaClima extends JFrame {

    private JTextField txtCidade;

    private JLabel lblAtual;
    private JLabel lblMaxima;
    private JLabel lblMinima;
    private JLabel lblUmidade;
    private JLabel lblCondicao;
    private JLabel lblPrecipitacao;
    private JLabel lblVelocidade;
    private JLabel lblDirecao;

    public TelaClima() {

        setTitle("Consulta Climática");

        setSize(700, 550);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Color fundoJanela =
                new Color(240, 248, 255);

        Color fundoPainel =
                Color.WHITE;

        Font fontePadrao =
                new Font("Segoe UI",
                        Font.PLAIN,
                        15);

        JPanel painelPrincipal =
                new JPanel();

        painelPrincipal.setLayout(
                new BorderLayout(15, 15));

        painelPrincipal.setBackground(
                fundoJanela);

        painelPrincipal.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20));

        JLabel titulo =
                new JLabel(
                        "CONSULTA CLIMÁTICA");

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        titulo.setHorizontalAlignment(
                SwingConstants.CENTER);

        titulo.setForeground(
                new Color(33, 97, 140));

        JPanel painelBusca =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                10));

        painelBusca.setBackground(
                fundoJanela);

        JLabel lblCidade =
                new JLabel("Cidade:");

        lblCidade.setFont(fontePadrao);

        txtCidade =
                new JTextField(25);

        txtCidade.setFont(fontePadrao);

        JButton btnBuscar =
                new JButton("Buscar");

        btnBuscar.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14));

        btnBuscar.setPreferredSize(
                new Dimension(
                        120,
                        35));

        painelBusca.add(lblCidade);
        painelBusca.add(txtCidade);
        painelBusca.add(btnBuscar);

        JPanel painelDados =
                new JPanel();

        painelDados.setLayout(
                new GridLayout(
                        8,
                        1,
                        5,
                        8));

        painelDados.setBackground(
                fundoPainel);

        painelDados.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        200,
                                        200,
                                        200)),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20)));

        lblAtual =
                new JLabel(
                        "Temperatura Atual:");

        lblMaxima =
                new JLabel(
                        "Temperatura Máxima:");

        lblMinima =
                new JLabel(
                        "Temperatura Mínima:");

        lblUmidade =
                new JLabel(
                        "Umidade:");

        lblCondicao =
                new JLabel(
                        "Condição:");

        lblPrecipitacao =
                new JLabel(
                        "Precipitação:");

        lblVelocidade =
                new JLabel(
                        "Velocidade do Vento:");

        lblDirecao =
                new JLabel(
                        "Direção do Vento:");

        JLabel[] labels = {
                lblAtual,
                lblMaxima,
                lblMinima,
                lblUmidade,
                lblCondicao,
                lblPrecipitacao,
                lblVelocidade,
                lblDirecao
        };

        for (JLabel label : labels) {

            label.setFont(fontePadrao);

            painelDados.add(label);
        }

        painelPrincipal.add(
                titulo,
                BorderLayout.NORTH);

        painelPrincipal.add(
                painelBusca,
                BorderLayout.CENTER);

        painelPrincipal.add(
                painelDados,
                BorderLayout.SOUTH);

        add(painelPrincipal);

        btnBuscar.addActionListener(
                e -> consultarClima());

        txtCidade.addActionListener(
                e -> consultarClima());

        setVisible(true);
    }

    private void consultarClima() {

        try {

            String cidade =
                    txtCidade.getText().trim();

            if (cidade.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Informe uma cidade.");

                return;
            }

            ServicoClima servico =
                    new ServicoClima();

            DadosClima dados =
                    servico.buscarClima(cidade);

            lblAtual.setText(
                    "Temperatura Atual: "
                            + String.format(
                            "%.1f",
                            dados.getTemperaturaAtual())
                            + " °C");

            lblMaxima.setText(
                    "Temperatura Máxima: "
                            + String.format(
                            "%.1f",
                            dados.getTemperaturaMaxima())
                            + " °C");

            lblMinima.setText(
                    "Temperatura Mínima: "
                            + String.format(
                            "%.1f",
                            dados.getTemperaturaMinima())
                            + " °C");

            lblUmidade.setText(
                    "Umidade: "
                            + String.format(
                            "%.1f",
                            dados.getUmidade())
                            + "%");

            lblCondicao.setText(
                    "Condição: "
                            + dados.getCondicao());

            lblPrecipitacao.setText(
                    "Precipitação: "
                            + String.format(
                            "%.1f",
                            dados.getPrecipitacao())
                            + " mm");

            lblVelocidade.setText(
                    "Velocidade do Vento: "
                            + String.format(
                            "%.1f",
                            dados.getVelocidadeVento())
                            + " km/h");

            lblDirecao.setText(
                    "Direção do Vento: "
                            + String.format(
                            "%.1f",
                            dados.getDirecaoVento())
                            + "°");

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}