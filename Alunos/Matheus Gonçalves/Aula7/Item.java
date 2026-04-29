package fag;

public class Item {
	private int id;
    private String nome;
    private String tipo;
    private double valor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            System.out.println("ID inválido!");
        }
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isBlank()) {
            this.nome = nome;
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        if (tipo != null && !tipo.isBlank()) {
            this.tipo = tipo;
        }
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        if (valor > 0) {
            this.valor = valor;
        }
    }

    public void gerarDescricao() {
        System.out.println("ID: " + id +
                " | Nome: " + nome +
                " | Tipo: " + tipo +
                " | Valor: R$" + valor);
    }
}