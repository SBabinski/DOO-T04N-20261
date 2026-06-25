
public class Item {

    private static int proximoId = 1;
    int id;
    String nome;
    String tipo;
    double valor;

    // Construtor para a classe Item    
    public Item(String nome, String tipo, double valor) {
        this.id = proximoId++;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    // Getters e Setters para os atributos da classe Item
    public int getId() {
    return id;
}
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void gerarDescricao() {
        System.out.println("ID: " + id + " | Nome: " + nome + " | Tipo: " + tipo + " | Valor: R$ " + valor);
    }

}
