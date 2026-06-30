package Fag;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private List<Serie> favoritos = new ArrayList<Serie>();
    private List<Serie> assistidas = new ArrayList<Serie>();
    private List<Serie> assistir = new ArrayList<Serie>();

    // Construtor padrão
    public Usuario() {
    }

    // Construtor com nome
    public Usuario(String nome) {
        this.nome = nome;
    }

    // Retorna nome
    public String getNome() {
        return nome;
    }

    // Define o nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna a lista de favoritos
    public List<Serie> getFavoritos() {
        return favoritos;
    }

    // Define a lista de favoritos
    public void setFavoritos(List<Serie> favoritos) {
        this.favoritos = favoritos;
    }

    // Retorna a lista de assistidas
    public List<Serie> getAssistidas() {
        return assistidas;
    }

    // Define a lista de assistidas
    public void setAssistidas(List<Serie> assistidas) {
        this.assistidas = assistidas;
    }

    // Retorna a lista de assistir
    public List<Serie> getAssistir() {
        return assistir;
    }

    // Define a lista de assistir
    public void setAssistir(List<Serie> assistir) {
        this.assistir = assistir;
    }
}
