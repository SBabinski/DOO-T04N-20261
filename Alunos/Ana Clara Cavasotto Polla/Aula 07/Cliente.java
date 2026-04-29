package fag.objetos;

public class Cliente extends Pessoa {

	public Cliente(String nome, int idade, Endereco endereco, Loja loja) {
		super(nome, idade, endereco, loja);
	}

	// metodos

	@Override
	public void apresentar() {
		System.out.printf("Cliente - Nome: %s | Idade: %d%n", getNome(), getIdade());
	}
}
