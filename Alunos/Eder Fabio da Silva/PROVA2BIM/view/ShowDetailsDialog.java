package com.tvtracker.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.tvtracker.controller.AppController;
import com.tvtracker.model.Show;

/**
 * Diálogo modal que exibe todos os detalhes de uma série e permite adicioná-la
 * a qualquer uma das três listas do usuário.
 */
public class ShowDetailsDialog extends JDialog {

    private final Show show;
    private final AppController controller;

    // O construtor recebe a série a ser exibida, o controlador para manipular as ações do 
    // usuário e a janela pai para centralizar o diálogo.
    public ShowDetailsDialog(Window parent, Show show, AppController controller) {
        super(parent, "Detalhes — " + show.getName(), ModalityType.APPLICATION_MODAL);
        this.show = show;
        this.controller = controller;
        initComponents();
    }

    // O método initComponents configura a interface do diálogo, organizando os detalhes da 
    // série em um painel central e os botões de ação em um painel inferior.
    private void initComponents() {
        setMinimumSize(new Dimension(480, 420));
        setLayout(new BorderLayout(10, 10));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        add(buildDetailsPanel(), BorderLayout.CENTER);
        add(buildActionsPanel(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getParent());
    }

    // ── Painel de detalhes 
    private JPanel buildDetailsPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(16, 18, 8, 18));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(4, 4, 4, 8);

        // Título
        JLabel titleLabel = new JLabel(show.getName());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 17));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Separador visual
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new JSeparator(), gbc);
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridwidth = 1;

        int row = 2;
        addRow(panel, gbc, row++, "Idioma:", show.getLanguage());
        addRow(panel, gbc, row++, "Generos:", show.getGenresAsString());
        addRow(panel, gbc, row++, "Nota:", show.getRatingAsString() + (show.getRating() > 0 ? " / 10" : ""));
        addRow(panel, gbc, row++, "Estado:", show.getStatus());
        addRow(panel, gbc, row++, "Estreia:", show.getPremiered());

        String ended = show.getEnded().equals("N/A") ? "Em andamento" : show.getEnded();
        addRow(panel, gbc, row++, "Termino:", ended);
        addRow(panel, gbc, row, "Emissora:", show.getNetworkName());

        return panel;
    }

    // Método auxiliar para adicionar uma linha de detalhes ao painel, com formatação consistente
    private void addRow(JPanel panel, GridBagConstraints gbc, int row, String label, String value) {
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        panel.add(lbl, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        panel.add(new JLabel(value != null ? value : "N/A"), gbc);
    }

    // ── Painel de ações 
    // O painel de ações contém botões para adicionar a série a cada uma das listas do usuário, bem como um botão para fechar o diálogo.
    private JPanel buildActionsPanel() {
        JPanel outer = new JPanel(new BorderLayout());
        outer.setBorder(BorderFactory.createTitledBorder("Adicionar a uma lista"));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 8));

        JButton favBtn = new JButton("Favoritos");
        JButton watchedBtn = new JButton("Ja assisti");
        JButton watchlistBtn = new JButton("Quero assistir");
        JButton closeBtn = new JButton("Fechar");

        favBtn.addActionListener(e -> handleAdd(
                controller.addToFavorites(show), "Favoritos"));
        watchedBtn.addActionListener(e -> handleAdd(
                controller.addToWatched(show), "Ja Assistidas"));
        watchlistBtn.addActionListener(e -> handleAdd(
                controller.addToWatchlist(show), "Quero Assistir"));
        closeBtn.addActionListener(e -> dispose());

        btnPanel.add(favBtn);
        btnPanel.add(watchedBtn);
        btnPanel.add(watchlistBtn);
        btnPanel.add(closeBtn);
        outer.add(btnPanel, BorderLayout.CENTER);
        return outer;
    }

    // O método handleAdd é chamado quando o usuário clica em um dos botões de ação rápida 
    // no diálogo de detalhes da série.
    private void handleAdd(boolean added, String listName) {
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
}
