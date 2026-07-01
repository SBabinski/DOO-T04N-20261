package PROVA2BIM;

import java.util.List;
import java.util.ArrayList;

public class Serie {

    private int id;
    private String nome;
    private String idioma;
    private List<String> generos;
    private double nota;
    private String estado;
    private String dataEstreia;
    private String dataTermino;
    private String emissora;
    private String resumo;
    private String imagemUrl;

    public Serie() {
        this.generos = new ArrayList<>();
    }

    public Serie(int id, String nome, String idioma, List<String> generos,
                 double nota, String estado, String dataEstreia,
                 String dataTermino, String emissora, String resumo, String imagemUrl) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos != null ? generos : new ArrayList<>();
        this.nota = nota;
        this.estado = estado;
        this.dataEstreia = dataEstreia;
        this.dataTermino = dataTermino;
        this.emissora = emissora;
        this.resumo = resumo;
        this.imagemUrl = imagemUrl;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public List<String> getGeneros() { return generos; }
    public void setGeneros(List<String> generos) { this.generos = generos; }

    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getDataEstreia() { return dataEstreia; }
    public void setDataEstreia(String dataEstreia) { this.dataEstreia = dataEstreia; }

    public String getDataTermino() { return dataTermino; }
    public void setDataTermino(String dataTermino) { this.dataTermino = dataTermino; }

    public String getEmissora() { return emissora; }
    public void setEmissora(String emissora) { this.emissora = emissora; }

    public String getResumo() { return resumo; }
    public void setResumo(String resumo) { this.resumo = resumo; }

    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }

    public String getGenerosFormatado() {
        if (generos == null || generos.isEmpty()) return "N/A";
        return String.join(", ", generos);
    }

    public String getEstadoTraduzido() {
        if (estado == null) return "Desconhecido";
        switch (estado.toLowerCase()) {
            case "running": return "Em exibição";
            case "ended": return "Encerrada";
            case "to be determined": return "A ser determinado";
            case "in development": return "Em desenvolvimento";
            default: return estado;
        }
    }

    public String getNotaFormatada() {
        if (nota <= 0) return "N/A";
        return String.format("%.1f", nota);
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Serie)) return false;
        Serie other = (Serie) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
