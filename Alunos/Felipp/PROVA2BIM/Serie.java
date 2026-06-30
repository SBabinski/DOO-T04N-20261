package Fag;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

// modelo que representa uma serie de tv.
// os dados chegam da api tvmaze e os mesmos objetos sao salvos em json.
// a anotacao abaixo faz o jackson ignorar campos do json que nao usamos.
@JsonIgnoreProperties(ignoreUnknown = true)
public class Serie {

    private int id;
    private String nome;
    private String idioma;
    private List<String> generos;
    private Double nota;            // pode ser null quando a api nao tem nota
    private EstadoSerie estado;
    private String dataEstreia;     // formato textual "aaaa-mm-dd"
    private String dataTermino;     // null quando a serie ainda esta em exibicao
    private String emissora;

    // construtor vazio exigido pelo jackson para reconstruir o objeto a partir do json.
    public Serie() {
        this.generos = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public List<String> getGeneros() { return generos; }
    public void setGeneros(List<String> generos) { this.generos = generos; }

    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }

    public EstadoSerie getEstado() { return estado; }
    public void setEstado(EstadoSerie estado) { this.estado = estado; }

    public String getDataEstreia() { return dataEstreia; }
    public void setDataEstreia(String dataEstreia) { this.dataEstreia = dataEstreia; }

    public String getDataTermino() { return dataTermino; }
    public void setDataTermino(String dataTermino) { this.dataTermino = dataTermino; }

    public String getEmissora() { return emissora; }
    public void setEmissora(String emissora) { this.emissora = emissora; }

    // converte a data textual em localdate para permitir ordenar por estreia.
    // devolve null quando nao ha data valida. o jsonignore evita que o jackson
    // tente serializar esse localdate (que precisaria de um modulo extra).
    @JsonIgnore
    public LocalDate getDataEstreiaComoData() {
        if (dataEstreia == null || dataEstreia.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(dataEstreia);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @JsonIgnore
    public String getGenerosTexto() {
        if (generos == null || generos.isEmpty()) {
            return "não informado";
        }
        return String.join(", ", generos);
    }

    @JsonIgnore
    public String getNotaTexto() {
        return nota == null ? "sem nota" : String.valueOf(nota);
    }

    // duas series sao consideradas iguais quando tem o mesmo id da tvmaze.
    // isso evita itens repetidos dentro das listas do usuario.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Serie)) return false;
        Serie outra = (Serie) obj;
        return this.id == outra.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    // as listas da interface grafica usam isto para mostrar o nome da serie.
    @Override
    public String toString() {
        return nome;
    }
}
