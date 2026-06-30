import javax.swing.*;
import java.awt.*;

public class DetalhesDialog extends JDialog {

    public DetalhesDialog(Window owner, Serie serie) {
        super(owner, "Detalhes da Série", ModalityType.APPLICATION_MODAL);
        construir(serie);
        setSize(520, 480);
        setLocationRelativeTo(owner);
    }

    private void construir(Serie serie) {
        JPanel painel = new JPanel(new BorderLayout(12, 12));
        painel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        JLabel titulo = new JLabel(textoOuTraco(serie.getNome()));
        titulo.setFont(titulo.getFont().deriveFont(Font.BOLD, 20f));
        painel.add(titulo, BorderLayout.NORTH);

        JPanel info = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int linha = 0;
        linha = adicionarLinha(info, gbc, linha, "Idioma:", textoOuTraco(serie.getIdioma()));
        linha = adicionarLinha(info, gbc, linha, "Gêneros:", serie.getGenerosFormatados());
        linha = adicionarLinha(info, gbc, linha, "Nota geral:", serie.getNotaFormatada());
        linha = adicionarLinha(info, gbc, linha, "Estado:", serie.getStatus().getDescricao());
        linha = adicionarLinha(info, gbc, linha, "Data de estreia:", textoOuTraco(serie.getDataEstreia()));
        linha = adicionarLinha(info, gbc, linha, "Data de término:", textoOuTraco(serie.getDataTermino()));
        linha = adicionarLinha(info, gbc, linha, "Emissora:", textoOuTraco(serie.getEmissora()));

        painel.add(info, BorderLayout.CENTER);

        //Resumo
        JTextArea resumo = new JTextArea();
        resumo.setEditable(false);
        resumo.setLineWrap(true);
        resumo.setWrapStyleWord(true);
        resumo.setOpaque(false);
        resumo.setBorder(BorderFactory.createTitledBorder("Resumo"));
        String texto = serie.getResumo();
        resumo.setText((texto != null && !texto.isEmpty()) ? texto : "Sem resumo disponível.");
        JScrollPane scroll = new JScrollPane(resumo);
        scroll.setPreferredSize(new Dimension(480, 140));

        JPanel sul = new JPanel(new BorderLayout(8, 8));
        sul.add(scroll, BorderLayout.CENTER);
        JButton fechar = new JButton("Fechar");
        fechar.addActionListener(e -> dispose());
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoes.add(fechar);
        sul.add(botoes, BorderLayout.SOUTH);

        painel.add(sul, BorderLayout.SOUTH);
        setContentPane(painel);
    }

    private int adicionarLinha(JPanel painel, GridBagConstraints gbc, int linha,
                               String rotulo, String valor) {
        gbc.gridx = 0;
        gbc.gridy = linha;
        gbc.weightx = 0;
        JLabel lblRotulo = new JLabel(rotulo);
        lblRotulo.setFont(lblRotulo.getFont().deriveFont(Font.BOLD));
        painel.add(lblRotulo, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        painel.add(new JLabel("<html><body style='width:300px'>" + escapar(valor) + "</body></html>"), gbc);
        return linha + 1;
    }

    private String escapar(String s) {
        if (s == null) return "—";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }

    private String textoOuTraco(String s) {
        return (s != null && !s.trim().isEmpty()) ? s : "—";
    }
}
