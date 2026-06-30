package Fag;

import java.util.ArrayList;
import java.util.List;

// representa o usuario local do sistema.
// guarda o apelido e as tres listas solicitadas
// Salvo e carregado no JSON
public class Usuario {

    private String apelido;
    private List<Serie> favoritos;
    private List<Serie> assistidas;
    private List<Serie> desejaAssistir;

    public Usuario() {
        this.apelido = "";
        this.favoritos = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.desejaAssistir = new ArrayList<>();
    }

    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }

    public List<Serie> getFavoritos() { return favoritos; }
    public void setFavoritos(List<Serie> favoritos) { this.favoritos = favoritos; }

    public List<Serie> getAssistidas() { return assistidas; }
    public void setAssistidas(List<Serie> assistidas) { this.assistidas = assistidas; }

    public List<Serie> getDesejaAssistir() { return desejaAssistir; }
    public void setDesejaAssistir(List<Serie> desejaAssistir) { this.desejaAssistir = desejaAssistir; }

    // metodos auxiliares. o "contains" usa o equals da serie e evita repetidos.
    public void adicionar(List<Serie> lista, Serie serie) {
        if (serie != null && !lista.contains(serie)) {
            lista.add(serie);
        }
    }

    public void remover(List<Serie> lista, Serie serie) {
        lista.remove(serie);
    }
}
