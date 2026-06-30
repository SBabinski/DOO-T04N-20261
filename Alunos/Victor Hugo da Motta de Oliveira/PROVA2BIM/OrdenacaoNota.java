import java.util.Comparator;
import java.util.List;

// implementacao da interface pra ordenar pela maior nota
public class OrdenacaoNota implements EstrategiaOrdenacao {
    @Override
    public void ordenar(List<Serie> lista) {
        // organiza pela nota e usa o reversed() pra colocar a maior no topo
        lista.sort(Comparator.comparing(Serie::getScore).reversed());
    }
}