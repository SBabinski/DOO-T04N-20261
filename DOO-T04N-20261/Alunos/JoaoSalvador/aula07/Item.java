package calculadora_dona_gabrielinha;

public class Item {

    // Variáveis
    private int id;
    private String nome;
    private String tipo;
    private double valor;

    // Molde de dados
    public Item(int id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    // Getters
    public int getId() { return id;    }
    public String getNome() { return nome;  }
    public String getTipo() { return tipo;  }
    public double getValor() { return valor; }

    // Mostra os dados do item
    public void gerarDescricao() {
        System.out.printf("Item #%d | %-20s | Tipo: %-15s | Valor: R$ %.2f%n", id, nome, tipo, valor);
    }
}
