import java.util.Comparator;
import java.util.List;

// implementacao da interface pra ordenar por status (estado da serie)
public class OrdenacaoStatus implements EstrategiaOrdenacao {
    @Override
    public void ordenar(List<Serie> lista) {
        // nullsLast garante que o programa nao feche com excecao se vier nulo da API
        lista.sort(Comparator.comparing(Serie::getStatus, Comparator.nullsLast(String::compareToIgnoreCase)));
    }
}