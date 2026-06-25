package model;

import java.util.ArrayList;
import java.util.List;

public class Serie {
    private String nome;
    private String idioma;
    private List<String> generos;
    private double notaGeral;
    private String estado;
    private String dataEstreia;
    private String dataTermino;
    private String emissora;

    public Serie(String nome, String idioma, List<String> generos, double notaGeral, 
                 String estado, String dataEstreia, String dataTermino, String emissora) {
        this.nome = nome != null ? nome : "Desconhecido";
        this.idioma = idioma != null ? idioma : "N/A";
        this.generos = generos != null ? generos : new ArrayList<>();
        this.notaGeral = notaGeral;
        this.estado = estado != null ? estado : "N/A";
        this.dataEstreia = dataEstreia != null ? dataEstreia : "N/A";
        this.dataTermino = dataTermino != null ? dataTermino : "N/A";
        this.emissora = emissora != null ? emissora : "N/A";
    }

    public String getNome() { return nome; }
    public String getIdioma() { return idioma; }
    public List<String> getGeneros() { return generos; }
    public double getNotaGeral() { return notaGeral; }
    public String getEstado() { return estado; }
    public String getDataEstreia() { return dataEstreia; }
    public String getDataTermino() { return dataTermino; }
    public String getEmissora() { return emissora; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Serie)) return false;
        return this.nome.equalsIgnoreCase(((Serie) obj).getNome());
    }
}