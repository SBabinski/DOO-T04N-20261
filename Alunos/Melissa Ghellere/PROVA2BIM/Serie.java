import java.util.ArrayList;
import java.util.List;

// guarda os dados de cada serie 📺
public class Serie {
    
    private String name;
    private String language;
    private List<String> genres = new ArrayList<>();
    private double score;
    private String status;
    private String premiered;
    private String ended;
    private String networkName;
    private String imageUrl;

    public Serie() {}

    // getters e setters para acessar tudo em seguranca (🔒)
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPremiered() { return premiered; }
    public void setPremiered(String premiered) { this.premiered = premiered; }

    public String getEnded() { return ended; }
    public void setEnded(String ended) { this.ended = ended; }

    public String getNetworkName() { return networkName; }
    public void setNetworkName(String networkName) { this.networkName = networkName; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    // mostra o nome bonitinho na tela (✨)
    @Override
    public String toString() {
        return this.name;
    }
}