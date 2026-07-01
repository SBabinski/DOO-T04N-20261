package PROVA2BIM;

import java.util.Comparator;
import java.util.List;


public class OrdenacaoUtil {

    public enum CriterioOrdenacao {
        NOME("Nome (A-Z)"),
        NOTA("Nota (maior primeiro)"),
        ESTADO("Estado"),
        DATA_ESTREIA("Data de Estreia");

        private final String descricao;

        CriterioOrdenacao(String descricao) {
            this.descricao = descricao;
        }

        @Override
        public String toString() {
            return descricao;
        }
    }

    
    public static void ordenar(List<Serie> lista, CriterioOrdenacao criterio) {
        if (lista == null || criterio == null) return;

        Comparator<Serie> comparator;
        switch (criterio) {
            case NOME:
                comparator = Comparator.comparing(
                    s -> s.getNome() != null ? s.getNome().toLowerCase() : "");
                break;

            case NOTA:
                comparator = Comparator.comparingDouble(Serie::getNota).reversed();
                break;

            case ESTADO:
                comparator = Comparator.comparing(
                    s -> s.getEstado() != null ? s.getEstado().toLowerCase() : "");
                break;

            case DATA_ESTREIA:
                comparator = Comparator.comparing(s -> {
                    String d = s.getDataEstreia();
                    if (d == null || d.equals("N/A") || d.isEmpty()) return "0000-00-00";
                    return d;
                });
                break;

            default:
                return;
        }

        lista.sort(comparator);
    }
}
