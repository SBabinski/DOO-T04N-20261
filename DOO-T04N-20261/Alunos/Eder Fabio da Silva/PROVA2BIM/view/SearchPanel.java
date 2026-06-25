package com.tvtracker.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

import com.tvtracker.controller.AppController;
import com.tvtracker.model.Show;

/**
 * Painel de busca de séries pela API TVMaze. Usa {@link SwingWorker} para não
 * bloquear a interface durante a requisição HTTP.
 */
public class SearchPanel extends JPanel {

    private final AppController controller;

    private JTextField searchField;
    private JTable resultsTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;

    private List<Show> currentResults = new ArrayList<>();

    //
    public SearchPanel(AppController controller) {
        this.controller = controller;
        initComponents();
    }

    // ── Interface
    private void initComponents() {
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(buildSearchBar(), BorderLayout.NORTH);
        add(buildResultTable(), BorderLayout.CENTER);
        add(buildBottomPanel(), BorderLayout.SOUTH);
    }

    // ── Construção dos sub-painéis 
    // Barra de busca com campo de texto e botão
    private JPanel buildSearchBar() {
        JPanel bar = new JPanel(new BorderLayout(6, 0));
        bar.setBorder(BorderFactory.createEmptyBorder(0, 0, 6, 0));

        searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        JButton searchBtn = new JButton("Buscar");
        searchBtn.setFont(new Font("Arial", Font.BOLD, 13));

        bar.add(new JLabel("Buscar serie:  "), BorderLayout.WEST);
        bar.add(searchField, BorderLayout.CENTER);
        bar.add(searchBtn, BorderLayout.EAST);

        searchBtn.addActionListener(e -> performSearch());
        searchField.addActionListener(e -> performSearch());

        return bar;
    }

    // Tabela de resultados, configurada para exibir as informações relevantes das séries 
    // e permitir seleção de uma série para detalhes ou ações rápidas.
    private JScrollPane buildResultTable() {
        String[] columns = {"Nome", "Idioma", "Generos", "Nota", "Estado", "Estreia", "Emissora"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };

        resultsTable = new JTable(tableModel);
        resultsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        resultsTable.setRowHeight(24);
        resultsTable.setFont(new Font("Arial", Font.PLAIN, 12));
        resultsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        // Larguras sugeridas
        int[] widths = {200, 70, 160, 50, 80, 80, 130};
        for (int i = 0; i < widths.length; i++) {
            resultsTable.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
        }

        resultsTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    openDetails();
                }
            }
        });

        return new JScrollPane(resultsTable);
    }

    // Painel inferior com status e botões de ação rápida para a série selecionada
    private JPanel buildBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(6, 0, 0, 0));

        statusLabel = new JLabel("Digite o nome de uma serie e clique em Buscar.");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
        JButton detailsBtn = new JButton("Ver Detalhes");
        JButton favBtn = new JButton("Favoritos");
        JButton watchedBtn = new JButton("Ja assisti");
        JButton watchlistBtn = new JButton("Quero ver");

        actionPanel.add(detailsBtn);
        actionPanel.add(favBtn);
        actionPanel.add(watchedBtn);
        actionPanel.add(watchlistBtn);

        panel.add(statusLabel, BorderLayout.WEST);
        panel.add(actionPanel, BorderLayout.EAST);

        detailsBtn.addActionListener(e -> openDetails());
        favBtn.addActionListener(e -> quickAdd("favoritos"));
        watchedBtn.addActionListener(e -> quickAdd("assistidas"));
        watchlistBtn.addActionListener(e -> quickAdd("watchlist"));

        return panel;
    }

    // ── Ações 
    private void performSearch() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Digite o nome de uma serie para buscar.",
                    "Campo vazio", JOptionPane.WARNING_MESSAGE);
            return;
        }

        statusLabel.setText("Buscando...");
        tableModel.setRowCount(0);
        currentResults.clear();

        SwingWorker<List<Show>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Show> doInBackground() throws Exception {
                return controller.searchShows(query);
            }

            @Override
            protected void done() {
                try {
                    currentResults = get();
                    if (currentResults.isEmpty()) {
                        statusLabel.setText("Nenhuma serie encontrada para \"" + query + "\".");
                    } else {
                        for (Show s : currentResults) {
                            tableModel.addRow(showToRow(s));
                        }
                        statusLabel.setText(currentResults.size()
                                + " resultado(s). Duplo clique para detalhes.");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    statusLabel.setText("Busca interrompida.");
                } catch (ExecutionException e) {
                    statusLabel.setText("Erro ao buscar. Verifique sua conexao.");
                    JOptionPane.showMessageDialog(SearchPanel.this,
                            "Nao foi possivel buscar series:\n" + e.getCause().getMessage(),
                            "Erro de conexao", JOptionPane.ERROR_MESSAGE);
                }
            }
        };

        worker.execute();
    }

    // O método openDetails é chamado quando o usuário clica duas vezes em uma série ou
    // clica no botão "Ver Detalhes". .
    private void openDetails() {
        Show show = getSelectedShow();
        if (show == null) {
            return;
        }
        new ShowDetailsDialog(SwingUtilities.getWindowAncestor(this), show, controller)
                .setVisible(true);
    }

    // O método quickAdd é chamado quando o usuário clica em um dos botões de ação rápida. 
    // Ele adiciona a série selecionada à lista correspondente.
    private void quickAdd(String list) {
        Show show = getSelectedShow();
        if (show == null) {
            return;
        }

        boolean added;
        String listName;

        if ("favoritos".equals(list)) {
            added = controller.addToFavorites(show);
            listName = "Favoritos";
        } else if ("assistidas".equals(list)) {
            added = controller.addToWatched(show);
            listName = "Ja Assistidas";
        } else {
            added = controller.addToWatchlist(show);
            listName = "Quero Assistir";
        }

        if (added) {
            JOptionPane.showMessageDialog(this,
                    "\"" + show.getName() + "\" adicionado a " + listName + "!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this,
                    "\"" + show.getName() + "\" ja esta em " + listName + ".",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    // ── Helpers 
    /**
     * Retorna a série selecionada na tabela, ou {@code null} se nada
     * selecionado.
     */
    private Show getSelectedShow() {
        int row = resultsTable.getSelectedRow();
        if (row < 0 || row >= currentResults.size()) {
            JOptionPane.showMessageDialog(this,
                    "Selecione uma serie da lista.",
                    "Nenhuma selecao", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return currentResults.get(row);
    }

    // O método showToRow converte um objeto Show em um array de objetos para ser 
    // adicionado à tabela.
    private Object[] showToRow(Show s) {
        return new Object[]{
            s.getName(),
            s.getLanguage(),
            s.getGenresAsString(),
            s.getRatingAsString(),
            s.getStatus(),
            s.getPremiered(),
            s.getNetworkName()
        };
    }
}
