import java.util.*;
import java.util.stream.*;

// Classe que serve de base para as tres listas do usuario.
// aqui que adiciona, remove e ordena series
public abstract class ListaDeSeries {

    protected List<Serie> series = new ArrayList<Serie>();

    // cada lista responde de um jeito
    public abstract String getTitulo();

    public List<Serie> getSeries() {
        return series;
    }

    // adiciona e evita repetir a mesma serie, comparando pelo id 
    public boolean adicionar(Serie serie) {
        if (serie == null) {
            return false;
        }
        // stream api, verifica se ja existe alguma serie com o mesmo id.
        boolean jaExiste = series.stream().anyMatch(s -> s.getId() == serie.getId());
        if (jaExiste) {
            return false;
        }
        series.add(serie);
        return true;
    }

    public boolean remover(Serie serie) {
        if (serie == null) {
            return false;
        }
        return series.removeIf(s -> s.getId() == serie.getId());
    }

    // ordena a lista conforme o criterio escolhido usando stream api.
    public List<Serie> ordenar(String criterio) {
        Comparator<Serie> comparador;

        if ("Nota geral".equals(criterio)) {
            // ordena por nota, sem nota vira -1 e vai pro final
            comparador = Comparator
                    .comparingDouble((Serie s) -> s.getNotaGeral() == null ? -1.0 : s.getNotaGeral())
                    .reversed();
        } else if ("Estado".equals(criterio)) {
            comparador = Comparator.comparing(
                    (Serie s) -> textoSeguro(s.getEstado()), String.CASE_INSENSITIVE_ORDER);
        } else if ("Data de estreia".equals(criterio)) {
            comparador = Comparator.comparing((Serie s) -> dataSegura(s.getDataEstreia()));
        } else { // ordena por nome
            comparador = Comparator.comparing(
                    (Serie s) -> textoSeguro(s.getNome()), String.CASE_INSENSITIVE_ORDER);
        }

        return series.stream()
                .sorted(comparador)
                .collect(Collectors.toList());
    }

    private String textoSeguro(String t) {
        return (t == null) ? "" : t;
    }

    private String dataSegura(String d) {
        // sem data vai para o fim da ordem
        return (d == null || d.isEmpty()) ? "9999-99-99" : d;
    }
}

//listas que herdam ListaDeSeries
class ListaFavoritos extends ListaDeSeries {
    @Override
    public String getTitulo() {
        return "Favoritos";
    }
}

class ListaAssistidas extends ListaDeSeries {
    @Override
    public String getTitulo() {
        return "Series ja assistidas";
    }
}

class ListaDesejaAssistir extends ListaDeSeries {
    @Override
    public String getTitulo() {
        return "Series que deseja assistir";
    }
}
