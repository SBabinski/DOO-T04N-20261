public class Cliente extends Pessoa {

	public String apresentarSe() {
		return "Olá, meu nome é " + nome + " e eu tenho " + idade + " anos!";
	}

	public void listarCliente() {
        System.out.println("--------------------------");
		System.out.println("Nome: " + nome);
		System.out.println("Idade: " + idade);
		System.out.println("Bairro: " + bairro);
		System.out.println("Rua: " + rua);
		System.out.println("--------------------------");
	}
}