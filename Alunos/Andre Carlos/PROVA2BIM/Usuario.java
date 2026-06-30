import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private String nome;
    private List<Serie> favoritos;
    private List<Serie> assistidas;
    private List<Serie> desejaAssistir;

    public Usuario() {
        this.favoritos = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.desejaAssistir = new ArrayList<>();
    }

    public Usuario(String nome) {
        this();
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Serie> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Serie> favoritos) {
        this.favoritos = (favoritos != null) ? favoritos : new ArrayList<>();
    }

    public List<Serie> getAssistidas() {
        return assistidas;
    }

    public void setAssistidas(List<Serie> assistidas) {
        this.assistidas = (assistidas != null) ? assistidas : new ArrayList<>();
    }

    public List<Serie> getDesejaAssistir() {
        return desejaAssistir;
    }

    public void setDesejaAssistir(List<Serie> desejaAssistir) {
        this.desejaAssistir = (desejaAssistir != null) ? desejaAssistir : new ArrayList<>();
    }


    public List<Serie> getLista(TipoLista tipo) {
        switch (tipo) {
            case FAVORITOS:
                return favoritos;
            case ASSISTIDAS:
                return assistidas;
            case DESEJA_ASSISTIR:
                return desejaAssistir;
            default:
                throw new IllegalArgumentException("Tipo de lista invalido: " + tipo);
        }
    }
}
