public class Hospede {

    private String nome;
    private String cpf;
    private String telefone;


    public Hospede() {
    }

    public Hospede(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean setCpf(String cpf) {
        String apenasNumeros = cpf.replaceAll("[^0-9]", "");
        if (apenasNumeros.length() == 11) {
            this.cpf = apenasNumeros;
            return true;
        } else {
            return false;
        }
    }

    public boolean settelefone(String telefone) {
        String apenasNumeros = telefone.replaceAll("[^0-9]", "");
        if (apenasNumeros.length() == 11) {
            this.telefone = apenasNumeros;
            return true;
        } else {
            return false;
        }
    }

    public String gettelefone() {
        return this.telefone;
    }

}