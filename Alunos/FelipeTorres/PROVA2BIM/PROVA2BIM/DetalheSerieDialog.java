package PROVA2BIM;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DetalheSerieDialog extends JDialog {

    public DetalheSerieDialog(Frame parent, Serie serie, Usuario usuario,
                               PersistenciaService persistencia) {
        super(parent, "Detalhes da Série", true);
        setSize(560, 500);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(new EmptyBorder(15, 15, 15, 15));
        painel.setBackground(new Color(245, 245, 250));

        JLabel lblTitulo = new JLabel(serie.getNome());
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(40, 60, 120));
        painel.add(lblTitulo, BorderLayout.NORTH);

        JPanel infoPanel = new JPanel(new GridBagLayout());
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 210, 230)),
            new EmptyBorder(12, 12, 12, 12)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 8);
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;
        adicionarLinha(infoPanel, gbc, row++, "Idioma:", serie.getIdioma());
        adicionarLinha(infoPanel, gbc, row++, "Gêneros:", serie.getGenerosFormatado());
        adicionarLinha(infoPanel, gbc, row++, "Nota:", serie.getNotaFormatada());
        adicionarLinha(infoPanel, gbc, row++, "Estado:", serie.getEstadoTraduzido());
        adicionarLinha(infoPanel, gbc, row++, "Estreia:", serie.getDataEstreia() != null ? serie.getDataEstreia() : "N/A");
        String termino = (serie.getDataTermino() != null && !serie.getDataTermino().isEmpty()
                && !serie.getDataTermino().equals("N/A")) ? serie.getDataTermino() : "—";
        adicionarLinha(infoPanel, gbc, row++, "Término:", termino);
        adicionarLinha(infoPanel, gbc, row++, "Emissora:", serie.getEmissora() != null ? serie.getEmissora() : "N/A");

        if (serie.getResumo() != null && !serie.getResumo().isEmpty()) {
            gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            JLabel lblResumoTitulo = new JLabel("Resumo:");
            lblResumoTitulo.setFont(new Font("SansSerif", Font.BOLD, 12));
            lblResumoTitulo.setForeground(new Color(60, 80, 140));
            infoPanel.add(lblResumoTitulo, gbc);
            row++;

            gbc.gridy = row; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 1.0; gbc.weighty = 1.0;
            JTextArea taResumo = new JTextArea(serie.getResumo());
            taResumo.setWrapStyleWord(true);
            taResumo.setLineWrap(true);
            taResumo.setEditable(false);
            taResumo.setFont(new Font("SansSerif", Font.PLAIN, 12));
            taResumo.setBackground(Color.WHITE);
            taResumo.setBorder(null);
            JScrollPane scrollResumo = new JScrollPane(taResumo);
            scrollResumo.setPreferredSize(new Dimension(480, 80));
            scrollResumo.setBorder(null);
            infoPanel.add(scrollResumo, gbc);
        }

        painel.add(infoPanel, BorderLayout.CENTER);

        JPanel botoesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        botoesPanel.setBackground(new Color(245, 245, 250));

        JButton btnFav = criarBotaoLista("⭐ " + (usuario.isFavorito(serie) ? "Remover Favorito" : "Favoritar"),
                new Color(255, 200, 0), new Color(180, 140, 0));
        JButton btnAssistida = criarBotaoLista("✓ " + (usuario.isJaAssistida(serie) ? "Remover de Assistidas" : "Já Assisti"),
                new Color(80, 180, 80), new Color(40, 120, 40));
        JButton btnDeseja = criarBotaoLista("📌 " + (usuario.isDesejaAssistir(serie) ? "Remover de Quero Ver" : "Quero Ver"),
                new Color(80, 130, 220), new Color(40, 80, 160));
        JButton btnFechar = criarBotaoLista("Fechar", new Color(180, 70, 70), new Color(140, 40, 40));

        btnFav.addActionListener(e -> {
            if (usuario.isFavorito(serie)) {
                usuario.removerFavorito(serie);
                btnFav.setText("⭐ Favoritar");
            } else {
                usuario.adicionarFavorito(serie);
                btnFav.setText("⭐ Remover Favorito");
            }
            salvarSilencioso(persistencia, usuario);
        });

        btnAssistida.addActionListener(e -> {
            if (usuario.isJaAssistida(serie)) {
                usuario.removerJaAssistida(serie);
                btnAssistida.setText("✓ Já Assisti");
            } else {
                usuario.adicionarJaAssistida(serie);
                btnAssistida.setText("✓ Remover de Assistidas");
            }
            salvarSilencioso(persistencia, usuario);
        });

        btnDeseja.addActionListener(e -> {
            if (usuario.isDesejaAssistir(serie)) {
                usuario.removerDesejaAssistir(serie);
                btnDeseja.setText("📌 Quero Ver");
            } else {
                usuario.adicionarDesejaAssistir(serie);
                btnDeseja.setText("📌 Remover de Quero Ver");
            }
            salvarSilencioso(persistencia, usuario);
        });

        btnFechar.addActionListener(e -> dispose());

        botoesPanel.add(btnFav);
        botoesPanel.add(btnAssistida);
        botoesPanel.add(btnDeseja);
        botoesPanel.add(btnFechar);

        painel.add(botoesPanel, BorderLayout.SOUTH);
        setContentPane(painel);
    }

    private void adicionarLinha(JPanel panel, GridBagConstraints gbc, int row, String label, String valor) {
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;

        gbc.gridx = 0; gbc.gridy = row;
        JLabel lbl = new JLabel(label);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbl.setForeground(new Color(60, 80, 140));
        panel.add(lbl, gbc);

        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        JLabel val = new JLabel(valor != null ? valor : "N/A");
        val.setFont(new Font("SansSerif", Font.PLAIN, 12));
        panel.add(val, gbc);
    }

    private JButton criarBotaoLista(String texto, Color bg, Color hover) {
        JButton btn = new JButton(texto);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("SansSerif", Font.BOLD, 11));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(hover); }
            public void mouseExited(java.awt.event.MouseEvent e) { btn.setBackground(bg); }
        });
        return btn;
    }

    private void salvarSilencioso(PersistenciaService persistencia, Usuario usuario) {
        try {
            persistencia.salvar(usuario);
        } catch (Exception ex) {
            
        }
    }
}
