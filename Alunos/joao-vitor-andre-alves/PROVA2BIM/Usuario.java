import java.util.List;
import java.util.ArrayList;

public class Usuario {

    private String nomeOuApelido;
    private List<Serie> favoritas;
    private List<Serie> assistidas;
    private List<Serie> desejoAssistir;

    public Usuario() {
        this.favoritas = new ArrayList<>();
        this.assistidas = new ArrayList<>();
        this.desejoAssistir = new ArrayList<>();
    }

    public Usuario(String nomeOuApelido) {
        this();
        this.nomeOuApelido = nomeOuApelido;
    }

    public String getNomeOuApelido() {
        return nomeOuApelido;
    }

    public void setNomeOuApelido(String nomeOuApelido) {
        this.nomeOuApelido = nomeOuApelido;
    }

    public List<Serie> getFavoritas() {
        return favoritas;
    }

    public List<Serie> getAssistidas() {
        return assistidas;
    }

    public List<Serie> getDesejoAssistir() {
        return desejoAssistir;
    }

    public void adicionarFavorita(Serie serie) {
        adicionar(favoritas, serie);
    }

    public void removerFavorita(Serie serie) {
        favoritas.remove(serie);
    }

    public void adicionarAssistida(Serie serie) {
        adicionar(assistidas, serie);
    }

    public void removerAssistida(Serie serie) {
        assistidas.remove(serie);
    }

    public void adicionarDesejoAssistir(Serie serie) {
        adicionar(desejoAssistir, serie);
    }

    public void removerDesejoAssistir(Serie serie) {
        desejoAssistir.remove(serie);
    }

    private void adicionar(List<Serie> lista, Serie serie) {
        if (!lista.contains(serie)) {
            lista.add(serie);
        }
    }
}