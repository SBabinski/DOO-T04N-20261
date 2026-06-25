package com.tvtracker.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Diálogo de boas-vindas exibido somente na primeira execução da aplicação.
 * Solicita o nome ou apelido do usuário. Não pode ser fechado sem preencher o
 * campo.
 */
public class WelcomeDialog extends JDialog {

    private String username;
    private JTextField nameField;

    // O construtor recebe a janela pai para centralizar o diálogo e configurar a modaliade.
    public WelcomeDialog(JFrame parent) {
        super(parent, "Bem-vindo ao TV Tracker!", true);
        initComponents();
    }

    // O método initComponents configura a interface do diálogo, 
    // organizando os componentes em um layout simples e intuitivo.   
    private void initComponents() {
        setResizable(false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(24, 28, 20, 28));

        // Cabeçalho
        JLabel titleLabel = new JLabel("TV Tracker", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));

        JLabel subtitleLabel = new JLabel(
                "<html><center>Seu assistente pessoal de séries!<br><br>"
                + "Para começar, informe seu nome ou apelido:</center></html>",
                SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 13));

        JPanel headerPanel = new JPanel(new BorderLayout(5, 8));
        headerPanel.add(titleLabel, BorderLayout.NORTH);
        headerPanel.add(subtitleLabel, BorderLayout.CENTER);

        // Campo de nome
        nameField = new JTextField(22);
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setPreferredSize(new Dimension(260, 32));

        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fieldPanel.add(nameField);

        // Botão
        JButton confirmBtn = new JButton("  Entrar  ");
        confirmBtn.setFont(new Font("Arial", Font.BOLD, 13));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(confirmBtn);

        root.add(headerPanel, BorderLayout.NORTH);
        root.add(fieldPanel, BorderLayout.CENTER);
        root.add(btnPanel, BorderLayout.SOUTH);

        confirmBtn.addActionListener(e -> onConfirm());
        nameField.addActionListener(e -> onConfirm());

        add(root);
        pack();
        setLocationRelativeTo(null);  // centraliza na tela
    }

    // O método onConfirm é chamado quando o usuário clica no botão ou pressiona Enter. 
    // Ele valida o campo de nome e, se estiver preenchido, armazena o nome e fecha o diálogo. 
    // Caso contrário, exibe uma mensagem de aviso.
    private void onConfirm() {
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Por favor, informe seu nome ou apelido.",
                    "Campo obrigatório", JOptionPane.WARNING_MESSAGE);
            nameField.requestFocusInWindow();
            return;
        }
        this.username = name;
        dispose();
    }

    /**
     * Retorna o nome informado, ou {@code null} se o diálogo não foi concluído.
     */
    public String getUsername() {
        return username;
    }
}
