import java.util.Comparator;
import java.util.List;

// implementacao da interface pra ordenar por nome
public class OrdenacaoAlfabetica implements EstrategiaOrdenacao {
    @Override
    public void ordenar(List<Serie> lista) {
        // pega a lista e manda o java organizar usando o getName como base
        lista.sort(Comparator.comparing(Serie::getName));
    }
}