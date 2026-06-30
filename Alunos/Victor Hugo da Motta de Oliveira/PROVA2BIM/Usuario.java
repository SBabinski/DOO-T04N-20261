import java.util.ArrayList;
import java.util.List;

// classe pra guardar quem tá usando e organizar as listas dele
public class Usuario {
    
    private String nome = "Victor Hugo da Motta de Olivera";
    private String apelido = "Japa";
    
    // listas independentes de séries
    private List<Serie> favoritos = new ArrayList<>();
    private List<Serie> jaAssistidas = new ArrayList<>();
    private List<Serie> desejaAssistir = new ArrayList<>();

    public Usuario() {}

    // metodo pra adicionar séries nas listas verificando se ja existe pra nao duplicar
    public void adicionarFavorito(Serie serie) {
        if (!favoritos.contains(serie)) favoritos.add(serie);
    }
    
    public void adicionarJaAssistida(Serie serie) {
        if (!jaAssistidas.contains(serie)) jaAssistidas.add(serie);
    }
    
    public void adicionarDesejaAssistir(Serie serie) {
        if (!desejaAssistir.contains(serie)) desejaAssistir.add(serie);
    }

    // getters e setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }

    public List<Serie> getFavoritos() { return favoritos; }
    public void setFavoritos(List<Serie> favoritos) { this.favoritos = favoritos; }

    public List<Serie> getJaAssistidas() { return jaAssistidas; }
    public void setJaAssistidas(List<Serie> jaAssistidas) { this.jaAssistidas = jaAssistidas; }

    public List<Serie> getDesejaAssistir() { return desejaAssistir; }
    public void setDesejaAssistir(List<Serie> desejaAssistir) { this.desejaAssistir = desejaAssistir; }
}