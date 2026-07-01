package ListTV;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OrdenadorSeries {

    // ordena a lista pelo nome, em ordem alfabetica (A-Z)
    public static void ordenarPorNome(List<SerieTV> lista) {

        Collections.sort(lista, new Comparator<SerieTV>() {
            public int compare(SerieTV a, SerieTV b) {
                String nomeA = a.getNome();
                String nomeB = b.getNome();
                // trata os casos de nome nulo, jogando sempre pro final da lista
                if (nomeA == null && nomeB == null) return 0;
                if (nomeA == null) return 1;
                if (nomeB == null) return -1;
                // compareToIgnoreCase ja ignora maiusculas/minusculas na comparacao
                return nomeA.compareToIgnoreCase(nomeB);
            }
        });
    }
    // ordena a lista pela nota, da maior pra menor
    public static void ordenarPorNota(List<SerieTV> lista) {
        // series sem nota (null) vao para o final, mesmo a lista sendo decrescente
        Collections.sort(lista, new Comparator<SerieTV>() {
            public int compare(SerieTV a, SerieTV b) {
                Double notaA = a.getNota();
                Double notaB = b.getNota();

                if (notaA == null && notaB == null) return 0;
                if (notaA == null) return 1;
                if (notaB == null) return -1;

                // pra inverter a ordem (maior nota primeiro), comparo "b com a"
                // em vez de "a com b"
                return notaB.compareTo(notaA);
            }
        });
    }
    // ordena a lista pelo status (ex: "Ended", "Running"), em ordem alfabetica
    public static void ordenarPorStatus(List<SerieTV> lista) {
        Collections.sort(lista, new Comparator<SerieTV>() {
            public int compare(SerieTV a, SerieTV b) {
                String statusA = a.getStatus();
                String statusB = b.getStatus();

                if (statusA == null && statusB == null) return 0;
                if (statusA == null) return 1;
                if (statusB == null) return -1;

                return statusA.compareToIgnoreCase(statusB);
            }
        });
    }
    // ordena a lista pela data de estreia
    public static void ordenarPorEstreia(List<SerieTV> lista) {
        Collections.sort(lista, new Comparator<SerieTV>() {
            public int compare(SerieTV a, SerieTV b) {
                String estreiaA = a.getEstreia();
                String estreiaB = b.getEstreia();

                if (estreiaA == null && estreiaB == null) return 0;
                if (estreiaA == null) return 1;
                if (estreiaB == null) return -1;

                // funciona como ordem cronologica so porque a data vem
                // no formato "AAAA-MM-DD", que da pra comparar como texto direto
                return estreiaA.compareTo(estreiaB);
            }
        });
    }
}