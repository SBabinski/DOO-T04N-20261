package Fag.gui;

import Fag.Serie;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;

// tela que mostra todas as informacoes pedidas de uma serie.
public class TelaDetalhes extends TelaBase {

    public TelaDetalhes(Serie serie) {
        super("detalhes da série", 460, 380);
        montarTela(serie);
    }

    private void montarTela(Serie s) {
        JPanel painel = new JPanel(new GridLayout(0, 1, 4, 4));
        painel.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));

        painel.add(linha("nome", s.getNome()));
        painel.add(linha("idioma", s.getIdioma()));
        painel.add(linha("gêneros", s.getGenerosTexto()));
        painel.add(linha("nota geral", s.getNotaTexto()));
        painel.add(linha("estado", s.getEstado() == null ? "desconhecido" : s.getEstado().getDescricao()));
        painel.add(linha("data de estreia", s.getDataEstreia() == null ? "não informada" : s.getDataEstreia()));
        painel.add(linha("data de término",
                s.getDataTermino() == null ? "ainda em exibição ou não informada" : s.getDataTermino()));
        painel.add(linha("emissora", s.getEmissora()));

        add(new JScrollPane(painel));
    }

    private JLabel linha(String rotulo, String valor) {
        return new JLabel(rotulo + ": " + (valor == null ? "não informado" : valor));
    }
}
