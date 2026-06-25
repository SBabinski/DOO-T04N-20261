package PROVA2BIM;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private List<Serie> favoritos;
    private List<Serie> assistidas;
    private List<Serie> desejoAssistir;

    public Usuario(String nome) {
        this.nome = nome;
        this.favoritos = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.desejoAssistir = new ArrayList<>();
    }

    public Usuario() {
        this.favoritos = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.desejoAssistir = new ArrayList<>();
    }

    public String getNome() { return nome; }
    public List<Serie> getFavoritos() { return favoritos; }
    public List<Serie> getAssistidas() { return assistidas; }
    public List<Serie> getDesejoAssistir() { return desejoAssistir; }

    public void setNome(String nome) { this.nome = nome; }

    public void adicionarFavorito(Serie s) { favoritos.add(s); }
    public void adicionarAssistida(Serie s) { assistidas.add(s); }
    public void adicionarDesejoAssistir(Serie s) { desejoAssistir.add(s); }

    public void validarListas() {
        if (favoritos == null) favoritos = new ArrayList<>();
        if (assistidas == null) assistidas = new ArrayList<>();
        if (desejoAssistir == null) desejoAssistir = new ArrayList<>();
    }
}
