import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class SerieTableModel extends AbstractTableModel {

    private static final String[] COLUNAS = {
            "Nome", "Idioma", "Gêneros", "Nota", "Estado", "Estreia", "Término", "Emissora"
    };

    private List<Serie> series = new ArrayList<>();

    public void setSeries(List<Serie> series) {
        this.series = (series != null) ? series : new ArrayList<>();
        fireTableDataChanged();
    }

    public Serie getSerieEm(int linha) {
        if (linha >= 0 && linha < series.size()) {
            return series.get(linha);
        }
        return null;
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
    public String getColumnName(int column) {
        return COLUNAS[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Serie s = series.get(rowIndex);
        switch (columnIndex) {
            case 0: return texto(s.getNome());
            case 1: return texto(s.getIdioma());
            case 2: return s.getGenerosFormatados();
            case 3: return s.getNotaFormatada();
            case 4: return s.getStatus().getDescricao();
            case 5: return texto(s.getDataEstreia());
            case 6: return texto(s.getDataTermino());
            case 7: return texto(s.getEmissora());
            default: return "";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    private String texto(String s) {
        return (s != null && !s.trim().isEmpty()) ? s : "—";
    }
}
