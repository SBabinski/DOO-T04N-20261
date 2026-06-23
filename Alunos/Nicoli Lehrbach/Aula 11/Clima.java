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
    private static final Color ROSA_DESTAQUE = new Color(255, 105, 180);
    private static final Color ROSA_PASTEL = new Color(255, 240, 245);
    private static final Color FUNDO_SUAVE = new Color(255, 228, 225);
    private static final Color TEXTO_COR = new Color(74, 20, 140);

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
        setTitle("Aplicativo de clima");
        setSize(400, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(FUNDO_SUAVE);
    }

    private void criarComponentes() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        painelPrincipal.setBackground(FUNDO_SUAVE);

        JPanel painelInformacoes = new JPanel();
        painelInformacoes.setBackground(ROSA_PASTEL);
        painelInformacoes.setLayout(new BoxLayout(painelInformacoes, BoxLayout.Y_AXIS));
        painelInformacoes.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(ROSA_DESTAQUE, 2, true),
                        new EmptyBorder(15, 15, 15, 15)));

        rotuloCidade = criarRotuloTitulo("Cidade");
        rotuloIconeClima = criarRotuloGrande("🌸");
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
        painelInformacoes.add(Box.createVerticalStrut(15));
        painelInformacoes.add(rotuloTemperatura);
        painelInformacoes.add(Box.createVerticalStrut(8));
        painelInformacoes.add(rotuloTemperaturaMaxima);
        painelInformacoes.add(rotuloTemperaturaMinima);
        painelInformacoes.add(Box.createVerticalStrut(8));
        painelInformacoes.add(rotuloUmidade);
        painelInformacoes.add(rotuloCondicaoTempo);
        painelInformacoes.add(Box.createVerticalStrut(8));
        painelInformacoes.add(rotuloPrecipitacao);
        painelInformacoes.add(rotuloVelocidadeVento);
        painelInformacoes.add(rotuloDirecaoVento);

        JScrollPane scrollPane = new JScrollPane(painelInformacoes);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        painelPrincipal.add(scrollPane, BorderLayout.CENTER);


        JPanel painelBuscaBaixo = new JPanel();
        painelBuscaBaixo.setLayout(new BoxLayout(painelBuscaBaixo, BoxLayout.Y_AXIS));
        painelBuscaBaixo.setBackground(FUNDO_SUAVE);
        painelBuscaBaixo.setBorder(new EmptyBorder(10, 0, 5, 0));

        JLabel labelCidadePrompt = new JLabel("Digite a cidade: ");
        labelCidadePrompt.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelCidadePrompt.setForeground(TEXTO_COR);
        labelCidadePrompt.setAlignmentX(Component.CENTER_ALIGNMENT);

        campoCidade = new JTextField(15);
        campoCidade.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campoCidade.setMaximumSize(new Dimension(280, 30));
        campoCidade.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton botaoBuscar = new JButton("Buscar cidade (Cascavel não vale, olha pra fora!)");
        botaoBuscar.setBackground(ROSA_DESTAQUE);
        botaoBuscar.setForeground(Color.WHITE);
        botaoBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botaoBuscar.setFocusPainted(false);
        botaoBuscar.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        botaoBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);

        painelBuscaBaixo.add(labelCidadePrompt);
        painelBuscaBaixo.add(Box.createVerticalStrut(8));
        painelBuscaBaixo.add(campoCidade);
        painelBuscaBaixo.add(Box.createVerticalStrut(8));
        painelBuscaBaixo.add(botaoBuscar);

        painelPrincipal.add(painelBuscaBaixo, BorderLayout.SOUTH);

        botaoBuscar.addActionListener(evento -> buscarClima());

        add(painelPrincipal);
    }

    private JLabel criarRotulo(String texto) {
        JLabel rotulo = new JLabel(texto);
        rotulo.setForeground(TEXTO_COR);
        rotulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        return rotulo;
    }

    private JLabel criarRotuloTitulo(String texto) {
        JLabel rotulo = new JLabel(texto);
        rotulo.setForeground(TEXTO_COR);
        rotulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        return rotulo;
    }

    private JLabel criarRotuloGrande(String texto) {
        JLabel rotulo = new JLabel(texto);
        rotulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        return rotulo;
    }

    private void buscarClima() {
        try {
            String nomeCidade = campoCidade.getText().trim();

            if (nomeCidade.isEmpty()) {
                throw new ClimaException("Informe uma cidade! 💕");
            }

            String dataAtual = LocalDate.now().toString();

            String enderecoConsulta =
                    "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                            + URLEncoder.encode(nomeCidade, StandardCharsets.UTF_8)
                            + "/" + dataAtual
                            + "?key=VCKS9QYXHHZL32KN8HZQLH966&unitGroup=metric&lang=pt";

            HttpClient clienteHttp = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            HttpRequest requisicaoHttp = HttpRequest.newBuilder()
                    .uri(URI.create(enderecoConsulta))
                    .timeout(Duration.ofSeconds(15))
                    .GET()
                    .build();

            HttpResponse<String> respostaHttp = clienteHttp.send(
                    requisicaoHttp,
                    HttpResponse.BodyHandlers.ofString());

            if (respostaHttp.statusCode() != 200) {
                throw new ClimaException("Não consegui achar essa cidade. Erro: " + respostaHttp.statusCode());
            }

            JSONObject dadosClima = new JSONObject(respostaHttp.body());
            JSONObject condicoesAtuais = dadosClima.getJSONObject("currentConditions");
            JSONArray dias = dadosClima.getJSONArray("days");
            JSONObject diaAtual = dias.getJSONObject(0);

            rotuloCidade.setText(nomeCidade.substring(0, 1).toUpperCase() + nomeCidade.substring(1));

            rotuloTemperatura.setText(String.format("Temperatura Atual: %.1f °C", condicoesAtuais.optDouble("temp")));
            rotuloTemperaturaMaxima.setText(String.format("Máxima de hoje: %.1f °C", diaAtual.optDouble("tempmax")));
            rotuloTemperaturaMinima.setText(String.format("Mínima de hoje: %.1f °C", diaAtual.optDouble("tempmin")));
            rotuloUmidade.setText(String.format("Umidade do ar: %.0f%%", condicoesAtuais.optDouble("humidity")));

            String descricaoCondicao = condicoesAtuais.optString("conditions");
            rotuloCondicaoTempo.setText("O tempo está: " + descricaoCondicao.toLowerCase());

            rotuloPrecipitacao.setText(String.format("Chance de chuva: %.1f mm", diaAtual.optDouble("precip")));
            rotuloVelocidadeVento.setText(String.format("Vento: %.1f km/h", condicoesAtuais.optDouble("windspeed")));
            rotuloDirecaoVento.setText(String.format("Direção do vento: %.0f°", condicoesAtuais.optDouble("winddir")));

            atualizarIconeClima(descricaoCondicao);

        } catch (ConnectException excecao) {
            JOptionPane.showMessageDialog(this, "Erro na internet...", "Sem Conexão", JOptionPane.ERROR_MESSAGE);
        } catch (Exception excecao) {
            JOptionPane.showMessageDialog(this, excecao.getMessage(), "Opssssssssssss!", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void atualizarIconeClima(String descricaoCondicao) {
        String descricaoNormalizada = descricaoCondicao.toLowerCase();

        if (descricaoNormalizada.contains("rain") || descricaoNormalizada.contains("chuva")) {
            rotuloIconeClima.setText("🌧️");
        } else if (descricaoNormalizada.contains("cloud") || descricaoNormalizada.contains("nublado")) {
            rotuloIconeClima.setText("☁️");
        } else if (descricaoNormalizada.contains("storm") || descricaoNormalizada.contains("tempestade")) {
            rotuloIconeClima.setText("⛈️");
        } else if (descricaoNormalizada.contains("fog") || descricaoNormalizada.contains("neblina")) {
            rotuloIconeClima.setText("🌫️");
        } else {
            rotuloIconeClima.setText("☀️");
        }
    }
}