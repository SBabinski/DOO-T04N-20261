import java.util.ArrayList;
import java.util.List;

public class Usuario {
    //essa tambem, serve pra criar o obejto usuario com as informaçoes definidas

    private String nome;

    private List<Serie> favoritos;
    private List<Serie> assistidas;
    private List<Serie> desejaAssistir;

    // construtor vazio pro gson conseguir recriar o objetda
    public Usuario() {

    }

    public Usuario(String nome) {

        this.nome = nome;

        favoritos = new ArrayList<>();
        assistidas = new ArrayList<>();
        desejaAssistir = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public List<Serie> getFavoritos() {
        return favoritos;
    }

    public List<Serie> getAssistidas() {
        return assistidas;
    }

    public List<Serie> getDesejaAssistir() {
        return desejaAssistir;
    }

    public void adicionarFavorito(Serie serie) {
        favoritos.add(serie);
    }

    public void removerFavorito(Serie serie) {
        favoritos.remove(serie);
    }

    public void adicionarAssistida(Serie serie) {
        assistidas.add(serie);
    }

    public void removerAssistida(Serie serie) {
        assistidas.remove(serie);
    }

    public void adicionarDesejaAssistir(Serie serie) {
        desejaAssistir.add(serie);
    }

    public void removerDesejaAssistir(Serie serie) {
        desejaAssistir.remove(serie);
    }
}