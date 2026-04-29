package Objetos;

public class Endereco {

    private String estado;
    private String cidade;
    private String bairro;
    private int numero;
    private String complemento;

    public String apresentarLogradouro() {
        return bairro + ", " + numero + " - " + cidade + " (" + estado + ") " + complemento;
    }


    public void setEstado(String estado) {
    	this.estado = estado;
    	}
    
    public void setCidade(String cidade) {
    	this.cidade = cidade;
    	}
    
    public void setBairro(String bairro) {
    	this.bairro = bairro;
    	}
    
    public void setNumero(int numero) {
    	this.numero = numero;
    	}
    
    public void setComplemento(String complemento) {
    	this.complemento = complemento;
    	}
    
}