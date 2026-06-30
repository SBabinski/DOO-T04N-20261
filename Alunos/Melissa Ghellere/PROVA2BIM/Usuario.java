import java.util.ArrayList;
import java.util.List;

// quem ta usando o app 
public class Usuario {
    
    
    private String nome = "Melissa Ghellere";
    private String apelido = "Mel";
    
    
    private List<Serie> favoritos = new ArrayList<>();
    private List<Serie> jaAssistidas = new ArrayList<>();
    private List<Serie> desejaAssistir = new ArrayList<>();

    public Usuario() {}

    // adiciona na lista sem repetir (🚫 duplicatas)
    public void adicionarFavorito(Serie serie) {
        if (!favoritos.contains(serie)) favoritos.add(serie);
    }
    
    public void adicionarJaAssistida(Serie serie) {
        if (!jaAssistidas.contains(serie)) jaAssistidas.add(serie);
    }
    
    public void adicionarDesejaAssistir(Serie serie) {
        if (!desejaAssistir.contains(serie)) desejaAssistir.add(serie);
    }

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