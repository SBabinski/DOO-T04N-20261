package Fag.comparadores;

import Fag.Serie;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

// ordena as series por ordem alfabetica do nome, ignorando acentos e caixa.
// implementa a interface comparator (interface + polimorfismo).
public class ComparadorNome implements Comparator<Serie> {

    private final Collator collator;

    public ComparadorNome() {
        this.collator = Collator.getInstance(new Locale("pt", "BR"));
        this.collator.setStrength(Collator.PRIMARY);
    }

    @Override
    public int compare(Serie a, Serie b) {
        String na = a.getNome() == null ? "" : a.getNome();
        String nb = b.getNome() == null ? "" : b.getNome();
        return collator.compare(na, nb);
    }
}
