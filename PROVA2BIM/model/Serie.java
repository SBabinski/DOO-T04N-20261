package tvtracker.model;

import java.util.List; // interface para listas genéricas



public class Serie {

//private encapsulamento
    private int         id;          
    private String      nome;        
    private String      idioma;      
    private List<String>generos;     
    private Double      nota;        
    private String      estado;     
    private String      estreia;     
    private String      termino;     
    private String      emissora;    
    private String      sinopse;     
    private String      urlImagem;   

    public Serie() {} 

   //aq converte os dados recevbiodos pela api
    public Serie(int id, String nome, String idioma, List<String> generos,
                 Double nota, String estado, String estreia, String termino,
                 String emissora, String sinopse, String urlImagem) {
        this.id        = id;    
        this.nome      = nome;      
        this.idioma    = idioma;   
        this.generos   = generos;  
        this.nota      = nota;       
        this.estado    = estado;    
        this.estreia   = estreia;  
        this.termino   = termino;  
        this.emissora  = emissora;  
        this.sinopse   = sinopse;   
        this.urlImagem = urlImagem; 
    }


    public int getId()          { return id; }                                    
    public String getNome()     { return nome      != null ? nome      : "N/A"; } 
    public String getIdioma()   { return idioma    != null ? idioma    : "N/A"; } 
    public List<String> getGeneros() { return generos; }                          
    public Double getNota()     { return nota; }                                  
    public String getEstado()   { return estado    != null ? estado    : "N/A"; } 
    public String getEstreia()  { return estreia   != null ? estreia   : "N/A"; } 
    public String getTermino()  { return termino   != null ? termino   : "N/A"; } 
    public String getEmissora() { return emissora  != null ? emissora  : "N/A"; } 
    public String getSinopse()  { return sinopse   != null ? sinopse   : "Sem sinopse."; } 
    public String getUrlImagem(){ return urlImagem; }                            


    public void setId(int id)              { this.id        = id; } 
    public void setNome(String nome)       { this.nome      = nome; } 
    public void setIdioma(String idioma)   { this.idioma    = idioma; } 
    public void setGeneros(List<String> g) { this.generos   = g; } 
    public void setNota(Double nota)       { this.nota      = nota; } 
    public void setEstado(String estado)   { this.estado    = estado; } 
    public void setEstreia(String estreia) { this.estreia   = estreia; } 
    public void setTermino(String termino) { this.termino   = termino; } 
    public void setEmissora(String e)      { this.emissora  = e; }       
    public void setSinopse(String sinopse) { this.sinopse   = sinopse; } 
    public void setUrlImagem(String url)   { this.urlImagem = url; }     

  //metodo auxiliar de nota
    public String getNotaFormatada() {
        if (nota == null || nota <= 0) return "N/A"; //sem nota disponível
        return String.format("%.1f", nota);  //formata com uma casa decimal
    }

 //retorna os generos
    public String getGenerosTexto() {
        if (generos == null || generos.isEmpty()) return "N/A"; //quando n tem genero
        return String.join(", ", generos);    //une com vírgula e espaço
    }

  
    public String getEstadoTraduzido() {
        if (estado == null) return "N/A";                        
        if (estado.equals("Running"))           return "Em exibição";      
        if (estado.equals("Ended"))             return "Encerrada";  
        if (estado.equals("To Be Determined"))  return "A definir";       
        if (estado.equals("In Development"))    return "Em desenvolvimento"; 
        return estado; 
    }

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

    @Override
    public String toString() {
        return getNome(); // exibe apenas o nome da série
    }
}
