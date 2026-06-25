package Objetos;

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

public class TelaClima extends JFrame {

    private JTextField txtCidade;
    private JTextArea areaResultado;

    public TelaClima() {

        setTitle("Consulta de Clima");
        setSize(650, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painelSuperior = new JPanel();

        painelSuperior.add(new JLabel("Cidade:"));

        txtCidade = new JTextField(20);
        painelSuperior.add(txtCidade);

        JButton btnBuscar = new JButton("Buscar");
        painelSuperior.add(btnBuscar);

        add(painelSuperior, BorderLayout.NORTH);

        areaResultado = new JTextArea();
        areaResultado.setFont(new Font("Arial", Font.PLAIN, 16));
        areaResultado.setEditable(false);

        add(new JScrollPane(areaResultado), BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> consultarClima());
    }

    private void consultarClima() {

        String cidade = txtCidade.getText().trim();

        if (cidade.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite uma cidade."
            );

            return;
        }

        try {

            WeatherService service = new WeatherService();

            WeatherData clima = service.buscarClima(cidade);

            areaResultado.setText(
                    "CIDADE: " + cidade + "\n\n" +

                    "Temperatura Atual: "
                            + clima.getTemperaturaAtual() + " °C\n\n" +

                    "Temperatura Máxima: "
                            + clima.getTemperaturaMaxima() + " °C\n\n" +

                    "Temperatura Mínima: "
                            + clima.getTemperaturaMinima() + " °C\n\n" +

                    "Umidade do Ar: "
                            + clima.getUmidade() + "%\n\n" +

                    "Condição do Tempo: "
                            + clima.getCondicao() + "\n\n" +

                    "Precipitação: "
                            + clima.getPrecipitacao() + " mm\n\n" +

                    "Velocidade do Vento: "
                            + clima.getVelocidadeVento() + " km/h\n\n" +

                    "Direção do Vento: "
                            + clima.getDirecaoVento() + "°"
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}