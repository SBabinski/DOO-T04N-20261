public class Gerente extends Funcionario {

    public Gerente(String nome, int idade, Endereco endereco, String loja, float salBase) {
        super(nome, idade, endereco, loja, salBase);
    }

    public float calcularBonus() {
        return salBase * 0.35f;
    }
}