package com.tvtracker.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.tvtracker.controller.AppController;
import com.tvtracker.model.Show;

/**
 * Painel reutilizável para exibir e gerenciar uma das três listas do usuário
 * (Favoritos, Já Assistidas ou Quero Assistir). Oferece ordenação, remoção e
 * visualização de detalhes.
 */
public class ListPanel extends JPanel {

    //Tipo da lista gerenciada por esta instância.
    public enum ListType {
        FAVORITES, WATCHED, WATCHLIST
    }
    // Referências para o controlador e tipo de lista, além dos componentes Swing 
    // usados para exibir os dados e interagir com o usuário.
    private final AppController controller;
    private final ListType listType;
    // Componentes Swing para a tabela de séries, modelo de dados e label de contagem
    private JTable showTable;
    private DefaultTableModel tableModel;
    private JLabel countLabel;
    private List<Show> currentShows = new ArrayList<>();

    // Construtor que recebe o controlador e o tipo de lista, inicializa os componentes e 
    // carrega os dados.
    public ListPanel(AppController controller, ListType listType) {
        this.controller = controller;
        this.listType = listType;
        initComponents();
        refresh();
    }

    // ── Inicialização dos componentes Swing e layout do painel
    private void initComponents() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(buildTopBar(), BorderLayout.NORTH);
        add(buildTable(), BorderLayout.CENTER);
        add(buildBottomBar(), BorderLayout.SOUTH);
    }

    // ── Sub-painéis 
    private JPanel buildTopBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));

        bar.add(new JLabel("Ordenar por:"));
        JComboBox<String> sortCombo = new JComboBox<>(
                new String[]{"Nome", "Nota", "Estado", "Estreia"});
        JButton sortBtn = new JButton("Ordenar");
        JButton refreshBtn = new JButton("Atualizar");

        bar.add(sortCombo);
        bar.add(sortBtn);
        bar.add(refreshBtn);

        sortBtn.addActionListener(e -> {
            String criterion = (String) sortCombo.getSelectedItem();
            List<Show> sorted = controller.sortShows(currentShows, criterion);
            renderTable(sorted);
        });
        refreshBtn.addActionListener(e -> refresh());

        return bar;
    }

    // O método buildTable configura a tabela JTable para exibir as séries, 
    // definindo colunas, estilos e comportamento de clique.
    private JScrollPane buildTable() {
        String[] columns = {"Nome", "Idioma", "Generos", "Nota",
            "Estado", "Estreia", "Emissora"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        showTable = new JTable(tableModel);
        showTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        showTable.setRowHeight(24);
        showTable.setFont(new Font("Arial", Font.PLAIN, 12));
        showTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        int[] widths = {200, 70, 160, 50, 80, 80, 130};
        for (int i = 0; i < widths.length; i++) {
            showTable.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }

        showTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    openDetails();
                }
            }
        });

        return new JScrollPane(showTable);
    }

    // O método buildBottomBar cria a barra inferior do painel, que inclui um label 
    // para mostrar a contagem de séries e botões para ver detalhes ou remover a série 
    // selecionada. Ele também configura os listeners para os botões.
    private JPanel buildBottomBar() {
        JPanel bar = new JPanel(new BorderLayout(5, 0));
        bar.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));

        countLabel = new JLabel("0 serie(s)");
        countLabel.setFont(new Font("Arial", Font.ITALIC, 12));

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        JButton detailsBtn = new JButton("Ver Detalhes");
        JButton removeBtn = new JButton("Remover");

        actionPanel.add(detailsBtn);
        actionPanel.add(removeBtn);

        bar.add(countLabel, BorderLayout.WEST);
        bar.add(actionPanel, BorderLayout.EAST);

        detailsBtn.addActionListener(e -> openDetails());
        removeBtn.addActionListener(e -> removeSelected());

        return bar;
    }

    // ── Ações 
    /**
     * Recarrega os dados do controlador e atualiza a tabela.
     */
    public void refresh() {
        if (listType == ListType.FAVORITES) {
            currentShows = controller.getFavorites();
        } else if (listType == ListType.WATCHED) {
            currentShows = controller.getWatched();
        } else {
            currentShows = controller.getWatchlist();
        }
        renderTable(currentShows);
    }

    // O método renderTable recebe uma lista de séries e atualiza o modelo da  
    // tabela para exibir os dados.
    private void renderTable(List<Show> shows) {
        tableModel.setRowCount(0);
        for (Show s : shows) {
            tableModel.addRow(new Object[]{
                s.getName(),
                s.getLanguage(),
                s.getGenresAsString(),
                s.getRatingAsString(),
                s.getStatus(),
                s.getPremiered(),
                s.getNetworkName()
            });
        }
        countLabel.setText(shows.size() + " serie(s)");
        currentShows = new ArrayList<>(shows);  // mantém referência sincronizada
    }

    // O método openDetails é chamado quando o usuário clica duas vezes em uma série ou 
    // clica no botão "Ver Detalhes". Ele obtém a série selecionada e abre um diálogo 
    // com os detalhes da série. Após o diálogo ser fechado, ele chama refresh para 
    // atualizar a lista caso o usuário tenha feito alterações.
    private void openDetails() {
        Show show = getSelectedShow();
        if (show == null) {
            return;
        }
        new ShowDetailsDialog(SwingUtilities.getWindowAncestor(this), show, controller)
                .setVisible(true);
        refresh();  // atualiza caso o usuário tenha adicionado a outra lista
    }

    // O método removeSelected é chamado quando o usuário clica no botão "Remover". Ele
    // obtém a série selecionada, pede confirmação ao usuário e, se confirmado, remove
    // a série da lista correspondente.
    private void removeSelected() {
        Show show = getSelectedShow();
        if (show == null) {
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Remover \"" + show.getName() + "\" desta lista?",
                "Confirmar remocao", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        if (listType == ListType.FAVORITES) {
            controller.removeFromFavorites(show);
        } else if (listType == ListType.WATCHED) {
            controller.removeFromWatched(show);
        } else {
            controller.removeFromWatchlist(show);
        }
        refresh();
    }

    // ── Helper 
    // O método getSelectedShow verifica qual linha da tabela está selecionada e retorna 
    // o objeto Show correspondente.
    private Show getSelectedShow() {
        int row = showTable.getSelectedRow();
        if (row < 0 || row >= currentShows.size()) {
            JOptionPane.showMessageDialog(this,
                    "Selecione uma serie da lista.",
                    "Nenhuma selecao", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return currentShows.get(row);
    }
}
