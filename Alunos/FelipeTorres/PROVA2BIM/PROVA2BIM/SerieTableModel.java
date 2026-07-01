package PROVA2BIM;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SerieTableModel extends AbstractTableModel {

    private static final String[] COLUNAS = {
        "Nome", "Idioma", "Gêneros", "Nota", "Estado", "Estreia", "Término", "Emissora"
    };

    private List<Serie> series;

    public SerieTableModel() {
        this.series = new ArrayList<>();
    }

    public SerieTableModel(List<Serie> series) {
        this.series = series != null ? new ArrayList<>(series) : new ArrayList<>();
    }

    public void setSeries(List<Serie> novasSeries) {
        this.series = novasSeries != null ? new ArrayList<>(novasSeries) : new ArrayList<>();
        fireTableDataChanged();
    }

    public void adicionarSerie(Serie s) {
        if (s != null && !series.contains(s)) {
            series.add(s);
            fireTableRowsInserted(series.size() - 1, series.size() - 1);
        }
    }

    public void removerSerie(Serie s) {
        int idx = series.indexOf(s);
        if (idx >= 0) {
            series.remove(idx);
            fireTableRowsDeleted(idx, idx);
        }
    }

    public Serie getSerieNaLinha(int row) {
        if (row < 0 || row >= series.size()) return null;
        return series.get(row);
    }

    public List<Serie> getSeries() {
        return new ArrayList<>(series);
    }

    @Override
    public int getRowCount() {
        return series.size();
    }

    @Override
    public int getColumnCount() {
        return COLUNAS.length;
    }

    @Override
    public String getColumnName(int col) {
        return COLUNAS[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex < 0 || rowIndex >= series.size()) return "";
        Serie s = series.get(rowIndex);
        switch (columnIndex) {
            case 0: return s.getNome() != null ? s.getNome() : "";
            case 1: return s.getIdioma() != null ? s.getIdioma() : "N/A";
            case 2: return s.getGenerosFormatado();
            case 3: return s.getNotaFormatada();
            case 4: return s.getEstadoTraduzido();
            case 5: return s.getDataEstreia() != null ? s.getDataEstreia() : "N/A";
            case 6: return (s.getDataTermino() != null && !s.getDataTermino().isEmpty()
                            && !s.getDataTermino().equals("N/A"))
                           ? s.getDataTermino() : "—";
            case 7: return s.getEmissora() != null ? s.getEmissora() : "N/A";
            default: return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
