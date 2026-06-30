package Fag;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorSeries {

    // Adicionar series
    public void adicionarSerie(List<Serie> lista, Serie serie) throws Exception {
        if (serie == null) {
            throw new Exception("Escolha uma série primeiro.");
        }

        boolean jaExiste = lista.stream().anyMatch(s -> s.getId() == serie.getId());

        if (jaExiste) {
            throw new Exception("A série já está nesta lista.");
        }

        lista.add(serie);
    }

    // Remover series
    public void removerSerie(List<Serie> lista, Serie serie) throws Exception {
        if (serie == null) {
            throw new Exception("Escolha uma série para remover.");
        }

        boolean removeu = lista.removeIf(s -> s.getId() == serie.getId());

        if (!removeu) {
            throw new Exception("A série não está nessa lista.");
        }
    }

    // Filtros de pesquisa
    public List<Serie> ordenar(List<Serie> lista, String tipoOrdenacao) {
        Comparator<Serie> comparador;

        if ("Nota geral".equals(tipoOrdenacao)) {
            comparador = Comparator.comparingDouble(Serie::getNotaGeral).reversed();
        } else if ("Estado".equals(tipoOrdenacao)) {
            comparador = Comparator.comparing(Serie::getEstado, String.CASE_INSENSITIVE_ORDER);
        } else if ("Data de estreia".equals(tipoOrdenacao)) {
            comparador = Comparator.comparing(Serie::getDataEstreia, String.CASE_INSENSITIVE_ORDER);
        } else {
            comparador = Comparator.comparing(Serie::getNome, String.CASE_INSENSITIVE_ORDER);
        }

        return lista.stream().sorted(comparador).collect(Collectors.toList());
    }
}
