import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class ClimaGUI extends JFrame {
    private JTextField campoCidade;
    private JTextArea areaResultado;
    private ClimaService service = new ClimaService();

    public ClimaGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Consulta de Clima Tempo");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel painelTopo = new JPanel(new BorderLayout(5, 5));
        painelTopo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelTopo.add(new JLabel("Digite a Cidade:"), BorderLayout.WEST);
        campoCidade = new JTextField();
        campoCidade.setFont(new Font("Arial", Font.PLAIN, 16));
        painelTopo.add(campoCidade, BorderLayout.CENTER);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 14));
        btnBuscar.addActionListener(e -> acaoBuscar());
        painelTopo.add(btnBuscar, BorderLayout.EAST);
        add(painelTopo, BorderLayout.NORTH);
        
        areaResultado = new JTextArea("Aguardando consulta...\n");
        areaResultado.setEditable(false);
        areaResultado.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaResultado.setMargin(new Insets(10, 10, 10, 10));
        
        JScrollPane scroll = new JScrollPane(areaResultado);
        add(scroll, BorderLayout.CENTER);
    }

    private void acaoBuscar() {
        String cidade = campoCidade.getText();
        
        if (cidade.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite o nome de uma cidade.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        areaResultado.setText("Buscando informações para " + cidade + "...\n");
        
        Clima dados = service.buscarClima(cidade);
        
        if (dados == null) {
            areaResultado.setText("Falha na consulta.");
            JOptionPane.showMessageDialog(this, "Não foi possível buscar os dados.\nVerifique o nome da cidade ou a sua conexão.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== Clima atual em ").append(cidade).append(" ===\n\n");
        sb.append("Condição: ").append(traduzirCondicao(dados.getCondicao())).append("\n");
        sb.append("Temperatura Atual: ").append(dados.getTemperaturaAtual()).append("°C\n");
        sb.append("Máx do Dia: ").append(dados.getTemperaturaMaxima()).append("°C | Mín: ").append(dados.getTemperaturaMinima()).append("°C\n");
        sb.append("Umidade: ").append(dados.getUmidade()).append("%\n");
        sb.append("Precipitação: ").append(dados.getPrecipitacao()).append(" mm\n");
        sb.append("Vento: ").append(dados.getVelocidadeVento()).append(" km/h\n");
        
        areaResultado.setText(sb.toString());
    }

    private String traduzirCondicao(String condicaoIngles) {
        if (condicaoIngles == null) return "Desconhecida";
        String condicao = condicaoIngles.toLowerCase();
        
        if (condicao.contains("rain") || condicao.contains("drizzle")) {
            if (condicao.contains("cloudy") || condicao.contains("overcast")) return "Chuva e Nublado";
            return "Chuva";
        }
        if (condicao.contains("partially cloudy")) return "Parcialmente Nublado";
        if (condicao.contains("overcast")) return "Encoberto (Tempo fechado)";
        if (condicao.contains("cloudy")) return "Nublado";
        if (condicao.contains("clear")) return "Céu Limpo / Ensolarado";
        if (condicao.contains("snow")) return "Neve";
        if (condicao.contains("storm") || condicao.contains("thunder")) return "Tempestade";
        
        return condicaoIngles; 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ClimaGUI().setVisible(true));
    }
}