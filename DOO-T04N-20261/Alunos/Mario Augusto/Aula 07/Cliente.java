public class Cliente extends Pessoa {

    Endereco endereco;

    Cliente(String nome, int idade, Endereco endereco) {
        super(nome, idade);
        this.endereco = endereco;
    }

    @Override
    public void apresentarse() {
        super.apresentarse();
        endereco.apresentarLogradouro();
    }
}
