

public class Endereco {
    String estado;
    String cidade;
    String bairro;
    int numero;
    String complemento;

    // Construtor para a classe Endereco
    public Endereco(String estado, String cidade, String bairro, int numero, String complemento){
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }
    // Método para apresentar o logradouro completo do endereço, formatando as informações de forma legível
    public void apresentarLogradouro() {
        StringBuilder logradouro = new StringBuilder();
        logradouro.append("Endereço: ");
        if (complemento != null && !complemento.isEmpty()) {
            logradouro.append(complemento).append(", ");
        }
        logradouro.append("nº ").append(numero).append(", ");
        logradouro.append(bairro).append(" - ");
        logradouro.append(cidade).append(" / ").append(estado);
        System.out.println(logradouro.toString());
    }
}
