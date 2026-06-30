package model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private List<Serie> favoritos;
    private List<Serie> assistidos;
    private List<Serie> queroAssistir;

    public Usuario() {
        this.favoritos = new ArrayList<>();
        this.assistidos = new ArrayList<>();
        this.queroAssistir = new ArrayList<>();
    }

    public Usuario(String nome, List<Serie> favoritos, List<Serie> assistidos, List<Serie> queroAssistir) {
        this.nome = nome;
        this.favoritos = favoritos;
        this.assistidos = assistidos;
        this.queroAssistir = queroAssistir;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Serie> getFavoritos() { return favoritos; }
    public void setFavoritos(List<Serie> favoritos) { this.favoritos = favoritos; }

    public List<Serie> getAssistidos() { return assistidos; }
    public void setAssistidos(List<Serie> assistidos) { this.assistidos = assistidos; }

    public List<Serie> getQueroAssistir() { return queroAssistir; }
    public void setQueroAssistir(List<Serie> queroAssistir) { this.queroAssistir = queroAssistir; }

    public void adicionarAoFavoritos(Serie serie) {
        if (!favoritos.contains(serie)) favoritos.add(serie);
    }
    public void removerDosFavoritos(Serie serie) { favoritos.remove(serie); }

    public void adicionarAoAssistidos(Serie serie) {
        if (!assistidos.contains(serie)) assistidos.add(serie);
    }
    public void removerDosAssistidos(Serie serie) { assistidos.remove(serie); }

    public void adicionarAoQueroAssistir(Serie serie) {
        if (!queroAssistir.contains(serie)) queroAssistir.add(serie);
    }
    public void removerDosQueroAssistir(Serie serie) { queroAssistir.remove(serie); }
}