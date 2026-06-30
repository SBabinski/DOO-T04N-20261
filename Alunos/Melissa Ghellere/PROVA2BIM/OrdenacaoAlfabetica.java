import java.util.Comparator;
import java.util.List;

// ordena pelo nome (A-Z) 
public class OrdenacaoAlfabetica implements EstrategiaOrdenacao {
    @Override
    public void ordenar(List<Serie> lista) {
        lista.sort(Comparator.comparing(Serie::getName, Comparator.nullsLast(String::compareTo)));
    }
}