import java.util.Comparator;
import java.util.List;

// ordena pela data de lançamento 
public class OrdenacaoEstreia implements EstrategiaOrdenacao {
    @Override
    public void ordenar(List<Serie> lista) {
        lista.sort(Comparator.comparing(Serie::getPremiered, Comparator.nullsLast(String::compareTo)).reversed());
    }
}