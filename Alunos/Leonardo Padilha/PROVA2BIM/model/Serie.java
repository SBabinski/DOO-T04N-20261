package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Serie {
    private int id;
    private String nome;
    private String status;
    private String dataEstreia;
    private String dataFim;
    private double nota;
    private String emissora;
    private String generos;
    private String sumario;
    private String idioma;

    public Serie() {}

    public Serie(int id, String nome, String status, String dataEstreia, String dataFim, double nota,
                 String emissora, String generos, String sumario, String idioma) {
        this.id = id;
        this.nome = nome;
        this.status = status;
        this.dataEstreia = dataEstreia;
        this.dataFim = dataFim;
        this.nota = nota;
        this.emissora = emissora;
        this.generos = generos;
        this.sumario = sumario;
        this.idioma = idioma;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getDataEstreia() { return dataEstreia; }
    public void setDataEstreia(String dataEstreia) { this.dataEstreia = dataEstreia; }

    public String getDataFim() { return dataFim; }
    public void setDataFim(String dataFim) { this.dataFim = dataFim; }

    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }

    public String getEmissora() { return emissora; }
    public void setEmissora(String emissora) { this.emissora = emissora; }

    public String getGeneros() { return generos; }
    public void setGeneros(String generos) { this.generos = generos; }

    public String getSumario() { return sumario; }
    public void setSumario(String sumario) { this.sumario = sumario; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Serie outra = (Serie) obj;
        return this.id == outra.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}