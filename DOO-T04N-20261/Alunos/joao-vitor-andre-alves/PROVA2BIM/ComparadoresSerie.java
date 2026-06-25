import java.util.Comparator;

public class ComparadoresSerie {

    public static final Comparator<Serie> POR_NOME =
            (a, b) -> a.getNome().compareToIgnoreCase(b.getNome());

    public static final Comparator<Serie> POR_NOTA =
            (a, b) -> Double.compare(b.getNota(), a.getNota());

    public static final Comparator<Serie> POR_ESTADO =
            (a, b) -> a.getStatus().compareToIgnoreCase(b.getStatus());

    public static final Comparator<Serie> POR_DATA_ESTREIA = (a, b) -> {
        String da = a.getDataEstreia();
        String db = b.getDataEstreia();
        if (da == null) {
            return 1;
        }
        if (db == null) {
            return -1;
        }
        return da.compareTo(db);
    };
}