package view;

import model.WeatherData;
import service.WeatherService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class WeatherApp extends JFrame {

    private JTextField txtCidade;

    private JLabel lblTemperatura;
    private JLabel lblMaxima;
    private JLabel lblMinima;
    private JLabel lblUmidade;
    private JLabel lblCondicao;
    private JLabel lblPrecipitacao;
    private JLabel lblVento;
    private JLabel lblDirecao;
    private JLabel lblIcone;

    private final WeatherService weatherService;

    public WeatherApp() {

        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ignored) {
        }

        weatherService = new WeatherService();

        setTitle("Trabalho JAVA T04N - CONSULTA CLIMA");
        setSize(700, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel painelPrincipal = new JPanel(new BorderLayout(15, 15));
        painelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        painelPrincipal.setBackground(new Color(240, 248, 255));

        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setOpaque(false);

        JLabel titulo = new JLabel("Trabalho JAVA T04N - CONSULTA CLIMA");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));

        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBusca.setOpaque(false);

        txtCidade = new JTextField(20);
        txtCidade.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        painelBusca.add(new JLabel("Cidade:"));
        painelBusca.add(txtCidade);
        painelBusca.add(btnBuscar);

        painelTopo.add(titulo, BorderLayout.NORTH);
        painelTopo.add(painelBusca, BorderLayout.CENTER);

        JPanel painelCentro = new JPanel(new BorderLayout(20, 20));
        painelCentro.setOpaque(false);

        JPanel painelIcone = new JPanel(new BorderLayout());
        painelIcone.setOpaque(false);

        lblIcone = new JLabel();
        lblIcone.setHorizontalAlignment(SwingConstants.CENTER);

        painelIcone.add(lblIcone, BorderLayout.CENTER);

        JPanel painelInfo = new JPanel(new GridLayout(8, 1, 5, 5));
        painelInfo.setBackground(Color.WHITE);

        Font fonteInfo = new Font("Segoe UI", Font.PLAIN, 16);

        lblTemperatura = new JLabel("Temperatura Atual:");
        lblMaxima = new JLabel("Máxima:");
        lblMinima = new JLabel("Mínima:");
        lblUmidade = new JLabel("Umidade:");
        lblCondicao = new JLabel("Condição:");
        lblPrecipitacao = new JLabel("Precipitação:");
        lblVento = new JLabel("Velocidade do Vento:");
        lblDirecao = new JLabel("Direção do Vento:");

        JLabel[] labels = {
                lblTemperatura,
                lblMaxima,
                lblMinima,
                lblUmidade,
                lblCondicao,
                lblPrecipitacao,
                lblVento,
                lblDirecao
        };

        for (JLabel label : labels) {
            label.setFont(fonteInfo);
            painelInfo.add(label);
        }

        painelCentro.add(painelIcone, BorderLayout.WEST);
        painelCentro.add(painelInfo, BorderLayout.CENTER);

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);
        painelPrincipal.add(painelCentro, BorderLayout.CENTER);

        add(painelPrincipal);

        btnBuscar.addActionListener(e -> buscarClima());
    }

    private void buscarClima() {

        String cidade = txtCidade.getText().trim();

        if (cidade.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Informe uma cidade.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            WeatherData clima = weatherService.buscarClima(cidade);

            lblTemperatura.setText(
                    "Temperatura Atual: "
                            + clima.getTemperaturaAtual()
                            + " °C"
            );

            lblMaxima.setText(
                    "Máxima: "
                            + clima.getTemperaturaMaxima()
                            + " °C"
            );

            lblMinima.setText(
                    "Mínima: "
                            + clima.getTemperaturaMinima()
                            + " °C"
            );

            lblUmidade.setText(
                    "Umidade: "
                            + clima.getUmidade()
                            + "%"
            );

            lblCondicao.setText(
                    "Condição: "
                            + clima.getCondicao()
            );

            lblPrecipitacao.setText(
                    "Precipitação: "
                            + clima.getPrecipitacao()
                            + " mm"
            );

            lblVento.setText(
                    "Velocidade do Vento: "
                            + clima.getVelocidadeVento()
                            + " km/h"
            );

            lblDirecao.setText(
                    "Direção do Vento: "
                            + clima.getDirecaoVento()
                            + "°"
            );

            atualizarIcone(clima.getCondicao());

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void atualizarIcone(String condicao) {

        String texto = condicao.toLowerCase();

        String caminho;

        if (texto.contains("rain")) {

            caminho = "/icones/chuva.png";

        } else if (texto.contains("cloud")) {

            caminho = "/icones/nublado.png";

        } else if (texto.contains("storm")) {

            caminho = "/icones/tempestade.png";

        } else if (texto.contains("fog")) {

            caminho = "/icones/neblina.png";

        } else {

            caminho = "/icones/sol.png";
        }

        ImageIcon icone = carregarIcone(caminho);

        if (icone != null) {

            lblIcone.setIcon(icone);
            lblIcone.setText("");

        } else {

            lblIcone.setIcon(null);
            lblIcone.setText("Imagem não encontrada");
        }
    }

    private ImageIcon carregarIcone(String caminho) {

        URL url = getClass().getResource(caminho);

        if (url == null) {

            System.out.println("Imagem não encontrada: " + caminho);
            return null;
        }

        Image imagem = new ImageIcon(url)
                .getImage()
                .getScaledInstance(
                        140,
                        140,
                        Image.SCALE_SMOOTH
                );

        return new ImageIcon(imagem);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            WeatherApp app = new WeatherApp();
            app.setVisible(true);

        });
    }
}