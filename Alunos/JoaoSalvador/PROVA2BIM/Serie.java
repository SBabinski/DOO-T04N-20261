package Fag;

import java.util.ArrayList;
import java.util.List;

public class Serie {
    private int id;
    private String nome;
    private String idioma;
    private List<String> generos = new ArrayList<String>();
    private double notaGeral;
    private String estado;
    private String dataEstreia;
    private String dataTermino;
    private String emissora;

    public Serie() {
    }

    // Construtor da série
    public Serie(int id, String nome, String idioma, List<String> generos, double notaGeral,
            String estado, String dataEstreia, String dataTermino, String emissora) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = generos;
        this.notaGeral = notaGeral;
        this.estado = estado;
        this.dataEstreia = dataEstreia;
        this.dataTermino = dataTermino;
        this.emissora = emissora;
    }

    // Retorna ID
    public int getId() {
        return id;
    }

    // Seta ID
    public void setId(int id) {
        this.id = id;
    }

    // Retorna nome
    public String getNome() {
        return nome;
    }

    // Seta nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Retorna idioma
    public String getIdioma() {
        return idioma;
    }

    // Seta idioma
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    // Retorna generos
    public List<String> getGeneros() {
        return generos;
    }

    // Seta generos
    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    // Retorna nota geral
    public double getNotaGeral() {
        return notaGeral;
    }

    // Seta nota geral
    public void setNotaGeral(double notaGeral) {
        this.notaGeral = notaGeral;
    }

    // Retorna estado
    public String getEstado() {
        return estado;
    }

    // Seta estado
    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Retorna data de estreia
    public String getDataEstreia() {
        return dataEstreia;
    }

    // Seta data de estreia
    public void setDataEstreia(String dataEstreia) {
        this.dataEstreia = dataEstreia;
    }

    // Retorna data de término
    public String getDataTermino() {
        return dataTermino;
    }

    // Seta data de término
    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }

    // Retorna emissora
    public String getEmissora() {
        return emissora;
    }

    // Seta emissora
    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }

    // Retorna os gêneros como texto
    public String getGenerosTexto() {
        if (generos == null || generos.isEmpty()) {
            return "Não informado";
        }
        return String.join(", ", generos);
    }
}
