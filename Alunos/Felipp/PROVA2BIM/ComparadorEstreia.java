package Fag.comparadores;

import Fag.Serie;

import java.time.LocalDate;
import java.util.Comparator;

// ordena as series pela data de estreia, da mais antiga para a mais nova.
// series sem data ficam por ultimo.
public class ComparadorEstreia implements Comparator<Serie> {

    @Override
    public int compare(Serie a, Serie b) {
        LocalDate da = a.getDataEstreiaComoData();
        LocalDate db = b.getDataEstreiaComoData();
        if (da == null && db == null) return 0;
        if (da == null) return 1;
        if (db == null) return -1;
        return da.compareTo(db);
    }
}
