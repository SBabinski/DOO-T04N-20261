import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Serie {

    private int id;
    private String nome;
    private String idioma;
    private List<String> generos;
    private Double notaGeral;        // pode ser null se a API nao fornecer
    private StatusSerie status;
    private String statusOriginal;   // texto cru vindo da API
    private String dataEstreia;      // formato yyyy-MM-dd
    private String dataTermino;      // pode ser null
    private String emissora;
    private String urlImagem;
    private String resumo;

    public Serie() {
        this.generos = new ArrayList<>();
        this.status = StatusSerie.DESCONHECIDO;
    }

    public Serie(int id, String nome) {
        this();
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = (generos != null) ? generos : new ArrayList<>();
    }

    public Double getNotaGeral() {
        return notaGeral;
    }

    public void setNotaGeral(Double notaGeral) {
        this.notaGeral = notaGeral;
    }

    public StatusSerie getStatus() {
        return status;
    }

    public void setStatus(StatusSerie status) {
        this.status = (status != null) ? status : StatusSerie.DESCONHECIDO;
    }

    public String getStatusOriginal() {
        return statusOriginal;
    }

    public void setStatusOriginal(String statusOriginal) {
        this.statusOriginal = statusOriginal;
    }

    public String getDataEstreia() {
        return dataEstreia;
    }

    public void setDataEstreia(String dataEstreia) {
        this.dataEstreia = dataEstreia;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    public String getEmissora() {
        return emissora;
    }

    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getGenerosFormatados() {
        if (generos == null || generos.isEmpty()) {
            return "-";
        }
        return String.join(", ", generos);
    }

    public String getNotaFormatada() {
        return (notaGeral != null) ? String.format("%.1f", notaGeral) : "-";
    }
    //Duas series iguais se possuem o mesmo ID no TVMaze
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return id == serie.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return nome + (notaGeral != null ? " (" + getNotaFormatada() + ")" : "");
    }
}
