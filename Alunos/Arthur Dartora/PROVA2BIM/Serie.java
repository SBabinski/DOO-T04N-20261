import java.util.*;

//representa uma serie
public class Serie {

    //atributos privados (encapsulamento)
    private int id;
    private String nome;
    private String idioma;
    private List<String> generos;
    private Double notaGeral;
    private String estado;
    private String dataEstreia;
    private String dataTermino;
    private String emissora;
    private String resumo;

    public Serie() {
        this.generos = new ArrayList<String>();
    }

    // construtor completo usado p criar a serie
    public Serie(int id, String nome, String idioma, List<String> generos,
                 Double notaGeral, String estado, String dataEstreia,
                 String dataTermino, String emissora, String resumo) {
        this.id = id;
        this.nome = nome;
        this.idioma = idioma;
        this.generos = (generos == null) ? new ArrayList<String>() : generos;
        this.notaGeral = notaGeral;
        this.estado = estado;
        this.dataEstreia = dataEstreia;
        this.dataTermino = dataTermino;
        this.emissora = emissora;
        this.resumo = resumo;
    }

    // getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getIdioma() { return idioma; }
    public List<String> getGeneros() { return generos; }
    public Double getNotaGeral() { return notaGeral; }
    public String getEstado() { return estado; }
    public String getDataEstreia() { return dataEstreia; }
    public String getDataTermino() { return dataTermino; }
    public String getEmissora() { return emissora; }
    public String getResumo() { return resumo; }

    // setters
    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public void setGeneros(List<String> generos) { this.generos = generos; }
    public void setNotaGeral(Double notaGeral) { this.notaGeral = notaGeral; }
    public void setEstado(String estado) { this.estado = estado; }
    public void setDataEstreia(String dataEstreia) { this.dataEstreia = dataEstreia; }
    public void setDataTermino(String dataTermino) { this.dataTermino = dataTermino; }
    public void setEmissora(String emissora) { this.emissora = emissora; }
    public void setResumo(String resumo) { this.resumo = resumo; }

    // mostra o nome da serie na lista 
    @Override
    public String toString() {
        if (nome == null || nome.isEmpty()) {
            return "(sem nome)";
        }
        return nome;
    }
}
