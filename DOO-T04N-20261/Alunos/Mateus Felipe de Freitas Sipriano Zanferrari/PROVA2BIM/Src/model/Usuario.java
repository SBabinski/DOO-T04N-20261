package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Usuario {
    private String nome;
    private final List<Serie> favoritos = new ArrayList<>();
    private final List<Serie> jaAssistidas = new ArrayList<>();
    private final List<Serie> desejaAssistir = new ArrayList<>();

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public List<Serie> getFavoritos() { return favoritos; }
    public List<Serie> getJaAssistidas() { return jaAssistidas; }
    public List<Serie> getDesejaAssistir() { return desejaAssistir; }

    // Método de negócio que centraliza a ordenação das listas
    public void ordenarLista(List<Serie> lista, int tipoOrdenacao) {
        switch (tipoOrdenacao) {
            case 0 -> lista.sort(Comparator.comparing(Serie::getNome, String.CASE_INSENSITIVE_ORDER));
            case 1 -> lista.sort((s1, s2) -> Double.compare(s2.getNotaGeral(), s1.getNotaGeral()));
            case 2 -> lista.sort(Comparator.comparing(Serie::getEstado));
            case 3 -> lista.sort(Comparator.comparing(Serie::getDataEstreia));
        }
    }
}