package Fag.comparadores;

import Fag.Serie;

import java.util.Comparator;

// ordena as series pela nota geral, da maior para a menor.
// series sem nota ficam por ultimo.
public class ComparadorNota implements Comparator<Serie> {

    @Override
    public int compare(Serie a, Serie b) {
        double na = a.getNota() == null ? -1 : a.getNota();
        double nb = b.getNota() == null ? -1 : b.getNota();
        return Double.compare(nb, na);
    }
}
