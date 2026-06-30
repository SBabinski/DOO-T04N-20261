import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BuscaPanel extends JPanel {

    private final SerieService service;
    private final JTextField campoBusca;
    private final JButton botaoBuscar;
    private final SerieTableModel tableModel;
    private final JTable tabela;
    private final JLabel statusLabel;

    public BuscaPanel(SerieService service) {
        this.service = service;
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //campo de busca
        JPanel topo = new JPanel(new BorderLayout(6, 6));
        campoBusca = new JTextField();
        campoBusca.setToolTipText("Digite o nome da série e pressione Enter");
        botaoBuscar = new JButton("Buscar");

        topo.add(new JLabel("Nome da série:"), BorderLayout.WEST);
        topo.add(campoBusca, BorderLayout.CENTER);
        topo.add(botaoBuscar, BorderLayout.EAST);
        add(topo, BorderLayout.NORTH);

        //tabela de resultados
        tableModel = new SerieTableModel();
        tabela = new JTable(tableModel);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setRowHeight(24);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        //ações
        JPanel base = new JPanel(new BorderLayout());
        JPanel acoes = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnDetalhes = new JButton("Ver detalhes");
        JButton btnFav = new JButton("+ Favoritos");
        JButton btnAssistida = new JButton("+ Já assistidas");
        JButton btnDeseja = new JButton("+ Desejo assistir");

        acoes.add(btnDetalhes);
        acoes.add(btnFav);
        acoes.add(btnAssistida);
        acoes.add(btnDeseja);

        statusLabel = new JLabel(" ");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        base.add(acoes, BorderLayout.CENTER);
        base.add(statusLabel, BorderLayout.SOUTH);
        add(base, BorderLayout.SOUTH);

        //Eventos
        botaoBuscar.addActionListener(e -> buscar());
        campoBusca.addActionListener(e -> buscar());
        btnDetalhes.addActionListener(e -> verDetalhes());
        btnFav.addActionListener(e -> adicionar(TipoLista.FAVORITOS));
        btnAssistida.addActionListener(e -> adicionar(TipoLista.ASSISTIDAS));
        btnDeseja.addActionListener(e -> adicionar(TipoLista.DESEJA_ASSISTIR));
    }

    private void buscar() {
        final String termo = campoBusca.getText().trim();
        if (termo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Digite o nome de uma série para buscar.",
                    "Busca vazia", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        botaoBuscar.setEnabled(false);
        statusLabel.setText("Buscando \"" + termo + "\"...");

        // Executa a busca em background para não travar a interface.
        SwingWorker<List<Serie>, Void> worker = new SwingWorker<List<Serie>, Void>() {
            @Override
            protected List<Serie> doInBackground() throws Exception {
                return service.buscarSeries(termo);
            }

            @Override
            protected void done() {
                botaoBuscar.setEnabled(true);
                try {
                    List<Serie> resultados = get();
                    tableModel.setSeries(resultados);
                    ajustarColunas();
                    if (resultados.isEmpty()) {
                        statusLabel.setText("Nenhuma série encontrada para \"" + termo + "\".");
                    } else {
                        statusLabel.setText(resultados.size() + " série(s) encontrada(s).");
                    }
                } catch (Exception ex) {
                    Throwable causa = ex.getCause() != null ? ex.getCause() : ex;
                    String msg = (causa instanceof ApiException)
                            ? causa.getMessage()
                            : "Erro inesperado na busca: " + causa.getMessage();
                    statusLabel.setText("Falha na busca.");
                    JOptionPane.showMessageDialog(BuscaPanel.this, msg,
                            "Erro na busca", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }

    private void ajustarColunas() {
        int[] larguras = {200, 80, 200, 60, 120, 100, 100, 150};
        for (int i = 0; i < larguras.length && i < tabela.getColumnCount(); i++) {
            tabela.getColumnModel().getColumn(i).setPreferredWidth(larguras[i]);
        }
    }

    private Serie serieSelecionada() {
        int linha = tabela.getSelectedRow();
        if (linha < 0) {
            JOptionPane.showMessageDialog(this,
                    "Selecione uma série na lista de resultados.",
                    "Nenhuma seleção", JOptionPane.INFORMATION_MESSAGE);
            return null;
        }
        return tableModel.getSerieEm(linha);
    }

    private void verDetalhes() {
        Serie s = serieSelecionada();
        if (s != null) {
            DetalhesDialog dialog = new DetalhesDialog(
                    SwingUtilities.getWindowAncestor(this), s);
            dialog.setVisible(true);
        }
    }

    private void adicionar(TipoLista tipo) {
        Serie s = serieSelecionada();
        if (s == null) {
            return;
        }
        try {
            boolean adicionada = service.adicionarNaLista(tipo, s);
            if (adicionada) {
                statusLabel.setText("\"" + s.getNome() + "\" adicionada a " + tipo.getDescricao() + ".");
            } else {
                statusLabel.setText("\"" + s.getNome() + "\" já está em " + tipo.getDescricao() + ".");
            }
        } catch (PersistenceException ex) {
            JOptionPane.showMessageDialog(this,
                    "A série foi adicionada, mas houve erro ao salvar: " + ex.getMessage(),
                    "Erro ao salvar", JOptionPane.WARNING_MESSAGE);
        }
    }
}
