public class Vendedor extends Funcionario {

    public Vendedor(String nome, int idade, Endereco endereco, String loja, float salBase) {
        super(nome, idade, endereco, loja, salBase);
    }

    public float calcularBonus() {
        return salBase * 0.20f;
    }
}