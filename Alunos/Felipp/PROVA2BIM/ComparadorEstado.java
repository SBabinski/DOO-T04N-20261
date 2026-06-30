package Fag.comparadores;

import Fag.EstadoSerie;
import Fag.Serie;

import java.util.Comparator;

// ordena as series pelo estado (em exibicao, concluida, cancelada, desconhecido).
// usa a ordem em que os valores foram declarados no enum.
public class ComparadorEstado implements Comparator<Serie> {

    @Override
    public int compare(Serie a, Serie b) {
        EstadoSerie ea = a.getEstado() == null ? EstadoSerie.DESCONHECIDO : a.getEstado();
        EstadoSerie eb = b.getEstado() == null ? EstadoSerie.DESCONHECIDO : b.getEstado();
        return ea.compareTo(eb);
    }
}
