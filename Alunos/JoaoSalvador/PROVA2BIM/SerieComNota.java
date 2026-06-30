package Fag;

import java.util.List;

public class SerieComNota extends Serie {

    public SerieComNota() {
        super();
    }

    // Construtor da série com nota
    public SerieComNota(int id, String nome, String idioma, List<String> generos, double notaGeral, String estado, String dataEstreia, String dataTermino, String emissora) {
        super(id, nome, idioma, generos, notaGeral, estado, dataEstreia, dataTermino, emissora);
    }

    // Sobrescreve para incluir a nota
    @Override
    public String toString() {
        return getNome() + " - Nota: " + getNotaGeral();
    }
}
