package fag;

import fag.objetos.DadosClima;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class TelaClima extends JFrame {

    private JTextField txtCidade;
    private JButton btnConsultar;
    private JLabel lblStatus;
    private JLabel lblCidade;
    private JLabel lblTemperatura;
    private JLabel lblMinima;
    private JLabel lblMaxima;
    private JLabel lblUmidade;
    private JLabel lblCondicao;
    private JLabel lblPrecipitacao;
    private JLabel lblDirecaoVento;
    private JLabel lblVelocidadeVento;

    public TelaClima() {
        setTitle("Aplicativo Clima/Tempo");
        setSize(620, 640);
        setMinimumSize(new Dimension(520, 560));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        configurarAparencia();
        montarTela();
    }

    private void configurarAparencia() {
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 14));
        UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 14));
    }

    private void montarTela() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(16, 16));
        painelPrincipal.setBackground(new Color(239, 246, 252));
        painelPrincipal.setBorder(new EmptyBorder(22, 24, 22, 24));

        painelPrincipal.add(criarPainelTopo(), BorderLayout.NORTH);
        painelPrincipal.add(criarPainelDados(), BorderLayout.CENTER);

        lblStatus = new JLabel("Aguardando consulta.");
        lblStatus.setForeground(new Color(72, 84, 99));
        painelPrincipal.add(lblStatus, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    private JPanel criarPainelTopo() {
        JPanel painelTopo = new JPanel(new BorderLayout(10, 14));
        painelTopo.setOpaque(false);

        JLabel titulo = new JLabel("Aplicativo Clima/Tempo");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(new Color(27, 44, 61));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel explicacao = new JLabel(
                "Digite no formato: Cidade, Estado, Pais. Exemplo: Curitiba, PR, Brasil.");
        explicacao.setForeground(new Color(72, 84, 99));
        explicacao.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel painelTexto = new JPanel(new BorderLayout(0, 6));
        painelTexto.setOpaque(false);
        painelTexto.add(titulo, BorderLayout.NORTH);
        painelTexto.add(explicacao, BorderLayout.CENTER);

        txtCidade = new JTextField();
        txtCidade.setPreferredSize(new Dimension(260, 34));
        txtCidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultar();
            }
        });

        btnConsultar = new JButton("Consultar");
        btnConsultar.setPreferredSize(new Dimension(120, 34));
        btnConsultar.setBackground(new Color(36, 105, 170));
        btnConsultar.setForeground(Color.WHITE);
        btnConsultar.setFocusPainted(false);
        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultar();
            }
        });

        JPanel painelBusca = new JPanel(new BorderLayout(8, 0));
        painelBusca.setOpaque(false);
        painelBusca.add(txtCidade, BorderLayout.CENTER);
        painelBusca.add(btnConsultar, BorderLayout.EAST);

        painelTopo.add(painelTexto, BorderLayout.NORTH);
        painelTopo.add(painelBusca, BorderLayout.SOUTH);

        return painelTopo;
    }

    private JPanel criarPainelDados() {
        JPanel painelDados = new JPanel(new GridBagLayout());
        painelDados.setBackground(Color.WHITE);
        painelDados.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(212, 224, 236)),
                new EmptyBorder(18, 20, 18, 20)));

        lblCidade = criarValor();
        lblTemperatura = criarValor();
        lblMinima = criarValor();
        lblMaxima = criarValor();
        lblUmidade = criarValor();
        lblCondicao = criarValor();
        lblPrecipitacao = criarValor();
        lblDirecaoVento = criarValor();
        lblVelocidadeVento = criarValor();

        int linha = 0;
        adicionarLinha(painelDados, linha++, "Cidade requisitada:", lblCidade);
        adicionarLinha(painelDados, linha++, "Temperatura atual:", lblTemperatura);
        adicionarLinha(painelDados, linha++, "Minima do dia:", lblMinima);
        adicionarLinha(painelDados, linha++, "Maxima do dia:", lblMaxima);
        adicionarLinha(painelDados, linha++, "Umidade do ar:", lblUmidade);
        adicionarLinha(painelDados, linha++, "Condicao do tempo:", lblCondicao);
        adicionarLinha(painelDados, linha++, "Precipitacao:", lblPrecipitacao);
        adicionarLinha(painelDados, linha++, "Direcao do vento:", lblDirecaoVento);
        adicionarLinha(painelDados, linha, "Velocidade do vento:", lblVelocidadeVento);

        limparInformacoes();

        return painelDados;
    }

    private JLabel criarValor() {
        JLabel valor = new JLabel("-");
        valor.setFont(new Font("Segoe UI", Font.BOLD, 15));
        valor.setForeground(new Color(27, 44, 61));
        return valor;
    }

    private void adicionarLinha(JPanel painel, int linha, String nome, JLabel valor) {
        GridBagConstraints posicaoNome = new GridBagConstraints();
        posicaoNome.gridx = 0;
        posicaoNome.gridy = linha;
        posicaoNome.anchor = GridBagConstraints.WEST;
        posicaoNome.insets = new Insets(7, 0, 7, 12);

        JLabel lblNome = new JLabel(nome);
        lblNome.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblNome.setForeground(new Color(72, 84, 99));
        painel.add(lblNome, posicaoNome);

        GridBagConstraints posicaoValor = new GridBagConstraints();
        posicaoValor.gridx = 1;
        posicaoValor.gridy = linha;
        posicaoValor.weightx = 1;
        posicaoValor.fill = GridBagConstraints.HORIZONTAL;
        posicaoValor.anchor = GridBagConstraints.WEST;
        posicaoValor.insets = new Insets(7, 0, 7, 0);
        painel.add(valor, posicaoValor);
    }

    private void consultar() {
        String cidade = txtCidade.getText().trim();

        if (cidade.isBlank()) {
            mostrarErro("Informe uma cidade para consultar.");
            return;
        }

        if (!formatoLocalValido(cidade)) {
            mostrarErro("Digite no formato: cidade, estado, pais. Exemplo: Cascavel, PR, Brasil.");
            return;
        }

        limparInformacoes();
        btnConsultar.setEnabled(false);
        lblStatus.setText("Consultando clima de " + cidade + "...");

        SwingWorker<DadosClima, Void> worker = new SwingWorker<>() {
            @Override
            protected DadosClima doInBackground() throws Exception {
                ClimaService service = new ClimaService();
                return service.consultarCidade(cidade);
            }

            @Override
            protected void done() {
                try {
                    DadosClima dados = get();
                    exibirDados(dados);
                    lblStatus.setText("Consulta concluida.");
                } catch (Exception ex) {
                    lblStatus.setText("Nao foi possivel realizar a consulta.");
                    mostrarErro(mensagemErro(ex));
                } finally {
                    btnConsultar.setEnabled(true);
                }
            }
        };

        worker.execute();
    }

    private void exibirDados(DadosClima dados) {
        lblCidade.setText(dados.getCidade());
        lblTemperatura.setText(String.format("%.1f C", dados.getTemperaturaAtual()));
        lblMinima.setText(String.format("%.1f C", dados.getTemperaturaMinima()));
        lblMaxima.setText(String.format("%.1f C", dados.getTemperaturaMaxima()));
        lblUmidade.setText(String.format("%.0f%%", dados.getUmidade()));
        lblCondicao.setText(dados.getCondicao());
        lblPrecipitacao.setText(String.format("%.1f mm", dados.getPrecipitacao()));
        lblDirecaoVento.setText(dados.direcaoVentoTexto());
        lblVelocidadeVento.setText(String.format("%.1f km/h", dados.getVelocidadeVento()));
    }

    private void limparInformacoes() {
        lblCidade.setText("-");
        lblTemperatura.setText("-");
        lblMinima.setText("-");
        lblMaxima.setText("-");
        lblUmidade.setText("-");
        lblCondicao.setText("-");
        lblPrecipitacao.setText("-");
        lblDirecaoVento.setText("-");
        lblVelocidadeVento.setText("-");
    }

    private String mensagemErro(Exception ex) {
        Throwable causa = ex.getCause();

        if (causa != null && causa.getMessage() != null) {
            return causa.getMessage();
        }

        if (ex.getMessage() != null) {
            return ex.getMessage();
        }

        return "Erro inesperado ao consultar o clima.";
    }

    private boolean formatoLocalValido(String cidade) {
        String[] partes = cidade.split(",");

        if (partes.length < 3) {
            return false;
        }

        for (String parte : partes) {
            if (parte.trim().isBlank()) {
                return false;
            }
        }

        return true;
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(
                this,
                mensagem,
                "Erro",
                JOptionPane.ERROR_MESSAGE);
    }
}
