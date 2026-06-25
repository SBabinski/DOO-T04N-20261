package com.weather;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class WeatherApp extends JFrame {

    // ── Cores e fontes ──────────────────────────────────────────────────────────
    private static final Color BG          = new Color(236, 244, 255);
    private static final Color ACCENT      = new Color(25, 118, 210);
    private static final Color CARD_BG     = Color.WHITE;
    private static final Color CARD_BORDER = new Color(200, 210, 230);
    private static final Color TITLE_FG    = new Color(40, 40, 60);
    private static final Color SUBTITLE_FG = new Color(100, 110, 130);
    private static final Font  FONT_TITLE  = new Font("Segoe UI", Font.BOLD, 26);
    private static final Font  FONT_LABEL  = new Font("Segoe UI", Font.BOLD, 11);
    private static final Font  FONT_VALUE  = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font  FONT_INPUT  = new Font("Segoe UI", Font.PLAIN, 14);

    // ── Campos de entrada ────────────────────────────────────────────────────────
    private JTextField cityField;
    private String     apiKey = "";
    private JButton    searchButton;
    private JLabel     statusLabel;

    // ── Valores dos cards ────────────────────────────────────────────────────────
    private JLabel cityValue;
    private JLabel currentTempValue;
    private JLabel maxTempValue;
    private JLabel minTempValue;
    private JLabel humidityValue;
    private JLabel conditionValue;
    private JLabel precipValue;
    private JLabel windSpeedValue;
    private JLabel windDirValue;

    // ────────────────────────────────────────────────────────────────────────────

    public WeatherApp() {
        super("Consulta de Clima — Visual Crossing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(780, 620);
        setMinimumSize(new Dimension(680, 560));
        setLocationRelativeTo(null);
        buildUI();
        loadApiKeyFromConfig();
    }

    private void loadApiKeyFromConfig() {
        File configFile = new File("config.properties");
        if (!configFile.exists()) return;
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(configFile)) {
            props.load(fis);
            apiKey = props.getProperty("weather.api.key", "").trim();
        } catch (IOException ignored) { }
    }

    // ── Construção da interface ──────────────────────────────────────────────────

    private void buildUI() {
        JPanel root = new JPanel(new BorderLayout(0, 12));
        root.setBackground(BG);
        root.setBorder(new EmptyBorder(18, 18, 18, 18));

        root.add(buildHeader(), BorderLayout.NORTH);
        root.add(buildCenter(), BorderLayout.CENTER);
        root.add(buildStatusBar(), BorderLayout.SOUTH);

        add(root);
    }

    private JPanel buildHeader() {
        JLabel title = new JLabel("Consulta de Clima", SwingConstants.CENTER);
        title.setFont(FONT_TITLE);
        title.setForeground(ACCENT);

        JLabel subtitle = new JLabel("Powered by Visual Crossing Weather API", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        subtitle.setForeground(SUBTITLE_FG);

        JPanel panel = new JPanel(new BorderLayout(2, 2));
        panel.setBackground(BG);
        panel.add(title,    BorderLayout.CENTER);
        panel.add(subtitle, BorderLayout.SOUTH);
        panel.setBorder(new EmptyBorder(0, 0, 8, 0));
        return panel;
    }

    private JPanel buildCenter() {
        JPanel panel = new JPanel(new BorderLayout(0, 12));
        panel.setBackground(BG);
        panel.add(buildInputPanel(),   BorderLayout.NORTH);
        panel.add(buildResultsGrid(),  BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildInputPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BG);
        panel.setBorder(titledBorder("Configuração"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Cidade
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0;
        panel.add(label("Cidade:"), gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        cityField = inputField("Ex: São Paulo, BR");
        cityField.addActionListener(this::onSearch);
        panel.add(cityField, gbc);

        // Botão
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        searchButton = buildButton("Buscar Clima");
        searchButton.addActionListener(this::onSearch);
        panel.add(searchButton, gbc);

        return panel;
    }

    private JPanel buildResultsGrid() {
        // Inicializa os JLabels de valor
        cityValue       = valueLabel("—");
        currentTempValue = valueLabel("—");
        maxTempValue    = valueLabel("—");
        minTempValue    = valueLabel("—");
        humidityValue   = valueLabel("—");
        conditionValue  = valueLabel("—");
        precipValue     = valueLabel("—");
        windSpeedValue  = valueLabel("—");
        windDirValue    = valueLabel("—");

        JPanel grid = new JPanel(new GridLayout(3, 3, 10, 10));
        grid.setBackground(BG);
        grid.setBorder(titledBorder("Resultados"));

        grid.add(card("📍 Localidade",           cityValue));
        grid.add(card("🌡 Temperatura Atual",     currentTempValue));
        grid.add(card("🔺 Temp. Máxima",          maxTempValue));
        grid.add(card("🔻 Temp. Mínima",          minTempValue));
        grid.add(card("💧 Humidade do Ar",        humidityValue));
        grid.add(card("🌤 Condição do Tempo",     conditionValue));
        grid.add(card("🌧 Precipitação",          precipValue));
        grid.add(card("💨 Velocidade do Vento",   windSpeedValue));
        grid.add(card("🧭 Direção do Vento",      windDirValue));

        return grid;
    }

    private JLabel buildStatusBar() {
        statusLabel = new JLabel("Insira o nome da cidade e clique em Buscar.", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        statusLabel.setForeground(SUBTITLE_FG);
        return statusLabel;
    }

    // ── Helpers de construção ────────────────────────────────────────────────────

    private JPanel card(String title, JLabel valueLabel) {
        JPanel card = new JPanel(new BorderLayout(4, 4));
        card.setBackground(CARD_BG);
        card.setBorder(new CompoundBorder(
                new LineBorder(CARD_BORDER, 1, true),
                new EmptyBorder(10, 12, 10, 12)));

        JLabel titleLbl = new JLabel(title);
        titleLbl.setFont(FONT_LABEL);
        titleLbl.setForeground(SUBTITLE_FG);

        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);

        card.add(titleLbl,   BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        return card;
    }

    private JLabel valueLabel(String text) {
        JLabel lbl = new JLabel(text, SwingConstants.CENTER);
        lbl.setFont(FONT_VALUE);
        lbl.setForeground(TITLE_FG);
        return lbl;
    }

    private JLabel label(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lbl.setForeground(TITLE_FG);
        return lbl;
    }

    private JTextField inputField(String placeholder) {
        JTextField field = new JTextField(22);
        field.setFont(FONT_INPUT);
        field.setToolTipText(placeholder);
        field.setBorder(new CompoundBorder(
                new LineBorder(CARD_BORDER, 1, true),
                new EmptyBorder(4, 8, 4, 8)));
        return field;
    }

    private JButton buildButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(ACCENT);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(new CompoundBorder(
                new LineBorder(ACCENT, 1, true),
                new EmptyBorder(8, 24, 8, 24)));
        return btn;
    }

    private TitledBorder titledBorder(String title) {
        return BorderFactory.createTitledBorder(
                new LineBorder(ACCENT, 1, true),
                " " + title + " ",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12),
                ACCENT);
    }

    // ── Lógica de busca ──────────────────────────────────────────────────────────

    private void onSearch(ActionEvent e) {
        String city = cityField.getText().trim();

        if (city.isEmpty()) {
            showStatus("⚠ Informe o nome da cidade.", Color.ORANGE.darker());
            return;
        }
        if (apiKey.isEmpty()) {
            showStatus("⚠ API Key não configurada. Verifique o arquivo config.properties.", Color.RED.darker());
            return;
        }

        searchButton.setEnabled(false);
        showStatus("Consultando dados para: " + city + " …", SUBTITLE_FG);
        clearCards();

        WeatherService service = new WeatherService(apiKey);

        SwingWorker<WeatherData, Void> worker = new SwingWorker<>() {
            @Override
            protected WeatherData doInBackground() throws Exception {
                return service.fetchWeather(city);
            }

            @Override
            protected void done() {
                searchButton.setEnabled(true);
                try {
                    WeatherData data = get();
                    populateCards(data);
                    showStatus("✔ Dados carregados com sucesso para: " + data.getResolvedAddress(), new Color(27, 128, 62));
                } catch (Exception ex) {
                    Throwable cause = ex.getCause() != null ? ex.getCause() : ex;
                    showStatus("✖ Erro: " + cause.getMessage(), new Color(200, 30, 30));
                }
            }
        };

        worker.execute();
    }

    private void populateCards(WeatherData d) {
        cityValue.setText(d.getResolvedAddress());
        currentTempValue.setText(fmt(d.getCurrentTemp()) + " °C");
        maxTempValue.setText(fmt(d.getMaxTemp()) + " °C");
        minTempValue.setText(fmt(d.getMinTemp()) + " °C");
        humidityValue.setText(fmt(d.getHumidity()) + " %");
        conditionValue.setText(d.getCondition().isEmpty() ? "—" : d.getCondition());
        precipValue.setText(fmt(d.getPrecipitation()) + " mm");
        windSpeedValue.setText(fmt(d.getWindSpeed()) + " km/h");
        windDirValue.setText(WeatherService.degreesToCompass(d.getWindDirection()));

        // Ajusta fonte da condição para caber no card
        conditionValue.setFont(d.getCondition().length() > 18
                ? new Font("Segoe UI", Font.BOLD, 13)
                : FONT_VALUE);
    }

    private void clearCards() {
        for (JLabel lbl : new JLabel[]{cityValue, currentTempValue, maxTempValue,
                minTempValue, humidityValue, conditionValue,
                precipValue, windSpeedValue, windDirValue}) {
            lbl.setText("…");
        }
    }

    private void showStatus(String msg, Color color) {
        statusLabel.setText(msg);
        statusLabel.setForeground(color);
    }

    private static String fmt(double value) {
        if (Double.isNaN(value)) return "—";
        return String.format("%.1f", value);
    }

    // ── Ponto de entrada ─────────────────────────────────────────────────────────

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) { }

        SwingUtilities.invokeLater(() -> new WeatherApp().setVisible(true));
    }
}
