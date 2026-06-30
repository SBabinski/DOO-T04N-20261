import javax.swing.*;
import java.awt.*;
import java.util.List;
public class ListaPanel extends JPanel {

    private final SerieService service;
    private final TipoLista tipo;
    private final SerieTableModel tableModel;
    private final JTable tabela;
    private final JComboBox<SerieComparators.Criterio> comboOrdenacao;
    private final JLabel contador;

    public ListaPanel(SerieService service, TipoLista tipo) {
        this.service = service;
        this.tipo = tipo;
        setLayout(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //ordenação
        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topo.add(new JLabel("Ordenar por:"));
        comboOrdenacao = new JComboBox<>(SerieComparators.Criterio.values());
        topo.add(comboOrdenacao);

        JButton btnAtualizar = new JButton("Atualizar");
        topo.add(btnAtualizar);

        contador = new JLabel();
        topo.add(Box.createHorizontalStrut(20));
        topo.add(contador);

        add(topo, BorderLayout.NORTH);

        //tabela
        tableModel = new SerieTableModel();
        tabela = new JTable(tableModel);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setRowHeight(24);
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        //ações
        JPanel base = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnDetalhes = new JButton("Ver detalhes");
        JButton btnRemover = new JButton("Remover da lista");
        base.add(btnDetalhes);
        base.add(btnRemover);
        add(base, BorderLayout.SOUTH);

        //Eventos
        comboOrdenacao.addActionListener(e -> atualizar());
        btnAtualizar.addActionListener(e -> atualizar());
        btnDetalhes.addActionListener(e -> verDetalhes());
        btnRemover.addActionListener(e -> remover());

        atualizar();
    }

    public void atualizar() {
        SerieComparators.Criterio criterio =
                (SerieComparators.Criterio) comboOrdenacao.getSelectedItem();
        List<Serie> lista = service.obterListaOrdenada(tipo, criterio);
        tableModel.setSeries(lista);
        ajustarColunas();
        contador.setText(lista.size() + " série(s) em " + tipo.getDescricao() + ".");
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
                    "Selecione uma série na lista.",
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

    private void remover() {
        Serie s = serieSelecionada();
        if (s == null) {
            return;
        }
        int opcao = JOptionPane.showConfirmDialog(this,
                "Remover \"" + s.getNome() + "\" de " + tipo.getDescricao() + "?",
                "Confirmar remoção", JOptionPane.YES_NO_OPTION);
        if (opcao != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            service.removerDaLista(tipo, s);
            atualizar();
        } catch (PersistenceException ex) {
            JOptionPane.showMessageDialog(this,
                    "A série foi removida, mas houve erro ao salvar: " + ex.getMessage(),
                    "Erro ao salvar", JOptionPane.WARNING_MESSAGE);
        }
    }
}
