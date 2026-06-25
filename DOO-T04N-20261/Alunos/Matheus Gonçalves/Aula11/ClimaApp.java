package fag;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClimaApp extends JFrame {

    private JTextField txtCidade;
    private JTextArea areaResultado;

    private WeatherService weatherService;

    public ClimaApp() {

        weatherService = new WeatherService();

        setTitle("Consulta de Clima");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        criarTela();

        setVisible(true);
    }

    private void criarTela() {

        JPanel painelSuperior = new JPanel();

        JLabel lblCidade = new JLabel("Cidade:");
        lblCidade.setFont(new Font("Arial", Font.BOLD, 14));

        txtCidade = new JTextField(20);
        txtCidade.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton btnConsultar = new JButton("Consultar Clima");
        btnConsultar.setFont(new Font("Arial", Font.BOLD, 14));

        painelSuperior.add(lblCidade);
        painelSuperior.add(txtCidade);
        painelSuperior.add(btnConsultar);

        areaResultado = new JTextArea();
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Consolas", Font.PLAIN, 15));

        JScrollPane scrollPane = new JScrollPane(areaResultado);

        add(painelSuperior, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        btnConsultar.addActionListener(e -> consultarClima());

        txtCidade.addActionListener(e -> consultarClima());
    }

    private void consultarClima() {

        String cidade = txtCidade.getText().trim();

        if (cidade.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Digite o nome de uma cidade!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {

            WeatherData clima = weatherService.buscarClima(cidade);

            String texto =
                    "Cidade: " + cidade + "\n\n" +

                    "Temperatura Atual: " + clima.getTemperatura() + " °C\n" +
                    "Temperatura Máxima: " + clima.getTemperaturaMaxima() + " °C\n" +
                    "Temperatura Mínima: " + clima.getTemperaturaMinima() + " °C\n\n" +

                    "Umidade: " + clima.getUmidade() + " %\n" +
                    "Condição: " + clima.getCondicao() + "\n" +
                    "Precipitação: " + clima.getPrecipitacao() + " mm\n\n" +

                    "Velocidade do Vento: " + clima.getVelocidadeVento() + " km/h\n" +
                    "Direção do Vento: " + clima.getDirecaoVento() + "°";

            areaResultado.setText(texto);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao consultar clima:\n" + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();
        }
    }
}