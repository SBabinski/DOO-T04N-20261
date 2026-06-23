package org.example;

import org.example.exception.WeatherException;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.ConnectException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;

public class Clima extends JFrame {

    private static final String CHAVE_API =
            "CNBAMR5J7YDPAEEWZFGDF45HF";

    private static final Color ROSA =
            new Color(255, 182, 193);

    private static final Color FUNDO =
            new Color(255, 245, 248);

    private JTextField campoCidade;

    private JLabel rotuloCidade;
    private JLabel rotuloIconeClima;
    private JLabel rotuloTemperatura;
    private JLabel rotuloTemperaturaMaxima;
    private JLabel rotuloTemperaturaMinima;
    private JLabel rotuloUmidade;
    private JLabel rotuloCondicaoTempo;
    private JLabel rotuloPrecipitacao;
    private JLabel rotuloVelocidadeVento;
    private JLabel rotuloDirecaoVento;

    public Clima() {

        configurarJanela();
        criarComponentes();
    }

    private void configurarJanela() {

        setTitle("Trabalho T04 - Leticia");
        setSize(650, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(FUNDO);
    }

    private void criarComponentes() {

        JPanel painelPrincipal = new JPanel(new BorderLayout(20, 20));
        painelPrincipal.setBorder(new EmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(FUNDO);

        JPanel painelBusca = new JPanel(new BorderLayout(10, 10));
        painelBusca.setBackground(Color.WHITE);
        painelBusca.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ROSA, 2),
                new EmptyBorder(15, 15, 15, 15)));

        JLabel titulo = new JLabel(
                "Consulta",
                SwingConstants.CENTER);

        titulo.setFont(
                new Font("Segoe UI",
                        Font.BOLD,
                        24));

        campoCidade = new JTextField();

        JButton botaoBuscar = new JButton("Buscar");
        botaoBuscar.setBackground(ROSA);
        botaoBuscar.setFocusPainted(false);

        JPanel painelEntrada = new JPanel(new BorderLayout(10, 0));
        painelEntrada.setOpaque(false);

        painelEntrada.add(campoCidade, BorderLayout.CENTER);
        painelEntrada.add(botaoBuscar, BorderLayout.EAST);

        painelBusca.add(titulo, BorderLayout.NORTH);
        painelBusca.add(painelEntrada, BorderLayout.CENTER);

        painelPrincipal.add(painelBusca, BorderLayout.NORTH);

        JPanel painelCentro = new JPanel(new BorderLayout(15, 15));
        painelCentro.setOpaque(false);

        JPanel painelCabecalho = new JPanel();
        painelCabecalho.setPreferredSize(new Dimension(0, 120));
        painelCabecalho.setBackground(Color.WHITE);
        painelCabecalho.setLayout(
                new BoxLayout(
                        painelCabecalho,
                        BoxLayout.Y_AXIS));

        painelCabecalho.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                ROSA,
                                2),
                        new EmptyBorder(
                                15,
                                15,
                                15,
                                15)));

        rotuloIconeClima = criarRotuloGrande("");

        rotuloCidade = new JLabel(
                "Nenhuma cidade consultada",
                SwingConstants.CENTER);

        rotuloCidade.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22));

        rotuloCidade.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        painelCabecalho.add(rotuloIconeClima);
        painelCabecalho.add(Box.createVerticalStrut(10));
        painelCabecalho.add(rotuloCidade);

        painelCentro.add(
                painelCabecalho,
                BorderLayout.NORTH);

        JPanel painelGrade = new JPanel(
                new GridLayout(
                        4,
                        2,
                        10,
                        10));

        painelGrade.setOpaque(false);

        rotuloTemperatura =
                criarCartao("Temperatura");

        rotuloUmidade =
                criarCartao("Umidade");

        rotuloTemperaturaMaxima =
                criarCartao("Máxima");

        rotuloTemperaturaMinima =
                criarCartao("Mínima");

        rotuloCondicaoTempo =
                criarCartao("Condição");

        rotuloPrecipitacao =
                criarCartao("Precipitação");

        rotuloVelocidadeVento =
                criarCartao("Vento");

        rotuloDirecaoVento =
                criarCartao("Direção");

        painelGrade.add(rotuloTemperatura);
        painelGrade.add(rotuloUmidade);

        painelGrade.add(rotuloTemperaturaMaxima);
        painelGrade.add(rotuloTemperaturaMinima);

        painelGrade.add(rotuloCondicaoTempo);
        painelGrade.add(rotuloPrecipitacao);

        painelGrade.add(rotuloVelocidadeVento);
        painelGrade.add(rotuloDirecaoVento);

        painelCentro.add(
                painelGrade,
                BorderLayout.CENTER);

        painelPrincipal.add(
                painelCentro,
                BorderLayout.CENTER);

        botaoBuscar.addActionListener(
                evento -> buscarClima());

        add(painelPrincipal);
    }


    private JLabel criarCartao(String texto) {

        JLabel rotulo =
                new JLabel(
                        texto,
                        SwingConstants.CENTER);

        rotulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15));

        rotulo.setOpaque(true);

        rotulo.setBackground(Color.WHITE);

        rotulo.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                ROSA,
                                2),
                        new EmptyBorder(
                                20,
                                10,
                                20,
                                10)));

        return rotulo;
    }


    private JLabel criarRotulo(String texto) {

        JLabel rotulo = new JLabel(texto);

        rotulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        15));

        rotulo.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        return rotulo;
    }

    private JLabel criarRotuloGrande(String texto) {

        JLabel rotulo = new JLabel(texto);

        rotulo.setHorizontalAlignment(SwingConstants.CENTER);
        rotulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        rotulo.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        18)); // tamanho do emoji

        return rotulo;
    }

    private void buscarClima() {

        try {

            String nomeCidade =
                    campoCidade.getText().trim();

            if (nomeCidade.isEmpty()) {

                throw new WeatherException(
                        "Informe uma cidade.");
            }

            String dataAtual =
                    LocalDate.now().toString();

            String enderecoConsulta =
                    "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                            + URLEncoder.encode(
                            nomeCidade,
                            StandardCharsets.UTF_8)
                            + "/"
                            + dataAtual
                            + "?key="
                            + CHAVE_API
                            + "&unitGroup=metric&lang=pt";

            HttpClient clienteHttp =
                    HttpClient.newBuilder()
                            .connectTimeout(
                                    Duration.ofSeconds(10))
                            .build();

            HttpRequest requisicaoHttp =
                    HttpRequest.newBuilder()
                            .uri(
                                    URI.create(
                                            enderecoConsulta))
                            .timeout(
                                    Duration.ofSeconds(15))
                            .GET()
                            .build();

            HttpResponse<String> respostaHttp =
                    clienteHttp.send(
                            requisicaoHttp,
                            HttpResponse.BodyHandlers.ofString());

            if (respostaHttp.statusCode() != 200) {

                throw new WeatherException(
                        "Erro HTTP: "
                                + respostaHttp.statusCode());
            }

            JSONObject dadosClima =
                    new JSONObject(
                            respostaHttp.body());

            JSONObject condicoesAtuais =
                    dadosClima.getJSONObject(
                            "currentConditions");

            JSONArray dias =
                    dadosClima.getJSONArray(
                            "days");

            JSONObject diaAtual =
                    dias.getJSONObject(0);

            rotuloCidade.setText(
                    nomeCidade);

            rotuloTemperatura.setText(
                    String.format(
                            "Temperatura: %.1f °C",
                            condicoesAtuais.optDouble(
                                    "temp")));

            rotuloTemperaturaMaxima.setText(
                    String.format(
                            "Máxima: %.1f °C",
                            diaAtual.optDouble(
                                    "tempmax")));

            rotuloTemperaturaMinima.setText(
                    String.format(
                            "Mínima: %.1f °C",
                            diaAtual.optDouble(
                                    "tempmin")));

            rotuloUmidade.setText(
                    String.format(
                            "Umidade: %.0f%%",
                            condicoesAtuais.optDouble(
                                    "humidity")));

            String descricaoCondicao =
                    condicoesAtuais.optString(
                            "conditions");

            rotuloCondicaoTempo.setText(
                    "Condição: "
                            + descricaoCondicao);

            rotuloPrecipitacao.setText(
                    String.format(
                            "Precipitação: %.1f mm",
                            diaAtual.optDouble(
                                    "precip")));

            rotuloVelocidadeVento.setText(
                    String.format(
                            "Velocidade do vento: %.1f km/h",
                            condicoesAtuais.optDouble(
                                    "windspeed")));

            rotuloDirecaoVento.setText(
                    String.format(
                            "Direção do vento: %.0f°",
                            condicoesAtuais.optDouble(
                                    "winddir")));

            atualizarIconeClima(
                    descricaoCondicao);

        } catch (ConnectException excecao) {

            JOptionPane.showMessageDialog(
                    this,
                    "Sem conexão com a internet.");

        } catch (Exception excecao) {

            JOptionPane.showMessageDialog(
                    this,
                    excecao.getMessage(),
                    "Ops!",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void atualizarIconeClima(String descricaoCondicao) {

        String emoji = "☀️";

        String descricaoNormalizada =
                descricaoCondicao.toLowerCase();

        if (descricaoNormalizada.contains("rain")
                || descricaoNormalizada.contains("chuva")) {

            emoji = "🌧️";

        } else if (descricaoNormalizada.contains("cloud")
                || descricaoNormalizada.contains("nublado")) {

            emoji = "☁️";

        } else if (descricaoNormalizada.contains("storm")
                || descricaoNormalizada.contains("tempestade")) {

            emoji = "⛈️";

        } else if (descricaoNormalizada.contains("fog")
                || descricaoNormalizada.contains("neblina")) {

            emoji = "🌫️";
        }

        rotuloIconeClima.setIcon(null);
        rotuloIconeClima.setText(emoji);
    }
}