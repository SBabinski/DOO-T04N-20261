package ListTV;

import java.util.ArrayList;
import java.util.List;

public class PerfilUsuario {

    private String nome;
    private List<SerieTV> favoritos;
    private List<SerieTV> assistidas;
    private List<SerieTV> desejoAssistir;

    public PerfilUsuario() {
        this.favoritos = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.desejoAssistir = new ArrayList<>();
    }

    public PerfilUsuario(String nome) {
        this();
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<SerieTV> getFavoritos() {
        return favoritos;
    }

    public List<SerieTV> getAssistidas() {
        return assistidas;
    }

    public List<SerieTV> getDesejoAssistir() {
        return desejoAssistir;
    }

    // devolve a lista certa de acordo com a categoria
    public List<SerieTV> getLista(CategoriaLista categoria) {
        switch (categoria) {
            case FAVORITOS: return favoritos;
            case ASSISTIDAS: return assistidas;
            case DESEJO_ASSISTIR: return desejoAssistir;
            default: throw new IllegalArgumentException("Categoria desconhecida: " + categoria);
        }
    }

    // adiciona a serie na lista, sem deixar duplicar (mesmo id). retorna true se adicionou
    public boolean adicionarNaLista(CategoriaLista categoria, SerieTV serie) {
        List<SerieTV> lista = getLista(categoria);
        if (lista.contains(serie)) {
            return false;
        }
        return lista.add(serie);
    }

    // remove a serie da lista. retorna true se removeu
    public boolean removerDaLista(CategoriaLista categoria, SerieTV serie) {
        return getLista(categoria).remove(serie);
    }

    // diz se a serie ja ta na lista dessa categoria
    public boolean estaNaLista(CategoriaLista categoria, SerieTV serie) {
        return getLista(categoria).contains(serie);
    }

    public void adicionarFavorito(SerieTV serie) {
        adicionarNaLista(CategoriaLista.FAVORITOS, serie);
    }

    public void removerFavorito(SerieTV serie) {
        removerDaLista(CategoriaLista.FAVORITOS, serie);
    }

    public void adicionarAssistida(SerieTV serie) {
        adicionarNaLista(CategoriaLista.ASSISTIDAS, serie);
    }

    public void removerAssistida(SerieTV serie) {
        removerDaLista(CategoriaLista.ASSISTIDAS, serie);
    }

    public void adicionarDesejo(SerieTV serie) {
        adicionarNaLista(CategoriaLista.DESEJO_ASSISTIR, serie);
    }

    public void removerDesejo(SerieTV serie) {
        removerDaLista(CategoriaLista.DESEJO_ASSISTIR, serie);
    }
}

enum CategoriaLista {
    FAVORITOS("Favoritos"),
    ASSISTIDAS("Já Assistidas"),
    DESEJO_ASSISTIR("Quero Assistir");

    private final String rotulo;

    CategoriaLista(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getRotulo() {
        return rotulo;
    }
}