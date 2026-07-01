package ListTV;

import java.util.ArrayList;
import java.util.List;

public class SerieTV {

    private int id;
    private String nome;
    private String idioma;
    private List<String> generos;
    private Double nota;       // pode ser nulo, caso a serie ainda nao tenha nota
    private String status;
    private String estreia;
    private String termino;
    private String emissora;

    // construtor vazio, cria a lista de generos ja inicializada
    public SerieTV() {
        this.generos = new ArrayList<>();
    }

    // construtor completo, usado tanto pela busca na API quanto ao carregar do arquivo
    public SerieTV(int id, String nome, String idioma, List<String> generos,
                   Double nota, String status, String estreia,
                   String termino, String emissora) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        // se vier nulo, cria uma lista vazia pra nao dar erro depois
        this.generos = (generos != null) ? generos : new ArrayList<>();
        this.nota = nota;
        this.status = status;
        this.estreia = estreia;
        this.termino = termino;
        this.emissora = emissora;
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
    public Double getNota() {
        return nota;
    }
    public void setNota(Double nota) {
        this.nota = nota;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getEstreia() {
        return estreia;
    }
    public void setEstreia(String estreia) {
        this.estreia = estreia;
    }
    public String getTermino() {
        return termino;
    }
    public void setTermino(String termino) {
        this.termino = termino;
    }

    public String getEmissora() {
        return emissora;
    }
    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    public String getGenerosComoTexto() {
        if (generos == null || generos.isEmpty()) return "N/A";
        return String.join(", ", generos);
    }

    public String getNotaComoTexto() {
        return (nota == null) ? "N/A" : String.valueOf(nota);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SerieTV)) return false;
        return this.id == ((SerieTV) o).id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return nome + " (" + idioma + ")";
    }
}