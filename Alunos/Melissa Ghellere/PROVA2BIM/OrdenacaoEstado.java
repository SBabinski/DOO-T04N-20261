import java.util.Comparator;
import java.util.List;

// agrupa pelo status (ended, running) 📺
public class OrdenacaoEstado implements EstrategiaOrdenacao {
    @Override
    public void ordenar(List<Serie> lista) {
        lista.sort(Comparator.comparing(Serie::getStatus, Comparator.nullsLast(String::compareTo)));
    }
}