import java.util.Comparator;
import java.util.List;

// ordena pela melhor nota primeiro 🌟
public class OrdenacaoNota implements EstrategiaOrdenacao {
    @Override
    public void ordenar(List<Serie> lista) {
        lista.sort(Comparator.comparing(Serie::getScore).reversed());
    }
}