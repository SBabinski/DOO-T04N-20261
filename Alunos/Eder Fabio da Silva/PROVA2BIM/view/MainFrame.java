package com.tvtracker.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import com.tvtracker.controller.AppController;

/**
 * Janela principal da aplicação. Organiza as funcionalidades em abas: Busca,
 * Favoritos, Já Assistidas e Quero Assistir.
 */
public class MainFrame extends JFrame {

    private final AppController controller;

    private ListPanel favoritesPanel;
    private ListPanel watchedPanel;
    private ListPanel watchlistPanel;

    // Construtor
    public MainFrame(AppController controller) {
        this.controller = controller;
        handleFirstRun();
        initComponents();
    }

    // ── Primeiro acesso 
    private void handleFirstRun() {
        if (!controller.isFirstRun()) {
            return;
        }

        WelcomeDialog dialog = new WelcomeDialog(this);
        dialog.setVisible(true);

        String name = dialog.getUsername();
        if (name != null && !name.isBlank()) {
            controller.setUsername(name);
        }
    }

    // ── Interface 
    // Configura a janela principal, adicionando as abas e a barra de status
    private void initComponents() {
        String user = controller.getUsername() != null ? controller.getUsername() : "Usuario";
        setTitle("TV Tracker  —  Ola, " + user + "!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(950, 580));
        setPreferredSize(new Dimension(1150, 700));

        JTabbedPane tabs = buildTabs();
        add(tabs, BorderLayout.CENTER);
        add(buildStatusBar(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    // Cria as abas da interface, associando cada uma a um painel específico
    private JTabbedPane buildTabs() {
        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
        tabs.setFont(new Font("Arial", Font.PLAIN, 13));

        SearchPanel searchPanel = new SearchPanel(controller);
        favoritesPanel = new ListPanel(controller, ListPanel.ListType.FAVORITES);
        watchedPanel = new ListPanel(controller, ListPanel.ListType.WATCHED);
        watchlistPanel = new ListPanel(controller, ListPanel.ListType.WATCHLIST);

        tabs.addTab("  Busca        ", searchPanel);
        tabs.addTab("  Favoritos    ", favoritesPanel);
        tabs.addTab("  Ja Assistidas", watchedPanel);
        tabs.addTab("  Quero Assistir", watchlistPanel);

        // Atualiza a lista ao entrar na aba
        tabs.addChangeListener(e -> {
            int i = tabs.getSelectedIndex();
            if (i == 1) {
                favoritesPanel.refresh();
            } else if (i == 2) {
                watchedPanel.refresh();
            } else if (i == 3) {
                watchlistPanel.refresh();
            }
        });

        return tabs;
    }

    // Cria a barra de status, exibindo o caminho do arquivo de dados
    private JLabel buildStatusBar() {
        JLabel bar = new JLabel(
                "  Dados salvos em: " + controller.getDataFilePath());
        bar.setFont(new Font("Arial", Font.ITALIC, 11));
        bar.setBorder(BorderFactory.createEmptyBorder(3, 6, 3, 6));
        return bar;
    }
}
