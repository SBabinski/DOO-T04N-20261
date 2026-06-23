package org.example;

import org.example.exception.ClimaException;
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
    private static final Color AZUL_GREMIO =
            new Color(0, 102, 204);

    private static final Color FUNDO =
            new Color(218, 218, 218);

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

        setTitle("Clima do Nessa");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(FUNDO);
    }

    private void criarComponentes() {

        JPanel painelPrincipal =
                new JPanel(new BorderLayout(15, 15));

        painelPrincipal.setBorder(
                new EmptyBorder(20, 20, 20, 20));

        painelPrincipal.setBackground(FUNDO);

        JPanel painelSuperior = new JPanel();
        painelSuperior.setBackground(FUNDO);

        campoCidade = new JTextField(20);

        JButton botaoBuscar = new JButton("Buscar");
        botaoBuscar.setBackground(AZUL_GREMIO);
        botaoBuscar.setFocusPainted(false);

        painelSuperior.add(new JLabel("Cidade: "));
        painelSuperior.add(campoCidade);
        painelSuperior.add(botaoBuscar);

        painelPrincipal.add(
                painelSuperior,
                BorderLayout.NORTH);

        JPanel painelInformacoes = new JPanel();

        painelInformacoes.setBackground(Color.WHITE);
        painelInformacoes.setLayout(
                new BoxLayout(
                        painelInformacoes,
                        BoxLayout.Y_AXIS));

        painelInformacoes.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                AZUL_GREMIO,
                                2),
                        new EmptyBorder(
                                15,
                                15,
                                15,
                                15)));

        rotuloCidade = criarRotulo("Cidade");
        rotuloIconeClima = criarRotuloGrande("");
        rotuloTemperatura = criarRotulo("Temperatura");
        rotuloTemperaturaMaxima = criarRotulo("Máxima");
        rotuloTemperaturaMinima = criarRotulo("Mínima");
        rotuloUmidade = criarRotulo("Umidade");
        rotuloCondicaoTempo = criarRotulo("Condição");
        rotuloPrecipitacao = criarRotulo("Precipitação");
        rotuloVelocidadeVento = criarRotulo("Vento");
        rotuloDirecaoVento = criarRotulo("Direção");

        painelInformacoes.add(rotuloCidade);
        painelInformacoes.add(Box.createVerticalStrut(10));
        painelInformacoes.add(rotuloIconeClima);
        painelInformacoes.add(Box.createVerticalStrut(10));
        painelInformacoes.add(rotuloTemperatura);
        painelInformacoes.add(rotuloTemperaturaMaxima);
        painelInformacoes.add(rotuloTemperaturaMinima);
        painelInformacoes.add(rotuloUmidade);
        painelInformacoes.add(rotuloCondicaoTempo);
        painelInformacoes.add(rotuloPrecipitacao);
        painelInformacoes.add(rotuloVelocidadeVento);
        painelInformacoes.add(rotuloDirecaoVento);

        painelPrincipal.add(
                painelInformacoes,
                BorderLayout.CENTER);

        botaoBuscar.addActionListener(
                evento -> buscarClima());

        add(painelPrincipal);
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

        rotulo.setFont(
                new Font(
                        "Segoe UI Emoji",
                        Font.PLAIN,
                        50));

        rotulo.setAlignmentX(
                Component.CENTER_ALIGNMENT);

        return rotulo;
    }

    private void buscarClima() {

        try {

            String nomeCidade =
                    campoCidade.getText().trim();

            if (nomeCidade.isEmpty()) {

                throw new ClimaException(
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
                            + "VCKS9QYXHHZL32KN8HZQLH966"
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

                throw new ClimaException(
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

    private void atualizarIconeClima(
            String descricaoCondicao) {

        String descricaoNormalizada =
                descricaoCondicao.toLowerCase();

        if (descricaoNormalizada.contains("rain")
                || descricaoNormalizada.contains("chuva")) {

            rotuloIconeClima.setText("🌧️");

        } else if (descricaoNormalizada.contains("cloud")
                || descricaoNormalizada.contains("nublado")) {

            rotuloIconeClima.setText("☁️");

        } else if (descricaoNormalizada.contains("storm")
                || descricaoNormalizada.contains("tempestade")) {

            rotuloIconeClima.setText("⛈️");

        } else if (descricaoNormalizada.contains("fog")
                || descricaoNormalizada.contains("neblina")) {

            rotuloIconeClima.setText("🌫️");

        } else {

            rotuloIconeClima.setText("☀️");
        }
    }
}