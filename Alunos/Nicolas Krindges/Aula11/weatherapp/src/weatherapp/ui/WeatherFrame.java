package weatherapp.ui;

import weatherapp.exception.WeatherApiException;
import weatherapp.model.WeatherData;
import weatherapp.service.WeatherService;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

/**
 * Janela principal da aplicacao de clima.
 * Permite ao usuario digitar uma cidade e visualizar as condicoes
 * climaticas atuais retornadas pela API da Visual Crossing.
 */
public class WeatherFrame extends JFrame {

    private final WeatherService weatherService;

    private final JTextField campoCidade;
    private final JButton botaoBuscar;
    private final JLabel labelStatus;

    private final JLabel valorTemperaturaAtual;
    private final JLabel valorMaxMin;
    private final JLabel valorUmidade;
    private final JLabel valorCondicao;
    private final JLabel valorPrecipitacao;
    private final JLabel valorVento;

    public WeatherFrame(WeatherService weatherService) {
        super("Previsão do Tempo — Visual Crossing");
        this.weatherService = weatherService;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 460);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        campoCidade = new JTextField();
        botaoBuscar = new JButton("Buscar");
        labelStatus = new JLabel(" ");
        labelStatus.setForeground(Color.RED);

        valorTemperaturaAtual = criarLabelValor();
        valorMaxMin = criarLabelValor();
        valorUmidade = criarLabelValor();
        valorCondicao = criarLabelValor();
        valorPrecipitacao = criarLabelValor();
        valorVento = criarLabelValor();

        painelPrincipal.add(montarPainelBusca(), BorderLayout.NORTH);
        painelPrincipal.add(montarPainelResultado(), BorderLayout.CENTER);
        painelPrincipal.add(labelStatus, BorderLayout.SOUTH);

        setContentPane(painelPrincipal);

        botaoBuscar.addActionListener(e -> buscarClima());
        campoCidade.addActionListener(e -> buscarClima());
    }

    private JPanel montarPainelBusca() {
        JPanel painel = new JPanel(new BorderLayout(8, 0));
        painel.add(new JLabel("Cidade:"), BorderLayout.WEST);
        painel.add(campoCidade, BorderLayout.CENTER);
        painel.add(botaoBuscar, BorderLayout.EAST);
        return painel;
    }

    private JPanel montarPainelResultado() {
        JPanel painel = new JPanel(new GridLayout(6, 2, 8, 12));
        painel.setBorder(BorderFactory.createTitledBorder("Condições atuais"));

        painel.add(new JLabel("Temperatura atual:"));
        painel.add(valorTemperaturaAtual);

        painel.add(new JLabel("Máxima / Mínima:"));
        painel.add(valorMaxMin);

        painel.add(new JLabel("Umidade do ar:"));
        painel.add(valorUmidade);

        painel.add(new JLabel("Condição:"));
        painel.add(valorCondicao);

        painel.add(new JLabel("Precipitação:"));
        painel.add(valorPrecipitacao);

        painel.add(new JLabel("Vento:"));
        painel.add(valorVento);

        return painel;
    }

    private JLabel criarLabelValor() {
        JLabel label = new JLabel("—");
        label.setFont(label.getFont().deriveFont(Font.BOLD));
        return label;
    }

    private void buscarClima() {
        String cidade = campoCidade.getText().trim();
        if (cidade.isEmpty()) {
            mostrarStatus("Digite o nome de uma cidade.", true);
            return;
        }

        alternarCarregando(true);

        // SwingWorker evita travar a interface grafica durante a chamada HTTP
        SwingWorker<WeatherData, Void> worker = new SwingWorker<>() {
            @Override
            protected WeatherData doInBackground() throws Exception {
                return weatherService.buscarClimaAtual(cidade);
            }

            @Override
            protected void done() {
                alternarCarregando(false);
                try {
                    WeatherData dados = get();
                    exibirResultado(dados);
                    mostrarStatus(" ", false);
                } catch (Exception e) {
                    Throwable causa = e.getCause() != null ? e.getCause() : e;
                    tratarErro(causa);
                }
            }
        };
        worker.execute();
    }

    private void tratarErro(Throwable causa) {
        limparResultado();
        if (causa instanceof WeatherApiException) {
            mostrarStatus(causa.getMessage(), true);
        } else {
            mostrarStatus("Erro inesperado: " + causa.getMessage(), true);
        }
    }

    private void exibirResultado(WeatherData dados) {
        valorTemperaturaAtual.setText(formatarTemperatura(dados.getTemperaturaAtual()));
        valorMaxMin.setText(formatarTemperatura(dados.getTemperaturaMaxima())
                + " / " + formatarTemperatura(dados.getTemperaturaMinima()));
        valorUmidade.setText(String.format(Locale.forLanguageTag("pt-BR"), "%.0f%%", dados.getUmidade()));
        valorCondicao.setText(dados.getCondicao());
        valorPrecipitacao.setText(String.format(Locale.forLanguageTag("pt-BR"), "%.1f mm", dados.getPrecipitacao()));
        valorVento.setText(String.format(Locale.forLanguageTag("pt-BR"), "%.1f km/h (%s)",
                dados.getVelocidadeVento(), dados.getDirecaoVentoCardinal()));
    }

    private void limparResultado() {
        valorTemperaturaAtual.setText("—");
        valorMaxMin.setText("—");
        valorUmidade.setText("—");
        valorCondicao.setText("—");
        valorPrecipitacao.setText("—");
        valorVento.setText("—");
    }

    private String formatarTemperatura(double temperatura) {
        return String.format(Locale.forLanguageTag("pt-BR"), "%.1f °C", temperatura);
    }

    private void mostrarStatus(String mensagem, boolean erro) {
        labelStatus.setForeground(erro ? Color.RED : Color.DARK_GRAY);
        labelStatus.setText(mensagem);
    }

    private void alternarCarregando(boolean carregando) {
        botaoBuscar.setEnabled(!carregando);
        campoCidade.setEnabled(!carregando);
        botaoBuscar.setText(carregando ? "Buscando..." : "Buscar");
    }
}
