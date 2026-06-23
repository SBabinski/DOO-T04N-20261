import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherApp extends JFrame implements ActionListener {
    private final JTextField cidadeField;
    private final JButton buscarButton;
    private final JPanel infoPanel;
    private final JLabel iconLabel;
    private final JLabel titleLabel;
    private final JLabel detailsLabel;

    public WeatherApp() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ignored) {
        }

        setTitle("Consulta de Clima");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(520, 420);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titulo = new JLabel("Consulta de Clima", JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(6, 0, 6, 0));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel painelEntrada = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel labelCidade = new JLabel("Cidade:");
        labelCidade.setFont(new Font("Arial", Font.PLAIN, 14));
        painelEntrada.add(labelCidade);

        cidadeField = new JTextField(24);
        cidadeField.setFont(new Font("Arial", Font.PLAIN, 14));
        painelEntrada.add(cidadeField);

        buscarButton = new JButton("Buscar Clima");
        buscarButton.setFont(new Font("Arial", Font.BOLD, 14));
        buscarButton.addActionListener(this);
        painelEntrada.add(buscarButton);

        painelPrincipal.add(painelEntrada, BorderLayout.NORTH);

        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        infoPanel.setBackground(Color.white);

        iconLabel = new JLabel("", SwingConstants.CENTER);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        titleLabel = new JLabel("Informe uma cidade e clique em Buscar Clima.", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));

        detailsLabel = new JLabel(" ");
        detailsLabel.setFont(new Font("Consolas", Font.PLAIN, 14));
        detailsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        infoPanel.add(iconLabel);
        infoPanel.add(titleLabel);
        infoPanel.add(detailsLabel);

        JPanel southWrapper = new JPanel(new BorderLayout());
        southWrapper.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        southWrapper.add(infoPanel, BorderLayout.CENTER);
        southWrapper.setBackground(painelPrincipal.getBackground());

        painelPrincipal.add(southWrapper, BorderLayout.CENTER);

        add(painelPrincipal);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buscarButton) {
            buscarClima();
        }
    }

    private void buscarClima() {
        String cidade = cidadeField.getText().trim();
        if (cidade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome da cidade.", "Atenção", JOptionPane.WARNING_MESSAGE);
            cidadeField.requestFocus();
            return;
        }

        buscarButton.setEnabled(false);
        iconLabel.setText("⏳");
        titleLabel.setText("Buscando clima para: " + cidade + "...");
        detailsLabel.setText(" ");

        SwingWorker<WeatherInfo, Void> worker = new SwingWorker<WeatherInfo, Void>() {
            @Override
            protected WeatherInfo doInBackground() throws Exception {
                return WeatherService.fetchWeather(cidade);
            }

            @Override
            protected void done() {
                buscarButton.setEnabled(true);
                try {
                    WeatherInfo info = get();
                    updateWeatherDisplay(info);
                } catch (Exception ex) {
                    titleLabel.setText("Erro ao buscar clima");
                    detailsLabel.setText("" + ex.getMessage());
                    iconLabel.setText("⚠️");
                    JOptionPane.showMessageDialog(WeatherApp.this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        worker.execute();
    }

    private String formatWeather(WeatherInfo info) {
        return "Local: " + info.getLocation() + "\n" +
            "Temperatura atual: " + formatNumber(info.getTemperature()) + " °C\n" +
            "Máxima do dia: " + formatNumber(info.getTempMax()) + " °C\n" +
            "Mínima do dia: " + formatNumber(info.getTempMin()) + " °C\n" +
            "Umidade: " + formatNumber(info.getHumidity()) + " %\n" +
            "Condição: " + info.getCondition() + "\n" +
            "Precipitação: " + formatNumber(info.getPrecipitation()) + " mm\n" +
            "Velocidade do vento: " + formatNumber(info.getWindSpeed()) + " km/h\n" +
            "Direção do vento: " + formatWindDirection(info.getWindDirection()) + " (" + formatNumber(info.getWindDirection()) + "°)\n";
    }

    private String formatNumber(double valor) {
        return String.format("%.1f", valor);
    }

    private String formatWindDirection(double degrees) {
        String[] compass = {"Norte", "Nordeste", "Leste", "Sudeste", "Sul", "Sudoeste", "Oeste", "Noroeste"};
        int index = (int) Math.round(((degrees % 360) / 45));
        if (index == 8) {
            index = 0;
        }
        return compass[index];
    }

    private void updateWeatherDisplay(WeatherInfo info) {
        String icon = getConditionIcon(info.getCondition());
        iconLabel.setText(icon);
        titleLabel.setText(info.getLocation() + " — " + formatNumber(info.getTemperature()) + " °C");

        String details = "<html>" +
            "Máx: " + formatNumber(info.getTempMax()) + " °C &nbsp;&nbsp; Mín: " + formatNumber(info.getTempMin()) + " °C<br>" +
            "Umidade: " + formatNumber(info.getHumidity()) + " % &nbsp;&nbsp; Precip: " + formatNumber(info.getPrecipitation()) + " mm<br>" +
            "Vento: " + formatNumber(info.getWindSpeed()) + " km/h (" + formatWindDirection(info.getWindDirection()) + ")<br>" +
            "Condição: " + info.getCondition() +
            "</html>";

        detailsLabel.setText(details);
    }

    private String getConditionIcon(String cond) {
        if (cond == null) return "🌤️";
        String c = cond.toLowerCase();
        if (c.contains("clear") || c.contains("sun")) return "☀️";
        if (c.contains("cloud")) return "⛅";
        if (c.contains("rain") || c.contains("shower") || c.contains("precip")) return "🌧️";
        if (c.contains("thunder") || c.contains("storm")) return "⛈️";
        if (c.contains("snow") || c.contains("sleet")) return "❄️";
        if (c.contains("fog") || c.contains("mist") || c.contains("haze")) return "🌫️";
        return "🌤️";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WeatherApp::new);
    }
}
