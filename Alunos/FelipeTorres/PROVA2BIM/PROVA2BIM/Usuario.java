package PROVA2BIM;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private List<Serie> favoritos;
    private List<Serie> jaAssistidas;
    private List<Serie> desejassistir;

    public Usuario() {
        this.favoritos = new ArrayList<>();
        this.jaAssistidas = new ArrayList<>();
        this.desejassistir = new ArrayList<>();
    }

    public Usuario(String nome) {
        this();
        this.nome = nome;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public List<Serie> getFavoritos() { return favoritos; }
    public void setFavoritos(List<Serie> favoritos) { this.favoritos = favoritos; }

    public List<Serie> getJaAssistidas() { return jaAssistidas; }
    public void setJaAssistidas(List<Serie> jaAssistidas) { this.jaAssistidas = jaAssistidas; }

    public List<Serie> getDesejassistir() { return desejassistir; }
    public void setDesejassistir(List<Serie> desejassistir) { this.desejassistir = desejassistir; }

   
    public boolean adicionarFavorito(Serie serie) {
        if (serie == null) return false;
        if (favoritos.contains(serie)) return false;
        return favoritos.add(serie);
    }

    public boolean removerFavorito(Serie serie) {
        if (serie == null) return false;
        return favoritos.remove(serie);
    }

    public boolean isFavorito(Serie serie) {
        if (serie == null) return false;
        return favoritos.contains(serie);
    }

   
    public boolean adicionarJaAssistida(Serie serie) {
        if (serie == null) return false;
        if (jaAssistidas.contains(serie)) return false;
        return jaAssistidas.add(serie);
    }

    public boolean removerJaAssistida(Serie serie) {
        if (serie == null) return false;
        return jaAssistidas.remove(serie);
    }

    public boolean isJaAssistida(Serie serie) {
        if (serie == null) return false;
        return jaAssistidas.contains(serie);
    }

   
    public boolean adicionarDesejaAssistir(Serie serie) {
        if (serie == null) return false;
        if (desejassistir.contains(serie)) return false;
        return desejassistir.add(serie);
    }

    public boolean removerDesejaAssistir(Serie serie) {
        if (serie == null) return false;
        return desejassistir.remove(serie);
    }

    public boolean isDesejaAssistir(Serie serie) {
        if (serie == null) return false;
        return desejassistir.contains(serie);
    }
}
